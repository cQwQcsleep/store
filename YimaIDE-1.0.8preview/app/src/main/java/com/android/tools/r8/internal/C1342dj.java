package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.dj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1342dj extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C1513fj c1513fj = new C1513fj();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        char c = 0;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 8) {
                            int iF = abstractC0663Md.f();
                            if (iF == 0 || iF == 1 || iF == 2) {
                                c1513fj.f = 1 | c1513fj.f;
                                c1513fj.g = iF;
                            } else {
                                c2285ok0.a(1, iF);
                            }
                        } else if (iS == 16) {
                            c1513fj.f |= 2;
                            c1513fj.h = abstractC0663Md.c();
                        } else if (iS == 24) {
                            c1513fj.f |= 16;
                            c1513fj.k = abstractC0663Md.c();
                        } else if (iS == 40) {
                            c1513fj.f |= 8;
                            c1513fj.j = abstractC0663Md.c();
                        } else if (iS == 48) {
                            int iF2 = abstractC0663Md.f();
                            if (iF2 == 0 || iF2 == 1 || iF2 == 2) {
                                c1513fj.f |= 4;
                                c1513fj.i = iF2;
                            } else {
                                c2285ok0.a(6, iF2);
                            }
                        } else if (iS == 80) {
                            c1513fj.f |= 32;
                            c1513fj.l = abstractC0663Md.c();
                        } else if (iS == 7994) {
                            if ((c & '@') == 0) {
                                c1513fj.m = new ArrayList();
                                c = '@';
                            }
                            c1513fj.m.add(abstractC0663Md.a(C0851Tj.o, c0415Co));
                        } else if (!c1513fj.a(abstractC0663Md, c2285ok0, c0415Co, iS)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c1513fj;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c1513fj;
                    throw rb;
                }
            } catch (Throwable th) {
                if ((c & '@') != 0) {
                    c1513fj.m = Collections.unmodifiableList(c1513fj.m);
                }
                c1513fj.d = c2285ok0.build();
                c1513fj.e.d();
                throw th;
            }
        }
        if ((c & '@') != 0) {
            c1513fj.m = Collections.unmodifiableList(c1513fj.m);
        }
        c1513fj.d = c2285ok0.build();
        c1513fj.e.d();
        return c1513fj;
    }
}
