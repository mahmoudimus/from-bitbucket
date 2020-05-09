package org.cocuyo.parsing.debug;

public class NonTerminalNode
{
    private String fName;

    public NonTerminalNode(String aName)
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

    @Override
    public String toString()
    {
	return getName();
    }

}
