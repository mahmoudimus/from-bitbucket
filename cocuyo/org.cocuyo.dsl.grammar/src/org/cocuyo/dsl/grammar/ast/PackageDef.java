package org.cocuyo.dsl.grammar.ast;

import org.cocuyo.dsl.protocol.ObjectPackage;

//close-imports
public class PackageDef
//open-inheritance//close-inheritance
{
	private Name name;

	//open-fields//close-fields

	public PackageDef(Name name) {
		this.name = name;
	}

	public PackageDef() {

	}

	//open-members

	public ObjectPackage build(ObjectPackage aPkg) {
		return name != null ? aPkg.getRoot().definePackage(name.toArray())
				: aPkg;
	}

	//close-members
}