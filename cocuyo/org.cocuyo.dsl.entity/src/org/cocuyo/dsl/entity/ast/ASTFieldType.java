package org.cocuyo.dsl.entity.ast;

//open-imports
import org.cocuyo.dsl.entity.DomainModel;
import org.cocuyo.dsl.entity.Entity;
import org.cocuyo.dsl.entity.type.EntityFieldType;
import org.cocuyo.dsl.protocol.InvalidNameClassException;
import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.parsing.RecognitionError;
import org.cocuyo.parsing.RecognitionException;

@SuppressWarnings("unused")
//close-imports
public class ASTFieldType implements IASTRoot
//open-inheritance
//close-inheritance
{
	
	private ASTName fName;
	private ASTCardinality fCardinality;
	private ASTTypeOptionList fTypeOptionList;
	
	public ASTFieldType(ASTName aName, ASTCardinality aCardinality, ASTTypeOptionList aTypeOptionList)
	{
		this.fName = aName;
		this.fCardinality = aCardinality;
		this.fTypeOptionList = aTypeOptionList;
	}
	public ASTFieldType(ASTName aName, ASTCardinality aCardinality)
	{
		this.fName = aName;
		this.fCardinality = aCardinality;
	}
	public ASTFieldType(ASTName aName, ASTTypeOptionList aTypeOptionList)
	{
		this.fName = aName;
		this.fTypeOptionList = aTypeOptionList;
	}
	public ASTFieldType(ASTName aName)
	{
		this.fName = aName;
	}
	
	//open-members
	public EntityFieldType build(Entity aEntity) {
		return build(aEntity.getModel());
	}

	public EntityFieldType build(DomainModel aModel) {
		EntityFieldType _type;
		try {
			_type = aModel.findObject(EntityFieldType.class, fName.getIds());
		} catch (NameNotFoundException _e) {
			throw new RecognitionException(new RecognitionError(
					_e.getMessage(), fName.get(0).getToken()));
		} catch (InvalidNameClassException _e) {
			throw new RecognitionException(new RecognitionError(
					_e.getMessage(), fName.get(0).getToken()));
		}

		return _type;
	}

	//close-members
	
}