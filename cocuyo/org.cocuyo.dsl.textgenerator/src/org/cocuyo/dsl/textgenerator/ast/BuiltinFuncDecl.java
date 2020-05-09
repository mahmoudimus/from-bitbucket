package org.cocuyo.dsl.textgenerator.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.textgenerator.TextGeneratorType;

//close-imports
public class BuiltinFuncDecl extends GeneratorMember
// open-inheritance//close-inheritance
{
	private Id id;
	private ArrayList<FormalArg> args;
	private Name name;

	// open-fields//close-fields

	public BuiltinFuncDecl(Id id, ArrayList<FormalArg> args, Name name)
	{
		this.id = id;
		this.args = args;
		this.name = name;
	}

	// open-members

	@Override
	public void buildTypeLevel(TextGeneratorType aType)
	{

	}

	@Override
	public void buildInstrLevel()
	{
		// TODO Auto-generated method stub

	}

	// close-members
}