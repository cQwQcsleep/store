package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC3175z9;
import com.android.tools.r8.internal.C1345dk0;
import com.android.tools.r8.internal.C2481r30;
import com.android.tools.r8.internal.K9;
import com.android.tools.r8.utils.structural.AbstractC3519a;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class K implements com.android.tools.r8.utils.structural.u {
    public final C2481r30 a;
    public final C2481r30 b;

    public K(O o) {
        G g = o.a;
        C2481r30 c2481r30 = new C2481r30();
        for (AbstractC3175z9 abstractC3175z9 : g.H0()) {
            abstractC3175z9.getClass();
            if (abstractC3175z9 instanceof K9) {
                c2481r30.b(c2481r30.i, abstractC3175z9.o());
            }
        }
        this.a = c2481r30;
        G g2 = o.b;
        C2481r30 c2481r31 = new C2481r30();
        for (AbstractC3175z9 abstractC3175z10 : g2.H0()) {
            abstractC3175z10.getClass();
            if (abstractC3175z10 instanceof K9) {
                c2481r31.b(c2481r31.i, abstractC3175z10.o());
            }
        }
        this.b = c2481r31;
    }

    @Override // com.android.tools.r8.utils.structural.v
    public final int a(Object obj, Object obj2, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this.a.b((K9) obj), this.b.b((K9) obj2));
    }

    @Override // com.android.tools.r8.utils.structural.w
    public final void a(Object obj, com.android.tools.r8.utils.structural.o oVar) {
        throw new C1345dk0();
    }
}
