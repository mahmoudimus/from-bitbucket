package org.cocuyo.dsl.protocol;


public class SimpleObjectContainer<TNamed extends INamed> extends Named
		implements IObjectContainer<TNamed> {
	private ObjectTable<TNamed> fTable;
	private ObjectTable<INamed> fIncludeTable;
	private IObjectContainer<TNamed> fContainer;

	public SimpleObjectContainer(String aName, IObjectContainer aContainer) {
		super(aName);
		fContainer = aContainer;
		fTable = new ObjectTable<TNamed>();
		fIncludeTable = new ObjectTable<INamed>();
	}

	@Override
	public void define(TNamed aNamed) {
		fTable.put(aNamed);
	}

	@Override
	public TNamed findDefined(String aName) throws NameNotFoundException {
		return fTable.find(aName);
	}

	@Override
	public INamed findObject(String... aNames) throws NameNotFoundException {
		Object _result = null;
		for (String _name : aNames) {
			if (_result == null) {
				_result = findObject(_name);
			} else {
				if (_result instanceof IObjectContainer) {
					_result = ((IObjectContainer<INamed>) _result)
							.findDefined(_name);
				}
			}
		}
		return (INamed) _result;
	}

	@Override
	public <TNamed extends INamed> TNamed findObject(Class<TNamed> aClass,
			String... aNames) throws NameNotFoundException,
			InvalidNameClassException {

		INamed _obj = findObject(aNames);

		if (!aClass.isInstance(_obj))
			throw new InvalidNameClassException(aClass,
					"Unexpected class for name '#name'.", aNames);
		else
			return (TNamed) _obj;
	}

	public INamed findObject(String aName) throws NameNotFoundException {
		INamed _result = null;
		try {
			_result = findDefined(aName);
		} catch (NameNotFoundException _e) {
			try {
				_result = findIncluded(aName);
			} catch (NameNotFoundException _ex) {
				if (isRoot())
					throw _e;
				else
					_result = getContainer().findObject(aName);
			}
		}
		return _result;
	}

	protected void include(INamed aObject) {
		fIncludeTable.put(aObject);
	}

	protected INamed findIncluded(String aName) throws NameNotFoundException {
		return fIncludeTable.find(aName);
	}

	public IObjectContainer getContainer() {
		return fContainer;
	}

	public Iterable<TNamed> getElements() {
		return fTable.getElements();
	}

	@Override
	public boolean contain(String aName) {
		return fTable.contain(aName);
	}

	public boolean isRoot() {
		return getContainer() == null;
	}

}
