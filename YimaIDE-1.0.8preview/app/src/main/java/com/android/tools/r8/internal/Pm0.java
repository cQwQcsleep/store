package com.android.tools.r8.internal;

import java.io.Serializable;
import org.xmlpull.v1.XmlPullParser;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Pm0 {
    public static final Pm0 c = new Pm0("INT", 0, 0);
    public static final Pm0 d = new Pm0("LONG", 1, 0L);
    public static final Pm0 e = new Pm0("FLOAT", 2, Float.valueOf(0.0f));
    public static final Pm0 f = new Pm0("DOUBLE", 3, Double.valueOf(0.0d));
    public static final Pm0 g = new Pm0("BOOLEAN", 4, Boolean.FALSE);
    public static final Pm0 h = new Pm0("STRING", 5, XmlPullParser.NO_NAMESPACE);
    public static final Pm0 i = new Pm0("BYTE_STRING", 6, U7.c);
    public static final Pm0 j = new Pm0("ENUM", 7, null);
    public static final Pm0 k = new Pm0("MESSAGE", 8, null);
    public final Object b;

    public Pm0(String str, int i2, Serializable serializable) {
        super(str, i2);
    }
}
