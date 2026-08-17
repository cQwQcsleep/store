package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Mi, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0668Mi extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C0720Oi c0720Oi = new C0720Oi();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        char c = 0;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 16) {
                            c0720Oi.f |= 1;
                            c0720Oi.g = abstractC0663Md.c();
                        } else if (iS == 24) {
                            c0720Oi.f |= 2;
                            c0720Oi.h = abstractC0663Md.c();
                        } else if (iS == 7994) {
                            if ((c & 4) == 0) {
                                c0720Oi.i = new ArrayList();
                                c = 4;
                            }
                            c0720Oi.i.add(abstractC0663Md.a(C0851Tj.o, c0415Co));
                        } else if (!c0720Oi.a(abstractC0663Md, c2285ok0, c0415Co, iS)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c0720Oi;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c0720Oi;
                    throw rb;
                }
            } catch (Throwable th) {
                if ((c & 4) != 0) {
                    c0720Oi.i = Collections.unmodifiableList(c0720Oi.i);
                }
                c0720Oi.d = c2285ok0.build();
                c0720Oi.e.d();
                throw th;
            }
        }
        if ((c & 4) != 0) {
            c0720Oi.i = Collections.unmodifiableList(c0720Oi.i);
        }
        c0720Oi.d = c2285ok0.build();
        c0720Oi.e.d();
        return c0720Oi;
    }
}
