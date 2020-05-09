package org.cocuyo.dsl.textgenerator.code;

import java.util.ArrayList;

public class ObjectCode implements ICode
{
    private final Object fObject;

    public ObjectCode(Object aObject)
    {
	fObject = aObject;
    }

    public Object getObject()
    {
	return fObject instanceof ObjectCode ? ((ObjectCode) fObject)
		.getObject() : fObject;
    }

    @Override
    public ICode getLowerCase()
    {
	assert false;
	return null;
    }

    @Override
    public ICode getUpperCase()
    {
	assert false;
	return null;
    }

    @Override
    public String getText(int aIndantation)
    {
	return fObject != null ? fObject instanceof ICode ? ((ICode) fObject)
		.getText(aIndantation) : fObject.toString() : "<NULL>";
    }

    @Override
    public void addFileWriteCodeTo(ArrayList<FileCode> aUnits)
    {
	if (fObject instanceof ICode)
	{
	    ICode _code = (ICode) fObject;
	    _code.addFileWriteCodeTo(aUnits);
	}
    }

    @Override
    public void merge(String aContent)
    {
	if (fObject instanceof ICode)
	{
	    ICode _code = (ICode) fObject;
	    _code.merge(aContent);
	}
    }

    @Override
    public String toString()
    {
	return fObject.toString();
    }

}
