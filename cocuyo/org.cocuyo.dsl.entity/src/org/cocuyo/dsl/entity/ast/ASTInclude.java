package org.cocuyo.dsl.entity.ast;

//open-imports
import org.cocuyo.dsl.entity.DomainModel;
import org.cocuyo.dsl.protocol.INamed;
import org.cocuyo.dsl.protocol.InvalidIncludeException;
import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.parsing.RecognitionError;
import org.cocuyo.parsing.RecognitionException;

//close-imports
public class ASTInclude implements IASTRoot
//open-inheritance
//close-inheritance
{
	
	private ASTName fName;
	
	public ASTInclude(ASTName aName)
	{
		this.fName = aName;
	}
	
	//open-members

	public void build(DomainModel aModel) {
		try {
			INamed _object = aModel.getContainer().findObject(fName.getIds());
			aModel.includeObject(_object);
		} catch (NameNotFoundException _e) {
			throw new RecognitionException(new RecognitionError(
					_e.getMessage(), fName.get(0).getToken()));
		} catch (InvalidIncludeException _e) {
			throw new RecognitionException(new RecognitionError(
					_e.getMessage(), fName.get(0).getToken()));
		}
	}

	//close-members
	
}