package org.cocuyo.dsl.grammar.ebnf;

import org.cocuyo.util.Printing;

public class EBNFCuantifier extends EBNFGrammarElement implements
	IEBNFAlternativeExpression
{
    private EBNFAlternative fAltContainer;

    public static enum Kind
    {
	MANY_OR_EMPTY, MANY_OR_ONE, ONE_OR_EMPTY
    }

    private Kind fKind;
    private IEBNFAlternativeExpression fExpression;

    public EBNFCuantifier(IEBNFAlternativeExpression aExpression, Kind aKind)
    {
	fKind = aKind;
	fExpression = aExpression;
    }

    public boolean isCuantifier()
    {
	return true;
    }

    public String getKindText()
    {
	String _str = "*";
	switch (getKind())
	{
	case MANY_OR_ONE:
	    _str = "+";
	    break;
	case ONE_OR_EMPTY:
	    _str = "?";
	    break;
	}
	return _str;
    }

    @Override
    public void debug()
    {
	getExpression().debug();

	Printing.print(getKindText());
    }

    public Kind getKind()
    {
	return fKind;
    }

    public void setKind(Kind aKind)
    {
	fKind = aKind;
    }

    public IEBNFAlternativeExpression getExpression()
    {
	return fExpression;
    }

    public void setExpression(IEBNFAlternativeExpression aExpression)
    {
	fExpression = aExpression;
    }

    @Override
    public EBNFAlternative getAltContainer()
    {
	return fAltContainer;
    }

    @Override
    public void setAltContainer(EBNFAlternative aAltContainer)
    {
	fAltContainer = aAltContainer;
    }

}
