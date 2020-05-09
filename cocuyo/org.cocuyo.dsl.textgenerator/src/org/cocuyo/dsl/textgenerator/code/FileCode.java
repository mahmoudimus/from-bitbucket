package org.cocuyo.dsl.textgenerator.code;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.cocuyo.util.InputFile;

public class FileCode extends BaseCode {
	private final String fFileName;
	private final ICode fCode;
	private InputFile fFile;

	public FileCode(String aFileName, ICode aCode) {
		fFileName = aFileName;
		fFile = new InputFile(aFileName);
		fCode = aCode;
	}

	public ICode getCode() {
		return fCode;
	}

	public String getFileName() {
		return fFileName;
	}

	public String getText() {
		return getText(0);
	}

	@Override
	public String getText(int aIndantation) {
		return fCode.getText(aIndantation);
	}

	@Override
	public void addFileWriteCodeTo(ArrayList<FileCode> aUnits) {
		aUnits.add(this);
	}

	@Override
	public void merge(String aContent) {
		getCode().merge(aContent);
	}

	public File writeToFile(String aPath) throws IOException {
		fFile = new InputFile(aPath);

		if (exists()) {
			String _content = fFile.getContent();

			this.getCode().merge(_content);

		} else {
			fFile.forceCreate();
		}

		FileOutputStream _outStream = new FileOutputStream(fFile);
		_outStream.write(getText().getBytes());
		_outStream.close();

		return fFile;
	}

	public File writeToFile() throws IOException {
		return writeToFile(getFileName());
	}

	@Override
	public String toString() {
		return getText();
	}

	public boolean exists() {
		return fFile.exists();
	}
}
