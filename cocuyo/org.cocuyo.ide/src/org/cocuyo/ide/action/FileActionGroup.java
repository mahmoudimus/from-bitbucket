package org.cocuyo.ide.action;

import javax.swing.JFileChooser;

public class FileActionGroup extends ActionGroup {
	public static FileActionGroup instance = new FileActionGroup();
	private JFileChooser fFileChooser;

	protected FileActionGroup() {
		super("File");
		addAction(new NewAction());
		addAction(new SaveAction());
		addAction(new OpenAction());
		addAction(new CloseAction());
		addAction(new ExitAction());
	}

	public JFileChooser getFileChooser() {
		if (fFileChooser == null) {
			fFileChooser = new JFileChooser();
		}
		return fFileChooser;
	}

}
