package org.cocuyo.ide.action;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import org.cocuyo.ide.IDE;

public class OpenAction extends BasicAction {

	private static final long serialVersionUID = 2509777073558610201L;

	public OpenAction() {
		super("Open File...", new ImageIcon("open.png"));
	}

	@Override
	public void actionPerformed(ActionEvent aEvent) {
		super.actionPerformed(aEvent);
		JFileChooser _chooser = FileActionGroup.instance.getFileChooser();
		_chooser.setDialogType(JFileChooser.OPEN_DIALOG);

		int _result = _chooser.showOpenDialog(null);
		if (_result == 0) {
			File[] _files = new File[] { _chooser.getSelectedFile() };
			try {
				IDE.getEditorPart().openFiles(_files);
			} catch (IOException _e) {
				_e.printStackTrace();
			}
		}
	}
}
