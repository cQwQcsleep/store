package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Kj0 implements Mj0 {
    public static final /* synthetic */ boolean b = true;
    public final KQ a;

    public Kj0(KQ kq) {
        if (b || kq != null) {
            this.a = kq;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.Mj0
    public final com.android.tools.r8.graph.I2 a() {
        return this.a.i;
    }

    public final String toString() {
        return "new:" + this.a.i;
    }
}
