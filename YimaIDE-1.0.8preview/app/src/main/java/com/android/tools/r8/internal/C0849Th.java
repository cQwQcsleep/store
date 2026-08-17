package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Th, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0849Th {
    public static final C0849Th e = new C0849Th(-1, false, 0, 0);
    public final int a;
    public final boolean b;
    public final int c;
    public final int d;

    public C0849Th(int i, boolean z, int i2, int i3) {
        this.a = i;
        this.b = z;
        this.c = i2;
        this.d = i3;
    }

    public final String toString() {
        int i = this.a;
        boolean z = this.b;
        int i2 = this.d;
        int i3 = this.c;
        return "pc=" + i + ", converted=" + z + ", cost=" + i2 + ", methods=" + i3 + ", saved=" + (i2 - i) + ", overhead=" + C0901Vh.a(i, i3, i2);
    }
}
