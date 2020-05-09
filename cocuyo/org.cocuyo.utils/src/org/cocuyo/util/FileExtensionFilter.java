package org.cocuyo.util;

import java.io.File;
import java.io.FileFilter;

public class FileExtensionFilter implements FileFilter {

	private String fFileExtension;
	private boolean fAccept;

	public FileExtensionFilter(String aFileExtension) {
		setFileExtension(aFileExtension);
	}

	public String getFileExtension() {
		return fFileExtension;
	}

	public void setFileExtension(String aFileExtension) {
		fFileExtension = aFileExtension;
	}

	@Override
	public boolean accept(File aPathname) {
		return InputFile.extensionOf(aPathname.getPath()).equalsIgnoreCase(
				getFileExtension());
	}

}
