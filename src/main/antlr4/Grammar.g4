grammar Grammar;

//Parser rules

program_all: procedures main;

procedures: (PROCEDURE proc_head IS declarations BEGIN commands END
           | PROCEDURE proc_head IS BEGIN commands END)*;

main: PROGRAM IS declarations BEGIN commands END
    | PROGRAM IS BEGIN commands END
    ;

commands: command+;

command : identifier ':=' expression ';'
       | IF condition THEN commands ELSE commands ENDIF
       | IF condition THEN commands ENDIF
       | WHILE condition DO commands ENDWHILE
       | REPEAT commands UNTIL condition ';'
       | FOR PIDENTIFIER FROM value TO value DO commands ENDFOR
       | FOR PIDENTIFIER FROM value DOWNTO value DO commands ENDFOR
       | proc_call ';'
       | READ identifier ';'
       | WRITE value ';'
       ;

proc_head: PIDENTIFIER '(' args_decl ')';

proc_call: PIDENTIFIER '(' args ')';

declarations: declarations',' PIDENTIFIER
            | declarations',' PIDENTIFIER '['NUM':'NUM']'
            | PIDENTIFIER
            | PIDENTIFIER '['NUM':'NUM']'
            ;

args_decl: ('T'? PIDENTIFIER (',' 'T'? PIDENTIFIER)*)?;

args: PIDENTIFIER (',' PIDENTIFIER)*;

expression: value
            | value '+' value
            | value '-' value
            | value '*' value
            | value '/' value
            | value '%' value
            ;

condition: value '=' value
            | value NOTEQUAL value
            | value '>' value
            | value '<' value
            | value '>=' value
            | value '<=' value
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