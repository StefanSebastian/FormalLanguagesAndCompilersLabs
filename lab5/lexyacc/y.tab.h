
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
     ASSIGN = 273,
     SEMICOLON = 274,
     BEGINTOK = 275,
     ENDTOK = 276,
     INT = 277,
     CHAR = 278,
     IF = 279,
     ELSE = 280,
     WHILE = 281,
     READ = 282,
     WRITE = 283,
     IDENTIFIER = 284,
     NUMERIC_CONSTANT = 285,
     CHAR_CONSTANT = 286
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
#define ASSIGN 273
#define SEMICOLON 274
#define BEGINTOK 275
#define ENDTOK 276
#define INT 277
#define CHAR 278
#define IF 279
#define ELSE 280
#define WHILE 281
#define READ 282
#define WRITE 283
#define IDENTIFIER 284
#define NUMERIC_CONSTANT 285
#define CHAR_CONSTANT 286




#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef int YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
#endif

extern YYSTYPE yylval;


