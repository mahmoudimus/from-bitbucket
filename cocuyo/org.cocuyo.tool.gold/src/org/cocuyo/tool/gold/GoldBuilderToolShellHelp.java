package org.cocuyo.tool.gold;

import org.cocuyo.help.shell.HelpShell;

public class GoldBuilderToolShellHelp extends HelpShell
{

	public GoldBuilderToolShellHelp()
	{
		super("Gold Builder help");
		addHelpItem(
				"gold [grammar name] -in [input]",
				"Generate java classes and tables for lexer, parser and AST of grammar [grammar name] with tool GOLD Builder.");
	}

}
