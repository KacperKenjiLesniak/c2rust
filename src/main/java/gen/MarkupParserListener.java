package gen;// Generated from /home/kenjik/University/c2rust/src/main/antlr/MarkupParser.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MarkupParser}.
 */
public interface MarkupParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MarkupParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(MarkupParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link MarkupParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(MarkupParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link MarkupParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(MarkupParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MarkupParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(MarkupParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MarkupParser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(MarkupParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MarkupParser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(MarkupParser.ContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MarkupParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(MarkupParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MarkupParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(MarkupParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MarkupParser#tag}.
	 * @param ctx the parse tree
	 */
	void enterTag(MarkupParser.TagContext ctx);
	/**
	 * Exit a parse tree produced by {@link MarkupParser#tag}.
	 * @param ctx the parse tree
	 */
	void exitTag(MarkupParser.TagContext ctx);
}