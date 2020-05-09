package org.cocuyo.util;

public class Text {
	public final static String NEW_LINE = "\n";

	public static String namesToCualifiedNames(String... aNames) {
		String _result = "";
		for (int _i = 0; _i < aNames.length; _i++) {
			_result += aNames[_i];
			if (_i < aNames.length - 1) {
				_result += ".";
			}
		}
		return _result;
	}
}
