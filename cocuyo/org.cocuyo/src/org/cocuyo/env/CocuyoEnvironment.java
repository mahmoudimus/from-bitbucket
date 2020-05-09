/**
 * 
 */
package org.cocuyo.env;

import java.io.File;
import java.util.Hashtable;

import org.cocuyo.startup.Startup;

/**
 * @author Arian Fornaris Fernandez
 * 
 */
public class CocuyoEnvironment {
	private static ShellArguments fCmdArguments;
	private static String fAppPath = null;
	private static final Hashtable<String, Object> fData;
	public final static String VERSION = "0.0.3";

	static {
		fCmdArguments = new ShellArguments();
		fData = new Hashtable<String, Object>();
		File _appFolder = new File(Startup.class.getProtectionDomain()
				.getCodeSource().getLocation().getPath());
		fAppPath = _appFolder.getParent();
		fAppPath = fAppPath != null ? fAppPath : "";
	}

	public void putData(String aKey, Object aData) {
		fData.put(aKey, aData);
	}

	public Object getData(String aKey) {
		return fData.get(aKey);
	}

	public boolean existData(String aKey) {
		return fData.containsKey(aKey);
	}

	public static ShellArguments getShellArguments() {
		return fCmdArguments;
	}

	public static void setCmdArguments(ShellArguments aCmdArguments) {
		fCmdArguments = aCmdArguments;
	}

	public static void manageException(Exception aException) {
		throw new RuntimeException(aException);
	}

	public static String getAppPath() {
		return fAppPath;
	}
}
