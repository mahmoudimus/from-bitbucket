package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.dsl.protocol.ObjectPackage;
import org.cocuyo.dsl.textgenerator.TextGeneratorFunc;
import org.cocuyo.dsl.textgenerator.TextGeneratorType;
import org.cocuyo.parsing.RecognitionError;
import org.cocuyo.parsing.RecognitionException;

//close-imports
public class AspectDecl extends GeneratorMember
// open-inheritance//close-inheritance
{
	private Name pointcut;
	private Id newfunc;
	// open-fields
	private TextGeneratorType fType;

	// close-fields

	public AspectDecl(Name pointcut, Id newfunc) {
		this.pointcut = pointcut;
		this.newfunc = newfunc;
	}

	// open-members

	@Override
	public void buildTypeLevel(TextGeneratorType aType) {
		fType = aType;
	}

	@Override
	public void buildInstrLevel() {
		String[] _name = pointcut.toArray();
		Object _obj = null;

		try {
			String _id = null;

			for (int _i = 0; _i < _name.length; _i++) {
				_id = _name[_i];

				if (_obj == null) {
					_obj = fType.findPackageMember(_id);
				} else {
					if (_obj instanceof ObjectPackage) {
						ObjectPackage _pkg = (ObjectPackage) _obj;
						_obj = _pkg.find(_id);
					} else {
						TextGeneratorType _gen = (TextGeneratorType) _obj;
						_obj = _gen.findFunction(_id);
					}

				}
			}

			try {
				TextGeneratorFunc _oldFunc = (TextGeneratorFunc) _obj;
				try {
					TextGeneratorFunc _newFunc = fType.findFunction(newfunc
							.getText());

					fType.defineAspect(_oldFunc, _newFunc);
				} catch (NameNotFoundException _ex) {
					throw new RecognitionException(new RecognitionError(
							"The generator '" + fType.getName()
									+ "' do not contain definition for '"
									+ newfunc.getText() + "'", newfunc
									.getToken()));
				}
			} catch (ClassCastException _ex) {
				throw new RecognitionException(new RecognitionError(
						"Invalid member '" + _id
								+ "', expecting a function aspect", pointcut
								.getToken()));

			}

		} catch (NameNotFoundException _ex) {
			throw new RecognitionException(new RecognitionError(_ex
					.getMessage(), pointcut.getToken()));
		}

	}
	// close-members
}