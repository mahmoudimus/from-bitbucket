package org.cocuyo.parsing;

import java.io.IOException;

public interface IParser
{
    public void parseFile(String aPath) throws IOException;

    public void parseSource(String aSource);

    public void addListener(IParserListener aListener);

    public RecognitionErrorCollection getErrors();

    public boolean hasErrors();

}
