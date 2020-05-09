package org.cocuyo.dsl._native.ast;

//open-imports
import org.cocuyo.dsl.IInterpreterUnit;
import org.cocuyo.dsl._native.NativeObject;
import org.cocuyo.dsl.protocol.DSLEnvironment;
import org.cocuyo.dsl.protocol.ObjectPackage;
import org.cocuyo.parsing.IToken;

//close-imports
public class ASTCompileUnit implements IASTRoot
//open-inheritance
		, IInterpreterUnit
//close-inheritance
{

	private ASTPackageDef packagedef;
	private ASTId id;
	private IToken text;

	public ASTCompileUnit(ASTPackageDef packagedef, ASTId id, IToken text) {
		this.packagedef = packagedef;
		this.id = id;
		this.text = text;
	}

	public ASTCompileUnit(ASTPackageDef packagedef) {
		this.packagedef = packagedef;
	}

	public ASTCompileUnit(ASTId id, IToken text) {
		this.id = id;
		this.text = text;
	}

	public ASTCompileUnit() {

	}

	//open-members

	@Override
	public void build(DSLEnvironment aEnv) {
		ObjectPackage _package = aEnv.getObjectPackage();
		NativeObject _obj = new NativeObject(id.getText(), text.getText(),
				_package);
		_package = packagedef != null ? packagedef.build(_package) : _package
				.getRoot();
		_package.define(_obj);
	}

	@Override
	public boolean isBuilt() {
		return true;
	}

	@Override
	public void nextBuildPass() {

	}

	//close-members

}