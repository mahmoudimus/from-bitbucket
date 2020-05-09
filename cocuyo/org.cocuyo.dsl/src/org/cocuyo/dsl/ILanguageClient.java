package org.cocuyo.dsl;

import org.cocuyo.CocuyoException;
import org.cocuyo.dsl.protocol.DSLEnvironment;
import org.cocuyo.extension.ExtensionPoint;

@ExtensionPoint
public interface ILanguageClient {
	public void run(DSLEnvironment aEnv) throws CocuyoException;
}
