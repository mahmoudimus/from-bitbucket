package org.cocuyo.dsl.textgenerator.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.IInterpreterUnit;
import org.cocuyo.dsl.protocol.DSLEnvironment;
import org.cocuyo.dsl.protocol.ObjectPackage;
import org.cocuyo.dsl.textgenerator.env.ImportsEnvironment;
import org.cocuyo.dsl.textgenerator.env.TextGeneratorEnv;

//close-imports
public class CompileUnit
// open-inheritance
		implements IInterpreterUnit
// close-inheritance
{
	private PackageDef packagedef;
	private ArrayList<UnitElement> unitelementlist;
	// open-fields

	private int fPassNumber = 0;

	// close-fields

	public CompileUnit(PackageDef packagedef,
			ArrayList<UnitElement> unitelementlist) {
		this.packagedef = packagedef;
		this.unitelementlist = unitelementlist;
	}

	// open-members

	@Override
	public void build(DSLEnvironment aEnv) {
		ObjectPackage _pkg = aEnv.getObjectPackage().definePackage(
				packagedef.getName());
		TextGeneratorEnv _env = new TextGeneratorEnv(_pkg);

		for (UnitElement _elem : unitelementlist) {
			_elem.buildTypeLevel(_env);
		}
	}

	public void buildInstr() {
		ImportsEnvironment aEnv = new ImportsEnvironment();

		for (UnitElement _elem : unitelementlist) {
			_elem.buildInstrLevel(aEnv);
		}
	}

	@Override
	public boolean isBuilt() {
		return fPassNumber == 3;
	}

	@Override
	public void nextBuildPass() {
		switch (fPassNumber) {
		case 0:
			buildInstr();
			break;
		case 1:
			break;

		}
		fPassNumber++;
	}
	// close-members
}