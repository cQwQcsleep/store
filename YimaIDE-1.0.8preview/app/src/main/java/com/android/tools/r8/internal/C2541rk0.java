package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rk0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2541rk0 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws RB {
        int iS;
        C2285ok0 c2285ok0 = new C2285ok0();
        do {
            try {
                iS = abstractC0663Md.s();
                if (iS == 0) {
                    break;
                }
            } catch (RB e) {
                e.b = c2285ok0.build();
                throw e;
            } catch (IOException e2) {
                RB rb = new RB(e2);
                rb.b = c2285ok0.build();
                throw rb;
            }
        } while (c2285ok0.a(iS, abstractC0663Md));
        return c2285ok0.build();
    }
}
