package org.cocuyo.dsl.grammar.astdesign;

public abstract class ChildNodeModel
{
    private String fName;
    private String fTypeName;

    public ChildNodeModel(String aName, String aTypeName)
    {
	fName = aName;
	fTypeName = aTypeName;
    }

    public String getTypeName()
    {
	return fTypeName;
    }

    public String getName()
    {
	return fName;
    }

    public void setName(String aName)
    {
	fName = aName;
    }

    public void setTypeName(String aTypeName)
    {
	fTypeName = aTypeName;
    }

}
