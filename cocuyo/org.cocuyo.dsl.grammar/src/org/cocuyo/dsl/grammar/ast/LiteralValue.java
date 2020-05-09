package org.cocuyo.dsl.grammar.ast;

import org.cocuyo.dsl.grammar.ebnf.EBNFGrammar;

//close-imports
public class LiteralValue extends Value
//open-inheritance//close-inheritance
{
	private NumberToken number;
	private StringToken string;
	//open-fields//close-fields
	
	public LiteralValue(NumberToken number)
	{
		this.number = number;
	}
	public LiteralValue(StringToken string)
	{
		this.string = string;
	}
	//open-members

    @Override
    public Object evaluate(EBNFGrammar aGrammar)
    {
	return string != null ? string.getText() : number.getValue();
    }

    //close-members
}