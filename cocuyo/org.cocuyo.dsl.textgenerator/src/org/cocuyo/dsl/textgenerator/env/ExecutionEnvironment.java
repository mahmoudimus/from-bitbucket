package org.cocuyo.dsl.textgenerator.env;

import java.util.ArrayList;
import java.util.Hashtable;

import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.dsl.protocol.Named;
import org.cocuyo.dsl.protocol.ObjectTable;
import org.cocuyo.dsl.textgenerator.TextGeneratorFunc;
import org.cocuyo.dsl.textgenerator.TextGeneratorInstance;
import org.cocuyo.dsl.textgenerator.TextGeneratorType;

public class ExecutionEnvironment
{
	private ObjectTable<Local> fLocals;
	private TextGeneratorType fStaticType;
	private TextGeneratorInstance fInstance;
	private Hashtable<TextGeneratorFunc, ArrayList<TextGeneratorFunc>> fAspects;
	private ExecutionEnvironment fParent;
	private TextGeneratorFunc fCurrentFunc;

	static class Local extends Named
	{
		private Object fValue;

		public Local(String aName, Object aValue)
		{
			super(aName);
			fValue = aValue;
		}

		public Object getValue()
		{
			return fValue;
		}

		public void setValue(Object aValue)
		{
			fValue = aValue;
		}
	}

	public ExecutionEnvironment(TextGeneratorType aStaticType,
			TextGeneratorInstance aInstance)
	{
		fLocals = new ObjectTable<Local>();
		fStaticType = aStaticType;
		fInstance = aInstance;
		fAspects = new Hashtable<TextGeneratorFunc, ArrayList<TextGeneratorFunc>>();
	}

	public ExecutionEnvironment(ExecutionEnvironment aParent)
	{
		this(aParent.getStaticType(), aParent.getInstance());
		fParent = aParent;
	}

	public void defineLocal(String aName, Object aValue)
	{
		fLocals.put(new Local(aName, aValue));
	}

	public boolean containLocal(String aName)
	{
		return fLocals.contain(aName) || !isRoot()
				&& getParent().containLocal(aName);
	}

	public Local findLocal(String aName) throws NameNotFoundException
	{
		try
		{
			return fLocals.find(aName);
		} catch (NameNotFoundException _ex)
		{
			if (isRoot())
			{
				throw _ex;
			} else
			{
				return getParent().findLocal(aName);
			}
		}
	}

	public Object findObject(String aName) throws NameNotFoundException
	{
		try
		{
			return findLocal(aName).getValue();
		} catch (NameNotFoundException _ex)
		{
			TextGeneratorType _type = fInstance.getType();

			if (_type.containFunction(aName))
			{
				return _type.executeFunction(aName, this);
			} else
			{
				return fStaticType.findPackageMember(aName);
			}
		}
	}

	public TextGeneratorInstance getInstance()
	{
		return fInstance;
	}

	public TextGeneratorType getStaticType()
	{
		return fStaticType;
	}

	public void setStaticType(TextGeneratorType aType)
	{
		fStaticType = aType;
	}

	public ArrayList<TextGeneratorFunc> manageFuncCallAspect(
			TextGeneratorFunc aFunc)
	{
		ArrayList<TextGeneratorFunc> _list = null;

		if (fAspects.containsKey(aFunc))
		{
			_list = fAspects.get(aFunc);
		}

		if (_list == null)
			_list = new ArrayList<TextGeneratorFunc>();

		if (!isRoot())
		{
			_list.addAll(getParent().manageFuncCallAspect(aFunc));
		}

		return _list;
	}

	public void registerAspect(TextGeneratorFunc aOldFunc,
			TextGeneratorFunc aNewFunc)
	{
		ArrayList<TextGeneratorFunc> _list = null;

		if (fAspects.containsKey(aOldFunc))
		{
			_list = fAspects.get(aOldFunc);
		} else
		{
			_list = new ArrayList<TextGeneratorFunc>();
			fAspects.put(aOldFunc, _list);
		}

		_list.add(aNewFunc);

	}

	public void registerAspectsOf(TextGeneratorType aType)
	{
		for (TextGeneratorType _type : aType.getSupers())
		{
			registerAspectsOf(_type);
		}

		for (TextGeneratorFunc _func : aType.getPointCuts().keySet())
		{
			TextGeneratorFunc _newFunc = aType.getPointCuts().get(_func);
			registerAspect(_func, _newFunc);
		}

	}

	public boolean isRoot()
	{
		return fParent == null;
	}

	public ExecutionEnvironment getParent()
	{
		return fParent;
	}

	public void setAspectListenresOf(ExecutionEnvironment aEnv)
	{
		ExecutionEnvironment _env = aEnv;

		do
		{
			fAspects.putAll(_env.fAspects);
			_env = _env.getParent();
		} while (_env != null);
	}

	public TextGeneratorFunc getCurrentFunc()
	{
		return fCurrentFunc == null && !isRoot() ? getParent().getCurrentFunc()
				: fCurrentFunc;
	}

	public void setCurrentFunc(TextGeneratorFunc aCurrentFunc)
	{
		fCurrentFunc = aCurrentFunc;
	}

	public void setInstance(TextGeneratorInstance aInstance)
	{
		fInstance = aInstance;
	}
}
