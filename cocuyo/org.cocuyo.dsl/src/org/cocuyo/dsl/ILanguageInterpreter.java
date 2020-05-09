package org.cocuyo.dsl;

import org.cocuyo.extension.ExtensionPoint;
import org.cocuyo.util.InputFile;

@ExtensionPoint
public interface ILanguageInterpreter {
	public String getLanguageID();

	public IInterpreterUnit buildUnit(InputFile aFile);
}
