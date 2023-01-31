// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExprArithASTParser}.
 */
public interface ExprArithASTListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExprArithASTParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(ExprArithASTParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprArithASTParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(ExprArithASTParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprArithASTParser#additionExpr}.
	 * @param ctx the parse tree
	 */
	void enterAdditionExpr(ExprArithASTParser.AdditionExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprArithASTParser#additionExpr}.
	 * @param ctx the parse tree
	 */
	void exitAdditionExpr(ExprArithASTParser.AdditionExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprArithASTParser#multiplyExpr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplyExpr(ExprArithASTParser.MultiplyExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprArithASTParser#multiplyExpr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplyExpr(ExprArithASTParser.MultiplyExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprArithASTParser#atomExpr}.
	 * @param ctx the parse tree
	 */
	void enterAtomExpr(ExprArithASTParser.AtomExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprArithASTParser#atomExpr}.
	 * @param ctx the parse tree
	 */
	void exitAtomExpr(ExprArithASTParser.AtomExprContext ctx);
}