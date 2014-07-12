// Generated from Parens.g by ANTLR 4.1
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ParensParser}.
 */
public interface ParensListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ParensParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(@NotNull ParensParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParensParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(@NotNull ParensParser.ProgContext ctx);
}