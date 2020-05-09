package org.cocuyo.dsl.textgenerator.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.textgenerator.code.ListCode;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;

//close-imports
public class CodeBlock extends Code
//open-inheritance//close-inheritance
{
	private ArrayList<Code> codelist;
	//open-fields//close-fields
	
	public CodeBlock(ArrayList<Code> codelist)
	{
		this.codelist = codelist;
	}
	//open-members

    @Override
    public Object execute(ExecutionEnvironment aEnv)
    {
	ListCode _list = new ListCode();

	for (Code _code : codelist)
	{
	    _list.addObject(_code.execute(aEnv));
	}

	return _list;
    }

    //close-members
}