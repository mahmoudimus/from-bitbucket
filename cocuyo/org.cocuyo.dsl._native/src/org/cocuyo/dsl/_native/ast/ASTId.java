package org.cocuyo.dsl._native.ast;

//open-imports

import org.cocuyo.parsing.IToken;

//close-imports
public class ASTId implements IASTRoot
//open-inheritance
//close-inheritance
{
	
	private IToken id;
	
	public ASTId(IToken id)
	{
		this.id = id;
	}
	
	//open-members
	public String getText()
	{
		return id.getText();
	}
	//close-members
	
}