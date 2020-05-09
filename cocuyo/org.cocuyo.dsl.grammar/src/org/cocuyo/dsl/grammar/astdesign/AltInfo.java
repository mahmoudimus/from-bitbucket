/**
 * 
 */
package org.cocuyo.dsl.grammar.astdesign;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.cocuyo.dsl.grammar.ebnf.EBNFAlternative;
import org.cocuyo.dsl.grammar.ebnf.EBNFNonTerminal;
import org.cocuyo.dsl.grammar.ebnf.EBNFSymbol;
import org.cocuyo.dsl.grammar.ebnf.EBNFTerminal;
import org.cocuyo.dsl.grammar.ebnf.EBNFVirtualSymbol;
import org.cocuyo.dsl.grammar.ebnf.IEBNFAlternativeExpression;

public class AltInfo implements Iterable<SymbolInfo>
{

    private EBNFAlternative fAlt;
    private int fIndex;
    private ArrayList<SymbolInfo> fSymbolsInfo;
    private TrivialTypeModel fType;
    private boolean fIsCompleteAbstract;
    private int fCountSymbols;

    public AltInfo(EBNFAlternative aAlt, TrivialTypeModel aType)
    {
	fAlt = aAlt;
	fType = aType;
	fIsCompleteAbstract = isAbstract() && findIfIsCompleteAbstract(fAlt);
	fCountSymbols = countSymbols();
    }

    public int getIndex()
    {
	return fIndex;
    }

    public boolean isAbstract()
    {
	return fType instanceof AbstractTypeModel;
    }

    public boolean isCompleteAbstract()
    {
	return fIsCompleteAbstract;
    }

    private boolean findIfIsCompleteAbstract(EBNFAlternative aAlt)
    {

	if (aAlt.getCountExpressions() == 1)
	{
	    EBNFSymbol _symbol = (EBNFSymbol) aAlt.getExpressionAt(0);
	    if (_symbol instanceof EBNFVirtualSymbol)
	    {
		EBNFVirtualSymbol _virtualSymbol = (EBNFVirtualSymbol) _symbol;
		return findIfIsCompleteAbstract(_virtualSymbol.getSubAlt());
	    }
	    else
		return _symbol instanceof EBNFNonTerminal;
	}

	return false;
    }

    public boolean isList()
    {
	return fType instanceof ListTypeModel;
    }

    public int getCountSymbols()
    {
	return fCountSymbols;
    }

    private int countSymbols()
    {
	int _count = 0;
	for (SymbolInfo _info : getSymbolsInfo())
	{
	    _count += _info.getCountSymbols();
	}

	return _count;
    }

    public EBNFAlternative getAlt()
    {
	return fAlt;
    }

    public String getTypeName()
    {
	return fType.getName();
    }

    public Collection<SymbolInfo> getSymbolsInfo()
    {
	if (fSymbolsInfo == null)
	{
	    fSymbolsInfo = new ArrayList<SymbolInfo>();

	    for (IEBNFAlternativeExpression _expr : fAlt.getExpressions())
	    {
		EBNFSymbol _symbol = (EBNFSymbol) _expr;
		SymbolInfo _symbolInfo = null;

		if (_symbol instanceof EBNFVirtualSymbol)
		{
		    EBNFVirtualSymbol _virtualSymbol = (EBNFVirtualSymbol) _symbol;
		    _symbolInfo = new VirtualSymbolInfo(_virtualSymbol, fType
			    .getHierarchy().getType(_virtualSymbol.getName()));
		}
		else if (_symbol instanceof EBNFNonTerminal)
		{
		    EBNFNonTerminal _nonTerminalSymbol = (EBNFNonTerminal) _symbol;
		    _symbolInfo = new NonTerminalInfo(_nonTerminalSymbol);
		}
		else
		{
		    _symbolInfo = new TerminalInfo((EBNFTerminal) _symbol);
		}

		fSymbolsInfo.add(_symbolInfo);
	    }
	}
	return fSymbolsInfo;
    }

    public void buildIndex(int aIndex)
    {
	fIndex = aIndex;

	int _symbolIndex = 0;

	for (SymbolInfo _symInfo : getSymbolsInfo())
	{
	    _symbolIndex = _symInfo.buildIndex(_symbolIndex);
	}
    }

    @Override
    public Iterator<SymbolInfo> iterator()
    {
	return fSymbolsInfo.iterator();
    }

}