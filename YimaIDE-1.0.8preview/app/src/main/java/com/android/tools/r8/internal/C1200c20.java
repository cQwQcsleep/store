package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;

/* JADX INFO: renamed from: com.android.tools.r8.internal.c20, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1200c20 extends AbstractC1454f20 {
    public final C0322w2 E;
    public final AbstractC2624sj0 F;
    public final AbstractC2624sj0 G;

    public C1200c20(C0322w2 c0322w2, AbstractC2624sj0 abstractC2624sj0, AbstractC2624sj0 abstractC2624sj1) {
        this.E = c0322w2;
        this.F = abstractC2624sj0;
        this.G = abstractC2624sj1;
    }

    @Override // com.android.tools.r8.internal.AbstractC1454f20
    public final Object a() {
        return AbstractC0551Hu.a(C1200c20.class, this.E);
    }

    public final String toString() {
        String strM0 = this.E.m0();
        AbstractC2624sj0 abstractC2624sj0 = this.F;
        String strM1 = abstractC2624sj0.w() ? abstractC2624sj0.b().Q().m0() : abstractC2624sj0.toString();
        AbstractC2624sj0 abstractC2624sj1 = this.G;
        return "NonComparableElements(" + strM0 + " - " + strM1 + " vs " + (abstractC2624sj1.w() ? abstractC2624sj1.b().Q().m0() : abstractC2624sj1.toString()) + ")";
    }
}
