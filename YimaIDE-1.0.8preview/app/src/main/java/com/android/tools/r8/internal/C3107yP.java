package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yP, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3107yP extends G {
    public final String g;
    public final int h;

    public C3107yP(int i, String str) {
        super(197);
        this.g = str;
        this.h = i;
    }

    @Override // com.android.tools.r8.internal.G
    public final G a(RC rc) {
        return new C3107yP(this.h, this.g).a(this);
    }

    @Override // com.android.tools.r8.internal.G
    public final void a(XO xo) {
        xo.a(this.h, this.g);
        b(xo);
    }

    @Override // com.android.tools.r8.internal.G
    public final int a() {
        return 13;
    }
}
