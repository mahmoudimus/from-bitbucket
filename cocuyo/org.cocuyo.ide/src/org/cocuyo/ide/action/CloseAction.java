package org.cocuyo.ide.action;

import java.awt.event.ActionEvent;

import org.cocuyo.ide.IDE;

public class CloseAction extends BasicAction {
	public CloseAction() {
		super("Close");
	}

	@Override
	public void actionPerformed(ActionEvent aEvent) {
		IDE.getEditorPart().closeActiveEditor();
	}

}
