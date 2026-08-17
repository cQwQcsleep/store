package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class G70 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        J70 j70 = new J70();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        while (!z) {
            try {
                try {
                    try {
                        int iS = abstractC0663Md.s();
                        if (iS != 0) {
                            if (iS == 8) {
                                j70.e = abstractC0663Md.f();
                            } else if (iS == 16) {
                                j70.f = abstractC0663Md.t();
                            } else if (iS == 26) {
                                j70.g = abstractC0663Md.r();
                            } else if (iS == 32) {
                                j70.h = abstractC0663Md.c();
                            } else if (iS == 42) {
                                C2573s60 c2573s60 = j70.i;
                                C2487r60 c2487r60D = c2573s60 != null ? c2573s60.d() : null;
                                C2573s60 c2573s61 = (C2573s60) abstractC0663Md.a(C2573s60.h, c0415Co);
                                j70.i = c2573s61;
                                if (c2487r60D != null) {
                                    c2487r60D.a(c2573s61);
                                    C2573s60 c2573s62 = new C2573s60(c2487r60D);
                                    c2573s62.e = c2487r60D.f;
                                    c2487r60D.o();
                                    j70.i = c2573s62;
                                }
                            } else if (iS == 48) {
                                j70.j = abstractC0663Md.t();
                            } else if (iS == 56) {
                                j70.k = abstractC0663Md.c();
                            } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                            }
                        }
                        z = true;
                    } catch (RB e) {
                        e.b = j70;
                        throw e;
                    }
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = j70;
                    throw rb;
                }
            } catch (Throwable th) {
                j70.d = c2285ok0.build();
                throw th;
            }
        }
        j70.d = c2285ok0.build();
        return j70;
    }
}
