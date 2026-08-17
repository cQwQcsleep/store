package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class VO extends IndexOutOfBoundsException {
    public final String b;
    public final String c;
    public final String d;
    public final int e;

    public VO(int i, String str, String str2, String str3) {
        super("Method too large: " + str + "." + str2 + " " + str3);
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = i;
    }
}
