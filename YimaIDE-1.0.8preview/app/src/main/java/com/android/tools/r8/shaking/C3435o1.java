package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.internal.C2752uB;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.o1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3435o1 extends AbstractC3420l1 {
    public static final C3435o1 w;
    public static final C3435o1 x;
    public static final /* synthetic */ boolean y = true;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final boolean m;
    public final boolean n;
    public final boolean o;
    public final boolean p;
    public final boolean q;
    public final boolean r;
    public final boolean s;
    public final boolean t;
    public final boolean u;
    public final boolean v;

    static {
        C3425m1 c3425m1 = (C3425m1) new C3425m1().k();
        c3425m1.k = false;
        C3425m1 c3425m1N = c3425m1.j();
        c3425m1N.l = false;
        C3425m1 c3425m1N2 = c3425m1N.j();
        c3425m1N2.m = false;
        C3425m1 c3425m1N3 = c3425m1N2.j();
        c3425m1N3.n = false;
        C3425m1 c3425m1N4 = c3425m1N3.j();
        c3425m1N4.o = false;
        C3425m1 c3425m1N5 = c3425m1N4.j();
        c3425m1N5.p = false;
        C3425m1 c3425m1N6 = c3425m1N5.j();
        c3425m1N6.q = false;
        C3425m1 c3425m1N7 = c3425m1N6.j();
        c3425m1N7.r = false;
        C3425m1 c3425m1N8 = c3425m1N7.j();
        c3425m1N8.s = false;
        C3425m1 c3425m1N9 = c3425m1N8.j();
        c3425m1N9.t = false;
        C3425m1 c3425m1N10 = c3425m1N9.j();
        c3425m1N10.u = false;
        C3425m1 c3425m1N11 = c3425m1N10.j();
        c3425m1N11.v = false;
        C3425m1 c3425m1N12 = c3425m1N11.j();
        c3425m1N12.w = false;
        w = (C3435o1) c3425m1N12.j().a();
        x = (C3435o1) new C3425m1().m().a();
    }

    public C3435o1(C3425m1 c3425m1) {
        super(c3425m1);
        this.j = c3425m1.k;
        this.k = c3425m1.l;
        this.l = c3425m1.m;
        this.m = c3425m1.n;
        this.n = c3425m1.o;
        this.o = c3425m1.p;
        this.p = c3425m1.q;
        this.q = c3425m1.r;
        this.r = c3425m1.s;
        this.s = c3425m1.t;
        this.t = c3425m1.u;
        this.u = c3425m1.v;
        this.v = c3425m1.w;
    }

    public static C3430n1 c() {
        return x.b();
    }

    public final boolean a(B5 b5) {
        return !b5.D().w() && this.r;
    }

    public C3430n1 b() {
        if (y || !equals(w)) {
            return new C3430n1(this);
        }
        x1f.a();
        return null;
    }

    public final boolean g(L0 l0) {
        return c(l0) && this.k;
    }

    public final boolean h(L0 l0) {
        return c(l0) && this.m;
    }

    public final boolean i(L0 l0) {
        return g(l0) && c(l0) && e(l0) && !this.h && this.o;
    }

    public final boolean a(C2752uB c2752uB) {
        return i(c2752uB);
    }

    public C3425m1 a() {
        return new C3425m1(this);
    }
}
