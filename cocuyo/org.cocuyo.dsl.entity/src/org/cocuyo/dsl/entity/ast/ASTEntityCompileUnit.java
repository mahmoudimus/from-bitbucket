package org.cocuyo.dsl.entity.ast;

//open-imports
import org.cocuyo.dsl.IInterpreterUnit;
import org.cocuyo.dsl.protocol.DSLEnvironment;
import org.cocuyo.dsl.protocol.ObjectPackage;

@SuppressWarnings("unused")
//close-imports
public class ASTEntityCompileUnit implements IASTRoot
//open-inheritance
		, IInterpreterUnit
//close-inheritance
{
	
	private ASTPackageDef fPackageDef;
	private ASTImport fImport;
	private ASTModelList fModelList;
	
	public ASTEntityCompileUnit(ASTPackageDef aPackageDef, ASTImport aImport, ASTModelList aModelList)
	{
		this.fPackageDef = aPackageDef;
		this.fImport = aImport;
		this.fModelList = aModelList;
	}
	public ASTEntityCompileUnit(ASTPackageDef aPackageDef, ASTModelList aModelList)
	{
		this.fPackageDef = aPackageDef;
		this.fModelList = aModelList;
	}
	public ASTEntityCompileUnit(ASTImport aImport, ASTModelList aModelList)
	{
		this.fImport = aImport;
		this.fModelList = aModelList;
	}
	public ASTEntityCompileUnit(ASTModelList aModelList)
	{
		this.fModelList = aModelList;
	}
	
	//open-members
	private int fBuildPass;

	@Override
	public void build(DSLEnvironment aEnv) {
		fBuildPass = 1;
		ObjectPackage _pkg = fPackageDef.buildPackage(aEnv.getObjectPackage());

		for (ASTModel _model : fModelList) {
			_model.buildPackage(_pkg);
		}
	}

	@Override
	public boolean isBuilt() {
		return fBuildPass == 3;
	}

	@Override
	public void nextBuildPass() {
		fBuildPass++;
		switch (fBuildPass) {
		case 2:
			buildElements();
			break;
		case 3:
			buildIncludes();
		}
	}

	private void buildIncludes() {
		for (ASTModel _model : fModelList) {
			_model.buildIncludes();
		}
	}

	private void buildElements() {
		for (ASTModel _model : fModelList) {
			_model.buildElements();
		}
	}

	//close-members
	
}