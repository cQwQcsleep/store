package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.h70, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1635h70 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C1805j70 c1805j70 = new C1805j70();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 10) {
                            C2062m70 c2062m70 = c1805j70.e;
                            C1976l70 c1976l70D = c2062m70 != null ? c2062m70.d() : null;
                            C2062m70 c2062m71 = (C2062m70) abstractC0663Md.a(C2062m70.h, c0415Co);
                            c1805j70.e = c2062m71;
                            if (c1976l70D != null) {
                                c1976l70D.a(c2062m71);
                                c1805j70.e = c1976l70D.i();
                            }
                        } else if (iS == 18) {
                            c1805j70.f = abstractC0663Md.r();
                        } else if (iS == 26) {
                            if (!z2) {
                                c1805j70.g = new ArrayList();
                                z2 = true;
                            }
                            c1805j70.g.add((F80) abstractC0663Md.a(F80.j, c0415Co));
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c1805j70;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c1805j70;
                    throw rb;
                }
            } catch (Throwable th) {
                if (z2) {
                    c1805j70.g = Collections.unmodifiableList(c1805j70.g);
                }
                c1805j70.d = c2285ok0.build();
                throw th;
            }
        }
        if (z2) {
            c1805j70.g = Collections.unmodifiableList(c1805j70.g);
        }
        c1805j70.d = c2285ok0.build();
        return c1805j70;
    }
}
