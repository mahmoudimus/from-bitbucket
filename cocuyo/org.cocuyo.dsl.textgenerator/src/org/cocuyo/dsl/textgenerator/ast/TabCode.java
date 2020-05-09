package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.dsl.textgenerator.code.SimpleTabCode;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;

//close-imports
public class TabCode extends Code
//open-inheritance//close-inheritance
{
	//open-fields//close-fields
	
	public TabCode()
	{
		
	}
	//open-members

    @Override
    public Object execute(ExecutionEnvironment aEnv)
    {
	return SimpleTabCode.SIMPLE_TAB_CODE;
    }

    //close-members
}