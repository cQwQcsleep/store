package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2383pu extends G {
    public final int g;
    public final int h;

    public C2383pu(int i, int i2) {
        super(132);
        this.g = i;
        this.h = i2;
    }

    @Override // com.android.tools.r8.internal.G
    public final G a(RC rc) {
        return new C2383pu(this.g, this.h).a(this);
    }

    @Override // com.android.tools.r8.internal.G
    public final void a(XO xo) {
        xo.a(this.g, this.h);
        b(xo);
    }

    @Override // com.android.tools.r8.internal.G
    public final int a() {
        return 10;
    }
}
