package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ip, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1779ip extends G {
    public final String g;
    public final String h;
    public final String i;

    public C1779ip(int i, String str, String str2, String str3) {
        super(i);
        this.g = str;
        this.h = str2;
        this.i = str3;
    }

    @Override // com.android.tools.r8.internal.G
    public final G a(RC rc) {
        return new C1779ip(this.a, this.g, this.h, this.i).a(this);
    }

    @Override // com.android.tools.r8.internal.G
    public final void a(XO xo) {
        xo.a(this.a, this.g, this.h, this.i);
        b(xo);
    }

    @Override // com.android.tools.r8.internal.G
    public final int a() {
        return 4;
    }
}
