package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class TA extends CQ {
    public static final /* synthetic */ int o = 0;
    public final InterfaceC1375e6 n;

    public TA(C0333y c0333y, Z5 z5, C1291d6 c1291d6) {
        super(c0333y, CQ.j, z5.b, CQ.l, z5);
        this.n = c1291d6;
    }

    @Override // com.android.tools.r8.internal.CQ, com.android.tools.r8.internal.AbstractC1511fi, com.android.tools.r8.internal.XR
    public final C0322w2 e(C0322w2 c0322w2) {
        return (C0322w2) this.i.b(c0322w2, (C0322w2) this.n.b(c0322w2, c0322w2));
    }

    @Override // com.android.tools.r8.internal.CQ, com.android.tools.r8.internal.AbstractC1511fi, com.android.tools.r8.internal.XR
    public final C0322w2 f(C0322w2 c0322w2) {
        return (C0322w2) this.n.c(c0322w2, (C0322w2) this.i.c(c0322w2, c0322w2));
    }
}
