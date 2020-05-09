package org.cocuyo.dsl._native;

import org.cocuyo.extension.ExtensionException;
import org.cocuyo.extension.ExtensionsInstaller;

public class DSLNativeInstaller extends ExtensionsInstaller
{

	@Override
	public void installExtensions() throws ExtensionException
	{
		installExtension(new NativeInterpreter());
	}

	@Override
	public void registerExtensionPoints()
	{

	}

}
