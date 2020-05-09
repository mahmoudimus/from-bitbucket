package org.cocuyo.tool.gold;

import org.cocuyo.extension.ExtensionException;
import org.cocuyo.extension.ExtensionsInstaller;

public class GoldBuilderToolInstaller extends ExtensionsInstaller
{

	@Override
	public void installExtensions() throws ExtensionException
	{
		installExtension(new CocuyoGoldCmdTool());
		installExtension(new GoldBuilderToolShellHelp());
	}

	@Override
	public void registerExtensionPoints()
	{

	}

}
