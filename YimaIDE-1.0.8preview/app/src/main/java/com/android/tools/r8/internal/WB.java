package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class WB extends G {
    public final String g;
    public final String h;
    public final C0497Fs i;
    public final Object[] j;

    public WB(String str, String str2, C0497Fs c0497Fs, Object... objArr) {
        super(186);
        this.g = str;
        this.h = str2;
        this.i = c0497Fs;
        this.j = objArr;
    }

    @Override // com.android.tools.r8.internal.G
    public final G a(RC rc) {
        return new WB(this.g, this.h, this.i, this.j).a(this);
    }

    @Override // com.android.tools.r8.internal.G
    public final void a(XO xo) {
        xo.a(this.g, this.h, this.i, this.j);
        b(xo);
    }

    @Override // com.android.tools.r8.internal.G
    public final int a() {
        return 6;
    }
}
