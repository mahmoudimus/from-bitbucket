package org.cocuyo.dsl.textgenerator;

import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;


public abstract class TextGeneratorFunc extends TextGeneratorTypeMember
{
    private TextGeneratorType fType;
    private TextGeneratorFunc fSuper;

    public TextGeneratorFunc(String aName)
    {
	super(aName);
    }

    @Override
    public String toString()
    {
	return "fun " + getName();
    }

    public abstract Object execute(ExecutionEnvironment aEnv, Object... aArgs);

    public TextGeneratorType getType()
    {
	return fType;
    }

    public void setType(TextGeneratorType aType)
    {
	fType = aType;
    }

    public TextGeneratorFunc getSuper()
    {
	try
	{
	    if (fSuper == null)
		fSuper = getType().findSuperFunction(getName());
	    return fSuper;
	}
	catch (NameNotFoundException _e)
	{
	    return null;
	}
    }
}
