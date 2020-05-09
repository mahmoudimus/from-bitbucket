package tiger;

import java.io.IOException;

import org.cocuyo.parsing.cup.GeneralCupParser;

import tiger.ast.ASTExpr;
import tiger.ast.TigerASTBuilder;
import tiger.syntax.TigerParser;

public class TigerCompiler {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		GeneralCupParser<ASTExpr> _parser = new GeneralCupParser<ASTExpr>(
				new TigerParser(), new TigerASTBuilder());
		_parser.parseFile("input.txt");
		_parser.getErrors().print();

	}

}
