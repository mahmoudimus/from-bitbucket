package org.cocuyo.dsl.textgenerator.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.textgenerator.code.ListCode;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;

//close-imports
public class FuncCallArg
//open-inheritance//close-inheritance
{
	private ArrayList<Code> codelist;
	//open-fields//close-fields
	
	public FuncCallArg(ArrayList<Code> codelist)
	{
		this.codelist = codelist;
	}
	//open-members

    public Object execute(ExecutionEnvironment aEnv)
    {
	Object _result = null;

	if (codelist.size() == 1)
	{
	    _result = codelist.get(0).execute(aEnv);
	}
	else
	{
	    ListCode _list = new ListCode();

	    for (Code _code : codelist)
	    {
		_list.addObject(_code.execute(aEnv));
	    }

	    _result = _list;
	}

	return _result;
    }

    //close-members
}