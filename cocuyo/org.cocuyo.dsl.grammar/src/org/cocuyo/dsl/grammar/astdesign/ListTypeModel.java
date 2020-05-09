package org.cocuyo.dsl.grammar.astdesign;


public class ListTypeModel extends TrivialTypeModel
{
	private ChildNodeModel fRecChild;
	private ChildNodeModel fElemChild;
	private TrivialTypeModel fTrivialType;
	private boolean fIsLeftRec;
	private boolean fCanBeEmpty;
	private boolean fHasOneElemAlt;

	public ListTypeModel(TrivialTypeModel aTrivialType)
			throws CanNotCreateListTypeException
	{
		super(aTrivialType.getRule(), aTrivialType.getHierarchy());
		fTrivialType = aTrivialType;

		setSupers(fTrivialType.getSupers());
		setInitMethodsOf(aTrivialType);
		setChildNodesOf(aTrivialType);
		buildAsList();
	}

	public boolean isList()
	{
		return true;
	}

	protected void buildAsList() throws CanNotCreateListTypeException
	{
		fRecChild = null;
		fElemChild = null;
		fIsLeftRec = false;
		fCanBeEmpty = false;
		fHasOneElemAlt = false;

		ChildNodeModel _left = null, _right = null;
		boolean _checkOne = false;
		boolean _checkTwo = false;

		if (getCountChildNodes() == 2)
		{
			_left = getChildNode(0);
			_right = getChildNode(1);

			if (_left.getTypeName().equals(getName()))
			{
				fRecChild = _left;
				fIsLeftRec = true;
				fElemChild = _right;
			} else
			{
				if (_right.getTypeName().equals(getName()))
				{
					fElemChild = _left;
					fRecChild = _right;
				} else
					throw new CanNotCreateListTypeException();
			}

			if (fElemChild.getTypeName().equals(getName()))
				throw new CanNotCreateListTypeException();
		} else
		{
			throw new CanNotCreateListTypeException();
		}

		for (InitMethodModel _initMethod : fTrivialType.getInitMethods())
		{
			if (_initMethod.getCountArgs() == 2)
			{
				if (_checkTwo)
					throw new CanNotCreateListTypeException();
				_checkTwo = true;

				_left = _initMethod.getArg(0);
				_right = _initMethod.getArg(1);

				if (!(_left.getTypeName().equals(getName())
						&& _right.getTypeName()
								.equals(fElemChild.getTypeName()) || _right
						.getTypeName().equals(getName())
						&& _left.getTypeName().equals(fElemChild.getTypeName())
						&& _left.getName().equals(fElemChild.getName())))
				{
					throw new CanNotCreateListTypeException();
				}
			} else if (_initMethod.getCountArgs() == 1)
			{
				if (_checkOne)
					throw new CanNotCreateListTypeException();
				_checkOne = true;
				fHasOneElemAlt = true;

				_left = _initMethod.getArg(0);

				if (!(_left.getTypeName().equals(fElemChild.getTypeName()) && _left
						.getName().equals(fElemChild.getName())))
					throw new CanNotCreateListTypeException();

			} else if (_initMethod.getCountArgs() == 0)
			{
				if (fCanBeEmpty)
					throw new CanNotCreateListTypeException();
				fCanBeEmpty = true;
			} else
			{
				throw new CanNotCreateListTypeException();
			}

		}
	}

	@Override
	protected void build()
	{

	}

	public ChildNodeModel getRecChild()
	{
		return fRecChild;
	}

	public ChildNodeModel getElemChild()
	{
		return fElemChild;
	}

	public boolean getCanBeEmpty()
	{
		return fCanBeEmpty;
	}

	@Override
	public String toString()
	{
		String _str = "\trec " + fRecChild + "\n\telem " + fElemChild + "\nof ";
		return "list \n" + _str + super.toString();
	}

	public boolean isLeftRec()
	{
		return fIsLeftRec;
	}

	public boolean hasOneElemAlt()
	{
		return fHasOneElemAlt;
	}

}
