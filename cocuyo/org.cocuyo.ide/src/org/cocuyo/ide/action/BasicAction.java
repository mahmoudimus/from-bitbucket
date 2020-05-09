package org.cocuyo.ide.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

public class BasicAction extends AbstractAction {

	public BasicAction() {
		super();
	}

	public BasicAction(String aText) {
		super(aText);
	}

	public BasicAction(String aText, Icon aIcon) {
		super(aText, aIcon);
	}

	@Override
	public void actionPerformed(ActionEvent aE) {
		System.out.println("Action " + this.getClass().getSimpleName()
				+ " performed");
	}

}
