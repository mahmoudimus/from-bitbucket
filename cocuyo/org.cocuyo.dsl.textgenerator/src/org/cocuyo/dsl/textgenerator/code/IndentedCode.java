package org.cocuyo.dsl.textgenerator.code;

import java.util.ArrayList;

public class IndentedCode extends BaseCode
{
    private final ICode fCode;

    public IndentedCode(ICode aCode)
    {
	fCode = aCode;
    }

    @Override
    public String getText(int aIndentation)
    {
	return IndentedTabCode.code.getText(1)
		+ fCode.getText(aIndentation + 1)
	/* + NewlineCode.NEWLINE.getText(aIndentation) */;
    }

    public ICode getCode()
    {
	return fCode;
    }

    @Override
    public void addFileWriteCodeTo(ArrayList<FileCode> aUnits)
    {
	getCode().addFileWriteCodeTo(aUnits);
    }

    @Override
    public void merge(String aContent)
    {
	getCode().merge(aContent);
    }

}
