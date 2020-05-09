package org.cocuyo.dsl.grammar.astdesign;

import java.util.ArrayList;

import org.cocuyo.dsl.grammar.ebnf.EBNFAlternative;
import org.cocuyo.dsl.grammar.ebnf.EBNFGrammar;
import org.cocuyo.dsl.grammar.ebnf.EBNFNonTerminal;
import org.cocuyo.dsl.grammar.ebnf.EBNFRootRule;
import org.cocuyo.dsl.grammar.ebnf.EBNFRule;
import org.cocuyo.parsing.RecognitionException;

public class ASTDesigner
{
    private EBNFGrammar fGrammar;
    private ASTHierarchyModel fHierarchy;

    public ASTDesigner(EBNFGrammar aGrammar)
    {
	fGrammar = aGrammar.getBNF();
    }

    public ASTHierarchyModel getHierarchy()
    {
	if (fHierarchy == null)
	{
	    designHierarchy();
	}

	return fHierarchy;
    }

    protected void designHierarchy()
    {
	fHierarchy = new ASTHierarchyModel(fGrammar);

	for (EBNFRootRule _rule : fGrammar.getBNF().getRules())
	{
	    buildTypeFrom(_rule);
	}

	for (EBNFRootRule _rule : fGrammar.getVirtualRules())
	{
	    buildTypeFrom(_rule);
	}

	buildInheritance();
    }

    protected void buildTypeFrom(EBNFRootRule aRule)
    {
	if (fHierarchy.containType(aRule.getName()))
	    throw new RecognitionException("A rule '" + aRule.getName()
		    + "' can not bu used as virtual symbol");

	TrivialTypeModel _type = isAbstractRule(aRule) ? new AbstractTypeModel(
		aRule, fHierarchy) : new TrivialTypeModel(aRule, fHierarchy);
	fHierarchy.add(_type);
    }

    public static boolean isAbstractRule(EBNFRule aRule)
    {
	for (EBNFAlternative _alt : aRule.getAlts())
	{
	    if (!isAltWithOneNonTerminal(_alt))
		return false;
	}

	return true;
    }

    private static boolean isAltWithOneNonTerminal(EBNFAlternative aAlt)
    {
	return aAlt.getCountExpressions() == 1
		&& aAlt.getExpressionAt(0) instanceof EBNFNonTerminal;
    }

    protected void buildInheritance()
    {
	for (TrivialTypeModel _type : fHierarchy)
	{
	    if (_type instanceof AbstractTypeModel)
	    {
		AbstractTypeModel _abstractType = (AbstractTypeModel) _type;

		for (String _name : _abstractType.getSubTypeNames())
		{
		    TrivialTypeModel _subType = fHierarchy.getType(_name);
		    _subType.getSupers().add(_abstractType);
		}
	    }

	}

	for (TrivialTypeModel _type : fHierarchy)
	{
	    if (_type instanceof AbstractTypeModel)
	    {
		AbstractTypeModel _abstractType = (AbstractTypeModel) _type;
		_abstractType.getSubstitute();
	    }
	}

	for (TrivialTypeModel _type : fHierarchy)
	{
	    _type.replaceFoolTypes();
	}

	ArrayList<TrivialTypeModel> _types = new ArrayList<TrivialTypeModel>();

	for (TrivialTypeModel _type : fHierarchy)
	{

	    try
	    {
		_type = new ListTypeModel(_type);
	    }
	    catch (CanNotCreateListTypeException _e)
	    {
	    }

	    _types.add(_type);

	}
	fHierarchy.clear();
	fHierarchy.addAll(_types);
    }
}
