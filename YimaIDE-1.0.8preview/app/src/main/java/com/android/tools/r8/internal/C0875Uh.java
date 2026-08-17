package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Uh, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0875Uh {
    public static final /* synthetic */ boolean d = true;
    public final int a;
    public int b = 0;
    public int c = 0;

    public C0875Uh(int i) {
        if (d || i >= 0) {
            this.a = i;
        } else {
            x1f.a();
            throw null;
        }
    }

    public final String toString() {
        int i = this.a;
        int i2 = this.b;
        int i3 = this.c;
        return "pc=" + i + ", cost=" + i2 + ", methods=" + i3 + ", saved=" + (i2 - i) + ", overhead=" + C0901Vh.a(i, i3, i2);
    }
}
