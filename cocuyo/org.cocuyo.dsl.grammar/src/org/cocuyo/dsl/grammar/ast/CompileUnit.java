package org.cocuyo.dsl.grammar.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.IInterpreterUnit;
import org.cocuyo.dsl.protocol.DSLEnvironment;
import org.cocuyo.dsl.protocol.ObjectPackage;

//close-imports
public class CompileUnit
// open-inheritance
		implements IInterpreterUnit
// close-inheritance
{
	private PackageDef packagedef;
	private ArrayList<UnitElement> unitelements;

	// open-fields//close-fields

	public CompileUnit(PackageDef packagedef,
			ArrayList<UnitElement> unitelements) {
		this.packagedef = packagedef;
		this.unitelements = unitelements;
	}

	// open-members

	@Override
	public void build(DSLEnvironment aEnv) {
		ObjectPackage _pkg = packagedef.build(aEnv.getObjectPackage());

		for (UnitElement _elem : unitelements) {
			_elem.build(_pkg);
		}
	}

	@Override
	public boolean isBuilt() {
		return true;
	}

	@Override
	public void nextBuildPass() {
	}
	// close-members
}