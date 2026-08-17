package com.android.tools.r8.internal;

import java.io.Serializable;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1700hv extends AbstractC2306p1 implements Serializable {
    public static final C1700hv c;
    public static final C1700hv d;
    public final transient AbstractC0551Hu b;

    static {
        int i = AbstractC0551Hu.c;
        c = new C1700hv(P40.e);
        d = new C1700hv(new Bc0(K10.d));
    }

    public C1700hv(AbstractC0551Hu abstractC0551Hu) {
        this.b = abstractC0551Hu;
    }

    public static C1614gv b() {
        return new C1614gv();
    }

    @Override // com.android.tools.r8.internal.AbstractC2306p1
    public final AbstractC1955kv a() {
        if (this.b.isEmpty()) {
            int i = AbstractC2554rv.c;
            return W40.j;
        }
        AbstractC0551Hu abstractC0551Hu = this.b;
        K10 k10 = K10.d;
        return new X40(abstractC0551Hu, J10.b);
    }
}
