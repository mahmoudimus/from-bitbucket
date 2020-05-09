package org.cocuyo.dsl.grammar.ast;

import org.cocuyo.dsl.grammar.ebnf.EBNFGrammar;


//close-imports

public abstract class Rule  
//open-inheritance//close-inheritance

{
	//open-members
    public abstract void buildRule(EBNFGrammar aGrammar);

    public abstract void build();

    //close-members
}