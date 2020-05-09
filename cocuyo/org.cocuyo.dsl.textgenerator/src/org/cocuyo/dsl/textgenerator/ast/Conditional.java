package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;

//close-imports
public class Conditional extends Code
//open-inheritance//close-inheritance
{
	private ConditionalPart conditionalpart;
	//open-fields//close-fields
	
	public Conditional(ConditionalPart conditionalpart)
	{
		this.conditionalpart = conditionalpart;
	}
	//open-members

    @Override
    public Object execute(ExecutionEnvironment aEnv)
    {
	return conditionalpart.execute(aEnv);
    }

    //close-members
}