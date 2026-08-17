package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nV, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2173nV extends P6 {
    public static final AbstractC2173nV a = new C1916kV();
    public static final AbstractC2173nV b = new C2002lV();
    public static final AbstractC2173nV c = new C2088mV();

    public static AbstractC2173nV a(boolean z) {
        return z ? a : b;
    }

    public static AbstractC2173nV g() {
        return c;
    }

    @Override // com.android.tools.r8.internal.P6
    public final boolean equals(Object obj) {
        return this == obj;
    }

    public abstract int f();

    @Override // com.android.tools.r8.internal.P6
    public final int hashCode() {
        return System.identityHashCode(this);
    }
}
