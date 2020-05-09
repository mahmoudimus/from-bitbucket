package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.dsl.textgenerator.code.NewlineCode;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;

//close-imports
public class NewLineCode extends Code
//open-inheritance//close-inheritance
{
	//open-fields//close-fields
	
	public NewLineCode()
	{
		
	}
	//open-members
    @Override
    public Object execute(ExecutionEnvironment aEnv)
    {
	return NewlineCode.NEWLINE;
    }
    //close-members
}