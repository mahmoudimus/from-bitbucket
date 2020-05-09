package org.cocuyo.env;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Hashtable;

public class ShellArguments
{
	private ArrayList<String> fFreeValues;
	private Hashtable<String, ArrayList<String>> fArgumentsTable;

	public ShellArguments()
	{
		fFreeValues = new ArrayList<String>();
		fArgumentsTable = new Hashtable<String, ArrayList<String>>();
	}

	public void parse(String[] aArgs)
	{
		fArgumentsTable = new Hashtable<String, ArrayList<String>>();

		ArrayList<String> _freeValues = new ArrayList<String>();

		String _key = null;
		ArrayList<String> _values = null;

		for (String _arg : aArgs)
		{

			if (isParamKey(_arg))
			{
				_key = _arg.substring(1);
				_values = getParamValue(_key);

				if (_values == null)
				{
					_values = new ArrayList<String>();
					fArgumentsTable.put(_key, _values);
				}
			} else
			{
				if (_key == null)
				{
					_freeValues.add(_arg);
				} else
				{
					_values.add(_arg);
				}
			}
		}

		fFreeValues = _freeValues;
	}

	public ArrayList<String> getParamValue(String aParamName)
	{
		return fArgumentsTable.get(aParamName);
	}

	public String getFirstParamValueAndCheck(String aParamName)
			throws ShellArgumentNotFoundException
	{
		String _result = getFirstParamValue(aParamName);

		if (_result == null)
		{
			throw new ShellArgumentNotFoundException(aParamName);
		}

		return _result;
	}

	public String getFirstParamValue(String aParaName)
	{
		if (existValue(aParaName))
		{
			ArrayList<String> _values = fArgumentsTable.get(aParaName);
			return _values.size() > 0 ? _values.get(0) : null;
		}

		return null;
	}

	public boolean existValue(String aParamName)
	{
		return fArgumentsTable.containsKey(aParamName)
				&& getParamValue(aParamName) != null
				&& getParamValue(aParamName).size() > 0;
	}

	public ArrayList<String> getFreeValues()
	{
		return fFreeValues;
	}

	public String getFirstFreeValue()
	{
		return fFreeValues.size() > 0 ? fFreeValues.get(0) : null;
	}

	/**
	 * For debug. Print all pair key-value
	 */
	public void print()
	{
		for (String _key : fArgumentsTable.keySet())
		{
			out.println(_key);

			for (String _value : getParamValue(_key))
			{
				out.println("\t" + _value);
			}

		}

		out.println();
		out.println("Free values:");

		for (String _value : fFreeValues)
		{
			out.println(_value);
		}
	}

	protected boolean isParamKey(String aString)
	{
		return (aString.length() > 1) && (aString.charAt(0) == '-');
	}

	public boolean containParam(String aParamName)
	{
		return fArgumentsTable.containsKey(aParamName);
	}

	public String getFirstParamValueOr(String aParamName, String aDefaultValue)
	{
		return containParam(aParamName) ? getFirstParamValue(aParamName)
				: aDefaultValue;
	}

	public boolean isEmpty()
	{
		return fArgumentsTable.isEmpty() && fFreeValues.isEmpty();
	}

	public void removeParam(String aString)
	{
		fArgumentsTable.remove(aString);
		fArgumentsTable.remove("-" + aString);
	}

}
