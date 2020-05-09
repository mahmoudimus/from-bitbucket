package tiger.ast;

//open-imports
//close-imports
public class ASTAsignExpr implements ASTExpr
//open-inheritance
//close-inheritance
{
	
	private ASTExpr left;
	private ASTExpr right;
	
	public ASTAsignExpr(ASTExpr left, ASTExpr right)
	{
		this.left = left;
		this.right = right;
	}
	
	//open-members
	//close-members
	
}