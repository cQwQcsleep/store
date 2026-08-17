package com.android.tools.r8.graph;

import com.android.tools.r8.graph.S0;
import com.android.tools.r8.internal.AbstractC2004lX;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import com.sun.jna.platform.linux.Fcntl;
import defpackage.fmc;
import java.util.Objects;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class S0 extends O0 {
    public final AbstractC2004lX d;

    public S0(AbstractC2004lX abstractC2004lX) {
        this.d = abstractC2004lX;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.j(new Function() { // from class: hmc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((S0) obj).d;
            }
        });
    }

    @Override // com.android.tools.r8.graph.O0, com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    @Override // com.android.tools.r8.graph.O0
    public final int b(O0 o0, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (S0) o0, new fmc());
    }

    @Override // com.android.tools.r8.graph.O0
    public final void c(com.android.tools.r8.utils.structural.o oVar) {
        fmc fmcVar = new fmc();
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.getClass();
        fmcVar.a(new com.android.tools.r8.utils.structural.p(this, qVar));
    }

    public final int hashCode() {
        return Objects.hashCode(this.d) * 31;
    }

    @Override // com.android.tools.r8.graph.O0
    public final S0 p0() {
        return this;
    }

    @Override // com.android.tools.r8.graph.O0
    public final int q0() {
        return Fcntl.S_IRUSR;
    }

    public final String toString() {
        return String.format("SET_POSITION_FRAME %s", this.d);
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
