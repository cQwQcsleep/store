package com.android.tools.r8.graph;

import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0346z5;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.F0;
import com.android.tools.r8.graph.InterfaceC0221h5;
import com.android.tools.r8.internal.AbstractC0728Oq;
import com.android.tools.r8.internal.AbstractC1597gi0;
import com.android.tools.r8.internal.AbstractC2173nV;
import com.android.tools.r8.internal.AbstractC3179zC;
import com.android.tools.r8.internal.C0613Ke;
import com.android.tools.r8.internal.C0702Nq;
import com.android.tools.r8.internal.C1159bb;
import com.android.tools.r8.internal.C1512fi0;
import com.android.tools.r8.internal.C2924wC;
import com.android.tools.r8.internal.InterfaceC0392Br;
import com.android.tools.r8.internal.KC;
import com.android.tools.r8.internal.MX;
import com.android.tools.r8.internal.RJ;
import com.android.tools.r8.kotlin.InterfaceC3298p;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.shaking.C3403i;
import com.android.tools.r8.utils.structural.A;
import defpackage.a06;
import defpackage.r43;
import defpackage.u53;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class D2 extends E0 implements InterfaceC0332x5, C5, Supplier<D2>, com.android.tools.r8.utils.structural.x<D2> {
    public static final D2[] B = new D2[0];
    public static final /* synthetic */ boolean C = true;
    public com.android.tools.r8.synthesis.L A;
    public final ProgramResource.Kind u;
    public C1159bb v;
    public boolean w;
    public InterfaceC3298p x;
    public AbstractC2173nV y;
    public final a z;

    public interface a {
        long a(D2 d2);
    }

    public D2(I2 i2, ProgramResource.Kind kind, Origin origin, Q q, I2 i3, K2 k2, H2 h2, C0228i5 c0228i5, List list, List list2, List list3, C0191d3 c0191d3, List list4, B3.b bVar, C0306u0 c0306u0, C0210g1[] c0210g1Arr, C0210g1[] c0210g1Arr2, H4.a aVar, boolean z, a aVar2, com.android.tools.r8.synthesis.L l) {
        super(h2, k2, q, i3, i2, c0210g1Arr, c0210g1Arr2, aVar, c0228i5, list, list2, list3, c0191d3, list4, bVar, c0306u0, origin, z);
        this.v = null;
        this.w = false;
        this.x = com.android.tools.r8.kotlin.d0.a;
        this.y = AbstractC2173nV.c;
        boolean z2 = C;
        if (!z2 && aVar2 == null) {
            x1f.a();
            throw null;
        }
        if (!z2 && c0306u0 == null) {
            x1f.a();
            throw null;
        }
        this.u = kind;
        this.z = aVar2;
        this.A = l;
    }

    public static /* synthetic */ boolean i(D2 d2) {
        return d2.s == B3.b.f();
    }

    public final void H1() {
        this.x = com.android.tools.r8.kotlin.d0.a;
    }

    public final C2924wC I1() {
        return AbstractC3179zC.a(L0(), new InterfaceC0392Br() { // from class: q53
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.e((C0231j1) obj);
            }
        });
    }

    public long J1() {
        return this.z.a(this);
    }

    public C1159bb K1() {
        return this.v;
    }

    public final InterfaceC3298p L1() {
        return this.x;
    }

    public B5 M1() {
        return i(a(I2.h));
    }

    public final boolean N1() {
        boolean zE;
        if (!n0().isEmpty()) {
            return true;
        }
        H4 h4 = this.l;
        synchronized (h4) {
            zE = h4.e();
        }
        return zE || a(this.k);
    }

    public final boolean O1() {
        return this.k.b.f() > 0;
    }

    public final boolean P1() {
        return this.l.b.h() > 0;
    }

    public final boolean Q1() {
        return P1() || O1();
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public final boolean R1() {
        return this.u == ProgramResource.Kind.CF;
    }

    public final C2924wC S1() {
        return m(new u53());
    }

    public final C0702Nq T1() {
        return AbstractC0728Oq.a(I1(), U1());
    }

    public final C2924wC U1() {
        return AbstractC3179zC.a(G1(), new InterfaceC0392Br() { // from class: p53
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.g((C0231j1) obj);
            }
        });
    }

    @Override // com.android.tools.r8.graph.E0
    public final V V0() {
        return V.c;
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1, com.android.tools.r8.graph.InterfaceC0265o0
    public D2 X() {
        return this;
    }

    @Override // com.android.tools.r8.graph.E0
    public final boolean a(C0333y c0333y, E0 e0, Predicate predicate, Set set) {
        E0 e0D;
        if (!set.add(getType()) || predicate.test(getType())) {
            return false;
        }
        if (isInterface()) {
            return b(c0333y, e0, predicate, set);
        }
        boolean z = C;
        if (!z && isInterface()) {
            x1f.a();
            return false;
        }
        if (!z && !set.contains(getType())) {
            x1f.a();
            return false;
        }
        if (!z && predicate.test(getType())) {
            x1f.a();
            return false;
        }
        if (f1()) {
            C0231j1 c0231j1O0 = O0();
            c0231j1O0.O0();
            if (!c0231j1O0.m.f()) {
                return true;
            }
        }
        if (K0()) {
            return true;
        }
        I2 i2 = this.g;
        if (i2 != null && ((e0D = c0333y.d(i2)) == null || e0D.a(c0333y, e0, predicate, set))) {
            return true;
        }
        for (I2 i3 : this.h) {
            i3.getClass();
            E0 e0D2 = c0333y.d(i3);
            if (e0D2 == null || e0D2.a(c0333y, e0, predicate, set)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1, com.android.tools.r8.graph.InterfaceC0265o0
    public final boolean a0() {
        return true;
    }

    @Override // com.android.tools.r8.graph.E0, com.android.tools.r8.graph.InterfaceC0265o0, com.android.tools.r8.graph.InterfaceC0332x5
    public final D2 asClass() {
        return this;
    }

    @Override // com.android.tools.r8.graph.E0, com.android.tools.r8.graph.InterfaceC0265o0, com.android.tools.r8.graph.InterfaceC0332x5
    public final E0 asClass() {
        return this;
    }

    public final boolean b(C0333y c0333y, E0 e0, Predicate predicate, Set set) {
        E0 e0D;
        boolean z = C;
        if (!z && !isInterface()) {
            x1f.a();
            return false;
        }
        if (!z && !set.contains(getType())) {
            x1f.a();
            return false;
        }
        if (!z && predicate.test(getType())) {
            x1f.a();
            return false;
        }
        if (this == e0) {
            if (f1()) {
                C0231j1 c0231j1O0 = O0();
                c0231j1O0.O0();
                if (!c0231j1O0.m.f()) {
                    return true;
                }
            }
            return K0();
        }
        if (f1()) {
            C0231j1 c0231j1O1 = O0();
            c0231j1O1.O0();
            if (!c0231j1O1.m.f() && V().b(new r43())) {
                return true;
            }
        }
        I2 i2 = this.g;
        if (i2 == null || ((e0D = c0333y.d(i2)) != null && !e0D.a(c0333y, e0, predicate, set))) {
            for (I2 i3 : this.h) {
                i3.getClass();
                E0 e0D2 = c0333y.d(i3);
                if (e0D2 == null || e0D2.a(c0333y, e0, predicate, set)) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // com.android.tools.r8.graph.E0
    public final boolean c(C0333y c0333y) {
        if (this.f.f()) {
            return true;
        }
        if (c0333y.g().i()) {
            if (!C && !c0333y.o()) {
                x1f.a();
                return false;
            }
            if (!c0333y.t().a(this).d(c0333y.M())) {
                C3403i c3403iM = c0333y.g().i() ? c0333y.a.m() : null;
                if (!C3403i.J) {
                    c3403iM.c();
                }
                if (!c3403iM.t.a(this)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final /* synthetic */ void d(Consumer consumer, C0210g1 c0210g1) {
        consumer.accept(new C0346z5(this, c0210g1));
    }

    public final void e(final Consumer consumer, Predicate predicate) {
        this.l.a(new Consumer() { // from class: v53
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.b(consumer, (C0231j1) obj);
            }
        }, predicate);
    }

    public final boolean f(C0333y c0333y) {
        boolean z;
        if (this.y.e()) {
            B1 b1A = c0333y.a();
            Iterator it = B1().iterator();
            loop0: while (true) {
                KC kc = (KC) it;
                z = false;
                if (!kc.hasNext()) {
                    break;
                }
                for (C0285r0 c0285r0 : ((AbstractC0217h1) kc.next()).n0().d) {
                    if (c0285r0.c.b == b1A.x5) {
                        z = true;
                        break loop0;
                    }
                }
            }
            this.y = AbstractC2173nV.a(z);
        }
        return this.y.d();
    }

    public final void g(Consumer consumer, final Predicate predicate) {
        e(consumer, new Predicate() { // from class: g53
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return D2.a(predicate, (C0231j1) obj);
            }
        });
    }

    @Override // java.util.function.Supplier
    public final D2 get() {
        return this;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0339y5
    public final InterfaceC0265o0 getContext() {
        return this;
    }

    public final void h(final Consumer consumer, Predicate predicate) {
        this.l.b(new Consumer() { // from class: z53
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.c(consumer, (C0231j1) obj);
            }
        }, predicate);
    }

    public final void j(Consumer consumer) {
        if (f1()) {
            consumer.accept(i(O0()));
        }
    }

    public final void k(Consumer consumer) {
        e(consumer, MX.b);
    }

    public final void l(final Consumer consumer) {
        g(new Consumer() { // from class: k53
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.c(consumer, (C0210g1) obj);
            }
        });
    }

    public final C2924wC m(Predicate predicate) {
        return new C2924wC(b(predicate), new InterfaceC0392Br() { // from class: a63
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.f((C0231j1) obj);
            }
        });
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public String m0() {
        return this.e.m0();
    }

    public final C2924wC n(Predicate predicate) {
        return AbstractC3179zC.a(l((Predicate<? super C0231j1>) predicate), new InterfaceC0392Br() { // from class: m53
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.h((C0231j1) obj);
            }
        });
    }

    public final void o(Consumer consumer) {
        e(consumer, new Predicate() { // from class: j53
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((C0231j1) obj).z0();
            }
        });
    }

    public final String toString() {
        return this.e.toString();
    }

    @Override // com.android.tools.r8.graph.E0
    public final boolean y1() {
        return false;
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1, com.android.tools.r8.graph.InterfaceC0265o0
    public final I2 z() {
        return getType();
    }

    public final /* synthetic */ B5 g(C0231j1 c0231j1) {
        return new B5(this, c0231j1);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: d63
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a2) {
                D2.a(a2);
            }
        };
    }

    public final /* synthetic */ void d(Consumer consumer, C0231j1 c0231j1) {
        consumer.accept(new B5(this, c0231j1));
    }

    public final /* synthetic */ B5 e(C0231j1 c0231j1) {
        return new B5(this, c0231j1);
    }

    public final /* synthetic */ B5 h(C0231j1 c0231j1) {
        return new B5(this, c0231j1);
    }

    public final void i(final Consumer consumer, Predicate predicate) {
        d(new Consumer() { // from class: x53
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.d(consumer, (C0210g1) obj);
            }
        }, predicate);
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    public final AbstractC0175b1 e() {
        return this;
    }

    public final B5 i(C0231j1 c0231j1) {
        if (c0231j1 != null) {
            return new B5(this, c0231j1);
        }
        return null;
    }

    public void n(Consumer<? super B5> consumer) {
        h(consumer, MX.b);
    }

    public final void m(Consumer consumer) {
        f(consumer, MX.b);
        n((Consumer<? super B5>) consumer);
    }

    public final void j(final Consumer consumer, Predicate predicate) {
        this.l.c(new Consumer() { // from class: o53
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.d(consumer, (C0231j1) obj);
            }
        }, predicate);
    }

    public final void f(final Consumer consumer, Predicate predicate) {
        c(new Consumer() { // from class: r53
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.b(consumer, (C0210g1) obj);
            }
        }, predicate);
    }

    public final /* synthetic */ B5 f(C0231j1 c0231j1) {
        return new B5(this, c0231j1);
    }

    public B5 f(C0322w2 c0322w2) {
        return i(V().b.a(c0322w2.C0(), c0322w2.x0()));
    }

    public final /* synthetic */ void c(Consumer consumer, C0210g1 c0210g1) {
        consumer.accept(new C0346z5(this, c0210g1));
    }

    public D2(I2 i2, ProgramResource.Kind kind, Origin origin, Q q, I2 i3, K2 k2, H2 h2, C0228i5 c0228i5, List<C0235j5> list, List<C0311u5> list2, List<J5> list3, C0191d3 c0191d3, List<Y3> list4, B3.b bVar, C0306u0 c0306u0, C0210g1[] c0210g1Arr, C0210g1[] c0210g1Arr2, H4.a aVar, boolean z, a aVar2) {
        this(i2, kind, origin, q, i3, k2, h2, c0228i5, list, list2, list3, c0191d3, list4, bVar, c0306u0, c0210g1Arr, c0210g1Arr2, aVar, z, aVar2, null);
    }

    public final /* synthetic */ void c(Consumer consumer, C0231j1 c0231j1) {
        consumer.accept(new B5(this, c0231j1));
    }

    public final AbstractC1597gi0 c(final Function function) {
        H4 h4V = V();
        return h4V.b.g(new Function() { // from class: n53
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(function, (C0231j1) obj);
            }
        });
    }

    public static void a(C0333y c0333y, com.android.tools.r8.dex.M m, RJ rj, B5 b5) {
        C0333y c0333y2;
        com.android.tools.r8.dex.M m2;
        C0231j1 c0231j1E = b5.e();
        if (!B5.f && c0231j1E.t) {
            x1f.a();
            return;
        }
        b5.getReference().a(c0333y, m);
        if (c0231j1E.i1()) {
            AbstractC0223i0 abstractC0223i0U0 = c0231j1E.U0();
            c0333y2 = c0333y;
            m2 = m;
            abstractC0223i0U0.p0().a(c0333y2, abstractC0223i0U0.a(c0333y), m2, b5, rj);
        } else {
            c0333y2 = c0333y;
            m2 = m;
        }
        c0231j1E.n0().a(c0333y2, m2);
        for (C0306u0 c0306u0 : c0231j1E.i.b) {
            c0306u0.a(c0333y2, m2);
        }
    }

    @Override // com.android.tools.r8.graph.E0, com.android.tools.r8.graph.InterfaceC0265o0, com.android.tools.r8.graph.InterfaceC0332x5
    public final E0 b() {
        return this;
    }

    public final AbstractC1597gi0 b(final BiFunction biFunction) {
        C0247l3 c0247l3 = this.k;
        return c0247l3.b.a(c0247l3.a, new BiFunction() { // from class: w53
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return D2.a(biFunction, (F0) obj, obj2);
            }
        });
    }

    public final void b(final Function function) {
        C0247l3 c0247l3 = this.k;
        c0247l3.b.b(c0247l3.a, new Function() { // from class: y53
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return D2.b(function, (F0) obj);
            }
        });
    }

    public final /* synthetic */ void b(Consumer consumer, C0210g1 c0210g1) {
        consumer.accept(new C0346z5(this, c0210g1));
    }

    public final /* synthetic */ void b(Consumer consumer, C0231j1 c0231j1) {
        consumer.accept(new B5(this, c0231j1));
    }

    public static D2 a(B1 b1) {
        I2 i2E = b1.e("LMock;");
        Origin originUnknown = Origin.unknown();
        Q qG = Q.g(0);
        I2 i2 = b1.a2;
        K2 k2N0 = K2.n0();
        List list = Collections.EMPTY_LIST;
        B3.b bVarF = B3.b.f();
        C0306u0 c0306u0O0 = C0306u0.o0();
        C0210g1[] c0210g1Arr = C0210g1.o;
        return new D2(i2E, null, originUnknown, qG, i2, k2N0, null, null, list, list, list, null, list, bVarF, c0306u0O0, c0210g1Arr, c0210g1Arr, H4.a.empty(), false, new a06());
    }

    public final C0346z5 b(C0245l1 c0245l1) {
        C0210g1 c0210g1A = a(c0245l1);
        if (c0210g1A != null) {
            return new C0346z5(this, c0210g1A);
        }
        return null;
    }

    public static /* synthetic */ AbstractC1597gi0 b(Function function, F0 f0) {
        return (AbstractC1597gi0) function.apply(f0.O());
    }

    public final void b(final com.android.tools.r8.dex.X x) {
        boolean z = C;
        if (!z && R0() != null) {
            x1f.a();
            return;
        }
        if (!z && !T0().isEmpty()) {
            x1f.a();
            return;
        }
        if (!z && this.s.b()) {
            x1f.a();
            return;
        }
        if (Q1()) {
            x.a(this);
            H4 h4 = this.l;
            Consumer consumer = new Consumer() { // from class: h53
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    D2.a(x, (C0231j1) obj);
                }
            };
            h4.getClass();
            h4.b(consumer, MX.b);
            this.k.a(new Consumer() { // from class: i53
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((F0) obj).e().a(x);
                }
            });
        }
        C0306u0 c0306u0N0 = n0();
        c0306u0N0.getClass();
        x.a(c0306u0N0);
        AbstractC0259n1.a(x, c0306u0N0.d);
        K2 k2 = this.h;
        if (k2 != null) {
            x.a(k2);
        }
    }

    @Override // com.android.tools.r8.graph.E0
    public final void a(Consumer consumer, Consumer consumer2, Consumer consumer3) {
        consumer.accept(this);
    }

    public final AbstractC1597gi0 a(final Function function) {
        C0247l3 c0247l3 = this.k;
        return c0247l3.b.a(c0247l3.a, new Function() { // from class: s53
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return D2.a(function, (F0) obj);
            }
        });
    }

    public static void a(com.android.tools.r8.utils.structural.A a2) {
        a2.e(new Function() { // from class: s43
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((D2) obj).e;
            }
        }).e(new Function() { // from class: w43
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((D2) obj).g;
            }
        }).e(new Function() { // from class: x43
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((D2) obj).h;
            }
        }).e(new Function() { // from class: y43
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((D2) obj).f;
            }
        }).j(new Function() { // from class: z43
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((D2) obj).i;
            }
        }).j(new Function() { // from class: a53
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((D2) obj).v;
            }
        }).b(new Predicate() { // from class: b53
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((D2) obj).w;
            }
        }).j(new Function() { // from class: d53
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((D2) obj).X0();
            }
        }).h(new Function() { // from class: e53
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((D2) obj).Y0();
            }
        }).e(new Function() { // from class: f53
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((D2) obj).n0();
            }
        }).a(new Predicate() { // from class: t43
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return D2.i((D2) obj);
            }
        }).h(new Function() { // from class: u43
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((D2) obj).B0();
            }
        }).h(new Function() { // from class: v43
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((D2) obj).D0();
            }
        });
    }

    public static D2 b(E0 e0) {
        if (e0 != null) {
            return e0.X();
        }
        return null;
    }

    @Override // com.android.tools.r8.graph.E0, com.android.tools.r8.graph.InterfaceC0265o0, com.android.tools.r8.graph.InterfaceC0332x5
    public final D2 b() {
        return this;
    }

    public static /* synthetic */ boolean a(Predicate predicate, C0231j1 c0231j1) {
        return c0231j1.q1() && predicate.test(c0231j1);
    }

    public static /* synthetic */ AbstractC1597gi0 a(Function function, F0 f0) {
        return (AbstractC1597gi0) function.apply(f0.O());
    }

    public static /* synthetic */ AbstractC1597gi0 a(BiFunction biFunction, F0 f0, Object obj) {
        return (AbstractC1597gi0) biFunction.apply(f0.O(), obj);
    }

    public final /* synthetic */ AbstractC1597gi0 a(Function function, C0231j1 c0231j1) {
        return (AbstractC1597gi0) function.apply(new B5(this, c0231j1));
    }

    public final AbstractC1597gi0 a(final Function function, final Predicate predicate) {
        H4 h4V = V();
        return h4V.b.g(new Function() { // from class: l53
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(predicate, function, (C0231j1) obj);
            }
        });
    }

    public final /* synthetic */ AbstractC1597gi0 a(Predicate predicate, Function function, C0231j1 c0231j1) {
        if (predicate.test(c0231j1)) {
            return (AbstractC1597gi0) function.apply(new B5(this, c0231j1));
        }
        return C1512fi0.c;
    }

    public final void a(final C0333y c0333y, final com.android.tools.r8.dex.M m, final RJ rj) {
        if (m.a(this)) {
            this.e.a(c0333y, m);
            I2 i2 = this.g;
            if (i2 != null) {
                i2.a(c0333y, m);
            } else if (!C && !this.e.Z0().equals("Ljava/lang/Object;")) {
                x1f.a();
                return;
            }
            H2 h2 = this.i;
            if (h2 != null) {
                m.a(h2);
            }
            n0().a(c0333y, m);
            K2 k2 = this.h;
            if (k2 != null) {
                k2.a(c0333y, m);
            }
            if (R0() != null) {
                C0191d3 c0191d3R0 = R0();
                I2 i3 = c0191d3R0.a;
                if (i3 != null) {
                    i3.a(c0333y, m);
                }
                C0322w2 c0322w2 = c0191d3R0.b;
                if (c0322w2 != null) {
                    c0322w2.a(c0333y, m);
                }
            }
            for (Y3 y3 : T0()) {
                y3.b.a(c0333y, m);
                I2 i4 = y3.c;
                if (i4 != null) {
                    i4.a(c0333y, m);
                }
                H2 h3 = y3.d;
                if (h3 != null) {
                    m.a(h3);
                }
            }
            f(new Consumer() { // from class: b63
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    D2.a(c0333y, m, (C0346z5) obj);
                }
            }, MX.b);
            n(new Consumer() { // from class: c63
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    D2.a(c0333y, m, rj, (B5) obj);
                }
            });
        }
    }

    @Override // com.android.tools.r8.graph.E0, com.android.tools.r8.graph.AbstractC0259n1
    public final void a(com.android.tools.r8.dex.X x) {
        boolean z = C;
        if (!z && R0() != null) {
            x1f.a();
            return;
        }
        if (!z && !T0().isEmpty()) {
            x1f.a();
            return;
        }
        if (!z && this.s.b()) {
            x1f.a();
        } else if (N1()) {
            x.a(this, new C0292s0(this));
        }
    }

    public static void a(com.android.tools.r8.dex.X x, C0231j1 c0231j1) {
        c0231j1.getClass();
        x.b(c0231j1);
    }

    public static void a(C0333y c0333y, com.android.tools.r8.dex.M m, C0346z5 c0346z5) {
        c0346z5.getReference().a(c0333y, m);
        C0210g1 c0210g1E = c0346z5.e();
        c0210g1E.n0().a(c0333y, m);
        if (c0210g1E.z0() && c0210g1E.S0()) {
            c0210g1E.R0().a(c0333y, m);
        }
    }

    public static boolean a(C0247l3 c0247l3) {
        boolean zB;
        synchronized (c0247l3) {
            zB = c0247l3.b();
        }
        return zB;
    }

    public final C0203f1 a(final AbstractC3345r0 abstractC3345r0) {
        for (C0210g1 c0210g1 : D1()) {
            O2 o2R0 = c0210g1.R0();
            if (o2R0 != null && o2R0 != O2.a(c0210g1.getReference().i)) {
                ArrayList arrayList = new ArrayList(D1());
                arrayList.sort(new Comparator() { // from class: c53
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        return ((C0210g1) obj).getReference().a((InterfaceC0221h5) ((C0210g1) obj2).getReference(), abstractC3345r0);
                    }
                });
                ArrayList arrayList2 = new ArrayList(arrayList.size());
                int i = 0;
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    C0210g1 c0210g2 = (C0210g1) arrayList.get(i2);
                    O2 o2R1 = c0210g2.R0();
                    if (!C && o2R1 == null) {
                        x1f.a();
                        return null;
                    }
                    arrayList2.add(o2R1);
                    I2 i3 = c0210g2.getReference().i;
                    o2R1.getClass();
                    if (o2R1 != O2.a(i3)) {
                        i = i2 + 1;
                    }
                }
                if (i <= 0) {
                    break;
                }
                return new C0203f1((O2[]) arrayList2.subList(0, i).toArray(O2.b));
            }
        }
        return null;
    }

    public final void a(List list, B1 b1) {
        if (list.isEmpty()) {
            return;
        }
        K2 k2 = this.h;
        I2[] i2Arr = (I2[]) Arrays.copyOf(k2.b, list.size() + k2.size());
        for (int size = this.h.size(); size < i2Arr.length; size++) {
            i2Arr[size] = ((B3.c) list.get(size - this.h.size())).b;
        }
        this.h = new K2(i2Arr);
        if (!C && list.isEmpty()) {
            x1f.a();
            return;
        }
        if (this.s.a()) {
            return;
        }
        D3 d3 = new D3();
        d3.c.addAll(this.s.e());
        d3.c.addAll(list);
        d3.b = this.s.d();
        d3.a.addAll(this.s.c());
        this.s = d3.a(b1);
    }

    public static Iterable a(final Collection collection, final InterfaceC0189d1 interfaceC0189d1) {
        return new Iterable() { // from class: t53
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return D2.a(collection, interfaceC0189d1);
            }
        };
    }

    public static /* synthetic */ Iterator a(Iterable iterable, InterfaceC0189d1 interfaceC0189d1) {
        return new C2(iterable, interfaceC0189d1);
    }

    public static long a(D2 d2) {
        throw new C0613Ke(d2 + " has no checksum information while checksum encoding is requested", d2.d);
    }
}
