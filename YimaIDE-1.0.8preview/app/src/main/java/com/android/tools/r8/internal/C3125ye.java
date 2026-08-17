package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ye, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3125ye extends AbstractC0353Ae {
    @Override // com.android.tools.r8.internal.AbstractC0353Ae
    public final AbstractC0353Ae a(Comparable comparable, Comparable comparable2) {
        int iCompareTo = comparable.compareTo(comparable2);
        if (iCompareTo < 0) {
            return AbstractC0353Ae.b;
        }
        return iCompareTo > 0 ? AbstractC0353Ae.c : AbstractC0353Ae.a;
    }

    @Override // com.android.tools.r8.internal.AbstractC0353Ae
    public final int a() {
        return 0;
    }
}
