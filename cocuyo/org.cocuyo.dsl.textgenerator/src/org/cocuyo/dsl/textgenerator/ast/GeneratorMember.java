package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.dsl.textgenerator.TextGeneratorType;

//close-imports

public abstract class GeneratorMember  
//open-inheritance//close-inheritance

{
	//open-members

    public abstract void buildTypeLevel(TextGeneratorType aType);

    public abstract void buildInstrLevel();

    //close-members
}