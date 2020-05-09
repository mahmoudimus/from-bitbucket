package org.cocuyo.dsl.protocol;

import org.cocuyo.CocuyoException;

public class NameNotFoundException extends CocuyoException
{
	private String fName;

	public NameNotFoundException(String aName)
	{
		super("Object '" + aName + "' not found");
		fName = aName;
	}

	public NameNotFoundException(String aName, String aMessage)
	{
		super(aMessage);
		fName = aName;
	}

	public String getName()
	{
		return fName;
	}
}
