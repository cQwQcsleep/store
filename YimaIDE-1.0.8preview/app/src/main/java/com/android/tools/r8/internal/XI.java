package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class XI extends G {
    public WI g;

    public XI() {
        super(-1);
    }

    @Override // com.android.tools.r8.internal.G
    public final void a(XO xo) {
        xo.a(b());
    }

    public final WI b() {
        if (this.g == null) {
            this.g = new WI();
        }
        return this.g;
    }

    @Override // com.android.tools.r8.internal.G
    public final int a() {
        return 8;
    }

    @Override // com.android.tools.r8.internal.G
    public final G a(RC rc) {
        return rc.a(this);
    }
}
