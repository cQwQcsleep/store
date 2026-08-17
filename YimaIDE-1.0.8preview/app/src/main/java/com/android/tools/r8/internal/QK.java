package com.android.tools.r8.internal;

import com.android.tools.r8.utils.structural.AbstractC3519a;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class QK implements com.android.tools.r8.utils.structural.u {
    @Override // com.android.tools.r8.utils.structural.w
    public final void a(Object obj, com.android.tools.r8.utils.structural.o oVar) {
        C2490r8 c2490r8 = (C2490r8) obj;
        AbstractC0551Hu abstractC0551Hu = c2490r8.c;
        for (int i = 0; i < abstractC0551Hu.size(); i++) {
            oVar.a((com.android.tools.r8.graph.I2) c2490r8.b.get(i));
            ((com.android.tools.r8.utils.structural.q) oVar).a.a(((Integer) abstractC0551Hu.get(i)).intValue());
        }
    }

    @Override // com.android.tools.r8.utils.structural.v
    public final int a(Object obj, Object obj2, AbstractC3519a abstractC3519a) {
        C2490r8 c2490r8 = (C2490r8) obj;
        C2490r8 c2490r9 = (C2490r8) obj2;
        int iA = abstractC3519a.a(c2490r8.b, c2490r9.b);
        return iA != 0 ? iA : AbstractC2956we.a(c2490r8.c, c2490r9.c);
    }
}
