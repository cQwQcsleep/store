package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class D80 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        F80 f80 = new F80();
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
                            I80 i80 = f80.e;
                            H80 h80D = i80 != null ? i80.d() : null;
                            I80 i81 = (I80) abstractC0663Md.a(I80.h, c0415Co);
                            f80.e = i81;
                            if (h80D != null) {
                                h80D.a(i81);
                                I80 i82 = new I80(h80D);
                                i82.e = h80D.f;
                                h80D.o();
                                f80.e = i82;
                            }
                        } else if (iS == 18) {
                            f80.f = abstractC0663Md.r();
                        } else if (iS == 26) {
                            if (!z2) {
                                f80.g = new ArrayList();
                                z2 = true;
                            }
                            f80.g.add((E60) abstractC0663Md.a(E60.n, c0415Co));
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = f80;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = f80;
                    throw rb;
                }
            } catch (Throwable th) {
                if (z2) {
                    f80.g = Collections.unmodifiableList(f80.g);
                }
                f80.d = c2285ok0.build();
                throw th;
            }
        }
        if (z2) {
            f80.g = Collections.unmodifiableList(f80.g);
        }
        f80.d = c2285ok0.build();
        return f80;
    }
}
