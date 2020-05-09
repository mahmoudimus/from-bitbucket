package org.cocuyo.ide.action;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import org.cocuyo.ide.IDE;

public class SaveAction extends BasicAction {
	public final static SaveAction instance = new SaveAction();

	protected SaveAction() {
		super("Save", new ImageIcon("save.png"));
	}

	@Override
	public void actionPerformed(ActionEvent aE) {
		IDE.getEditorPart().save();
	}
}
