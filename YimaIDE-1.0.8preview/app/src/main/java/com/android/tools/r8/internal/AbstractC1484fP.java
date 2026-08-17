package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fP, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1484fP {
    public static final C1230cP a = new C1230cP();
    public static final C1313dP b = new C1313dP();
    public static final C1398eP c = new C1398eP();

    public static AbstractC1484fP a(boolean z) {
        return z ? b : c;
    }

    public final boolean equals(Object obj) {
        return this == obj;
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }
}
