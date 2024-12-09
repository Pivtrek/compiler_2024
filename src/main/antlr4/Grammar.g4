grammar Grammar;

//Parser rules

program_all: procedures main;

procedures: procedures PROCEDURE proc_head IS declarations BEGIN commands END
        |  procedures PROCEDURE proc_head IS BEGIN commands END
        ;

main: PROGRAM IS declarations BEGIN commands END
    | PROGRAM IS BEGIN commands END
    ;

commands: commands command
        | command
        ;

command : identifier ':=' expression ';'
       | IF condition THEN commands ELSE commands ENDIF
       | IF condition THEN commands ENDIF
       | WHILE condition DO commands ENDWHILE
       | REPEAT commands UNTIL condition ';'
       | FOR pidentifier FROM value TO value DO commands ENDFOR
       | FOR pidentifier FROM value DOWNTO value DO commands ENDFOR
       | proc_call ';'
       | READ identifier ';'
       | WRITE value ';'
       ;

proc_head: pidentifier '(' args_decl ')';

proc_call: pidentifier '(' args ')';

declarations: declarations',' pidentifier
            | declarations',' pidentifier '['num':'num']'
            | pidentifier
            | pidentifier '['num':'num']'
            ;

args_decl: args_decl',' pidentifier
        | args_decl',' 'T' pidentifier
        | pidentifier
        | 'T' pidentifier
        ;

args: args',' pidentifier
    | pidentifier
    ;

expression: value
            | value '+' value
            | value '-' value
            | value '*' value
            | value '/' value
            | value '%' value
            ;






//Lexer rules


WS: [ \t\r\n]+ -> skip ; //Pomijanie białych znaków
COMMENT: '#' ~[ \r\n]* -> skip ; //Pomijanie komentarzy