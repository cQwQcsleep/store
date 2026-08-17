package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0230j0;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class OS implements com.android.tools.r8.ir.optimize.A {
    public static final /* synthetic */ boolean c = true;
    public final C0333y b;

    public OS(C0333y c0333y) {
        this.b = c0333y;
    }

    @Override // com.android.tools.r8.ir.optimize.A
    public final Set a(C0705Nt c0705Nt, IO io2, com.android.tools.r8.graph.proto.j jVar, XR xr) {
        C1983lC c1983lCA;
        C2496rC c2496rCB;
        C1983lC c1983lCA2;
        boolean z = c;
        if (!z && !(xr instanceof LS)) {
            x1f.a();
            return null;
        }
        Set setC = AbstractC2780ub0.c();
        ArrayList<C1983lC> arrayList = new ArrayList();
        K5 k5A = c0705Nt.j().a(c0705Nt);
        AbstractC2004lX position = k5A.m().getPosition();
        if (!z && com.android.tools.r8.graph.proto.c.a(Integer.MAX_VALUE, jVar.b.a) != 0) {
            x1f.a();
            return null;
        }
        int iN = c0705Nt.n();
        for (int i = 0; i < iN; i++) {
            com.android.tools.r8.graph.proto.b bVarA = jVar.b.a(i);
            AbstractC0890Uw next = k5A.next();
            if (!c && !next.k1()) {
                x1f.a();
                return null;
            }
            bVarA.getClass();
            if ((bVarA instanceof com.android.tools.r8.graph.proto.k) && (c1983lCA2 = a(c0705Nt, next.c(), bVarA.b(), position)) != null) {
                arrayList.add(c1983lCA2);
            }
        }
        if (!c && k5A.m().k1()) {
            x1f.a();
            return null;
        }
        for (C1983lC c1983lC : arrayList) {
            k5A.add(c1983lC);
            setC.addAll(c1983lC.c().a0());
        }
        InterfaceC0968Xw interfaceC0968XwR = c0705Nt.r();
        while (interfaceC0968XwR.hasNext()) {
            AbstractC0890Uw next2 = interfaceC0968XwR.next();
            if (next2.W1()) {
                AbstractC1047aC abstractC1047aCB0 = next2.b0();
                C0322w2 c0322w2 = (C0322w2) xr.a(abstractC1047aCB0.U2(), c0705Nt.i().getReference(), abstractC1047aCB0.P2(), xr.d).a;
                boolean z2 = c;
                if (!z2 && c0322w2 == null) {
                    x1f.a();
                    return null;
                }
                com.android.tools.r8.graph.proto.j jVarE = xr.e(xr.d, c0322w2);
                if (jVarE.g()) {
                    continue;
                } else {
                    if (!z2 && com.android.tools.r8.graph.proto.c.a(Integer.MAX_VALUE, jVarE.b.a) != 0) {
                        x1f.a();
                        return null;
                    }
                    for (int i2 = 0; i2 < abstractC1047aCB0.c.size(); i2++) {
                        com.android.tools.r8.graph.proto.b bVarA2 = jVarE.b.a(i2);
                        bVarA2.getClass();
                        if ((bVarA2 instanceof com.android.tools.r8.graph.proto.k) && (c2496rCB = b(c0705Nt, abstractC1047aCB0.b(i2), bVarA2.b(), abstractC1047aCB0.getPosition())) != null) {
                            interfaceC0968XwR.a(c2496rCB);
                            abstractC1047aCB0.a(i2, c2496rCB.c());
                        }
                    }
                    if (abstractC1047aCB0.c1() && (c1983lCA = a(c0705Nt, abstractC1047aCB0.c(), jVarE.c, abstractC1047aCB0.getPosition())) != null) {
                        interfaceC0968XwR.add(c1983lCA);
                        setC.addAll(c1983lCA.c().a0());
                    }
                }
            } else if (next2.v2() && !next2.D0().L2()) {
                C1837ja0 c1837ja0D0 = next2.D0();
                C2496rC c2496rCB2 = b(c0705Nt, c1837ja0D0.M2(), jVar.c, c1837ja0D0.getPosition());
                if (c2496rCB2 != null) {
                    interfaceC0968XwR.a(c2496rCB2);
                    c1837ja0D0.a(c1837ja0D0.M2(), c2496rCB2.c(), (Set) null);
                }
            }
        }
        return setC;
    }

    public final C2496rC b(C0705Nt c0705Nt, C2543rl0 c2543rl0, com.android.tools.r8.graph.proto.k kVar, AbstractC2004lX abstractC2004lX) {
        if (kVar == null) {
            return null;
        }
        boolean z = c;
        if (!z && !kVar.g().U0()) {
            x1f.a();
            return null;
        }
        if (!z && !kVar.f().T0()) {
            x1f.a();
            return null;
        }
        if (!z && !this.b.a().S5.containsValue(kVar.g())) {
            x1f.a();
            return null;
        }
        boolean z2 = C2496rC.m;
        C2412qC c2412qC = new C2412qC();
        c2412qC.d = this.b.a().g(kVar.f());
        C2412qC c2412qC2 = (C2412qC) c2412qC.a(c0705Nt.e, kVar.f().b(this.b), null);
        int i = AbstractC0551Hu.c;
        C2412qC c2412qC3 = (C2412qC) c2412qC2.a(new Bc0(c2543rl0));
        c2412qC3.b = abstractC2004lX;
        return c2412qC3.c();
    }

    public final C1983lC a(C0705Nt c0705Nt, C2543rl0 c2543rl0, com.android.tools.r8.graph.proto.k kVar, AbstractC2004lX abstractC2004lX) {
        if (kVar == null) {
            return null;
        }
        boolean z = c;
        if (!z && !kVar.g().U0()) {
            x1f.a();
            return null;
        }
        if (!z && !kVar.f().T0()) {
            x1f.a();
            return null;
        }
        if (!z && !this.b.a().S5.containsValue(kVar.g())) {
            x1f.a();
            return null;
        }
        C2543rl0 c2543rl0A = c0705Nt.a(kVar.g().b(this.b), (C0230j0) null);
        c2543rl0.f(c2543rl0A);
        boolean z2 = C1983lC.m;
        C1897kC c1897kC = new C1897kC();
        c1897kC.a = c2543rl0A;
        c1897kC.d = this.b.a().c(kVar.f());
        C1897kC c1897kC2 = (C1897kC) c1897kC.b(c2543rl0);
        c1897kC2.b = abstractC2004lX;
        return c1897kC2.c();
    }
}
