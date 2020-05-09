package org.cocuyo.parsing.debug;

import static java.lang.System.out;

import java.io.IOException;

import org.cocuyo.parsing.IParserListener;


public class ParserConsoleDebugger implements IParserListener
{
    private boolean fStop;

    public ParserConsoleDebugger()
    {
	fStop = false;
    }

    public ParserConsoleDebugger(boolean aStop)
    {
	fStop = aStop;
    }

    @Override
    public void listenEOF()
    {
	printMessage("end");
    }

    @Override
    public void listenReduce(int aRuleIndex)
    {
	printMessage("(" + aRuleIndex + ")");
    }

    @Override
    public void listenShift(Object aToken)
    {
	printMessage("> " + aToken);
	try
	{
	    if (fStop)
	    {
		printMessage("[Press RETURN]");
		System.in.read();
	    }
	}
	catch (IOException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @Override
    public void listenStart()
    {
	printMessage("starting..");
    }

    protected void printMessage(String aMessage)
    {
	out.println(aMessage);
    }

    public boolean isStop()
    {
	return fStop;
    }

    public void setStop(boolean aStop)
    {
	fStop = aStop;
    }

}
