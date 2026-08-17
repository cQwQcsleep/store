package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.utils.structural.AbstractC3519a;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class OK implements com.android.tools.r8.utils.structural.x {
    public static final OK[] c = new OK[0];
    public final int b;

    public OK(int i) {
        this.b = i;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public abstract int a();

    public abstract int a(OK ok, AbstractC3519a abstractC3519a);

    @Override // com.android.tools.r8.utils.structural.x
    public final int a(com.android.tools.r8.utils.structural.x xVar, AbstractC3519a abstractC3519a) {
        OK ok = (OK) xVar;
        int iA = abstractC3519a.a(a(), ok.a());
        return iA != 0 ? iA : a(ok, abstractC3519a);
    }

    public abstract AbstractC2004lX a(C0322w2 c0322w2, boolean z);

    public abstract void b(com.android.tools.r8.utils.structural.o oVar);

    public abstract boolean b();

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final void a(com.android.tools.r8.utils.structural.o oVar) {
        ((com.android.tools.r8.utils.structural.q) oVar).a.a(a());
        b(oVar);
    }
}
