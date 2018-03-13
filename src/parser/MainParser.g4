parser grammar MainParser;

options {
    tokenVocab = MainLexer;
}

file
    :   functions += function*
    ;

function
    :   'fun' name=IDENT '('')' '{' stmtList '}'
    ;
    
stmtList
    :   (statements += stmt ';')*
    ;
    
stmt
    :   'write' expr    #write
    |   'return' expr   #return
    ;
   
expr
    :   INT                                     #const
    |   IDENT '(' ')'                           #call
    |   '(' expr ')'                            #parens
    |   e1=expr op=('*' | '/' | '%') e2=expr    #mulDiv     
    |   e1=expr op=('+' | '-') e2=expr          #plusMinus
    ;