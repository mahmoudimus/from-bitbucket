package org.cocuyo;

import org.cocuyo.env.CocuyoEnvironment;
import org.cocuyo.extension.ExtensionManager;
import org.cocuyo.extension.IStartExtension;

public class Platfom {
	public static void start() throws CocuyoException {
		try {
			ExtensionManager.registerInstallersFromJarsAt(CocuyoEnvironment
					.getAppPath());
			ExtensionManager.install();

			for (IStartExtension _ext : ExtensionManager
					.findAllExtensionFor(IStartExtension.class)) {
				_ext.start();
			}

		} catch (Exception _e) {
			throw new CocuyoException("Somre exception", _e);
		}
	}

}
