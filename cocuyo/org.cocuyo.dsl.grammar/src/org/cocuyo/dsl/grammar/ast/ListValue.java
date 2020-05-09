package org.cocuyo.dsl.grammar.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.grammar.ebnf.EBNFGrammar;
//open-imports//close-imports
public class ListValue extends Value
//open-inheritance//close-inheritance
{
	private ArrayList<Value> valuelist;
	//open-fields//close-fields
	
	public ListValue(ArrayList<Value> valuelist)
	{
		this.valuelist = valuelist;
	}
	//open-members

    @Override
    public Object evaluate(EBNFGrammar aGrammar)
    {
	// TODO Auto-generated method stub
	return null;
    }

    //close-members
}