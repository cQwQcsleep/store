package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.C2752uB;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Y0 extends AbstractC3395g1 {
    public static final Y0 p;
    public static final Y0 q;
    public static final /* synthetic */ boolean r = true;
    public final boolean i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final boolean m;
    public final boolean n;
    public final boolean o;

    static {
        W0 w0K = new W0().i();
        w0K.j = false;
        w0K.k = false;
        w0K.l = false;
        w0K.m = false;
        w0K.n = false;
        w0K.o = false;
        p = (Y0) w0K.a();
        W0 w0 = (W0) new W0().h();
        w0.p = false;
        w0.j = true;
        w0.k = true;
        w0.l = true;
        w0.m = true;
        w0.n = true;
        w0.o = true;
        q = (Y0) w0.a();
    }

    public Y0(W0 w0) {
        super(w0);
        this.i = w0.j;
        this.j = w0.k;
        this.k = w0.l;
        this.l = w0.m;
        this.m = w0.n;
        this.n = w0.o;
        this.o = w0.p;
    }

    public static boolean a(com.android.tools.r8.graph.B1 b1, C2752uB c2752uB, Function function, Function function2) {
        com.android.tools.r8.graph.E0 e0 = (com.android.tools.r8.graph.E0) function.apply(b1.w3);
        return e0 == null || e0.y1() || !((Y0) function2.apply(e0.X())).e(c2752uB);
    }

    public static X0 b() {
        return q.a();
    }

    public final boolean a(C2752uB c2752uB, boolean z) {
        if (z && d(c2752uB) && c2752uB.h.f().l) {
            return !c2752uB.V0 && this.c;
        }
        return true;
    }

    public final X0 a() {
        if (!r && this == p) {
            x1f.a();
            return null;
        }
        return new X0(this);
    }
}
