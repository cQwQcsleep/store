package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bA, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1131bA {
    public static final /* synthetic */ boolean b = true;
    public int a;

    public C1131bA(int i) {
        d(i);
    }

    public void a(int i) {
        if (b || i > 0) {
            this.a -= i;
        } else {
            x1f.a();
        }
    }

    public int b() {
        int i = this.a;
        c(1);
        return i;
    }

    public void c(int i) {
        if (b || i >= 0) {
            this.a += i;
        } else {
            x1f.a();
        }
    }

    public int d() {
        c(1);
        return a();
    }

    public C1131bA() {
    }

    public final int b(int i) {
        int i2 = this.a;
        c(i);
        return i2;
    }

    public void d(int i) {
        this.a = i;
    }

    public int a() {
        return this.a;
    }

    public void c() {
        c(1);
    }

    public void a(boolean z) {
        if (z) {
            c();
        }
    }
}
