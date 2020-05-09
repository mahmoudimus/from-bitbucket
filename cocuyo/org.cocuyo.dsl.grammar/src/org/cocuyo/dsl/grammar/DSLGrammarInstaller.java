package org.cocuyo.dsl.grammar;

import org.cocuyo.extension.ExtensionException;
import org.cocuyo.extension.ExtensionsInstaller;

public class DSLGrammarInstaller extends ExtensionsInstaller
{

	@Override
	public void installExtensions() throws ExtensionException
	{
		installExtension(new CocuyoGrammarInterpreter());
	}

	@Override
	public void registerExtensionPoints()
	{

	}

}
