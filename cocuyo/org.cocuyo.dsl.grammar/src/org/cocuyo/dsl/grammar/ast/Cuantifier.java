package org.cocuyo.dsl.grammar.ast;

import org.cocuyo.dsl.grammar.ebnf.EBNFCuantifier;
import org.cocuyo.parsing.IToken;
//open-imports//close-imports
public class Cuantifier
//open-inheritance//close-inheritance
{
	private IToken token;
	//open-fields//close-fields
	
	public Cuantifier(IToken token)
	{
		this.token = token;
	}
	//open-members

    public EBNFCuantifier.Kind getKind()
    {
	if (token.getText().contains("+"))
	    return EBNFCuantifier.Kind.MANY_OR_ONE;

	if (token.getText().contains("?"))
	    return EBNFCuantifier.Kind.ONE_OR_EMPTY;

	return EBNFCuantifier.Kind.MANY_OR_EMPTY;
    }

    //close-members
}