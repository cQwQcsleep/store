package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class P80 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        S80 s80 = new S80();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 8) {
                            s80.e = abstractC0663Md.f();
                        } else if (iS == 18) {
                            P70 p70 = s80.f;
                            O70 o70D = p70 != null ? p70.d() : null;
                            P70 p71 = (P70) abstractC0663Md.a(P70.i, c0415Co);
                            s80.f = p71;
                            if (o70D != null) {
                                o70D.a(p71);
                                s80.f = o70D.i();
                            }
                        } else if (iS == 26) {
                            s80.g = abstractC0663Md.r();
                        } else if (iS == 32) {
                            s80.h = abstractC0663Md.c();
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = s80;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = s80;
                    throw rb;
                }
            } catch (Throwable th) {
                s80.d = c2285ok0.build();
                throw th;
            }
        }
        s80.d = c2285ok0.build();
        return s80;
    }
}
