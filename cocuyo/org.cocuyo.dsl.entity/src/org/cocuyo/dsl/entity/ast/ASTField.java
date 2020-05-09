package org.cocuyo.dsl.entity.ast;

//open-imports
import org.cocuyo.dsl.entity.Entity;
import org.cocuyo.dsl.entity.EntityField;
import org.cocuyo.dsl.entity.type.EntityFieldType;

//close-imports
public class ASTField implements ASTEntityElement
//open-inheritance
//close-inheritance
{
	
	private ASTModifier fModifier;
	private ASTId fId;
	private ASTFieldType fFieldType;
	
	public ASTField(ASTModifier aModifier, ASTId aId, ASTFieldType aFieldType)
	{
		this.fModifier = aModifier;
		this.fId = aId;
		this.fFieldType = aFieldType;
	}
	public ASTField(ASTId aId, ASTFieldType aFieldType)
	{
		this.fId = aId;
		this.fFieldType = aFieldType;
	}
	
	//open-members
	Entity fEntity;
	EntityField _field;

	@Override
	public void build(Entity aEntity) {
		fEntity = aEntity;
		_field = new EntityField(fId.getToken().getText());
		aEntity.defineField(_field);

		if (fModifier != null) {
			fModifier.build(_field);
		}
	}

	public void buildType() {
		EntityFieldType _type = fFieldType.build(fEntity);
		_field.setType(_type);
	}

	//close-members
	
}