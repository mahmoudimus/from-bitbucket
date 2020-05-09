package org.cocuyo.dsl.textgenerator.code;

public class IndentedTabCode extends BaseCode
{
    public static IndentedTabCode code = new IndentedTabCode();

    private IndentedTabCode()
    {

    }

    @Override
    public String getText(int aIndentation)
    {
	String _tabs = "";

	for (int _i = 0; _i < aIndentation; _i++)
	{
	    _tabs += "\t";
	}
	return _tabs;
    }
}
