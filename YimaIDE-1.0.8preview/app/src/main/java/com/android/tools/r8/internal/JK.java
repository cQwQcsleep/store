package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.utils.structural.AbstractC3519a;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class JK extends GK {
    public final C0245l1[] b;

    public JK(C0245l1[] c0245l1Arr) {
        this.b = c0245l1Arr;
    }

    @Override // com.android.tools.r8.internal.UK
    public final int a(UK uk, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this.b, ((JK) uk).b);
    }

    @Override // com.android.tools.r8.internal.UK
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        oVar.a(this.b);
    }

    @Override // com.android.tools.r8.internal.UK
    public final int y() {
        return 12;
    }
}
