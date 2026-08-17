package com.android.tools.r8.internal;

import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class BF extends K2 {
    public final C2516rW c;
    public final InterfaceC1221cG d;
    public final C2585sF e;
    public final String f;
    public final int g;

    public BF(C2516rW c2516rW, InterfaceC1221cG interfaceC1221cG, Consumer consumer, String str, int i) {
        super(c2516rW);
        C3097yF c3097yF = C3097yF.c;
        C2585sF c2585sF = new C2585sF();
        this.e = c2585sF;
        this.c = c2516rW;
        this.d = interfaceC1221cG;
        this.f = str;
        this.g = i;
        consumer.accept(c2585sF);
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a() {
        AF af = new AF(this, this.c);
        af.a((Object) this.f, "className");
        af.a();
        InterfaceC1221cG interfaceC1221cG = this.d;
        C3097yF c3097yF = C3097yF.c;
        C3097yF c3097yFA = this.e.a();
        int i = this.g;
        AbstractC2671tG abstractC2671tGC = af.c().c();
        if (abstractC2671tGC != null) {
            interfaceC1221cG.accept(new JE(c3097yFA, i, abstractC2671tGC));
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
