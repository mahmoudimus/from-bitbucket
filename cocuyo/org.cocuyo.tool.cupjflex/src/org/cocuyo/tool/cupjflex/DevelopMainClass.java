package org.cocuyo.tool.cupjflex;

import org.cocuyo.startup.Startup;

public class DevelopMainClass
{

	/**
	 * @param _args
	 * @throws Exception
	 */
	public static void main(String[] _args) throws Exception
	{
		_args = new String[]
		{ "-in", "src/G.gr", "-cup", "G" };
		Startup.main(_args);
	}

}
