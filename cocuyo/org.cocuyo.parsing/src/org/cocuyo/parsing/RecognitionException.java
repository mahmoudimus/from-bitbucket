package org.cocuyo.parsing;

public class RecognitionException extends RuntimeException
{
    private RecognitionErrorCollection fErrors;

    public RecognitionException(RecognitionError aError)
    {
	fErrors = new RecognitionErrorCollection();
	fErrors.addError(aError);
    }

    public RecognitionException(RecognitionErrorCollection aErrors)
    {
	fErrors = aErrors;
    }

    public RecognitionException(String aMessage)
    {
	this(new RecognitionError(aMessage));
    }

    @Override
    public String getMessage()
    {
	return fErrors.getPrettyMessages();
    }

    public RecognitionErrorCollection getErrors()
    {
	return fErrors;
    }
}
