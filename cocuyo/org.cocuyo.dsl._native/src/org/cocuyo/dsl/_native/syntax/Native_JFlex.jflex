package org.cocuyo.dsl._native.syntax;

import org.cocuyo.parsing.cup.*;
import org.cocuyo.parsing.*;
/*open-imports *//*close-imports */


%%

%class NativeJFlexLexer
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


%state VERBATIM

%%

<YYINITIAL>
{
	/*open-state-def- . */
		"."
	/*close-state-def- . */
	{
		return new CupToken(NativeSymbol.LIT_3, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- is */
		"is"
	/*close-state-def- is */
	{
		yybegin(VERBATIM);
		return new CupToken(NativeSymbol.LIT_2, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- native */
		"native"
	/*close-state-def- native */
	{
		return new CupToken(NativeSymbol.LIT_1, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- package */
		"package"
	/*close-state-def- package */
	{
		return new CupToken(NativeSymbol.LIT_0, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- text */
		"\"" [^"\""]* "\"" 
	/*close-state-def- text */
	{
		return new CupToken(NativeSymbol.TEXT, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- id */
		[:jletter:][:jletterdigit:]*
	/*close-state-def- id */
	{
		return new CupToken(NativeSymbol.ID, yytext(), "", yyline, yycolumn, 0);
	}
	
}
<VERBATIM>
{
	(.|\r|\n)*
	{
		return new CupToken(NativeSymbol.TEXT, yytext(), "", yyline, yycolumn, 0);
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
	return new CupToken(NativeSymbol.EOF, yytext(), "", yyline, yycolumn, 0, true);
}