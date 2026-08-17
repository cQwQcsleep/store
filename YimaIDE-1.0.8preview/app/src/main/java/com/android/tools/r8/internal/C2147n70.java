package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.n70, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2147n70 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C2660t70 c2660t70 = new C2660t70();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    try {
                        int iS = abstractC0663Md.s();
                        if (iS != 0) {
                            if (iS == 10) {
                                if (!z2) {
                                    c2660t70.e = new ArrayList();
                                    z2 = true;
                                }
                                c2660t70.e.add((C2575s70) abstractC0663Md.a(C2575s70.k, c0415Co));
                            } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        RB rb = new RB(e);
                        rb.b = c2660t70;
                        throw rb;
                    }
                } catch (RB e2) {
                    e2.b = c2660t70;
                    throw e2;
                }
            } catch (Throwable th) {
                if (z2) {
                    c2660t70.e = Collections.unmodifiableList(c2660t70.e);
                }
                c2660t70.d = c2285ok0.build();
                throw th;
            }
        }
        if (z2) {
            c2660t70.e = Collections.unmodifiableList(c2660t70.e);
        }
        c2660t70.d = c2285ok0.build();
        return c2660t70;
    }
}
