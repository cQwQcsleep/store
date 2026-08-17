package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class I60 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        L60 l60 = new L60();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 10) {
                            l60.e = abstractC0663Md.r();
                        } else if (iS == 16) {
                            l60.f = abstractC0663Md.f();
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = l60;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = l60;
                    throw rb;
                }
            } catch (Throwable th) {
                l60.d = c2285ok0.build();
                throw th;
            }
        }
        l60.d = c2285ok0.build();
        return l60;
    }
}
