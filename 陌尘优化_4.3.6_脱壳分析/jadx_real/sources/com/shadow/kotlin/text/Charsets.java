package com.shadow.kotlin.text;

import com.shadow.kotlin.io.CloseableKt;
import java.nio.charset.Charset;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class Charsets {
    public static final Charset UTF_8;
    private static volatile Charset utf_32be;
    private static volatile Charset utf_32le;

    static {
        Charset charsetForName = Charset.forName("UTF-8");
        CloseableKt.checkNotNullExpressionValue(charsetForName, "forName(...)");
        UTF_8 = charsetForName;
        CloseableKt.checkNotNullExpressionValue(Charset.forName("UTF-16"), "forName(...)");
        CloseableKt.checkNotNullExpressionValue(Charset.forName("UTF-16BE"), "forName(...)");
        CloseableKt.checkNotNullExpressionValue(Charset.forName("UTF-16LE"), "forName(...)");
        CloseableKt.checkNotNullExpressionValue(Charset.forName("US-ASCII"), "forName(...)");
        CloseableKt.checkNotNullExpressionValue(Charset.forName("ISO-8859-1"), "forName(...)");
    }

    public static Charset UTF32_BE() {
        Charset charset = utf_32be;
        if (charset != null) {
            return charset;
        }
        Charset charsetForName = Charset.forName("UTF-32BE");
        CloseableKt.checkNotNullExpressionValue(charsetForName, "forName(...)");
        utf_32be = charsetForName;
        return charsetForName;
    }

    public static Charset UTF32_LE() {
        Charset charset = utf_32le;
        if (charset != null) {
            return charset;
        }
        Charset charsetForName = Charset.forName("UTF-32LE");
        CloseableKt.checkNotNullExpressionValue(charsetForName, "forName(...)");
        utf_32le = charsetForName;
        return charsetForName;
    }
}
