package org.cocuyo.dsl.entity.ast;

//open-imports
import org.cocuyo.parsing.IToken;

//close-imports
public class ASTSymbolLiteral implements ASTLiteral
//open-inheritance
//close-inheritance
{
	
	private IToken fsymbol;
	
	public ASTSymbolLiteral(IToken asymbol)
	{
		this.fsymbol = asymbol;
	}
	
	//open-members
	//close-members
	
}