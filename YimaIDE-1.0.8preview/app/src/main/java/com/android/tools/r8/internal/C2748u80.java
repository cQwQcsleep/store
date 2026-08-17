package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.u80, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2748u80 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C3174z80 c3174z80 = new C3174z80();
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
                            c3174z80.e = abstractC0663Md.r();
                        } else if (iS == 18) {
                            if (!z2) {
                                c3174z80.f = new ArrayList();
                                z2 = true;
                            }
                            c3174z80.f.add((C3089y80) abstractC0663Md.a(C3089y80.j, c0415Co));
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c3174z80;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c3174z80;
                    throw rb;
                }
            } catch (Throwable th) {
                if (z2) {
                    c3174z80.f = Collections.unmodifiableList(c3174z80.f);
                }
                c3174z80.d = c2285ok0.build();
                throw th;
            }
        }
        if (z2) {
            c3174z80.f = Collections.unmodifiableList(c3174z80.f);
        }
        c3174z80.d = c2285ok0.build();
        return c3174z80;
    }
}
