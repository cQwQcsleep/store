package com.android.tools.r8.dex;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class I {
    public final int a;
    public final int b;
    public final int c;

    public I(int i, int i2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    public final int a(C0161y c0161y) {
        if (this.c == 0) {
            return 0;
        }
        c0161y.a((short) this.a);
        c0161y.a((short) 0);
        c0161y.f(this.c);
        c0161y.f(this.b);
        return 1;
    }

    public final int a() {
        return this.b;
    }
}
