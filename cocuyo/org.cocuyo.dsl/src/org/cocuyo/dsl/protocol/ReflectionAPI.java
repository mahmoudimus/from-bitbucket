package org.cocuyo.dsl.protocol;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Stack;

public abstract class ReflectionAPI
{

    public static void setInnerProperty(Object aObj, String[] aPropNames,
	    Object aValue) throws ObjectProtocolException
    {
	int _last = aPropNames.length - 1;

	for (int _i = 0; _i <= _last; _i++)
	{
	    String _name = aPropNames[_i];

	    if (_i < _last)
	    {
		aObj = getProperty(aObj, _name);
	    }
	    else
	    {
		setProperty(aObj, _name, aValue);
	    }
	}
    }

    public static void setProperty(Object aObj, String aPropertyName,
	    Object aValue) throws ObjectProtocolException
    {
	try
	{
	    Object _result = null;

	    String[] _names = posibleSetNamesOf(aPropertyName);

	    for (String _name : _names)
	    {
		Class _class = aObj.getClass();
		try
		{
		    Field _field;
		    _field = _class.getField(_name);
		    _field.set(aObj, aValue);
		    return;
		}
		catch (SecurityException _e)
		{
		}
		catch (NoSuchFieldException _e)
		{
		}
		catch (IllegalArgumentException _e)
		{
		}
		catch (IllegalAccessException _e)
		{
		}

		try
		{
		    Method _method = _class.getMethod(_name, aValue.getClass());
		    _method.invoke(aObj, new Object[] { aValue });
		    return;
		}
		catch (SecurityException _e)
		{
		}
		catch (NoSuchMethodException _e)
		{
		}
		catch (InvocationTargetException _e)
		{
		    throw new RuntimeException(_e.getTargetException());
		}
		catch (IllegalArgumentException _e)
		{
		}
		catch (IllegalAccessException _e)
		{
		}
	    }
	}
	catch (NullPointerException _ex)
	{
	    throw new ObjectProtocolException(
		    "Accesising to a null value when invoking '"
			    + aPropertyName + "'");
	}
	throw new ObjectProtocolException("Invlaid property '" + aPropertyName
		+ "' acces in instance of type '"
		+ aObj.getClass().getSimpleName() + "'");
    }

    public static Object getProperty(Object aObj, String aPropertyName)
	    throws ObjectProtocolException
    {

	try
	{
	    if (aObj == null)
		throw new NullPointerException();

	    Object _result = null;

	    String[] _names = posibleGetNamesOf(aPropertyName);

	    if (aObj instanceof IObject
		    && ((IObject) aObj).containProperty(aPropertyName))
	    {
		return ((IObject) aObj).getProperty(aPropertyName);
	    }

	    for (String _name : _names)
	    {

		Class _class = aObj.getClass();
		try
		{
		    Field _field;
		    _field = _class.getField(_name);
		    return _field.get(aObj);
		}
		catch (SecurityException _e)
		{
		}
		catch (NoSuchFieldException _e)
		{
		}
		catch (IllegalArgumentException _e)
		{
		}
		catch (IllegalAccessException _e)
		{
		}

		try
		{
		    Method _method;
		    _method = _class.getMethod(_name, null);

		    return _method.invoke(aObj, new Object[] {});
		}
		catch (SecurityException _e)
		{
		}
		catch (NoSuchMethodException _e)
		{
		}
		catch (InvocationTargetException _e)
		{
		    throw new ObjectProtocolException(
			    "Invokation of property '" + aPropertyName
				    + "' to object '" + aObj + "'" + " throws "
				    + _e.getTargetException());
		}
		catch (IllegalArgumentException _e)
		{
		}
		catch (IllegalAccessException _e)
		{
		}
	    }
	}
	catch (NullPointerException _ex)
	{
	    throw new ObjectProtocolException(
		    "Accesising to a null value when invoking '"
			    + aPropertyName + "'");
	}
	throw new ObjectProtocolException("Invlaid property '" + aPropertyName
		+ "' acces in instance of type '"
		+ aObj.getClass().getSimpleName() + "'");
    }

    public static String[] posibleGetNamesOf(String aName)
    {
	return new String[] {
		fromScriptToJavaGetProperty(fromScriptToCamell(aName)),
		fromScriptToCamell(aName), aName };
    }

    public static String[] posibleSetNamesOf(String aName)
    {
	return new String[] {
		fromScriptToJavaSetProperty(fromScriptToCamell(aName)),
		fromScriptToCamell(aName), aName };
    }

    public static String fromScriptToCamell(String aName)
    {
	Character _char = 0;
	Stack<String> _stack = new Stack<String>();
	StringBuilder _nameBuilder = new StringBuilder();

	for (int _i = 0; _i < aName.length(); _i++)
	{
	    _char = aName.charAt(_i);

	    if (_i < aName.length() - 1 && aName.charAt(_i + 1) == '_')
	    {
		_nameBuilder.append(_char.toString());
		_nameBuilder.append(((Character) aName.charAt(_i + 2))
			.toString().toUpperCase());
		_i += 2;
	    }
	    else
	    {
		_nameBuilder.append(_char);
	    }
	}

	return _nameBuilder.toString();
    }

    public static String fromScriptToJavaGetProperty(String aName)
    {
	String _propName = "get" + aName.substring(0, 1).toUpperCase()
		+ aName.substring(1, aName.length());

	return _propName;
    }

    public static String fromScriptToJavaSetProperty(String aName)
    {
	String _propName = "set" + aName.substring(0, 1).toUpperCase()
		+ aName.substring(1, aName.length());

	return _propName;
    }

}
