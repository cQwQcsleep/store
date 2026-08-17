package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sO, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2594sO extends G {
    public final String g;
    public final String h;
    public final String i;
    public final boolean j;

    public C2594sO(int i, String str, String str2, String str3, boolean z) {
        super(i);
        this.g = str;
        this.h = str2;
        this.i = str3;
        this.j = z;
    }

    @Override // com.android.tools.r8.internal.G
    public final G a(RC rc) {
        return new C2594sO(this.a, this.g, this.h, this.i, this.j).a(this);
    }

    @Override // com.android.tools.r8.internal.G
    public final void a(XO xo) {
        xo.a(this.a, this.g, this.h, this.i, this.j);
        b(xo);
    }

    @Override // com.android.tools.r8.internal.G
    public final int a() {
        return 5;
    }
}
