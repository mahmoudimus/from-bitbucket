package org.cocuyo.dsl.grammar.ebnf;

import java.util.ArrayList;
import java.util.Iterator;

import org.cocuyo.util.Printing;


public class EBNFAlternative extends EBNFGrammarElement implements
	Iterable<IEBNFAlternativeExpression>
{
    private final ArrayList<IEBNFAlternativeExpression> fExpressions;
    private EBNFRule fRule;

    public EBNFAlternative()
    {
	fExpressions = new ArrayList<IEBNFAlternativeExpression>();
    }

    @Override
    public EBNFGrammar getGrammar()
    {
	return getRule().getGrammar();
    }

    public void addExpression(IEBNFAlternativeExpression aExpr)
    {
	aExpr.setAltContainer(this);
	fExpressions.add(aExpr);
    }

    public Iterable<IEBNFAlternativeExpression> getExpressions()
    {
	return fExpressions;
    }

    public IEBNFAlternativeExpression getExpressionAt(int aIndex)
    {
	return fExpressions.get(aIndex);
    }

    public boolean hasName()
    {
	return getName() != null;
    }

    public EBNFRule getRule()
    {
	return fRule;
    }

    public void setRule(EBNFRule aRule)
    {
	fRule = aRule;
    }

    public boolean isEmpty()
    {
	return fExpressions.isEmpty();
    }

    public int getCountExpressions()
    {
	return fExpressions.size();
    }

    public void debug()
    {
	for (IEBNFAlternativeExpression _expr : getExpressions())
	{
	    _expr.debug();
	    Printing.print(" ");
	}
    }

    @Override
    public Iterator<IEBNFAlternativeExpression> iterator()
    {
	return getExpressions().iterator();
    }

}
