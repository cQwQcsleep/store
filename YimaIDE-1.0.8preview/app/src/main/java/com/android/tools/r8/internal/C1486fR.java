package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fR, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1486fR extends AbstractC1757ic0 {
    public static final /* synthetic */ boolean d = true;
    public final com.android.tools.r8.shaking.k4 b;
    public final com.android.tools.r8.synthesis.J c;

    public C1486fR(C0333y c0333y, com.android.tools.r8.shaking.k4 k4Var) {
        this.b = k4Var;
        this.c = c0333y.a.g();
    }

    @Override // com.android.tools.r8.internal.AbstractC1757ic0
    public final boolean a(com.android.tools.r8.graph.D2 d2) {
        com.android.tools.r8.shaking.k4 k4Var = this.b;
        if (k4Var != null) {
            return !k4Var.a(d2);
        }
        if (d || this.c.b(d2)) {
            return true;
        }
        pe1.a("Expected synthetic, got: ", d2.e1());
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final String f() {
        return "NoDirectRuntimeTypeChecks";
    }
}
