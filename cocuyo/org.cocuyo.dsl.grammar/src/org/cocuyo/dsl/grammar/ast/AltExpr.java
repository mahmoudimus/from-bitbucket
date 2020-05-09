package org.cocuyo.dsl.grammar.ast;

import org.cocuyo.dsl.grammar.ebnf.EBNFAlternative;
import org.cocuyo.dsl.grammar.ebnf.EBNFCuantifier;
import org.cocuyo.dsl.grammar.ebnf.IEBNFAlternativeExpression;
//open-imports//close-imports
public class AltExpr
//open-inheritance//close-inheritance
{
	private AltElement altelement;
	private AltExpr altexpr;
	private Cuantifier cuantifier;
	//open-fields//close-fields
	
	public AltExpr(AltElement altelement)
	{
		this.altelement = altelement;
	}
	public AltExpr(AltExpr altexpr, Cuantifier cuantifier)
	{
		this.altexpr = altexpr;
		this.cuantifier = cuantifier;
	}
	//open-members

    public IEBNFAlternativeExpression build(EBNFAlternative aAlt)
    {
	IEBNFAlternativeExpression _expr;

	if (altexpr != null)
	    _expr = altexpr.build(aAlt);
	else
	    _expr = altelement.build(aAlt);

	if (cuantifier != null)
	    _expr = new EBNFCuantifier(_expr, cuantifier.getKind());
	return _expr;
    }

    //close-members
}