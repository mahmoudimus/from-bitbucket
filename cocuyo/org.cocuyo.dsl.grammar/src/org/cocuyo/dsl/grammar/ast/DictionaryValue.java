package org.cocuyo.dsl.grammar.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.grammar.ebnf.EBNFGrammar;
//open-imports//close-imports
public class DictionaryValue extends Value
//open-inheritance//close-inheritance
{
	private ArrayList<Entry> entrylist;
	//open-fields//close-fields
	
	public DictionaryValue(ArrayList<Entry> entrylist)
	{
		this.entrylist = entrylist;
	}
	//open-members

    @Override
    public Object evaluate(EBNFGrammar aGrammar)
    {
	return null;
    }
    //close-members
}