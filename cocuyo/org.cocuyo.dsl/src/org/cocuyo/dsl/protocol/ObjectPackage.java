package org.cocuyo.dsl.protocol;

public class ObjectPackage extends SimpleObjectContainer<IObjectPackageMember>
		implements IObjectPackageMember {

	public ObjectPackage(String aName) {
		super(aName, null);
	}

	public ObjectPackage(String aName, ObjectPackage aContainer) {
		super(aName, aContainer);
	}

	/**
	 * Find a ObjectPackage with cualified name aNames o create one
	 * @param aNames
	 * @return
	 */
	public ObjectPackage definePackage(String... aNames) {
		ObjectPackage _result = this;

		for (String _name : aNames) {
			_result = _result.definePackage(_name);
		}

		return _result;
	}

	/**
	 * Find a defined ObjectPackage with name aName or create one
	 * @param aName
	 * @return
	 */
	public ObjectPackage definePackage(String aName) {
		ObjectPackage _result = null;
		IObjectPackageMember _defined = null;

		if (contain(aName)) {
			try {
				_defined = findDefined(aName);
			} catch (NameNotFoundException _e) {
			}
			if (_defined instanceof ObjectPackage) {
				_result = (ObjectPackage) _defined;
			}
		}
		if (_result == null) {
			ObjectPackage _pkg = new ObjectPackage(aName, this);
			_result = _pkg;
			define(_pkg);
		}

		return _result;

	}

	public IObjectPackageMember findByCualifiedName(String aName)
			throws NameNotFoundException {
		return find(aName.split("\\."));
	}

	@Override
	public String getFullName() {
		String _result = getName();
		if (!isRoot() && !getContainer().isRoot()) {
			_result = getContainer().getFullName() + "." + _result;
		}
		return _result;
	}

	public IObjectPackageMember find(String aName) throws NameNotFoundException {
		return (IObjectPackageMember) super.findObject(aName);
	}

	public IObjectPackageMember find(String... aNames)
			throws NameNotFoundException {
		Object _result = null;
		for (String _name : aNames) {
			if (_result == null) {
				_result = find(_name);
			} else {
				if (_result instanceof ObjectPackage) {
					_result = ((ObjectPackage) _result).findDefined(_name);
				} else {
					throw new NameNotFoundException(_name, "The object '"
							+ _name + "' is not a valid package member");
				}
			}
		}
		return (IObjectPackageMember) _result;
	}

	@Override
	public ObjectPackage getRoot() {
		return isRoot() ? this : getContainer().getRoot();
	}

	@Override
	public ObjectPackage getContainer() {
		return (ObjectPackage) super.getContainer();
	}
}
