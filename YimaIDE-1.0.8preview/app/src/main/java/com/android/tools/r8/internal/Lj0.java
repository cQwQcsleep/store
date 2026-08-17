package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Lj0 implements Mj0 {
    public static final /* synthetic */ boolean c = true;
    public final com.android.tools.r8.graph.I2 a;
    public final C1201c3 b;

    public Lj0(C1201c3 c1201c3, com.android.tools.r8.graph.I2 i2) {
        boolean z = c;
        if (!z && c1201c3 == null) {
            x1f.a();
            throw null;
        }
        if (!z && i2 == null) {
            x1f.a();
            throw null;
        }
        this.b = c1201c3;
        this.a = i2;
    }

    @Override // com.android.tools.r8.internal.Mj0
    public final com.android.tools.r8.graph.I2 a() {
        return this.a;
    }

    public final String toString() {
        return "this:" + this.a;
    }
}
