// $Id$

grammar ExprArithList;

// Lists of expressions
listExpr returns [ArrayList<ExprArith> value]
@init{$value = new ArrayList<ExprArith>();} :
    (e = expr {$value.add($e.value);})+ ;

// Expressions
expr returns [ExprArith value] :
  e = additionExpr {$value = $e.value;} ;

// Addition and subtraction have the lowest precedence.
additionExpr returns [ExprArith value] :
  e1 = multiplyExpr {$value = $e1.value;}
  ('+' e2 = multiplyExpr {$value = new Add($value, $e2.value);}
  | '-' e2 = multiplyExpr {$value = new Sub($value, $e2.value);})* ;

// Multiplication and division have a higher precedence.
multiplyExpr returns [ExprArith value] :
  e1 = atomExpr {$value = $e1.value;}
  ('*' e2 = atomExpr {$value = new Mul($value, $e2.value);}
  | '/' e2 = atomExpr {$value = new Div($value, $e2.value);})* ;

/* An expression atom is the smallest part of an expression: a number. Or 
   when we encounter parenthesis, we're making a recursive call back to the
   rule 'additionExpr'. As you can see, an 'atomExpr' has the highest
   precedence. */
atomExpr returns [ExprArith value] :
  c = Number {$value = new Cte(Integer.parseInt($c.text));}
  | '(' e1 = additionExpr ')' {$value = $e1.value;}
  | '-' e2 = atomExpr {$value = new Inv($e2.value);} ;

// A number is an integer value
Number : ('0'..'9')+ ;

// We're going to ignore all white space characters
WS : [ \t\r\n]+ -> skip ;
