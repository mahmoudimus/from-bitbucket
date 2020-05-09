package org.cocuyo.dsl.grammar.ast;

//open-imports

@SuppressWarnings("unused")
//close-imports
public class AltId
//open-inheritance//close-inheritance
{
	private Id id;
	private Decoration decoration;

	//open-fields//close-fields

	public AltId(Id id) {
		this.id = id;
	}

	public AltId() {

	}

	public AltId(Decoration decoration, Id id) {
		this.decoration = decoration;
		this.id = id;
	}

	public AltId(Decoration decoration) {
		this.decoration = decoration;
	}
	//open-members//close-members
}