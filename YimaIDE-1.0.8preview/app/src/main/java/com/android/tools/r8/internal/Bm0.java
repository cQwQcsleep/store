package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Bm0 extends AbstractC2005lY {
    public static final Bm0 c = new Bm0();

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public boolean M() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public int O() {
        return 2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2005lY
    public String P() {
        throw new Kk0("Unexpected attempt to get descriptor of " + this);
    }

    @Override // com.android.tools.r8.internal.AbstractC2005lY
    public String Q() {
        throw new Kk0("Unexpected attempt to get type name of " + this);
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public final boolean equals(Object obj) {
        return this == obj;
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public int hashCode() {
        return System.identityHashCode(c);
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public String toString() {
        return "WIDE";
    }
}
