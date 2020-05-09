package org.cocuyo.dsl.textgenerator.taskdefs;

import org.cocuyo.dsl.protocol.IObjectPackageMember;
import org.cocuyo.dsl.taskdefs.DslTask;
import org.cocuyo.dsl.textgenerator.TextGeneratorType;
import org.cocuyo.dsl.textgenerator.code.CodeManager;
import org.cocuyo.util.AntLogPrinter;
import org.cocuyo.util.IStringPrinter;

public class GenTask extends TextGeneratorBasedTask {

	@Override
	public void processObject(IObjectPackageMember aObject) {

		if (aObject instanceof TextGeneratorType) {
			processGenerator((TextGeneratorType) aObject);
		} else {
			log("ERROR: The object '" + getObject()
					+ "' is not a text generator.");
		}
	}

	public void processGenerator(TextGeneratorType aGenerator) {
		executeGenerator(aGenerator, "main");
	}

}
