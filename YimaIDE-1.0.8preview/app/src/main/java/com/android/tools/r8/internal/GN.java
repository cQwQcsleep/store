package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0229j;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class GN implements com.android.tools.r8.graph.K5 {
    public final /* synthetic */ Set a;
    public final /* synthetic */ EN b;

    public GN(Set set, EN en) {
        this.a = set;
        this.b = en;
    }

    @Override // com.android.tools.r8.graph.K5
    public final void a(C0322w2 c0322w2) {
        com.android.tools.r8.graph.E0 e0C;
        com.android.tools.r8.graph.T4.c<?> cVarO;
        if (this.a.add(c0322w2)) {
            EN en = this.b;
            en.getClass();
            if (c0322w2.w0().I0() || (e0C = ((C0229j) en.a.g()).c(c0322w2.w0())) == null || (cVarO = ((C0229j) en.a.g()).f(e0C, c0322w2).o()) == null || cVarO.d() == e0C) {
                return;
            }
            C0322w2 reference = cVarO.q().getReference();
            if (EN.e || !c0322w2.a(reference)) {
                en.d.put(c0322w2, reference);
            } else {
                x1f.a();
            }
        }
    }

    @Override // com.android.tools.r8.graph.K5
    public final void a(C0245l1 c0245l1) {
        if (this.a.add(c0245l1)) {
            EN en = this.b;
            C0210g1 c0210g1Q = ((C0229j) en.a.g()).c(c0245l1).q();
            if (c0210g1Q == null || c0210g1Q.getReference().a(c0245l1)) {
                return;
            }
            C0245l1 reference = c0210g1Q.getReference();
            if (!EN.e && c0245l1.a(reference)) {
                x1f.a();
            } else {
                en.c.put(c0245l1, reference);
            }
        }
    }
}
