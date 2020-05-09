package org.cocuyo.dsl.protocol;

public interface IObjectContainer<TNamed extends INamed> {

	public void define(TNamed aNamed);

	public TNamed findDefined(String aName) throws NameNotFoundException;

	public INamed findObject(String... aNames) throws NameNotFoundException;

	public <TNamed extends INamed> TNamed findObject(Class<TNamed> aClass,
			String... aNames) throws NameNotFoundException,
			InvalidNameClassException;

	public boolean contain(String aName);
}
