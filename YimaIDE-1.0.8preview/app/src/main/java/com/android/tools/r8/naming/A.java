package com.android.tools.r8.naming;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class A implements L, Cloneable {
    public static final /* synthetic */ boolean e = true;
    public int b;
    public int c;
    public final /* synthetic */ B d;

    public A(B b) {
        this.d = b;
        this.b = 0;
        this.c = 1;
    }

    @Override // com.android.tools.r8.naming.L
    public final int a() {
        int i = this.c;
        this.c = i + 1;
        return i;
    }

    @Override // com.android.tools.r8.naming.L
    public final int b() {
        return this.b;
    }

    @Override // com.android.tools.r8.naming.L
    public final int c() {
        int i = this.b;
        this.b = i + 1;
        return i;
    }

    public final Object clone() {
        return new A(this.d, this.c, this.b);
    }

    public A(B b, int i, int i2) {
        this.d = b;
        this.b = i2;
        this.c = i;
    }
}
