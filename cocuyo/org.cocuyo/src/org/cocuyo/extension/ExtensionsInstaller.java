package org.cocuyo.extension;

public abstract class ExtensionsInstaller
{
	public abstract void registerExtensionPoints();

	public abstract void installExtensions() throws ExtensionException;

	public void registerTargetClass(Class aTargetClass)
	{
		ExtensionManager.registerExtensionPoint(aTargetClass);
	}

	public void installExtension(Class aTargetClass, Object aExtension)
			throws ExtensionException
	{
		ExtensionManager.installExtension(aTargetClass, aExtension);
	}

	public void installExtension(Object aExtension) throws ExtensionException
	{
		ExtensionManager.installExtension(aExtension);
	}
}
