package org.cocuyo.tool.gold;

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
import org.cocuyo.env.CocuyoEnvironment;
import org.cocuyo.env.ShellArguments;

public class CocuyoGoldCmdTool extends LanguageClient {

	@Override
	public void run(DSLEnvironment aEnv) throws CocuyoException {
		try {
			ShellArguments _args = CocuyoEnvironment.getShellArguments();

			if (!_args.getFreeValues().isEmpty()
					&& _args.getFirstFreeValue().equals("gold")
					&& loadPrograms()) {

				if (_args.getFreeValues().size() > 1) {

					ObjectPackage _pkg = aEnv.getObjectPackage();
					String _grName = _args.getFreeValues().get(1);

					IObjectPackageMember _obj = _pkg
							.findByCualifiedName(_grName);
					if (_obj instanceof EBNFGrammar) {
						EBNFGrammar _gr = (EBNFGrammar) _obj;
						TextGeneratorType _gn = (TextGeneratorType) _pkg
								.findByCualifiedName("dsl.gn.gold.GoldToolGn");

						ObjectCode _code = new ObjectCode(_gn.executeFunction(
								"grammar", _gr));

						ArrayList<FileCode> _units = new ArrayList<FileCode>();
						_code.addFileWriteCodeTo(_units);

						for (FileCode _fileCode : _units) {
							System.out.println((_fileCode.exists() ? "Rewrite"
									: "Add")
									+ " file\t" + _fileCode.getFileName());
							_fileCode.writeToFile();
						}

						GoldBuilderTool.processGoldFile(_gr.getParserPath()
								+ File.separator + _gr.getName() + "_Gold");

					} else {
						System.out.println("The object '" + _obj.getName()
								+ "' is not a grammar, is a '"
								+ _obj.getClass().getName() + "'");
					}
				}

			}
		} catch (NameNotFoundException _ex) {
			System.out.println(_ex);
		} catch (Exception _ex) {
			throw new CocuyoException("Unexpected exception", _ex);
		}
	}

}
