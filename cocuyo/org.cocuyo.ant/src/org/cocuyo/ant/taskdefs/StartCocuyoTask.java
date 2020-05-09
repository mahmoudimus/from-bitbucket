package org.cocuyo.ant.taskdefs;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.cocuyo.CocuyoException;
import org.cocuyo.Platfom;

public class StartCocuyoTask extends Task {
	@Override
	public void execute() throws BuildException {
		try {
			Platfom.start();
		} catch (CocuyoException _e) {
			throw new BuildException(_e);
		}
	}

}
