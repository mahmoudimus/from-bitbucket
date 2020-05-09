package org.cocuyo.dsl.textgenerator.code;

public class NewlineCode extends BaseCode
{
    public static NewlineCode NEWLINE = new NewlineCode();

    private NewlineCode()
    {

    }

    @Override
    public String getText(int aIndentation)
    {

	return System.getProperty("line.separator")
		+ IndentedTabCode.code.getText(aIndentation);
    }

}
