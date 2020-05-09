package org.cocuyo.dsl.grammar.astdesign;

import java.util.ArrayList;
import java.util.Hashtable;

import org.cocuyo.dsl.grammar.ebnf.EBNFGrammar;
import org.cocuyo.dsl.grammar.ebnf.EBNFRootRule;

public class ASTBuilderModel
{

    private EBNFGrammar fGrammar;
    private ArrayList<RuleInfo> fRulesInfo;
    private Hashtable<String, TerminalInfo> fTerminalsByName;
    private Hashtable<String, TerminalInfo> fTerminalsByTypeName;

    public ASTBuilderModel(EBNFGrammar aGrammar)
    {
	fGrammar = aGrammar.getBNF();
	buildIndex();
	buildTokenInfoSets();
    }

    public EBNFGrammar getGrammar()
    {
	return fGrammar;
    }

    private void buildTokenInfoSets()
    {
	fTerminalsByName = new Hashtable<String, TerminalInfo>();
	fTerminalsByTypeName = new Hashtable<String, TerminalInfo>();

	for (RuleInfo _rule : fRulesInfo)
	    for (AltInfo _alt : _rule.getAltsInfo())
		buildTokenInfoSetsForm(_alt);
    }

    private void buildTokenInfoSetsForm(AltInfo aAltInfo)
    {
	for (SymbolInfo _symbolInfo : aAltInfo.getSymbolsInfo())
	{
	    if (_symbolInfo instanceof TerminalInfo)
	    {
		fTerminalsByName.put(_symbolInfo.getName(),
			(TerminalInfo) _symbolInfo);
		if (_symbolInfo.isAst())
		{
		    fTerminalsByTypeName.put(_symbolInfo.getNodeTypeName(),
			    (TerminalInfo) _symbolInfo);
		}
	    }
	    else if (_symbolInfo instanceof VirtualSymbolInfo)
	    {
		VirtualSymbolInfo _virtualInfo = (VirtualSymbolInfo) _symbolInfo;
		buildTokenInfoSetsForm(_virtualInfo.getSubAltInfo());
	    }
	}
    }

    public Iterable<TerminalInfo> getTerminalsInfoByName()
    {
	return fTerminalsByName.values();
    }

    public Iterable<TerminalInfo> getTerminalsInfoByTypeName()
    {
	return fTerminalsByTypeName.values();
    }

    public Iterable<RuleInfo> getRulesInfo()
    {
	if (fRulesInfo == null)
	{

	    fRulesInfo = new ArrayList<RuleInfo>();
	    int _altIndex = 0;

	    for (EBNFRootRule _rule : fGrammar.getRules())
	    {
		fRulesInfo.add(new RuleInfo(_rule));
	    }
	}
	return fRulesInfo;
    }

    public void buildIndex()
    {
	int _altIndex = 0;

	for (RuleInfo _ruleInfo : getRulesInfo())
	{
	    _altIndex = _ruleInfo.buildIndex(_altIndex);
	}
    }

    public String getGrammarName()
    {
	return fGrammar.getName();
    }

    public Iterable<ListTypeModel> getListTypes()
    {
	return fGrammar.getHierarchy().getListTypes();
    }
}
