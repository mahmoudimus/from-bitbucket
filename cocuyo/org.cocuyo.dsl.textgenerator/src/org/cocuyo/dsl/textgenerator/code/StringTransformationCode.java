/**
 * 
 */
package org.cocuyo.dsl.textgenerator.code;

import java.util.ArrayList;

/**
 * @author Arian Fornaris Fernandez
 * 
 */
public class StringTransformationCode extends BaseCode
{
    private final ICode fCode;
    private final IStringTransformation fTransform;

    public StringTransformationCode(ICode aCode,
	    IStringTransformation aTransform)
    {
	fCode = aCode;
	fTransform = aTransform;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cocuyo.generator.code.ICode#getText(int)
     */
    @Override
    public String getText(int indentation)
    {
	return fTransform.transform(fCode.getText(indentation));
    }

    @Override
    public void addFileWriteCodeTo(ArrayList<FileCode> aUnits)
    {
	fCode.addFileWriteCodeTo(aUnits);
    }

    @Override
    public void merge(String aContent)
    {
	fCode.merge(aContent);
    }

}
