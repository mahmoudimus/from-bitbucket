package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.dsl.textgenerator.code.FileSeparatorCode;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;

//close-imports
public class PathSeparator extends Code
//open-inheritance//close-inheritance
{
	//open-fields//close-fields
	
	public PathSeparator()
	{
		
	}
	//open-members

    @Override
    public Object execute(ExecutionEnvironment aEnv)
    {
	return FileSeparatorCode.PATH_SEPARATOR;
    }

    //close-members
}