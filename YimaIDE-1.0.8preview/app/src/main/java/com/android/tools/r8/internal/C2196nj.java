package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2196nj extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C2368pj c2368pj = new C2368pj();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        char c = 0;
        while (!z) {
            try {
                try {
                    try {
                        int iS = abstractC0663Md.s();
                        if (iS != 0) {
                            if (iS == 8) {
                                c2368pj.f |= 1;
                                c2368pj.g = abstractC0663Md.c();
                            } else if (iS == 16) {
                                c2368pj.f |= 2;
                                c2368pj.h = abstractC0663Md.c();
                            } else if (iS == 24) {
                                c2368pj.f |= 4;
                                c2368pj.i = abstractC0663Md.c();
                            } else if (iS == 56) {
                                c2368pj.f |= 8;
                                c2368pj.j = abstractC0663Md.c();
                            } else if (iS == 7994) {
                                if ((c & 16) == 0) {
                                    c2368pj.k = new ArrayList();
                                    c = 16;
                                }
                                c2368pj.k.add(abstractC0663Md.a(C0851Tj.o, c0415Co));
                            } else if (!c2368pj.a(abstractC0663Md, c2285ok0, c0415Co, iS)) {
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        RB rb = new RB(e);
                        rb.b = c2368pj;
                        throw rb;
                    }
                } catch (RB e2) {
                    e2.b = c2368pj;
                    throw e2;
                }
            } catch (Throwable th) {
                if ((c & 16) != 0) {
                    c2368pj.k = Collections.unmodifiableList(c2368pj.k);
                }
                c2368pj.d = c2285ok0.build();
                c2368pj.e.d();
                throw th;
            }
        }
        if ((c & 16) != 0) {
            c2368pj.k = Collections.unmodifiableList(c2368pj.k);
        }
        c2368pj.d = c2285ok0.build();
        c2368pj.e.d();
        return c2368pj;
    }
}
