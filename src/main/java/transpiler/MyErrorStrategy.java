package transpiler;

import app.Controller;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;

public class MyErrorStrategy extends DefaultErrorStrategy {
    @Override
    public void reportError(Parser recognizer, RecognitionException e) {
        if (e instanceof InputMismatchException) {
            int line = -1;
            int charPositionInLine = -1;
            line = e.getOffendingToken().getLine();
            charPositionInLine =e.getOffendingToken().getCharPositionInLine();
            String msg = "mismatched input at: " + line + ":" + charPositionInLine + "\n" + getTokenErrorDisplay(e.getOffendingToken()) +
                    " expecting " + e.getExpectedTokens().toString(recognizer.getVocabulary());
            Controller.rustCodeStaticException += msg;
        }
        super.reportError(recognizer, e);
    }
}
