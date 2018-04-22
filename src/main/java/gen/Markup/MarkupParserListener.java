package gen.Markup;// Generated from /home/kenjik/University/c2rust/src/main/antlr/MarkupParser.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a transpile tree produced by
 * {@link MarkupParser}.
 */
public interface MarkupParserListener extends ParseTreeListener {
	/**
	 * Enter a transpile tree produced by {@link MarkupParser#file}.
	 * @param ctx the transpile tree
	 */
	void enterFile(MarkupParser.FileContext ctx);
	/**
	 * Exit a transpile tree produced by {@link MarkupParser#file}.
	 * @param ctx the transpile tree
	 */
	void exitFile(MarkupParser.FileContext ctx);
	/**
	 * Enter a transpile tree produced by {@link MarkupParser#attribute}.
	 * @param ctx the transpile tree
	 */
	void enterAttribute(MarkupParser.AttributeContext ctx);
	/**
	 * Exit a transpile tree produced by {@link MarkupParser#attribute}.
	 * @param ctx the transpile tree
	 */
	void exitAttribute(MarkupParser.AttributeContext ctx);
	/**
	 * Enter a transpile tree produced by {@link MarkupParser#content}.
	 * @param ctx the transpile tree
	 */
	void enterContent(MarkupParser.ContentContext ctx);
	/**
	 * Exit a transpile tree produced by {@link MarkupParser#content}.
	 * @param ctx the transpile tree
	 */
	void exitContent(MarkupParser.ContentContext ctx);
	/**
	 * Enter a transpile tree produced by {@link MarkupParser#element}.
	 * @param ctx the transpile tree
	 */
	void enterElement(MarkupParser.ElementContext ctx);
	/**
	 * Exit a transpile tree produced by {@link MarkupParser#element}.
	 * @param ctx the transpile tree
	 */
	void exitElement(MarkupParser.ElementContext ctx);
	/**
	 * Enter a transpile tree produced by {@link MarkupParser#tag}.
	 * @param ctx the transpile tree
	 */
	void enterTag(MarkupParser.TagContext ctx);
	/**
	 * Exit a transpile tree produced by {@link MarkupParser#tag}.
	 * @param ctx the transpile tree
	 */
	void exitTag(MarkupParser.TagContext ctx);
}