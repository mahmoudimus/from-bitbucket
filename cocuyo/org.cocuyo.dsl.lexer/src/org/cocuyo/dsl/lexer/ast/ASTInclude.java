package org.cocuyo.dsl.lexer.ast;

//open-imports
import org.cocuyo.parsing.IToken;

@SuppressWarnings("unused")
//close-imports
public class ASTInclude implements IASTRoot
//open-inheritance
//close-inheritance
{

	private IToken id;
	private ASTName name;

	public ASTInclude(IToken id, ASTName name) {
		this.id = id;
		this.name = name;
	}

	public ASTInclude(ASTName name) {
		this.name = name;
	}

	//open-members
	//close-members

}