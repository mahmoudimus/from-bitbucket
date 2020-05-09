package org.cocuyo.ide.action;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import org.cocuyo.ide.IDE;

public class ExitAction extends BasicAction {
	public ExitAction() {
		super("Exit", new ImageIcon("exit.png"));
	}

	@Override
	public void actionPerformed(ActionEvent aE) {
		IDE.exit();
	}
}
