package org.cocuyo.dsl.lexer.ast;

import org.cocuyo.dsl.lexer.LexerDefinition;

public class LexerASTEnv
{
	private final LexerDefinition fLexer;
	private String fDefaultGoto;
	private String fDefaultFrom;
	private String fDefaultYield;

	public LexerASTEnv(LexerDefinition aLexer)
	{
		super();
		fLexer = aLexer;
	}

	public LexerDefinition getLexer()
	{
		return fLexer;
	}

	public String getDefaultGoto()
	{
		return fDefaultGoto;
	}

	public void setDefaultGoto(String aDefaultGoto)
	{
		fDefaultGoto = aDefaultGoto;
	}

	public String getDefaultFrom()
	{
		return fDefaultFrom;
	}

	public void setDefaultFrom(String aDefaultFrom)
	{
		fDefaultFrom = aDefaultFrom;
	}

	public void setDefaultYield(String aDafaultYield)
	{
		fDefaultYield = aDafaultYield;
	}

	public String getDefaultYield()
	{
		return fDefaultYield;
	}
}
