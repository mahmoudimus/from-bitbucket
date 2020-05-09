package org.cocuyo.dsl.entity.ast;

//open-imports
import org.cocuyo.dsl.entity.DomainModel;
import org.cocuyo.dsl.entity.Entity;
import org.cocuyo.dsl.protocol.InvalidNameClassException;
import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.parsing.RecognitionError;
import org.cocuyo.parsing.RecognitionException;

//close-imports
public class ASTEntity implements ASTModelElement
//open-inheritance
//close-inheritance
{
	
	private ASTId fId;
	private ASTName fSuper;
	private ASTEntityElementList fEntityElementList;
	
	public ASTEntity(ASTId aId, ASTName aSuper, ASTEntityElementList aEntityElementList)
	{
		this.fId = aId;
		this.fSuper = aSuper;
		this.fEntityElementList = aEntityElementList;
	}
	public ASTEntity(ASTId aId, ASTEntityElementList aEntityElementList)
	{
		this.fId = aId;
		this.fEntityElementList = aEntityElementList;
	}
	
	//open-members

	private Entity fEntity;

	@Override
	public void buildModel(DomainModel aModel) {
		fEntity = new Entity(fId.getToken().getText(), aModel);
		aModel.define(fEntity);
	}

	@Override
	public void buildElements() {
		if (fSuper != null) {
			try {
				Entity _super = fEntity.getModel().findObject(Entity.class,
						fSuper.getIds());
				fEntity.setSuperEntity(_super);
			} catch (NameNotFoundException _e) {
				throw new RecognitionException(new RecognitionError(_e
						.getMessage(), fSuper.get(0).getToken()));
			} catch (InvalidNameClassException _e) {
				throw new RecognitionException(new RecognitionError(_e
						.getMessage(), fSuper.get(0).getToken()));
			}
		}
		for (ASTEntityElement _elem : fEntityElementList) {
			_elem.build(fEntity);
		}
	}

	@Override
	public void buildTypes() {
		for (ASTEntityElement _elem : fEntityElementList) {
			_elem.buildType();
		}
	}
	//close-members
	
}