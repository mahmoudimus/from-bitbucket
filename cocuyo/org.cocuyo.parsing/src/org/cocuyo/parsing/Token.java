package org.cocuyo.parsing;

public class Token implements IToken
{
    private String fText;
    private String fFilePath;
    private int fLine;
    private int fColumn;
    private int fPosition;
    private int fID;
    private boolean fIsWhitespace;
    private boolean fIsEOF;
    private boolean fIsBadToken;

    public Token(int aID, String aText, String aFilePath, int aLine,
	    int aColumn, int aPosition)
    {
	super();
	fID = aID;
	fText = aText;
	fFilePath = aFilePath;
	fLine = aLine;
	fColumn = aColumn;
	fPosition = aPosition;
	fIsWhitespace = false;
	fIsEOF = false;
    }

    @Override
    public int getColumn()
    {
	return fColumn;
    }

    @Override
    public String getFilePath()
    {
	return fFilePath;
    }

    @Override
    public int getID()
    {
	return fID;
    }

    @Override
    public int getLine()
    {
	return fLine;
    }

    @Override
    public int getPosition()
    {
	return fPosition;
    }

    @Override
    public String getText()
    {
	return fText;
    }

    @Override
    public boolean isEOF()
    {
	return fIsEOF;
    }

    @Override
    public boolean isWhitespace()
    {
	return fIsWhitespace;
    }

    public void setColumn(int aColumn)
    {
	fColumn = aColumn;
    }

    public void setLine(int aLine)
    {
	fLine = aLine;
    }

    public void setPosition(int aPosition)
    {
	fPosition = aPosition;
    }

    public void setText(String aText)
    {
	fText = aText;
    }

    public void setFilePath(String aFilePath)
    {
	fFilePath = aFilePath;
    }

    public void setID(int aID)
    {
	fID = aID;
    }

    public void isWhitespace(boolean aValue)
    {
	fIsWhitespace = aValue;
    }

    public void setEOF(boolean aValue)
    {
	fIsEOF = aValue;
    }

    @Override
    public String toString()
    {
	return getText();
    }

    public boolean isBadToken()
    {
	return fIsBadToken;
    }

    public void isBadToken(boolean aValue)
    {
	fIsBadToken = aValue;
    }
}
