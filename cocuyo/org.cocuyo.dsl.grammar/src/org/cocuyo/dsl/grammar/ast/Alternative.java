package org.cocuyo.dsl.grammar.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.grammar.ebnf.EBNFAlternative;
import org.cocuyo.dsl.grammar.ebnf.EBNFRule;
import org.cocuyo.dsl.grammar.ebnf.EBNFVirtualSymbol;

//open-imports

@SuppressWarnings("unused")
//close-imports
public class Alternative
//open-inheritance//close-inheritance
{
	private AltId id;
	private ArrayList<AltExpr> exprlist;
	private Symbol symbol;

	//open-fields

	//close-fields

	public Alternative(AltId id, ArrayList<AltExpr> exprlist) {
		this.id = id;
		this.exprlist = exprlist;
	}

	public Alternative(AltId id, ArrayList<AltExpr> exprlist, Symbol symbol) {
		this.id = id;
		this.exprlist = exprlist;
		this.symbol = symbol;
	}

	public Alternative(AltId id) {
		this.id = id;
	}

	//open-members

	public EBNFAlternative build(EBNFRule aRule) {
		EBNFAlternative _resultAlt = new EBNFAlternative();
		_resultAlt.setRule(aRule);

		if (exprlist != null) {
			for (AltExpr _expr : exprlist) {
				_resultAlt.addExpression(_expr.build(_resultAlt));
			}
		}

		if (symbol != null) {
			EBNFVirtualSymbol _virtualSymbol = new EBNFVirtualSymbol();

			_virtualSymbol.setText(symbol.getName());
			_virtualSymbol.setBuilderName(symbol.getBuilderName());
			_virtualSymbol.setTypeName(symbol.getTypeName());
			_virtualSymbol.setID(symbol.getID());

			EBNFAlternative _subAlt = _resultAlt;
			_virtualSymbol.setSubAlternative(_subAlt);

			_resultAlt = new EBNFAlternative();
			_resultAlt.addExpression(_virtualSymbol);
		}

		return _resultAlt;
	}
	//close-members
}