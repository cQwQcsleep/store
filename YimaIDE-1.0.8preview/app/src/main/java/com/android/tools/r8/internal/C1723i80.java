package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.i80, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1723i80 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C2149n80 c2149n80 = new C2149n80();
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
                            c2149n80.e = abstractC0663Md.r();
                        } else if (iS == 18) {
                            if (!z2) {
                                c2149n80.f = new ArrayList();
                                z2 = true;
                            }
                            c2149n80.f.add((C2064m80) abstractC0663Md.a(C2064m80.j, c0415Co));
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c2149n80;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c2149n80;
                    throw rb;
                }
            } catch (Throwable th) {
                if (z2) {
                    c2149n80.f = Collections.unmodifiableList(c2149n80.f);
                }
                c2149n80.d = c2285ok0.build();
                throw th;
            }
        }
        if (z2) {
            c2149n80.f = Collections.unmodifiableList(c2149n80.f);
        }
        c2149n80.d = c2285ok0.build();
        return c2149n80;
    }
}
