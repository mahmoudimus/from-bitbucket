/**
 * 
 */
package org.cocuyo.dsl.grammar.astdesign;

import java.util.ArrayList;

import org.cocuyo.dsl.grammar.ebnf.EBNFAlternative;
import org.cocuyo.dsl.grammar.ebnf.EBNFRootRule;

public class RuleInfo
{

    private ArrayList<AltInfo> fAltsInfo;

    public RuleInfo(EBNFRootRule aRule)
    {
	fAltsInfo = new ArrayList<AltInfo>();

	for (EBNFAlternative _alt : aRule.getAlts())
	{
	    ASTHierarchyModel _hierarchy = aRule.getGrammar().getHierarchy();

	    fAltsInfo
		    .add(new AltInfo(_alt, _hierarchy.getType(aRule.getName())));
	}

    }

    public int buildIndex(int aIndex)
    {

	for (AltInfo _altInfo : fAltsInfo)
	{
	    _altInfo.buildIndex(aIndex);
	    aIndex++;
	}

	return aIndex;
    }

    public Iterable<AltInfo> getAltsInfo()
    {
	return fAltsInfo;
    }

}