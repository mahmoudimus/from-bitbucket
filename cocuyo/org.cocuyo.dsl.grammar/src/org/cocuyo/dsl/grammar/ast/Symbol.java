package org.cocuyo.dsl.grammar.ast;

import org.cocuyo.dsl.grammar.ebnf.EBNFAlternative;
import org.cocuyo.dsl.grammar.ebnf.EBNFSymbol;
import org.cocuyo.dsl.grammar.ebnf.IEBNFAlternativeExpression;
//open-imports//close-imports
public class Symbol extends AltElement
//open-inheritance//close-inheritance
{
	private Node node;
	private Id type;
	private Id builder;
	private Id id;
	//open-fields//close-fields
	
	public Symbol(Node node)
	{
		this.node = node;
	}
	public Symbol(Node node, Id type)
	{
		this.node = node;
		this.type = type;
	}
	public Symbol(Node node, Id type, Id builder)
	{
		this.node = node;
		this.type = type;
		this.builder = builder;
	}
	public Symbol(Id id, Node node)
	{
		this.id = id;
		this.node = node;
	}
	public Symbol(Id id, Node node, Id type)
	{
		this.id = id;
		this.node = node;
		this.type = type;
	}
	public Symbol(Id id, Node node, Id type, Id builder)
	{
		this.id = id;
		this.node = node;
		this.type = type;
		this.builder = builder;
	}
	//open-members

    public String getName()
    {
	return node.getText();
    }

    public String getBuilderName()
    {
	return builder != null ? builder.getText() : null;
    }

    public String getTypeName()
    {
	return type != null ? type.getText() : null;
    }

    public String getID()
    {
	return id != null ? id.getText() : null;
    }

    @Override
    public IEBNFAlternativeExpression build(EBNFAlternative aAlt)
    {
	EBNFSymbol _symbol = node.build(aAlt);
	if (builder != null)
	    _symbol.setBuilderName(builder.getText());
	if (type != null)
	    _symbol.setNodeTypeName(type.getText());
	if (id != null)
	    _symbol.setID(id.getText());
	return _symbol;
    }

    //close-members
}