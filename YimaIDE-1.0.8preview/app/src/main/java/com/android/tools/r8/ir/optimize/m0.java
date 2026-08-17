package com.android.tools.r8.ir.optimize;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.AbstractC2624sj0;
import com.android.tools.r8.internal.C2543rl0;
import com.android.tools.r8.internal.K5;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class m0 implements l0 {
    public final C2543rl0 a;
    public final /* synthetic */ o0 b;

    public m0(o0 o0Var, C2543rl0 c2543rl0) {
        this.b = o0Var;
        this.a = c2543rl0;
    }

    @Override // com.android.tools.r8.ir.optimize.l0
    public final void a(K5 k5, AbstractC0890Uw abstractC0890Uw) {
        abstractC0890Uw.c().a(this.a, this.b.d);
        k5.p();
        this.b.h = true;
    }

    public final String toString() {
        return "ExistingValue(v" + this.a.s() + ")";
    }

    @Override // com.android.tools.r8.ir.optimize.l0
    public final AbstractC2624sj0 a(C0333y c0333y, AbstractC2624sj0 abstractC2624sj0) {
        return this.a.t();
    }
}
