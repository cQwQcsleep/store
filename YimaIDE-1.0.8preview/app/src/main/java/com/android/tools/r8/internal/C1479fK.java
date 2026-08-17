package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fK, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1479fK extends G {
    public final int g;
    public final XI h;

    public C1479fK(int i, XI xi) {
        super(-1);
        this.g = i;
        this.h = xi;
    }

    @Override // com.android.tools.r8.internal.G
    public final G a(RC rc) {
        return new C1479fK(this.g, rc.a(this.h));
    }

    @Override // com.android.tools.r8.internal.G
    public final void a(XO xo) {
        xo.b(this.g, this.h.b());
    }

    @Override // com.android.tools.r8.internal.G
    public final int a() {
        return 15;
    }
}
