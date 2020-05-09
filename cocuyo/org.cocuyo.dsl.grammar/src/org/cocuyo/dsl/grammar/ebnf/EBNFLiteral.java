package org.cocuyo.dsl.grammar.ebnf;

import java.util.Hashtable;

public class EBNFLiteral extends EBNFTerminal
{

    private static int fGlobalCount = 0;
    private static Hashtable<String, String> fNameTable = new Hashtable<String, String>();

    @Override
    public String getNodeName()
    {
	return getID() != null ? getID()
		: (getTypeName() != null ? getTypeName() : getName());
    }

    public boolean isLiteral()
    {
	return true;
    }

    @Override
    public boolean isAst()
    {
	return getNodeTypeName() != null;
    }

    @Override
    public String getTypeName()
    {
	return getNodeTypeName();
    }

    @Override
    public String getName()
    {
	if (fNameTable.containsKey(getText()))
	    return fNameTable.get(getText());

	String _name = "LIT_" + fGlobalCount++;
	fNameTable.put(getText(), _name);
	return _name;
    }

}
