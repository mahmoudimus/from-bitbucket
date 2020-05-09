package org.cocuyo.dsl.grammar.ebnf;

import java.util.ArrayList;

public abstract class EBNFRule extends EBNFGrammarElement
{
    private final ArrayList<EBNFAlternative> fAlts;

    public EBNFRule(String aName)
    {
	fAlts = new ArrayList<EBNFAlternative>();
	setName(aName);
    }

    public void addAlt(EBNFAlternative aAlt)
    {
	aAlt.setRule(this);
	fAlts.add(aAlt);
    }

    public void insertAlt(EBNFAlternative aAlt)
    {
	aAlt.setRule(this);
	fAlts.add(0, aAlt);
    }

    public Iterable<EBNFAlternative> getAlts()
    {
	return fAlts;
    }

    public EBNFAlternative getAltAt(int aIndex)
    {
	return fAlts.get(aIndex);
    }

    public int getCountAlts()
    {
	return fAlts.size();
    }
}
