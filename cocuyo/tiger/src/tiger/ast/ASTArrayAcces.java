package tiger.ast;

//open-imports
//close-imports
public class ASTArrayAcces implements ASTExpr
//open-inheritance
//close-inheritance
{
	
	private ASTExpr expr;
	private ASTExpr index;
	
	public ASTArrayAcces(ASTExpr expr, ASTExpr index)
	{
		this.expr = expr;
		this.index = index;
	}
	
	//open-members
	//close-members
	
}