package org.cocuyo.dsl.textgenerator;

import java.util.ArrayList;

import org.cocuyo.dsl.textgenerator.ast.Code;
import org.cocuyo.dsl.textgenerator.code.ListCode;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;

public class TextGeneratorStandardFunc extends TextGeneratorFunc
{
    private ArrayList<Code> fCodes;
    private ArrayList<TextGeneratorFuncArg> fArgs;

    public TextGeneratorStandardFunc(String aName, ArrayList<Code> aCodes)
    {
	super(aName);
	fCodes = aCodes;
    }

    @Override
    public Object execute(ExecutionEnvironment aEnv, Object... aArgs)
    {
	ExecutionEnvironment _env = new ExecutionEnvironment(aEnv
		.getStaticType(), aEnv.getInstance());

	_env.setAspectListenresOf(aEnv);

	_env.setCurrentFunc(this);

	int _i = 0;

	for (TextGeneratorFuncArg _arg : fArgs)
	{
	    if (_i < aArgs.length)
	    {
		_env.defineLocal(_arg.getName(), aArgs[_i]);
	    }
	    else
	    {
		Code _default = _arg.getDefaultValue();
		_env.defineLocal(_arg.getName(), _default != null ? _default
			.execute(_env) : null);
	    }
	    _i++;
	}

	Object _result = null;

	if (fCodes.size() == 1)
	    _result = fCodes.get(0).execute(_env);
	else
	{
	    ListCode _list = new ListCode();

	    for (Code _code : fCodes)
	    {
		_list.addObject(_code.execute(_env));
	    }
	    _result = _list;

	}

	return _result;
    }

    public void setArgs(ArrayList<TextGeneratorFuncArg> aFuncArgs)
    {
	fArgs = aFuncArgs;
    }
}
