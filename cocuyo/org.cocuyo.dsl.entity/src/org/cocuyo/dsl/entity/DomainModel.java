package org.cocuyo.dsl.entity;

import org.cocuyo.dsl.protocol.INamed;
import org.cocuyo.dsl.protocol.IObjectContainer;
import org.cocuyo.dsl.protocol.IObjectPackageMember;
import org.cocuyo.dsl.protocol.InvalidIncludeException;
import org.cocuyo.dsl.protocol.ObjectPackage;
import org.cocuyo.dsl.protocol.SimpleObjectContainer;

public class DomainModel extends SimpleObjectContainer<DomainModelElement>
		implements IObjectContainer<DomainModelElement>, IObjectPackageMember {

	public DomainModel(String aName, ObjectPackage aPkg) {
		super(aName, aPkg);
	}

	@Override
	public ObjectPackage getContainer() {
		return (ObjectPackage) super.getContainer();
	}

	@Override
	public String getFullName() {
		return getContainer().getFullName() + "." + getName();
	}

	@Override
	public ObjectPackage getRoot() {
		return getContainer().getRoot();
	}

	public void includeModel(DomainModel aModel) {
		super.include(aModel);
		for (DomainModelElement _elem : aModel.getElements()) {
			includeElement(_elem);
		}
	}

	public void includeElement(DomainModelElement aElement) {
		define(aElement);
	}

	public void includeObject(INamed aObject) throws InvalidIncludeException {
		if (aObject instanceof DomainModel) {
			includeModel((DomainModel) aObject);
		} else if (aObject instanceof DomainModelElement) {
			includeElement((DomainModelElement) aObject);
		} else {
			throw new InvalidIncludeException(
					"A model just can include a model or a model member");
		}

	}
}
