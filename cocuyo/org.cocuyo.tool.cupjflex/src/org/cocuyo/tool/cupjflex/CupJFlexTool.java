package org.cocuyo.tool.cupjflex;

import static org.cocuyo.util.Printing.println;

import java.io.File;
import java.util.ArrayList;

import org.cocuyo.CocuyoException;
import org.cocuyo.dsl.LanguageClient;
import org.cocuyo.dsl.grammar.ebnf.EBNFGrammar;
import org.cocuyo.dsl.protocol.DSLEnvironment;
import org.cocuyo.dsl.protocol.IObjectPackageMember;
import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.dsl.protocol.ObjectPackage;
import org.cocuyo.dsl.textgenerator.TextGeneratorType;
import org.cocuyo.dsl.textgenerator.code.FileCode;
import org.cocuyo.dsl.textgenerator.code.ObjectCode;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;
import org.cocuyo.env.CocuyoEnvironment;
import org.cocuyo.env.ShellArguments;
import org.cocuyo.parsing.RecognitionException;

public class CupJFlexTool extends LanguageClient {

	public void processCupFile(String aDestDir, String aCupFilePath,
			String aGrammarName, int aWarningsExpected) throws Exception {

		println();
		println("Processing CUP file");
		println();

		String[] _args = new String[] { "-parser", aGrammarName + "CupParser",
				"-expect", aWarningsExpected + "", "-symbols",
				aGrammarName + "Symbol", "-destdir", aDestDir, aCupFilePath };

		java_cup.Main.main(_args);
	}

	public void processJFlexFile(String aJFlexFilePath) {
		println();
		println("Processing JFlex file");
		println();

		JFlex.Main.main(new String[] { "-nobak", aJFlexFilePath });
	}

	@Override
	public void run(DSLEnvironment aEnv) throws CocuyoException {

		ShellArguments _args = CocuyoEnvironment.getShellArguments();

		if (_args.getFreeValues().size() > 1
				&& _args.getFirstFreeValue().equals("cup") && loadPrograms()) {
			try {
				String _grammarName = _args.getFreeValues().get(1);
				ObjectPackage _pkg = aEnv.getObjectPackage();
				IObjectPackageMember _member = null;

				try {
					_member = _pkg.findByCualifiedName(_grammarName);
				} catch (NameNotFoundException _e) {
					println("Grammar '" + _grammarName + "' not found.");
					System.exit(-1);
				}

				try {
					EBNFGrammar _grammar = (EBNFGrammar) _member;
					try {
						TextGeneratorType _astGn = (TextGeneratorType) (_member = _pkg
								.findByCualifiedName("dsl.gn.cup.CupJFlexToolGn"));

						ExecutionEnvironment _env = new ExecutionEnvironment(
								_astGn, _astGn.getInstance());

						if (_args.containParam("aspects")) {

							for (String _name : _args.getParamValue("aspects")) {
								TextGeneratorType _gn = (TextGeneratorType) _pkg
										.findByCualifiedName(_name);

								_env.registerAspectsOf(_gn);
							}

						}

						boolean _no_jflex = !_args.containParam("no-jflex");
						ObjectCode _result = new ObjectCode(_astGn
								.executeFunction("grammar", _env, _grammar,
										_no_jflex));

						ArrayList<FileCode> aUnits = new ArrayList<FileCode>();
						_result.addFileWriteCodeTo(aUnits);

						for (FileCode _fileCode : aUnits) {
							println((_fileCode.exists() ? "Rewrite" : "Add")
									+ " file\t " + _fileCode.getFileName());
							_fileCode.writeToFile();
						}

						processJFlexFile(_grammar.getParserPath()
								+ File.separator + _grammarName
								+ "_JFlex.jflex");

						int _warningsExpected = _args.containParam("expect") ? Integer
								.parseInt(_args.getFirstParamValue("expect"))
								: 0;

						processCupFile(_grammar.getParserPath(), _grammar
								.getParserPath()
								+ File.separator + _grammarName + "_Cup.cup",
								_grammarName, _warningsExpected);
					} catch (ClassCastException _ex) {
						println("Invalid type of '" + _member.getFullName()
								+ "'.");
					}
				} catch (NameNotFoundException _ex) {
					println(_ex.getMessage());
				}

			} catch (RecognitionException _ex) {
				_ex.getErrors().print();
				System.exit(-1);
			}

			catch (Exception _ex) {
				_ex.printStackTrace();
				System.exit(-1);
			}
			println();
		}

	}
}
