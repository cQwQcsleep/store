package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.utils.structural.AbstractC3519a;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class NK extends OK {
    public final int d;

    public NK(int i, int i2) {
        super(i);
        this.d = i2;
    }

    @Override // com.android.tools.r8.internal.OK
    public final AbstractC2004lX a(C0322w2 c0322w2, boolean z) {
        AbstractC2004lX.a aVarA = (z ? AbstractC2004lX.c.s() : AbstractC2004lX.b.s()).a(c0322w2);
        aVarA.e = z;
        return aVarA.c().a(this.d).a();
    }

    @Override // com.android.tools.r8.internal.OK
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        ((com.android.tools.r8.utils.structural.q) oVar).a.a(this.d);
    }

    @Override // com.android.tools.r8.internal.OK
    public final boolean b() {
        return false;
    }

    @Override // com.android.tools.r8.internal.OK
    public final int a() {
        return 0;
    }

    @Override // com.android.tools.r8.internal.OK
    public final int a(OK ok, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this.d, ((NK) ok).d);
    }
}
