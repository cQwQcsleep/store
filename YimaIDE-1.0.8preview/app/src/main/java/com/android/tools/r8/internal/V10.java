package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class V10 extends AbstractC1454f20 {
    public final C0322w2 E;

    public V10(C0322w2 c0322w2) {
        this.E = c0322w2;
    }

    @Override // com.android.tools.r8.internal.AbstractC1454f20
    public final Object a() {
        return V10.class;
    }

    public final String toString() {
        return "IllegalInvokeWithImpreciseParameterType(" + this.E.m0() + ")";
    }
}
