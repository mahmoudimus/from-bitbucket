package org.cocuyo.parsing.cup;

import java_cup.runtime.Symbol;

import org.cocuyo.parsing.IToken;
import org.cocuyo.parsing.Token;

public class CupToken extends Symbol implements IToken
{
    private final Token fToken;
    private boolean fIsBadToken;

    public CupToken(int aSymbolID, String aText, String aFilePath, int aLine,
	    int aColumn, int aPosition)
    {
	super(aSymbolID);
	fToken = new Token(aSymbolID, aText, aFilePath, aLine, aColumn,
		aPosition);
	fToken.setEOF(false);
    }

    public CupToken(int aSymbolID, String aText, String aFilePath, int aLine,
	    int aColumn, int aPosition, boolean aEOF)
    {
	this(aSymbolID, aText, aFilePath, aLine, aColumn, aPosition);
	fToken.setEOF(aEOF);
    }

    public int getColumn()
    {
	return fToken.getColumn();
    }

    public String getFilePath()
    {
	return fToken.getFilePath();
    }

    public int getID()
    {
	return fToken.getID();
    }

    public int getLine()
    {
	return fToken.getLine();
    }

    public int getPosition()
    {
	return fToken.getPosition();
    }

    public String getText()
    {
	return fToken.getText();
    }

    public boolean isEOF()
    {
	return fToken.isEOF();
    }

    public void isEOF(boolean aValue)
    {
	fToken.setEOF(aValue);
    }

    public boolean isWhitespace()
    {
	return fToken.isWhitespace();
    }

    public void isWhitespace(boolean aValue)
    {
	fToken.isWhitespace(aValue);
    }

    public void setColumn(int aColumn)
    {
	fToken.setColumn(aColumn);
    }

    public void setFilePath(String aFilePath)
    {
	fToken.setFilePath(aFilePath);
    }

    public void setID(int aID)
    {
	fToken.setID(aID);
    }

    public void setLine(int aLine)
    {
	fToken.setLine(aLine);
    }

    public void setPosition(int aPosition)
    {
	fToken.setPosition(aPosition);
    }

    public void setText(String aText)
    {
	fToken.setText(aText);
    }

    @Override
    public String toString()
    {
	return fToken.toString();
    }

    @Override
    public boolean isBadToken()
    {
	return fIsBadToken;
    }

    public void isBadToken(boolean aValue)
    {
	fIsBadToken = aValue;
    }
}
