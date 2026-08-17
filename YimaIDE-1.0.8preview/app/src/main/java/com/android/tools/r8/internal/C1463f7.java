package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.f7, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1463f7 extends AbstractC2003lW {
    public static final C1463f7 b = new C1463f7();

    @Override // com.android.tools.r8.internal.AbstractC2003lW
    public final AbstractC2003lW a(int i, AbstractC1917kW abstractC1917kW) {
        C1873jy c1873jy = new C1873jy(16);
        c1873jy.a(i, abstractC1917kW);
        return c1873jy.isEmpty() ? b : new UR(c1873jy);
    }

    @Override // com.android.tools.r8.internal.AbstractC2003lW
    public final AbstractC2003lW e() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC3159z1
    public final boolean equals(Object obj) {
        return obj == b;
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC2003lW
    public final AbstractC1917kW a(int i) {
        return C1377e7.a;
    }
}
