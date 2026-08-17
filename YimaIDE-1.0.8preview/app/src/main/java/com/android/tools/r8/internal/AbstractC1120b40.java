package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.b40, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1120b40 extends AbstractC2624sj0 {
    public static final C1034a40 c = new C1034a40(C2427qS.c());
    public static final C1034a40 d = new C1034a40(C2427qS.a());
    public final C2427qS b;

    public AbstractC1120b40(C2427qS c2427qS) {
        this.b = c2427qS;
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public final boolean I() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public final C2427qS N() {
        return this.b;
    }

    public AbstractC2624sj0 P() {
        C2427qS c2427qS = this.b;
        C2427qS c2427qSB = C2427qS.b();
        c2427qS.getClass();
        C2427qS c2427qS2 = C2427qS.c;
        if (c2427qS == c2427qS2) {
            c2427qS = c2427qSB;
        } else if (c2427qSB != c2427qS2 && c2427qS != c2427qSB) {
            c2427qS = C2427qS.d;
        }
        return a(c2427qS);
    }

    public abstract AbstractC1120b40 a(AbstractC1120b40 abstractC1120b40, C0333y c0333y);

    public abstract AbstractC1120b40 a(C2427qS c2427qS);

    public abstract com.android.tools.r8.graph.I2 b(com.android.tools.r8.graph.B1 b1);

    public AbstractC1120b40 b(C2427qS c2427qS) {
        return a(this.b.a(c2427qS));
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public final AbstractC1120b40 d() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public boolean equals(Object obj) {
        throw new Kk0("Should be implemented on each sub type");
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public int hashCode() {
        throw new Kk0("Should be implemented on each sub type");
    }
}
