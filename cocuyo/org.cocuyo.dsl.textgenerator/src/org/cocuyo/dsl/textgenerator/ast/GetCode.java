package org.cocuyo.dsl.textgenerator.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.textgenerator.code.ICode;
import org.cocuyo.dsl.textgenerator.code.ListCode;
import org.cocuyo.dsl.textgenerator.code.MergeCode;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;

//close-imports
public class GetCode extends Code
//open-inheritance//close-inheritance
{
	private ArrayList<Code> from;
	private ArrayList<Code> to;
	private ArrayList<Code> def;
	//open-fields//close-fields
	
	public GetCode(ArrayList<Code> from, ArrayList<Code> to, ArrayList<Code> def)
	{
		this.from = from;
		this.to = to;
		this.def = def;
	}
	public GetCode(ArrayList<Code> from, ArrayList<Code> to)
	{
		this.from = from;
		this.to = to;
	}
	//open-members

    @Override
    public Object execute(ExecutionEnvironment aEnv)
    {
	if (def == null)
	{
	    return new MergeCode(valueOf(aEnv, from), valueOf(aEnv, to));
	}

	return new MergeCode(valueOf(aEnv, from), valueOf(aEnv, to), valueOf(
		aEnv, def));
    }

    private ICode valueOf(ExecutionEnvironment aEnv, ArrayList<Code> aCodes)
    {
	ListCode _list = new ListCode();

	for (Code _code : aCodes)
	    _list.addObject(_code.execute(aEnv));

	return _list;
    }
    //close-members
}