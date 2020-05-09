package org.cocuyo.dsl.protocol;


public abstract class Named implements INamed
{
    private String fName;

    public Named()
    {

    }

    public Named(String aName)
    {
	fName = aName;
    }

    public String getName()
    {
	return fName;
    }

    public void setName(String aName)
    {
	fName = aName;
    }

}
