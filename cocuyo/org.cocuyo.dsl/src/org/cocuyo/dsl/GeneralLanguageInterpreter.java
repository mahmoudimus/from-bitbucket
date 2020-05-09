package org.cocuyo.dsl;

import java.io.IOException;

import org.cocuyo.parsing.ASTBuilder;
import org.cocuyo.parsing.IParser;
import org.cocuyo.parsing.RecognitionException;
import org.cocuyo.parsing.cup.GeneralCupParser;
import org.cocuyo.util.InputFile;

public class GeneralLanguageInterpreter<TCompileUnit extends IInterpreterUnit>
		implements ILanguageInterpreter {

	private IParser fParser;
	private ASTBuilder fBuilder;
	private String fLanguageID;

	public GeneralLanguageInterpreter(IParser aParser, ASTBuilder aBuilder,
			String aLanguageID) {
		fParser = aParser;
		fBuilder = aBuilder;
		fLanguageID = aLanguageID;
	}

	@Override
	public IInterpreterUnit buildUnit(InputFile aFile) {
		GeneralCupParser<TCompileUnit> _parser = new GeneralCupParser<TCompileUnit>(
				fParser, fBuilder);
		try {
			_parser.parseFile(aFile.getPath());
		} catch (IOException _e) {
			_e.printStackTrace();
		}

		if (_parser.getErrors().hasErrors()) {
			throw new RecognitionException(_parser.getErrors());
		}

		return (IInterpreterUnit) _parser.getAST();
	}

	@Override
	public String getLanguageID() {
		return fLanguageID;
	}

}
