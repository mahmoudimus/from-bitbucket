package org.cocuyo.ide.editor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTabbedPane;

public class EditorPart {

	private JTabbedPane fTabPane;
	private ArrayList<IEditor> fEditors;

	public EditorPart() {
		fEditors = new ArrayList<IEditor>();
	}

	public void openFiles(File... aFiles) throws IOException {
		for (File _file : aFiles) {
			IEditor _editor = createEditorForFile(_file);
			fTabPane.addTab(_file.getName(), _editor.getComponent());
			fTabPane.setSelectedComponent(_editor.getComponent());
			fEditors.add(_editor);
		}
	}

	public void save() {
		if (hasEditors()) {
			IEditor _editor = getActiveEditor();
			_editor.save();
		}
	}

	public IEditor getActiveEditor() {
		return hasEditors() ? fEditors.get(fTabPane.getSelectedIndex()) : null;
	}

	public IEditor createEditorForFile(File aFile) throws IOException {
		return new TextEditor(aFile);
	}

	public JTabbedPane getPane() {
		if (fTabPane == null) {
			fTabPane = new JTabbedPane();
		}
		return fTabPane;
	}

	public void closeActiveEditor() {
		if (hasEditors()) {
			int _index = fTabPane.getSelectedIndex();
			fTabPane.removeTabAt(_index);
			fEditors.remove(_index);
		}
	}

	public boolean hasEditors() {
		return !fEditors.isEmpty();
	}
}
