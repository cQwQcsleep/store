package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Z10 extends AbstractC1454f20 {
    public static final /* synthetic */ boolean H = true;
    public final C0245l1 E;
    public final int F;
    public final C0245l1 G;

    public Z10(C0245l1 c0245l1) {
        this.E = null;
        this.F = -1;
        this.G = c0245l1;
    }

    @Override // com.android.tools.r8.internal.AbstractC1454f20
    public final Object a() {
        return Z10.class;
    }

    public final String toString() {
        C0245l1 c0245l1 = this.E;
        if (c0245l1 != null) {
            return "MissingInstanceFieldValueForEnumInstance(enum field=" + c0245l1.m0() + ", instance field=" + this.G.m0() + ")";
        }
        int i = this.F;
        if (i == -1) {
            return "MissingInstanceFieldValueForEnumInstance(Cannot resolve instance field=" + this.G.m0() + ")";
        }
        if (!H && i < 0) {
            x1f.a();
            return null;
        }
        return "MissingInstanceFieldValueForEnumInstance(ordinal=" + i + ", instance field=" + this.G.m0() + ")";
    }

    public Z10(C0245l1 c0245l1, C0245l1 c0245l2) {
        this.E = c0245l2;
        this.F = -1;
        this.G = c0245l1;
    }

    public Z10(int i, C0245l1 c0245l1) {
        this.E = null;
        this.F = i;
        this.G = c0245l1;
    }
}
