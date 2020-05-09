package org.cocuyo.dsl.grammar.ebnf;

public class EBNFNonTerminal extends EBNFSymbol
{
    public EBNFNonTerminal(String aName)
    {
	setText(aName);
    }

    public EBNFNonTerminal()
    {
	super();
    }

    public boolean isNonTerminal()
    {
	return true;
    }

    @Override
    public String getNodeTypeName()
    {
	return super.getNodeTypeName() == null ? getTypeName() : super
		.getNodeTypeName();
    }

    @Override
    public String getTypeName()
    {
	return getName();
    }

    @Override
    public boolean isAst()
    {
	return true;
    }

}
