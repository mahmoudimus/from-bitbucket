package org.cocuyo.dsl.entity.type;

public class EntityFieldTypeDecorator extends EntityFieldType {

	private EntityFieldType fBaseType;

	public EntityFieldTypeDecorator(String aName, EntityFieldType aBaseType) {
		super(aName);
		fBaseType = aBaseType;
	}

	public EntityFieldType getPrimitiveType() {
		return fBaseType instanceof EntityFieldTypeDecorator ? ((EntityFieldTypeDecorator) fBaseType)
				.getPrimitiveType()
				: fBaseType;
	}

	public void setBaseType(EntityFieldType aBuild) {
		fBaseType = aBuild;
	}
}
