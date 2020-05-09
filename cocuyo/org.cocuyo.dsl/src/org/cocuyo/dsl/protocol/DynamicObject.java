package org.cocuyo.dsl.protocol;

import java.util.Hashtable;

public class DynamicObject implements IObject
{
    private Hashtable<String, Object> fMembers;

    public DynamicObject()
    {
	fMembers = new Hashtable<String, Object>();
    }

    @Override
    public boolean containProperty(String aPropertyName)
    {
	return fMembers.containsKey(aPropertyName);
    }

    @Override
    public Object getProperty(String aPropertyName)
    {
	return fMembers.get(aPropertyName);
    }

    public void setProperty(String aPropertyName, Object aValue)
    {
	fMembers.put(aPropertyName, aValue);
    }

}
