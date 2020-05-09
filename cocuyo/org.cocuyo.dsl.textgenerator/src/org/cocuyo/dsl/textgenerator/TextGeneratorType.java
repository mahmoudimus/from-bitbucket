package org.cocuyo.dsl.textgenerator;

import static org.cocuyo.util.Text.NEW_LINE;

import java.util.ArrayList;
import java.util.Hashtable;

import org.cocuyo.dsl.IExecutable;
import org.cocuyo.dsl.protocol.IObjectPackageMember;
import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.dsl.protocol.ObjectPackage;
import org.cocuyo.dsl.protocol.ObjectPackageMember;
import org.cocuyo.dsl.protocol.ObjectTable;
import org.cocuyo.dsl.textgenerator.code.ListCode;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;
import org.cocuyo.dsl.textgenerator.env.ImportsEnvironment;
import org.cocuyo.parsing.RecognitionException;

public class TextGeneratorType extends ObjectPackageMember implements
		IExecutable {
	private ObjectTable<TextGeneratorFunc> fFuncs;
	private ArrayList<TextGeneratorType> fSupers;
	private TextGeneratorInstance fInstance;
	private Hashtable<String, TextGeneratorFunc> fAliasFuncs;
	private ImportsEnvironment fImports;
	private Hashtable<TextGeneratorFunc, TextGeneratorFunc> fPointCuts;

	public TextGeneratorType(String aName, ObjectPackage aPackage) {
		super(aName, aPackage);
		fFuncs = new ObjectTable<TextGeneratorFunc>();

		super.setContainer(aPackage);
		fSupers = new ArrayList<TextGeneratorType>();
		fInstance = new TextGeneratorInstance(this);
		fAliasFuncs = new Hashtable<String, TextGeneratorFunc>();
		fImports = new ImportsEnvironment();
		fPointCuts = new Hashtable<TextGeneratorFunc, TextGeneratorFunc>();
	}

	public void addSuper(TextGeneratorType aType) {
		if (aType.isA(this))
			throw new RecognitionException("The generator '" + getName()
					+ "' can not inherit from it self");
		fSupers.add(aType);
	}

	public boolean isA(TextGeneratorType aType) {
		if (this == aType)
			return true;

		for (TextGeneratorType _super : getSupers()) {
			if (_super.isA(aType))
				return true;
		}
		return false;
	}

	public void defineFunction(TextGeneratorFunc aFunc) {
		aFunc.setType(this);
		fFuncs.put(aFunc);
	}

	public boolean containFunction(String aName) {
		boolean _result = fAliasFuncs.containsKey(aName)
				|| fFuncs.contain(aName);

		if (!_result) {
			for (TextGeneratorType _type : fSupers) {
				if (_type.containFunction(aName))
					return true;
			}
		}

		return _result;
	}

	public TextGeneratorFunc findSuperFunction(String aFuncName)
			throws NameNotFoundException {
		for (TextGeneratorType _type : fSupers) {
			try {
				return _type.findFunction(aFuncName);
			} catch (NameNotFoundException _ex) {

			}
		}
		throw new NameNotFoundException(aFuncName);
	}

	public TextGeneratorFunc findFunction(String aFuncName)
			throws NameNotFoundException {
		try {
			if (fAliasFuncs.containsKey(aFuncName)) {
				return fAliasFuncs.get(aFuncName);
			}
			return fFuncs.find(aFuncName);
		} catch (NameNotFoundException _ex) {
			return findSuperFunction(aFuncName);
		}

	}

	@Override
	public String toString() {
		String _str = "gen " + getName() + " extends ";

		for (TextGeneratorType _super : fSupers) {
			_str += _super.getName() + " ";
		}

		_str += NEW_LINE;

		for (TextGeneratorFunc _func : fFuncs.getElements()) {
			_str += "\t" + _func + NEW_LINE;
		}

		return _str;
	}

	public TextGeneratorInstance getInstance() {
		return fInstance;
	}

	public TextGeneratorType findSuper(String aTypeName)
			throws NameNotFoundException {
		for (TextGeneratorType _super : fSupers) {
			if (_super.getName().equals(aTypeName)) {
				return _super;
			}
		}

		for (TextGeneratorType _super : fSupers) {
			try {
				return _super.findSuper(aTypeName);
			} catch (NameNotFoundException _ex) {

			}
		}
		throw new NameNotFoundException(aTypeName);
	}

	public void defineAlias(String aTypeName, String aMemberName, String aAlias)
			throws NameNotFoundException {
		TextGeneratorType _type = null;

		try {
			_type = findSuper(aTypeName);
		} catch (NameNotFoundException _ex) {
			throw new NameNotFoundException(aTypeName, "Generator '"
					+ getName() + "' do not inherit from '" + aTypeName + "'");
		}

		try {
			TextGeneratorFunc _func = _type.findFunction(aMemberName);
			fAliasFuncs.put(aAlias, _func);
		} catch (NameNotFoundException _ex) {
			throw new NameNotFoundException(aMemberName, "Generator '"
					+ _type.getName() + "' do not a contain a definition for '"
					+ aMemberName + '"');
		}
	}

	@Override
	public Object execute(Object... aArgs) {
		try {
			ExecutionEnvironment _env = new ExecutionEnvironment(this,
					getInstance());

			_env.defineLocal("args", aArgs);

			Object _obj = findFunction("main").execute(_env);

			return _obj;
		} catch (NameNotFoundException _e) {

			throw new RecognitionException("Generator '" + getName()
					+ "' don't contain a 'main' function");
		}
	}

	public Object executeSuperFunction(ExecutionEnvironment aEnv,
			Object... aArgs) throws NameNotFoundException {
		TextGeneratorFunc _super = aEnv.getCurrentFunc().getSuper();
		assert _super != null;
		return _super.getType().executeFunction(_super.getName(), aEnv, aArgs);
	}

	public Object executeFunction(String aFunctionName, Object... aArgs)
			throws NameNotFoundException {
		return executeFunction(aFunctionName, new ExecutionEnvironment(this,
				getInstance()), aArgs);
	}

	public Object executeFunction(String aFunctionName,
			ExecutionEnvironment aEnv, Object... aArgs)
			throws NameNotFoundException {
		try {
			TextGeneratorType _old = aEnv.getStaticType();

			ExecutionEnvironment _env = new ExecutionEnvironment(aEnv);
			_env.setStaticType(this);

			// aEnv.setStaticType(this);
			ListCode _list = new ListCode();
			TextGeneratorFunc _func = findFunction(aFunctionName);

			ArrayList<TextGeneratorFunc> _funcs = new ArrayList<TextGeneratorFunc>();
			_funcs.addAll(aEnv.manageFuncCallAspect(_func));

			if (_funcs.isEmpty()) {
				return _func.execute(_env, aArgs);
			}

			for (TextGeneratorFunc _newFunc : _funcs) {
				if (_newFunc != _func && _env.getCurrentFunc() == _newFunc) {
					_newFunc = _func;
				}

				_list.addObject(_newFunc.execute(_env, aArgs));
			}

			// aEnv.setStaticType(_old);

			return _list.getAsResult();
		} catch (NameNotFoundException _ex) {
			throw new NameNotFoundException(aFunctionName, "The generator '"
					+ getName() + "' don't contain a function '"
					+ aFunctionName + "'");
		}
	}

	public void setImportEnv(ImportsEnvironment aImportsEnv) {
		fImports = aImportsEnv;
	}

	public ImportsEnvironment getImportEnv() {
		return fImports;
	}

	public IObjectPackageMember findPackageMember(String... aName)
			throws NameNotFoundException {

		try {
			return getImportEnv().findPackageMember(aName);
		} catch (NameNotFoundException _e) {
			return getContainer().find(aName);
		}

	}

	public void defineAspect(TextGeneratorFunc aOldFunc,
			TextGeneratorFunc aNewFunc) {
		fPointCuts.put(aOldFunc, aNewFunc);
	}

	public Hashtable<TextGeneratorFunc, TextGeneratorFunc> getPointCuts() {
		return fPointCuts;
	}

	public Iterable<TextGeneratorType> getSupers() {
		return fSupers;
	}

}
