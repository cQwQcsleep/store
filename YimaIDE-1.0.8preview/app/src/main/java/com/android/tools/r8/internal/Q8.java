package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Q8 implements InterfaceC1947kn, InterfaceC1179bl, InterfaceC0774Qk, InterfaceC2223o20, Kl0 {
    public static Q8 a(C0333y c0333y) {
        P8 p8 = new P8();
        AbstractC2775uY abstractC2775uYB = AbstractC2775uY.b(c0333y);
        abstractC2775uYB.getClass();
        return abstractC2775uYB instanceof C1233cS ? p8 : new C3032xY(abstractC2775uYB.a(), c0333y.M().w(), p8);
    }

    public abstract Set a();

    public void b(C0333y c0333y) {
    }
}
