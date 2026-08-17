package org.jetbrains.kotlin.wasm.ir.convertors;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.text.StringsKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u000fJ\n\u0010\u0015\u001a\u00020\u0013H\u0096\u0080\u0004R\u0012\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/convertors/StringBuilderWithLocations;", "", "<init>", "()V", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "value", "", "lineNumber", "getLineNumber", "()I", "columnNumber", "getColumnNumber", "append", "", "char", "", "text", "", "appendLine", "toString", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class StringBuilderWithLocations {
    private final StringBuilder builder = new StringBuilder();
    private int columnNumber = -1;
    private int lineNumber;

    public final void append(String text) {
        text.getClass();
        this.builder.append(text);
        List listSplit$default = StringsKt.split$default(text, new char[]{'\n'}, false, 0, 6, (Object) null);
        if (listSplit$default.size() > 1) {
            this.columnNumber = -1;
        }
        this.lineNumber += listSplit$default.size() - 1;
        this.columnNumber += ((String) CollectionsKt.last(listSplit$default)).length();
    }

    public final void appendLine() {
        this.builder.append('\n');
        this.lineNumber++;
        this.columnNumber = -1;
    }

    public final int getColumnNumber() {
        return this.columnNumber;
    }

    public final int getLineNumber() {
        return this.lineNumber;
    }

    public String toString() {
        return this.builder.toString();
    }

    public final void append(char c) {
        if (c == '\n') {
            appendLine();
        } else {
            this.builder.append(c);
        }
    }
}
