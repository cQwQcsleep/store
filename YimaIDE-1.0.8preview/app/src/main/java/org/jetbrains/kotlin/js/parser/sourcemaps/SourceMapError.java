package org.jetbrains.kotlin.js.parser.sourcemaps;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/js/parser/sourcemaps/SourceMapError;", "Lorg/jetbrains/kotlin/js/parser/sourcemaps/SourceMapParseResult;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "org.jetbrains.kotlin:js.parser"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class SourceMapError extends SourceMapParseResult {
    private final String message;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SourceMapError(String str) {
        super(null);
        str.getClass();
        this.message = str;
    }

    public final String getMessage() {
        return this.message;
    }
}
