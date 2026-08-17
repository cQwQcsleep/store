package com.android.tools.r8.dex.code;

import java.nio.ShortBuffer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class A1 implements InterfaceC0012a {
    public static final /* synthetic */ boolean g = true;
    public final int a;
    public final int b;
    public final ShortBuffer c;
    public int e;
    public int d = 0;
    public boolean f = false;

    public A1(int i, int i2, ShortBuffer shortBuffer) {
        this.b = i;
        this.a = i2;
        this.c = shortBuffer;
    }

    public final int a() {
        if (this.f) {
            this.f = false;
            return this.e;
        }
        int iB = b();
        this.e = iB & 255;
        this.f = true;
        return (iB >> 8) & 255;
    }

    public final int b() {
        boolean z = g;
        if (!z && this.f) {
            x01.a("Unread byte in cache.");
            return 0;
        }
        if (!z && this.d >= this.a) {
            x1f.a();
            return 0;
        }
        short s = this.c.get(this.b + this.d);
        this.d++;
        return s;
    }
}
