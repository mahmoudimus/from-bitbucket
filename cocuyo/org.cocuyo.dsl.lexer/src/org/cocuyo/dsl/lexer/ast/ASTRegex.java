package org.cocuyo.dsl.lexer.ast;

//open-imports
import org.cocuyo.dsl.lexer.NativeRegularExpression;
import org.cocuyo.parsing.IToken;

//close-imports
public class ASTRegex implements IASTRoot
//open-inheritance
//close-inheritance
{
	
	private IToken id;
	private IToken line;
	
	public ASTRegex(IToken id, IToken line)
	{
		this.id = id;
		this.line = line;
	}
	
	//open-members
	public void build(LexerASTEnv aEnv)
	{
		aEnv.getLexer().add(
				new NativeRegularExpression(id.getText(), line.getText()));
	}
	//close-members
	
}