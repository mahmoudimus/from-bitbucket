package org.cocuyo.extension;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.cocuyo.CocuyoException;
import org.cocuyo.util.FileExtensionFilter;

public class ExtensionManager {
	private static final String INSTALLER_FILE_NAME = "install";

	private static Hashtable<Class, ArrayList> fExtensionTable;
	private static ArrayList<ExtensionsInstaller> fInstallers;

	static {
		fExtensionTable = new Hashtable<Class, ArrayList>();
		registerExtensionPoint(IStartExtension.class);
		fInstallers = new ArrayList<ExtensionsInstaller>();
	}

	public static void registerExtensionPoint(
			Class<IStartExtension> aExtensionPointClass) {
		assert aExtensionPointClass.isAnnotationPresent(ExtensionPoint.class);
		fExtensionTable.put(aExtensionPointClass, new ArrayList());
		//Console.silentPrint("Register point " + aExtensionPointClass);
	}

	public static void installExtension(Class aExtensionPointClass,
			Object aExtension) throws ExtensionException {
		assert aExtensionPointClass.isAnnotationPresent(ExtensionPoint.class);

		if (containExtensionPoint(aExtensionPointClass)) {
			fExtensionTable.get(aExtensionPointClass).add(aExtension);
			//Console.silentPrint("Install extension " + aExtension);
		} else
			throw new ExtensionException("The target class "
					+ aExtensionPointClass.getName() + " is not registred.");
	}

	/**
	 * Instala la extension teniendo como clase objetivo aquella interfaz de la
	 * extension anotada como ExtensionPoint.
	 * 
	 * @param aExtension
	 * @throws ExtensionException
	 */
	public static void installExtension(Object aExtension)
			throws ExtensionException {
		installExtensionOfClass(aExtension.getClass(), aExtension);
	}

	public static boolean containExtensionPoint(Class<?> aTargetClass) {
		return fExtensionTable.containsKey(aTargetClass);
	}

	public static <T> T findExtensionFor(Class<T> aExtensionPoint)
			throws CocuyoException {
		if (containExtensionPoint(aExtensionPoint))
			return (T) fExtensionTable.get(aExtensionPoint).get(0);
		else
			throw new ExtensionException("The target class "
					+ aExtensionPoint.getName() + " is not registred");
	}

	public static <T> Iterable<T> findAllExtensionFor(Class<T> aTargetClass) {
		return containExtensionPoint(aTargetClass) ? fExtensionTable
				.get(aTargetClass) : null;
	}

	private static void installExtensionOfClass(Class<?> aExtensionClass,
			Object aExtension) throws ExtensionException {
		for (Class<?> _class : aExtensionClass.getInterfaces()) {
			if (_class.isAnnotationPresent(ExtensionPoint.class)) {
				installExtension(_class, aExtension);
			}
		}

		if (aExtensionClass != Object.class) {
			installExtensionOfClass(aExtensionClass.getSuperclass(), aExtension);
		}
	}

	public static Collection<ExtensionsInstaller> getInstallersFrom(
			File aJarFile) throws IOException, InstantiationException,
			IllegalAccessException, ClassNotFoundException {

		ArrayList<ExtensionsInstaller> _installers = new ArrayList<ExtensionsInstaller>();

		JarFile _jar = new JarFile(aJarFile);

		ZipEntry _entry = _jar.getEntry(INSTALLER_FILE_NAME);

		if (_entry != null) {

			//Console.silentPrint("Loading jar " + aJarFile);

			InputStream _input = _jar.getInputStream(_entry);
			BufferedReader _reader = new BufferedReader(new InputStreamReader(
					_input));

			String _installerName;

			while ((_installerName = _reader.readLine()) != null) {
				ExtensionsInstaller _installer = (ExtensionsInstaller) createInstaller(_installerName);
				_installers.add(_installer);
			}
		}
		return _installers;
	}

	private static ExtensionsInstaller createInstaller(String aInstallerName)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		return (ExtensionsInstaller) Class.forName(aInstallerName)
				.newInstance();
	}

	public static void registerInstallersFromFile(String aInstallFile)
			throws IOException, InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		FileInputStream _input = new FileInputStream(new File(aInstallFile));
		BufferedReader _reader = new BufferedReader(new InputStreamReader(
				_input));
		String _installerName;

		while ((_installerName = _reader.readLine()) != null) {
			ExtensionsInstaller _installer = (ExtensionsInstaller) createInstaller(_installerName);
			fInstallers.add(_installer);
		}
	}

	public static void registerInstallersFromJarsAt(String aFolderPath)
			throws IOException, InstantiationException, IllegalAccessException,
			ClassNotFoundException {

		//Console.silentPrint("Search plugins at " + aFolderPath);

		File _dir = new File(aFolderPath);
		File[] _files = _dir.listFiles(new FileExtensionFilter("jar"));

		if (_files != null) {
			for (File _file : _files) {
				fInstallers.addAll(getInstallersFrom(_file));
			}
		}
	}

	public static void install() throws ExtensionException {
		for (ExtensionsInstaller _installer : fInstallers) {
			_installer.registerExtensionPoints();
		}

		for (ExtensionsInstaller _installer : fInstallers) {
			_installer.installExtensions();
		}

		fInstallers = new ArrayList<ExtensionsInstaller>();
	}

}
