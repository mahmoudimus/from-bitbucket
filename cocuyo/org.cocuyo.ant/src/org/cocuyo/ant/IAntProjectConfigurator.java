package org.cocuyo.ant;

import org.apache.tools.ant.Project;
import org.cocuyo.extension.ExtensionPoint;

@ExtensionPoint
public interface IAntProjectConfigurator {
	public void configureAntProject(Project aProject);
}
