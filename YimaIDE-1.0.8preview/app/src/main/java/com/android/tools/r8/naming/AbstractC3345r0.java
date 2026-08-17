package com.android.tools.r8.naming;

import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.E2;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.Y3;
import com.android.tools.r8.internal.AbstractC0464El;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.shaking.C3403i;
import defpackage.hih;
import defpackage.pni;
import defpackage.v5g;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.naming.r0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3345r0 {
    public static final /* synthetic */ boolean a = true;

    public abstract H2 a(I2 i2);

    public abstract H2 a(Y3 y3, C2752uB c2752uB);

    public abstract H2 a(C0245l1 c0245l1);

    public abstract H2 a(C0322w2 c0322w2);

    public abstract String a(String str);

    public final void a(Collection collection, B1 b1) {
        Set setC = AbstractC2780ub0.c();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            D2 d2 = (D2) it.next();
            I2 i2A = a(b1, d2.e);
            boolean zAdd = setC.add(i2A);
            if (!a && !zAdd) {
                hih.a("Duplicate definition of type `", i2A.m0(), "`");
                return;
            }
            Iterator<C0210g1> it2 = d2.M0().iterator();
            while (it2.hasNext()) {
                C0245l1 reference = it2.next().getReference();
                C0245l1 c0245l1A = b1.a(a(b1, reference.f), a(b1, reference.i), a(reference));
                boolean zAdd2 = setC.add(c0245l1A);
                if (!a && !zAdd2) {
                    hih.a("Duplicate definition of field `", c0245l1A.m0(), "`");
                    return;
                }
            }
            Iterator<C0231j1> it3 = d2.C1().iterator();
            while (it3.hasNext()) {
                C0322w2 c0322w2A = a(b1, it3.next().getReference());
                boolean zAdd3 = setC.add(c0322w2A);
                if (!a && !zAdd3) {
                    hih.a("Duplicate definition of method `", c0322w2A.m0(), "`");
                    return;
                }
            }
        }
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final I2 a(B1 b1, I2 i2) {
        if (i2.T0() || i2.W0()) {
            return i2;
        }
        if (i2.I0()) {
            return i2.a(b1, a(b1, i2.a(b1)));
        }
        if (a || i2.M0()) {
            return b1.c(b(i2));
        }
        x1f.a();
        return null;
    }

    public abstract H2 c(I2 i2);

    public final String d(I2 i2) {
        if (a || i2.M0() || i2.I0()) {
            return C0929Wj.a(c(i2).toString());
        }
        x1f.a();
        return null;
    }

    public H2 e(I2 i2) {
        return null;
    }

    public H2 b(I2 i2) {
        if (a || i2.M0()) {
            return a(i2);
        }
        x1f.a();
        return null;
    }

    public boolean b() {
        return this instanceof C3355w0;
    }

    public static /* synthetic */ boolean a(H2 h2, H2 h3) {
        return h3 == h2;
    }

    public final H2 a(com.android.tools.r8.graph.D0 d0, final C0333y c0333y) {
        if (!c0333y.g().i()) {
            return d0.e;
        }
        C0333y<C3403i> c0333yV = c0333y.V();
        AbstractC0464El abstractC0464ElA = ((C3403i) c0333yV.g()).a(d0, c0333yV);
        if (abstractC0464ElA.b.isEmpty()) {
            return d0.e;
        }
        final H2 h2X0 = a(c0333y.a(), ((com.android.tools.r8.graph.H0) abstractC0464ElA.b.values().iterator().next()).getReference()).x0();
        if (a || abstractC0464ElA.stream().map(new v5g()).map(new Function() { // from class: d6i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(c0333y, (C0322w2) obj);
            }
        }).map(new pni()).allMatch(new Predicate() { // from class: g6i
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return AbstractC3345r0.a(h2X0, (H2) obj);
            }
        })) {
            return h2X0;
        }
        x1f.a();
        return null;
    }

    public final /* synthetic */ C0322w2 a(C0333y c0333y, C0322w2 c0322w2) {
        return a(c0333y.a(), c0322w2);
    }

    public final C0322w2 a(B1 b1, C0322w2 c0322w2) {
        return b1.a(a(b1, c0322w2.f), a(c0322w2.i, b1), a(c0322w2));
    }

    public final E2 a(E2 e2, final B1 b1) {
        return b1.a(a(b1, e2.e), (I2[]) Arrays.stream(e2.f.b).map(new Function() { // from class: u5i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(b1, (I2) obj);
            }
        }).toArray(new IntFunction() { // from class: z5i
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return AbstractC3345r0.a(i);
            }
        }));
    }

    public static /* synthetic */ I2[] a(int i) {
        return new I2[i];
    }

    public static AbstractC3345r0 a() {
        return new C3342p0();
    }
}
