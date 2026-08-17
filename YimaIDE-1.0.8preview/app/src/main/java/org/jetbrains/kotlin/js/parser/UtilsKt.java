package org.jetbrains.kotlin.js.parser;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"isEndOfLine", "", "", "offsetOf", "", "", "position", "Lorg/jetbrains/kotlin/js/parser/CodePosition;", "org.jetbrains.kotlin:js.parser"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class UtilsKt {
    public static final boolean isEndOfLine(char c) {
        return c == '\r' || c == '\n' || c == 8232 || c == 8233;
    }

    public static final int offsetOf(String str, CodePosition codePosition) {
        str.getClass();
        codePosition.getClass();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            if (i2 == codePosition.getLine() && i3 == codePosition.getOffset()) {
                return i;
            }
            i++;
            i3++;
            if (isEndOfLine(cCharAt)) {
                i2++;
                codePosition.getLine();
                i3 = 0;
            }
        }
        return str.length();
    }
}
