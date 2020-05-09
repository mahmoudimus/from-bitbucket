package org.cocuyo.dsl.grammar.ebnf;

public class EBNFGrammarElement
{
    private EBNFGrammar fGrammar;
    private String fName;

    public EBNFGrammarElement(String aName, EBNFGrammar aGrammar)
    {
	setName(aName);
	setGrammar(aGrammar);
    }

    public EBNFGrammarElement()
    {

    }

    public EBNFGrammar getGrammar()
    {
	return fGrammar;
    }

    public String getName()
    {
	return fName;
    }

    public void setGrammar(EBNFGrammar aGrammar)
    {
	fGrammar = aGrammar;
    }

    public void setName(String aName)
    {
	fName = aName;
    }
}
