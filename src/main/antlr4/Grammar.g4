grammar Grammar;

//Parser rules

//Lexer rules


WS: [ \t\r\n]+ -> skip ; //Pomijanie białych znaków
COMMENT: '#' ~[ \r\n]* -> skip ; //Pomijanie komentarzy