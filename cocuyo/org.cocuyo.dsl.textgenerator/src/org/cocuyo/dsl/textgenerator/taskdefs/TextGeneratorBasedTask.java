package org.cocuyo.dsl.textgenerator.taskdefs;

import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.dsl.taskdefs.DslTask;
import org.cocuyo.dsl.textgenerator.TextGeneratorType;
import org.cocuyo.dsl.textgenerator.code.CodeManager;
import org.cocuyo.util.AntLogPrinter;

public abstract class TextGeneratorBasedTask extends DslTask {
	private boolean fShowText;
	private boolean fWriteToFile;
	private String fBaseDir;

	public TextGeneratorBasedTask() {
		super();
		setShowText(false);
		setWriteToFile(true);
	}

	public void executeGenerator(TextGeneratorType aGenerator, String aFunName,
			Object... aArgs) {

		try {
			Object _result = aGenerator.executeFunction(aFunName, aArgs);
			CodeManager _manager = new CodeManager(_result, new AntLogPrinter(
					this));

			_manager.writeToFile(getShowText(), getWriteToFile(), getBaseDir());

		} catch (NameNotFoundException _e) {
			log(_e.getMessage());
		}
	}

	public void setShowText(boolean showText) {
		fShowText = showText;
	}

	public boolean getShowText() {
		return fShowText;
	}

	public void setBaseDir(String basePath) {
		fBaseDir = basePath;
	}

	public String getBaseDir() {
		return fBaseDir;
	}

	public void setWriteToFile(boolean writeToFile) {
		fWriteToFile = writeToFile;
	}

	public boolean getWriteToFile() {
		return fWriteToFile;
	}

}
