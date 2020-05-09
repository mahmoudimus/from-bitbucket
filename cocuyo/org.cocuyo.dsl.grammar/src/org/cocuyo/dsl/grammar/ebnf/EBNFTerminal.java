package org.cocuyo.dsl.grammar.ebnf;

public class EBNFTerminal extends EBNFSymbol
{
    public EBNFTerminal(String aText)
    {
	super();
	setText(aText);
    }

    public EBNFTerminal()
    {
	super();
    }

    public boolean isTerminal()
    {
	return true;
    }

    @Override
    public boolean isAst()
    {
	return getNodeTypeName() != null;
    }

    @Override
    public String getTypeName()
    {
	return getNodeTypeName();
    }
}
