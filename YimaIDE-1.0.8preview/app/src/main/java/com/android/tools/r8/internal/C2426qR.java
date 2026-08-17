package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qR, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2426qR extends AbstractC1757ic0 {
    public final C2752uB.h b;

    public C2426qR(C0333y c0333y) {
        this.b = c0333y.M().S();
    }

    @Override // com.android.tools.r8.internal.AbstractC1757ic0
    public final boolean a(com.android.tools.r8.graph.D2 d2) {
        return !d2.isInterface();
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final String f() {
        return "NoInterfaces";
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final boolean l() {
        return this.b.c;
    }
}
