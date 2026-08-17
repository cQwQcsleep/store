package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Z70 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C1128b80 c1128b80 = new C1128b80();
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
                                c1128b80.e = abstractC0663Md.d();
                            } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                            }
                        }
                        z = true;
                    } catch (RB e) {
                        e.b = c1128b80;
                        throw e;
                    }
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c1128b80;
                    throw rb;
                }
            } catch (Throwable th) {
                c1128b80.d = c2285ok0.build();
                throw th;
            }
        }
        c1128b80.d = c2285ok0.build();
        return c1128b80;
    }
}
