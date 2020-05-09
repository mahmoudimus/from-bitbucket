/**
 * 
 */
package org.cocuyo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Arian Fornaris Fernandez
 * 
 */
public class InputFile extends File {
	private String fExtension;

	public static boolean isFile(String aFilePath) {
		File _file = new File(aFilePath);
		return _file.isFile();
	}

	public static String[] getFilesNameOf(String aDirectoryPath) {
		File _file = new File(aDirectoryPath);
		return _file.list();
	}

	public static ArrayList<InputFile> getAllFilesOf(String aPath) {
		ArrayList<InputFile> _list = new ArrayList<InputFile>();
		findAllFilesOf(aPath, _list);
		return _list;
	}

	private static void findAllFilesOf(String aPath, ArrayList<InputFile> aList) {
		File _file = new File(aPath);

		if (_file.isDirectory()) {
			String[] _files = _file.list();
			if (_files != null)
				for (String _path : _files)
					findAllFilesOf(aPath + "/" + _path, aList);
		} else
			aList.add(new InputFile(_file));
	}

	public static boolean exist(String aFilePath) {
		File _file = new File(aFilePath);
		return _file.exists() || _file.isDirectory();
	}

	/**
	 * Require: !isDirectory()
	 * 
	 * @param aPath
	 */
	public InputFile(String aPath) {
		super(aPath);
	}

	public InputFile(File aFile) {
		super(aFile.getPath());
	}

	public static String extensionOf(String aName) {
		String[] _parts = aName.split("\\.");
		return _parts[_parts.length - 1];
	}

	public String getExtension() {
		if (fExtension == null) {
			fExtension = extensionOf(getName());
		}
		return fExtension;
	}

	/**
	 * The case sensitive is ignored
	 * 
	 * @param aExtension
	 * @return
	 */
	public boolean hasExtension(String aExtension) {
		return aExtension.equalsIgnoreCase(getExtension());
	}

	/**
	 * Force to create the file
	 * 
	 * @throws IOException
	 */
	public void forceCreate() throws IOException {
		if (getParent() != null) {
			createParent(new File(this.getParent()));
		}
		createNewFile();
	}

	private void createParent(File aFile) {
		if (!aFile.exists() && aFile.getParent() != null) {
			createParent(new File(aFile.getParent()));
		}
		aFile.mkdir();
	}

	public String getContent() throws IOException {
		FileInputStream _input = new FileInputStream(this);
		byte[] _buffer = new byte[(int) this.length()];
		_input.read(_buffer);
		return new String(_buffer);
	}

	/**
	 * @param filePath
	 * @return
	 */
}
