package org.cocuyo.dsl._native;

import java.io.IOException;

import org.cocuyo.dsl.IInterpreterUnit;
import org.cocuyo.dsl.ILanguageInterpreter;
import org.cocuyo.dsl._native.ast.ASTCompileUnit;
import org.cocuyo.dsl._native.ast.NativeASTBuilder;
import org.cocuyo.dsl._native.syntax.NativeParser;
import org.cocuyo.parsing.RecognitionException;
import org.cocuyo.parsing.cup.GeneralCupParser;
import org.cocuyo.util.InputFile;

public class NativeInterpreter implements ILanguageInterpreter
{

	@Override
	public IInterpreterUnit buildUnit(InputFile aFile)
	{
		GeneralCupParser<ASTCompileUnit> _parser = new GeneralCupParser<ASTCompileUnit>(
				new NativeParser(), new NativeASTBuilder());

		try
		{
			_parser.parseFile(aFile.getPath());
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		if (_parser.getErrors().hasErrors())
			throw new RecognitionException(_parser.getErrors());

		IInterpreterUnit _unit = _parser.getAST();
		return _unit;
	}

	@Override
	public String getLanguageID()
	{
		return "n";
	}

}
