package org.cocuyo.dsl.protocol;

public interface IScopedObject<TScopedObject extends IScopedObject<TScopedObject, TNamed>, TNamed extends INamed> {

	public void define(TNamed aObject);

	public TNamed findDefined(String aName) throws NameNotFoundException;

	public TNamed find(String... aNames) throws NameNotFoundException;

	public INamed findObject(String... ANames) throws NameNotFoundException;

	public boolean contain(String aName);

	public TScopedObject getContainer();

	public boolean isRoot();

	public TScopedObject getRoot();
}
