package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2453qj extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C2623sj c2623sj = new C2623sj();
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
                            c2623sj.e = 1 | c2623sj.e;
                            c2623sj.f = q7D;
                        } else if (iS == 18) {
                            Q7 q7D2 = abstractC0663Md.d();
                            c2623sj.e |= 2;
                            c2623sj.g = q7D2;
                        } else if (iS == 26) {
                            Q7 q7D3 = abstractC0663Md.d();
                            c2623sj.e |= 4;
                            c2623sj.h = q7D3;
                        } else if (iS == 34) {
                            C2794uj c2794ujK = (c2623sj.e & 8) != 0 ? c2623sj.i.d() : null;
                            C2880vj c2880vj = (C2880vj) abstractC0663Md.a(C2880vj.l, c0415Co);
                            c2623sj.i = c2880vj;
                            if (c2794ujK != null) {
                                c2794ujK.a(c2880vj);
                                c2623sj.i = c2794ujK.i();
                            }
                            c2623sj.e |= 8;
                        } else if (iS == 40) {
                            c2623sj.e |= 16;
                            c2623sj.j = abstractC0663Md.c();
                        } else if (iS == 48) {
                            c2623sj.e |= 32;
                            c2623sj.k = abstractC0663Md.c();
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c2623sj;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c2623sj;
                    throw rb;
                }
            } catch (Throwable th) {
                c2623sj.d = c2285ok0.build();
                throw th;
            }
        }
        c2623sj.d = c2285ok0.build();
        return c2623sj;
    }
}
