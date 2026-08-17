package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class W60 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        Y60 y60 = new Y60();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        while (!z) {
            try {
                try {
                    try {
                        int iS = abstractC0663Md.s();
                        if (iS != 0) {
                            if (iS == 10) {
                                y60.e = abstractC0663Md.r();
                            } else if (iS == 18) {
                                y60.f = abstractC0663Md.r();
                            } else if (iS == 24) {
                                y60.g = abstractC0663Md.c();
                            } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                            }
                        }
                        z = true;
                    } catch (RB e) {
                        e.b = y60;
                        throw e;
                    }
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = y60;
                    throw rb;
                }
            } catch (Throwable th) {
                y60.d = c2285ok0.build();
                throw th;
            }
        }
        y60.d = c2285ok0.build();
        return y60;
    }
}
