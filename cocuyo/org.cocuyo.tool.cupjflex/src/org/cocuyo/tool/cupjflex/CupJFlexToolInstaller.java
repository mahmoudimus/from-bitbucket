package org.cocuyo.tool.cupjflex;

import org.cocuyo.extension.ExtensionException;
import org.cocuyo.extension.ExtensionsInstaller;

public class CupJFlexToolInstaller extends ExtensionsInstaller
{

	@Override
	public void installExtensions() throws ExtensionException
	{
		installExtension(new CupJFlexTool());
		installExtension(new CupJFlexToolShellHelp());
	}

	@Override
	public void registerExtensionPoints()
	{
	}

}
