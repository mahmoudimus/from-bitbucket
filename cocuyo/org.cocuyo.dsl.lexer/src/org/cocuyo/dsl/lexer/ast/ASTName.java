package org.cocuyo.dsl.lexer.ast;

//open-imports
import org.cocuyo.parsing.IToken;

//close-imports
public class ASTName extends ASTList<IToken> implements IASTRoot
//open-inheritance
//close-inheritance
{
	
	//open-members

	public String[] toStringArray()
	{
		int _i = 0;
		String[] _arr = new String[size()];
		return _arr;
	}
	//close-members
	
}