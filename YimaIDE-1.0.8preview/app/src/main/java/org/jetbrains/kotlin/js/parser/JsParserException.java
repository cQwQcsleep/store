package org.jetbrains.kotlin.js.parser;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/js/parser/JsParserException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "message", "", "position", "Lorg/jetbrains/kotlin/js/parser/CodePosition;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/js/parser/CodePosition;)V", "getPosition", "()Lorg/jetbrains/kotlin/js/parser/CodePosition;", "org.jetbrains.kotlin:js.parser"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JsParserException extends RuntimeException {
    private final CodePosition position;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsParserException(String str, CodePosition codePosition) {
        super(str + " at " + codePosition);
        str.getClass();
        codePosition.getClass();
        this.position = codePosition;
    }

    public final CodePosition getPosition() {
        return this.position;
    }
}
