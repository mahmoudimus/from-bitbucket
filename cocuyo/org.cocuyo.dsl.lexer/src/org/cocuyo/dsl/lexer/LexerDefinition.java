package org.cocuyo.dsl.lexer;

import java.util.ArrayList;
import java.util.HashSet;

import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.dsl.protocol.ObjectPackage;
import org.cocuyo.dsl.protocol.ObjectPackageMember;
import org.cocuyo.dsl.protocol.ObjectTable;

public class LexerDefinition extends ObjectPackageMember {

	private ObjectTable<RegularExpression> fRegexTable;
	private ArrayList<LexerTransition> fTransitions;
	private HashSet<String> fStatesNames;

	public LexerDefinition(String aName, ObjectPackage aContainer) {
		super(aName, aContainer);
		fRegexTable = new ObjectTable<RegularExpression>();
		fTransitions = new ArrayList<LexerTransition>();
	}

	public void add(RegularExpression aRegex) {
		aRegex.setLexerDefinition(this);
		fRegexTable.put(aRegex);
	}

	public void add(LexerTransition aTransition) {
		aTransition.setLexerDefinition(this);
		aTransition.setIndex(fTransitions.size());
		fTransitions.add(aTransition);
	}

	public Iterable<RegularExpression> getRegularExpressions() {
		return fRegexTable.getElements();
	}

	public Iterable<LexerTransition> getTransitions() {
		return fTransitions;
	}

	public RegularExpression findRegularExpression(String aText)
			throws NameNotFoundException {
		return fRegexTable.find(aText);
	}

	public HashSet<String> getStatesNames() {
		if (fStatesNames == null) {
			fStatesNames = new HashSet<String>();

			for (LexerTransition _t : fTransitions) {
				fStatesNames.add(_t.getFromState());
			}
		}
		return fStatesNames;
	}
}
