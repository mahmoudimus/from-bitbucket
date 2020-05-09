package org.cocuyo.dsl.lexer.ast;

//open-imports

import org.cocuyo.dsl.IInterpreterUnit;
import org.cocuyo.dsl.protocol.DSLEnvironment;
import org.cocuyo.dsl.protocol.ObjectPackage;

//close-imports
public class ASTUnit implements IASTRoot
//open-inheritance
		, IInterpreterUnit
//close-inheritance
{

	private ASTPackageDef packagedef;
	private ASTLexerDefList lexerdeflist;

	public ASTUnit(ASTPackageDef packagedef, ASTLexerDefList lexerdeflist) {
		this.packagedef = packagedef;
		this.lexerdeflist = lexerdeflist;
	}

	public ASTUnit(ASTLexerDefList lexerdeflist) {
		this.lexerdeflist = lexerdeflist;
	}

	//open-members
	@Override
	public void build(DSLEnvironment aEnv) {
		ObjectPackage _pkg = packagedef != null ? packagedef.build(aEnv
				.getObjectPackage()) : aEnv.getObjectPackage();
		for (ASTLexerDef _lexerDef : this.lexerdeflist) {
			_lexerDef.build(_pkg);
		}
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