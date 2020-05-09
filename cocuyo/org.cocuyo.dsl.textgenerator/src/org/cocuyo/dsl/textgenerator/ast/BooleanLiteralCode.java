package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;
import org.cocuyo.parsing.IToken;

//close-imports
public class BooleanLiteralCode extends Code
//open-inheritance//close-inheritance
{
	private IToken token;
	//open-fields//close-fields
	
	public BooleanLiteralCode(IToken token)
	{
		this.token = token;
	}
	//open-members
    @Override
    public Object execute(ExecutionEnvironment aEnv)
    {
	return token.getText().length() == 4;
    }
    //close-members
}