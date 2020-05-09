package org.cocuyo.dsl.entity.ast;

//open-imports
import org.cocuyo.parsing.IToken;

//close-imports
public class ASTId implements IASTRoot
//open-inheritance
//close-inheritance
{
	
	private IToken fid;
	
	public ASTId(IToken aid)
	{
		this.fid = aid;
	}
	
	//open-members
	public IToken getToken() {
		return fid;
	}
	//close-members
	
}