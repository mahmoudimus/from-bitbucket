package org.cocuyo.dsl.textgenerator.code;

import java.util.ArrayList;

public interface ICode
{
    public String getText(int aIndentation);

    public ICode getUpperCase();

    public ICode getLowerCase();

    public void addFileWriteCodeTo(ArrayList<FileCode> aUnits);

    public void merge(String aContent);
}
