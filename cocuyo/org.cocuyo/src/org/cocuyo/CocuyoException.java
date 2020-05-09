package org.cocuyo;

public class CocuyoException extends Exception
{

	private String fMsg;

	public CocuyoException(String aMsg, Exception aException)
	{
		super(aException);
		fMsg = aMsg;
	}

	public CocuyoException(String aMsg)
	{
		fMsg = aMsg;
	}

	@Override
	public String getMessage()
	{
		return fMsg;
	}
}
