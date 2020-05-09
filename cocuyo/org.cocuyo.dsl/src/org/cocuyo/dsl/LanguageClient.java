package org.cocuyo.dsl;

import java.util.ArrayList;

import org.cocuyo.CocuyoException;
import org.cocuyo.env.CocuyoEnvironment;
import org.cocuyo.env.ShellArguments;
import org.cocuyo.parsing.RecognitionErrorCollection;
import org.cocuyo.parsing.RecognitionException;

public abstract class LanguageClient implements ILanguageClient {
	public boolean loadPrograms() throws CocuyoException {

		ShellArguments _args = CocuyoEnvironment.getShellArguments();

		DSLInterpreterManager _loader = DSLInterpreterManager
				.singleton();

		ArrayList<String> _input;

		RecognitionErrorCollection _errors = new RecognitionErrorCollection();
		_input = _args.getParamValue("in");

		if (_input == null) {
			_input = new ArrayList<String>();
			_input.add(".");
		}
		try {
			return _loader.load(_input);
		} catch (RecognitionException _ex) {
			_errors.addErrors(_ex.getErrors());
		}

		if (_errors.hasErrors()) {
			_errors.print();
		}

		return !_errors.hasErrors();

	}
}
