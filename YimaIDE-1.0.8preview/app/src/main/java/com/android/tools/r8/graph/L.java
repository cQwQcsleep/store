package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC3175z9;
import com.android.tools.r8.internal.C1345dk0;
import com.android.tools.r8.utils.structural.AbstractC3519a;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L implements com.android.tools.r8.utils.structural.u {
    public final /* synthetic */ O a;

    public L(O o) {
        this.a = o;
    }

    @Override // com.android.tools.r8.utils.structural.v
    public final int a(Object obj, Object obj2, AbstractC3519a abstractC3519a) {
        AbstractC3175z9 abstractC3175z9 = (AbstractC3175z9) obj;
        AbstractC3175z9 abstractC3175z10 = (AbstractC3175z9) obj2;
        O o = this.a;
        int iA = abstractC3519a.a(abstractC3175z9.z(), abstractC3175z10.z());
        return iA != 0 ? iA : abstractC3175z9.a(abstractC3175z10, abstractC3519a, o);
    }

    @Override // com.android.tools.r8.utils.structural.w
    public final void a(Object obj, com.android.tools.r8.utils.structural.o oVar) {
        throw new C1345dk0();
    }
}
