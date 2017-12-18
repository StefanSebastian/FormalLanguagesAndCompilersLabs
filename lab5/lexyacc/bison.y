%{
#include <stdio.h>
#include <stdlib.h>
extern int yylex();
extern int yyparse();
extern FILE* yyin;
void yyerror(const char* s);
%}

%token EQ
%token NE
%token LT
%token LE
%token GT
%token GE
%token PLUS
%token MINUS
%token MULT
%token DIVIDE
%token MODULO
%token RROUNDPAREN
%token LROUNDPAREN
%token RSQUAREPAREN
%token LSQUAREPAREN
%token RBRACKET
%token LBRACKET
%token ASSIGN
%token SEMICOLON
%token BEGINTOK
%token ENDTOK
%token INT
%token CHAR
%token IF
%token ELSE
%token WHILE
%token READ
%token WRITE
%token IDENTIFIER
%token NUMERIC_CONSTANT
%token CHAR_CONSTANT

%%

program:
	BEGINTOK instructions ENDTOK {printf("works?");}
	;

instructions:
	|
	IDENTIFIER
	;

%%

int main() {
	yyin = stdin;
	do {
		yyparse();
	} while(!feof(yyin));
	return 0;
}
void yyerror(const char* s) {
	fprintf(stderr, "Parse error: %s\n", s);
	exit(1);
}
