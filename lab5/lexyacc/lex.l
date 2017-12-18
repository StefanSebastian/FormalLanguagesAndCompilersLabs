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
"!=" { printf("NE"); return NE;  }
"<" { printf("LT"); return LT;  }
"<=" { printf("LE"); return LE;  }
">" { printf("GT"); return GT; }
">=" { printf("GE"); return GE; }
"+" { printf("PLUS"); return PLUS; }
"-" { printf("MINUS"); return MINUS; }
"*" { printf("MULT"); return MULT; }
"/" { printf("DIVIDE"); return DIVIDE; }
"%" { printf("MODULO"); return MODULO; }
")" { printf("RROUNDPAREN"); return RROUNDPAREN; }
"(" { printf("LROUNDPAREN"); return LROUNDPAREN; }
"]" { printf("RSQUAREPAREN"); return RSQUAREPAREN; }
"[" { printf("LSQUAREPAREN"); return LSQUAREPAREN; }
"=" { printf("ASSIGN"); return ASSIGN; }
";" { printf("SEMICOLON"); return SEMICOLON; }
"BEGIN" { return BEGINTOK; }
"END" { return ENDTOK; }
"INT" { printf("INT"); return INT; }
"CHAR" { printf("CHAR"); return CHAR; }
"IF" { printf("IF"); return IF; }
"ELSE" { printf("ELSE"); return ELSE; }
"WHILE"  { printf("WHILE"); return WHILE; }
"READ"  { printf("READ"); return READ; }
"WRITE" { printf("WRITE"); return WRITE; }
{IDENTIFIER} { printf("IDENTIFIER"); return IDENTIFIER; }
{NUMERIC_CONSTANT} { printf("NUMERIC_CONSTANT"); return NUMERIC_CONSTANT; }
{CHAR_CONSTANT} { printf("CHAR_CONSTANT"); return CHAR_CONSTANT; }
. { printf("error %s ", yytext); }

%%
