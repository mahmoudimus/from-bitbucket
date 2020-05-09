package org.cocuyo.dsl.textgenerator;

import java.io.IOException;

import org.cocuyo.dsl.IInterpreterUnit;
import org.cocuyo.dsl.ILanguageInterpreter;
import org.cocuyo.dsl.textgenerator.ast.TextGeneratorGrammarASTBuilder;
import org.cocuyo.env.CocuyoEnvironment;
import org.cocuyo.parsing.RecognitionException;
import org.cocuyo.parsing.cup.CupParserToASTBuilderEventsMapper;
import org.cocuyo.util.InputFile;

public class TextGeneratorInterpreter implements ILanguageInterpreter
{

	@Override
	public IInterpreterUnit buildUnit(InputFile aFile)
	{
		IInterpreterUnit _unit = null;

		TextGeneratorGrammarParser _parser = new TextGeneratorGrammarParser(
				new TextGeneratorGrammarLexer());
		TextGeneratorGrammarASTBuilder _builder = new TextGeneratorGrammarASTBuilder();
		_parser.addListener(new CupParserToASTBuilderEventsMapper(_builder));

		try
		{
			_parser.parseFile(aFile.getPath());
			if (_parser.getErrors().hasErrors())
			{
				throw new RecognitionException(_parser.getErrors());
			} else
			{
				_unit = (IInterpreterUnit) _builder.getLastAST();
			}
		} catch (IOException _e)
		{
			CocuyoEnvironment.manageException(_e);
		}

		return _unit;
	}

	@Override
	public String getLanguageID()
	{
		return "gn";
	}

}
