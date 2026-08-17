package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.tj0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2710tj0 extends G {
    public final String g;

    public C2710tj0(int i, String str) {
        super(i);
        this.g = str;
    }

    @Override // com.android.tools.r8.internal.G
    public final G a(RC rc) {
        return new C2710tj0(this.a, this.g).a(this);
    }

    @Override // com.android.tools.r8.internal.G
    public final void a(XO xo) {
        xo.c(this.a, this.g);
        b(xo);
    }

    @Override // com.android.tools.r8.internal.G
    public final int a() {
        return 3;
    }
}
