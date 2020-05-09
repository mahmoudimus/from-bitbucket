package org.cocuyo.help.shell;

import org.cocuyo.extension.ExtensionException;
import org.cocuyo.extension.ExtensionsInstaller;

public class HelpShellInstaller extends ExtensionsInstaller
{

	@Override
	public void installExtensions() throws ExtensionException
	{
		installExtension(new HelpShellManager());
		installExtension(new HelpShellHelp());
	}

	@Override
	public void registerExtensionPoints()
	{
		registerTargetClass(IHelpShell.class);
	}

}
