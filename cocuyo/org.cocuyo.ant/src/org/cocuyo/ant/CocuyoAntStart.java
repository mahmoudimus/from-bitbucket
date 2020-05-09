package org.cocuyo.ant;

import org.apache.tools.ant.Main;
import org.cocuyo.CocuyoException;
import org.cocuyo.env.CocuyoEnvironment;
import org.cocuyo.extension.IStartExtension;

public class CocuyoAntStart implements IStartExtension {

	@Override
	public void start() throws CocuyoException {
		if (CocuyoEnvironment.getShellArguments().getFirstFreeValue().equals(
				"ant")) {
			Main.main(new String[] { "-listener",
					CocuyoBuildListener.class.getName() });
		}
	}
}
