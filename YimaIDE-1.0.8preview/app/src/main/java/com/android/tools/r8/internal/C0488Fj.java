package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Fj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0488Fj extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C0540Hj c0540Hj = new C0540Hj();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        char c = 0;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 264) {
                            c0540Hj.f |= 1;
                            c0540Hj.g = abstractC0663Md.c();
                        } else if (iS == 7994) {
                            if ((c & 2) == 0) {
                                c0540Hj.h = new ArrayList();
                                c = 2;
                            }
                            c0540Hj.h.add(abstractC0663Md.a(C0851Tj.o, c0415Co));
                        } else if (!c0540Hj.a(abstractC0663Md, c2285ok0, c0415Co, iS)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c0540Hj;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c0540Hj;
                    throw rb;
                }
            } catch (Throwable th) {
                if ((c & 2) != 0) {
                    c0540Hj.h = Collections.unmodifiableList(c0540Hj.h);
                }
                c0540Hj.d = c2285ok0.build();
                c0540Hj.e.d();
                throw th;
            }
        }
        if ((c & 2) != 0) {
            c0540Hj.h = Collections.unmodifiableList(c0540Hj.h);
        }
        c0540Hj.d = c2285ok0.build();
        c0540Hj.e.d();
        return c0540Hj;
    }
}
