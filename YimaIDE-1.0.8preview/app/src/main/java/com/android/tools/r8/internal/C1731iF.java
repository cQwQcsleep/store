package com.android.tools.r8.internal;

import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.iF, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1731iF extends AbstractC2072mF {
    public final C1900kF b;

    public C1731iF(C1900kF c1900kF) {
        this.b = c1900kF;
    }

    @Override // com.android.tools.r8.internal.AbstractC2072mF
    public final TG a(TG tg) {
        TG tgA = this.b.a(tg);
        TG tg2 = TG.b;
        RG rg = new RG(false);
        for (SG sg : (SG[]) SG.g.clone()) {
            if (!tgA.a.contains(sg) || !tg.a.contains(sg)) {
                rg.b.add(sg);
            }
        }
        return rg.a();
    }

    @Override // com.android.tools.r8.internal.AbstractC2072mF
    public final Set b() {
        return this.b.b();
    }

    @Override // com.android.tools.r8.internal.AbstractC2072mF
    public final AbstractC2554rv a() {
        int i = AbstractC2554rv.c;
        C1870jv c1870jv = new C1870jv();
        c1870jv.a((Iterable) AbstractC2554rv.a(ZE.a, C1220cF.a, UE.a, C1050aF.a, WE.a, YE.a, new AbstractC1560gF[0]));
        c1870jv.a((Iterable) this.b.b);
        return c1870jv.a();
    }
}
