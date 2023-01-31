// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PPParser}.
 */
public interface PPListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PPParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(PPParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(PPParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(PPParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(PPParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#constante}.
	 * @param ctx the parse tree
	 */
	void enterConstante(PPParser.ConstanteContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#constante}.
	 * @param ctx the parse tree
	 */
	void exitConstante(PPParser.ConstanteContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#cibleAppel}.
	 * @param ctx the parse tree
	 */
	void enterCibleAppel(PPParser.CibleAppelContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#cibleAppel}.
	 * @param ctx the parse tree
	 */
	void exitCibleAppel(PPParser.CibleAppelContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#fonction}.
	 * @param ctx the parse tree
	 */
	void enterFonction(PPParser.FonctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#fonction}.
	 * @param ctx the parse tree
	 */
	void exitFonction(PPParser.FonctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#expressions}.
	 * @param ctx the parse tree
	 */
	void enterExpressions(PPParser.ExpressionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#expressions}.
	 * @param ctx the parse tree
	 */
	void exitExpressions(PPParser.ExpressionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#variables}.
	 * @param ctx the parse tree
	 */
	void enterVariables(PPParser.VariablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#variables}.
	 * @param ctx the parse tree
	 */
	void exitVariables(PPParser.VariablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#listeDeclarationArguments}.
	 * @param ctx the parse tree
	 */
	void enterListeDeclarationArguments(PPParser.ListeDeclarationArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#listeDeclarationArguments}.
	 * @param ctx the parse tree
	 */
	void exitListeDeclarationArguments(PPParser.ListeDeclarationArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#listeArguments}.
	 * @param ctx the parse tree
	 */
	void enterListeArguments(PPParser.ListeArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#listeArguments}.
	 * @param ctx the parse tree
	 */
	void exitListeArguments(PPParser.ListeArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#instructions}.
	 * @param ctx the parse tree
	 */
	void enterInstructions(PPParser.InstructionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#instructions}.
	 * @param ctx the parse tree
	 */
	void exitInstructions(PPParser.InstructionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#definitionFonctionProcedure}.
	 * @param ctx the parse tree
	 */
	void enterDefinitionFonctionProcedure(PPParser.DefinitionFonctionProcedureContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#definitionFonctionProcedure}.
	 * @param ctx the parse tree
	 */
	void exitDefinitionFonctionProcedure(PPParser.DefinitionFonctionProcedureContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(PPParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(PPParser.ProgContext ctx);
}