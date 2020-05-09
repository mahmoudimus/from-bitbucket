package org.cocuyo.ide.editor;

import java.awt.Component;

public interface IEditor {
	public IEditorSource getSource();

	public Component getComponent();

	public void save();

}
