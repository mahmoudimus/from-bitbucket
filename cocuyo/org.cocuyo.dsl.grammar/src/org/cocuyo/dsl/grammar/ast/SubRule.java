package org.cocuyo.dsl.grammar.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.grammar.ebnf.EBNFAlternative;
import org.cocuyo.dsl.grammar.ebnf.EBNFRule;
import org.cocuyo.dsl.grammar.ebnf.EBNFSubRule;
import org.cocuyo.dsl.grammar.ebnf.IEBNFAlternativeExpression;
//open-imports//close-imports
public class SubRule
//open-inheritance//close-inheritance
{
	private ArrayList<Alternative> alternativelist;
	//open-fields//close-fields
	
	public SubRule(ArrayList<Alternative> alternativelist)
	{
		this.alternativelist = alternativelist;
	}
	//open-members
    public void build(EBNFRule aRule)
    {
	for (Alternative _alt : alternativelist)
	{
	    aRule.addAlt(_alt.build(aRule));
	}
    }

    public IEBNFAlternativeExpression build(EBNFAlternative aAlt)
    {
	EBNFSubRule _subRule = new EBNFSubRule("subrule");
	_subRule.setAltContainer(aAlt);
	//aAlt.addExpression(_subRule);
	build(_subRule);
	return _subRule;
    }

    //close-members
}