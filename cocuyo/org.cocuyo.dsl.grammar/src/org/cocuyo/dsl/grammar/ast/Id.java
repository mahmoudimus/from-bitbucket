package org.cocuyo.dsl.grammar.ast;

import org.cocuyo.parsing.IToken;
//open-imports//close-imports
public class Id
//open-inheritance//close-inheritance
{
	private IToken token;
	//open-fields//close-fields
	
	public Id(IToken token)
	{
		this.token = token;
	}
	//open-members

    public String getText()
    {
	return token.getText();
    }

    public IToken getToken()
    {
	return token;
    }

    //close-members
}