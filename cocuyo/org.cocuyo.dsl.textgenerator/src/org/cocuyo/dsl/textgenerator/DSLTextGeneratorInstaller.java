package org.cocuyo.dsl.textgenerator;

import org.cocuyo.extension.ExtensionException;
import org.cocuyo.extension.ExtensionsInstaller;

public class DSLTextGeneratorInstaller extends ExtensionsInstaller
{

	@Override
	public void installExtensions() throws ExtensionException
	{
		installExtension(new TextGeneratorInterpreter());
		installExtension(new TextGeneratorExecutor());
		installExtension(new TextGeneratorShellHelp());

	}

	@Override
	public void registerExtensionPoints()
	{

	}

}
