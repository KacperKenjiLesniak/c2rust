package gen.Markup;// Generated from /home/kenjik/University/c2rust/src/main/antlr/MarkupParser.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a transpile tree produced
 * by {@link MarkupParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MarkupParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a transpile tree produced by {@link MarkupParser#file}.
	 * @param ctx the transpile tree
	 * @return the visitor result
	 */
	T visitFile(MarkupParser.FileContext ctx);
	/**
	 * Visit a transpile tree produced by {@link MarkupParser#attribute}.
	 * @param ctx the transpile tree
	 * @return the visitor result
	 */
	T visitAttribute(MarkupParser.AttributeContext ctx);
	/**
	 * Visit a transpile tree produced by {@link MarkupParser#content}.
	 * @param ctx the transpile tree
	 * @return the visitor result
	 */
	T visitContent(MarkupParser.ContentContext ctx);
	/**
	 * Visit a transpile tree produced by {@link MarkupParser#element}.
	 * @param ctx the transpile tree
	 * @return the visitor result
	 */
	T visitElement(MarkupParser.ElementContext ctx);
	/**
	 * Visit a transpile tree produced by {@link MarkupParser#tag}.
	 * @param ctx the transpile tree
	 * @return the visitor result
	 */
	T visitTag(MarkupParser.TagContext ctx);
}