package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Ll0 extends G {
    public final int g;

    public Ll0(int i, int i2) {
        super(i);
        this.g = i2;
    }

    @Override // com.android.tools.r8.internal.G
    public final G a(RC rc) {
        return new Ll0(this.a, this.g).a(this);
    }

    @Override // com.android.tools.r8.internal.G
    public final void a(XO xo) {
        xo.d(this.a, this.g);
        b(xo);
    }

    @Override // com.android.tools.r8.internal.G
    public final int a() {
        return 2;
    }
}
