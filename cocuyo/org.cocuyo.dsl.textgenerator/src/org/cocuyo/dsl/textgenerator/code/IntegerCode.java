package org.cocuyo.dsl.textgenerator.code;

public class IntegerCode extends BaseCode
{
    private Integer fValue;

    public IntegerCode(int aValue)
    {
	fValue = aValue;
    }

    @Override
    public String getText(int aIndentation)
    {
	return new String(new byte[] { fValue.byteValue() });
    }
}
