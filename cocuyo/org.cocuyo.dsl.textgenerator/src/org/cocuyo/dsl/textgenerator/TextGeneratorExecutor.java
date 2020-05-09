package org.cocuyo.dsl.textgenerator;

import static org.cocuyo.util.Printing.println;

import java.io.IOException;
import java.util.ArrayList;

import org.cocuyo.CocuyoException;
import org.cocuyo.dsl.LanguageClient;
import org.cocuyo.dsl.protocol.DSLEnvironment;
import org.cocuyo.dsl.protocol.IObjectPackageMember;
import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.dsl.textgenerator.code.FileCode;
import org.cocuyo.dsl.textgenerator.code.ICode;
import org.cocuyo.env.CocuyoEnvironment;
import org.cocuyo.env.ShellArguments;
import org.cocuyo.parsing.RecognitionException;

public class TextGeneratorExecutor extends LanguageClient {

	@Override
	public void run(DSLEnvironment aEnv) throws CocuyoException {

		ShellArguments _args = CocuyoEnvironment.getShellArguments();

		if (_args.getFreeValues().size() > 1
				&& _args.getFirstFreeValue().equals("gen") && loadPrograms()) {

			String _name = _args.getFreeValues().get(1);
			String[] _cualifiedName = _name.split("\\.");

			try {
				IObjectPackageMember _member = aEnv.getObjectPackage().find(
						_cualifiedName);

				if (_member instanceof TextGeneratorType) {
					TextGeneratorType _type = (TextGeneratorType) _member;
					System.out.println("Executing generator '"
							+ _type.getFullName() + "'");

					Object _obj = _type.execute(new Object[] {});

					if (_obj instanceof ICode) {
						ICode _code = (ICode) _obj;

						if (_args.containParam("show-text")) {
							println("Showing generated text:");
							println("--- start ---");
							println();
							println(_code.getText(0));
							println();
							println("---- end ---");
						}
						if (!_args.containParam("no-gen-files")) {
							ArrayList<FileCode> _units = new ArrayList<FileCode>();
							_code.addFileWriteCodeTo(_units);

							System.out.println("Generating (" + _units.size()
									+ ") files ...");

							for (FileCode _file : _units) {
								try {
									System.out.print(_file.getFileName()
											+ "...");
									_file.writeToFile();
									System.out.println("ok!");
								} catch (IOException _e) {
									throw new RuntimeException(_e);
								}
							}

						}
					} else {
						println(_obj);
						println();
					}
				} else {
					println("Expecting '"
							+ TextGeneratorType.class.getSimpleName()
							+ "' class, found '"
							+ _member.getClass().getSimpleName() + "' at '"
							+ _member.getFullName() + "'");
					println();
				}
			} catch (RecognitionException _e) {
				_e.getErrors().print();
			} catch (NameNotFoundException _e) {
				// getEnvironment().manageException(_e);
				println(_e.getMessage());
				System.exit(-1);
			}

		}

	}
}
