package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.g60, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1548g60 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C1719i60 c1719i60 = new C1719i60();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 10) {
                            P70 p70 = c1719i60.e;
                            O70 o70D = p70 != null ? p70.d() : null;
                            P70 p71 = (P70) abstractC0663Md.a(P70.i, c0415Co);
                            c1719i60.e = p71;
                            if (o70D != null) {
                                o70D.a(p71);
                                c1719i60.e = o70D.i();
                            }
                        } else if (iS == 18) {
                            c1719i60.f = abstractC0663Md.r();
                        } else if (iS == 26) {
                            R60 r60 = c1719i60.g;
                            Q60 q60D = r60 != null ? r60.d() : null;
                            R60 r61 = (R60) abstractC0663Md.a(R60.i, c0415Co);
                            c1719i60.g = r61;
                            if (q60D != null) {
                                q60D.a(r61);
                                c1719i60.g = q60D.i();
                            }
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c1719i60;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c1719i60;
                    throw rb;
                }
            } catch (Throwable th) {
                c1719i60.d = c2285ok0.build();
                throw th;
            }
        }
        c1719i60.d = c2285ok0.build();
        return c1719i60;
    }
}
