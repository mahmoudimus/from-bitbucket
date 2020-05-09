package org.cocuyo.dsl.textgenerator.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.textgenerator.code.ListCode;
import org.cocuyo.dsl.textgenerator.code.ObjectCode;
import org.cocuyo.dsl.textgenerator.code.StringCode;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;
import org.cocuyo.parsing.RecognitionError;
import org.cocuyo.parsing.RecognitionException;

//close-imports
public class ForLoop extends Code
//open-inheritance//close-inheritance
{
	private Id varid;
	private ArrayList<Code> iterable;
	private ArrayList<Code> instr;
	private ArrayList<Code> separator;
	//open-fields//close-fields
	
	public ForLoop(Id varid, ArrayList<Code> iterable, ArrayList<Code> instr)
	{
		this.varid = varid;
		this.iterable = iterable;
		this.instr = instr;
	}
	public ForLoop(Id varid, ArrayList<Code> iterable, ArrayList<Code> separator, ArrayList<Code> instr)
	{
		this.varid = varid;
		this.iterable = iterable;
		this.separator = separator;
		this.instr = instr;
	}
	//open-members

    @Override
    public Object execute(ExecutionEnvironment aEnv)
    {
	Object _iterable = iterableValueOf(aEnv, iterable);
	_iterable = _iterable instanceof ObjectCode ? ((ObjectCode) _iterable)
		.getObject() : _iterable;
	Object _sep = (separator != null ? valueOf(aEnv, separator) : null);

	ListCode _list = new ListCode();

	ExecutionEnvironment _env = new ExecutionEnvironment(aEnv);

	if (_iterable instanceof Object[])
	{
	    ArrayList<Object> _array_list = new ArrayList<Object>();
	    Object[] _array = (Object[]) _iterable;

	    for (int _i = 0; _i < _array.length; _i++)
	    {
		_array_list.add(_array[_i]);
	    }
	    _iterable = _array_list;
	}

	if (_iterable instanceof Iterable)
	{
	    ArrayList _result = new ArrayList<Object>();

	    for (Object _item : (Iterable) _iterable)
	    {
		_env.defineLocal(varid.getText(), _item);

		Object _instrResult = valueOf(_env, instr);
		if (_instrResult != StringCode.VOID_CODE)
		{
		    _result.add(_instrResult);
		}
	    }

	    int _i = 1;
	    int _last = _result.size();

	    for (Object _item : _result)
	    {
		_list.addObject(_item);

		if (_i < _last && _sep != null)
		{
		    _list.addObject(_sep);
		}

		_i++;

	    }
	}
	else
	{
	    throw new RecognitionException(new RecognitionError(
		    "Can not iterate for an instance of '"
			    + _iterable.getClass().getSimpleName() + "'",
		    this.varid.getToken()));
	}

	return _list;
    }

    private Object iterableValueOf(ExecutionEnvironment aEnv,
	    ArrayList<Code> aCodes)
    {
	ListCode _list = new ListCode();

	for (Code _code : aCodes)
	{
	    _list.addObject(_code.execute(aEnv));
	}

	return _list.getAsResult();
    }

    private Object valueOf(ExecutionEnvironment aEnv, ArrayList<Code> aCodes)
    {
	ListCode _list = new ListCode();

	for (Code _code : aCodes)
	{
	    _list.addObject(_code.execute(aEnv));
	}

	return _list.getAsResult();
    }

    //close-members
}