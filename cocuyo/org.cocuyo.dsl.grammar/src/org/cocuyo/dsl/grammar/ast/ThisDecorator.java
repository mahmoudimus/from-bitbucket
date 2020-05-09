package org.cocuyo.dsl.grammar.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.grammar.ebnf.EBNFGrammar;
//open-imports//close-imports
public class ThisDecorator extends Decorator
//open-inheritance//close-inheritance
{
	private ArrayList<Property> propertylist;
	//open-fields//close-fields
	
	public ThisDecorator(ArrayList<Property> propertylist)
	{
		this.propertylist = propertylist;
	}
	//open-members

    @Override
    public EBNFGrammar build(EBNFGrammar aGrammar)
    {
	for (Property _prop : propertylist)
	{
	    _prop.build(aGrammar, aGrammar);
	}

	return aGrammar;
    }

    //close-members
}