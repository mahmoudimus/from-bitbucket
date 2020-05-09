package org.cocuyo.dsl.grammar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import org.cocuyo.parsing.RecognitionError;
import org.cocuyo.parsing.RecognitionException;
import org.cocuyo.parsing.cup.CupToken;
import org.cocuyo.parsing.cup.ICupLexer;

//close-imports

public class CocuyoGrammarLexer extends CocuyoGrammarJFlexLexer implements ICupLexer
{
	private File fFile;
	private String fSource;
	
	public CocuyoGrammarLexer()
	{
		this("");
	}
	
	public CocuyoGrammarLexer(String aSource)
	{
		super(new StringReader(aSource));
		fSource = aSource;
		fFile = null;
	}
	
	public CocuyoGrammarLexer(File aFile) throws FileNotFoundException
	{
		super(new FileInputStream(aFile));
		fFile = aFile;
		fSource = null;
	}
	
	@Override
	public CupToken next_token() throws IOException
	{
		CupToken _token = super.next_token();
		_token.setFilePath(getInputFilePath());
		if (_token.isBadToken())
		{
			throw new RecognitionException(new RecognitionError("Invalid char '"+ _token.getText() +"'", _token));
			
		}
		return _token;
	}
	
	public void setInputSource(String aSource)
	{
		fSource = aSource;fFile = null;
		yyreset(new StringReader(aSource));
	}
	
	public void setInputFile(String aFilePath) throws FileNotFoundException
	{
		fSource = null;
		fFile = new File(aFilePath);
		yyreset(new FileReader(aFilePath));
	}
	
	public String getInputFilePath()
	{
		return fFile == null? "" : fFile.getPath();
	}
	
	public File getInputFile()
	{
		return fFile;
	}
	
	public String getInputSource()
	{
		if (fSource != null)
		{
			try
			{
				FileInputStream _input = new FileInputStream(fFile);
				byte[] _buffer = new byte[(int) fFile.length()];
				_input.read(_buffer);
				fSource = new String(_buffer);
				
			}
			catch (Exception e)
			{
				throw new RuntimeException(e);
			}
			
		}
		return fSource;
	}
}

