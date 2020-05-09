package org.cocuyo.dsl;

import static org.cocuyo.util.Printing.println;

import java.io.File;
import java.util.ArrayList;

import org.cocuyo.CocuyoException;
import org.cocuyo.dsl.protocol.DSLEnvironment;
import org.cocuyo.env.CocuyoEnvironment;
import org.cocuyo.extension.ExtensionManager;
import org.cocuyo.extension.IStartExtension;
import org.cocuyo.parsing.RecognitionErrorCollection;
import org.cocuyo.parsing.RecognitionException;
import org.cocuyo.util.InputFile;

public class DSLInterpreterManager implements IStartExtension {
	private ArrayList<IInterpreterUnit> fUnits;
	private Iterable<ILanguageInterpreter> fExtensions;
	private static DSLInterpreterManager fSingleton = new DSLInterpreterManager();
	private boolean fIsLoaded = false;
	private boolean fNoHasErrors = true;
	private DSLEnvironment fDSLEnvironment;

	public static String getDSLsLibPath() {
		return CocuyoEnvironment.getAppPath() + File.separator + "dsl";
	}

	public static DSLInterpreterManager singleton() {
		return fSingleton;
	}

	public DSLInterpreterManager() {
		setDSLEnvironment(new DSLEnvironment());
	}

	@Override
	public void start() throws CocuyoException {
		loadPackageClientsPlugins();
	}

	public boolean load(Iterable<String> aInput) {

		if (!fIsLoaded) {
			fIsLoaded = true;

			if (ExtensionManager
					.containExtensionPoint(ILanguageInterpreter.class)) {
				fExtensions = ExtensionManager
						.findAllExtensionFor(ILanguageInterpreter.class);

				fUnits = new ArrayList<IInterpreterUnit>();

				RecognitionErrorCollection _errors = new RecognitionErrorCollection();

				loadDSLsLib(_errors);

				for (String _inputPath : aInput) {
					fNoHasErrors = fNoHasErrors && analize(_errors, _inputPath);
				}

				if (_errors.hasErrors())
					throw new RecognitionException(_errors);

				if (fNoHasErrors)
					rebuild(build(fUnits));

			}
		}
		return fNoHasErrors;
	}

	public boolean loadDSLsLib(RecognitionErrorCollection aErrors) {
		File _scripts = new File(getDSLsLibPath());

		if (_scripts.exists()) {
			long _initTime = System.currentTimeMillis();

			System.out.print("Loading DSLs library...");
			analize(aErrors, getDSLsLibPath());

			if (aErrors.hasErrors()) {
				println();
				println("Exist some errors in library");
				return false;
			} else {
				long _endTime = System.currentTimeMillis();
				println("ok (" + (double) (_endTime - _initTime) / 1000
						+ " sec)");

				println();
			}
		} else {
			System.out.print("DSL folder was created at '"
					+ _scripts.getAbsolutePath() + "'");
			_scripts.mkdirs();
		}
		return true;
	}

	protected void loadPackageClientsPlugins() throws CocuyoException {
		if (ExtensionManager.containExtensionPoint(ILanguageInterpreter.class)) {
			for (ILanguageClient _langClient : ExtensionManager
					.findAllExtensionFor(ILanguageClient.class)) {
				_langClient.run(getDSLEnvironment());
			}
		}
	}

	protected Iterable<IInterpreterUnit> build(Iterable<IInterpreterUnit> aUnits) {
		ArrayList<IInterpreterUnit> _units = new ArrayList<IInterpreterUnit>();

		for (IInterpreterUnit _unit : aUnits) {
			_unit.build(getDSLEnvironment());

			if (!_unit.isBuilt())
				_units.add(_unit);
		}
		return _units;
	}

	protected void rebuild(Iterable<IInterpreterUnit> aUnits) {
		ArrayList<IInterpreterUnit> _units = new ArrayList<IInterpreterUnit>();

		for (IInterpreterUnit _unit : aUnits) {
			_unit.nextBuildPass();
			if (!_unit.isBuilt())
				_units.add(_unit);
		}

		if (_units.size() > 0)
			rebuild(_units);
	}

	protected boolean analize(RecognitionErrorCollection aErrors,
			String aInputPath) {

		if (InputFile.exist(aInputPath)) {
			File _inputFile = new InputFile(aInputPath);

			for (InputFile _file : InputFile.getAllFilesOf(aInputPath)) {
				try {
					String _lang = _file.getExtension();

					for (ILanguageInterpreter _interpreter : fExtensions) {
						if (_interpreter.getLanguageID().equalsIgnoreCase(
								_file.getExtension())) {
							IInterpreterUnit _unit = _interpreter
									.buildUnit(_file);

							if (_unit != null) {
								fUnits.add(_unit);
							}
						}
					}
				} catch (RecognitionException _ex) {
					aErrors.addErrors(_ex.getErrors());
				}
			}
		} else {
			println("Invalid path '" + aInputPath + "'");
			return false;
		}
		return !aErrors.hasErrors();
	}

	public void setDSLEnvironment(DSLEnvironment dSLEnvironment) {
		fDSLEnvironment = dSLEnvironment;
	}

	public DSLEnvironment getDSLEnvironment() {
		return fDSLEnvironment;
	}

}
