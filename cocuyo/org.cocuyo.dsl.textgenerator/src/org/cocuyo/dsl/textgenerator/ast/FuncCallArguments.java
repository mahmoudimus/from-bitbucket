package org.cocuyo.dsl.textgenerator.ast;

import java.util.ArrayList;
import java.util.Iterator;
//open-imports//close-imports
public class FuncCallArguments
//open-inheritance
	implements Iterable<FuncCallArg>
//close-inheritance
{
	private ArrayList<FuncCallArg> funccallarglist;
	//open-fields//close-fields
	
	public FuncCallArguments(ArrayList<FuncCallArg> funccallarglist)
	{
		this.funccallarglist = funccallarglist;
	}
	//open-members
    @Override
    public Iterator<FuncCallArg> iterator()
    {
	return funccallarglist.iterator();
    }
    //close-members
}