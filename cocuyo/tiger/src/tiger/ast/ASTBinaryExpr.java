package tiger.ast;

//open-imports
//close-imports
public class ASTBinaryExpr implements ASTExpr
//open-inheritance
//close-inheritance
{
	
	private ASTExpr left;
	private ASTExpr right;
	
	public ASTBinaryExpr(ASTExpr left, ASTExpr right)
	{
		this.left = left;
		this.right = right;
	}
	
	//open-members
	//close-members
	
}