package org.cocuyo.dsl.lexer.syntax;

import org.cocuyo.parsing.cup.*;
import org.cocuyo.parsing.*;
/*open-imports *//*close-imports */


%%

%class LexerJFlexLexer
%line
%column
%char
%full
%type CupToken
%cup
%public


%{
	public boolean isAtEOF()
{
	return zzEOFDone;
}
/*open-other-methods *//*close-other-methods */
%}

/*open-common-macros */
WHITE_SPACE = [\ \b\012]
NEW_LINE = \r|\n|\r\n
TAB = \t
/*close-common-macros */

/*open-other-macros *//*close-other-macros */


%state LINE
%%

<YYINITIAL>
{
	/*open-state-def- , */
		","
	/*close-state-def- , */
	{
		return new CupToken(LexerSymbol.LIT_9, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- -> */
		"->"
	/*close-state-def- -> */
	{
		return new CupToken(LexerSymbol.LIT_8, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- yield */
		"yield"
	/*close-state-def- yield */
	{
		return new CupToken(LexerSymbol.LIT_7, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- on */
		"on"
	/*close-state-def- on */
	{
		return new CupToken(LexerSymbol.LIT_6, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- ?= */
		"="
	/*close-state-def- ?= */
	{
		yybegin(LINE);
		return new CupToken(LexerSymbol.LIT_5, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- from */
		"from"
	/*close-state-def- from */
	{
		return new CupToken(LexerSymbol.LIT_4, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- include */
		"include"
	/*close-state-def- include */
	{
		return new CupToken(LexerSymbol.LIT_3, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- end */
		"end"
	/*close-state-def- end */
	{
		return new CupToken(LexerSymbol.LIT_2, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- lexer */
		"lexer"
	/*close-state-def- lexer */
	{
		return new CupToken(LexerSymbol.LIT_1, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- package */
		"package"
	/*close-state-def- package */
	{
		return new CupToken(LexerSymbol.LIT_0, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- id */
		[:jletter:][:jletterdigit:]*
	/*close-state-def- id */
	{
		return new CupToken(LexerSymbol.ID, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- string_literal */
		"\"" [^"\""]* "\"" 
	|	"\'" [^"\'"]* "\'"
	/*close-state-def- string_literal */
	{
		return new CupToken(LexerSymbol.STRING_LITERAL, yytext(), "", yyline, yycolumn, 0);
	}
	
}
<LINE>
{
	[^"\n"]*
	{
		yybegin(YYINITIAL);
		return new CupToken(LexerSymbol.LINE, yytext().substring(0,yytext().length()-1), "", yyline, yycolumn, 0);
	}
}
/*open-common-states */
{TAB}
{
	/*open-tab-incr */yycolumn = yycolumn - yycolumn%4 + 3;/*close-tab-incr */
}
{NEW_LINE} {}
{WHITE_SPACE}+ {}
.
{
	CupToken _token = new CupToken(-1, yytext(), "",yyline, yycolumn, yychar);
	_token.isBadToken(true);
	return _token;
}
/*close-common-states */

<<EOF>>
{
	return new CupToken(LexerSymbol.EOF, yytext(), "", yyline, yycolumn, 0, true);
}