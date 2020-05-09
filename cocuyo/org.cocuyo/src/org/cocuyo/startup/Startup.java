package org.cocuyo.startup;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.cocuyo.CocuyoException;
import org.cocuyo.env.CocuyoEnvironment;
import org.cocuyo.env.ShellArguments;
import org.cocuyo.extension.ExtensionManager;
import org.cocuyo.extension.IStartExtension;

@Deprecated
public class Startup {
	public static void main(String aArgsString) throws CocuyoException {
		main(aArgsString.split(" "));
	}

	public static void main(String... aArgs) throws CocuyoException {
		try {
			ShellArguments _args = CocuyoEnvironment.getShellArguments();
			_args.parse(aArgs);

			if (!_args.containParam("silent")) {
				printVersion();
			}

			String _pluginsPath = _args.getFirstParamValueOr("path",
					CocuyoEnvironment.getAppPath());

			ExtensionManager.registerInstallersFromJarsAt(_pluginsPath);

			String _installFile = null;

			if (_args.containParam("installfile")) {
				_installFile = _args.getFirstParamValue("installfile");
			}

			if (_args.containParam("if")) {
				_installFile = _args.getFirstParamValue("if");
			}

			if (_installFile != null) {
				ExtensionManager.registerInstallersFromFile(_installFile);
			}

			ExtensionManager.install();

			start();
		} catch (Exception _ex) {
			throw new CocuyoException("Unexpected exception", _ex);
		}
	}

	private static void printVersion() {
		System.out.println("Cocuyo v" + CocuyoEnvironment.VERSION
				+ " (2007-08) by Arian Fornaris (arianfornaris@gmail.com)");
		System.out.println();
	}

	private static void start() throws CocuyoException {
		for (IStartExtension _ext : ExtensionManager
				.findAllExtensionFor(IStartExtension.class)) {
			_ext.start();
		}
	}

	private static void processInstallFile(ArrayList<String> aNames, File aFile)
			throws IOException {
		BufferedReader _reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(aFile)));

		String _line;
		while ((_line = _reader.readLine()) != null) {
			aNames.add(_line);
		}
	}

}
