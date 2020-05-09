package org.cocuyo.dsl.textgenerator;

import org.cocuyo.dsl.protocol.Named;
import org.cocuyo.dsl.textgenerator.ast.Code;


public class TextGeneratorFuncArg extends Named
{
    private Code fDefaultValue;

    public TextGeneratorFuncArg(String aName)
    {
	super(aName);
    }

    public TextGeneratorFuncArg(String aName, Code aDefaultValue)
    {
	this(aName);
	fDefaultValue = aDefaultValue;
    }

    public Code getDefaultValue()
    {
	return fDefaultValue;
    }
}
