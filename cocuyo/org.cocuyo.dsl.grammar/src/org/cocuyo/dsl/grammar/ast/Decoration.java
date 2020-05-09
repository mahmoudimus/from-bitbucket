package org.cocuyo.dsl.grammar.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.grammar.ebnf.EBNFGrammar;
//open-imports//close-imports
public class Decoration
//open-inheritance//close-inheritance
{
	private ArrayList<Decorator> decoratorlist;
	//open-fields//close-fields
	
	public Decoration(ArrayList<Decorator> decoratorlist)
	{
		this.decoratorlist = decoratorlist;
	}
	//open-members
    public EBNFGrammar build(EBNFGrammar aGrammar)
    {
	for (Decorator _d : decoratorlist)
	{
	    aGrammar = _d.build(aGrammar);
	}

	return aGrammar;
    }
    //close-members
}