package org.cocuyo.dsl;

import org.cocuyo.dsl.protocol.DSLEnvironment;

public interface IInterpreterUnit {
	public void build(DSLEnvironment aEnv);

	public boolean isBuilt();

	public void nextBuildPass();
}
