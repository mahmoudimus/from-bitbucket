package org.cocuyo.dsl.textgenerator.ast;

import org.cocuyo.dsl.protocol.ReflectionAPI;
import org.cocuyo.dsl.textgenerator.env.ExecutionEnvironment;
import org.cocuyo.parsing.RecognitionError;
import org.cocuyo.parsing.RecognitionException;

//close-imports
public class ObjectPropertyExistAndAccess extends Code
//open-inheritance//close-inheritance
{
	private Code code;
	private Id id;

	//open-fields//close-fields

	public ObjectPropertyExistAndAccess(Code code, Id id) {
		this.code = code;
		this.id = id;
	}

	//open-members

	@Override
	public Object execute(ExecutionEnvironment aEnv) {
		Object _obj = code.execute(aEnv);
		String _id = id.getText();

		try {
			Boolean _result = (Boolean) ReflectionAPI.getProperty(_obj, _id);
			return _result;
		} catch (ClassCastException _ex) {
			throw new RecognitionException(new RecognitionError(
					"Expecting boolean value for the property '" + _id
							+ "' of the object class '"
							+ _obj.getClass().getSimpleName() + "'", id
							.getToken()));
		} catch (Exception _e) {
			return false;
		}
	}

	//close-members
}