package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fa, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1495fa implements A1 {
    public final InterfaceC2576s8 a;
    public final com.android.tools.r8.graph.B5 b;
    public final /* synthetic */ C1580ga c;

    public C1495fa(C1580ga c1580ga, C1240ca c1240ca, com.android.tools.r8.graph.B5 b5) {
        this.c = c1580ga;
        this.a = c1240ca;
        this.b = b5;
    }

    @Override // com.android.tools.r8.internal.A1
    public final AbstractC3159z1 a(Object obj, com.android.tools.r8.graph.I2 i2, Object obj2, Object obj3, AbstractC3159z1 abstractC3159z1) {
        return ((AbstractC1808j9) abstractC3159z1).a((C1240ca) this.a, i2);
    }

    @Override // com.android.tools.r8.internal.A1
    public final AbstractC3159z1 b(Object obj, AbstractC3159z1 abstractC3159z1) {
        AbstractC1808j9 c1419ef = new C1419ef();
        int iF0 = 0;
        if (!this.b.e().z0()) {
            if (this.b.e().q1()) {
                int i = InterfaceC1101ar.a;
                c1419ef = c1419ef.a(0, C1857jk0.c, this.a);
            } else {
                c1419ef = c1419ef.a(0, InterfaceC1101ar.b(this.b.s()), this.a);
            }
            iF0 = 1;
        }
        for (com.android.tools.r8.graph.I2 i2 : this.b.E()) {
            c1419ef = c1419ef.a(iF0, InterfaceC1101ar.a(i2), this.a);
            iF0 += i2.F0();
        }
        return c1419ef;
    }

    @Override // com.android.tools.r8.internal.A1
    public final AbstractC3159z1 a(Object obj, Object obj2, AbstractC3159z1 abstractC3159z1) {
        return (AbstractC1808j9) abstractC3159z1;
    }

    @Override // com.android.tools.r8.internal.A1
    public final Vh0 a(Object obj, AbstractC3159z1 abstractC3159z1) {
        return ((AbstractC3175z9) obj).a((AbstractC1808j9) abstractC3159z1, this.c.a, this.a);
    }

    @Override // com.android.tools.r8.internal.A1
    public final C0434Dh a(Object obj, Vh0 vh0) {
        return new C1409ea((AbstractC3175z9) obj, (C2291oo) vh0);
    }
}
