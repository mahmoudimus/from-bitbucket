package org.cocuyo.dsl.entity.ast;

//open-imports
//close-imports
public class ASTName extends ASTList<ASTId> implements IASTRoot
//open-inheritance
//close-inheritance
{
	
	//open-members
	public String[] getIds() {
		String[] _ids = new String[this.size()];

		int _i = 0;

		for (ASTId _id : this) {
			_ids[_i] = _id.getToken().getText();
			_i++;
		}

		return _ids;
	}
	//close-members
	
}