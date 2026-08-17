package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.k80, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1892k80 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C2064m80 c2064m80 = new C2064m80();
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
                                c2064m80.e = abstractC0663Md.r();
                            } else if (iS == 16) {
                                c2064m80.f = abstractC0663Md.t();
                            } else if (iS == 24) {
                                c2064m80.g = abstractC0663Md.t();
                            } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                            }
                        }
                        z = true;
                    } catch (RB e) {
                        e.b = c2064m80;
                        throw e;
                    }
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c2064m80;
                    throw rb;
                }
            } catch (Throwable th) {
                c2064m80.d = c2285ok0.build();
                throw th;
            }
        }
        c2064m80.d = c2285ok0.build();
        return c2064m80;
    }
}
