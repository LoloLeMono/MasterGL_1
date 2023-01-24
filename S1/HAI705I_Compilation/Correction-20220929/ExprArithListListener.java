// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExprArithListParser}.
 */
public interface ExprArithListListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExprArithListParser#listExpr}.
	 * @param ctx the parse tree
	 */
	void enterListExpr(ExprArithListParser.ListExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprArithListParser#listExpr}.
	 * @param ctx the parse tree
	 */
	void exitListExpr(ExprArithListParser.ListExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprArithListParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(ExprArithListParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprArithListParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(ExprArithListParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprArithListParser#additionExpr}.
	 * @param ctx the parse tree
	 */
	void enterAdditionExpr(ExprArithListParser.AdditionExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprArithListParser#additionExpr}.
	 * @param ctx the parse tree
	 */
	void exitAdditionExpr(ExprArithListParser.AdditionExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprArithListParser#multiplyExpr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplyExpr(ExprArithListParser.MultiplyExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprArithListParser#multiplyExpr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplyExpr(ExprArithListParser.MultiplyExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprArithListParser#atomExpr}.
	 * @param ctx the parse tree
	 */
	void enterAtomExpr(ExprArithListParser.AtomExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprArithListParser#atomExpr}.
	 * @param ctx the parse tree
	 */
	void exitAtomExpr(ExprArithListParser.AtomExprContext ctx);
}