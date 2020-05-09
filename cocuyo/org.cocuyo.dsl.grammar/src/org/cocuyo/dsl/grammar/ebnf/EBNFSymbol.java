package org.cocuyo.dsl.grammar.ebnf;

import org.cocuyo.util.Printing;

public abstract class EBNFSymbol extends EBNFGrammarElement implements
	IEBNFAlternativeExpression
{

    private String fText;
    private String fBuilderName;
    private String fTypeName;
    private String fNodeTypeName;
    private String fID;
    private EBNFAlternative fAlt;

    @Override
    public String getName()
    {
	return getText();
    }

    public String getText()
    {
	return fText;
    }

    public void setText(String aText)
    {
	fText = aText;
    }

    public String getBuilderName()
    {
	return fBuilderName;
    }

    public void setBuilderName(String aBuilderName)
    {
	fBuilderName = aBuilderName;
    }

    public String getTypeName()
    {
	return fTypeName;
    }

    public void setTypeName(String aTypeName)
    {
	fTypeName = aTypeName;
    }

    public String getID()
    {
	return fID;
    }

    public void setID(String aId)
    {
	fID = aId;
    }

    @Override
    public EBNFAlternative getAltContainer()
    {
	return fAlt;
    }

    @Override
    public void setAltContainer(EBNFAlternative aAlt)
    {
	fAlt = aAlt;
    }

    @Override
    public EBNFGrammar getGrammar()
    {
	return getRule().getGrammar();
    }

    public EBNFRule getRule()
    {
	return getAltContainer().getRule();
    }

    @Override
    public void debug()
    {
	Printing.print(getName());
    }

    @Override
    public String toString()
    {
	return getText();
    }

    public String getNodeName()
    {
	return fID != null ? fID : getName();
    }

    public String getNodeTypeName()
    {
	return fNodeTypeName;
    }

    public void setNodeTypeName(String aNodeTypeName)
    {
	fNodeTypeName = aNodeTypeName;
    }

    public abstract boolean isAst();
}
