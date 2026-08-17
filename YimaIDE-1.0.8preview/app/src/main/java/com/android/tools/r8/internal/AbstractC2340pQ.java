package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0007c;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.naming.AbstractC3345r0;
import defpackage.hkh;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pQ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2340pQ {
    public final com.android.tools.r8.graph.H2 a(com.android.tools.r8.graph.F2 f2, C0333y c0333y) {
        AbstractC3345r0 abstractC3345r0W = c0333y.w();
        if (d()) {
            if (this instanceof C2206np) {
                f2.getClass();
                hkh.a();
                return null;
            }
            if (this instanceof C0637Lc) {
                C0637Lc c0637Lc = (C0637Lc) this;
                com.android.tools.r8.graph.I2 i2R0 = f2.r0();
                return c0637Lc.c.a(abstractC3345r0W.c(i2R0).toString(), c0333y.d(i2R0), c0333y.a(), c0637Lc.b);
            }
            if (c()) {
                return a().a(f2.r0(), c0333y, abstractC3345r0W);
            }
        }
        com.android.tools.r8.graph.B1 b1A = c0333y.a();
        abstractC3345r0W.getClass();
        f2.getClass();
        if (f2 instanceof com.android.tools.r8.graph.I2) {
            return b1A.c(C0929Wj.b(abstractC3345r0W.c(f2.r0()).toString()));
        }
        if (f2.u0()) {
            return abstractC3345r0W.a(f2.q0());
        }
        if (AbstractC3345r0.a || f2.s0()) {
            return abstractC3345r0W.a(f2.o0());
        }
        x1f.a();
        return null;
    }

    public abstract com.android.tools.r8.graph.H2 a(com.android.tools.r8.graph.I2 i2, InterfaceC0189d1 interfaceC0189d1, AbstractC3345r0 abstractC3345r0);

    public abstract AbstractC2340pQ a(AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2);

    public abstract int b();

    public abstract void b(com.android.tools.r8.utils.structural.o oVar);

    public boolean c() {
        return false;
    }

    public abstract boolean d();

    public abstract boolean e();

    public AbstractC2736u20 a() {
        return null;
    }

    public final void a(com.android.tools.r8.utils.structural.o oVar) {
        ((com.android.tools.r8.utils.structural.q) oVar).a.a(AbstractC0007c.b(b()));
        b(oVar);
    }
}
