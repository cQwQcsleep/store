package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vD, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2839vD extends G {
    public final XI g;

    public C2839vD(int i, XI xi) {
        super(i);
        this.g = xi;
    }

    @Override // com.android.tools.r8.internal.G
    public final G a(RC rc) {
        return new C2839vD(this.a, rc.a(this.g)).a(this);
    }

    @Override // com.android.tools.r8.internal.G
    public final void a(XO xo) {
        xo.a(this.a, this.g.b());
        b(xo);
    }

    @Override // com.android.tools.r8.internal.G
    public final int a() {
        return 7;
    }
}
