package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Cj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0410Cj extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C0462Ej c0462Ej = new C0462Ej();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        char c = 0;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 10) {
                            Q7 q7D = abstractC0663Md.d();
                            c0462Ej.e = 1 | c0462Ej.e;
                            c0462Ej.f = q7D;
                        } else if (iS == 18) {
                            if ((c & 2) == 0) {
                                c0462Ej.g = new ArrayList();
                                c = 2;
                            }
                            c0462Ej.g.add(abstractC0663Md.a(C2623sj.n, c0415Co));
                        } else if (iS == 26) {
                            C0514Gj c0514GjD = (c0462Ej.e & 2) != 0 ? c0462Ej.h.d() : null;
                            C0540Hj c0540Hj = (C0540Hj) abstractC0663Md.a(C0540Hj.k, c0415Co);
                            c0462Ej.h = c0540Hj;
                            if (c0514GjD != null) {
                                c0514GjD.a(c0540Hj);
                                c0462Ej.h = c0514GjD.i();
                            }
                            c0462Ej.e |= 2;
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c0462Ej;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c0462Ej;
                    throw rb;
                }
            } catch (Throwable th) {
                if ((c & 2) != 0) {
                    c0462Ej.g = Collections.unmodifiableList(c0462Ej.g);
                }
                c0462Ej.d = c2285ok0.build();
                throw th;
            }
        }
        if ((c & 2) != 0) {
            c0462Ej.g = Collections.unmodifiableList(c0462Ej.g);
        }
        c0462Ej.d = c2285ok0.build();
        return c0462Ej;
    }
}
