package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Hg0 extends PP {
    public final com.android.tools.r8.synthesis.J b;

    public Hg0(C0333y c0333y) {
        this.b = c0333y.a.g();
    }

    @Override // com.android.tools.r8.internal.PP
    public final Object a(com.android.tools.r8.graph.D2 d2) {
        com.android.tools.r8.synthesis.J j = this.b;
        j.getClass();
        return j.g(d2.e) ? Gg0.b : Gg0.c;
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final String f() {
        return "SyntheticItemsPolicy";
    }
}
