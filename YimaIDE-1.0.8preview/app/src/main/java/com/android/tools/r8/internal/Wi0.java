package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0007c;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Wi0 extends AbstractC3220zi0 {
    public final Object a(C2327pD c2327pD) throws IOException {
        AbstractC1643hD c1558gD;
        AbstractC1643hD c1558gD2;
        AbstractC1643hD c2155nD;
        int iP = c2327pD.p();
        int iB = AbstractC0007c.b(iP);
        if (iB == 0) {
            c2327pD.c();
            c1558gD = new C1558gD();
        } else if (iB != 2) {
            c1558gD = null;
        } else {
            c2327pD.d();
            c1558gD = new C1898kD();
        }
        if (c1558gD == null) {
            int iB2 = AbstractC0007c.b(iP);
            if (iB2 == 5) {
                return new C2155nD(c2327pD.n());
            }
            if (iB2 == 6) {
                return new C2155nD((Number) new qJ(c2327pD.n()));
            }
            if (iB2 == 7) {
                return new C2155nD(Boolean.valueOf(c2327pD.k()));
            }
            if (iB2 == 8) {
                c2327pD.m();
                return C1813jD.b;
            }
            k2d.a("Unexpected token: ".concat(AbstractC2497rD.a(iP)));
            return null;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        while (true) {
            if (c2327pD.i()) {
                String strL = c1558gD instanceof C1898kD ? c2327pD.l() : null;
                int iP2 = c2327pD.p();
                int iB3 = AbstractC0007c.b(iP2);
                if (iB3 == 0) {
                    c2327pD.c();
                    c1558gD2 = new C1558gD();
                } else if (iB3 != 2) {
                    c1558gD2 = null;
                } else {
                    c2327pD.d();
                    c1558gD2 = new C1898kD();
                }
                boolean z = c1558gD2 != null;
                if (c1558gD2 != null) {
                    c2155nD = c1558gD2;
                } else {
                    int iB4 = AbstractC0007c.b(iP2);
                    if (iB4 == 5) {
                        c2155nD = new C2155nD(c2327pD.n());
                    } else if (iB4 == 6) {
                        c1558gD2 = new C2155nD((Number) new qJ(c2327pD.n()));
                        c2155nD = c1558gD2;
                    } else if (iB4 == 7) {
                        c2155nD = new C2155nD(Boolean.valueOf(c2327pD.k()));
                    } else {
                        if (iB4 != 8) {
                            k2d.a("Unexpected token: ".concat(AbstractC2497rD.a(iP2)));
                            return null;
                        }
                        c2327pD.m();
                        c2155nD = C1813jD.b;
                    }
                }
                if (c1558gD instanceof C1558gD) {
                    ((C1558gD) c1558gD).b.add(c2155nD);
                } else {
                    ((C1898kD) c1558gD).a(strL, c2155nD);
                }
                if (z) {
                    arrayDeque.addLast(c1558gD);
                    c1558gD = c2155nD;
                }
            } else {
                if (c1558gD instanceof C1558gD) {
                    c2327pD.g();
                } else {
                    c2327pD.h();
                }
                if (arrayDeque.isEmpty()) {
                    return c1558gD;
                }
                c1558gD = (AbstractC1643hD) arrayDeque.removeLast();
            }
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final /* bridge */ /* synthetic */ void a(C2754uD c2754uD, Object obj) throws IOException {
        a(c2754uD, (AbstractC1643hD) obj);
    }

    public static void a(C2754uD c2754uD, AbstractC1643hD abstractC1643hD) throws IOException {
        if (abstractC1643hD != null && !(abstractC1643hD instanceof C1813jD)) {
            boolean z = abstractC1643hD instanceof C2155nD;
            if (z) {
                if (z) {
                    C2155nD c2155nD = (C2155nD) abstractC1643hD;
                    Object obj = c2155nD.b;
                    if (obj instanceof Number) {
                        c2754uD.a(c2155nD.i());
                        return;
                    } else if (obj instanceof Boolean) {
                        c2754uD.a(c2155nD.a());
                        return;
                    } else {
                        c2754uD.d(c2155nD.g());
                        return;
                    }
                }
                qu7.a("Not a JSON Primitive: ", abstractC1643hD);
                return;
            }
            if (abstractC1643hD instanceof C1558gD) {
                c2754uD.d();
                Iterator it = abstractC1643hD.c().b.iterator();
                while (it.hasNext()) {
                    a(c2754uD, (AbstractC1643hD) it.next());
                }
                c2754uD.f();
                return;
            }
            if (abstractC1643hD instanceof C1898kD) {
                c2754uD.e();
                BK bk = ((C2932wK) abstractC1643hD.d().b.entrySet()).b;
                AK ak = bk.g.e;
                int i = bk.f;
                while (true) {
                    AK ak2 = bk.g;
                    if (ak == ak2) {
                        c2754uD.g();
                        return;
                    }
                    if (ak != ak2) {
                        if (bk.f == i) {
                            AK ak3 = ak.e;
                            c2754uD.b((String) ak.g);
                            a(c2754uD, (AbstractC1643hD) ak.i);
                            ak = ak3;
                        } else {
                            a1e.a();
                            return;
                        }
                    } else {
                        z0e.a();
                        return;
                    }
                }
            } else {
                z01.a("Couldn't write ", abstractC1643hD.getClass());
            }
        } else {
            c2754uD.i();
        }
    }
}
