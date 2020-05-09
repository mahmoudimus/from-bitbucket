package tiger.ast;

//open-imports
//close-imports
public class ASTFieldAcces implements ASTExpr
//open-inheritance
//close-inheritance
{
	
	private ASTExpr expr;
	private ASTId field;
	
	public ASTFieldAcces(ASTExpr expr, ASTId field)
	{
		this.expr = expr;
		this.field = field;
	}
	
	//open-members
	//close-members
	
}