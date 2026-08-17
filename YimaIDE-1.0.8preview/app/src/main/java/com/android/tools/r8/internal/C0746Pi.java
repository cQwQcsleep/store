package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Pi, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0746Pi extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C0798Ri c0798Ri = new C0798Ri();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 10) {
                            Q7 q7D = abstractC0663Md.d();
                            c0798Ri.e = 1 | c0798Ri.e;
                            c0798Ri.f = q7D;
                        } else if (iS == 16) {
                            c0798Ri.e |= 2;
                            c0798Ri.g = abstractC0663Md.j();
                        } else if (iS == 26) {
                            C0850Ti c0850TiK = (c0798Ri.e & 4) != 0 ? c0798Ri.h.d() : null;
                            C0876Ui c0876Ui = (C0876Ui) abstractC0663Md.a(C0876Ui.k, c0415Co);
                            c0798Ri.h = c0876Ui;
                            if (c0850TiK != null) {
                                c0850TiK.a(c0876Ui);
                                c0798Ri.h = c0850TiK.i();
                            }
                            c0798Ri.e |= 4;
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c0798Ri;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c0798Ri;
                    throw rb;
                }
            } catch (Throwable th) {
                c0798Ri.d = c2285ok0.build();
                throw th;
            }
        }
        c0798Ri.d = c2285ok0.build();
        return c0798Ri;
    }
}
