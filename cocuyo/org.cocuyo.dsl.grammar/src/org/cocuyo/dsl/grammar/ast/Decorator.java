package org.cocuyo.dsl.grammar.ast;

import org.cocuyo.dsl.grammar.ebnf.EBNFGrammar;

public abstract class Decorator  
//open-inheritance//close-inheritance

{
	//open-members
    public abstract EBNFGrammar build(EBNFGrammar aGrammar);

    //close-members
}