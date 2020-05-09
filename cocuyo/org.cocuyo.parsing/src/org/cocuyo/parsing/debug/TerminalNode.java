package org.cocuyo.parsing.debug;

public class TerminalNode
{
    private Object fTerminal;

    public TerminalNode(Object aTerminal)
    {
	this.fTerminal = aTerminal;
    }

    public Object getTerminal()
    {
	return fTerminal;
    }

    public void setTerminal(Object aTerminal)
    {
	this.fTerminal = aTerminal;
    }

    @Override
    public String toString()
    {
	return getTerminal().toString();
    }

}
