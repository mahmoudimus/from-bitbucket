package org.cocuyo.dsl.lexer.ast;

//open-imports
import org.cocuyo.dsl.lexer.RegularExpression;
import org.cocuyo.dsl.lexer.StringRegularExpression;
import org.cocuyo.dsl.lexer.syntax.LexerSymbol;
import org.cocuyo.dsl.protocol.NameNotFoundException;
import org.cocuyo.parsing.IToken;
import org.cocuyo.parsing.RecognitionError;
import org.cocuyo.parsing.RecognitionException;

//close-imports
public class ASTPattern implements IASTRoot
//open-inheritance
//close-inheritance
{
	
	private IToken item;
	
	public ASTPattern(IToken item)
	{
		this.item = item;
	}
	
	//open-members
	public RegularExpression build(LexerASTEnv aEnv)
	{
		try
		{
			return item.getID() == LexerSymbol.ID ? aEnv.getLexer()
					.findRegularExpression(item.getText())
					: new StringRegularExpression(item.getText());
		} catch (NameNotFoundException _e)
		{
			throw new RecognitionException(new RecognitionError(
					_e.getMessage(), item));
		}

	}

	//close-members
	
}