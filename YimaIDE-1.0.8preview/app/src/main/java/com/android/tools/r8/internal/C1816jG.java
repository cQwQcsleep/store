package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jG, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1816jG extends AbstractC2928wG {
    public AbstractC1484fP f;
    public AbstractC1484fP g;

    public C1816jG() {
        C1230cP c1230cP = AbstractC1484fP.a;
        this.f = c1230cP;
        this.g = c1230cP;
    }

    @Override // com.android.tools.r8.internal.AbstractC2928wG
    public final AbstractC2928wG b() {
        return this;
    }

    public final C1901kG c() {
        C1901kG c1901kG = new C1901kG(a(), this.c, this.d, this.f, this.g, this.e);
        return c1901kG.a() ? C1901kG.h : c1901kG;
    }
}
