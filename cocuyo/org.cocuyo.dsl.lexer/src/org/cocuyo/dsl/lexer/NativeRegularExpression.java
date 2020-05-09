package org.cocuyo.dsl.lexer;

public class NativeRegularExpression extends RegularExpression
{

	private String fNativeText;

	public boolean getIsNative()
	{
		return true;
	}

	public NativeRegularExpression(String aName, String aNativeText)
	{
		super(aName);
		fNativeText = aNativeText;
	}

	public String getNativeText()
	{
		return fNativeText;
	}

}
