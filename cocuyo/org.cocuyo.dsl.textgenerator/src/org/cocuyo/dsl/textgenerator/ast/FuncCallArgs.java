package org.cocuyo.dsl.textgenerator.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.textgenerator.code.ListCode;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;

//close-imports
public class FuncCallArgs
//open-inheritance//close-inheritance
{
    private FuncCallArguments funccallarguments;
    private ArrayList<Code> codelist;

    //open-fields//close-fields

    public FuncCallArgs(FuncCallArguments funccallarguments,
	    ArrayList<Code> codelist)
    {
	this.funccallarguments = funccallarguments;
	this.codelist = codelist;
    }

    public FuncCallArgs(FuncCallArguments funccallarguments)
    {
	this.funccallarguments = funccallarguments;
    }

    public FuncCallArgs(ArrayList<Code> codelist)
    {
	this.codelist = codelist;
    }

    //open-members

    public ArrayList<Object> getArgs(ExecutionEnvironment aEnv)
    {
	ArrayList<Object> _args = new ArrayList<Object>();
	ListCode _list = new ListCode();
	if (codelist != null)
	{
	    for (Code _code : codelist)
	    {
		_list.addObject(_code.execute(aEnv));
	    }
	    _args.add(_list);
	}
	if (funccallarguments != null)
	{
	    for (FuncCallArg _arg : funccallarguments)
	    {
		_args.add(_arg.execute(aEnv));
	    }
	}

	return _args;
    }

    //close-members
}