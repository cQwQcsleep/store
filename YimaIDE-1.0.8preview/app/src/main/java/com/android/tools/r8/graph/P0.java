package com.android.tools.r8.graph;

import com.android.tools.r8.dex.C0157u;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.utils.structural.AbstractC3519a;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class P0 extends O0 {
    public final int d;

    public P0(int i) {
        this.d = i;
    }

    @Override // com.android.tools.r8.graph.O0, com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    @Override // com.android.tools.r8.graph.O0
    public final void a(C0157u c0157u, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        c0157u.b(5);
        c0157u.c(this.d);
    }

    @Override // com.android.tools.r8.graph.O0
    public final int b(O0 o0, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this.d, ((P0) o0).d);
    }

    @Override // com.android.tools.r8.graph.O0
    public final void c(com.android.tools.r8.utils.structural.o oVar) {
        ((com.android.tools.r8.utils.structural.q) oVar).a.a(this.d);
    }

    public final int hashCode() {
        return (this.d * 7) + 5;
    }

    @Override // com.android.tools.r8.graph.O0
    public final int q0() {
        return 5;
    }

    @Override // com.android.tools.r8.graph.O0
    public final boolean r0() {
        return true;
    }

    public final String toString() {
        return "END_LOCAL " + this.d;
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
