package org.cocuyo.dsl.lexer.ast;

//open-imports
import org.cocuyo.dsl.lexer.LexerDefinition;
import org.cocuyo.dsl.protocol.ObjectPackage;
import org.cocuyo.parsing.IToken;

@SuppressWarnings("unused")
//close-imports
public class ASTLexerDef implements IASTRoot
//open-inheritance
//close-inheritance
{

	private IToken id;
	private ASTIncludeList includelist;
	private ASTRegexList regexlist;
	private ASTTransitionSetList transitionsetlist;

	public ASTLexerDef(IToken id, ASTIncludeList includelist,
			ASTRegexList regexlist, ASTTransitionSetList transitionsetlist) {
		this.id = id;
		this.includelist = includelist;
		this.regexlist = regexlist;
		this.transitionsetlist = transitionsetlist;
	}

	//open-members
	public void build(ObjectPackage aPkg) {
		LexerDefinition _lexer = new LexerDefinition(this.id.getText(), aPkg);
		aPkg.define(_lexer);

		LexerASTEnv _env = new LexerASTEnv(_lexer);

		for (ASTRegex _regex : this.regexlist) {
			_regex.build(_env);
		}

		for (ASTTransitionSet _tranSet : this.transitionsetlist) {
			_tranSet.build(_env);
		}
	}
	//close-members

}