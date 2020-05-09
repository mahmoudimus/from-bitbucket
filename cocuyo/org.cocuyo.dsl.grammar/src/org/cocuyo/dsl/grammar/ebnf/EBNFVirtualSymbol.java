package org.cocuyo.dsl.grammar.ebnf;

import org.cocuyo.util.Printing;

public class EBNFVirtualSymbol extends EBNFNonTerminal
{
    private EBNFAlternative fAltContainer;
    private EBNFAlternative fSubAlternative;

    public EBNFVirtualSymbol()
    {

    }

    public boolean isVirtual()
    {
	return true;
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

    public EBNFAlternative getSubAlt()
    {
	return fSubAlternative;
    }

    public void setSubAlternative(EBNFAlternative aSubAlternative)
    {
	fSubAlternative = aSubAlternative;
    }

    @Override
    public void debug()
    {
	Printing.print("(");
	getSubAlt().debug();
	Printing.print("~> ");
	super.debug();
	Printing.print(")");
    }

    public EBNFVirtualSymbol getSimilar()
    {
	EBNFVirtualSymbol _result = new EBNFVirtualSymbol();
	_result.setBuilderName(getBuilderName());
	_result.setID(getID());
	_result.setText(getText());
	_result.setNodeTypeName(getNodeTypeName());

	return _result;
    }

    @Override
    public String toString()
    {
	String _str = "";
	for (IEBNFAlternativeExpression _expr : getSubAlt().getExpressions())
	{
	    _str += _expr + " ";
	}
	return "(" + _str + "~> " + getName() + ")";
    }
}
