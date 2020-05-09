package org.cocuyo.dsl.grammar.ast;

import org.cocuyo.parsing.IToken;
import org.cocuyo.parsing.cup.CupToken;

public class NumberToken extends CupToken
{
    public NumberToken(IToken aToken)
    {
	super(aToken.getID(), aToken.getText().substring(1,
		aToken.getText().length() - 1), aToken.getFilePath(), aToken
		.getLine(), aToken.getColumn(), aToken.getPosition());
    }

    public int getValue()
    {
	return Integer.parseInt(getText());
    }
}
