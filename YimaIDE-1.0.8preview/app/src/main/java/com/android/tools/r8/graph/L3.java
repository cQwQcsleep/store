package com.android.tools.r8.graph;

import com.android.tools.r8.DiagnosticsLevel;
import com.android.tools.r8.graph.B3;
import com.android.tools.r8.graph.L3;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.MX;
import com.android.tools.r8.shaking.C3380d1;
import com.android.tools.r8.shaking.C3435o1;
import defpackage.x0g;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class L3 {
    public static final /* synthetic */ boolean d = true;
    public final C0333y a;
    public final int b;
    public final G3 c;

    public enum a {
        b,
        /* JADX INFO: Fake field, exist only in values array */
        EF1,
        c,
        d,
        e,
        f;

        public static final /* synthetic */ boolean h = true;

        a() {
        }

        public final String a() {
            int i = I3.a[ordinal()];
            if (i == 1) {
                return "The applied generic arguments have different count than the expected formals";
            }
            if (i == 2) {
                return "The generic signature has a different number of interfaces than the class";
            }
            if (i == 3) {
                return "The generic super type is not the same as the class super type";
            }
            if (i == 4) {
                return "A type variable is not in scope";
            }
            if (h || c()) {
                x0g.a("Should not throw an error for a valid signature");
                return null;
            }
            x1f.a();
            return null;
        }

        public final boolean b() {
            return this != f;
        }

        public final boolean c() {
            return this == f;
        }
    }

    public L3(C0333y c0333y, G3 g3, int i) {
        this.a = c0333y;
        this.c = g3;
        this.b = i;
    }

    public static L3 b(C0333y<?> c0333y, G3 g3) {
        return new L3(c0333y, g3, 1);
    }

    public a a(final D2 d2) {
        a aVarA;
        this.a.M().getClass();
        if (!this.a.M().b0()) {
            return a.f;
        }
        final G3.a aVarA2 = this.c.a(this.a, (F2) d2.e, (Predicate) MX.c);
        C0333y c0333y = this.a;
        int i = this.b;
        final J3 j3 = new J3(i, c0333y, d2);
        B3.b bVar = d2.s;
        if (!bVar.a()) {
            aVarA = j3.a(bVar.c(), aVarA2);
            if (!aVarA.b()) {
                B1 b1A = c0333y.a();
                B3.c cVar = bVar.b;
                if (cVar == null) {
                    cVar = new B3.c(b1A.a2);
                }
                if (d2.g == cVar.b) {
                    aVarA = j3.a(cVar.r(), d2.g, aVarA2);
                    if (!aVarA.b()) {
                        List<B3.c> listE = bVar.e();
                        if (d2.h.size() == listE.size()) {
                            I2[] i2Arr = d2.h.b;
                            int i2 = 0;
                            while (true) {
                                if (i2 >= i2Arr.length) {
                                    aVarA = a.f;
                                    break;
                                }
                                aVarA = j3.a(listE.get(i2).r(), i2Arr[i2], aVarA2);
                                if (aVarA.b()) {
                                    break;
                                }
                                i2++;
                            }
                        } else {
                            if (!J3.d && !K3.a(i)) {
                                x1f.a();
                                return null;
                            }
                            aVarA = a.c;
                        }
                    }
                } else {
                    if (!J3.d && !K3.a(i)) {
                        x01.a("Super type inconsistency in generic signature");
                        return null;
                    }
                    aVarA = a.b;
                }
            }
        } else {
            aVarA = a.f;
        }
        if (aVarA.b() && K3.a(this.b)) {
            com.android.tools.r8.shaking.Y0 y0A = this.a.t().a(d2);
            if (this.a.g().i() && !y0A.e(this.a.M())) {
                if (!d && y0A.f(this.a.M())) {
                    x1f.a();
                    return null;
                }
                C2742u50 c2742u50 = this.a.M().i;
                U3 u3A = U3.a(d2.Q0().toString(), "class", d2.e1(), d2.d, aVarA);
                synchronized (c2742u50) {
                    c2742u50.a(DiagnosticsLevel.INFO, u3A);
                }
            }
            d2.s = B3.b.f();
        }
        for (final C0231j1 c0231j1 : d2.C1()) {
            Objects.requireNonNull(c0231j1);
            a aVarA3 = a(new Supplier() { // from class: vk8
                @Override // java.util.function.Supplier
                public final Object get() {
                    return c0231j1.W0();
                }
            }, new Function() { // from class: yk8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return j3.a((B3.g) obj, aVarA2);
                }
            }, new Consumer() { // from class: bl8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a(c0231j1, d2, (L3.a) obj);
                }
            });
            if (!aVarA.b()) {
                aVarA = aVarA3;
            }
        }
        for (final C0210g1 c0210g1 : d2.M0()) {
            Objects.requireNonNull(c0210g1);
            a aVarA4 = a(new Supplier() { // from class: dl8
                @Override // java.util.function.Supplier
                public final Object get() {
                    return c0210g1.N0();
                }
            }, new Function() { // from class: fl8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return L3.a(j3, aVarA2, (B3.e) obj);
                }
            }, new Consumer() { // from class: gl8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a(c0210g1, d2, (L3.a) obj);
                }
            });
            if (!aVarA.b()) {
                aVarA = aVarA4;
            }
        }
        return aVarA;
    }

    public a a(Collection<D2> collection) {
        this.a.M().getClass();
        if (!this.a.M().b0()) {
            return a.f;
        }
        a aVar = a.f;
        Iterator<D2> it = collection.iterator();
        while (it.hasNext()) {
            a aVarA = a(it.next());
            if (!aVar.b()) {
                aVar = aVarA;
            }
        }
        return aVar;
    }

    public static L3 a(C0333y<?> c0333y, G3 g3) {
        return new L3(c0333y, g3, 2);
    }

    public final void a(C0231j1 c0231j1, D2 d2, a aVar) {
        C3435o1 c3435o1A = this.a.t().a(d2, c0231j1);
        if (this.a.g().i() && !c3435o1A.e(this.a.M())) {
            if (!d && c3435o1A.f(this.a.M())) {
                x1f.a();
                return;
            }
            C2742u50 c2742u50 = this.a.M().i;
            U3 u3A = U3.a(c0231j1.W0().toString(), "method", c0231j1.m0(), d2.d, aVar);
            synchronized (c2742u50) {
                c2742u50.a(DiagnosticsLevel.INFO, u3A);
            }
        }
        c0231j1.q = B3.g.d();
    }

    public final void a(C0210g1 c0210g1, D2 d2, a aVar) {
        C3380d1 c3380d1A = this.a.t().a(d2, c0210g1);
        if (this.a.g().i() && !c3380d1A.e(this.a.M())) {
            if (!d && c3380d1A.f(this.a.M())) {
                x1f.a();
                return;
            }
            C2742u50 c2742u50 = this.a.M().i;
            U3 u3A = U3.a(c0210g1.N0().toString(), "field", c0210g1.m0(), d2.d, aVar);
            synchronized (c2742u50) {
                c2742u50.a(DiagnosticsLevel.INFO, u3A);
            }
        }
        c0210g1.k = B3.e.p();
    }

    public final a a(Supplier supplier, Function function, Consumer consumer) {
        B3.d dVar = (B3.d) supplier.get();
        if (!dVar.a()) {
            a aVar = (a) function.apply(dVar);
            if (!d && !aVar.c() && !K3.a(this.b)) {
                x1f.a();
                return null;
            }
            if (aVar.b() && K3.a(this.b)) {
                consumer.accept(aVar);
            }
            return aVar;
        }
        return a.f;
    }

    public static a a(J3 j3, G3.a aVar, B3.e eVar) {
        j3.b.s.getClass();
        return j3.a(eVar, aVar);
    }
}
