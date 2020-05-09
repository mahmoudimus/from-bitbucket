package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.dsl.textgenerator.env.ImportsEnvironment;
import org.cocuyo.dsl.textgenerator.env.TextGeneratorEnv;

//close-imports

public abstract class UnitElement  
//open-inheritance//close-inheritance

{
	//open-members
    public abstract void buildTypeLevel(TextGeneratorEnv aEnv);

    public abstract void buildInstrLevel(ImportsEnvironment aEnv);
    //close-members
}