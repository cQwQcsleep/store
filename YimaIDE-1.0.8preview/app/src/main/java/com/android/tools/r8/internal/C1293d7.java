package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;

/* JADX INFO: renamed from: com.android.tools.r8.internal.d7, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1293d7 extends AbstractC1832jW {
    public static final C1293d7 a = new C1293d7();

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW a(com.android.tools.r8.graph.I2 i2) {
        int i = AbstractC2554rv.c;
        C1870jv c1870jv = new C1870jv();
        C1870jv c1870jv2 = new C1870jv();
        C1870jv c1870jv3 = new C1870jv();
        c1870jv.a(i2);
        return new C2581sB(c1870jv.a(), c1870jv2.a(), c1870jv3.a(), false, false, false);
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW c() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final boolean d() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW e() {
        int i = AbstractC2554rv.c;
        return new C2581sB(new C1870jv().a(), new C1870jv().a(), new C1870jv().a(), true, false, false);
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW f() {
        int i = AbstractC2554rv.c;
        return new C2581sB(new C1870jv().a(), new C1870jv().a(), new C1870jv().a(), false, true, false);
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW g() {
        int i = AbstractC2554rv.c;
        return new C2581sB(new C1870jv().a(), new C1870jv().a(), new C1870jv().a(), false, false, true);
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW a(C0245l1 c0245l1) {
        int i = AbstractC2554rv.c;
        C1870jv c1870jv = new C1870jv();
        C1870jv c1870jv2 = new C1870jv();
        C1870jv c1870jv3 = new C1870jv();
        c1870jv2.a(c0245l1);
        return new C2581sB(c1870jv.a(), c1870jv2.a(), c1870jv3.a(), false, false, false);
    }

    @Override // com.android.tools.r8.internal.AbstractC1832jW
    public final AbstractC1832jW a(AbstractC1133bC abstractC1133bC) {
        int i = AbstractC2554rv.c;
        C1870jv c1870jv = new C1870jv();
        C1870jv c1870jv2 = new C1870jv();
        C1870jv c1870jv3 = new C1870jv();
        c1870jv3.a(abstractC1133bC);
        return new C2581sB(c1870jv.a(), c1870jv2.a(), c1870jv3.a(), false, false, false);
    }
}
