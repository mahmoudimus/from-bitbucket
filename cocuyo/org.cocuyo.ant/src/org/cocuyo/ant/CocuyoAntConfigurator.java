package org.cocuyo.ant;

import java.io.File;

import org.apache.tools.ant.Project;
import org.cocuyo.env.CocuyoEnvironment;

public class CocuyoAntConfigurator implements IAntProjectConfigurator {

	public static String getCocuyoAntLib(String aLibName) {
		return CocuyoEnvironment.getAppPath() + File.separator + aLibName
				+ ".xml";
	}

	@Override
	public void configureAntProject(Project aProject) {
		aProject.setProperty("cocuyo.ant", getCocuyoAntLib("cocuyo"));
		aProject.setProperty("cocuyo.path", CocuyoEnvironment.getAppPath());
	}
}
