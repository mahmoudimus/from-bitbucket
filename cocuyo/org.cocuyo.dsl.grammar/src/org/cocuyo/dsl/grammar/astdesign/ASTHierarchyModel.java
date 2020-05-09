package org.cocuyo.dsl.grammar.astdesign;

import static org.cocuyo.util.Text.NEW_LINE;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import org.cocuyo.dsl.grammar.ebnf.EBNFGrammar;

public class ASTHierarchyModel implements Iterable<TrivialTypeModel> {
	private ArrayList<TrivialTypeModel> fTypes;
	private Hashtable<String, TrivialTypeModel> fTypeTable;
	private ArrayList<ListTypeModel> fListTypes;
	private EBNFGrammar fGrammar;

	public ASTHierarchyModel(EBNFGrammar aGrammar) {
		fGrammar = aGrammar;
		clear();

	}

	public String getBasePath() {
		return fGrammar.getBasePath();
	}

	public String getAstPackageName() {
		return fGrammar.getAstPackageName();
	}

	public void add(TrivialTypeModel aType) {
		fTypes.add(aType);
		fTypeTable.put(aType.getName(), aType);
	}

	@Override
	public Iterator<TrivialTypeModel> iterator() {
		return fTypes.iterator();
	}

	@Override
	public String toString() {
		String _str = "";

		for (TrivialTypeModel _type : this) {
			_str += _type + NEW_LINE;
		}

		return _str;

	}

	public boolean containType(String aName) {
		return fTypeTable.containsKey(aName);
	}

	public TrivialTypeModel getType(String aName) {
		return fTypeTable.get(aName);
	}

	public void clear() {
		fTypes = new ArrayList<TrivialTypeModel>();
		fTypeTable = new Hashtable<String, TrivialTypeModel>();
	}

	public void addAll(Iterable<TrivialTypeModel> aTypes) {
		for (TrivialTypeModel _type : aTypes)
			add(_type);
	}

	public Iterable<ListTypeModel> getListTypes() {
		if (fListTypes == null) {
			fListTypes = new ArrayList<ListTypeModel>();
			for (TrivialTypeModel _type : fTypes) {
				if (_type instanceof ListTypeModel)
					fListTypes.add((ListTypeModel) _type);
			}
		}
		return fListTypes;
	}
}
