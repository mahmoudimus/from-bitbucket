package org.cocuyo.dsl.protocol;


public class DSLEnvironment {
	private ObjectPackage fObjectPackage;

	public DSLEnvironment() {
		this(new ObjectPackage(""));
	}

	public DSLEnvironment(ObjectPackage aObjectPackage) {
		super();
		fObjectPackage = aObjectPackage;
	}

	public ObjectPackage getObjectPackage() {
		return fObjectPackage;
	}

	public void setObjectPackage(ObjectPackage aObjectPackage) {
		fObjectPackage = aObjectPackage;
	}
}
