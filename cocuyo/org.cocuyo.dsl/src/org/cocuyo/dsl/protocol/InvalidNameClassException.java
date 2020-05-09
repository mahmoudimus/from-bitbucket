package org.cocuyo.dsl.protocol;

import org.cocuyo.util.Text;

public class InvalidNameClassException extends Exception {
	private String[] fNames;

	public InvalidNameClassException(Class aClass, String aMessage,
			String... aNames) {
		super(aMessage.replaceAll("#name", Text.namesToCualifiedNames(aNames)));
		fNames = aNames;
	}
}
