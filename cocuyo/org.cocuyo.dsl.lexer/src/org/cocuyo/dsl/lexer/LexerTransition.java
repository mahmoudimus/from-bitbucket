package org.cocuyo.dsl.lexer;


public class LexerTransition
{
	private String fFromState;
	private String fGotoState;
	private String fYieldSymbol;
	private LexerDefinition fLexerDefinition;
	private RegularExpression fPattern;
	private int fIndex;

	public static final String START_STATE_NAME = "start";

	public LexerTransition(RegularExpression aPattern, String aFromState,
			String aGotoState, String aYieldSymbol)
	{
		super();
		fPattern = aPattern;
		fFromState = aFromState;
		fGotoState = aGotoState;
		fYieldSymbol = aYieldSymbol;
	}

	public LexerTransition(RegularExpression aPattern, String aFromState,
			String aGotoState)
	{
		super();
		fPattern = aPattern;
		fFromState = aFromState;
		fGotoState = aGotoState;
	}

	public String getFromState()
	{
		return fFromState == null ? START_STATE_NAME : fFromState;
	}

	public void setFromState(String aFromState)
	{
		fFromState = aFromState;
	}

	public String getGotoState()
	{
		return fGotoState == null ? getFromState() : fGotoState;
	}

	public String getYieldSymbol()
	{
		return fYieldSymbol;
	}

	public LexerDefinition getLexerDefinition()
	{
		return fLexerDefinition;
	}

	public RegularExpression getPattern()
	{
		return fPattern;
	}

	public boolean getIsFinal()
	{
		return fYieldSymbol != null;
	}

	void setLexerDefinition(LexerDefinition aLexerDefinition)
	{
		fLexerDefinition = aLexerDefinition;
	}

	public int getIndex()
	{
		return fIndex;
	}

	public void setIndex(int aIndex)
	{
		fIndex = aIndex;
	}
}
