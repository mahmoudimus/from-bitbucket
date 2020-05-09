package org.cocuyo.tool.gold;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.cocuyo.env.CocuyoEnvironment;

public class GoldBuilderTool {

	public static final String GOLD_BUILDER_PROGRAM_PATH = CocuyoEnvironment
			.getAppPath()
			+ File.separator
			+ "tools"
			+ File.separator
			+ "gold-parser-builder-cmd"
			+ File.separator
			+ "goldbuilder_main.exe";

	public static void processGoldFile(String aPath) throws IOException,
			InterruptedException {
		System.out.println();

		Runtime _runtime = Runtime.getRuntime();

		String _cmd = GOLD_BUILDER_PROGRAM_PATH + " " + aPath + ".grm";

		System.out.println("Executing " + _cmd);

		Process _process = _runtime.exec(_cmd);

		BufferedReader _input = new BufferedReader(new InputStreamReader(
				_process.getInputStream()));

		String _line = null;

		while ((_line = _input.readLine()) != null) {
			System.out.println(_line);
		}

		BufferedReader _errorBuffer = new BufferedReader(new InputStreamReader(
				_process.getErrorStream()));

		while ((_line = _errorBuffer.readLine()) != null) {
			System.err.println(_line);
		}

		System.out.println(_process.waitFor() == 0 ? "Execution succeed"
				: "Execution don't succeed");

		try {
			FileInputStream _logInput = new FileInputStream(aPath + ".log");

			String _log = "";
			String _errors = "";
			String _warinings = "";

			BufferedReader _reader = new BufferedReader(new InputStreamReader(
					_logInput));
			while ((_line = _reader.readLine()) != null) {
				_log += _line + "\n";

				if (_line.contains("Error"))
					_errors += _line + "\n";
				if (_line.contains("Warning")) {
					_warinings += _line + "\n";
				}
			}

			System.out.println("Gold Log file:");
			System.out.println(_log);

			if (_warinings.length() > 0) {
				System.out.println();
				System.out.println("Warnings:");
				System.out.println();
				System.out.println(_warinings);
			}

			if (_errors.length() > 0) {
				System.out.println();
				System.out.println("Errors:");
				System.out.println();
				System.out.println(_errors);
			} else
				System.out.println("No Gold Errors");
		} catch (Exception _e) {
			System.out.println(_e.getMessage());
		}

	}
}
