package org.cocuyo.dsl.textgenerator;

public class TextGeneratorInstance
{
    private TextGeneratorType fType;

    public TextGeneratorInstance(TextGeneratorType aType)
    {
	fType = aType;
    }

    public TextGeneratorType getType()
    {
	return fType;
    }
}
