package org.cocuyo.dsl.entity.ast;

//open-imports
import org.cocuyo.parsing.IToken;

//close-imports
public class ASTStringLiteral implements ASTLiteral
//open-inheritance
//close-inheritance
{
	
	private IToken fstring;
	
	public ASTStringLiteral(IToken astring)
	{
		this.fstring = astring;
	}
	
	//open-members
	public String getText() {
		return fstring.getText().substring(1, fstring.getText().length() - 1);
	}

	public IToken getToken() {
		return fstring;
	}
	//close-members
	
}