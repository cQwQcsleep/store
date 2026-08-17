package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class BJ extends G {
    public final Object g;

    public BJ(Object obj) {
        super(18);
        this.g = obj;
    }

    @Override // com.android.tools.r8.internal.G
    public final G a(RC rc) {
        return new BJ(this.g).a(this);
    }

    @Override // com.android.tools.r8.internal.G
    public final void a(XO xo) {
        xo.a(this.g);
        b(xo);
    }

    @Override // com.android.tools.r8.internal.G
    public final int a() {
        return 9;
    }
}
