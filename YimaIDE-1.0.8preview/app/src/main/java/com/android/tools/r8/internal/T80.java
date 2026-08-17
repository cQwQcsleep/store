package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class T80 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        V80 v80 = new V80();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 10) {
                            v80.e = abstractC0663Md.r();
                        } else if (iS == 18) {
                            v80.f = abstractC0663Md.r();
                        } else if (iS != 26) {
                            if (iS == 34) {
                                S70 s70 = v80.h;
                                R70 r70D = s70 != null ? s70.d() : null;
                                S70 s71 = (S70) abstractC0663Md.a(S70.i, c0415Co);
                                v80.h = s71;
                                if (r70D != null) {
                                    r70D.a(s71);
                                    v80.h = r70D.i();
                                }
                            } else if (iS == 40) {
                                v80.i = abstractC0663Md.t();
                            } else if (iS == 50) {
                                R60 r60 = v80.j;
                                Q60 q60D = r60 != null ? r60.d() : null;
                                R60 r61 = (R60) abstractC0663Md.a(R60.i, c0415Co);
                                v80.j = r61;
                                if (q60D != null) {
                                    q60D.a(r61);
                                    v80.j = q60D.i();
                                }
                            } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                            }
                        } else {
                            v80.g = abstractC0663Md.r();
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = v80;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = v80;
                    throw rb;
                }
            } catch (Throwable th) {
                v80.d = c2285ok0.build();
                throw th;
            }
        }
        v80.d = c2285ok0.build();
        return v80;
    }
}
