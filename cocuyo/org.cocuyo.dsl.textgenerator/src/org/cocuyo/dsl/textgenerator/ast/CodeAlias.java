package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.dsl.textgenerator.code.StringCode;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;

//close-imports
public class CodeAlias extends Code
//open-inheritance//close-inheritance
{
	private Id id;
	private Code code;
	//open-fields//close-fields
	
	public CodeAlias(Id id, Code code)
	{
		this.id = id;
		this.code = code;
	}
	//open-members

    @Override
    public Object execute(ExecutionEnvironment aEnv)
    {
	Object _obj = code.execute(aEnv);
	aEnv.defineLocal(id.getText(), _obj);
	return StringCode.VOID_CODE;
    }

    //close-members
}