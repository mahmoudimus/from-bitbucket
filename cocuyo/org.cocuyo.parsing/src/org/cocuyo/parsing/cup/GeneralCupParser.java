package org.cocuyo.parsing.cup;

import java.io.IOException;

import org.cocuyo.parsing.ASTBuilder;
import org.cocuyo.parsing.IParser;
import org.cocuyo.parsing.IParserListener;
import org.cocuyo.parsing.RecognitionErrorCollection;

public class GeneralCupParser<TASTRoot> implements IParser
{
    private final IParser fParser;
    private final CupParserToASTBuilderEventsMapper fMapper;

    public GeneralCupParser(IParser aParser, ASTBuilder aASTBuilder)
    {
	fParser = aParser;
	fMapper = new CupParserToASTBuilderEventsMapper(aASTBuilder);
	fParser.addListener(fMapper);
    }

    public void setDebug(boolean aDebug)
    {
	fMapper.setDebug(aDebug);
    }

    public boolean isDebug()
    {
	return fMapper.isDebug();
    }

    @Override
    public void addListener(IParserListener aListener)
    {
	fParser.addListener(aListener);
    }

    @Override
    public RecognitionErrorCollection getErrors()
    {
	return fParser.getErrors();
    }

    @Override
    public boolean hasErrors()
    {
	return fParser.hasErrors();
    }

    @Override
    public void parseFile(String aPath) throws IOException
    {
	//fMapper.getBuilder().reset();
	fParser.parseFile(aPath);
    }

    @Override
    public void parseSource(String aSource)
    {
	//fMapper.getBuilder().reset();
	fParser.parseSource(aSource);
    }

    public TASTRoot getAST()
    {
	return (TASTRoot) getMapper().getAST();
    }

    public CupParserToASTBuilderEventsMapper getMapper()
    {
	return fMapper;
    }

    public ASTBuilder getASTBuilder()
    {
	return getMapper().getBuilder();
    }
}
