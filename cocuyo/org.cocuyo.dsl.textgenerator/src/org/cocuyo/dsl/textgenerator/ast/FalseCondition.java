package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;

//close-imports
public class FalseCondition extends Condition
//open-inheritance//close-inheritance
{
	private Code code;
	//open-fields//close-fields
	
	public FalseCondition(Code code)
	{
		this.code = code;
	}
	//open-members

    @Override
    public Code getCode()
    {
	return code;
    }

    @Override
    public boolean execute(ExecutionEnvironment aEnv)
    {
	return !super.execute(aEnv);
    }

    //close-members
}