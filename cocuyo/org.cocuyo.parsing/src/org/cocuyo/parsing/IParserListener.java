package org.cocuyo.parsing;

public interface IParserListener
{
    public void listenStart();

    public void listenShift(Object aToken);

    public void listenReduce(int aRuleIndex);

    public void listenEOF();

}
