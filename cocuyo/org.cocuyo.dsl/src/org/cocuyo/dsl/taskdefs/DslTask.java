package org.cocuyo.dsl.taskdefs;

import java.util.ArrayList;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.cocuyo.dsl.DSLInterpreterManager;
import org.cocuyo.dsl.protocol.IObjectContainer;
import org.cocuyo.dsl.protocol.IObjectPackageMember;
import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.dsl.protocol.ObjectPackage;

public abstract class DslTask extends Task {

	private String fObject;

	public IObjectPackageMember findObject(String aName)
			throws NameNotFoundException {
		return getPackage().findByCualifiedName(aName);
	}

	public ObjectPackage getPackage() {
		return DSLInterpreterManager.singleton().getDSLEnvironment()
				.getObjectPackage();
	}

	@Override
	public void execute() throws BuildException {
		DSLInterpreterManager _manager = DSLInterpreterManager.singleton();

		ArrayList<String> _input = new ArrayList<String>();
		_input.add(".");

		_manager.load(_input);

		String _objName = getObject();
		if (_objName != null) {
			try {
				IObjectPackageMember _obj = findObject(_objName);

				processObject(_obj);

			} catch (NameNotFoundException _e) {
				log(_e.getMessage());
			}
		} else {
			log("The property 'object' is required.");
		}
	}

	public boolean checkObjectType(Class aRequireObjectClass,
			IObjectPackageMember pObject) {

		if (!aRequireObjectClass.isAssignableFrom(pObject.getClass())) {
			log("Expecting class " + aRequireObjectClass.getSimpleName()
					+ " found class " + pObject.getClass().getSimpleName());
			return false;
		}
		return true;
	}

	public abstract void processObject(IObjectPackageMember aObject);

	public void setObject(String object) {
		fObject = object;
	}

	public String getObject() {
		return fObject;
	}
}
