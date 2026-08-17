package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Kj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0618Kj extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C0669Mj c0669Mj = new C0669Mj();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 8) {
                            if ((i & 1) == 0) {
                                c0669Mj.f = new C0945Wz();
                                i |= 1;
                            }
                            ((C0945Wz) c0669Mj.f).j(abstractC0663Md.j());
                        } else if (iS == 10) {
                            int iC = abstractC0663Md.c(abstractC0663Md.l());
                            if ((i & 1) == 0 && abstractC0663Md.b() > 0) {
                                c0669Mj.f = new C0945Wz();
                                i |= 1;
                            }
                            while (abstractC0663Md.b() > 0) {
                                ((C0945Wz) c0669Mj.f).j(abstractC0663Md.j());
                            }
                            abstractC0663Md.b(iC);
                        } else if (iS == 16) {
                            if ((i & 2) == 0) {
                                c0669Mj.h = new C0945Wz();
                                i |= 2;
                            }
                            ((C0945Wz) c0669Mj.h).j(abstractC0663Md.j());
                        } else if (iS == 18) {
                            int iC2 = abstractC0663Md.c(abstractC0663Md.l());
                            if ((i & 2) == 0 && abstractC0663Md.b() > 0) {
                                c0669Mj.h = new C0945Wz();
                                i |= 2;
                            }
                            while (abstractC0663Md.b() > 0) {
                                ((C0945Wz) c0669Mj.h).j(abstractC0663Md.j());
                            }
                            abstractC0663Md.b(iC2);
                        } else if (iS == 26) {
                            Q7 q7D = abstractC0663Md.d();
                            c0669Mj.e = 1 | c0669Mj.e;
                            c0669Mj.j = q7D;
                        } else if (iS == 34) {
                            Q7 q7D2 = abstractC0663Md.d();
                            c0669Mj.e |= 2;
                            c0669Mj.k = q7D2;
                        } else if (iS == 50) {
                            Q7 q7D3 = abstractC0663Md.d();
                            if ((i & 16) == 0) {
                                c0669Mj.l = new C3101yJ();
                                i |= 16;
                            }
                            c0669Mj.l.a(q7D3);
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c0669Mj;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c0669Mj;
                    throw rb;
                }
            } catch (Throwable th) {
                if ((i & 1) != 0) {
                    ((AbstractC2220o1) c0669Mj.f).b = false;
                }
                if ((i & 2) != 0) {
                    ((AbstractC2220o1) c0669Mj.h).b = false;
                }
                if ((i & 16) != 0) {
                    c0669Mj.l = c0669Mj.l.f();
                }
                c0669Mj.d = c2285ok0.build();
                throw th;
            }
        }
        if ((i & 1) != 0) {
            ((AbstractC2220o1) c0669Mj.f).b = false;
        }
        if ((i & 2) != 0) {
            ((AbstractC2220o1) c0669Mj.h).b = false;
        }
        if ((i & 16) != 0) {
            c0669Mj.l = c0669Mj.l.f();
        }
        c0669Mj.d = c2285ok0.build();
        return c0669Mj;
    }
}
