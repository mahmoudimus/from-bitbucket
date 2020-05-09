package org.cocuyo.parsing;

public interface IToken extends IAST
{
	public String getText();

	public String getFilePath();

	public int getLine();

	public int getColumn();

	public int getPosition();

	public boolean isEOF();

	public int getID();

	public boolean isWhitespace();

	public boolean isBadToken();
}
