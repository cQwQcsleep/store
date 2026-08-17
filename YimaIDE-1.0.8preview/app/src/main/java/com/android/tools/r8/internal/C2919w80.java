package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.w80, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2919w80 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C3089y80 c3089y80 = new C3089y80();
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
                                c3089y80.e = abstractC0663Md.r();
                            } else if (iS == 16) {
                                c3089y80.f = abstractC0663Md.t();
                            } else if (iS == 24) {
                                c3089y80.g = abstractC0663Md.t();
                            } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                            }
                        }
                        z = true;
                    } catch (RB e) {
                        e.b = c3089y80;
                        throw e;
                    }
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c3089y80;
                    throw rb;
                }
            } catch (Throwable th) {
                c3089y80.d = c2285ok0.build();
                throw th;
            }
        }
        c3089y80.d = c2285ok0.build();
        return c3089y80;
    }
}
