package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Gi, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0513Gi extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C0643Li c0643Li = new C0643Li();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 10) {
                            Q7 q7D = abstractC0663Md.d();
                            c0643Li.e = 1 | c0643Li.e;
                            c0643Li.f = q7D;
                        } else if (iS == 18) {
                            if ((i & 2) == 0) {
                                c0643Li.g = new ArrayList();
                                i |= 2;
                            }
                            c0643Li.g.add(abstractC0663Md.a(C0798Ri.k, c0415Co));
                        } else if (iS == 26) {
                            C0694Ni c0694NiD = (c0643Li.e & 2) != 0 ? c0643Li.h.d() : null;
                            C0720Oi c0720Oi = (C0720Oi) abstractC0663Md.a(C0720Oi.l, c0415Co);
                            c0643Li.h = c0720Oi;
                            if (c0694NiD != null) {
                                c0694NiD.a(c0720Oi);
                                c0643Li.h = c0694NiD.i();
                            }
                            c0643Li.e |= 2;
                        } else if (iS == 34) {
                            if ((i & 8) == 0) {
                                c0643Li.i = new ArrayList();
                                i |= 8;
                            }
                            c0643Li.i.add(abstractC0663Md.a(C0617Ki.j, c0415Co));
                        } else if (iS == 42) {
                            Q7 q7D2 = abstractC0663Md.d();
                            if ((i & 16) == 0) {
                                c0643Li.j = new C3101yJ();
                                i |= 16;
                            }
                            c0643Li.j.a(q7D2);
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c0643Li;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c0643Li;
                    throw rb;
                }
            } catch (Throwable th) {
                if ((i & 2) != 0) {
                    c0643Li.g = Collections.unmodifiableList(c0643Li.g);
                }
                if ((i & 8) != 0) {
                    c0643Li.i = Collections.unmodifiableList(c0643Li.i);
                }
                if ((i & 16) != 0) {
                    c0643Li.j = c0643Li.j.f();
                }
                c0643Li.d = c2285ok0.build();
                throw th;
            }
        }
        if ((i & 2) != 0) {
            c0643Li.g = Collections.unmodifiableList(c0643Li.g);
        }
        if ((i & 8) != 0) {
            c0643Li.i = Collections.unmodifiableList(c0643Li.i);
        }
        if ((i & 16) != 0) {
            c0643Li.j = c0643Li.j.f();
        }
        c0643Li.d = c2285ok0.build();
        return c0643Li;
    }
}
