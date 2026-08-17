package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum OM {
    c("UNKNOWN"),
    d("OBFUSCATED_TYPE_NAME_AS_KEY"),
    e("OBFUSCATED_TYPE_NAME_AS_KEY_WITH_PARTITIONS");

    public static final OM f = e;
    public final int b;

    OM(String str) {
        this.b = i;
    }

    public static OM a() {
        return f;
    }

    public static OM b(int i) {
        if (i != 0) {
            return i != 1 ? c : e;
        }
        return d;
    }
}
