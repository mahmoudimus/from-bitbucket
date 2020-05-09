package org.cocuyo.dsl.grammar.ebnf;

import org.cocuyo.util.Printing;

public class EBNFSubRule extends EBNFRule implements IEBNFAlternativeExpression
{
    private EBNFAlternative fAltContainer;

    public EBNFSubRule(String aName)
    {
	super(aName);
    }

    public boolean isSubRule()
    {
	return true;
    }

    @Override
    public void debug()
    {
	Printing.print("(");
	boolean _first = true;
	for (EBNFAlternative _alt : getAlts())
	{
	    if (!_first)
		Printing.print(" |");
	    _first = false;
	    Printing.print(" ");
	    _alt.debug();
	}
	Printing.print(")");
    }

    @Override
    public EBNFGrammar getGrammar()
    {
	return getRule().getGrammar();
    }

    public EBNFRule getRule()
    {
	return fAltContainer.getRule();
    }

    public EBNFAlternative getAltContainer()
    {
	return fAltContainer;
    }

    public void setAltContainer(EBNFAlternative aAltContainer)
    {
	fAltContainer = aAltContainer;
    }

}
