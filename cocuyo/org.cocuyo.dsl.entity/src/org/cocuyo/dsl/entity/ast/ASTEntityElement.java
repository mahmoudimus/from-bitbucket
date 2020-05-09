package org.cocuyo.dsl.entity.ast;

//open-imports
import org.cocuyo.dsl.entity.Entity;

//close-imports
public interface ASTEntityElement extends IASTRoot
//open-inheritance
//close-inheritance
{
	
	//open-members
	void build(Entity aEntity);

	void buildType();
	//close-members
	
}