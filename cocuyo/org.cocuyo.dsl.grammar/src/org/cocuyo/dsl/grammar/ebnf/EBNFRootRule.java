package org.cocuyo.dsl.grammar.ebnf;

import org.cocuyo.util.Printing;


public class EBNFRootRule extends EBNFRule
{

    private int fIndex;

    public EBNFRootRule(String aName)
    {
	super(aName);
    }

    public void debug()
    {
	Printing.println(getName() + "->");

	for (EBNFAlternative _alt : getAlts())
	{
	    Printing.print("\t-\t");
	    _alt.debug();
	    Printing.println();
	}
    }

    public int getIndex()
    {
	return fIndex;
    }

    public void setIndex(int aIndex)
    {
	fIndex = aIndex;
    }

}
