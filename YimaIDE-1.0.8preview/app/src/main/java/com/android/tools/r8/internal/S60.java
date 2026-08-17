package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class S60 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        U60 u60 = new U60();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS != 10) {
                            if (iS == 18) {
                                C2149n80 c2149n80 = u60.f;
                                C1807j80 c1807j80D = c2149n80 != null ? c2149n80.d() : null;
                                C2149n80 c2149n81 = (C2149n80) abstractC0663Md.a(C2149n80.i, c0415Co);
                                u60.f = c2149n81;
                                if (c1807j80D != null) {
                                    c1807j80D.a(c2149n81);
                                    u60.f = c1807j80D.i();
                                }
                            } else if (iS == 26) {
                                int i2 = (i == true ? 1 : 0) & 1;
                                i = i;
                                if (i2 == 0) {
                                    u60.g = new ArrayList();
                                    i = (i == true ? 1 : 0) | 1;
                                }
                                u60.g.add((L80) abstractC0663Md.a(L80.i, c0415Co));
                            } else if (iS == 34) {
                                int i3 = (i == true ? 1 : 0) & 2;
                                i = i;
                                if (i3 == 0) {
                                    u60.h = new ArrayList();
                                    i = (i == true ? 1 : 0) | 2;
                                }
                                u60.h.add((Y60) abstractC0663Md.a(Y60.j, c0415Co));
                            } else if (iS == 42) {
                                S70 s70 = u60.i;
                                R70 r70D = s70 != null ? s70.d() : null;
                                S70 s71 = (S70) abstractC0663Md.a(S70.i, c0415Co);
                                u60.i = s71;
                                if (r70D != null) {
                                    r70D.a(s71);
                                    u60.i = r70D.i();
                                }
                            } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                            }
                        } else {
                            u60.e = abstractC0663Md.r();
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = u60;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = u60;
                    throw rb;
                }
            } catch (Throwable th) {
                if (((i == true ? 1 : 0) & 1) != 0) {
                    u60.g = Collections.unmodifiableList(u60.g);
                }
                if (((i == true ? 1 : 0) & 2) != 0) {
                    u60.h = Collections.unmodifiableList(u60.h);
                }
                u60.d = c2285ok0.build();
                throw th;
            }
        }
        if (((i == true ? 1 : 0) & 1) != 0) {
            u60.g = Collections.unmodifiableList(u60.g);
        }
        if (((i == true ? 1 : 0) & 2) != 0) {
            u60.h = Collections.unmodifiableList(u60.h);
        }
        u60.d = c2285ok0.build();
        return u60;
    }
}
