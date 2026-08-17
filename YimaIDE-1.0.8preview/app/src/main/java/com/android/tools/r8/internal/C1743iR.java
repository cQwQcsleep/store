package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.iR, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1743iR extends AbstractC1757ic0 {
    public final C0333y b;
    public final V20 c = new V20();

    public C1743iR(C0333y c0333y) {
        this.b = c0333y;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0043  */
    public final boolean a(com.android.tools.r8.graph.E0 e0) {
        boolean z;
        if (this.c.containsKey(e0)) {
            return this.c.a(e0);
        }
        if (e0.e != this.b.a().a2) {
            if (e0.e != this.b.a().f2) {
                com.android.tools.r8.graph.E0 e0D = e0.l1() ? this.b.d(e0.g) : null;
                z = e0D != null && a(e0D);
            }
        }
        this.c.a(e0, z);
        return z;
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final void e() {
        this.c.clear();
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final String f() {
        return "NoEnums";
    }

    @Override // com.android.tools.r8.internal.AbstractC1757ic0
    public final boolean a(com.android.tools.r8.graph.D2 d2) {
        return (d2.r1() || a((com.android.tools.r8.graph.E0) d2)) ? false : true;
    }
}
