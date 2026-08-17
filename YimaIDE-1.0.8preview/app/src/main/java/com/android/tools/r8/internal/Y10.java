package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Y10 extends AbstractC1454f20 {
    public final C0245l1 E;
    public final int F;

    public Y10(C0245l1 c0245l1) {
        this.E = c0245l1;
        this.F = -1;
    }

    @Override // com.android.tools.r8.internal.AbstractC1454f20
    public final Object a() {
        return Y10.class;
    }

    public final String toString() {
        String strM0;
        C0245l1 c0245l1 = this.E;
        if (c0245l1 != null) {
            strM0 = c0245l1.m0();
        } else {
            strM0 = "ordinal=" + this.F;
        }
        return "MissingDynamicType(" + strM0 + ")";
    }

    public Y10(int i) {
        this.F = i;
        this.E = null;
    }
}
