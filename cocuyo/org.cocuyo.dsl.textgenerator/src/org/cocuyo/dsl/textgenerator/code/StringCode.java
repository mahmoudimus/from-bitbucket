package org.cocuyo.dsl.textgenerator.code;

public class StringCode extends BaseCode
{
    private final String fString;

    public final static StringCode VOID_CODE = new StringCode("");

    public StringCode(String aString)
    {
	fString = aString;
    }

    @Override
    public String getText(int aIndantation)
    {
	return fString;
    }

    @Override
    public String toString()
    {
	return getText(0);
    }

}
