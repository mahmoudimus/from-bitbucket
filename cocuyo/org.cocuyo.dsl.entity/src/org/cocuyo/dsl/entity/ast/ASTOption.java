package org.cocuyo.dsl.entity.ast;

//open-imports
//close-imports
public class ASTOption implements IASTRoot
//open-inheritance
//close-inheritance
{
	
	private ASTId fId;
	private ASTLiteral fLiteral;
	
	public ASTOption(ASTId aId, ASTLiteral aLiteral)
	{
		this.fId = aId;
		this.fLiteral = aLiteral;
	}
	
	//open-members
	//close-members
	
}