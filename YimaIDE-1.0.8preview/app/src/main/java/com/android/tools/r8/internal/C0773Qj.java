package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Qj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0773Qj extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C0825Sj c0825Sj = new C0825Sj();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 10) {
                            Q7 q7D = abstractC0663Md.d();
                            c0825Sj.e = 1 | c0825Sj.e;
                            c0825Sj.f = q7D;
                        } else if (iS == 16) {
                            c0825Sj.e |= 2;
                            c0825Sj.g = abstractC0663Md.c();
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c0825Sj;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c0825Sj;
                    throw rb;
                }
            } catch (Throwable th) {
                c0825Sj.d = c2285ok0.build();
                throw th;
            }
        }
        c0825Sj.d = c2285ok0.build();
        return c0825Sj;
    }
}
