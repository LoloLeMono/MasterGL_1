grammar PP;

expr returns (PPExpr value) :
	c = cte {$value = $c.value;}
 |  v = Var {$value = new PPVar($v.text);}
 |	'-' e = expr {$value = new PPInv($e.value);}
 |  'not' e = expr {$value = new PPNot($e.value);}
 |  e1 = expr '+' e2 = expr {$value = new PPAdd($e1.value, $e2.value);}