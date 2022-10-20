// $Id$

import org.antlr.v4.runtime.*;
import java.util.*;

public class ExprArithListTest {

    public static void main(String [] argv) {
        ANTLRInputStream stream = new ANTLRInputStream(argv[0]);
        ExprArithListLexer lexer = new ExprArithListLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprArithListParser parser = new ExprArithListParser(tokens);
        ArrayList<ExprArith> l = parser.listExpr().value;
	for (ExprArith e : l)
	    System.out.println(e.eval()); // print the value
    }
}
