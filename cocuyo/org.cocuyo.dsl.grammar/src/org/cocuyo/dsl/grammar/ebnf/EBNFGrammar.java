package org.cocuyo.dsl.grammar.ebnf;

import static org.cocuyo.util.Printing.println;

import java.util.ArrayList;
import java.util.Hashtable;

import org.cocuyo.dsl.grammar.astdesign.ASTBuilderModel;
import org.cocuyo.dsl.grammar.astdesign.ASTDesigner;
import org.cocuyo.dsl.grammar.astdesign.ASTHierarchyModel;
import org.cocuyo.dsl.protocol.ObjectPackageMember;

public class EBNFGrammar extends ObjectPackageMember {
	private final Hashtable<String, EBNFRootRule> fRuleTable;
	private ArrayList<EBNFRootRule> fRuleList;
	private ArrayList<EBNFRootRule> fVirtualRules;
	private Hashtable<String, EBNFRootRule> fVirtualRuleTable;
	private ASTHierarchyModel fHierarchy;
	private EBNFGrammar fBNF;
	private ASTBuilderModel fASTBuilder;
	private Hashtable<String, EBNFTerminal> fTerminalsByNameTable;
	private ArrayList<EBNFTerminal> fTerminalsByNameList;
	private String fAstPackageName;
	private String fBasePath;
	private String fParserPackageName;
	private String fParserPath;

	public class AuxiliarRuleProvider {
		private int fCounter;
		private ArrayList<EBNFRootRule> fAuxListRuleList;
		private Hashtable<String, EBNFNonTerminal> fAuxListRuleNameSet;

		public AuxiliarRuleProvider() {
			fCounter = 0;
			fAuxListRuleList = new ArrayList<EBNFRootRule>();
			fAuxListRuleNameSet = new Hashtable<String, EBNFNonTerminal>();
		}

		public EBNFNonTerminal addRuleFrom(EBNFCuantifier aCuantifier) {
			IEBNFAlternativeExpression _expr = aCuantifier.getExpression();
			String _name = "_List_" + fCounter;
			fCounter++;

			EBNFNonTerminal _result = new EBNFNonTerminal();

			if (!(_expr instanceof EBNFLiteral) && _expr instanceof EBNFSymbol) {
				EBNFSymbol _symbol = (EBNFSymbol) _expr;

				_name = _symbol.getName() + "List";

				EBNFNonTerminal _nonTerminal = new EBNFNonTerminal(_name);

				if (fAuxListRuleNameSet.containsKey(_name)) {
					_result = fAuxListRuleNameSet.get(_name);
					return _result;
				}

				_result = _nonTerminal;
			}

			_result.setText(_name);

			EBNFRootRule _rule = new EBNFRootRule(_name);
			fAuxListRuleList.add(_rule);

			SymbolSecuenceSet _set = buildSetFromExpr(this, _expr);

			for (SymbolSecuence _sec : _set) {
				EBNFAlternative _altMany = new EBNFAlternative();
				EBNFAlternative _altOne = new EBNFAlternative();

				for (EBNFSymbol _symbol : _sec) {
					_altMany.addExpression(_symbol);
					_altOne.addExpression(_symbol);
				}
				_altMany.addExpression(_result);
				_rule.insertAlt(_altMany);

				if (aCuantifier.getKind() != EBNFCuantifier.Kind.MANY_OR_EMPTY) {
					_rule.addAlt(_altOne);
				}
			}

			if (aCuantifier.getKind() == EBNFCuantifier.Kind.MANY_OR_EMPTY) {
				_rule.addAlt(new EBNFAlternative());
			}

			fAuxListRuleNameSet.put(_name, _result);
			return _result;
		}

		public ArrayList<EBNFRootRule> getListRules() {
			return fAuxListRuleList;
		}
	}

	public EBNFGrammar(String aName) {
		super(aName, null);
		fRuleTable = new Hashtable<String, EBNFRootRule>();
		fRuleList = new ArrayList<EBNFRootRule>();
		fVirtualRules = new ArrayList<EBNFRootRule>();
		fAstPackageName = "ast";
		fBasePath = "ast";
		fParserPackageName = "syntax";
		fParserPath = "syntax";
	}

	public void addRule(EBNFRootRule aRule) {
		aRule.setGrammar(this);
		aRule.setIndex(fRuleList.size());
		fRuleTable.put(aRule.getName(), aRule);
		fRuleList.add(aRule);
	}

	public Iterable<EBNFRootRule> getRules() {
		return fRuleList;
	}

	public int getCountRules() {
		return fRuleList.size();
	}

	public boolean containRule(String aRuleName) {
		return fRuleTable.containsKey(aRuleName);
	}

	public EBNFGrammar getBNF() {
		if (fBNF == null)
			rebuildBNF();

		return fBNF;
	}

	public ASTBuilderModel getAstBuilder() {
		if (fASTBuilder == null) {
			fASTBuilder = new ASTBuilderModel(this);
		}
		return fASTBuilder;
	}

	public Iterable<EBNFRootRule> getVirtualRules() {
		if (fVirtualRuleTable == null) {
			rebuildVirtualRules();
		}
		return fVirtualRules;
	}

	public ASTHierarchyModel getHierarchy() {
		if (fHierarchy == null)
			fHierarchy = new ASTDesigner(this).getHierarchy();

		return fHierarchy;
	}

	protected void rebuildVirtualRules() {
		fVirtualRuleTable = new Hashtable<String, EBNFRootRule>();
		fVirtualRules = new ArrayList<EBNFRootRule>();

		for (EBNFRootRule _rule : getBNF().getRules()) {
			buildVirtualRulesFrom(_rule);
		}
	}

	private void buildVirtualRulesFrom(EBNFRule aRule) {
		for (EBNFAlternative _alt : aRule.getAlts()) {
			buildVirtualRulesFromAlt(_alt);
		}
	}

	private void buildVirtualRulesFromAlt(EBNFAlternative aAlt) {
		for (IEBNFAlternativeExpression _expr : aAlt.getExpressions()) {
			if (_expr instanceof EBNFVirtualSymbol) {
				EBNFVirtualSymbol _virtualSymbol = (EBNFVirtualSymbol) _expr;
				buildVirtualRulesFromSymbol(_virtualSymbol);
			}
		}
	}

	private void buildVirtualRulesFromSymbol(EBNFVirtualSymbol aVirtualSymbol) {
		EBNFRootRule _rule = null;

		if (fVirtualRuleTable.containsKey(aVirtualSymbol.getName())) {
			_rule = fVirtualRuleTable.get(aVirtualSymbol.getName());
		} else {
			_rule = new EBNFRootRule(aVirtualSymbol.getName());
			_rule.setGrammar(this);
			fVirtualRules.add(_rule);
			fVirtualRuleTable.put(_rule.getName(), _rule);
		}
		_rule.addAlt(aVirtualSymbol.getSubAlt());
		buildVirtualRulesFromAlt(aVirtualSymbol.getSubAlt());
	}

	protected void rebuildBNF() {
		fBNF = new EBNFGrammar(getName());
		fBNF.setAstPackageName(getAstPackageName());
		fBNF.setBasePath(getBasePath());
		fBNF.setParserPackageName(getParserPackageName());
		fBNF.setParserPath(getParserPath());

		AuxiliarRuleProvider _ruleProvider = new AuxiliarRuleProvider();

		for (EBNFRootRule _oldRule : getRules()) {
			EBNFRootRule _newRule = new EBNFRootRule(_oldRule.getName());

			SymbolSecuenceSet _set = buildSetFromRule(_ruleProvider, _oldRule);

			for (SymbolSecuence _sec : _set) {
				EBNFAlternative _alt = new EBNFAlternative();
				for (EBNFSymbol _symbol : _sec)
					_alt.addExpression(_symbol);
				_newRule.addAlt(_alt);

			}
			fBNF.addRule(_newRule);

			// CocuyoUtils.println(_set);
		}
		for (EBNFRootRule _listRule : _ruleProvider.getListRules()) {
			fBNF.addRule(_listRule);
		}

		// fBNF.debug();
	}

	private SymbolSecuenceSet buildSetFromRule(
			AuxiliarRuleProvider aRuleProvider, EBNFRule aOldRule) {
		SymbolSecuenceSet _result = new SymbolSecuenceSet();
		for (EBNFAlternative _oldAlt : aOldRule.getAlts()) {
			_result = _result.union(buildSetFromAlt(aRuleProvider, _oldAlt));
		}
		return _result;
	}

	private SymbolSecuenceSet buildSetFromAlt(
			AuxiliarRuleProvider aRuleProvider, EBNFAlternative aOldAlt) {
		SymbolSecuenceSet _result = new SymbolSecuenceSet();
		_result = _result.union(SymbolSecuence.EMPTY);

		for (IEBNFAlternativeExpression _expr : aOldAlt.getExpressions()) {
			_result = _result.multiply(buildSetFromExpr(aRuleProvider, _expr));
		}

		return _result;
	}

	private SymbolSecuenceSet buildSetFromExpr(
			AuxiliarRuleProvider aRuleProvider, IEBNFAlternativeExpression aExpr) {
		SymbolSecuenceSet _result = new SymbolSecuenceSet();

		if (aExpr instanceof EBNFVirtualSymbol) {
			EBNFVirtualSymbol _virtualSymbol = (EBNFVirtualSymbol) aExpr;
			SymbolSecuenceSet _set = buildSetFromAlt(aRuleProvider,
					_virtualSymbol.getSubAlt());

			for (SymbolSecuence _sec : _set) {
				EBNFVirtualSymbol _clone = _virtualSymbol.getSimilar();
				EBNFAlternative _alt = new EBNFAlternative();

				for (IEBNFAlternativeExpression _expr : _sec) {
					_alt.addExpression(_expr);
				}
				_clone.setSubAlternative(_alt);
				SymbolSecuence _virtualSec = new SymbolSecuence();
				_virtualSec.add(_clone);
				_result = _result.union(_virtualSec);
			}
		} else if (aExpr instanceof EBNFSubRule) {
			EBNFSubRule _subRule = (EBNFSubRule) aExpr;
			_result = buildSetFromRule(aRuleProvider, _subRule);
		} else if (aExpr instanceof EBNFCuantifier) {
			EBNFCuantifier _cuantifier = (EBNFCuantifier) aExpr;
			switch (_cuantifier.getKind()) {
			case ONE_OR_EMPTY:
				_result = buildSetFromExpr(aRuleProvider, _cuantifier
						.getExpression());
				_result = _result.union(SymbolSecuence.EMPTY);
				break;
			case MANY_OR_ONE:
				_cuantifier = simplifyCuantifier(_cuantifier);
				EBNFNonTerminal _nonTerminal1 = aRuleProvider
						.addRuleFrom(_cuantifier);
				_result = _result.append(_nonTerminal1);
				break;
			case MANY_OR_EMPTY:
				_cuantifier = simplifyCuantifier(_cuantifier);
				EBNFNonTerminal _nonTerminal2 = aRuleProvider
						.addRuleFrom(_cuantifier);
				_result = _result.append(_nonTerminal2);
				break;
			}
		} else {
			_result = _result.append((EBNFSymbol) aExpr);
		}
		return _result;
	}

	public EBNFCuantifier simplifyCuantifier(EBNFCuantifier aCuantifier) {
		if (aCuantifier.getExpression() instanceof EBNFSubRule) {
			EBNFSubRule _subRule = ((EBNFSubRule) aCuantifier.getExpression());

			if (_subRule.getCountAlts() == 1) {
				EBNFAlternative _alt = _subRule.getAltAt(0);
				if (_alt.getCountExpressions() == 1) {
					aCuantifier = new EBNFCuantifier(_alt.getExpressionAt(0),
							aCuantifier.getKind());
				}
			}
		}
		return aCuantifier;
	}

	public void debug() {
		println("grammar " + getName());

		for (EBNFRootRule _rule : getRules()) {
			_rule.debug();
		}
	}

	public String getAstPackageName() {
		return fAstPackageName;
	}

	public void setAstPackageName(String aUserPackageName) {
		fAstPackageName = aUserPackageName;
	}

	public String getParserPackageName() {
		return fParserPackageName;
	}

	public String getParserPath() {
		return fParserPath;
	}

	public void setParserPath(String aParserPath) {
		fParserPath = aParserPath;
	}

	public void setParserPackageName(String aParserPackageName) {
		fParserPackageName = aParserPackageName;
	}

	public String getBasePath() {
		return fBasePath;
	}

	public Iterable<EBNFTerminal> getTerminalsByName() {
		if (fTerminalsByNameTable == null) {
			buildTerminals();
		}
		return fTerminalsByNameList;
	}

	private void buildTerminals() {
		fTerminalsByNameTable = new Hashtable<String, EBNFTerminal>();
		fTerminalsByNameList = new ArrayList<EBNFTerminal>();

		for (EBNFRule _rule : getBNF().getRules()) {
			for (EBNFAlternative _alt : _rule.getAlts()) {
				buildTerminalsFromAlt(_alt);
			}
		}
	}

	private void buildTerminalsFromAlt(EBNFAlternative aAlt) {
		for (IEBNFAlternativeExpression _expr : aAlt.getExpressions()) {
			if (_expr instanceof EBNFVirtualSymbol) {
				buildTerminalsFromAlt(((EBNFVirtualSymbol) _expr).getSubAlt());
			} else if (_expr instanceof EBNFTerminal) {
				EBNFTerminal _t = (EBNFTerminal) _expr;

				if (!fTerminalsByNameTable.containsKey(_t.getName())) {
					fTerminalsByNameTable.put(_t.getName(), _t);

					if (_t instanceof EBNFLiteral) {
						fTerminalsByNameList.add(0, _t);
					} else {
						fTerminalsByNameList.add(_t);
					}
				}

			}
		}
	}

	public void setBasePath(String aBasePath) {
		fBasePath = aBasePath;
	}

}
