package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class BY implements CN {
    public final C2274of a;
    public final CN b;

    public BY(C2274of c2274of) {
        BN bn = BN.a;
        this.a = c2274of;
        this.b = bn;
    }

    @Override // com.android.tools.r8.internal.CN
    public final void a(com.android.tools.r8.graph.B5 b5, com.android.tools.r8.graph.H0 h0) {
        this.a.a(b5, h0);
        this.b.a(b5, h0);
    }

    @Override // com.android.tools.r8.internal.CN
    public final void a(C0333y c0333y, JN jn) {
        this.a.a(c0333y);
        this.b.a(c0333y, jn);
    }
}
