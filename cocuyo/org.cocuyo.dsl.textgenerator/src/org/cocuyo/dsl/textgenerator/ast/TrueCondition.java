package org.cocuyo.dsl.textgenerator.ast;

//open-imports//close-imports
public class TrueCondition extends Condition
//open-inheritance//close-inheritance
{
	private Code code;
	//open-fields//close-fields
	
	public TrueCondition(Code code)
	{
		this.code = code;
	}
	//open-members
    @Override
    public Code getCode()
    {
	return code;
    }
    //close-members
}