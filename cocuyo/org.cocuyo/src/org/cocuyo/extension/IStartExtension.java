package org.cocuyo.extension;

import org.cocuyo.CocuyoException;

@ExtensionPoint
public interface IStartExtension
{
	public void start() throws CocuyoException;
}
