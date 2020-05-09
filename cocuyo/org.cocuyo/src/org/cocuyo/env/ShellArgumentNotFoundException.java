package org.cocuyo.env;

public class ShellArgumentNotFoundException extends Exception
{
    public ShellArgumentNotFoundException(String aParamName)
    {
	super("Expecting param '" + aParamName + "'.");
    }
}
