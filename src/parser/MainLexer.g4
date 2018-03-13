lexer grammar MainLexer;

PLUS:           '+';
MINUS:          '-';
STAR:           '*';
DIV:            '/';
MOD:            '%';

LPAREN:         '(';
RPAREN:         ')';
LCBRACE:        '{';
RCBRACE:        '}';
SEMICOLON:      ';';

WRITE_KW:       'write';
READ_KW:        'read';
FUN_KW:         'fun';
RETURN_KW:      'return';

IDENT:          [a-zA-Z_]+ [a-zA-Z0-9_]*;
INT:            [0-9]+;
WS:             [ \t\n\r]+ -> skip;
COMMENT:        '/*' .*? '*/' -> skip;
LINE_COMMENT:   '//' ~'\n'* '\n' -> skip;