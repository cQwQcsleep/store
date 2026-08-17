package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class O5 implements InterfaceC2458ql0 {
    public static final O5 b = new O5(null);
    public static final O5 c = new O5(C3050xi0.j);
    public static final O5 d = new O5(C3050xi0.k);
    public static final O5 e = new O5(C3050xi0.l);
    public static final O5 f = new O5(C3050xi0.m);
    public static final O5 g = new O5(C3050xi0.e("java/lang/Object"));
    public static final O5 h = new O5(C3050xi0.e);
    public final C3050xi0 a;

    public O5(C3050xi0 c3050xi0) {
        this.a = c3050xi0;
    }

    public final int a() {
        C3050xi0 c3050xi0 = this.a;
        return (c3050xi0 == C3050xi0.l || c3050xi0 == C3050xi0.m) ? 2 : 1;
    }

    public final boolean b() {
        C3050xi0 c3050xi0 = this.a;
        if (c3050xi0 != null) {
            return c3050xi0.c() == 10 || this.a.c() == 9;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof O5)) {
            return false;
        }
        C3050xi0 c3050xi0 = this.a;
        if (c3050xi0 == null) {
            return ((O5) obj).a == null;
        }
        return c3050xi0.equals(((O5) obj).a);
    }

    public final int hashCode() {
        C3050xi0 c3050xi0 = this.a;
        if (c3050xi0 == null) {
            return 0;
        }
        return c3050xi0.hashCode();
    }

    public final String toString() {
        if (this == b) {
            return ".";
        }
        if (this == h) {
            return "A";
        }
        return this == g ? "R" : this.a.b();
    }
}
