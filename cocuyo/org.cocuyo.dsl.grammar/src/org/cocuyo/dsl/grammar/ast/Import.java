package org.cocuyo.dsl.grammar.ast;

//open-imports

import org.cocuyo.dsl.protocol.ObjectPackage;

//close-imports
public class Import extends UnitElement
//open-inheritance//close-inheritance
{
	private Name name;

	//open-fields//close-fields

	public Import(Name name) {
		this.name = name;
	}

	//open-members    

	@Override
	public void build(ObjectPackage aPkg) {
	}

	//close-members
}