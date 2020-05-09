package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;
import org.cocuyo.parsing.RecognitionError;
import org.cocuyo.parsing.RecognitionException;


//close-imports
public class ObjectId extends Code
//open-inheritance//close-inheritance
{
	private Id id;
	//open-fields//close-fields
	
	public ObjectId(Id id)
	{
		this.id = id;
	}
	public ObjectId()
	{
		
	}
	//open-members

    @Override
    public Object execute(ExecutionEnvironment aEnv)
    {
	try
	{
	    return id == null ? aEnv.getInstance().getType()
		    .executeSuperFunction(aEnv) : aEnv.findObject(id.getText());
	}
	catch (NameNotFoundException _e)
	{
	    throw new RecognitionException(new RecognitionError(
		    _e.getMessage(), id.getToken()));
	}
    }
    //close-members
}