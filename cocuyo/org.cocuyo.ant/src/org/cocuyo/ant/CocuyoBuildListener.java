package org.cocuyo.ant;

import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildListener;
import org.cocuyo.extension.ExtensionManager;

public class CocuyoBuildListener implements BuildListener {

	@Override
	public void buildFinished(BuildEvent aEvent) {

	}

	@Override
	public void buildStarted(BuildEvent aEvent) {
		Iterable<IAntProjectConfigurator> _confs = ExtensionManager
				.findAllExtensionFor(IAntProjectConfigurator.class);

		for (IAntProjectConfigurator _conf : _confs) {
			_conf.configureAntProject(aEvent.getProject());
		}
	}

	@Override
	public void messageLogged(BuildEvent aEvent) {

	}

	@Override
	public void targetFinished(BuildEvent aEvent) {

	}

	@Override
	public void targetStarted(BuildEvent aEvent) {

	}

	@Override
	public void taskFinished(BuildEvent aEvent) {

	}

	@Override
	public void taskStarted(BuildEvent aEvent) {

	}

}
