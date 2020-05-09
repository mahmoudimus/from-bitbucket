package org.cocuyo.ide.editor;

import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

public class TextEditor extends JEditorPane implements IEditor {

	private File fFile;
	private JScrollPane fPane;

	public TextEditor(File aFile) throws IOException {
		super(aFile.toURI().toURL());
		fFile = aFile;
	}

	@Override
	public IEditorSource getSource() {
		return null;
	}

	@Override
	public Component getComponent() {
		if (fPane == null) {
			fPane = new JScrollPane(this);
		}
		return fPane;
	}

	@Override
	public void save() {
		try {
			FileOutputStream _out = new FileOutputStream(fFile);
			_out.write(getText().getBytes());
			_out.close();
			System.out.println("Save " + fFile.getName());
		} catch (IOException _e) {
			_e.printStackTrace();
		}
	}

}
