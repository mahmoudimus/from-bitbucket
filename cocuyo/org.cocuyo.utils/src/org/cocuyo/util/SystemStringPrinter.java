package org.cocuyo.util;

import static java.lang.System.out;

public class SystemStringPrinter implements IStringPrinter {
	public static final SystemStringPrinter singletone = new SystemStringPrinter();

	public static final String NEWLINE = System.getProperty("line.separator");

	@Override
	public void println() {
		out.println();
	}

	@Override
	public void println(String aText) {
		out.println(aText);
	}

}
