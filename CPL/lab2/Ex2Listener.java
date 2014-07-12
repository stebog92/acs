// Generated from Ex2.g by ANTLR 4.1

        import java.util.HashMap;
    
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Ex2Parser}.
 */
public interface Ex2Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Ex2Parser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(@NotNull Ex2Parser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link Ex2Parser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(@NotNull Ex2Parser.ProgContext ctx);

	/**
	 * Enter a parse tree produced by {@link Ex2Parser#fact}.
	 * @param ctx the parse tree
	 */
	void enterFact(@NotNull Ex2Parser.FactContext ctx);
	/**
	 * Exit a parse tree produced by {@link Ex2Parser#fact}.
	 * @param ctx the parse tree
	 */
	void exitFact(@NotNull Ex2Parser.FactContext ctx);

	/**
	 * Enter a parse tree produced by {@link Ex2Parser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(@NotNull Ex2Parser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link Ex2Parser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(@NotNull Ex2Parser.TermContext ctx);

	/**
	 * Enter a parse tree produced by {@link Ex2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull Ex2Parser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Ex2Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull Ex2Parser.ExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link Ex2Parser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(@NotNull Ex2Parser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link Ex2Parser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(@NotNull Ex2Parser.StatContext ctx);
}