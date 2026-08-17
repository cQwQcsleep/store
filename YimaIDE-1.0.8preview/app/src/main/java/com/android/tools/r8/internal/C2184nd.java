package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2184nd extends IndexOutOfBoundsException {
    public final String b;
    public final int c;

    public C2184nd(int i, String str) {
        super(F40.a("Class too large: ", str));
        this.b = str;
        this.c = i;
    }
}
