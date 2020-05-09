package org.cocuyo.tool.gold.taskdefs;

import java.io.File;

import org.cocuyo.dsl.grammar.ebnf.EBNFGrammar;
import org.cocuyo.dsl.protocol.IObjectPackageMember;
import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.dsl.textgenerator.TextGeneratorType;
import org.cocuyo.dsl.textgenerator.taskdefs.TextGeneratorBasedTask;
import org.cocuyo.tool.gold.GoldBuilderTool;

public class CocuyoGoldTask extends TextGeneratorBasedTask {

	private String fGenerator = "dsl.gn.gold.GoldToolGn";
	private String fAstPackage;
	private String fSyntaxPackage;

	public CocuyoGoldTask() {
		setAstPackage("Ast");
		setSyntaxPackage("Syntax");
	}

	@Override
	public void processObject(IObjectPackageMember aObject) {
		if (aObject instanceof EBNFGrammar) {
			processGrammar((EBNFGrammar) aObject);
		}
	}

	public void processGrammar(EBNFGrammar aObject) {
		try {
			TextGeneratorType _gen = (TextGeneratorType) getPackage()
					.findByCualifiedName(getGenerator());

			aObject.setAstPackageName(getAstPackage());
			aObject.setBasePath(getAstPackage());
			aObject.setParserPackageName(getSyntaxPackage());
			aObject.setParserPath(getSyntaxPackage());

			executeGenerator(_gen, "grammar", aObject, this);

			GoldBuilderTool.processGoldFile(getBaseDir() + File.separator
					+ aObject.getParserPath() + File.separator
					+ aObject.getName() + "_Gold");

		} catch (NameNotFoundException _e) {
			log(_e.getMessage());
		} catch (Exception _e) {
			log(_e.getMessage());
		}

	}

	public void setGenerator(String aGenerator) {
		fGenerator = aGenerator;
	}

	public String getGenerator() {
		return fGenerator;
	}

	public void setAstPackage(String astPackage) {
		fAstPackage = astPackage;
	}

	public String getAstPackage() {
		return fAstPackage;
	}

	public void setSyntaxPackage(String syntaxPackage) {
		fSyntaxPackage = syntaxPackage;
	}

	public String getSyntaxPackage() {
		return fSyntaxPackage;
	}
}
