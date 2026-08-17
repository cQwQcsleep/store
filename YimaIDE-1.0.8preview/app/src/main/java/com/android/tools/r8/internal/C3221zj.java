package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.zj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3221zj extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C0384Bj c0384Bj = new C0384Bj();
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
                            if (iS == 7994) {
                                if (!z2) {
                                    c0384Bj.f = new ArrayList();
                                    z2 = true;
                                }
                                c0384Bj.f.add(abstractC0663Md.a(C0851Tj.o, c0415Co));
                            } else if (!c0384Bj.a(abstractC0663Md, c2285ok0, c0415Co, iS)) {
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        RB rb = new RB(e);
                        rb.b = c0384Bj;
                        throw rb;
                    }
                } catch (RB e2) {
                    e2.b = c0384Bj;
                    throw e2;
                }
            } catch (Throwable th) {
                if (z2) {
                    c0384Bj.f = Collections.unmodifiableList(c0384Bj.f);
                }
                c0384Bj.d = c2285ok0.build();
                c0384Bj.e.d();
                throw th;
            }
        }
        if (z2) {
            c0384Bj.f = Collections.unmodifiableList(c0384Bj.f);
        }
        c0384Bj.d = c2285ok0.build();
        c0384Bj.e.d();
        return c0384Bj;
    }
}
