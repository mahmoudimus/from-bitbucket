package org.cocuyo.dsl.grammar.astdesign;

import java.util.ArrayList;

import org.cocuyo.dsl.grammar.ebnf.EBNFAlternative;
import org.cocuyo.dsl.grammar.ebnf.EBNFNonTerminal;
import org.cocuyo.dsl.grammar.ebnf.EBNFRootRule;


public class AbstractTypeModel extends TrivialTypeModel
{

    private ArrayList<String> fSubTypeNames;
    private AbstractTypeModel fSubstitute;

    public AbstractTypeModel(EBNFRootRule aRule, ASTHierarchyModel aHierarchy)
    {
	super(aRule, aHierarchy);
    }

    public boolean isAbstract()
    {
	return true;
    }

    @Override
    protected void build()
    {
	buildSubTypeNames();
    }

    public void buildSubTypeNames()
    {
	fSubTypeNames = new ArrayList<String>();

	for (EBNFAlternative _alt : getRule().getAlts())
	{
	    EBNFNonTerminal _nonTerm = (EBNFNonTerminal) _alt
		    .getExpressionAt(0);
	    fSubTypeNames.add(_nonTerm.getName());
	}
    }

    public Iterable<String> getSubTypeNames()
    {
	return fSubTypeNames;
    }

    protected AbstractTypeModel getSubstitute()
    {
	if (fSubstitute == null)
	{
	    replaceFoolSupers();

	    if (getSupers().size() == 1)
	    {
		fSubstitute = (AbstractTypeModel) getSupers().toArray()[0];
	    }
	    else
	    {
		fSubstitute = this;
	    }
	}

	return fSubstitute;
    }

    public boolean isFool()
    {
	return getSubstitute() != this;
    }

    @Override
    public String toString()
    {
	String _str = (isFool() ? "foo " : "") + "abstract " + super.toString();

	return _str;
    }

}
