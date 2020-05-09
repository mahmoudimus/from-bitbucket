package org.cocuyo.dsl.grammar.astdesign;

import java.util.ArrayList;

public class InitMethodModel
{
    private String fName;
    private ArrayList<ChildNodeModel> fArgs;

    public InitMethodModel(String aName)
    {
	fName = aName;
	fArgs = new ArrayList<ChildNodeModel>();
    }

    public String getName()
    {
	return fName;
    }

    public void addArg(ChildNodeModel aArg)
    {
	fArgs.add(aArg);
    }

    public Iterable<ChildNodeModel> getArgs()
    {
	return fArgs;
    }

    public boolean match(InitMethodModel aMethod)
    {

	if (fArgs.size() == aMethod.fArgs.size())
	{
	    for (int _i = 0; _i < fArgs.size(); _i++)
	    {
		ChildNodeModel _left = fArgs.get(_i);
		ChildNodeModel _right = aMethod.fArgs.get(_i);

		if (!_left.getTypeName().equals(_right.getTypeName()))
		    return false;
	    }
	    return true;
	}

	return false;
    }

    @Override
    public String toString()
    {
	String _str = "constructor " + getName() + "( ";
	for (ChildNodeModel _arg : getArgs())
	    _str += _arg + ", ";
	return _str + ")";
    }

    public int getCountArgs()
    {
	return fArgs.size();
    }

    public ChildNodeModel getArg(int aIndex)
    {
	return fArgs.get(aIndex);
    }
}
