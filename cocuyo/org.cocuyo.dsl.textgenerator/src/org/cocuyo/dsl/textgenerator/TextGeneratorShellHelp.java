package org.cocuyo.dsl.textgenerator;

import org.cocuyo.help.shell.HelpShell;

public class TextGeneratorShellHelp extends HelpShell
{

	public TextGeneratorShellHelp()
	{
		super("DSL TextGenerator help");
		addHelpItem("gen [name]", "Load programs execute the generator [name].");
		addHelpItem("-in [path] (opt)",
				"Specify the system path where find programs. By default is '.'");
		addHelpItem("-show-text (opt)", "Print the generated text.");
		addHelpItem("-no-gen-files (opt)", "Don't generate the files.");
	}
}
