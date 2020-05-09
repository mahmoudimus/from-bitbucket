/**
 * 
 */
package org.cocuyo.dsl.grammar.astdesign;

import org.cocuyo.dsl.grammar.ebnf.EBNFSymbol;

public abstract class SymbolInfo
{

    private int fIndex;

    public abstract int getCountSymbols();

    public abstract EBNFSymbol getSymbol();

    public boolean isAst()
    {
	return getSymbol().isAst();
    }

    public int buildIndex(int aIndex)
    {
	fIndex = aIndex;
	return aIndex + 1;
    }

    public void setIndex(int aIndex)
    {
	fIndex = aIndex;
    }

    public int getIndex()
    {
	return fIndex;
    }

    public String getNodeTypeName()
    {
	return getSymbol().getNodeTypeName();
    }

    public String getName()
    {
	return getSymbol().getName();
    }

}