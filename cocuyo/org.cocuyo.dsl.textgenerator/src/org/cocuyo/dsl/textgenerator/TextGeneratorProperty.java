package org.cocuyo.dsl.textgenerator;

import org.cocuyo.dsl.textgenerator.ast.Code;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;

public class TextGeneratorProperty extends TextGeneratorFunc
{
    private Code fCode;

    public TextGeneratorProperty(String aName, Code aCode)
    {
	super(aName);
	fCode = aCode;
    }

    @Override
    public Object execute(ExecutionEnvironment aEnv, Object... aArgs)
    {
	ExecutionEnvironment _env = new ExecutionEnvironment(aEnv
		.getStaticType(), aEnv.getInstance());

	_env.setAspectListenresOf(aEnv);

	_env.setCurrentFunc(this);

	return fCode.execute(_env);
    }

    @Override
    public String toString()
    {
	return "prop " + getName();
    }

}
