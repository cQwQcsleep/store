package org.jetbrains.kotlin.js.parser;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J \u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/js/parser/ThrowExceptionOnErrorReporter;", "Lorg/jetbrains/kotlin/js/parser/ErrorReporter;", "<init>", "()V", "warning", "", "message", "", "startPosition", "Lorg/jetbrains/kotlin/js/parser/CodePosition;", "endPosition", "error", "", "org.jetbrains.kotlin:js.parser"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ThrowExceptionOnErrorReporter implements ErrorReporter {
    public static final ThrowExceptionOnErrorReporter INSTANCE = new ThrowExceptionOnErrorReporter();

    private ThrowExceptionOnErrorReporter() {
    }

    @Override // org.jetbrains.kotlin.js.parser.ErrorReporter
    /* JADX INFO: renamed from: error, reason: merged with bridge method [inline-methods] */
    public Void mo521error(String message, CodePosition startPosition, CodePosition endPosition) {
        message.getClass();
        startPosition.getClass();
        endPosition.getClass();
        throw new JsParserException(message, startPosition);
    }

    @Override // org.jetbrains.kotlin.js.parser.ErrorReporter
    public void warning(String message, CodePosition startPosition, CodePosition endPosition) {
        message.getClass();
        startPosition.getClass();
        endPosition.getClass();
    }
}
