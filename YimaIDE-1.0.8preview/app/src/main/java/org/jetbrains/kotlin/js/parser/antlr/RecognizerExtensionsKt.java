package org.jetbrains.kotlin.js.parser.antlr;

import kotlin.Metadata;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.jetbrains.kotlin.js.parser.CodePosition;
import org.jetbrains.kotlin.js.parser.ErrorReporter;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"addErrorListener", "", "Lorg/antlr/v4/runtime/Recognizer;", "reporter", "Lorg/jetbrains/kotlin/js/parser/ErrorReporter;", "org.jetbrains.kotlin:js.parser"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class RecognizerExtensionsKt {
    public static final void addErrorListener(Recognizer<?, ?> recognizer, final ErrorReporter errorReporter) {
        recognizer.getClass();
        errorReporter.getClass();
        recognizer.addErrorListener(new BaseErrorListener() { // from class: org.jetbrains.kotlin.js.parser.antlr.RecognizerExtensionsKt.addErrorListener.1
            public void syntaxError(Recognizer<?, ?> recognizer2, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                ErrorReporter errorReporter2 = errorReporter;
                if (msg == null) {
                    msg = "";
                }
                errorReporter2.mo521error(msg, new CodePosition(line, charPositionInLine), new CodePosition(line, charPositionInLine));
            }
        });
    }
}
