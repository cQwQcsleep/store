package com.android.tools.r8.graph;

import com.android.tools.r8.graph.B3;
import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0235j5;
import com.android.tools.r8.graph.E0;
import com.android.tools.r8.graph.F0;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC0728Oq;
import com.android.tools.r8.internal.AbstractC2173nV;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.AbstractC3179zC;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.C0613Ke;
import com.android.tools.r8.internal.C0702Nq;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C2753uC;
import com.android.tools.r8.internal.C2838vC;
import com.android.tools.r8.internal.C2924wC;
import com.android.tools.r8.internal.EC;
import com.android.tools.r8.internal.IC;
import com.android.tools.r8.internal.InterfaceC0392Br;
import com.android.tools.r8.internal.JC;
import com.android.tools.r8.internal.KC;
import com.android.tools.r8.internal.Kk0;
import com.android.tools.r8.internal.MX;
import com.android.tools.r8.internal.NC;
import com.android.tools.r8.internal.P40;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.Reference;
import defpackage.c24;
import defpackage.h24;
import defpackage.hih;
import defpackage.v14;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class E0 extends AbstractC0175b1 implements S, InterfaceC0174b0 {
    public static final /* synthetic */ boolean t = true;
    public final Origin d;
    public final I2 e;
    public final Q f;
    public I2 g;
    public K2 h;
    public H2 i;
    public AbstractC2173nV j;
    public final C0247l3 k;
    public final H4 l;
    public C0191d3 m;
    public List n;
    public C0228i5 o;
    public List p;
    public List q;
    public final List r;
    protected B3.b s;

    public E0(H2 h2, K2 k2, Q q, I2 i2, I2 i3, C0210g1[] c0210g1Arr, C0210g1[] c0210g1Arr2, H4.a aVar, C0228i5 c0228i5, List list, List list2, List list3, C0191d3 c0191d3, List list4, B3.b bVar, C0306u0 c0306u0, Origin origin, boolean z) {
        super(c0306u0);
        this.j = AbstractC2173nV.g();
        boolean z2 = t;
        if (!z2 && origin == null) {
            x1f.a();
            throw null;
        }
        this.d = origin;
        this.i = h2;
        this.h = k2;
        this.f = q;
        this.g = i2;
        this.e = i3;
        this.k = C0247l3.a(this, c0210g1Arr, c0210g1Arr2);
        this.l = aVar.a(this);
        this.o = c0228i5;
        this.p = list;
        if (!z2 && list == null) {
            x1f.a();
            throw null;
        }
        this.q = list2;
        this.r = list3;
        if (!z2 && list2 == null) {
            x1f.a();
            throw null;
        }
        this.m = c0191d3;
        this.n = list4;
        if (!z2 && bVar == null) {
            x1f.a();
            throw null;
        }
        this.s = bVar;
        if (!z2 && !T3.a(bVar, c0306u0)) {
            x1f.a();
            throw null;
        }
        if (i3 == i2) {
            throw new C0613Ke("Class " + i3.toString() + " cannot extend itself");
        }
        I2[] i2Arr = k2.b;
        for (I2 i4 : i2Arr) {
            if (i3 == i4) {
                throw new C0613Ke("Interface " + i3.toString() + " cannot implement itself");
            }
        }
        if (z || i3.f.s0()) {
            return;
        }
        throw new C0613Ke("Class descriptor '" + i3.f.toString() + "' cannot be represented in dex format.");
    }

    public static /* synthetic */ boolean c(C0210g1 c0210g1) {
        return c0210g1.S0() && c0210g1.R0().b1();
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1
    public final boolean A0() {
        return false;
    }

    public boolean A1() {
        return this.f.M();
    }

    public final List B0() {
        return this.k.a();
    }

    public final C0702Nq B1() {
        return AbstractC0728Oq.a(M0(), C1());
    }

    public final Iterable C0() {
        final Iterator itA;
        I2 i2 = this.g;
        if (i2 != null) {
            IC ic = new IC(i2);
            I2[] i2Arr = this.h.b;
            JC jcA = NC.a(i2Arr.length, 0, i2Arr);
            jcA.getClass();
            itA = new KC(new EC(new Iterator[]{ic, jcA}));
        } else {
            I2[] i2Arr2 = this.h.b;
            itA = NC.a(i2Arr2.length, 0, i2Arr2);
        }
        return new Iterable() { // from class: a24
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return E0.a(itA);
            }
        };
    }

    public Iterable<C0231j1> C1() {
        return this.l.b.e();
    }

    @Override // com.android.tools.r8.graph.InterfaceC0174b0
    public final E0 D() {
        return null;
    }

    public List<C0231j1> D0() {
        return this.l.a();
    }

    public List<C0210g1> D1() {
        return this.k.b.g();
    }

    public final C2924wC E0() {
        return AbstractC3179zC.a(M0(), new InterfaceC0392Br() { // from class: d24
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.b((C0210g1) obj);
            }
        });
    }

    public final boolean E1() {
        return Q0().e().isEmpty() || this.h.b.length == Q0().e().size();
    }

    public final C2924wC F0() {
        return AbstractC3179zC.a(C1(), new InterfaceC0392Br() { // from class: v24
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.b((C0231j1) obj);
            }
        });
    }

    public final C2924wC F1() {
        return AbstractC3179zC.a(G1(), new InterfaceC0392Br() { // from class: l24
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.c((C0231j1) obj);
            }
        });
    }

    public final void G0() {
        this.s = B3.b.f();
    }

    public Iterable<C0231j1> G1() {
        return this.l.b.j();
    }

    public final C0210g1[] H0() {
        List<C0210g1> listM1 = m1();
        this.k.b.a();
        return (C0210g1[]) listM1.toArray(C0210g1.o);
    }

    public final void I0() {
        this.o = null;
    }

    public final C0210g1[] J0() {
        List<C0210g1> listD1 = D1();
        this.k.b.b();
        return (C0210g1[]) listD1.toArray(C0210g1.o);
    }

    public final boolean K0() {
        return D1().stream().anyMatch(new Predicate() { // from class: b24
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return E0.c((C0210g1) obj);
            }
        });
    }

    public Iterable<C0231j1> L0() {
        return this.l.b.c();
    }

    public Iterable<C0210g1> M0() {
        return c(MX.b);
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1, com.android.tools.r8.graph.InterfaceC0265o0
    /* JADX INFO: renamed from: N0, reason: merged with bridge method [inline-methods] */
    public Q getAccessFlags() {
        return this.f;
    }

    public final C0231j1 O0() {
        C0231j1 c0231j1;
        H4 h4 = this.l;
        synchronized (h4) {
            try {
                if (h4.c == C0231j1.v) {
                    h4.c = null;
                    for (C0231j1 c0231j2 : h4.b.c()) {
                        if (c0231j2.m1()) {
                            h4.c = c0231j2;
                            break;
                        }
                    }
                }
                c0231j1 = h4.c;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (t || c0231j1 != C0231j1.v) {
            return c0231j1;
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0174b0
    public final E0 P() {
        return this;
    }

    public ClassReference P0() {
        return Reference.classFromDescriptor(getType().Z0());
    }

    @Override // com.android.tools.r8.graph.InterfaceC0174b0
    public final boolean Q() {
        return true;
    }

    public B3.b Q0() {
        return this.s;
    }

    public C0191d3 R0() {
        return this.m;
    }

    public Y3 S0() {
        for (Y3 y3 : T0()) {
            if (this.e == y3.b()) {
                return y3;
            }
        }
        return null;
    }

    public List<Y3> T0() {
        return this.n;
    }

    public K2 U0() {
        return this.h;
    }

    @Override // com.android.tools.r8.graph.S
    public H4 V() {
        return this.l;
    }

    public abstract V V0();

    public I2 W0() {
        if (x1()) {
            return this.o.a();
        }
        if (w1()) {
            return this.e;
        }
        return null;
    }

    public C0228i5 X0() {
        return this.o;
    }

    public List<C0235j5> Y0() {
        return this.p;
    }

    @Override // com.android.tools.r8.graph.S
    public C0281q2 Z() {
        return null;
    }

    public List<C0311u5> Z0() {
        return this.q;
    }

    public final void a(List list, final BiConsumer biConsumer) {
        if (!t && !E1()) {
            x1f.a();
            return;
        }
        if (Q0().e().isEmpty()) {
            e(new Consumer() { // from class: r24
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    E0.a(biConsumer, (I2) obj);
                }
            });
            return;
        }
        Iterator<B3.c> it = Q0().e().iterator();
        for (I2 i2 : Arrays.asList(this.h.b)) {
            if (!t && !it.hasNext()) {
                x1f.a();
                return;
            }
            B3.c next = it.next();
            if (list.isEmpty()) {
                Iterator<B3.e> it2 = next.c.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (it2.next().o()) {
                            int i = AbstractC0551Hu.c;
                            biConsumer.accept(i2, P40.e);
                            break;
                        }
                    }
                }
            }
            biConsumer.accept(i2, a(next, list));
        }
        if (t || !it.hasNext()) {
            return;
        }
        x1f.a();
    }

    public abstract void a(Consumer consumer, Consumer consumer2, Consumer consumer3);

    public abstract boolean a(C0333y c0333y, E0 e0, Predicate predicate, Set set);

    public List<J5> a1() {
        return this.r;
    }

    public E0 asClass() {
        return this;
    }

    public final void b(Collection collection) {
        H4 h4 = this.l;
        if (!H4.d) {
            h4.getClass();
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                C0231j1 c0231j1 = (C0231j1) it.next();
                if (!H4.d) {
                    h4.c(c0231j1);
                }
            }
        }
        h4.b.b(collection);
    }

    @Override // com.android.tools.r8.graph.S
    public boolean b0() {
        return false;
    }

    public final String b1() {
        return getType().G0();
    }

    public H2 c1() {
        return this.i;
    }

    public final boolean d(C0333y c0333y) {
        boolean zA;
        if (this.j.e()) {
            if (b0()) {
                zA = com.android.tools.r8.internal.C2.a(Z(), c0333y);
            } else {
                boolean z = true;
                for (I2 i2 : C0()) {
                    i2.getClass();
                    E0 e0D = c0333y.d(i2);
                    z &= e0D != null && e0D.d(c0333y);
                    if (!z) {
                        break;
                    }
                }
                zA = z;
            }
            this.j = AbstractC2173nV.a(zA);
        }
        if (t || !this.j.e()) {
            return this.j.d();
        }
        x1f.a();
        return false;
    }

    public I2 d1() {
        return this.g;
    }

    public final C0231j1 e(C0322w2 c0322w2) {
        H4 h4 = this.l;
        C0231j1 c0231j1C = h4.b.c(c0322w2);
        if (c0231j1C != null) {
            h4.b.getClass();
            if (c0231j1C.g.F()) {
                h4.c = C0231j1.v;
                return c0231j1C;
            }
            if (!H4.d) {
                h4.b.getClass();
                if (!c0231j1C.M0()) {
                    x1f.a();
                    return null;
                }
            }
        }
        return c0231j1C;
    }

    public final String e1() {
        return getType().H0();
    }

    public final Iterable f(Predicate predicate) {
        return C2753uC.a(this.l.b.e(), predicate);
    }

    public final boolean f1() {
        return O0() != null;
    }

    public final void g(Predicate predicate) {
        C0191d3 c0191d3 = this.m;
        if (c0191d3 == null || !predicate.test(c0191d3)) {
            return;
        }
        this.m = null;
    }

    public final boolean g1() {
        return a(I2.h) != null;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    public final Origin getOrigin() {
        return this.d;
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1, com.android.tools.r8.graph.InterfaceC0265o0
    public F2 getReference() {
        return getType();
    }

    @Override // com.android.tools.r8.graph.S
    public I2 getType() {
        return this.e;
    }

    public void h(Consumer<C0231j1> consumer) {
        H4 h4 = this.l;
        h4.getClass();
        h4.b(consumer, MX.b);
    }

    public final boolean h1() {
        return this.m != null;
    }

    public final void i(final Consumer consumer) {
        if (t || w1()) {
            Y0().forEach(new Consumer() { // from class: f24
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    consumer.accept(((C0235j5) obj).a());
                }
            });
        } else {
            x1f.a();
        }
    }

    public boolean i1() {
        return this.k.b.d() > 0;
    }

    @Override // com.android.tools.r8.graph.S
    public boolean isInterface() {
        return this.f.L();
    }

    public final void j(Predicate predicate) {
        this.q.removeIf(predicate);
    }

    public boolean j1() {
        return this.k.b.e() > 0;
    }

    public final void k(Predicate predicate) {
        if (this.r.isEmpty()) {
            return;
        }
        this.r.removeIf(predicate);
    }

    public final boolean k1() {
        for (C0231j1 c0231j1 : L0()) {
            if (c0231j1.z0() && c0231j1.g.O()) {
                return true;
            }
        }
        return false;
    }

    public Iterable<C0231j1> l(Predicate<? super C0231j1> predicate) {
        Iterable<C0231j1> iterableG1 = G1();
        Objects.requireNonNull(predicate);
        return AbstractC3179zC.c(iterableG1, new h24(predicate));
    }

    public final boolean l1() {
        return this.g != null;
    }

    @Override // com.android.tools.r8.graph.S
    public I0 m() {
        return null;
    }

    public List<C0210g1> m1() {
        return this.k.b.c();
    }

    public boolean n1() {
        return this.f.I();
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1
    public final E0 o0() {
        return this;
    }

    public final boolean o1() {
        return this.f.J();
    }

    public boolean p1() {
        Y3 y3S0 = S0();
        return y3S0 != null && y3S0.d == null;
    }

    public boolean q1() {
        return this instanceof I0;
    }

    public boolean r1() {
        return this.f.K();
    }

    public final boolean s1() {
        return this.f.f();
    }

    public boolean t1() {
        return w1() || x1();
    }

    public boolean u1() {
        Y3 y3S0 = S0();
        return y3S0 != null && y3S0.d() == null && y3S0.e();
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1
    public final boolean v0() {
        return true;
    }

    public boolean v1() {
        Y3 y3S0 = S0();
        boolean z = (y3S0 == null || y3S0.d() == null || !y3S0.e()) ? false : true;
        if (t || !z || R0() == null) {
            return z;
        }
        x1f.a();
        return false;
    }

    public boolean w1() {
        return !this.p.isEmpty();
    }

    public boolean x1() {
        return this.o != null;
    }

    public abstract boolean y1();

    public boolean z1() {
        return this.f.m();
    }

    public final void h(Predicate predicate) {
        this.n.removeIf(predicate);
    }

    public final void f(Consumer consumer) {
        I2 i2 = this.g;
        if (i2 != null) {
            consumer.accept(i2);
        }
        e(consumer);
    }

    public final void g(Consumer consumer) {
        d(MX.b).forEach(consumer);
    }

    public final Iterable c(Predicate predicate) {
        return this.k.b.a(predicate);
    }

    public final /* synthetic */ H0 c(C0231j1 c0231j1) {
        return H0.a(this, c0231j1);
    }

    public final void c(Consumer consumer, Predicate predicate) {
        c(predicate).forEach(consumer);
    }

    public final C0231j1 c(C0322w2 c0322w2) {
        return this.l.b.a(c0322w2.C0(), c0322w2.x0());
    }

    public boolean c(C0333y c0333y) {
        return this.f.f();
    }

    public final void i(Predicate predicate) {
        this.p.removeIf(predicate);
    }

    @Override // com.android.tools.r8.graph.S
    public void c(Consumer<? super H0> consumer) {
        b(consumer, MX.b);
    }

    @Override // com.android.tools.r8.graph.InterfaceC0174b0
    public final void b(Consumer consumer) {
        consumer.accept(this);
    }

    public final void b(final Consumer consumer, Predicate predicate) {
        this.l.b(new Consumer() { // from class: s24
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(consumer, (C0231j1) obj);
            }
        }, predicate);
    }

    public final /* synthetic */ F0 b(C0210g1 c0210g1) {
        return F0.a(this, c0210g1);
    }

    public final /* synthetic */ H0 b(C0231j1 c0231j1) {
        return H0.a(this, c0231j1);
    }

    public final C2838vC b(Predicate predicate) {
        Iterable<C0231j1> iterableL0 = L0();
        Objects.requireNonNull(predicate);
        return AbstractC3179zC.c(iterableL0, new h24(predicate));
    }

    public E0 b() {
        return this;
    }

    public final void b(C0210g1[] c0210g1Arr) {
        C0247l3 c0247l3 = this.k;
        c0247l3.b.b(c0210g1Arr);
        if (C0247l3.c) {
            return;
        }
        c0247l3.b.h();
    }

    public static /* synthetic */ boolean b(H2 h2, C0210g1 c0210g1) {
        return c0210g1.F0() == h2;
    }

    public final C0210g1 b(final H2 h2) {
        Predicate predicate = new Predicate() { // from class: i24
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return E0.b(h2, (C0210g1) obj);
            }
        };
        C0210g1 c0210g1 = null;
        for (C0210g1 c0210g2 : D1()) {
            if (predicate.test(c0210g2)) {
                if (c0210g1 != null) {
                    return null;
                }
                c0210g1 = c0210g2;
            }
        }
        return c0210g1;
    }

    public C0231j1 e(Predicate<C0231j1> predicate) {
        return this.l.b.b(predicate);
    }

    public final C0231j1 b(C0322w2 c0322w2) {
        return this.l.b.a(c0322w2);
    }

    public final boolean e(C0333y c0333y) {
        C0229j c0229j = (C0229j) c0333y.g();
        return c0229j.c(this.e, c0229j.a().G5);
    }

    public final void b(final BiConsumer biConsumer) {
        I2 i2 = this.g;
        if (i2 != null) {
            biConsumer.accept(i2, Boolean.FALSE);
        }
        e(new Consumer() { // from class: p24
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                biConsumer.accept((I2) obj, Boolean.TRUE);
            }
        });
    }

    public final void e(Consumer consumer) {
        for (I2 i2 : this.h.b) {
            consumer.accept(i2);
        }
    }

    public final void b(List list, BiConsumer biConsumer) {
        I2 i2 = this.g;
        if (i2 != null) {
            biConsumer.accept(i2, a(Q0().d(), list));
        }
        a(list, biConsumer);
    }

    public final ArrayList b(C0333y c0333y) {
        final ArrayList arrayList = new ArrayList();
        while (this != null && this.e != c0333y.a().a2) {
            this.a(new Consumer() { // from class: y14
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    arrayList.add((F0) obj);
                }
            }, new v14());
            this = c0333y.d(this.g);
        }
        return arrayList;
    }

    public final void d(Consumer consumer, Predicate predicate) {
        C2753uC.a(D1(), predicate).forEach(consumer);
    }

    public final C2838vC d(Predicate predicate) {
        List<C0210g1> listM1 = m1();
        Objects.requireNonNull(predicate);
        return AbstractC3179zC.c(listM1, new c24(predicate));
    }

    public final C0231j1 d(C0322w2 c0322w2) {
        return this.l.b.b(c0322w2);
    }

    public final H0 d(C0231j1 c0231j1) {
        if (c0231j1 != null) {
            return H0.a(this, c0231j1);
        }
        return null;
    }

    public final void d(Consumer consumer) {
        c(MX.b).forEach(consumer);
    }

    @Override // com.android.tools.r8.graph.S
    public void a(Consumer<? super F0> consumer) {
        a(consumer, MX.b);
    }

    public final void a(final Consumer consumer, Predicate predicate) {
        c(predicate).forEach(new Consumer() { // from class: u24
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(consumer, (C0210g1) obj);
            }
        });
    }

    public final /* synthetic */ void a(Consumer consumer, C0210g1 c0210g1) {
        consumer.accept(F0.a(this, c0210g1));
    }

    public final /* synthetic */ void a(Consumer consumer, C0231j1 c0231j1) {
        consumer.accept(H0.a(this, c0231j1));
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public void a(com.android.tools.r8.dex.X x) {
        throw new Kk0();
    }

    public final void a(C0231j1 c0231j1) {
        H4 h4 = this.l;
        h4.getClass();
        h4.c = C0231j1.v;
        h4.b.a(c0231j1);
    }

    public final void a(Collection collection) {
        H4 h4 = this.l;
        h4.getClass();
        if (!H4.d) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                C0231j1 c0231j1 = (C0231j1) it.next();
                if (!H4.d) {
                    h4.c(c0231j1);
                }
            }
        }
        h4.c = C0231j1.v;
        h4.b.a(collection);
    }

    public final void a(C0231j1[] c0231j1Arr) {
        H4 h4 = this.l;
        if (!H4.d) {
            h4.getClass();
            if (c0231j1Arr != null) {
                for (C0231j1 c0231j1 : Arrays.asList(c0231j1Arr)) {
                    if (!H4.d) {
                        h4.c(c0231j1);
                    }
                }
            }
        }
        h4.b.b(c0231j1Arr);
    }

    public final void a(Iterable iterable, C2752uB c2752uB) {
        if (!c2752uB.f() || n1()) {
            return;
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            C0231j1 c0231j1 = (C0231j1) it.next();
            if (!t && c0231j1.k1()) {
                hih.a("Non-abstract method on abstract class: `", c0231j1.getReference().m0(), "`");
                return;
            }
        }
    }

    public final void a(C0210g1 c0210g1) {
        C0247l3 c0247l3 = this.k;
        boolean z = C0247l3.c;
        if (!z) {
            c0247l3.a(c0210g1);
        }
        c0247l3.b.b(c0210g1);
        if (z) {
            return;
        }
        c0247l3.b.h();
    }

    public final void a(C0210g1[] c0210g1Arr) {
        C0247l3 c0247l3 = this.k;
        c0247l3.b.a(c0210g1Arr);
        if (C0247l3.c) {
            return;
        }
        c0247l3.b.h();
    }

    public final C0210g1 a(C0245l1 c0245l1) {
        return this.k.b.a(c0245l1);
    }

    public static /* synthetic */ boolean a(H2 h2, C0210g1 c0210g1) {
        return c0210g1.F0() == h2;
    }

    public final C0210g1 a(final H2 h2) {
        Predicate predicate = new Predicate() { // from class: n24
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return E0.a(h2, (C0210g1) obj);
            }
        };
        C0210g1 c0210g1 = null;
        for (C0210g1 c0210g2 : m1()) {
            if (predicate.test(c0210g2)) {
                if (c0210g1 != null) {
                    return null;
                }
                c0210g1 = c0210g2;
            }
        }
        return c0210g1;
    }

    public final H0 a(C0322w2 c0322w2) {
        return d(this.l.b.a(c0322w2.C0(), c0322w2.x0()));
    }

    public final C0231j1 a(E2 e2, H2 h2) {
        return this.l.b.a(e2, h2);
    }

    public final boolean a(C0231j1 c0231j1, B1 b1) {
        if (!t) {
            I2 i2 = this.e;
            I2 i3 = b1.E2;
            i2.getClass();
            if (!I2.a(i2, i3)) {
                I2 i4 = this.e;
                I2 i5 = b1.D2;
                i4.getClass();
                if (!I2.a(i4, i5)) {
                    x1f.a();
                    return false;
                }
            }
        }
        if (c0231j1.g.P() && c0231j1.g.M() && c0231j1.c1().size() == 1) {
            I2 i2J = c0231j1.j(0);
            I2 i6 = b1.d2;
            i2J.getClass();
            if (I2.a(i2J, i6)) {
                return true;
            }
        }
        return false;
    }

    public final C0231j1 a(I2[] i2Arr) {
        for (C0231j1 c0231j1 : L0()) {
            if (c0231j1.q1() && Arrays.equals(c0231j1.getReference().i.f.b, i2Arr)) {
                return c0231j1;
            }
        }
        return null;
    }

    public boolean a(C0333y<?> c0333y) {
        return a(c0333y, this, MX.c, AbstractC2780ub0.c());
    }

    public final boolean a(final C0333y c0333y, final InterfaceC0265o0 interfaceC0265o0) {
        return a(c0333y, this, new Predicate() { // from class: t24
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return c0333y.a(interfaceC0265o0.z(), (I2) obj).d();
            }
        }, AbstractC2780ub0.c());
    }

    public final void a(final InterfaceC0189d1 interfaceC0189d1, final BiPredicate biPredicate, final BiConsumer biConsumer) {
        f(new Consumer() { // from class: z14
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                E0.a(interfaceC0189d1, biPredicate, biConsumer, (I2) obj);
            }
        });
    }

    public static /* synthetic */ void a(InterfaceC0189d1 interfaceC0189d1, BiPredicate biPredicate, BiConsumer biConsumer, I2 i2) {
        E0 e0D = interfaceC0189d1.d(i2);
        if (biPredicate.test(i2, e0D)) {
            biConsumer.accept(i2, e0D);
        }
    }

    public final void a(final BiConsumer biConsumer) {
        if (!t && !E1()) {
            x1f.a();
            return;
        }
        if (Q0().e().isEmpty()) {
            e(new Consumer() { // from class: g24
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    I2 i2 = (I2) obj;
                    biConsumer.accept(i2, new B3.c(i2));
                }
            });
            return;
        }
        Iterator it = Arrays.asList(this.h.b).iterator();
        Iterator<B3.c> it2 = Q0().e().iterator();
        while (it.hasNext()) {
            if (!t && !it2.hasNext()) {
                x1f.a();
                return;
            }
            biConsumer.accept((I2) it.next(), it2.next());
        }
    }

    public final void a(B1 b1, BiConsumer biConsumer) {
        I2 i2 = this.g;
        if (i2 != null) {
            B3.c cVar = this.s.b;
            if (cVar == null) {
                cVar = new B3.c(b1.a2);
            }
            biConsumer.accept(i2, cVar);
        }
        a(biConsumer);
    }

    public static /* synthetic */ Iterator a(Iterator it) {
        return it;
    }

    public static void a(BiConsumer biConsumer, I2 i2) {
        int i = AbstractC0551Hu.c;
        biConsumer.accept(i2, P40.e);
    }

    public final List a(B3.c cVar, final List list) {
        if (cVar == null) {
            return Collections.EMPTY_LIST;
        }
        final C0473Eu c0473EuG = AbstractC0551Hu.g();
        cVar.r().forEach(new Consumer() { // from class: u14
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(list, c0473EuG, (B3.e) obj);
            }
        });
        return c0473EuG.a();
    }

    public final void a(List list, C0473Eu c0473Eu, B3.e eVar) {
        if (eVar.o()) {
            for (int i = 0; i < Q0().c().size(); i++) {
                if (Q0().c().get(i).a.equals(eVar.i().b)) {
                    if (i >= list.size()) {
                        if (!t) {
                            x1f.a();
                            return;
                        }
                    } else {
                        c0473Eu.a((B3.e) list.get(i));
                    }
                }
            }
            return;
        }
        c0473Eu.a(eVar);
    }

    public final void a(B3.b bVar) {
        this.s = bVar;
    }

    public final boolean a(E0 e0) {
        if (!t1() || !e0.t1()) {
            return false;
        }
        I2 i2W0 = W0();
        I2 i2W1 = e0.W0();
        i2W0.getClass();
        return I2.a(i2W0, i2W1);
    }

    public final void a(List list) {
        this.p = list;
    }

    public final void a(C2752uB c2752uB) {
        boolean z = t;
        if (!z) {
            a(G1(), c2752uB);
        }
        if (!z && isInterface() && V().b(new Predicate() { // from class: e24
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((C0231j1) obj).o1();
            }
        })) {
            x1f.a();
            return;
        }
        if (!z) {
            this.k.c();
        }
        if (z) {
            return;
        }
        this.l.j();
    }
}
