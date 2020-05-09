package org.cocuyo.dsl.textgenerator.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.textgenerator.code.IndentedCode;
import org.cocuyo.dsl.textgenerator.code.ListCode;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;

//close-imports
public class IndentCode extends Code
//open-inheritance//close-inheritance
{
    private ArrayList<Code> codelist;

    //open-fields//close-fields

    public IndentCode(ArrayList<Code> codelist)
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

	IndentedCode _code = new IndentedCode(_list);
	return _code;
    }

    //close-members
}