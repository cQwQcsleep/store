package com.android.tools.r8.internal;

import com.android.tools.r8.shaking.AbstractC3385e1;
import com.android.tools.r8.shaking.AbstractC3390f1;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fF, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1474fF extends AbstractC1560gF {
    public static final C1474fF a = new C1474fF();

    @Override // com.android.tools.r8.internal.AbstractC1560gF
    public final void a(AbstractC1645hF abstractC1645hF) {
        AbstractC3390f1 abstractC3390f1 = ((C2498rE) abstractC1645hF).a;
        AbstractC3385e1 abstractC3385e1 = abstractC3390f1.a;
        abstractC3385e1.b = false;
        abstractC3385e1.j();
        abstractC3390f1.o();
    }

    @Override // com.android.tools.r8.internal.AbstractC1560gF
    public final void a(RG rg) {
        rg.b.add(SG.e);
    }
}
