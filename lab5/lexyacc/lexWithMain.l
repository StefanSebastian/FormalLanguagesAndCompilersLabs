%option noyywrap
%option caseless

IDENTIFIER (_|[a-zA-Z])[a-zA-Z0-9_]*
NUMERIC_CONSTANT [-]?[1-9][0-9]*|0
CHAR_CONSTANT '[0-9a-zA-Z]'

%{
#include <stdio.h>
#include "y.tab.h"

extern int yylex();
%}

%%

[ \t]+ ;
\n { printf("\n"); }
"==" { printf("EQ "); }
"!=" { printf("NE "); }
"<" { printf("LT ");  }
"<=" { printf("LE ");  }
">" { printf("GT "); }
">=" { printf("GE "); }
"+" { printf("PLUS "); }
"-" { printf("MINUS "); }
"*" { printf("MULT "); }
"/" { printf("DIVIDE "); }
"%" { printf("MODULO "); }
")" { printf("RROUNDPAREN "); }
"(" { printf("LROUNDPAREN "); }
"]" { printf("RSQUAREPAREN "); }
"[" { printf("LSQUAREPAREN "); }
"}" { printf("RBRACKET "); }
"{" { printf("LBRACKET "); }
"=" { printf("ASSIGN "); }
";" { printf("SEMICOLON "); }
"BEGIN" { printf("BEGIN "); }
"END" { printf("END "); }
"INT" { printf("INT ");}
"CHAR" { printf("CHAR "); }
"IF" { printf("IF "); }
"ELSE" { printf("ELSE "); }
"WHILE"  { printf("WHILE "); }
"READ"  { printf("READ "); }
"WRITE" { printf("WRITE "); }
{IDENTIFIER} { printf("IDENTIFIER "); }
{NUMERIC_CONSTANT} { printf("NUMERIC_CONSTANT "); }
{CHAR_CONSTANT} { printf("CHAR_CONSTANT "); }
. { printf("error %s ", yytext); }

%%

int main() {
	yylex();
}
