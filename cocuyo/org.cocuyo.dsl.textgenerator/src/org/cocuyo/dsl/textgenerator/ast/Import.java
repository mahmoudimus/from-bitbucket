package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.dsl.protocol.IObjectPackageMember;
import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.dsl.protocol.ObjectPackage;
import org.cocuyo.dsl.textgenerator.env.ImportsEnvironment;
import org.cocuyo.dsl.textgenerator.env.TextGeneratorEnv;
import org.cocuyo.parsing.RecognitionError;
import org.cocuyo.parsing.RecognitionException;

//close-imports
public class Import extends UnitElement
//open-inheritance//close-inheritance
{
	private Name name;
	//open-fields

	private ObjectPackage fPackage;

	//close-fields

	public Import(Name name) {
		this.name = name;
	}

	//open-members
	@Override
	public void buildTypeLevel(TextGeneratorEnv aEnv) {
		fPackage = aEnv.getPackage();
	}

	@Override
	public void buildInstrLevel(ImportsEnvironment aEnv) {
		try {
			IObjectPackageMember _member = fPackage.getRoot().find(
					name.toArray());

			aEnv.addPackage((ObjectPackage) _member);

		} catch (NameNotFoundException _e) {
			throw new RecognitionException(new RecognitionError("Package '"
					+ _e.getName() + "' not found", name.getToken()));
		}
	}
	//close-members
}