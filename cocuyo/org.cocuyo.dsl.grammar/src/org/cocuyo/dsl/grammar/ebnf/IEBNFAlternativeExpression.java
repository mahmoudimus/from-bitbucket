package org.cocuyo.dsl.grammar.ebnf;

public interface IEBNFAlternativeExpression
{
    public void debug();

    public void setAltContainer(EBNFAlternative aAlt);

    public EBNFAlternative getAltContainer();

}
