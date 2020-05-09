package org.cocuyo.dsl.grammar.ast;

import org.cocuyo.dsl.grammar.ebnf.EBNFAlternative;
import org.cocuyo.dsl.grammar.ebnf.EBNFNonTerminal;
import org.cocuyo.dsl.grammar.ebnf.EBNFSymbol;
import org.cocuyo.dsl.grammar.ebnf.EBNFVarTerminal;
//open-imports//close-imports
public class IdNode extends Node
//open-inheritance//close-inheritance
{
	private Decoration decoration;
	private Id id;
	//open-fields//close-fields
	
	public IdNode(Decoration decoration, Id id)
	{
		this.decoration = decoration;
		this.id = id;
	}
	public IdNode(Id id)
	{
		this.id = id;
	}
	//open-members

    @Override
    public String getText()
    {
	return id.getText();
    }

    @Override
    public EBNFSymbol build(EBNFAlternative aAlt)
    {
	EBNFSymbol _symbol = null;

	if (aAlt.getGrammar().containRule(getText()))
	{
	    _symbol = new EBNFNonTerminal();
	}
	else
	{
	    _symbol = new EBNFVarTerminal();
	}
	_symbol.setText(getText());
	return _symbol;
    }
    //close-members
}