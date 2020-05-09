package org.cocuyo.dsl.textgenerator.code;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.cocuyo.util.IStringPrinter;
import org.cocuyo.util.SystemStringPrinter;

public class CodeManager {
	private Object fObject;

	private IStringPrinter fPrinter;

	public CodeManager(Object aObject) {
		this(aObject, SystemStringPrinter.singletone);
	}

	public CodeManager(Object aObject, IStringPrinter aPrinter) {
		setObject(aObject);
		setPrinter(aPrinter);
	}

	public void writeToFile(boolean aShowText, boolean aWriteFiles,
			String aBaseDir) {
		if (getObject() instanceof ICode) {
			ICode _code = (ICode) getObject();

			if (aShowText) {
				println("Showing generated text:");
				println("--- start ---");
				println();
				println(_code.getText(0));
				println();
				println("---- end ---");
			}
			if (aWriteFiles) {
				ArrayList<FileCode> _units = new ArrayList<FileCode>();
				_code.addFileWriteCodeTo(_units);

				System.out.println("Generating (" + _units.size()
						+ ") files ...");

				for (FileCode _file : _units) {
					try {
						String _fileName = aBaseDir + File.separator
								+ _file.getFileName();
						File _sysFile = new File(_fileName);

						println((_sysFile.exists() ? "Rewrite " : "Add ")
								+ _fileName);

						_file.writeToFile(_fileName);
					} catch (IOException _e) {
						throw new RuntimeException(_e);
					}
				}

			}
		} else {
			println(getObject().toString());
			println();
		}
	}

	public void setObject(Object object) {
		fObject = object;
	}

	public void println(String aText) {
		System.out.println(aText);
	}

	public void println() {
		System.out.println();
	}

	public Object getObject() {
		return fObject;
	}

	public void setPrinter(IStringPrinter printer) {
		fPrinter = printer;
	}

	public IStringPrinter getPrinter() {
		return fPrinter;
	}
}
