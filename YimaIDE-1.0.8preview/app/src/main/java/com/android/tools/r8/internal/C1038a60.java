package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.a60, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1038a60 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C1208c60 c1208c60 = new C1208c60();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 10) {
                            P70 p70 = c1208c60.e;
                            O70 o70D = p70 != null ? p70.d() : null;
                            P70 p71 = (P70) abstractC0663Md.a(P70.i, c0415Co);
                            c1208c60.e = p71;
                            if (o70D != null) {
                                o70D.a(p71);
                                c1208c60.e = o70D.i();
                            }
                        } else if (iS == 18) {
                            c1208c60.f = abstractC0663Md.r();
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c1208c60;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c1208c60;
                    throw rb;
                }
            } catch (Throwable th) {
                c1208c60.d = c2285ok0.build();
                throw th;
            }
        }
        c1208c60.d = c2285ok0.build();
        return c1208c60;
    }
}
