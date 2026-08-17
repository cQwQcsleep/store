package com.android.tools.r8.naming;

/* JADX INFO: renamed from: com.android.tools.r8.naming.k0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3332k0 implements L {
    public int b = 0;
    public int c = 1;

    @Override // com.android.tools.r8.naming.L
    public final int a() {
        int i = this.c;
        this.c = i + 1;
        return i;
    }

    @Override // com.android.tools.r8.naming.L
    public final int b() {
        return this.b;
    }

    @Override // com.android.tools.r8.naming.L
    public final int c() {
        int i = this.b;
        this.b = i + 1;
        return i;
    }
}
