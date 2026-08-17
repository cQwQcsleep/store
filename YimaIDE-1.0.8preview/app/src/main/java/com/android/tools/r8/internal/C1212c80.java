package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.c80, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1212c80 extends AbstractC1537g1 {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        C1637h80 c1637h80 = new C1637h80();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        Object[] objArr = false;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 10) {
                            J70 j70 = c1637h80.e;
                            H70 h70D = j70 != null ? j70.d() : null;
                            J70 j71 = (J70) abstractC0663Md.a(J70.n, c0415Co);
                            c1637h80.e = j71;
                            if (h70D != null) {
                                h70D.a(j71);
                                c1637h80.e = h70D.i();
                            }
                        } else if (iS == 18) {
                            P70 p70 = c1637h80.f;
                            O70 o70D = p70 != null ? p70.d() : null;
                            P70 p71 = (P70) abstractC0663Md.a(P70.i, c0415Co);
                            c1637h80.f = p71;
                            if (o70D != null) {
                                o70D.a(p71);
                                c1637h80.f = o70D.i();
                            }
                        } else if (iS == 26) {
                            if (objArr == false) {
                                c1637h80.g = new ArrayList();
                                objArr = true;
                            }
                            c1637h80.g.add((C1552g80) abstractC0663Md.a(C1552g80.k, c0415Co));
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = c1637h80;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = c1637h80;
                    throw rb;
                }
            } catch (Throwable th) {
                if (objArr != false) {
                    c1637h80.g = Collections.unmodifiableList(c1637h80.g);
                }
                c1637h80.d = c2285ok0.build();
                throw th;
            }
        }
        if (objArr != false) {
            c1637h80.g = Collections.unmodifiableList(c1637h80.g);
        }
        c1637h80.d = c2285ok0.build();
        return c1637h80;
    }
}
