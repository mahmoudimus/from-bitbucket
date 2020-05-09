package org.cocuyo.dsl.entity.ast;

//open-imports
import org.cocuyo.dsl.protocol.ObjectPackage;

//close-imports
public class ASTPackageDef implements IASTRoot
//open-inheritance
//close-inheritance
{
	
	private ASTName fName;
	
	public ASTPackageDef(ASTName aName)
	{
		this.fName = aName;
	}
	
	//open-members
	public ObjectPackage buildPackage(ObjectPackage aPkg) {
		return aPkg.definePackage(fName.getIds());
	}
	//close-members
	
}