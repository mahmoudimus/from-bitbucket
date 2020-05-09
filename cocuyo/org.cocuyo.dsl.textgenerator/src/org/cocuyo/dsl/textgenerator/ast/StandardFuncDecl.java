package org.cocuyo.dsl.textgenerator.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.textgenerator.TextGeneratorFuncArg;
import org.cocuyo.dsl.textgenerator.TextGeneratorStandardFunc;
import org.cocuyo.dsl.textgenerator.TextGeneratorType;

//close-imports
public class StandardFuncDecl extends GeneratorMember
// open-inheritance//close-inheritance
{
	private Id id;
	private ArrayList<FormalArg> args;
	private ArrayList<Code> code;

	// open-fields//close-fields

	public StandardFuncDecl(Id id, ArrayList<FormalArg> args,
			ArrayList<Code> code)
	{
		this.id = id;
		this.args = args;
		this.code = code;
	}

	// open-members

	@Override
	public void buildTypeLevel(TextGeneratorType aType)
	{
		TextGeneratorStandardFunc _func = new TextGeneratorStandardFunc(id
				.getText(), code);

		aType.defineFunction(_func);

		ArrayList<TextGeneratorFuncArg> _funcArgs = new ArrayList<TextGeneratorFuncArg>();

		for (FormalArg _arg : args)
		{
			_funcArgs.add(_arg.getFuncArg());
		}

		_func.setArgs(_funcArgs);
	}

	@Override
	public void buildInstrLevel()
	{

	}

	// close-members
}