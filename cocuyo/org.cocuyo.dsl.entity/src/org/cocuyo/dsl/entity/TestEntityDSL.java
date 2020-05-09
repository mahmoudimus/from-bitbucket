package org.cocuyo.dsl.entity;

import java.io.IOException;

import org.cocuyo.CocuyoException;
import org.cocuyo.dsl.entity.ast.ASTEntityCompileUnit;
import org.cocuyo.dsl.entity.ast.EntityASTBuilder;
import org.cocuyo.dsl.entity.syntax.EntityParser;
import org.cocuyo.parsing.cup.GeneralCupParser;

public class TestEntityDSL {
	public static void main(String[] aArgs) throws IOException, CocuyoException {
		System.out.println("Testing...");
		GeneralCupParser<ASTEntityCompileUnit> _parser = new GeneralCupParser<ASTEntityCompileUnit>(
				new EntityParser(), new EntityASTBuilder());

		_parser.parseFile("Library.entity");
		if (_parser.getErrors().hasErrors()) {
			_parser.getErrors().print();
		} else {
			System.out.println(_parser.getAST());
		}
	}
}
