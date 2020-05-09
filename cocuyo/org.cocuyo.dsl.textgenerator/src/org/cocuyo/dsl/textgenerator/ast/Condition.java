package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;

//close-imports

public abstract class Condition
//open-inheritance//close-inheritance

{
    //open-members
    public abstract Code getCode();

    public boolean execute(ExecutionEnvironment aEnv)
    {
	Object _result = getCode().execute(aEnv);
	return _result instanceof Boolean && (Boolean) _result;
    }

    //close-members
}