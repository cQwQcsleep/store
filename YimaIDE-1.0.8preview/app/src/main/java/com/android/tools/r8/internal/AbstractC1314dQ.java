package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.dQ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1314dQ {
    public abstract int a();

    public abstract Object b();

    public final boolean equals(Object obj) {
        if (obj instanceof AbstractC1314dQ) {
            AbstractC1314dQ abstractC1314dQ = (AbstractC1314dQ) obj;
            if (a() == abstractC1314dQ.a() && WU.a(b(), abstractC1314dQ.b())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        Object objB = b();
        return a() ^ (objB == null ? 0 : objB.hashCode());
    }

    public final String toString() {
        String strValueOf = String.valueOf(b());
        int iA = a();
        if (iA == 1) {
            return strValueOf;
        }
        return strValueOf + " x " + iA;
    }
}
