package org.cocuyo.parsing;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Iterator;

public class RecognitionErrorCollection implements Iterable<RecognitionError>
{
    private final ArrayList<RecognitionError> fErrors;

    public RecognitionErrorCollection()
    {
	fErrors = new ArrayList<RecognitionError>();
    }

    public void addError(RecognitionError aError)
    {
	fErrors.add(aError);
    }

    public void addError(String aMessage, IToken aToken)
    {
	addError(new RecognitionError(aMessage, aToken));
    }

    public void addError(String aMessage)
    {
	addError(new RecognitionError(aMessage));
    }

    public void addUnexpectedTokenError(IToken aUnexpectedToken)
    {
	if (aUnexpectedToken.isEOF())
	{
	    fErrors.add(new RecognitionError("Unexpected end of file",
		    aUnexpectedToken));
	}
	else
	{
	    fErrors.add(new RecognitionError(aUnexpectedToken));
	}
    }

    public void clear()
    {
	fErrors.clear();
    }

    public int count()
    {
	return fErrors.size();
    }

    public boolean hasErrors()
    {
	return count() > 0;
    }

    public void print()
    {
	out.println(fErrors.size() + " Errors founded.");

	out.print(getPrettyMessages());
    }

    public Iterator<RecognitionError> iterator()
    {
	return fErrors.iterator();
    }

    public String getPrettyMessages()
    {
	String _str = "";
	for (RecognitionError _error : fErrors)
	{
	    _str += _error.getPrettyMessage() + "\n";
	}
	return _str;
    }

    public void addErrors(RecognitionErrorCollection aErrors)
    {
	fErrors.addAll(aErrors.fErrors);
    }

}
