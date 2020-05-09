package org.cocuyo.dsl.textgenerator.env;

import java.util.ArrayList;

import org.cocuyo.dsl.protocol.IObjectPackageMember;
import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.dsl.protocol.ObjectPackage;

public class ImportsEnvironment {
	private ArrayList<ObjectPackage> fPackages;

	public ImportsEnvironment() {
		fPackages = new ArrayList<ObjectPackage>();
	}

	public void addPackage(ObjectPackage aPackage) {
		fPackages.add(aPackage);
	}

	public IObjectPackageMember findPackageMember(String... aCualifiedName)
			throws NameNotFoundException {
		String _name = "";

		for (ObjectPackage _pkg : fPackages) {
			try {
				return _pkg.find(aCualifiedName);
			} catch (NameNotFoundException _ex) {
				_name = _ex.getName();
			}
		}

		throw new NameNotFoundException(_name);
	}
}
