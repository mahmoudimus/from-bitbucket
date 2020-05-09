package org.cocuyo.dsl.lexer.ast;

//open-imports
import org.cocuyo.dsl.lexer.LexerTransition;
import org.cocuyo.parsing.IToken;

//close-imports
public class ASTTransition implements IASTRoot
//open-inheritance
//close-inheritance
{
	
	private ASTPattern pattern;
	private IToken yield;
	private IToken go;
	
	public ASTTransition(ASTPattern pattern, IToken yield, IToken go)
	{
		this.pattern = pattern;
		this.yield = yield;
		this.go = go;
	}
	public ASTTransition(ASTPattern pattern, IToken yield)
	{
		this.pattern = pattern;
		this.yield = yield;
	}
	public ASTTransition(ASTPattern pattern)
	{
		this.pattern = pattern;
	}
	
	//open-members
	public void build(LexerASTEnv aEnv)
	{
		String _from = aEnv.getDefaultFrom();
		String _go = go == null ? aEnv.getDefaultGoto() : go.getText();
		String _yield = yield == null ? null : yield.getText();

		LexerTransition _trans = new LexerTransition(pattern.build(aEnv),
				_from, _go, _yield);
		aEnv.getLexer().add(_trans);
	}
	//close-members
	
}