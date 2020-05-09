package org.cocuyo.help.shell;

import org.cocuyo.CocuyoException;
import org.cocuyo.startup.Startup;

public class DevelopMainClass
{
	public static void main(String aArgs[]) throws Exception
	{
		try
		{
			aArgs = new String[]
			{ "help", "help", "-ext", "..\\cocuyo\\ext\\" };
			Startup.main(aArgs);
		} catch (CocuyoException e)
		{
			e.printStackTrace();
		}
	}
}
