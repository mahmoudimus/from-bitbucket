package org.cocuyo.languages.textgenerator;

import cocuyo.parsing.cup.*;
import cocuyo.parsing.*;
/*open-imports*//*close-imports*/


%%

%class TextGeneratorGrammarJFlexLexer
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

/*open-other-macros*//*close-other-macros*/



%%

<YYINITIAL>
{
		"package"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.PACKAGE, yytext(), "", yyline, yycolumn, 0);
	}
		"import"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.IMPORT, yytext(), "", yyline, yycolumn, 0);
	}
		"gen"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.GEN, yytext(), "", yyline, yycolumn, 0);
	}
		"is"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.IS, yytext(), "", yyline, yycolumn, 0);
	}
		"end"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.END, yytext(), "", yyline, yycolumn, 0);
	}
		"."
	
	{
		return new CupToken(TextGeneratorGrammarSymbol._PTO, yytext(), "", yyline, yycolumn, 0);
	}
		"as"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.AS, yytext(), "", yyline, yycolumn, 0);
	}
		"on"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.ON, yytext(), "", yyline, yycolumn, 0);
	}
		"do"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.DO, yytext(), "", yyline, yycolumn, 0);
	}
		"fun"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.FUN, yytext(), "", yyline, yycolumn, 0);
	}
		"("
	
	{
		return new CupToken(TextGeneratorGrammarSymbol._LPAR, yytext(), "", yyline, yycolumn, 0);
	}
		")"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol._RPAR, yytext(), "", yyline, yycolumn, 0);
	}
		"return"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.RETURN, yytext(), "", yyline, yycolumn, 0);
	}
		"="
	
	{
		return new CupToken(TextGeneratorGrammarSymbol._EQUAL, yytext(), "", yyline, yycolumn, 0);
	}
		","
	
	{
		return new CupToken(TextGeneratorGrammarSymbol._COMMA, yytext(), "", yyline, yycolumn, 0);
	}
		"{"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol._LKEY, yytext(), "", yyline, yycolumn, 0);
	}
		"}"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol._RKEY, yytext(), "", yyline, yycolumn, 0);
	}
		"$"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol._MONEY, yytext(), "", yyline, yycolumn, 0);
	}
		"-"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol._MINUS, yytext(), "", yyline, yycolumn, 0);
	}
		"indent"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.INDENT, yytext(), "", yyline, yycolumn, 0);
	}
		"super"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.SUPER, yytext(), "", yyline, yycolumn, 0);
	}
		"?"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol._QUERY, yytext(), "", yyline, yycolumn, 0);
	}
	/*open-state-def-STRING_LITERAL*/
	
	"\"" [^"\""]* "\"" 
	|	"\'" [^"\'"]* "\'"
	|	">" [^\n]* \n
	
	/*close-state-def-STRING_LITERAL*/
	{
		return new CupToken(TextGeneratorGrammarSymbol.STRING_LITERAL, yytext(), "", yyline, yycolumn, 0);
	}
	/*open-state-def-NUMBER_LITERAL*/
	[1-9][0-9]*
	/*close-state-def-NUMBER_LITERAL*/
	{
		return new CupToken(TextGeneratorGrammarSymbol.NUMBER_LITERAL, yytext(), "", yyline, yycolumn, 0);
	}
		"true"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.TRUE, yytext(), "", yyline, yycolumn, 0);
	}
		"false"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.FALSE, yytext(), "", yyline, yycolumn, 0);
	}
		"/"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol._SLASH, yytext(), "", yyline, yycolumn, 0);
	}
		"in"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.IN, yytext(), "", yyline, yycolumn, 0);
	}
		"write"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.WRITE, yytext(), "", yyline, yycolumn, 0);
	}
		"aspects"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.ASPECTS, yytext(), "", yyline, yycolumn, 0);
	}
		"listen"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.LISTEN, yytext(), "", yyline, yycolumn, 0);
	}
		"get"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.GET, yytext(), "", yyline, yycolumn, 0);
	}
		".."
	
	{
		return new CupToken(TextGeneratorGrammarSymbol._PTO_PTO, yytext(), "", yyline, yycolumn, 0);
	}
		"or"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.OR, yytext(), "", yyline, yycolumn, 0);
	}
		"if"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.IF, yytext(), "", yyline, yycolumn, 0);
	}
		"else"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.ELSE, yytext(), "", yyline, yycolumn, 0);
	}
		"elif"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.ELIF, yytext(), "", yyline, yycolumn, 0);
	}
		"not"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.NOT, yytext(), "", yyline, yycolumn, 0);
	}
		"for"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.FOR, yytext(), "", yyline, yycolumn, 0);
	}
		"sep"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol.SEP, yytext(), "", yyline, yycolumn, 0);
	}
		":"
	
	{
		return new CupToken(TextGeneratorGrammarSymbol._DPTO, yytext(), "", yyline, yycolumn, 0);
	}
	/*open-state-def-ID*/
	[:jletter:][:jletterdigit:]*
	| 	"`" [:jletter:][:jletterdigit:]* "`"
	/*close-state-def-ID*/
	{
		return new CupToken(TextGeneratorGrammarSymbol.ID, yytext(), "", yyline, yycolumn, 0);
	}
}

/*open-common-states*/
"#" [^\n]* \n
{}
 
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
	return new CupToken(TextGeneratorGrammarSymbol.EOF, yytext(), "", yyline, yycolumn, 0, true);
}