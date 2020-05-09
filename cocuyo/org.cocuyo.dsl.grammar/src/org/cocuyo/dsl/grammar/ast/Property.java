package org.cocuyo.dsl.grammar.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.grammar.ebnf.EBNFGrammar;
import org.cocuyo.dsl.protocol.ObjectProtocolException;
import org.cocuyo.dsl.protocol.ReflectionAPI;
import org.cocuyo.parsing.IToken;
import org.cocuyo.parsing.RecognitionError;
import org.cocuyo.parsing.RecognitionException;


//close-imports
public class Property
//open-inheritance//close-inheritance
{
	private Name name;
	private Value value;
	private ArrayList<Property> propertylist;
	private IToken not;
	//open-fields//close-fields
	
	public Property(Name name, Value value)
	{
		this.name = name;
		this.value = value;
	}
	public Property(Name name, ArrayList<Property> propertylist)
	{
		this.name = name;
		this.propertylist = propertylist;
	}
	public Property(Name name)
	{
		this.name = name;
	}
	public Property(IToken not, Name name)
	{
		this.not = not;
		this.name = name;
	}
	//open-members

    public void build(EBNFGrammar aGrammar, Object aObj)
    {
	Object _value = null;
	String[] _name = name.toArray();

	if (value != null)
	{
	    _value = value.evaluate(aGrammar);
	}
	try
	{
	    ReflectionAPI.setInnerProperty(aObj, _name, _value);
	}
	catch (ObjectProtocolException _e)
	{
	    throw new RecognitionException(new RecognitionError(
		    _e.getMessage(), name.getToken()));
	}
    }
    //close-members
}