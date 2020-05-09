package org.cocuyo.dsl.grammar.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.grammar.ebnf.EBNFGrammar;
import org.cocuyo.dsl.protocol.ObjectPackage;

//close-imports
public class Grammar extends UnitElement
//open-inheritance//close-inheritance
{
	private Id id;
	private ArrayList<Rule> rulelist;
	private Decoration decoration;
	//open-fields 

	private EBNFGrammar fGrammar;

	//close-fields

	public Grammar(Id id, ArrayList<Rule> rulelist) {
		this.id = id;
		this.rulelist = rulelist;
	}

	public Grammar(Decoration decoration, Id id, ArrayList<Rule> rulelist) {
		this.decoration = decoration;
		this.id = id;
		this.rulelist = rulelist;
	}

	//open-members
	@Override
	public void build(ObjectPackage aPkg) {
		fGrammar = new EBNFGrammar(id.getText());

		for (Rule _rule : rulelist) {
			_rule.buildRule(fGrammar);
		}

		for (Rule _rule : rulelist) {
			_rule.build();
		}

		if (decoration != null) {
			fGrammar = decoration.build(fGrammar);
		}

		aPkg.define(fGrammar);
	}

	//close-members
}