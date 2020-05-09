package org.cocuyo.dsl.entity;

import org.cocuyo.dsl.protocol.Named;

public abstract class DomainModelElement extends Named {

	private DomainModel fModel;

	public DomainModelElement(String aName, DomainModel aModel) {
		super(aName);
		fModel = aModel;
	}

	public DomainModel getModel() {
		return fModel;
	}

	public void setModel(DomainModel aModel) {
		fModel = aModel;
	}
}
