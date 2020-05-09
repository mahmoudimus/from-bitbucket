/**
 * 
 */
package org.cocuyo.dsl.textgenerator.code;

import java.util.ArrayList;

/**
 * @author Arian Fornaris Fernandez
 * 
 */
public abstract class BaseCode implements ICode
{
    /*
     * (non-Javadoc)
     * 
     * @see cocuyo.generator.code.ICode#getLowerCase()
     */
    @Override
    public ICode getLowerCase()
    {
	return new StringTransformationCode(this,
		SimpleStringTransform.lowerCase);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cocuyo.generator.code.ICode#getUpperCase()
     */
    @Override
    public ICode getUpperCase()
    {
	return new StringTransformationCode(this,
		SimpleStringTransform.upperCase);
    }

    @Override
    public void addFileWriteCodeTo(ArrayList<FileCode> aUnits)
    {
    }

    @Override
    public void merge(String aContent)
    {
    }
}
