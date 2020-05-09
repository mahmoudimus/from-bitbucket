package org.cocuyo.dsl.textgenerator.code;

import java.io.File;

public class FileSeparatorCode extends BaseCode
{
    public static FileSeparatorCode PATH_SEPARATOR = new FileSeparatorCode();

    private FileSeparatorCode()
    {
    }

    @Override
    public String getText(int aIndentation)
    {
	return File.separator;
    }

}
