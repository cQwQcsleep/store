package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface r extends InterfaceC0370Av {
    Cl0 a(C0333y c0333y, InterfaceC0599Jq interfaceC0599Jq, AbstractC2530rf abstractC2530rf);

    boolean a(B5 b5);

    @Override // com.android.tools.r8.internal.InterfaceC0370Av
    default boolean c() {
        return true;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0370Av
    default r d() {
        return this;
    }

    default boolean g() {
        return false;
    }

    Iterable k();
}
