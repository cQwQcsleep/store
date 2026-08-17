package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ij, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0566Ij extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C0695Nj c0695Nj = new C0695Nj();
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
                                    c0695Nj.e = new ArrayList();
                                    z2 = true;
                                }
                                c0695Nj.e.add(abstractC0663Md.a(C0669Mj.o, c0415Co));
                            } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        RB rb = new RB(e);
                        rb.b = c0695Nj;
                        throw rb;
                    }
                } catch (RB e2) {
                    e2.b = c0695Nj;
                    throw e2;
                }
            } catch (Throwable th) {
                if (z2) {
                    c0695Nj.e = Collections.unmodifiableList(c0695Nj.e);
                }
                c0695Nj.d = c2285ok0.build();
                throw th;
            }
        }
        if (z2) {
            c0695Nj.e = Collections.unmodifiableList(c0695Nj.e);
        }
        c0695Nj.d = c2285ok0.build();
        return c0695Nj;
    }
}
