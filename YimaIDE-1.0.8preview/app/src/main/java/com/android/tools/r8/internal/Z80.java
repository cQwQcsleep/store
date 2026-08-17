package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Z80 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C1130b90 c1130b90 = new C1130b90();
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
                                c1130b90.e = abstractC0663Md.r();
                            } else if (iS == 18) {
                                c1130b90.f = abstractC0663Md.r();
                            } else if (iS == 26) {
                                S70 s70 = c1130b90.g;
                                R70 r70D = s70 != null ? s70.d() : null;
                                S70 s71 = (S70) abstractC0663Md.a(S70.i, c0415Co);
                                c1130b90.g = s71;
                                if (r70D != null) {
                                    r70D.a(s71);
                                    c1130b90.g = r70D.i();
                                }
                            } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                            }
                        }
                        z = true;
                    } catch (RB e) {
                        e.b = c1130b90;
                        throw e;
                    }
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c1130b90;
                    throw rb;
                }
            } catch (Throwable th) {
                c1130b90.d = c2285ok0.build();
                throw th;
            }
        }
        c1130b90.d = c2285ok0.build();
        return c1130b90;
    }
}
