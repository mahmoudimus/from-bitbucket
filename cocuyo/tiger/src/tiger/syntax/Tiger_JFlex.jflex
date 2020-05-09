package tiger.syntax;

import org.cocuyo.parsing.cup.*;
import org.cocuyo.parsing.*;
/*open-imports *//*close-imports */


%%

%class TigerJFlexLexer
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
	/*open-state-def- ; */
		";"
	/*close-state-def- ; */
	{
		return new CupToken(TigerSymbol.LIT_12, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- ) */
		")"
	/*close-state-def- ) */
	{
		return new CupToken(TigerSymbol.LIT_11, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- ( */
		"("
	/*close-state-def- ( */
	{
		return new CupToken(TigerSymbol.LIT_10, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- ] */
		"]"
	/*close-state-def- ] */
	{
		return new CupToken(TigerSymbol.LIT_9, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- [ */
		"["
	/*close-state-def- [ */
	{
		return new CupToken(TigerSymbol.LIT_8, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- . */
		"."
	/*close-state-def- . */
	{
		return new CupToken(TigerSymbol.LIT_7, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- / */
		"/"
	/*close-state-def- / */
	{
		return new CupToken(TigerSymbol.LIT_6, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- * */
		"*"
	/*close-state-def- * */
	{
		return new CupToken(TigerSymbol.LIT_5, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- - */
		"-"
	/*close-state-def- - */
	{
		return new CupToken(TigerSymbol.LIT_4, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- + */
		"+"
	/*close-state-def- + */
	{
		return new CupToken(TigerSymbol.LIT_3, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- & */
		"&"
	/*close-state-def- & */
	{
		return new CupToken(TigerSymbol.LIT_2, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- | */
		"|"
	/*close-state-def- | */
	{
		return new CupToken(TigerSymbol.LIT_1, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- = */
		"="
	/*close-state-def- = */
	{
		return new CupToken(TigerSymbol.LIT_0, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- number */
		[1-9][0-9]*
	/*close-state-def- number */
	{
		return new CupToken(TigerSymbol.NUMBER, yytext(), "", yyline, yycolumn, 0);
	}
	
	/*open-state-def- ident */
		[a-zA-Z_]+
	/*close-state-def- ident */
	{
		return new CupToken(TigerSymbol.IDENT, yytext(), "", yyline, yycolumn, 0);
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
	return new CupToken(TigerSymbol.EOF, yytext(), "", yyline, yycolumn, 0, true);
}