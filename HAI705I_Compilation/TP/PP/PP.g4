grammar PP;

type : 'integer' | 'boolean' | array;

array : 'array of ' type;

constante  :
	('0'..'9')+ | 'true' | 'false' ;

cibleAppel  :
	'read' | 'write' | ID ;

ID : (('a'..'z')|('A'..'Z'))(('a'..'z')|('A'..'Z')|('0'..'9'))*;

expressions :
	constante 													|
	ID															|
	'-' expressions 											|
	'not' expressions											|

	expressions '+' expressions									|
	expressions '-' expressions 								|
	expressions '*' expressions									|
	expressions '/' expressions									|

	expressions 'and' expressions								|
	expressions 'or' expressions 								|

	expressions '<' expressions 								|
	expressions '<=' expressions 								|
	expressions '=' expressions 								|
	expressions '!=' expressions 								|
	expressions '>=' expressions								|
	expressions '>' expressions									|

	cibleAppel '(' listeArguments ')'							|
	expressions '[' expressions ']' 							|
	'new array of ' type '[' expressions ']';

variables :
	('var' ID ':' type (ID ':' type)* )? ;

listeDeclarationArguments :
	(ID ':' type (',' ID ':' type)*)? ;

listeArguments :
	(expressions (',' expressions)*)? ;

instructions :
	'skip'														|
	ID ':' '=' expressions 										|
	expressions '[' expressions ']' ':' '=' expressions			|
	'if' expressions 'then' instructions 'else' instructions	|
	'while' expressions 'do' instructions						|
	cibleAppel '(' listeArguments ')'							|
	instructions ';' instructions;

definitionFonctionProcedure :
	fonction '(' listeDeclarationArguments ')' (':' type)?
	variables
	instructions;

prog :
	variables
	definitionFonctionProcedure*
	instructions;

WS : [ \t\r\n]+ -> skip ;