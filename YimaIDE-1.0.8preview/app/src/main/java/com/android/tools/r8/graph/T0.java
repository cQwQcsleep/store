package com.android.tools.r8.graph;

import com.android.tools.r8.dex.C0157u;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.utils.structural.AbstractC3519a;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class T0 extends O0 {
    public static final /* synthetic */ boolean d = true;

    @Override // com.android.tools.r8.graph.O0, com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    @Override // com.android.tools.r8.graph.O0, com.android.tools.r8.utils.structural.x
    public final /* bridge */ /* synthetic */ int a(com.android.tools.r8.utils.structural.x xVar, AbstractC3519a abstractC3519a) {
        return a((O0) xVar, abstractC3519a);
    }

    @Override // com.android.tools.r8.graph.O0
    public final int b(O0 o0, AbstractC3519a abstractC3519a) {
        if (d || (o0 instanceof T0)) {
            return 0;
        }
        x1f.a();
        return 0;
    }

    @Override // com.android.tools.r8.graph.O0
    public final void c(com.android.tools.r8.utils.structural.o oVar) {
    }

    public final int hashCode() {
        return 7;
    }

    @Override // com.android.tools.r8.graph.O0
    public final int q0() {
        return 7;
    }

    @Override // com.android.tools.r8.graph.O0
    public final boolean r0() {
        return true;
    }

    public final String toString() {
        return "SET_PROLOGUE_END";
    }

    @Override // com.android.tools.r8.graph.O0
    public final void a(C0157u c0157u, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        c0157u.b(7);
    }

    @Override // com.android.tools.r8.graph.O0
    public final void a(V0 v0) {
        v0.a(this);
    }
}
