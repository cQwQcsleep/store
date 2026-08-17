package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.tj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2709tj extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C2880vj c2880vj = new C2880vj();
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
                            c2880vj.f |= 1;
                            c2880vj.g = abstractC0663Md.c();
                        } else if (iS == 272) {
                            int iF = abstractC0663Md.f();
                            if (iF == 0 || iF == 1 || iF == 2) {
                                c2880vj.f |= 2;
                                c2880vj.h = iF;
                            } else {
                                c2285ok0.a(34, iF);
                            }
                        } else if (iS == 7994) {
                            if ((c & 4) == 0) {
                                c2880vj.i = new ArrayList();
                                c = 4;
                            }
                            c2880vj.i.add(abstractC0663Md.a(C0851Tj.o, c0415Co));
                        } else if (!c2880vj.a(abstractC0663Md, c2285ok0, c0415Co, iS)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c2880vj;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c2880vj;
                    throw rb;
                }
            } catch (Throwable th) {
                if ((c & 4) != 0) {
                    c2880vj.i = Collections.unmodifiableList(c2880vj.i);
                }
                c2880vj.d = c2285ok0.build();
                c2880vj.e.d();
                throw th;
            }
        }
        if ((c & 4) != 0) {
            c2880vj.i = Collections.unmodifiableList(c2880vj.i);
        }
        c2880vj.d = c2285ok0.build();
        c2880vj.e.d();
        return c2880vj;
    }
}
