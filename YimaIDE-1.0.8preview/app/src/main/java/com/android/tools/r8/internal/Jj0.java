package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Jj0 implements Mj0 {
    public static final /* synthetic */ boolean b = true;
    public final com.android.tools.r8.graph.I2 a;

    public Jj0(com.android.tools.r8.graph.I2 i2) {
        if (b || i2 != null) {
            this.a = i2;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.Mj0
    public final com.android.tools.r8.graph.I2 a() {
        return this.a;
    }

    public final String toString() {
        return this.a.toString();
    }
}
