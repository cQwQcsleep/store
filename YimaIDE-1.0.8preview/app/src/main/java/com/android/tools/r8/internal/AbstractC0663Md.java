package com.android.tools.r8.internal;

import defpackage.go7;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Md, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0663Md {
    public int a;
    public final int b = 100;
    public final int c = Integer.MAX_VALUE;

    public abstract TN a(InterfaceC2346pW interfaceC2346pW, C0415Co c0415Co);

    public final void a() throws RB {
        if (this.a < this.b) {
            return;
        }
        go7.a("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    public abstract void a(int i);

    public abstract void a(int i, SN sn, C0415Co c0415Co);

    public abstract void a(H0 h0, C0415Co c0415Co);

    public abstract int b();

    public abstract void b(int i);

    public abstract int c(int i);

    public abstract boolean c();

    public abstract Q7 d();

    public abstract boolean d(int i);

    public abstract double e();

    public abstract int f();

    public abstract int g();

    public abstract long h();

    public abstract float i();

    public abstract int j();

    public abstract long k();

    public abstract int l();

    public abstract int m();

    public abstract long n();

    public abstract int o();

    public abstract long p();

    public abstract String q();

    public abstract String r();

    public abstract int s();

    public abstract int t();

    public abstract long u();
}
