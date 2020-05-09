package org.cocuyo.dsl.entity.ast;

//open-imports
import org.cocuyo.dsl.entity.DomainModel;
import org.cocuyo.dsl.entity.type.EntityFieldType;
import org.cocuyo.dsl.entity.type.EntityFieldTypeDecorator;
import org.cocuyo.parsing.RecognitionError;
import org.cocuyo.parsing.RecognitionException;

//close-imports
public class ASTType implements ASTModelElement
//open-inheritance
//close-inheritance
{
	
	private ASTId fId;
	private ASTFieldType fFieldType;
	private ASTStringLiteral fClassName;
	
	public ASTType(ASTId aId, ASTFieldType aFieldType)
	{
		this.fId = aId;
		this.fFieldType = aFieldType;
	}
	public ASTType(ASTId aId, ASTStringLiteral aClassName)
	{
		this.fId = aId;
		this.fClassName = aClassName;
	}
	
	//open-members
	private EntityFieldType fType;
	DomainModel fModel;

	@Override
	public void buildModel(DomainModel aModel) {
		fModel = aModel;
	}

	@Override
	public void buildElements() {
		if (fClassName != null) {
			try {
				fType = EntityFieldType.loadFromClasspath(fClassName.getText(),
						fModel);
			} catch (ClassNotFoundException _e) {
				throw new RecognitionException(new RecognitionError("Class '"
						+ fClassName.getText() + "' not found.", fClassName
						.getToken()));
			}
		} else {
			fType = new EntityFieldTypeDecorator(fId.getToken().getText(), null);
		}
		fModel.define(fType);
	}

	@Override
	public void buildTypes() {
		if (fClassName == null)
			((EntityFieldTypeDecorator) fType).setBaseType(fFieldType
					.build(fModel));
	}
	//close-members
	
}