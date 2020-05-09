/**
 * 
 */
package org.cocuyo.dsl.textgenerator.code;

/**
 * @author Arian Fornaris Fernandez
 * 
 */
public class MergeCode extends BaseCode
{
    private final ICode fStartLabel;
    private final ICode fEndLabel;
    private ICode fUserCode;

    public MergeCode(ICode aStartLabel, ICode aEndLabel, ICode aDefaultUserCode)
    {
	fStartLabel = aStartLabel;
	fEndLabel = aEndLabel;
	fUserCode = aDefaultUserCode == null ? new StringCode("")
		: aDefaultUserCode;
    }

    public MergeCode(ICode aStartLabel, ICode aEndLabel)
    {
	this(aStartLabel, aEndLabel, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cocuyo.generator.code.ICode#getText(int)
     */
    @Override
    public String getText(int indentation)
    {
	return fStartLabel.getText(indentation)
		+ fUserCode.getText(indentation)
		+ fEndLabel.getText(indentation);
    }

    @Override
    public void merge(String aContent)
    {
	try
	{
	    String _startTag = fStartLabel.getText(0);
	    String _endTag = fEndLabel.getText(0);
	    int _start = aContent.indexOf(_startTag) + _startTag.length();
	    int _end = aContent.indexOf(_endTag, _start);
	    String _userCode = aContent.substring(_start, _end);
	    fUserCode = new StringCode(_userCode);
	}
	catch (IndexOutOfBoundsException _ex)
	{
	}
    }
}
