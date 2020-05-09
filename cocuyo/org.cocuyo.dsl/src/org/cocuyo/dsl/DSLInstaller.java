package org.cocuyo.dsl;

import org.cocuyo.extension.ExtensionException;
import org.cocuyo.extension.ExtensionsInstaller;

public class DSLInstaller extends ExtensionsInstaller {

	@Override
	public void installExtensions() throws ExtensionException {
		installExtension(DSLInterpreterManager.singleton());
	}

	@Override
	public void registerExtensionPoints() {
		registerTargetClass(ILanguageInterpreter.class);
		registerTargetClass(ILanguageClient.class);
	}

}
