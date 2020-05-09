package org.cocuyo.dsl.entity;

import java.util.ArrayList;

import org.cocuyo.dsl.protocol.ObjectTable;

public class Entity extends DomainModelElement {

	private Entity fSuperEntity;
	private ObjectTable<EntityField> fFields;
	private ArrayList<EntityField> fFieldList;

	public Entity(String aName, DomainModel aModel) {
		super(aName, aModel);
		fFields = new ObjectTable<EntityField>();
		fFieldList = new ArrayList<EntityField>();
	}

	public Entity getSuperEntity() {
		return fSuperEntity;
	}

	public void setSuperEntity(Entity aSuperEntity) {
		fSuperEntity = aSuperEntity;
	}

	public void defineField(EntityField aField) {
		fFields.put(aField);
		fFieldList.add(aField);
	}

	public Iterable<EntityField> getFields() {
		return fFieldList;
	}

	public boolean getIsEntity() {
		return true;
	}
}
