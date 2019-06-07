package datatype;

import java_cup.runtime.*;
import java.io.*;
%%
%cup
%line
%column
%char
%class Scanner
%{

%}
%eofval{
    return new Symbol(sym.EOF);
%eofval}

%%
"class" { return new Symbol(sym.CLASS); }
"abstract" { return new Symbol(sym.ABSTRACT); }
"extends" { return new Symbol(sym.EXTENDS); }
"const" { return new Symbol(sym.CONST); }
"," { return new Symbol(sym.VIRG); }
"(" { return new Symbol(sym.LPAREN); }
")" { return new Symbol(sym.RPAREN); }
[a-zA-Z0-9]+ { return new Symbol(sym.ID, yytext()); }
[ \t\f\r\n] { /* ignore white space. */ }
. { System.err.println("Illegal character: "+yytext()); }
