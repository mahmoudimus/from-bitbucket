/**
 * 
 */
package org.cocuyo.dsl.grammar.astdesign;

import org.cocuyo.dsl.grammar.ebnf.EBNFVirtualSymbol;

public class VirtualSymbolInfo extends NonTerminalInfo
{

    private AltInfo fSubAlt;

    public VirtualSymbolInfo(EBNFVirtualSymbol aSymbol, TrivialTypeModel aType)
    {
	super(aSymbol);
	fSubAlt = new AltInfo(aSymbol.getSubAlt(), aType);
    }

    @Override
    public int getCountSymbols()
    {

	return fSubAlt.getCountSymbols();
    }

    @Override
    public int buildIndex(int aIndex)
    {
	int _index = aIndex;

	for (SymbolInfo _symInfo : getSubAltInfo())
	{
	    _index = _symInfo.buildIndex(_index);
	}
	return _index;
    }

    public AltInfo getSubAltInfo()
    {
	return fSubAlt;
    }

    public boolean isVirtual()
    {
	return true;
    }

}