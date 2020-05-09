package org.cocuyo.dsl.lexer;

import org.cocuyo.dsl.GeneralLanguageInterpreter;
import org.cocuyo.dsl.lexer.ast.ASTUnit;
import org.cocuyo.dsl.lexer.ast.LexerASTBuilder;
import org.cocuyo.dsl.lexer.syntax.LexerParser;
import org.cocuyo.extension.ExtensionException;
import org.cocuyo.extension.ExtensionsInstaller;

public class DSLLexerInstaller extends ExtensionsInstaller {

	@Override
	public void installExtensions() throws ExtensionException {
		installExtension(new GeneralLanguageInterpreter<ASTUnit>(
				new LexerParser(), new LexerASTBuilder(), "lexer"));
	}

	@Override
	public void registerExtensionPoints() {

	}

}
