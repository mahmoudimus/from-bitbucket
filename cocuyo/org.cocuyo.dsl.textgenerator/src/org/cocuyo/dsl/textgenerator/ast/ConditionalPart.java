package org.cocuyo.dsl.textgenerator.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.textgenerator.code.ListCode;
import org.cocuyo.dsl.textgenerator.code.StringCode;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;

//close-imports
public class ConditionalPart
//open-inheritance//close-inheritance
{
	private Condition condition;
	private ArrayList<Code> docode;
	private ArrayList<Code> elsecode;
	private ConditionalPart conditionalpart;
	//open-fields//close-fields
	
	public ConditionalPart(Condition condition, ArrayList<Code> docode, ArrayList<Code> elsecode)
	{
		this.condition = condition;
		this.docode = docode;
		this.elsecode = elsecode;
	}
	public ConditionalPart(Condition condition, ArrayList<Code> docode, ConditionalPart conditionalpart)
	{
		this.condition = condition;
		this.docode = docode;
		this.conditionalpart = conditionalpart;
	}
	public ConditionalPart(Condition condition, ArrayList<Code> docode)
	{
		this.condition = condition;
		this.docode = docode;
	}
	//open-members
    public Object execute(ExecutionEnvironment aEnv)
    {
	if (condition.execute(aEnv))
	{
	    ListCode _list = new ListCode();

	    for (Code _code : docode)
	    {
		_list.addObject(_code.execute(aEnv));
	    }
	    return _list;
	}
	else
	{
	    if (conditionalpart != null)
	    {
		return conditionalpart.execute(aEnv);
	    }
	    else
	    {
		if (elsecode != null)
		{
		    ListCode _list = new ListCode();

		    for (Code _code : elsecode)
		    {
			_list.addObject(_code.execute(aEnv));
		    }
		    return _list;
		}
	    }
	}

	return StringCode.VOID_CODE;
    }
    //close-members
}