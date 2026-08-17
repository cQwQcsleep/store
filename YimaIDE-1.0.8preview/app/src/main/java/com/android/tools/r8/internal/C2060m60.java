package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.m60, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2060m60 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C2231o60 c2231o60 = new C2231o60();
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
                                P70 p70 = c2231o60.e;
                                O70 o70D = p70 != null ? p70.d() : null;
                                P70 p71 = (P70) abstractC0663Md.a(P70.i, c0415Co);
                                c2231o60.e = p71;
                                if (o70D != null) {
                                    o70D.a(p71);
                                    c2231o60.e = o70D.i();
                                }
                            } else if (iS == 18) {
                                c2231o60.f = abstractC0663Md.r();
                            } else if (iS == 26) {
                                J70 j70 = c2231o60.g;
                                H70 h70D = j70 != null ? j70.d() : null;
                                J70 j71 = (J70) abstractC0663Md.a(J70.n, c0415Co);
                                c2231o60.g = j71;
                                if (h70D != null) {
                                    h70D.a(j71);
                                    c2231o60.g = h70D.i();
                                }
                            } else if (iS == 32) {
                                c2231o60.h = abstractC0663Md.t();
                            } else if (iS == 40) {
                                c2231o60.i = abstractC0663Md.t();
                            } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        RB rb = new RB(e);
                        rb.b = c2231o60;
                        throw rb;
                    }
                } catch (RB e2) {
                    e2.b = c2231o60;
                    throw e2;
                }
            } catch (Throwable th) {
                c2231o60.d = c2285ok0.build();
                throw th;
            }
        }
        c2231o60.d = c2285ok0.build();
        return c2231o60;
    }
}
