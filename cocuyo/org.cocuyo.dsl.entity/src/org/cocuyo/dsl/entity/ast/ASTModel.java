package org.cocuyo.dsl.entity.ast;

//open-imports
import org.cocuyo.dsl.entity.DomainModel;
import org.cocuyo.dsl.protocol.ObjectPackage;

//close-imports
public class ASTModel implements IASTRoot
//open-inheritance
//close-inheritance
{
	
	private ASTId fId;
	private ASTIncludeList fIncludeList;
	private ASTModelElementList fModelElementList;
	
	public ASTModel(ASTId aId, ASTIncludeList aIncludeList, ASTModelElementList aModelElementList)
	{
		this.fId = aId;
		this.fIncludeList = aIncludeList;
		this.fModelElementList = aModelElementList;
	}
	
	//open-members
	DomainModel fModel;

	public void buildPackage(ObjectPackage aPkg) {

		fModel = new DomainModel(fId.getToken().getText(), aPkg);
		aPkg.define(fModel);

		for (ASTModelElement _element : fModelElementList) {
			_element.buildModel(fModel);
		}
	}

	public void buildElements() {
		for (ASTModelElement _elem : fModelElementList) {
			_elem.buildElements();
		}
	}

	public void buildIncludes() {
		for (ASTInclude _include : fIncludeList) {
			_include.build(fModel);
		}
		for (ASTModelElement _elem : fModelElementList) {
			_elem.buildTypes();
		}
	}

	//close-members
	
}