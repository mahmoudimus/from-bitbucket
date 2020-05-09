package org.cocuyo.dsl.textgenerator.ast;

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

	public String[] getName() {
		return name != null ? name.toArray() : new String[] {};
	}

	//close-members
}