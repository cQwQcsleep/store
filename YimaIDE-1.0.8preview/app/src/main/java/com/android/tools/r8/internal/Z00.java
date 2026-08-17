package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Z00 {
    public static final /* synthetic */ boolean g = true;
    public final int a;
    public final boolean b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public final boolean f;

    public Z00(int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.a = i;
        this.b = z;
        this.c = z2;
        this.d = z3;
        this.e = z4;
        this.f = z5;
        if (g) {
            return;
        }
        g();
    }

    public int a(boolean z, C1028a10 c1028a10) {
        int i = this.a;
        if (i != 12) {
            if (i != 27) {
                if (i != 30 && i != 44) {
                    if (i != 49) {
                        if (i != 50) {
                            return 1;
                        }
                        return Y6.a(this.e) + 2;
                    }
                }
            }
            return 2;
        }
        return Y6.a(z) + 1;
    }

    public final boolean b() {
        return this.a == 50;
    }

    public boolean c() {
        return this instanceof C2221o10;
    }

    public boolean d() {
        return (f() || b()) ? false : true;
    }

    public final boolean e() {
        return this.b;
    }

    public boolean f() {
        return this.a <= 17;
    }

    public void g() {
        if (g || this.a < 51) {
            return;
        }
        x1f.a();
    }

    public final int h() {
        int i = this.a;
        if (this.b) {
            i |= Fcntl.S_IRUSR;
        }
        if (this.c) {
            i |= 512;
        }
        if (this.d) {
            i |= Fcntl.S_ISGID;
        }
        if (this.e) {
            i |= Fcntl.S_ISUID;
        }
        return this.f ? i | 4096 : i;
    }

    public boolean a(boolean z) {
        if (f()) {
            return z || this.f;
        }
        return false;
    }

    public C2221o10 a() {
        return null;
    }
}
