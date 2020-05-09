package org.cocuyo.dsl.textgenerator.code;

public class SimpleTabCode extends BaseCode
{
    public static final SimpleTabCode SIMPLE_TAB_CODE = new SimpleTabCode();

    private SimpleTabCode()
    {

    }

    @Override
    public String getText(int aIndentation)
    {
	return "\t";
    }
}
