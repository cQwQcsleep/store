package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Z6 extends AbstractC1889k7 {
    public static final Z6 b = new Z6();
    public static final /* synthetic */ boolean c = true;

    @Override // com.android.tools.r8.internal.Cl0
    public final Cl0 a(C0333y c0333y, Cl0 cl0, com.android.tools.r8.graph.I2 i2, AbstractC1589ge0 abstractC1589ge0, Y1 y1) {
        if (cl0.g()) {
            return this;
        }
        if (cl0 instanceof Bk0) {
            return cl0;
        }
        boolean z = c;
        if (!z && !cl0.h()) {
            x1f.a();
            return null;
        }
        if (!z && !cl0.c().j()) {
            x1f.a();
            return null;
        }
        AbstractC2445qf abstractC2445qfF = cl0.c().f();
        abstractC2445qfF.getClass();
        if (abstractC2445qfF instanceof C1334df) {
            return abstractC1589ge0.a(abstractC2445qfF);
        }
        C2427qS c2427qSU = abstractC2445qfF.u();
        return c2427qSU.f() ? Bk0.b : new C1334df(c2427qSU, abstractC2445qfF.n());
    }
}
