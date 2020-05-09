package org.cocuyo.dsl.grammar;

import java.io.IOException;

import org.cocuyo.dsl.IInterpreterUnit;
import org.cocuyo.dsl.ILanguageInterpreter;
import org.cocuyo.dsl.grammar.ast.CocuyoGrammarASTBuilder;
import org.cocuyo.env.CocuyoEnvironment;
import org.cocuyo.parsing.RecognitionException;
import org.cocuyo.parsing.cup.CupParserToASTBuilderEventsMapper;
import org.cocuyo.util.InputFile;

public class CocuyoGrammarInterpreter implements ILanguageInterpreter {

	@Override
	public IInterpreterUnit buildUnit(InputFile aFile) {
		IInterpreterUnit _result = null;
		CocuyoGrammarParser _parser = new CocuyoGrammarParser(
				new CocuyoGrammarLexer());
		try {

			CupParserToASTBuilderEventsMapper _mapper = new CupParserToASTBuilderEventsMapper(
					new CocuyoGrammarASTBuilder());
			_parser.addListener(_mapper);
			_parser.parseFile(aFile.getPath());

			if (_parser.getErrors().hasErrors()) {
				throw new RecognitionException(_parser.getErrors());
			} else {
				_result = (IInterpreterUnit) _mapper.getAST();
			}

		} catch (IOException _e) {
			CocuyoEnvironment.manageException(_e);
		}
		return _result;
	}

	@Override
	public String getLanguageID() {
		return "gr";
	}

}
