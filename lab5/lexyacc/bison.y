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
	BEGINTOK instructions ENDTOK {printf("ACCEPTED!");}
	;

instructions: {printf("instructions -> epsilon\n");}
	|
	instruction instructions {printf("instructions -> instruction instructions\n");}
	;

instruction:
	declaration {printf("instruction -> declaration\n");}
	| statement {printf("instruction -> statement\n");}
	;

statement:
	input {printf("statement -> input\n");}
	| output {printf("statement -> output\n");}
	;

declaration:
	type IDENTIFIER arrayDeclaration SEMICOLON {printf("declaration -> type id arrayDeclaration ;\n");}
	;

type:
	INT {printf("type -> int\n");}
	| CHAR {printf("type -> char\n");}
	;

arrayDeclaration: {printf("arrayDeclaration -> epsilon\n");}
	|
	LSQUAREPAREN NUMERIC_CONSTANT RSQUAREPAREN {printf("arrayDeclaration -> [ NUMERIC_CONSTANT ]\n");}
	;

variable:
	IDENTIFIER arrayIdentifier {printf("variable -> IDENTIFIER arrayIdentifier\n");}
	;

arrayIdentifier: {printf("arrayIdentifier -> epsilon\n");}
	|
	LSQUAREPAREN arrayPosition RSQUAREPAREN {printf("arrayIdentifier -> [ arrayPosition ]\n");}
	;

arrayPosition:
	variable {printf("arrayPosition -> variable\n");}
	| NUMERIC_CONSTANT {printf("arrayPosition -> NUMERIC_CONSTANT\n");}
	;

input:
	READ LROUNDPAREN variable RROUNDPAREN SEMICOLON {printf("input -> read( variable );\n");}
	;

output:
	WRITE LROUNDPAREN writeContent RROUNDPAREN SEMICOLON {printf("output -> write(writeContent);\n");}
	;

writeContent:
	variable {printf("writeContent -> variable\n");}
	| NUMERIC_CONSTANT {printf("writeContent -> NUMERIC_CONSTANT\n");}
	| CHAR_CONSTANT {printf("writeContent -> CHAR_CONSTANT\n");}
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
