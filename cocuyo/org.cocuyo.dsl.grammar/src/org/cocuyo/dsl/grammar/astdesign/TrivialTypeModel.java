package org.cocuyo.dsl.grammar.astdesign;

import static org.cocuyo.util.Text.NEW_LINE;

import java.util.ArrayList;
import java.util.HashSet;

import org.cocuyo.dsl.grammar.ebnf.EBNFAlternative;
import org.cocuyo.dsl.grammar.ebnf.EBNFRootRule;
import org.cocuyo.dsl.grammar.ebnf.EBNFSymbol;
import org.cocuyo.dsl.grammar.ebnf.EBNFTerminal;
import org.cocuyo.dsl.grammar.ebnf.IEBNFAlternativeExpression;

public class TrivialTypeModel {
	private EBNFRootRule fRule;
	private HashSet<AbstractTypeModel> fSupers;
	private ArrayList<ChildNodeModel> fChildNodes;
	private ArrayList<InitMethodModel> fInitMethods;
	private ASTHierarchyModel fHierarchy;

	public TrivialTypeModel(EBNFRootRule aRule, ASTHierarchyModel aHierarchy) {
		fRule = aRule;
		fSupers = new HashSet<AbstractTypeModel>();
		fChildNodes = new ArrayList<ChildNodeModel>();
		fInitMethods = new ArrayList<InitMethodModel>();
		fHierarchy = aHierarchy;
		build();
	}

	public String getBasePath() {
		return fRule.getGrammar().getBasePath();
	}

	public String getUserPackageName() {
		return fRule.getGrammar().getAstPackageName();
	}

	public boolean isTrivial() {
		return true;
	}

	public HashSet<AbstractTypeModel> getSupers() {
		return fSupers;
	}

	public void setSupers(HashSet<AbstractTypeModel> aSupers) {
		fSupers = aSupers;
	}

	public boolean hasSupers() {
		return !fSupers.isEmpty();
	}

	public EBNFRootRule getRule() {
		return fRule;
	}

	public Iterable<ChildNodeModel> getChildNodes() {
		return fChildNodes;
	}

	protected void setChildNodesOf(TrivialTypeModel aTrivialTypeModel) {
		fChildNodes = aTrivialTypeModel.fChildNodes;
	}

	protected void setInitMethodsOf(TrivialTypeModel aTrivialTypeModel) {
		fInitMethods = aTrivialTypeModel.fInitMethods;
	}

	public int getCountChildNodes() {
		return fChildNodes.size();
	}

	public ChildNodeModel getChildNode(int aIndex) {
		return fChildNodes.get(aIndex);
	}

	public Iterable<InitMethodModel> getInitMethods() {
		return fInitMethods;
	}

	public void addInitMethod(InitMethodModel aMethod) {
		for (InitMethodModel _method : fInitMethods)
			if (aMethod.match(_method))
				return;
		fInitMethods.add(aMethod);
	}

	public String getName() {
		return fRule.getName();
	}

	@Override
	public String toString() {
		String _str = "ast " + getName() + " :";

		for (AbstractTypeModel _super : getSupers()) {
			_str += " " + _super.getName();
		}

		_str += NEW_LINE + "{" + NEW_LINE;

		for (ChildNodeModel _child : fChildNodes)
			_str += "\tfield " + _child + NEW_LINE;

		for (InitMethodModel _method : fInitMethods)
			_str += "\t" + _method + NEW_LINE;

		return _str + "}";
	}

	protected void build() {
		for (EBNFAlternative _alt : getRule().getAlts()) {
			InitMethodModel _method = new InitMethodModel(getName());

			for (IEBNFAlternativeExpression _expr : _alt.getExpressions()) {
				EBNFSymbol _symbol = (EBNFSymbol) _expr;

				if (_symbol.isAst()) {
					SimpleChildNodeModel _child = new SimpleChildNodeModel(
							_symbol.getNodeName(), _symbol.getNodeTypeName(),
							_symbol instanceof EBNFTerminal);
					_method.addArg(_child);
					addChildNode(_child.getSimilar());
				}
			}

			addInitMethod(_method);
		}
	}

	protected void addChildNode(ChildNodeModel aChild) {
		for (ChildNodeModel _child : fChildNodes) {
			if (_child.getName().equals(aChild.getName())) {
				/*
				 * if (!_child.getTypeName().equals(aChild.getTypeName())) throw
				 * new RuntimeException( "ERROR! Campos de tipos diferentes
				 * tienen el mismo nombre");
				 * 
				 * Esto solo debe chequearse al final, por tanto, debe ser
				 * chequeado explisitamente, puesto que el final es algo
				 * indetermi- nado
				 */
				return;
			}
		}
		fChildNodes.add(aChild);
	}

	protected void replaceFoolTypes() {
		replaceFoolSupers();
		replaceFoolTypeNamesInChildNodeNames();
		replaceFoolTypeNamesInInitMethods();
	}

	protected void replaceFoolTypeNamesInInitMethods() {
		ArrayList<InitMethodModel> _methods = new ArrayList<InitMethodModel>();
		_methods.addAll(fInitMethods);
		fInitMethods = new ArrayList<InitMethodModel>();

		for (InitMethodModel _method : _methods) {
			InitMethodModel _newMethod = new InitMethodModel(_method.getName());

			for (ChildNodeModel _arg : _method.getArgs()) {
				String _typeName = _arg.getTypeName();

				if (fHierarchy.containType(_typeName)) {
					TrivialTypeModel _type = fHierarchy.getType(_typeName);
					if (_type instanceof AbstractTypeModel) {
						AbstractTypeModel _abstractType = (AbstractTypeModel) _type;

						if (_abstractType.isFool()) {
							_arg.setTypeName(_abstractType.getSubstitute()
									.getName());
						}
					}
				}

				_newMethod.addArg(_arg);
			}

			addInitMethod(_newMethod);
		}

	}

	protected void replaceFoolTypeNamesInChildNodeNames() {
		ArrayList<ChildNodeModel> _fields = new ArrayList<ChildNodeModel>();
		_fields.addAll(fChildNodes);
		fChildNodes = new ArrayList<ChildNodeModel>();

		for (ChildNodeModel _field : _fields) {
			String _typeName = _field.getTypeName();

			if (fHierarchy.containType(_typeName)) {
				TrivialTypeModel _type = fHierarchy.getType(_typeName);
				if (_type instanceof AbstractTypeModel) {
					AbstractTypeModel _abstractType = (AbstractTypeModel) _type;

					if (_abstractType.isFool()) {
						_field.setTypeName(_abstractType.getSubstitute()
								.getName());
					}
				}
			}

			addChildNode(_field);
		}
	}

	public void replaceFoolSupers() {
		HashSet<AbstractTypeModel> _supers = new HashSet<AbstractTypeModel>();

		for (AbstractTypeModel _super : getSupers()) {
			_supers.add(_super.getSubstitute());
		}

		setSupers(_supers);
	}

	public ASTHierarchyModel getHierarchy() {
		return fHierarchy;
	}

}
