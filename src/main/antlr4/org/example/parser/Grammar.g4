grammar Grammar;

//Parser rules

program_all: (procedures)* main;

procedures: PROCEDURE proc_head 'IS' declarations 'BEGIN' commands 'END'         #PROCEDUREWITHDECLARATIONS
           | PROCEDURE proc_head 'IS' 'BEGIN' commands 'END'                     #PROCEDUREWITHOUTDECLARATIONS
           ;


main: 'PROGRAM' 'IS' declarations 'BEGIN' commands 'END'                            #MAINDECLARATIONS
    | 'PROGRAM' 'IS' 'BEGIN' commands 'END'                                         #MAINWITHOUTDECLARATIONS
    ;

commands: command (command)*;

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

signedNum: '-'? NUM;

declarations: declarations ',' PIDENTIFIER '['signedNum ':' signedNum ']'         #MULTIARRAYDECLARATION
             | PIDENTIFIER '['signedNum ':' signedNum ']'                         #ARRAYDECLARATION
             | declarations ',' PIDENTIFIER                                       #MULTISINGLEDECLARATION
             | PIDENTIFIER                                                        #SINGLEDECLARATION
             ;

args_decl: args_decl COMMA T PIDENTIFIER #ARGSMUTLIARRDECL
         | args_decl COMMA PIDENTIFIER   #ARGSMULTIDECL
         | T PIDENTIFIER                 #ARGSARRDECL
         |  PIDENTIFIER                  #ARGSDECL
         ;
args: PIDENTIFIER (',' PIDENTIFIER)*;

expression: value   #VALEXPR
            | '-' value #NEGATE
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

value: signedNum
    | identifier
    ;

identifier: PIDENTIFIER                             #INTUSAGE
        | PIDENTIFIER LHBRACK PIDENTIFIER RHBRACK   #ARRAYWITHPIDUSAGE
        | PIDENTIFIER LHBRACK NUM RHBRACK           #ARRAYWITHNUMUSAGE
        ;

//Lexer rules

PROCEDURE: 'PROCEDURE';
COMMA: ',';
T: 'T';
PIDENTIFIER: [_a-z]+;
NUM: ('0' | [1-9] [0-9]*);

NOTEQUAL: '!=';
LHBRACK: '[';
RHBRACK: ']';
WS: [ \t\r\n]+ -> skip ; //Pomijanie białych znaków
COMMENT: '#' ~[\r\n]* -> skip ; //Pomijanie komentarzy

