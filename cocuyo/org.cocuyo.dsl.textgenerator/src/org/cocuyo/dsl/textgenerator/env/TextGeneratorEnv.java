package org.cocuyo.dsl.textgenerator.env;

import org.cocuyo.dsl.protocol.ObjectPackage;

public class TextGeneratorEnv {
	private ObjectPackage fPackage;

	public TextGeneratorEnv(ObjectPackage aPackage) {
		fPackage = aPackage;
	}

	public ObjectPackage getPackage() {
		return fPackage;
	}

}
