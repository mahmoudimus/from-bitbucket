package org.cocuyo.dsl._native;

import org.cocuyo.dsl.protocol.ObjectPackage;
import org.cocuyo.dsl.protocol.ObjectPackageMember;

public class NativeObject extends ObjectPackageMember {

	private String fDefinition;

	public NativeObject(String aName, ObjectPackage aContainer) {
		super(aName, aContainer);
	}

	public NativeObject(String aName, String aTextDefinition,
			ObjectPackage aContainer) {
		this(aName, aContainer);
		fDefinition = aTextDefinition;
	}

	public void setDefinition(String aTextDefinition) {
		fDefinition = aTextDefinition;
	}

	public String getDefinition() {
		return fDefinition;
	}

}
