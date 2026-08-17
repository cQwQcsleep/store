package com.reandroid.dex.dexopt;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MethodEncodingType {
    public static final MethodEncodingType HOT;
    public static final MethodEncodingType INLINE_CACHE_MEGAMORPHIC_ENCODING;
    public static final MethodEncodingType INLINE_CACHE_MISSING_TYPES_ENCODING;
    public static final MethodEncodingType POST_STARTUP;
    public static final MethodEncodingType STARTUP;
    private static final MethodEncodingType[] VALUES;
    private final int flag;
    private final String name;

    static {
        MethodEncodingType methodEncodingType = new MethodEncodingType(1, "HOT");
        HOT = methodEncodingType;
        MethodEncodingType methodEncodingType2 = new MethodEncodingType(2, "STARTUP");
        STARTUP = methodEncodingType2;
        MethodEncodingType methodEncodingType3 = new MethodEncodingType(4, "POST_STARTUP");
        POST_STARTUP = methodEncodingType3;
        MethodEncodingType methodEncodingType4 = new MethodEncodingType(64, "INLINE_CACHE_MISSING_TYPES_ENCODING");
        INLINE_CACHE_MISSING_TYPES_ENCODING = methodEncodingType4;
        MethodEncodingType methodEncodingType5 = new MethodEncodingType(128, "INLINE_CACHE_MEGAMORPHIC_ENCODING");
        INLINE_CACHE_MEGAMORPHIC_ENCODING = methodEncodingType5;
        VALUES = new MethodEncodingType[]{methodEncodingType, methodEncodingType2, methodEncodingType3, methodEncodingType4, methodEncodingType5};
    }

    private MethodEncodingType(int i, String str) {
        this.flag = i;
        this.name = str;
    }

    public static MethodEncodingType valueOf(String str) {
        for (MethodEncodingType methodEncodingType : VALUES) {
            if (methodEncodingType.name.equals(str)) {
                return methodEncodingType;
            }
        }
        return null;
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public int flag() {
        return this.flag;
    }

    public int hashCode() {
        return this.flag;
    }

    public boolean isPcMapSize() {
        return this == HOT || this == STARTUP || this == POST_STARTUP;
    }

    public String name() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

    public static boolean isPcMapSize(int i) {
        return isPcMapSize(valueOf(i));
    }

    public static boolean isPcMapSize(MethodEncodingType methodEncodingType) {
        return methodEncodingType == null || methodEncodingType.isPcMapSize();
    }

    public static MethodEncodingType valueOf(int i) {
        for (MethodEncodingType methodEncodingType : VALUES) {
            if (methodEncodingType.flag == i) {
                return methodEncodingType;
            }
        }
        return null;
    }
}
