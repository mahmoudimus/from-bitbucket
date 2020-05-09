package org.cocuyo.dsl.protocol;


public class ObjectPackageMember extends Named implements
		IObjectPackageMember {

	private ObjectPackage fContainer;

	public ObjectPackageMember(String aName, ObjectPackage aContainer) {
		super(aName);
		fContainer = aContainer;
	}

	@Override
	public String getFullName() {
		return (getContainer().isRoot() ? "" : getContainer().getFullName()
				+ ".")
				+ getName();
	}

	@Override
	public ObjectPackage getContainer() {
		return fContainer;
	}

	public void setContainer(ObjectPackage aContainer) {
		fContainer = aContainer;
	}

	@Override
	public ObjectPackage getRoot() {
		return getContainer().getRoot();
	}

}
