package org.cocuyo.ide.action;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

public class ActionGroup {
	private ArrayList<BasicAction> fActions;
	private String fName;

	public ActionGroup(String aName) {
		fActions = new ArrayList<BasicAction>();
		fName = aName;
	}

	public JToolBar fillToolBar(JToolBar aToolBar) {
		for (BasicAction _action : getActions()) {
			aToolBar.add(_action);
			aToolBar.addSeparator(new Dimension(5, 0));
		}

		return aToolBar;
	}

	public JToolBar getToolBar() {
		return fillToolBar(new JToolBar(getName()));
	}

	public JMenu fillMenu(JMenu aMenu) {
		for (BasicAction _action : getActions()) {
			JMenuItem _item = new JMenuItem(_action);
			aMenu.add(_item);
		}
		return aMenu;
	}

	public JMenu getMenu() {
		return fillMenu(new JMenu(getName()));
	}

	public Iterable<BasicAction> getActions() {
		return fActions;
	}

	public String getName() {
		return fName;
	}

	public void setName(String aName) {
		fName = aName;
	}

	public void addAction(BasicAction aAction) {
		fActions.add(aAction);
	}
}
