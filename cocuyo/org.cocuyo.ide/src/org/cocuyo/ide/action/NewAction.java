package org.cocuyo.ide.action;

import javax.swing.ImageIcon;

public class NewAction extends BasicAction {
	public final static NewAction instance = new NewAction();

	protected NewAction() {
		super("New", new ImageIcon("new.png"));
	}
}
