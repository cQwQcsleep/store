package com.android.tools.r8.shaking;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class T0 extends V0 {
    public static final /* synthetic */ boolean c = true;
    public final com.android.tools.r8.graph.D2 b;

    public T0(com.android.tools.r8.graph.D2 d2) {
        if (c || !d2.isInterface() || d2.f.J()) {
            this.b = d2;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.shaking.V0
    public final com.android.tools.r8.graph.D2 a() {
        return this.b;
    }
}
