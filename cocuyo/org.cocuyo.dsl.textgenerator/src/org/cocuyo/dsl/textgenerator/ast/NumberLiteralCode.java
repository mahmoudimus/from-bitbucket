package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.dsl.textgenerator.code.IntegerCode;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;
import org.cocuyo.parsing.IToken;

//close-imports
public class NumberLiteralCode extends Code
//open-inheritance//close-inheritance
{
	private IToken number_literal;
	//open-fields//close-fields
	
	public NumberLiteralCode(IToken number_literal)
	{
		this.number_literal = number_literal;
	}
	//open-members

    @Override
    public Object execute(ExecutionEnvironment aEnv)
    {
	return new IntegerCode(Integer.parseInt(number_literal.getText()));
    }

    //close-members
}