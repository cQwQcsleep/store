package com.android.tools.r8.internal;

import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class CF extends K2 {
    public final InterfaceC1221cG c;
    public final AbstractC2671tG d;
    public final C2585sF e;
    public final int f;

    public CF(C2516rW c2516rW, InterfaceC1221cG interfaceC1221cG, Consumer consumer, AG ag, int i) {
        super(c2516rW);
        C3097yF c3097yF = C3097yF.c;
        C2585sF c2585sF = new C2585sF();
        this.e = c2585sF;
        this.c = interfaceC1221cG;
        this.d = ag;
        this.f = i;
        consumer.accept(c2585sF);
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a() {
        super.a();
        InterfaceC1221cG interfaceC1221cG = this.c;
        C3097yF c3097yF = C3097yF.c;
        C3097yF c3097yFA = this.e.a();
        int i = this.f;
        AbstractC2671tG abstractC2671tG = this.d;
        if (abstractC2671tG != null) {
            interfaceC1221cG.accept(new JE(c3097yFA, i, abstractC2671tG));
        } else {
            defpackage.l0.a("KeepCheck must have an item pattern.");
        }
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final void a(Object obj, String str) {
        if (str.equals("description") && (obj instanceof String)) {
            C2585sF c2585sF = this.e;
            c2585sF.getClass();
            c2585sF.b = new C2841vF((String) obj);
            return;
        }
        super.a(obj, str);
        throw null;
    }
}
