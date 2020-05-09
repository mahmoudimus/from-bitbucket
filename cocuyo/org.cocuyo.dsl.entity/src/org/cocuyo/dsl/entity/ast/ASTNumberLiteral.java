package org.cocuyo.dsl.entity.ast;

//open-imports
import org.cocuyo.parsing.IToken;

@SuppressWarnings("unused")
//close-imports
public class ASTNumberLiteral implements ASTLiteral
//open-inheritance
//close-inheritance
{
	
	private IToken fnumber;
	
	public ASTNumberLiteral(IToken anumber)
	{
		this.fnumber = anumber;
	}
	
	//open-members
	//close-members
	
}