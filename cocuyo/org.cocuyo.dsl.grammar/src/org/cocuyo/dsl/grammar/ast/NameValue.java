package org.cocuyo.dsl.grammar.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.grammar.ebnf.EBNFGrammar;
//open-imports//close-imports
public class NameValue extends Value
//open-inheritance//close-inheritance
{
	private Name name;
	private ArrayList<Property> propertylist;
	//open-fields//close-fields
	
	public NameValue(Name name)
	{
		this.name = name;
	}
	public NameValue(Name name, ArrayList<Property> propertylist)
	{
		this.name = name;
		this.propertylist = propertylist;
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