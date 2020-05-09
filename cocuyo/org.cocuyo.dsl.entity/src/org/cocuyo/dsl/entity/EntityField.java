package org.cocuyo.dsl.entity;

import org.cocuyo.dsl.entity.type.EntityFieldType;
import org.cocuyo.dsl.protocol.Named;

public class EntityField extends Named {

	private EntityFieldType fType;
	private boolean fIsRequired;

	public EntityField(String aName, EntityFieldType aType) {
		super(aName);
		fType = aType;
		setIsRequired(false);
	}

	public EntityField(String aName) {
		this(aName, null);
	}

	public EntityFieldType getType() {
		return fType;
	}

	public void setIsRequired(boolean aValue) {
		fIsRequired = aValue;
	}

	public boolean getIsRequired() {
		return fIsRequired;
	}

	public void setType(EntityFieldType aType) {
		fType = aType;
	}

}
