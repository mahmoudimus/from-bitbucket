package org.cocuyo.dsl.entity;

import org.cocuyo.dsl.GeneralLanguageInterpreter;
import org.cocuyo.dsl.entity.ast.ASTEntityCompileUnit;
import org.cocuyo.dsl.entity.ast.EntityASTBuilder;
import org.cocuyo.dsl.entity.syntax.EntityParser;
import org.cocuyo.extension.ExtensionException;
import org.cocuyo.extension.ExtensionsInstaller;

public class DSLEntityInstaller extends ExtensionsInstaller {

	@Override
	public void installExtensions() throws ExtensionException {
		installExtension(new GeneralLanguageInterpreter<ASTEntityCompileUnit>(
				new EntityParser(), new EntityASTBuilder(), "entity"));
	}

	@Override
	public void registerExtensionPoints() {

	}

}
