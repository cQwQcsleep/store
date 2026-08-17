package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.c90, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1214c90 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C1382e90 c1382e90 = new C1382e90();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 10) {
                            X80 x80D = c1382e90.e == 1 ? ((Y80) c1382e90.f).d() : null;
                            TN tnA = abstractC0663Md.a(Y80.l, c0415Co);
                            c1382e90.f = tnA;
                            if (x80D != null) {
                                x80D.a((Y80) tnA);
                                c1382e90.f = x80D.i();
                            }
                            c1382e90.e = 1;
                        } else if (iS == 18) {
                            String strR = abstractC0663Md.r();
                            c1382e90.e = 2;
                            c1382e90.f = strR;
                        } else if (iS == 26) {
                            S70 s70 = c1382e90.g;
                            R70 r70D = s70 != null ? s70.d() : null;
                            S70 s71 = (S70) abstractC0663Md.a(S70.i, c0415Co);
                            c1382e90.g = s71;
                            if (r70D != null) {
                                r70D.a(s71);
                                c1382e90.g = r70D.i();
                            }
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c1382e90;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c1382e90;
                    throw rb;
                }
            } catch (Throwable th) {
                c1382e90.d = c2285ok0.build();
                throw th;
            }
        }
        c1382e90.d = c2285ok0.build();
        return c1382e90;
    }
}
