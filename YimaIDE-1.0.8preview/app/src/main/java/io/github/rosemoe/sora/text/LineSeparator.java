package io.github.rosemoe.sora.text;

import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public enum LineSeparator {
    NONE(""),
    LF("\n"),
    CR("\r"),
    CRLF("\r\n");

    private final char[] chars;
    private final int length;
    private final String str;

    LineSeparator(String str) {
        this.str = str;
        this.length = str.length();
        this.chars = str.toCharArray();
    }

    public static LineSeparator fromSeparatorString(String str) {
        Objects.requireNonNull(str, "text must not be null");
        switch (str) {
            case "":
                return NONE;
            case "
":
                return LF;
            case "":
                return CR;
            case "
":
                return CRLF;
            default:
                w01.a("unknown line separator type");
                return null;
        }
    }

    public char[] getChars() {
        return this.chars;
    }

    public String getContent() {
        return this.str;
    }

    public int getLength() {
        return this.length;
    }

    public static LineSeparator fromSeparatorString(CharSequence charSequence, int i, int i2) {
        Objects.requireNonNull(charSequence, "text must not be null");
        if (i2 == i) {
            return NONE;
        }
        int i3 = i2 - i;
        if (i3 == 1) {
            char cCharAt = charSequence.charAt(i);
            if (cCharAt == '\r') {
                return CR;
            }
            if (cCharAt == '\n') {
                return LF;
            }
        }
        if (i3 == 2 && charSequence.charAt(i) == '\r' && charSequence.charAt(i + 1) == '\n') {
            return CRLF;
        }
        w01.a("unknown line separator type");
        return null;
    }
}
