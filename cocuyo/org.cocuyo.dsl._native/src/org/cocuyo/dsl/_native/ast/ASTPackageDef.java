package org.cocuyo.dsl._native.ast;

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
	public ObjectPackage build(ObjectPackage aPackage) {
		String[] _name = new String[name.size()];

		int _i = 0;

		for (ASTId aId : this.name) {
			_name[_i] = aId.getText();
			_i++;
		}

		return aPackage.definePackage(_name);
	}
	//close-members

}