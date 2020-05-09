package org.cocuyo.dsl.lexer;

import org.cocuyo.dsl.protocol.INamed;

public abstract class RegularExpression implements INamed
{
	private String fName;
	private LexerDefinition fLexerDefinition;

	public RegularExpression(String aName, LexerDefinition aLexerDefinition)
	{
		super();
		fName = aName;
		fLexerDefinition = aLexerDefinition;
	}

	public RegularExpression(String aName)
	{
		super();
		fName = aName;
	}

	public String getName()
	{
		return fName;
	}

	public void setName(String aName)
	{
		fName = aName;
	}

	public LexerDefinition getLexerDefinition()
	{
		return fLexerDefinition;
	}

	public void setLexerDefinition(LexerDefinition aLexerDefinition)
	{
		fLexerDefinition = aLexerDefinition;
	}
}
