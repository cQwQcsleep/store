package org.jetbrains.kotlin.js.parser.sourcemaps;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00060\u0001j\u0002`\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/js/parser/sourcemaps/JsonSyntaxException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "offset", "", "line", "column", "text", "", "<init>", "(IIILjava/lang/String;)V", "getOffset", "()I", "getLine", "getColumn", "getText", "()Ljava/lang/String;", "org.jetbrains.kotlin:js.parser"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JsonSyntaxException extends RuntimeException {
    private final int column;
    private final int line;
    private final int offset;
    private final String text;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonSyntaxException(int i, int i2, int i3, String str) {
        super("JSON syntax error at " + (i2 + 1) + ", " + (i3 + 1) + ": " + str);
        str.getClass();
        this.offset = i;
        this.line = i2;
        this.column = i3;
        this.text = str;
    }

    public final int getColumn() {
        return this.column;
    }

    public final int getLine() {
        return this.line;
    }

    public final int getOffset() {
        return this.offset;
    }

    public final String getText() {
        return this.text;
    }
}
