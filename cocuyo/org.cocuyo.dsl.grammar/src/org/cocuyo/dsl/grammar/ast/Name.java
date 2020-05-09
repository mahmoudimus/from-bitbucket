package org.cocuyo.dsl.grammar.ast;

import java.util.ArrayList;

import org.cocuyo.parsing.IToken;
//open-imports//close-imports
public class Name
//open-inheritance//close-inheritance
{
	private ArrayList<Id> nameidlist;
	//open-fields//close-fields
	
	public Name(ArrayList<Id> nameidlist)
	{
		this.nameidlist = nameidlist;
	}
	//open-members

    public String[] toArray()
    {
	String[] _ids = new String[nameidlist.size()];

	int _i = 0;

	for (Id _id : nameidlist)
	{
	    _ids[_i] = _id.getText();
	    _i++;
	}

	return _ids;
    }

    public IToken getToken()
    {
	return nameidlist.get(0).getToken();
    }

    //close-members
}