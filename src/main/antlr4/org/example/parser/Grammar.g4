grammar Grammar;

@header {
    package org.example.parser;
}

//Parser rules

program_all: procedures main;

procedures: 'PROCEDURE' proc_head 'IS' declarations 'BEGIN' commands 'END'         #PROCEDUREWITHDECLARATIONS
           | 'PROCEDURE' proc_head 'IS' 'BEGIN' commands 'END'                     #PROCEDUREWITHOUTDECLARATIONS
           ;


main: 'PROGRAM' 'IS' declarations 'BEGIN' commands 'END'
    | 'PROGRAM' 'IS' 'BEGIN' commands 'END'
    ;

commands: command+;

command : identifier ':=' expression ';'                                            #ASSIGN
       | 'IF' condition 'THEN' commands 'ELSE' commands 'ENDIF'                     #IFELSE
       | 'IF' condition 'THEN' commands 'ENDIF'                                     #IF
       | 'WHILE' condition 'DO' commands 'ENDWHILE'                                 #WHILE
       | 'REPEAT' commands 'UNTIL' condition ';'                                    #REPEATUNTIL
       | 'FOR' PIDENTIFIER 'FROM' value 'TO' value 'DO' commands 'ENDFOR'           #FORUP
       | 'FOR' PIDENTIFIER 'FROM' value 'DOWNTO' value 'DO' commands 'ENDFOR'       #FORDOWNTO
       | proc_call ';'                                                              #CALLPROC
       | 'READ' identifier ';'                                                      #READ
       | 'WRITE' value ';'                                                          #WRITE
       ;

proc_head: PIDENTIFIER '(' args_decl ')';

proc_call: PIDENTIFIER '(' args ')';

declarations: declarations',' PIDENTIFIER                   #MULTISINGLEDECLARATION
            | declarations',' PIDENTIFIER '['NUM':'NUM']'   #MULTIARRAYDECLARATION
            | PIDENTIFIER                                   #SINGLEDECLARATION
            | PIDENTIFIER '['NUM':'NUM']'                   #ARRAYDECLARATION
            ;

args_decl: ('T'? PIDENTIFIER (',' 'T'? PIDENTIFIER)*)?;

args: PIDENTIFIER (',' PIDENTIFIER)*;

expression: value   #VALEXPR
            | value '+' value   #ADD
            | value '-' value   #SUB
            | value '*' value   #MUL
            | value '/' value   #DIV
            | value '%' value   #MOD
            ;

condition: value '=' value  #EQ
            | value NOTEQUAL value  #NEQ
            | value '>' value   #GT
            | value '<' value   #LT
            | value '>=' value  #GEQ
            | value '<=' value  #LEQ
            ;

value: NUM
    | identifier
    ;

identifier: PIDENTIFIER
        | PIDENTIFIER LHBRACK PIDENTIFIER RHBRACK
        | PIDENTIFIER RHBRACK NUM RHBRACK
        ;


//Lexer rules



PIDENTIFIER: [_a-z]+;
NUM: [0-9]+;

NOTEQUAL: '!=';
LHBRACK: '[';
RHBRACK: ']';
WS: [ \t\r\n]+ -> skip ; //Pomijanie białych znaków
COMMENT: '#' ~[\r\n]* -> skip ; //Pomijanie komentarzy