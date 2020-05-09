package org.cocuyo.help.shell;

import org.cocuyo.extension.ExtensionPoint;

@ExtensionPoint
public interface IHelpShell
{
	public void show();

	public boolean showHelpItem(String aItemName);
}
