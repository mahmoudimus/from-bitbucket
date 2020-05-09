package org.cocuyo.util;

import org.apache.tools.ant.Task;

public class AntLogPrinter implements IStringPrinter {

	private Task fTask;

	public AntLogPrinter(Task aTask) {
		super();
		fTask = aTask;
	}

	@Override
	public void println() {
		getTask().log("");
	}

	@Override
	public void println(String aText) {
		getTask().log(aText);
	}

	public void setTask(Task aTask) {
		fTask = aTask;
	}

	public Task getTask() {
		return fTask;
	}

}
