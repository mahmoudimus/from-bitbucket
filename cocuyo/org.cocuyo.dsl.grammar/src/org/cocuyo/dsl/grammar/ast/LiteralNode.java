package org.cocuyo.dsl.grammar.ast;

import org.cocuyo.dsl.grammar.ebnf.EBNFAlternative;
import org.cocuyo.dsl.grammar.ebnf.EBNFLiteral;
import org.cocuyo.dsl.grammar.ebnf.EBNFSymbol;
import org.cocuyo.parsing.IToken;
//open-imports//close-imports
public class LiteralNode extends Node
//open-inheritance//close-inheritance
{
	private Decoration decoration;
	private IToken token;
	//open-fields//close-fields
	
	public LiteralNode(Decoration decoration, IToken token)
	{
		this.decoration = decoration;
		this.token = token;
	}
	public LiteralNode(IToken token)
	{
		this.token = token;
	}
	//open-members

    @Override
    public String getText()
    {
	return token.getText();
    }

    @Override
    public EBNFSymbol build(EBNFAlternative aAlt)
    {
	EBNFLiteral _symbol = new EBNFLiteral();
	_symbol.setText(getText().substring(1, getText().length() - 1));
	return _symbol;
    }
    //close-members
}