package org.cocuyo.dsl.textgenerator.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.textgenerator.code.FileCode;
import org.cocuyo.dsl.textgenerator.code.ICode;
import org.cocuyo.dsl.textgenerator.code.ListCode;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;

//close-imports
public class UnitCode extends Code
//open-inheritance//close-inheritance
{
	private ArrayList<Code> path;
	private ArrayList<Code> content;
	//open-fields//close-fields
	
	public UnitCode(ArrayList<Code> path, ArrayList<Code> content)
	{
		this.path = path;
		this.content = content;
	}
	//open-members

    @Override
    public Object execute(ExecutionEnvironment aEnv)
    {
	return new FileCode(eval(aEnv, path).getText(0), eval(aEnv, content));
    }

    private ICode eval(ExecutionEnvironment aEnv, ArrayList<Code> aCodes)
    {
	ListCode _list = new ListCode();
	for (Code _code : aCodes)
	    _list.addObject(_code.execute(aEnv));
	return _list;
    }

    //close-members
}