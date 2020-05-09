package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.dsl.protocol.INamed;
import org.cocuyo.dsl.protocol.IObjectContainer;
import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.dsl.protocol.ObjectProtocolException;
import org.cocuyo.dsl.protocol.ReflectionAPI;
import org.cocuyo.dsl.textgenerator.TextGeneratorType;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;
import org.cocuyo.parsing.RecognitionError;
import org.cocuyo.parsing.RecognitionException;

//close-imports
public class ObjectPropertyAccess extends Code
// open-inheritance//close-inheritance
{
	private Code code;
	private Id id;

	// open-fields//close-fields

	public ObjectPropertyAccess(Code code, Id id) {
		this.code = code;
		this.id = id;
	}

	// open-members
	@Override
	public Object execute(ExecutionEnvironment aEnv) {
		Object _obj = code.execute(aEnv);
		String _id = id.getText();

		if (_obj instanceof TextGeneratorType) {
			TextGeneratorType _type = (TextGeneratorType) _obj;
			try {
				aEnv = new ExecutionEnvironment(aEnv);
				aEnv.setInstance(_type.getInstance());

				return _type.executeFunction(_id, aEnv);
			} catch (NameNotFoundException _ex) {
				throw new RecognitionException(new RecognitionError(
						"The generator '" + _type.getName()
								+ "' not contain a definition for '" + _id
								+ "'", id.getToken()));

			}
		} else if (_obj instanceof IObjectContainer) {
			IObjectContainer<INamed> _container = (IObjectContainer<INamed>) _obj;
			try {
				if (_container.contain(_id))
					return _container.findDefined(_id);
			} catch (NameNotFoundException _ex) {

				/*throw new RecognitionException(new RecognitionError(
						"The object container "
								+ (_container instanceof INamed ? "'"
										+ ((INamed) _container).getName() + "'"
										: "of class '"
												+ _container.getClass()
														.getName() + "'")
								+ " not contain the object '" + _id + "'", id
								.getToken()));*/
			}
		}

		try {
			return ReflectionAPI.getProperty(_obj, _id);
		} catch (ObjectProtocolException _e) {
			throw new RecognitionException(new RecognitionError(
					_e.getMessage(), id.getToken()));
		}

	}
	// close-members
}