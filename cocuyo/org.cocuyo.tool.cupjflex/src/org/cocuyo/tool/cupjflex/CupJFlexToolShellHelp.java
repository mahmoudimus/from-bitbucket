package org.cocuyo.tool.cupjflex;

import org.cocuyo.help.shell.HelpShell;

public class CupJFlexToolShellHelp extends HelpShell
{

	public CupJFlexToolShellHelp()
	{
		super("Cup-JFlex Tool help");
		addHelpItem(
				"cup [grammar name] -in [input]",
				"Generate java classes for lexer, parser and AST of grammar [grammar name] with tools CUP and JFlex.");
		addHelpItem("-aspects [gen1] .. [genN]",
				"Set the aspects generators [gen1] .. [genN] in the execution environment.");
	}

}
