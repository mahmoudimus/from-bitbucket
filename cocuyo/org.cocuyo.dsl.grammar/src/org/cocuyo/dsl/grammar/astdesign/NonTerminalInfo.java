/**
 * 
 */
package org.cocuyo.dsl.grammar.astdesign;

import org.cocuyo.dsl.grammar.ebnf.EBNFNonTerminal;
import org.cocuyo.dsl.grammar.ebnf.EBNFSymbol;

public class NonTerminalInfo extends SymbolInfo
{

    private EBNFNonTerminal fSymbol;
    private String fTypeName;

    public NonTerminalInfo(EBNFNonTerminal aSymbol)
    {
	fSymbol = aSymbol;
    }

    @Override
    public int getCountSymbols()
    {
	return 1;
    }

    @Override
    public EBNFSymbol getSymbol()
    {

	return fSymbol;
    }

    @Override
    public String getNodeTypeName()
    {
	if (fTypeName == null)
	{

	    TrivialTypeModel _type = fSymbol.getGrammar().getHierarchy()
		    .getType(getSymbol().getName());

	    if (_type instanceof AbstractTypeModel)
	    {
		AbstractTypeModel _substituteType = ((AbstractTypeModel) _type)
			.getSubstitute();
		fTypeName = _substituteType == null ? getSymbol().getName()
			: _substituteType.getName();
	    }
	    else
	    {
		fTypeName = getSymbol().getName();
	    }
	}

	return fTypeName;
    }
}