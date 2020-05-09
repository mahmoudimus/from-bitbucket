package org.cocuyo.dsl.entity.ast;

//open-imports
import org.cocuyo.dsl.entity.EntityField;

//close-imports
public class ASTModifier implements IASTRoot
//open-inheritance
//close-inheritance
{
	
	
	
	public ASTModifier()
	{
		
	}
	
	//open-members
	public void build(EntityField aField) {
		aField.setIsRequired(true);
	}
	//close-members
	
}