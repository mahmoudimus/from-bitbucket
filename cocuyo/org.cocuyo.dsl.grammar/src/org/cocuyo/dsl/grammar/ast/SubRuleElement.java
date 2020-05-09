package org.cocuyo.dsl.grammar.ast;

import org.cocuyo.dsl.grammar.ebnf.EBNFAlternative;
import org.cocuyo.dsl.grammar.ebnf.IEBNFAlternativeExpression;
//open-imports//close-imports
public class SubRuleElement extends AltElement
//open-inheritance//close-inheritance
{
	private SubRule subrule;
	//open-fields//close-fields
	
	public SubRuleElement(SubRule subrule)
	{
		this.subrule = subrule;
	}
	//open-members

    @Override
    public IEBNFAlternativeExpression build(EBNFAlternative aAlt)
    {
	return subrule.build(aAlt);
    }

    //close-members
}