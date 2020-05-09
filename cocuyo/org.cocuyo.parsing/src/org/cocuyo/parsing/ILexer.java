package org.cocuyo.parsing;

import java.io.FileNotFoundException;

public interface ILexer
{
    public void setInputFile(String aFilePath) throws FileNotFoundException;

    public void setInputSource(String aSource);

    public String getInputFilePath();

    public String getInputSource();
}
