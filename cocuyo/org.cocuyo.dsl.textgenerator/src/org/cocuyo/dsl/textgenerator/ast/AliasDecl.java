package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.dsl.textgenerator.TextGeneratorType;
import org.cocuyo.parsing.RecognitionError;
import org.cocuyo.parsing.RecognitionException;

//close-imports
public class AliasDecl extends GeneratorMember
// open-inheritance//close-inheritance
{
	private Id type;
	private Id member;
	private Id alias;
	// open-fields

	private TextGeneratorType fType;

	// close-fields

	public AliasDecl(Id type, Id member, Id alias)
	{
		this.type = type;
		this.member = member;
		this.alias = alias;
	}

	// open-members

	@Override
	public void buildTypeLevel(TextGeneratorType aType)
	{
		fType = aType;
	}

	@Override
	public void buildInstrLevel()
	{
		try
		{
			fType
					.defineAlias(type.getText(), member.getText(), alias
							.getText());
		} catch (NameNotFoundException _e)
		{
			throw new RecognitionException(new RecognitionError(
					_e.getMessage(), type.getToken()));
		}
	}

	// close-members
}