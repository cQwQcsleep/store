package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;

/* JADX INFO: renamed from: com.android.tools.r8.internal.d20, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1284d20 extends AbstractC1454f20 {
    public final int E;
    public final C0245l1 F;

    public C1284d20(int i, C0245l1 c0245l1) {
        this.E = i;
        this.F = c0245l1;
    }

    @Override // com.android.tools.r8.internal.AbstractC1454f20
    public final Object a() {
        return C1284d20.class;
    }

    public final String toString() {
        return "UnsupportedInstanceFieldValueForEnumInstance(ordinal=" + this.E + ", instance field=" + this.F.m0() + ")";
    }
}
