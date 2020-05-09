package org.cocuyo.dsl.textgenerator.ast;

import java.util.ArrayList;

import org.cocuyo.dsl.protocol.IObjectPackageMember;
import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.dsl.textgenerator.TextGeneratorType;
import org.cocuyo.dsl.textgenerator.code.ListCode;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;
import org.cocuyo.parsing.RecognitionError;
import org.cocuyo.parsing.RecognitionException;

//close-imports
public class AspectListeners extends Code
// open-inheritance//close-inheritance
{
	private ArrayList<Name> namelist;
	private ArrayList<Code> codelist;
	// open-fields

	ArrayList<TextGeneratorType> fTypes;

	// close-fields

	public AspectListeners(ArrayList<Name> namelist, ArrayList<Code> codelist) {
		this.namelist = namelist;
		this.codelist = codelist;
	}

	// open-members

	@Override
	public Object execute(ExecutionEnvironment aEnv) {

		ExecutionEnvironment _env = computeAspects(aEnv);

		ListCode _list = new ListCode();

		for (Code _code : codelist) {
			_list.addObject(_code.execute(_env));
		}

		return _list;
	}

	private ExecutionEnvironment computeAspects(ExecutionEnvironment aEnv) {
		ExecutionEnvironment _env = new ExecutionEnvironment(aEnv);

		if (fTypes == null) {
			fTypes = new ArrayList<TextGeneratorType>();
			for (Name _name : namelist) {
				try {
					IObjectPackageMember _member = aEnv.getStaticType()
							.findPackageMember(_name.toArray());

					if (_member instanceof TextGeneratorType) {
						TextGeneratorType _type = (TextGeneratorType) _member;
						fTypes.add(_type);
					}
				} catch (NameNotFoundException _ex) {
					throw new RecognitionException(new RecognitionError(
							"Object '" + _ex.getName() + "' not found", _name
									.getToken()));
				}
			}
		}
		for (TextGeneratorType _type : fTypes) {
			_env.registerAspectsOf(_type);
		}

		return _env;
	}
	// close-members
}