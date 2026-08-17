package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0007c;
import com.android.tools.r8.utils.structural.AbstractC3519a;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class TK implements com.android.tools.r8.utils.structural.u {
    public static final TK a = new TK();

    @Override // com.android.tools.r8.utils.structural.v
    public final int a(Object obj, Object obj2, AbstractC3519a abstractC3519a) {
        UK uk = (UK) obj;
        UK uk2 = (UK) obj2;
        int iA = abstractC3519a.a(AbstractC0007c.b(uk.y()), AbstractC0007c.b(uk2.y()));
        return iA != 0 ? iA : uk.a(uk2, abstractC3519a);
    }

    @Override // com.android.tools.r8.utils.structural.w
    public final void a(Object obj, com.android.tools.r8.utils.structural.o oVar) {
        UK uk = (UK) obj;
        ((com.android.tools.r8.utils.structural.q) oVar).a.a(AbstractC0007c.b(uk.y()));
        uk.b(oVar);
    }
}
