package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.dsl.textgenerator.TextGeneratorProperty;
import org.cocuyo.dsl.textgenerator.TextGeneratorType;

//close-imports
public class PropertyDecl extends GeneratorMember
// open-inheritance//close-inheritance
{
	private Id id;
	private Code code;

	// open-fields//close-fields

	public PropertyDecl(Id id, Code code)
	{
		this.id = id;
		this.code = code;
	}

	// open-members

	@Override
	public void buildTypeLevel(TextGeneratorType aType)
	{
		TextGeneratorProperty _property = new TextGeneratorProperty(id
				.getText(), code);
		aType.defineFunction(_property);
	}

	@Override
	public void buildInstrLevel()
	{

	}
	// close-members
}