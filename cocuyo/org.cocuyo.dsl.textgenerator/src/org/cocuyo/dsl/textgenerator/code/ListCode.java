package org.cocuyo.dsl.textgenerator.code;

import java.util.ArrayList;
import java.util.Iterator;

public class ListCode extends BaseCode implements Iterable<ICode>
{
    private final ArrayList<ICode> fList;

    public ListCode()
    {
	fList = new ArrayList<ICode>();
    }

    public ListCode(ICode aCode)
    {
	this();
	fList.add(aCode);
    }

    public ListCode(ICode aCode1, ICode aCode2)
    {
	this(aCode1);
	fList.add(aCode2);
    }

    public void add(ICode aCode)
    {
	assert aCode != null : "violation-> ListCode.add(aCode): aCode != null";

	fList.add(aCode);
    }

    public void addObject(Object aObj)
    {
	if (aObj instanceof ICode)
	    add((ICode) aObj);
	else
	    add(new ObjectCode(aObj));
    }

    @Override
    public Iterator<ICode> iterator()
    {
	return fList.iterator();
    }

    public ArrayList<ICode> getList()
    {
	return fList;
    }

    @Override
    public String getText(int aIndentation)
    {
	String _result = "";

	for (ICode _code : fList)
	{
	    _result += _code.getText(aIndentation);
	}

	return _result;
    }

    @Override
    public String toString()
    {
	return getText(0);
    }

    @Override
    public void addFileWriteCodeTo(ArrayList<FileCode> aUnits)
    {
	for (ICode _code : getList())
	{
	    _code.addFileWriteCodeTo(aUnits);
	}
    }

    @Override
    public void merge(String aContent)
    {
	for (ICode _code : getList())
	{
	    _code.merge(aContent);
	}
    }

    public int getCount()
    {
	return fList.size();
    }

    public Object getCode(int aIndex)
    {
	return fList.get(aIndex);
    }

    public Object getAsResult()
    {
	return getCount() == 1 ? getCode(0) : this;
    }
}
