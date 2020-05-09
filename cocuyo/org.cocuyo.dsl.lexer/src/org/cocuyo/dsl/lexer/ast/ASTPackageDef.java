package org.cocuyo.dsl.lexer.ast;

//open-imports
import org.cocuyo.dsl.protocol.ObjectPackage;

//close-imports
public class ASTPackageDef implements IASTRoot
//open-inheritance
//close-inheritance
{

	private ASTName name;

	public ASTPackageDef(ASTName name) {
		this.name = name;
	}

	//open-members
	public ObjectPackage build(ObjectPackage aPkg) {
		String[] _ids = this.name.toStringArray();
		return aPkg.definePackage(_ids);
	}
	//close-members

}