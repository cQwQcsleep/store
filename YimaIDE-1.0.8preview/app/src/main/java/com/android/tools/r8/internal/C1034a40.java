package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.a40, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1034a40 extends AbstractC1120b40 {
    public C1034a40(C2427qS c2427qS) {
        super(c2427qS);
    }

    @Override // com.android.tools.r8.internal.AbstractC1120b40
    public final AbstractC1120b40 a(C2427qS c2427qS) {
        return c2427qS.g() ? AbstractC1120b40.c : AbstractC1120b40.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC1120b40
    public final com.android.tools.r8.graph.I2 b(com.android.tools.r8.graph.B1 b1) {
        return com.android.tools.r8.graph.B1.e6;
    }

    @Override // com.android.tools.r8.internal.AbstractC1120b40, com.android.tools.r8.internal.AbstractC2624sj0
    public final boolean equals(Object obj) {
        return this == obj;
    }

    @Override // com.android.tools.r8.internal.AbstractC1120b40, com.android.tools.r8.internal.AbstractC2624sj0
    public final int hashCode() {
        return System.identityHashCode(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public final String toString() {
        return this.b.toString() + " " + com.android.tools.r8.graph.B1.e6.toString();
    }

    @Override // com.android.tools.r8.internal.AbstractC1120b40
    public final AbstractC1120b40 a(AbstractC1120b40 abstractC1120b40, C0333y c0333y) {
        return abstractC1120b40.b(this.b);
    }
}
