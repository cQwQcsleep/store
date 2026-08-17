package com.android.tools.r8.internal;

import com.android.tools.r8.ClassFileConsumer;
import com.android.tools.r8.graph.C0333y;
import defpackage.n33;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class B9 {
    public static OR a(C0333y c0333y, com.android.tools.r8.androidapi.a aVar) {
        C2752uB.g gVar = c0333y.M().E0;
        gVar.getClass();
        C2752uB.g gVar2 = C2752uB.g.b;
        if (gVar == gVar2 && c0333y.M().n0) {
            n33.a("Cannot combine -Dcom.android.tools.r8.forceNestDesugaring with desugaring turned off");
            return null;
        }
        if (c0333y.M().E0.a()) {
            return new OR(c0333y, aVar);
        }
        if (c0333y.M().j instanceof ClassFileConsumer) {
            boolean z = OR.g;
            if (!z) {
                C2752uB.g gVar3 = c0333y.M().E0;
                gVar3.getClass();
                if (gVar3 != gVar2) {
                    x1f.a();
                    return null;
                }
            }
            if (!z && !(c0333y.M().j instanceof ClassFileConsumer)) {
                x1f.a();
                return null;
            }
            OR or = new OR(c0333y, new com.android.tools.r8.androidapi.b());
            or.b.add(new C1728iC(c0333y));
            return or;
        }
        boolean z2 = OR.g;
        if (!z2) {
            C2752uB.g gVar4 = c0333y.M().E0;
            gVar4.getClass();
            if (gVar4 != gVar2) {
                x1f.a();
                return null;
            }
        }
        if (!z2 && !c0333y.M().Z()) {
            x1f.a();
            return null;
        }
        OR or2 = new OR(c0333y, aVar);
        or2.b.add(new C1728iC(c0333y));
        or2.c.add(new Rk0(c0333y));
        return or2;
    }

    public abstract PA a(VA va);

    public abstract PA a(Predicate predicate, VA va);

    public abstract Collection a(AbstractC3175z9 abstractC3175z9, AbstractC2004lX abstractC2004lX, InterfaceC1187br interfaceC1187br, LL ll, InterfaceC1467f9 interfaceC1467f9, E9 e9, com.android.tools.r8.graph.B5 b5, C0483Fe c0483Fe);

    public abstract void a(com.android.tools.r8.graph.B5 b5, E9 e9);

    public abstract void a(com.android.tools.r8.graph.B5 b5, C0483Fe c0483Fe, E9 e9);

    public abstract void a(E9 e9, HY hy, com.android.tools.r8.graph.B5 b5);

    public abstract void a(InterfaceC1936kh0 interfaceC1936kh0);

    public abstract void a(Consumer consumer);

    public abstract boolean a(com.android.tools.r8.graph.B5 b5);
}
