package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2966wj extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C3135yj c3135yj = new C3135yj();
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
                            c3135yj.e = 1 | c3135yj.e;
                            c3135yj.f = q7D;
                        } else if (iS == 18) {
                            C0358Aj c0358AjD = (c3135yj.e & 2) != 0 ? c3135yj.g.d() : null;
                            C0384Bj c0384Bj = (C0384Bj) abstractC0663Md.a(C0384Bj.i, c0415Co);
                            c3135yj.g = c0384Bj;
                            if (c0358AjD != null) {
                                c0358AjD.a(c0384Bj);
                                c3135yj.g = c0358AjD.i();
                            }
                            c3135yj.e |= 2;
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c3135yj;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c3135yj;
                    throw rb;
                }
            } catch (Throwable th) {
                c3135yj.d = c2285ok0.build();
                throw th;
            }
        }
        c3135yj.d = c2285ok0.build();
        return c3135yj;
    }
}
