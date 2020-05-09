package org.cocuyo.dsl.grammar.ast;

import org.cocuyo.dsl.grammar.ebnf.EBNFAlternative;
import org.cocuyo.dsl.grammar.ebnf.EBNFSymbol;

public abstract class Node  
//open-inheritance//close-inheritance

{
	//open-members

    public abstract String getText();

    public abstract EBNFSymbol build(EBNFAlternative aAlt);

    //close-members
}