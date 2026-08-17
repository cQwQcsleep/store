package com.android.tools.r8.graph;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3 extends B3.i {
    public static final /* synthetic */ boolean b = true;
    public final I2 a;

    public C3(I2 i2) {
        boolean z = b;
        if (!z && i2 == null) {
            x1f.a();
            throw null;
        }
        if (z || i2.T0()) {
            this.a = i2;
        } else {
            x01.a(i2.Z0());
            throw null;
        }
    }

    @Override // com.android.tools.r8.graph.B3.i
    public final C3 c() {
        return this;
    }

    @Override // com.android.tools.r8.graph.B3.i
    public final B3.a f() {
        if (b || !this.a.W0()) {
            return new B3.a(this, B3.k.b);
        }
        x1f.a();
        return null;
    }
}
