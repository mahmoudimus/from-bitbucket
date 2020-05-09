package org.cocuyo.dsl.grammar.ebnf;

import static org.cocuyo.util.Text.NEW_LINE;

import java.util.ArrayList;
import java.util.Iterator;

public class SymbolSecuenceSet implements Iterable<SymbolSecuence> {
	private ArrayList<SymbolSecuence> fSecuences;

	public SymbolSecuenceSet() {
		fSecuences = new ArrayList<SymbolSecuence>();
	}

	/**
	 * {[abc][cd]} multiply {[x][y]} = {[abcx][abcy][cdx][cdy]}
	 * 
	 * This method dont change this set, just compute a new one.
	 * 
	 * @param aSet
	 * @return A multiplication of this set with aSet
	 */
	public SymbolSecuenceSet multiply(SymbolSecuenceSet aSet) {
		SymbolSecuenceSet _result = new SymbolSecuenceSet();

		for (SymbolSecuence _leftSec : this)
			for (SymbolSecuence _rightSec : aSet) {
				SymbolSecuence _newSec = new SymbolSecuence();
				_newSec.addAll(_leftSec);
				_newSec.addAll(_rightSec);
				_result.fSecuences.add(_newSec);
			}

		return _result;
	}

	public SymbolSecuenceSet union(SymbolSecuenceSet aSet) {
		SymbolSecuenceSet _result = new SymbolSecuenceSet();

		for (SymbolSecuence _sec : this) {
			_result.fSecuences.add(_sec);
		}

		for (SymbolSecuence _sec : aSet) {
			_result.fSecuences.add(_sec);
		}

		return _result;
	}

	public SymbolSecuenceSet union(SymbolSecuence aSecuence) {
		SymbolSecuenceSet _result = new SymbolSecuenceSet();

		for (SymbolSecuence _sec : this) {
			_result.fSecuences.add(_sec);
		}
		_result.fSecuences.add(aSecuence);

		return _result;
	}

	public SymbolSecuenceSet append(EBNFSymbol aSymbol) {
		SymbolSecuenceSet _result = new SymbolSecuenceSet();

		if (isEmpty()) {
			fSecuences.add(new SymbolSecuence());
		}

		for (SymbolSecuence _sec : this) {
			SymbolSecuence _newSec = new SymbolSecuence();
			_newSec.addAll(_sec);
			_newSec.add(aSymbol);
			_result.fSecuences.add(_newSec);
		}

		return _result;
	}

	public boolean isEmpty() {
		return fSecuences.isEmpty();
	}

	@Override
	public Iterator<SymbolSecuence> iterator() {
		return fSecuences.iterator();
	}

	@Override
	public String toString() {
		String _str = "";
		for (SymbolSecuence _sec : this) {
			_str += "\t" + _sec.toString() + NEW_LINE;
		}
		return "{" + NEW_LINE + _str + "}";
	}

}
