package com.android.tools.r8.naming;

/* JADX INFO: renamed from: com.android.tools.r8.naming.t0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3349t0 extends AbstractC3353v0 {
    public final C3351u0[] b = new C3351u0[2];

    public C3349t0() {
        for (int i = 0; i <= 1; i++) {
            this.b[i] = new C3351u0(i);
        }
    }

    public final N0 a(int i, int i2) {
        int i3;
        if (i < 256 && (i3 = i2 - i) >= 0 && i3 <= 1) {
            C3351u0 c3351u0 = this.b[i3];
            c3351u0.getClass();
            return (i < 0 || i >= 256) ? new N0(i, i, true) : c3351u0.a[i];
        }
        return new N0(i, i2, false);
    }
}
