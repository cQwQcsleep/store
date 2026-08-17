package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.aU, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1065aU extends AbstractC1403eU {
    public final /* synthetic */ C1318dU g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1065aU(C1318dU c1318dU) {
        super(c1318dU.j);
        this.g = c1318dU;
        this.c = c1318dU.j();
    }

    @Override // com.android.tools.r8.internal.AbstractC1403eU
    public final void d() {
        ST stC = this.c.c();
        this.c = stC;
        C1318dU c1318dU = this.g;
        if (c1318dU.f || stC == null) {
            return;
        }
        C1574gU c1574gU = c1318dU.j;
        Object obj = stC.b;
        Object obj2 = c1318dU.d;
        c1574gU.getClass();
        if (((Comparable) obj).compareTo(obj2) >= 0) {
            this.c = null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC1403eU
    public final void e() {
        ST stE = this.b.e();
        this.b = stE;
        C1318dU c1318dU = this.g;
        if (c1318dU.e || stE == null) {
            return;
        }
        C1574gU c1574gU = c1318dU.j;
        Object obj = stE.b;
        Object obj2 = c1318dU.c;
        c1574gU.getClass();
        if (((Comparable) obj).compareTo(obj2) < 0) {
            this.b = null;
        }
    }
}
