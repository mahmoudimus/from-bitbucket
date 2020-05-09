package org.cocuyo.parsing;

public class RecognitionError
{
    public String fMessage;
    public IToken fToken;

    public RecognitionError(String aMessage)
    {
	fMessage = aMessage;
    }

    public RecognitionError(IToken aToken)
    {
	this("Unexpected token '" + aToken.getText() + "'");
	fToken = aToken;
    }

    public RecognitionError(String aMessage, IToken aToken)
    {
	this(aMessage);
	fToken = aToken;
    }

    public String getPrettyMessage()
    {
	return (fToken != null ? "in '" + fToken.getFilePath() + "' (Line "
		+ (fToken.getLine() + 1) + ", Col " + (fToken.getColumn() + 1)
		+ "): " : "")
		+ fMessage;
    }

    public IToken getToken()
    {
	return fToken;
    }
}
