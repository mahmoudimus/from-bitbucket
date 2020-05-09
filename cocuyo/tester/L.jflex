%%
%public
%class LexerEditorLexer
%implements IEditorLexer
%unicode
%pack
%buffer 128
%type List


%state STATE_NAT1 STATE_START STATE_NAT0

//open-macros
WHITE_SPACE = [\ \b\012]
NEW_LINE = \r|\n|\r\n
TAB = \t
//close-macros

<STATE_START> 
"lexer"
{
	//open-start-"lexer"-start
	//close-start-"lexer"-start
	yybenin(STATE_START);
	return yieldToken(LEXER);
}

<STATE_START> 
"package"
{
	//open-start-"package"-start
	//close-start-"package"-start
	yybenin(STATE_START);
	return yieldToken(PACKAGE);
}

<STATE_START> 
"include"
{
	//open-start-"include"-start
	//close-start-"include"-start
	yybenin(STATE_START);
	return yieldToken(INCLUDE);
}

<STATE_START> 
 [:jletter][:jletterdigit]*
{
	//open-start- [:jletter][:jletterdigit]*-start
	//close-start- [:jletter][:jletterdigit]*-start
	yybenin(STATE_START);
	return yieldToken(ID);
}

<STATE_START> 
"?="
{
	//open-start-"?="-nat0
	//close-start-"?="-nat0
	yybenin(STATE_NAT0);
	return yieldToken(NATIVE_ASSIGN);
}

<STATE_NAT0> 
 [:jletter][:jletterdigit]*
{
	//open-nat0- [:jletter][:jletterdigit]*-nat1
	//close-nat0- [:jletter][:jletterdigit]*-nat1
	yybenin(STATE_NAT1);
	return yieldToken(ID);
}

<STATE_NAT1> 
 [^"\n"]*
{
	//open-nat1- [^"\n"]*-start
	//close-nat1- [^"\n"]*-start
	yybenin(STATE_START);
	return yieldToken(LINE);
}