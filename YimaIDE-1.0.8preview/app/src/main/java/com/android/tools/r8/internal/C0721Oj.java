package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Oj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0721Oj extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C0851Tj c0851Tj = new C0851Tj();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 18) {
                            if (!z2) {
                                c0851Tj.f = new ArrayList();
                                z2 = true;
                            }
                            c0851Tj.f.add(abstractC0663Md.a(C0825Sj.j, c0415Co));
                        } else if (iS == 26) {
                            Q7 q7D = abstractC0663Md.d();
                            c0851Tj.e = 1 | c0851Tj.e;
                            c0851Tj.g = q7D;
                        } else if (iS == 32) {
                            c0851Tj.e |= 2;
                            c0851Tj.h = abstractC0663Md.u();
                        } else if (iS == 40) {
                            c0851Tj.e |= 4;
                            c0851Tj.i = abstractC0663Md.k();
                        } else if (iS == 49) {
                            c0851Tj.e |= 8;
                            c0851Tj.j = abstractC0663Md.e();
                        } else if (iS == 58) {
                            c0851Tj.e |= 16;
                            c0851Tj.k = abstractC0663Md.d();
                        } else if (iS == 66) {
                            Q7 q7D2 = abstractC0663Md.d();
                            c0851Tj.e |= 32;
                            c0851Tj.l = q7D2;
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c0851Tj;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c0851Tj;
                    throw rb;
                }
            } catch (Throwable th) {
                if (z2) {
                    c0851Tj.f = Collections.unmodifiableList(c0851Tj.f);
                }
                c0851Tj.d = c2285ok0.build();
                throw th;
            }
        }
        if (z2) {
            c0851Tj.f = Collections.unmodifiableList(c0851Tj.f);
        }
        c0851Tj.d = c2285ok0.build();
        return c0851Tj;
    }
}
