package org.cocuyo.dsl.entity.syntax;

import org.cocuyo.parsing.cup.*;
import org.cocuyo.parsing.*;
/*open-imports *//*close-imports */


%%

%class EntityJFlexLexer
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



%%

<YYINITIAL>
{
	/*open-state-def- include */
		"include"
	/*close-state-def- include */
	{
		return new CupToken(EntitySymbol.LIT_15, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- . */
		"."
	/*close-state-def- . */
	{
		return new CupToken(EntitySymbol.LIT_14, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- type */
		"type"
	/*close-state-def- type */
	{
		return new CupToken(EntitySymbol.LIT_13, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- = */
		"="
	/*close-state-def- = */
	{
		return new CupToken(EntitySymbol.LIT_12, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- , */
		","
	/*close-state-def- , */
	{
		return new CupToken(EntitySymbol.LIT_11, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- [] */
		"[]"
	/*close-state-def- [] */
	{
		return new CupToken(EntitySymbol.LIT_10, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- ) */
		")"
	/*close-state-def- ) */
	{
		return new CupToken(EntitySymbol.LIT_9, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- ( */
		"("
	/*close-state-def- ( */
	{
		return new CupToken(EntitySymbol.LIT_8, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- : */
		":"
	/*close-state-def- : */
	{
		return new CupToken(EntitySymbol.LIT_7, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- * */
		"*"
	/*close-state-def- * */
	{
		return new CupToken(EntitySymbol.LIT_6, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- is */
		"is"
	/*close-state-def- is */
	{
		return new CupToken(EntitySymbol.LIT_5, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- entity */
		"entity"
	/*close-state-def- entity */
	{
		return new CupToken(EntitySymbol.LIT_4, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- end */
		"end"
	/*close-state-def- end */
	{
		return new CupToken(EntitySymbol.LIT_3, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- model */
		"model"
	/*close-state-def- model */
	{
		return new CupToken(EntitySymbol.LIT_2, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- import */
		"import"
	/*close-state-def- import */
	{
		return new CupToken(EntitySymbol.LIT_1, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- package */
		"package"
	/*close-state-def- package */
	{
		return new CupToken(EntitySymbol.LIT_0, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- string */
		"\"" [^"\""]* "\"" 	
	/*close-state-def- string */
	{
		return new CupToken(EntitySymbol.STRING, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- number */
		[1..9][0..9]*
	/*close-state-def- number */
	{
		return new CupToken(EntitySymbol.NUMBER, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- symbol */
		":" [:jletter:][:jletterdigit:]*
	/*close-state-def- symbol */
	{
		return new CupToken(EntitySymbol.SYMBOL, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- id */
		[:jletter:][:jletterdigit:]*
	/*close-state-def- id */
	{
		return new CupToken(EntitySymbol.ID, yytext(), "", yyline, yycolumn, 0);
	}
	
}

/*open-common-states */
"#" [^\n]* \n
{}

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
	return new CupToken(EntitySymbol.EOF, yytext(), "", yyline, yycolumn, 0, true);
}