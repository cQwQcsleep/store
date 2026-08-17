package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Zr, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1015Zr {
    public final Iterator a;
    public Map.Entry b;
    public final boolean c;

    public C1015Zr(AbstractC1102as abstractC1102as) {
        C0520Gp c0520Gp = abstractC1102as.e;
        boolean z = c0520Gp.c;
        Rc0 rc0 = c0520Gp.a;
        Iterator c2845vJ = z ? new C2845vJ(new Oc0(((Qc0) rc0.entrySet()).b)) : new Oc0(((Qc0) rc0.entrySet()).b);
        this.a = c2845vJ;
        if (c2845vJ.hasNext()) {
            this.b = (Map.Entry) c2845vJ.next();
        }
        this.c = false;
    }

    public final void a(AbstractC0793Rd abstractC0793Rd) {
        while (true) {
            Map.Entry entry = this.b;
            if (entry == null || ((C1856jk) entry.getKey()).c.g >= 536870912) {
                return;
            }
            C1856jk c1856jk = (C1856jk) this.b.getKey();
            if (this.c && c1856jk.h() == Pm0.k && !c1856jk.m()) {
                Map.Entry entry2 = this.b;
                boolean z = entry2 instanceof AbstractC2674tJ;
                C1258cj c1258cj = c1856jk.c;
                if (z) {
                    int i = c1258cj.g;
                    throw null;
                }
                int i2 = c1258cj.g;
                J0 j0 = (J0) entry2.getValue();
                C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
                c0689Nd.c(1, 3);
                c0689Nd.c(2, 0);
                c0689Nd.f(i2);
                c0689Nd.a(3, j0);
                c0689Nd.c(1, 4);
            } else {
                C0520Gp.a(c1856jk, this.b.getValue(), abstractC0793Rd);
            }
            if (this.a.hasNext()) {
                this.b = (Map.Entry) this.a.next();
            } else {
                this.b = null;
            }
        }
    }
}
