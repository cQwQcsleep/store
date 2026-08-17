package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gA, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1555gA extends G {
    public final int g;

    public C1555gA(int i, int i2) {
        super(i);
        this.g = i2;
    }

    @Override // com.android.tools.r8.internal.G
    public final G a(RC rc) {
        return new C1555gA(this.a, this.g).a(this);
    }

    @Override // com.android.tools.r8.internal.G
    public final void a(XO xo) {
        xo.b(this.a, this.g);
        b(xo);
    }

    @Override // com.android.tools.r8.internal.G
    public final int a() {
        return 1;
    }
}
