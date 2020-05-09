/**
 * 
 */
package org.cocuyo.dsl.grammar.astdesign;

import org.cocuyo.dsl.grammar.ebnf.EBNFSymbol;
import org.cocuyo.dsl.grammar.ebnf.EBNFTerminal;

public class TerminalInfo extends SymbolInfo
{

    private EBNFTerminal fSybol;

    public TerminalInfo(EBNFTerminal aSymbol)
    {
	fSybol = aSymbol;
    }

    @Override
    public int getCountSymbols()
    {
	return 1;
    }

    @Override
    public EBNFSymbol getSymbol()
    {
	return fSybol;
    }

    public boolean isTerminal()
    {
	return true;
    }

}