package org.cocuyo.dsl.grammar.ast;

import org.cocuyo.dsl.grammar.ebnf.EBNFGrammar;
import org.cocuyo.dsl.grammar.ebnf.EBNFRootRule;


//close-imports
public class SimpleRule extends Rule
//open-inheritance//close-inheritance
{
	private Decoration decoration;
	private RuleLeftSide leftside;
	private SubRule subrule;
	//open-fields

    private EBNFRootRule fRule;

    //close-fields
	
	public SimpleRule(Decoration decoration, RuleLeftSide leftside, SubRule subrule)
	{
		this.decoration = decoration;
		this.leftside = leftside;
		this.subrule = subrule;
	}
	public SimpleRule(RuleLeftSide leftside, SubRule subrule)
	{
		this.leftside = leftside;
		this.subrule = subrule;
	}
	//open-members

    @Override
    public void buildRule(EBNFGrammar aGrammar)
    {
	fRule = new EBNFRootRule(leftside.getName());
	fRule.setGrammar(aGrammar);
	aGrammar.addRule(fRule);
    }

    @Override
    public void build()
    {
	subrule.build(fRule);
    }
    //close-members
}