package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.d70, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1294d70 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C1550g70 c1550g70 = new C1550g70();
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
                            P70 p70 = c1550g70.e;
                            O70 o70D = p70 != null ? p70.d() : null;
                            P70 p71 = (P70) abstractC0663Md.a(P70.i, c0415Co);
                            c1550g70.e = p71;
                            if (o70D != null) {
                                o70D.a(p71);
                                c1550g70.e = o70D.i();
                            }
                        } else if (iS == 18) {
                            c1550g70.f = abstractC0663Md.r();
                        } else if (iS == 24) {
                            int iF = abstractC0663Md.f();
                            if (!z2) {
                                c1550g70.g = new ArrayList();
                                z2 = true;
                            }
                            c1550g70.g.add(Integer.valueOf(iF));
                        } else if (iS == 26) {
                            int iC = abstractC0663Md.c(abstractC0663Md.l());
                            while (abstractC0663Md.b() > 0) {
                                int iF2 = abstractC0663Md.f();
                                if (!z2) {
                                    c1550g70.g = new ArrayList();
                                    z2 = true;
                                }
                                c1550g70.g.add(Integer.valueOf(iF2));
                            }
                            abstractC0663Md.b(iC);
                        } else if (iS == 32) {
                            c1550g70.i = abstractC0663Md.t();
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c1550g70;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c1550g70;
                    throw rb;
                }
            } catch (Throwable th) {
                if (z2) {
                    c1550g70.g = Collections.unmodifiableList(c1550g70.g);
                }
                c1550g70.d = c2285ok0.build();
                throw th;
            }
        }
        if (z2) {
            c1550g70.g = Collections.unmodifiableList(c1550g70.g);
        }
        c1550g70.d = c2285ok0.build();
        return c1550g70;
    }
}
