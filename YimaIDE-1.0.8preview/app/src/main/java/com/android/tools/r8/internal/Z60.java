package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Z60 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C1126b70 c1126b70 = new C1126b70();
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
                                c1126b70.e = abstractC0663Md.r();
                            } else if (iS == 18) {
                                P70 p70 = c1126b70.f;
                                O70 o70D = p70 != null ? p70.d() : null;
                                P70 p71 = (P70) abstractC0663Md.a(P70.i, c0415Co);
                                c1126b70.f = p71;
                                if (o70D != null) {
                                    o70D.a(p71);
                                    c1126b70.f = o70D.i();
                                }
                            } else if (iS == 26) {
                                c1126b70.g = abstractC0663Md.r();
                            } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                            }
                        }
                        z = true;
                    } catch (RB e) {
                        e.b = c1126b70;
                        throw e;
                    }
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c1126b70;
                    throw rb;
                }
            } catch (Throwable th) {
                c1126b70.d = c2285ok0.build();
                throw th;
            }
        }
        c1126b70.d = c2285ok0.build();
        return c1126b70;
    }
}
