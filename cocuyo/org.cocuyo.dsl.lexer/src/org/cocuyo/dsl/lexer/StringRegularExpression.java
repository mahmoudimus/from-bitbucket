package org.cocuyo.dsl.lexer;

public class StringRegularExpression extends RegularExpression
{

	private String fText;

	public StringRegularExpression(String aText)
	{
		super(aText);
		fText = aText;
	}

	public boolean getIsString()
	{
		return true;
	}

	public String getText()
	{
		return fText;
	}

}
