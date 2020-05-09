package org.cocuyo.dsl.grammar.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.grammar.ebnf.EBNFGrammar;
//open-imports//close-imports
public class ExternalDecorator extends Decorator
//open-inheritance//close-inheritance
{
	private Name name;
	private ArrayList<Property> propertylist;
	//open-fields//close-fields
	
	public ExternalDecorator(Name name, ArrayList<Property> propertylist)
	{
		this.name = name;
		this.propertylist = propertylist;
	}
	public ExternalDecorator(Name name)
	{
		this.name = name;
	}
	//open-members

    @Override
    public EBNFGrammar build(EBNFGrammar aGrammar)
    {
	// TODO Auto-generated method stub
	return null;
    }
    //close-members
}