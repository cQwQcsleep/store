package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.k60, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1888k60 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C2317p60 c2317p60 = new C2317p60();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 8) {
                            c2317p60.e = abstractC0663Md.t();
                        } else if (iS == 16) {
                            c2317p60.f = abstractC0663Md.j();
                        } else if (iS == 24) {
                            c2317p60.g = abstractC0663Md.j();
                        } else if (iS == 34) {
                            if (!z2) {
                                c2317p60.h = new ArrayList();
                                z2 = true;
                            }
                            c2317p60.h.add((C2231o60) abstractC0663Md.a(C2231o60.l, c0415Co));
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c2317p60;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c2317p60;
                    throw rb;
                }
            } catch (Throwable th) {
                if (z2) {
                    c2317p60.h = Collections.unmodifiableList(c2317p60.h);
                }
                c2317p60.d = c2285ok0.build();
                throw th;
            }
        }
        if (z2) {
            c2317p60.h = Collections.unmodifiableList(c2317p60.h);
        }
        c2317p60.d = c2285ok0.build();
        return c2317p60;
    }
}
