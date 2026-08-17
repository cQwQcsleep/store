package com.android.tools.r8.graph;

import com.android.tools.r8.dex.C0157u;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.CJ;
import com.android.tools.r8.utils.structural.AbstractC3519a;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class N0 extends O0 {
    public final int d;

    public N0(int i) {
        this.d = i;
    }

    @Override // com.android.tools.r8.graph.O0, com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    @Override // com.android.tools.r8.graph.O0
    public final void a(C0157u c0157u, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        c0157u.b(2);
        byte[] bArrA = CJ.a(this.d);
        c0157u.a(bArrA.length);
        c0157u.e.put(bArrA, 0, bArrA.length);
    }

    @Override // com.android.tools.r8.graph.O0
    public final int b(O0 o0, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this.d, ((N0) o0).d);
    }

    @Override // com.android.tools.r8.graph.O0
    public final void c(com.android.tools.r8.utils.structural.o oVar) {
        ((com.android.tools.r8.utils.structural.q) oVar).a.a(this.d);
    }

    public final int hashCode() {
        return (this.d * 7) + 2;
    }

    @Override // com.android.tools.r8.graph.O0
    public final N0 n0() {
        return this;
    }

    @Override // com.android.tools.r8.graph.O0
    public final int q0() {
        return 2;
    }

    public final String toString() {
        return "ADVANCE_LINE " + this.d;
    }

    @Override // com.android.tools.r8.graph.O0, com.android.tools.r8.utils.structural.x
    public final /* bridge */ /* synthetic */ int a(com.android.tools.r8.utils.structural.x xVar, AbstractC3519a abstractC3519a) {
        return a((O0) xVar, abstractC3519a);
    }

    @Override // com.android.tools.r8.graph.O0
    public final void a(V0 v0) {
        v0.a(this);
    }
}
