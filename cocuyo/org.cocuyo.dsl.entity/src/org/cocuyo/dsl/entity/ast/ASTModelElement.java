package org.cocuyo.dsl.entity.ast;

//open-imports
import org.cocuyo.dsl.entity.DomainModel;

//close-imports
public interface ASTModelElement extends IASTRoot
//open-inheritance
//close-inheritance
{
	
	//open-members
	void buildModel(DomainModel aModel);

	void buildElements();

	void buildTypes();

	//close-members
	
}