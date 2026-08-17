package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class GG extends AbstractC2928wG {
    public AbstractC1484fP f;
    public AbstractC1484fP g;
    public AbstractC1484fP h;
    public AbstractC1484fP i;
    public AbstractC1484fP j;

    public GG() {
        C1230cP c1230cP = AbstractC1484fP.a;
        this.f = c1230cP;
        this.g = c1230cP;
        this.h = c1230cP;
        this.i = c1230cP;
        this.j = c1230cP;
    }

    @Override // com.android.tools.r8.internal.AbstractC2928wG
    public final AbstractC2928wG b() {
        return this;
    }

    public final HG c() {
        HG hg = new HG(a(), this.c, this.d, this.f, this.g, this.h, this.i, this.e, this.j);
        return hg.a() ? HG.k : hg;
    }
}
