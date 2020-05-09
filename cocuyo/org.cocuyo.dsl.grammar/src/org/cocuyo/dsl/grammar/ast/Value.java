package org.cocuyo.dsl.grammar.ast;

import org.cocuyo.dsl.grammar.ebnf.EBNFGrammar;

public abstract class Value  
//open-inheritance//close-inheritance

{
	//open-members
    public abstract Object evaluate(EBNFGrammar aGrammar);
    //close-members
}