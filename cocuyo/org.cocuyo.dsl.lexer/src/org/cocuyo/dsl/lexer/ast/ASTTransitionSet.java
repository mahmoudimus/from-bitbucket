package org.cocuyo.dsl.lexer.ast;

//open-imports
import org.cocuyo.parsing.IToken;

//close-imports
public class ASTTransitionSet implements IASTRoot
//open-inheritance
//close-inheritance
{
	
	private IToken from;
	private IToken yield;
	private IToken go;
	private ASTTransitionList transitionlist;
	
	public ASTTransitionSet(IToken from, IToken yield, IToken go, ASTTransitionList transitionlist)
	{
		this.from = from;
		this.yield = yield;
		this.go = go;
		this.transitionlist = transitionlist;
	}
	public ASTTransitionSet(IToken from, IToken yield, ASTTransitionList transitionlist)
	{
		this.from = from;
		this.yield = yield;
		this.transitionlist = transitionlist;
	}
	public ASTTransitionSet(IToken from, ASTTransitionList transitionlist)
	{
		this.from = from;
		this.transitionlist = transitionlist;
	}
	
	//open-members

	public void build(LexerASTEnv aEnv)
	{
		if (go != null)
			aEnv.setDefaultGoto(go.getText());
		if (yield != null)
			aEnv.setDefaultYield(yield.getText());
		aEnv.setDefaultFrom(from.getText());

		for (ASTTransition _trans : transitionlist)
		{
			_trans.build(aEnv);
		}

	}
	//close-members
	
}