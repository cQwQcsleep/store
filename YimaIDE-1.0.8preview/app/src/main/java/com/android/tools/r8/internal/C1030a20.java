package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;

/* JADX INFO: renamed from: com.android.tools.r8.internal.a20, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1030a20 extends AbstractC1454f20 {
    public final C0245l1 E;

    public C1030a20(C0245l1 c0245l1) {
        this.E = c0245l1;
    }

    @Override // com.android.tools.r8.internal.AbstractC1454f20
    public final Object a() {
        return C1030a20.class;
    }

    public final String toString() {
        return "MissingObjectStateForEnumInstance(" + this.E + ")";
    }
}
