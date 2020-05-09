package org.cocuyo.dsl.grammar.astdesign;

public class SimpleChildNodeModel extends ChildNodeModel
{
    private boolean fIsFromTerminal;

    public SimpleChildNodeModel(String aName, String aTypeName, boolean aIsFromTerminal)
    {
	super(aName, aTypeName);
	fIsFromTerminal = aIsFromTerminal;
    }

    @Override
    public String toString()
    {
	return getTypeName() + " " + getName().toLowerCase();
    }

    public SimpleChildNodeModel getSimilar()
    {
	return new SimpleChildNodeModel(getName(), getTypeName(),
		isFromTerminal());
    }

    public boolean isFromTerminal()
    {
	return fIsFromTerminal;
    }

    public void setIsFromTerminal(boolean aValue)
    {
	fIsFromTerminal = aValue;
    }

}
