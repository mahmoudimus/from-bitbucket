package org.cocuyo.dsl.protocol;

import java.util.Hashtable;


public class ObjectTable<TNamed extends INamed>
{
	private Hashtable<String, TNamed> fHashTable;

	public ObjectTable()
	{
		fHashTable = new Hashtable<String, TNamed>();
	}

	public void put(TNamed aNamed)
	{
		fHashTable.put(aNamed.getName(), aNamed);
	}

	public boolean contain(String aName)
	{
		return fHashTable.containsKey(aName);
	}

	public TNamed find(String aName) throws NameNotFoundException
	{
		if (!fHashTable.containsKey(aName))
			throw new NameNotFoundException(aName);
		return fHashTable.get(aName);
	}

	public Iterable<TNamed> getElements()
	{
		return fHashTable.values();
	}
}
