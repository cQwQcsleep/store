package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class K70 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        M70 m70 = new M70();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    try {
                        int iS = abstractC0663Md.s();
                        if (iS != 0) {
                            if (iS == 10) {
                                C1128b80 c1128b80 = m70.e;
                                C1042a80 c1042a80D = c1128b80 != null ? c1128b80.d() : null;
                                C1128b80 c1128b81 = (C1128b80) abstractC0663Md.a(C1128b80.h, c0415Co);
                                m70.e = c1128b81;
                                if (c1042a80D != null) {
                                    c1042a80D.a(c1128b81);
                                    C1128b80 c1128b82 = new C1128b80(c1042a80D);
                                    c1128b82.e = c1042a80D.f;
                                    c1042a80D.o();
                                    m70.e = c1128b82;
                                }
                            } else if (iS == 18) {
                                if ((i & 1) == 0) {
                                    m70.f = new ArrayList();
                                    i |= 1;
                                }
                                m70.f.add((C1805j70) abstractC0663Md.a(C1805j70.j, c0415Co));
                            } else if (iS == 26) {
                                if ((i & 2) == 0) {
                                    m70.g = new ArrayList();
                                    i |= 2;
                                }
                                m70.g.add((C1126b70) abstractC0663Md.a(C1126b70.j, c0415Co));
                            } else if (iS == 34) {
                                if ((i & 4) == 0) {
                                    m70.h = new ArrayList();
                                    i |= 4;
                                }
                                m70.h.add((C80) abstractC0663Md.a(C80.i, c0415Co));
                            } else if (iS == 42) {
                                if ((i & 8) == 0) {
                                    m70.i = new ArrayList();
                                    i |= 8;
                                }
                                m70.i.add((B60) abstractC0663Md.a(B60.i, c0415Co));
                            } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        RB rb = new RB(e);
                        rb.b = m70;
                        throw rb;
                    }
                } catch (RB e2) {
                    e2.b = m70;
                    throw e2;
                }
            } catch (Throwable th) {
                if ((i & 1) != 0) {
                    m70.f = Collections.unmodifiableList(m70.f);
                }
                if ((i & 2) != 0) {
                    m70.g = Collections.unmodifiableList(m70.g);
                }
                if ((i & 4) != 0) {
                    m70.h = Collections.unmodifiableList(m70.h);
                }
                if ((i & 8) != 0) {
                    m70.i = Collections.unmodifiableList(m70.i);
                }
                m70.d = c2285ok0.build();
                throw th;
            }
        }
        if ((i & 1) != 0) {
            m70.f = Collections.unmodifiableList(m70.f);
        }
        if ((i & 2) != 0) {
            m70.g = Collections.unmodifiableList(m70.g);
        }
        if ((i & 4) != 0) {
            m70.h = Collections.unmodifiableList(m70.h);
        }
        if ((i & 8) != 0) {
            m70.i = Collections.unmodifiableList(m70.i);
        }
        m70.d = c2285ok0.build();
        return m70;
    }
}
