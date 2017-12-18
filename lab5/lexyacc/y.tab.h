
/* A Bison parser, made by GNU Bison 2.4.1.  */

/* Skeleton interface for Bison's Yacc-like parsers in C
   
      Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.
   
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.
   
   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   
   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.
   
   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */


/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     EQ = 258,
     NE = 259,
     LT = 260,
     LE = 261,
     GT = 262,
     GE = 263,
     PLUS = 264,
     MINUS = 265,
     MULT = 266,
     DIVIDE = 267,
     MODULO = 268,
     RROUNDPAREN = 269,
     LROUNDPAREN = 270,
     RSQUAREPAREN = 271,
     LSQUAREPAREN = 272,
     RBRACKET = 273,
     LBRACKET = 274,
     ASSIGN = 275,
     SEMICOLON = 276,
     BEGINTOK = 277,
     ENDTOK = 278,
     INT = 279,
     CHAR = 280,
     IF = 281,
     ELSE = 282,
     WHILE = 283,
     READ = 284,
     WRITE = 285,
     IDENTIFIER = 286,
     NUMERIC_CONSTANT = 287,
     CHAR_CONSTANT = 288
   };
#endif
/* Tokens.  */
#define EQ 258
#define NE 259
#define LT 260
#define LE 261
#define GT 262
#define GE 263
#define PLUS 264
#define MINUS 265
#define MULT 266
#define DIVIDE 267
#define MODULO 268
#define RROUNDPAREN 269
#define LROUNDPAREN 270
#define RSQUAREPAREN 271
#define LSQUAREPAREN 272
#define RBRACKET 273
#define LBRACKET 274
#define ASSIGN 275
#define SEMICOLON 276
#define BEGINTOK 277
#define ENDTOK 278
#define INT 279
#define CHAR 280
#define IF 281
#define ELSE 282
#define WHILE 283
#define READ 284
#define WRITE 285
#define IDENTIFIER 286
#define NUMERIC_CONSTANT 287
#define CHAR_CONSTANT 288




#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef int YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
#endif

extern YYSTYPE yylval;


