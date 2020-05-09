package org.cocuyo.languages.grammar;

import org.cocuyo.parsing.cup.*;
import org.cocuyo.parsing.*;
/*open-imports*//*close-imports*/


%%

%class CocuyoGrammarJFlexLexer
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
	/*open-other-methods*//*close-other-methods*/
%}

/*open-common-macros*/
WHITE_SPACE = [\ \b\012]
NEW_LINE = \r|\n|\r\n
TAB = \t
/*close-common-macros*/

/*open-other-macros*/

DIGIT = [0-9]

/*close-other-macros*/



%%

<YYINITIAL>
{
		"package"
	
	{
		return new CupToken(CocuyoGrammarSymbol.PACKAGE, yytext(), "", yyline, yycolumn, 0);
	}
		"import"
	
	{
		return new CupToken(CocuyoGrammarSymbol.IMPORT, yytext(), "", yyline, yycolumn, 0);
	}
		"."
	
	{
		return new CupToken(CocuyoGrammarSymbol._PTO, yytext(), "", yyline, yycolumn, 0);
	}
		"@"
	
	{
		return new CupToken(CocuyoGrammarSymbol._AT, yytext(), "", yyline, yycolumn, 0);
	}
		"("
	
	{
		return new CupToken(CocuyoGrammarSymbol._LPAR, yytext(), "", yyline, yycolumn, 0);
	}
		")"
	
	{
		return new CupToken(CocuyoGrammarSymbol._RPAR, yytext(), "", yyline, yycolumn, 0);
	}
		"="
	
	{
		return new CupToken(CocuyoGrammarSymbol._EQUAL, yytext(), "", yyline, yycolumn, 0);
	}
		"{"
	
	{
		return new CupToken(CocuyoGrammarSymbol._LKEY, yytext(), "", yyline, yycolumn, 0);
	}
		"}"
	
	{
		return new CupToken(CocuyoGrammarSymbol._RKEY, yytext(), "", yyline, yycolumn, 0);
	}
		"!"
	
	{
		return new CupToken(CocuyoGrammarSymbol._EXCLAM, yytext(), "", yyline, yycolumn, 0);
	}
	/*open-state-def-NUMBER_LITERAL*/
	[1-9][0-9]*
	/*close-state-def-NUMBER_LITERAL*/
	{
		return new CupToken(CocuyoGrammarSymbol.NUMBER_LITERAL, yytext(), "", yyline, yycolumn, 0);
	}
	/*open-state-def-STRING_LITERAL*/
	"\"" [^"\""]* "\"" 
	|	"\'" [^"\'"]* "\'"
	/*close-state-def-STRING_LITERAL*/
	{
		return new CupToken(CocuyoGrammarSymbol.STRING_LITERAL, yytext(), "", yyline, yycolumn, 0);
	}
		"["
	
	{
		return new CupToken(CocuyoGrammarSymbol._LCOR, yytext(), "", yyline, yycolumn, 0);
	}
		"]"
	
	{
		return new CupToken(CocuyoGrammarSymbol._RCOR, yytext(), "", yyline, yycolumn, 0);
	}
		":"
	
	{
		return new CupToken(CocuyoGrammarSymbol._DPTO, yytext(), "", yyline, yycolumn, 0);
	}
		"grammar"
	
	{
		return new CupToken(CocuyoGrammarSymbol.GRAMMAR, yytext(), "", yyline, yycolumn, 0);
	}
		"end"
	
	{
		return new CupToken(CocuyoGrammarSymbol.END, yytext(), "", yyline, yycolumn, 0);
	}
		"->"
	
	{
		return new CupToken(CocuyoGrammarSymbol._MINUS_RANG, yytext(), "", yyline, yycolumn, 0);
	}
		";"
	
	{
		return new CupToken(CocuyoGrammarSymbol._PTOCOMMA, yytext(), "", yyline, yycolumn, 0);
	}
		"|"
	
	{
		return new CupToken(CocuyoGrammarSymbol._OR, yytext(), "", yyline, yycolumn, 0);
	}
		"=>"
	
	{
		return new CupToken(CocuyoGrammarSymbol._EQUAL_RANG, yytext(), "", yyline, yycolumn, 0);
	}
		"+"
	
	{
		return new CupToken(CocuyoGrammarSymbol._ADD, yytext(), "", yyline, yycolumn, 0);
	}
		"*"
	
	{
		return new CupToken(CocuyoGrammarSymbol._MUL, yytext(), "", yyline, yycolumn, 0);
	}
		"?"
	
	{
		return new CupToken(CocuyoGrammarSymbol._QUERY, yytext(), "", yyline, yycolumn, 0);
	}
		"-"
	
	{
		return new CupToken(CocuyoGrammarSymbol._MINUS, yytext(), "", yyline, yycolumn, 0);
	}
	/*open-state-def-ID*/
	[:jletter:][:jletterdigit:]*
	| 	"`" [:jletter:][:jletterdigit:]* "`"
	/*close-state-def-ID*/
	{
		return new CupToken(CocuyoGrammarSymbol.ID, yytext(), "", yyline, yycolumn, 0);
	}
}

/*open-common-states*/
{TAB}
{
	yycolumn = yycolumn - yycolumn%4 + 3;
}
{NEW_LINE} {}
{WHITE_SPACE}+ {}
.
{
	CupToken _token = new CupToken(-1, yytext(), "",yyline, yycolumn, yychar);
	_token.isBadToken(true);
	return _token;
}
/*close-common-states*/

<<EOF>>
{
	return new CupToken(CocuyoGrammarSymbol.EOF, yytext(), "", yyline, yycolumn, 0, true);
}