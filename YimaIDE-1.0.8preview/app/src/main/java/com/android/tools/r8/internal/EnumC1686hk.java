package com.android.tools.r8.internal;

import java.io.Serializable;
import org.xmlpull.v1.XmlPullParser;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: renamed from: com.android.tools.r8.internal.hk, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EnumC1686hk {
    public static final EnumC1686hk c = new EnumC1686hk("INT", 0, 0);
    public static final EnumC1686hk d = new EnumC1686hk("LONG", 1, 0L);
    public static final EnumC1686hk e = new EnumC1686hk("FLOAT", 2, Float.valueOf(0.0f));
    public static final EnumC1686hk f = new EnumC1686hk("DOUBLE", 3, Double.valueOf(0.0d));
    public static final EnumC1686hk g = new EnumC1686hk("BOOLEAN", 4, Boolean.FALSE);
    public static final EnumC1686hk h = new EnumC1686hk("STRING", 5, XmlPullParser.NO_NAMESPACE);
    public static final EnumC1686hk i = new EnumC1686hk("BYTE_STRING", 6, U7.c);
    public static final EnumC1686hk j = new EnumC1686hk("ENUM", 7, null);
    public static final EnumC1686hk k = new EnumC1686hk("MESSAGE", 8, null);
    public final Object b;

    public EnumC1686hk(String str, int i2, Serializable serializable) {
        super(str, i2);
        this.b = serializable;
    }
}
