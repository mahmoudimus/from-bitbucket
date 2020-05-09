package org.cocuyo.dsl.textgenerator.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.protocol.IObjectPackageMember;
import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.dsl.textgenerator.TextGeneratorType;
import org.cocuyo.dsl.textgenerator.env.ImportsEnvironment;
import org.cocuyo.dsl.textgenerator.env.TextGeneratorEnv;
import org.cocuyo.parsing.RecognitionError;
import org.cocuyo.parsing.RecognitionException;

//close-imports
public class Generator extends UnitElement
// open-inheritance//close-inheritance
{
	private Id name;
	private ArrayList<Name> supers;
	private ArrayList<GeneratorMember> members;
	// open-fields

	TextGeneratorType fGenType;
	TextGeneratorEnv fEnv;

	// close-fields

	public Generator(Id name, ArrayList<Name> supers,
			ArrayList<GeneratorMember> members) {
		this.name = name;
		this.supers = supers;
		this.members = members;
	}

	public Generator(Id name, ArrayList<GeneratorMember> members) {
		this.name = name;
		this.members = members;
	}

	// open-members

	@Override
	public void buildTypeLevel(TextGeneratorEnv aEnv) {
		fEnv = aEnv;

		fGenType = new TextGeneratorType(name.getText(), aEnv.getPackage());

		aEnv.getPackage().define(fGenType);

		for (GeneratorMember _member : members) {
			_member.buildTypeLevel(fGenType);
		}
	}

	@Override
	public void buildInstrLevel(ImportsEnvironment aEnv) {
		fGenType.setImportEnv(aEnv);

		if (supers != null) {
			try {

				for (Name _super : supers) {
					String[] _name = _super.toArray();
					try {
						IObjectPackageMember _member = fGenType
								.findPackageMember(_name);
						/*
						 * IPackageMember _member =
						 * fGenType.getPackageContainer() .findMember(_name);
						 */

						if (_member instanceof TextGeneratorType) {
							TextGeneratorType _type = (TextGeneratorType) _member;
							fGenType.addSuper(_type);
						}
					} catch (NameNotFoundException _e) {
						throw new RecognitionException(new RecognitionError(
								"Generator '" + _e.getName() + "' not found"));
					}
				}
			} catch (RecognitionException _ex) {
				throw new RecognitionException(new RecognitionError(_ex
						.getMessage(), name.getToken()));
			}
		}
		for (GeneratorMember _member : members) {
			_member.buildInstrLevel();
		}
	}
	// close-members
}