package org.cocuyo.dsl.grammar.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.grammar.ebnf.EBNFGrammar;


//close-imports
public class RuleSet extends Rule
//open-inheritance//close-inheritance
{
	private Decoration decoration;
	private ArrayList<Rule> rulelist;
	//open-fields//close-fields
	
	public RuleSet(Decoration decoration, ArrayList<Rule> rulelist)
	{
		this.decoration = decoration;
		this.rulelist = rulelist;
	}
	public RuleSet(ArrayList<Rule> rulelist)
	{
		this.rulelist = rulelist;
	}
	//open-members
    @Override
    public void build()
    {
	for (Rule _rule : rulelist)
	{
	    _rule.build();
	}
    }

    @Override
    public void buildRule(EBNFGrammar aGrammar)
    {
	for (Rule _rule : rulelist)
	{
	    _rule.buildRule(aGrammar);
	}
    }

    //close-members
}