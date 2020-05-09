package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.dsl.textgenerator.TextGeneratorFuncArg;

//close-imports
public class FormalArg
// open-inheritance//close-inheritance
{
	private Id id;
	private Code code;

	// open-fields//close-fields

	public FormalArg(Id id)
	{
		this.id = id;
	}

	public FormalArg(Id id, Code code)
	{
		this.id = id;
		this.code = code;
	}

	// open-members
	public TextGeneratorFuncArg getFuncArg()
	{
		TextGeneratorFuncArg _arg = null;
		if (code == null)
			_arg = new TextGeneratorFuncArg(id.getText());
		else
			_arg = new TextGeneratorFuncArg(id.getText(), code);
		return _arg;

	}
	// close-members
}