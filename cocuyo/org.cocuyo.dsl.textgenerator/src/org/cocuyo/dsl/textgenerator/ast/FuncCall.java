package org.cocuyo.dsl.textgenerator.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.dsl.textgenerator.TextGeneratorType;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;
import org.cocuyo.parsing.RecognitionError;
import org.cocuyo.parsing.RecognitionException;

//close-imports
public class FuncCall extends Code
// open-inheritance//close-inheritance
{
	private Code code;
	private Id id;
	private FuncCallArgs funccallargs;

	// open-fields//close-fields

	public FuncCall(Code code, Id id, FuncCallArgs funccallargs)
	{
		this.code = code;
		this.id = id;
		this.funccallargs = funccallargs;
	}

	public FuncCall(Id id, FuncCallArgs funccallargs)
	{
		this.id = id;
		this.funccallargs = funccallargs;
	}

	public FuncCall(FuncCallArgs funccallargs)
	{
		this.funccallargs = funccallargs;
	}

	// open-members

	@Override
	public Object execute(ExecutionEnvironment aEnv)
	{

		ArrayList<Object> _args = funccallargs.getArgs(aEnv);

		if (code != null)
		{
			Object _obj = code.execute(aEnv);

			if (_obj instanceof TextGeneratorType)
			{
				TextGeneratorType _type = (TextGeneratorType) _obj;

				try
				{
					aEnv = new ExecutionEnvironment(aEnv);
					aEnv.setInstance(_type.getInstance());

					return _type.executeFunction(id.getText(), aEnv, _args
							.toArray());
				} catch (NameNotFoundException _e)
				{
					throw new RecognitionException(new RecognitionError(_e
							.getMessage(), id.getToken()));
				}
			}

			throw new RecognitionException(new RecognitionError(
					"Can not call the method '" + id.getText()
							+ "' of a non generator object ("
							+ _obj.getClass().getSimpleName() + ")", id
							.getToken()));

		} else
		{
			try
			{
				if (id == null)
				{
					// super
					return aEnv.getInstance().getType().executeSuperFunction(
							aEnv, _args.toArray());
				} else
				{
					return aEnv.getInstance().getType().executeFunction(
							id.getText(), aEnv, _args.toArray());
				}
			} catch (NameNotFoundException _e)
			{
				throw new RecognitionException(new RecognitionError(_e
						.getMessage(), id.getToken()));
			}
		}

	}
	// close-members
}