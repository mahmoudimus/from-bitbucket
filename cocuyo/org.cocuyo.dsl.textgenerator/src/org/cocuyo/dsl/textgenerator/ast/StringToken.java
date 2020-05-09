package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.parsing.IToken;
import org.cocuyo.parsing.cup.CupToken;

public class StringToken extends CupToken
{
    private boolean fIsLineString;

    public StringToken(IToken aToken)
    {
	super(aToken.getID(), aToken.getText().substring(1,
		aToken.getText().length() - 1), aToken.getFilePath(), aToken
		.getLine(), aToken.getColumn(), aToken.getPosition());
	fIsLineString = aToken.getText().startsWith(">");
    }

    public boolean isLineString()
    {
	return fIsLineString;
    }

}
