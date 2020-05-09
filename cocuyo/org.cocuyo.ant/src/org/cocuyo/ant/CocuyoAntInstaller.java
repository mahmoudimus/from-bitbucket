package org.cocuyo.ant;

import org.cocuyo.extension.ExtensionException;
import org.cocuyo.extension.ExtensionsInstaller;

public class CocuyoAntInstaller extends ExtensionsInstaller {

	@Override
	public void installExtensions() throws ExtensionException {
		installExtension(new CocuyoAntStart());
		installExtension(new CocuyoAntConfigurator());
	}

	@Override
	public void registerExtensionPoints() {
		registerTargetClass(IAntProjectConfigurator.class);
	}

}
