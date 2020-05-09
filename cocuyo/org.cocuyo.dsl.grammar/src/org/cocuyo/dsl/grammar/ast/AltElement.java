package org.cocuyo.dsl.grammar.ast;

import org.cocuyo.dsl.grammar.ebnf.EBNFAlternative;
import org.cocuyo.dsl.grammar.ebnf.IEBNFAlternativeExpression;

public abstract class AltElement  
//open-inheritance//close-inheritance

{
	//open-members
    public abstract IEBNFAlternativeExpression build(EBNFAlternative aAlt);

    //close-members
}