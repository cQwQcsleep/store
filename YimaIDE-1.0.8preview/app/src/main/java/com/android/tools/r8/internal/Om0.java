package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Om0 {
    public static final Om0 c = new Om0("INT", 0, 0);
    public static final Om0 d = new Om0("LONG", 1, 0L);
    public static final Om0 e = new Om0("FLOAT", 2, Float.valueOf(0.0f));
    public static final Om0 f = new Om0("DOUBLE", 3, Double.valueOf(0.0d));
    public static final Om0 g = new Om0("BOOLEAN", 4, Boolean.FALSE);
    public static final Om0 h = new Om0("STRING", 5, XmlPullParser.NO_NAMESPACE);
    public static final Om0 i = new Om0("BYTE_STRING", 6, T7.b);
    public static final Om0 j = new Om0("ENUM", 7, null);
    public static final Om0 k = new Om0("MESSAGE", 8, null);
    public final Object b;

    public Om0(String str, int i2, Object obj) {
        super(str, i2);
        this.b = obj;
    }
}
