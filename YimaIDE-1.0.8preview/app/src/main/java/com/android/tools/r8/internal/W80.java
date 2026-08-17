package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class W80 extends AbstractC1537g1 {
    @Override // com.android.tools.r8.internal.InterfaceC2346pW
    public final Object a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        Y80 y80 = new Y80();
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    try {
                        int iS = abstractC0663Md.s();
                        if (iS != 0) {
                            if (iS == 10) {
                                if ((i & 1) == 0) {
                                    y80.e = new ArrayList();
                                    i |= 1;
                                }
                                y80.e.add((C1130b90) abstractC0663Md.a(C1130b90.j, c0415Co));
                            } else if (iS == 18) {
                                y80.f = abstractC0663Md.r();
                            } else if (iS == 26) {
                                y80.g = abstractC0663Md.r();
                            } else if (iS == 34) {
                                if ((i & 2) == 0) {
                                    y80.h = new ArrayList();
                                    i |= 2;
                                }
                                y80.h.add((V80) abstractC0663Md.a(V80.m, c0415Co));
                            } else if (iS == 42) {
                                if ((i & 4) == 0) {
                                    y80.i = new ArrayList();
                                    i |= 4;
                                }
                                y80.i.add((C1382e90) abstractC0663Md.a(C1382e90.j, c0415Co));
                            } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        RB rb = new RB(e);
                        rb.b = y80;
                        throw rb;
                    }
                } catch (RB e2) {
                    e2.b = y80;
                    throw e2;
                }
            } catch (Throwable th) {
                if ((i & 1) != 0) {
                    y80.e = Collections.unmodifiableList(y80.e);
                }
                if ((i & 2) != 0) {
                    y80.h = Collections.unmodifiableList(y80.h);
                }
                if ((i & 4) != 0) {
                    y80.i = Collections.unmodifiableList(y80.i);
                }
                y80.d = c2285ok0.build();
                throw th;
            }
        }
        if ((i & 1) != 0) {
            y80.e = Collections.unmodifiableList(y80.e);
        }
        if ((i & 2) != 0) {
            y80.h = Collections.unmodifiableList(y80.h);
        }
        if ((i & 4) != 0) {
            y80.i = Collections.unmodifiableList(y80.i);
        }
        y80.d = c2285ok0.build();
        return y80;
    }
}
