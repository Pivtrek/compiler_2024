grammar Grammar;

//Parser rules

program_all: procedures main;

procedures: procedures PROCEDURE proc_head IS declarations BEGIN commands END
        |  procedures PROCEDURE proc_head IS BEGIN commands END
        ;

main: PROGRAM IS declarations BEGIN commands END
    | PROGRAM IS BEGIN commands END





//Lexer rules


WS: [ \t\r\n]+ -> skip ; //Pomijanie białych znaków
COMMENT: '#' ~[ \r\n]* -> skip ; //Pomijanie komentarzy