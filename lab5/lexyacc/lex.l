%option noyywrap
%option caseless

IDENTIFIER (_|[a-zA-Z])[a-zA-Z0-9_]*
NUMERIC_CONSTANT [-]?[1-9][0-9]*|0
CHAR_CONSTANT '[0-9a-zA-Z]'

%{
#include <stdio.h>
#include "y.tab.h"
%}

%%

[ \t\n]+ ;
"==" { printf("EQ");
       return EQ;  }
"!=" { return NE;  }
"<" { return LT;  }
"<=" { return LE;  }
">" { return GT; }
">=" { return GE; }
"+" { return PLUS; }
"-" { return MINUS; }
"*" { return MULT; }
"/" { return DIVIDE; }
"%" { return MODULO; }
")" { return RROUNDPAREN; }
"(" { return LROUNDPAREN; }
"]" { return RSQUAREPAREN; }
"[" { return LSQUAREPAREN; }
"}" { return RBRACKET; }
"{" { return LBRACKET; }
"=" { return ASSIGN; }
";" { return SEMICOLON; }
"BEGIN" { return BEGINTOK; }
"END" { return ENDTOK; }
"INT" { return INT; }
"CHAR" { return CHAR; }
"IF" { return IF; }
"ELSE" { return ELSE; }
"WHILE"  { return WHILE; }
"READ"  { return READ; }
"WRITE" { return WRITE; }
{IDENTIFIER} { return IDENTIFIER; }
{NUMERIC_CONSTANT} { return NUMERIC_CONSTANT; }
{CHAR_CONSTANT} { return CHAR_CONSTANT; }
. { printf("error %s ", yytext); }

%%
