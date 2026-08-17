package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.utils.structural.AbstractC3519a;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class PK extends OK {
    public final AbstractC2004lX d;

    public PK(int i, AbstractC2004lX abstractC2004lX) {
        super(i);
        this.d = abstractC2004lX;
    }

    @Override // com.android.tools.r8.internal.OK
    public final int a(OK ok, AbstractC3519a abstractC3519a) {
        return this.d.a(((PK) ok).d, abstractC3519a);
    }

    @Override // com.android.tools.r8.internal.OK
    public final boolean b() {
        return this.d.k();
    }

    @Override // com.android.tools.r8.internal.OK
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        this.d.a(oVar);
    }

    @Override // com.android.tools.r8.internal.OK
    public final AbstractC2004lX a(C0322w2 c0322w2, boolean z) {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.OK
    public final int a() {
        return 1;
    }
}
