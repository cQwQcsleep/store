package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.AbstractC0175b1;
import com.android.tools.r8.graph.AbstractC0184c3;
import com.android.tools.r8.graph.AbstractC0263n5;
import com.android.tools.r8.graph.AbstractC0327x0;
import com.android.tools.r8.graph.AbstractC0330x3;
import com.android.tools.r8.graph.B4;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0177b3;
import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0226i3;
import com.android.tools.r8.graph.C0229j;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0233j3;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0256m5;
import com.android.tools.r8.graph.C0310u4;
import com.android.tools.r8.graph.C0313v0;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.C0346z5;
import com.android.tools.r8.graph.C4;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.F2;
import com.android.tools.r8.graph.I5;
import com.android.tools.r8.graph.InterfaceC0265o0;
import com.android.tools.r8.graph.InterfaceC0331x4;
import com.android.tools.r8.graph.InterfaceC0332x5;
import com.android.tools.r8.graph.N5;
import com.android.tools.r8.graph.T4;
import com.android.tools.r8.graph.Y5;
import com.android.tools.r8.internal.AbstractC0439Dm;
import com.android.tools.r8.internal.AbstractC0464El;
import com.android.tools.r8.internal.AbstractC2229o50;
import com.android.tools.r8.internal.AbstractC2554rv;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C0385Bk;
import com.android.tools.r8.internal.C0412Cl;
import com.android.tools.r8.internal.C0438Dl;
import com.android.tools.r8.internal.C1159bb;
import com.android.tools.r8.internal.C1819jJ;
import com.android.tools.r8.internal.C1870jv;
import com.android.tools.r8.internal.C2098md;
import com.android.tools.r8.internal.C2283oj0;
import com.android.tools.r8.internal.C2441qd;
import com.android.tools.r8.internal.C2620sh0;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C2847vL;
import com.android.tools.r8.internal.C2924wC;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.EnumC2630sm0;
import com.android.tools.r8.internal.FX;
import com.android.tools.r8.internal.GC;
import com.android.tools.r8.internal.InterfaceC1681hh0;
import com.android.tools.r8.internal.NC;
import com.android.tools.r8.internal.Ng0;
import com.android.tools.r8.internal.Sm0;
import com.android.tools.r8.internal.UY;
import com.android.tools.r8.internal.XR;
import com.android.tools.r8.internal.XS;
import com.android.tools.r8.internal.YS;
import com.android.tools.r8.internal.ZS;
import com.android.tools.r8.shaking.C3375c1;
import com.android.tools.r8.shaking.C3400h1;
import com.android.tools.r8.shaking.C3403i;
import com.android.tools.r8.shaking.C3430n1;
import com.android.tools.r8.shaking.X0;
import com.android.tools.r8.synthesis.C3492a;
import defpackage.hih;
import defpackage.hni;
import defpackage.s4h;
import defpackage.w0h;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3403i extends C0229j implements com.android.tools.r8.graph.Z3 {
    public static final /* synthetic */ boolean J = true;
    public final FX A;
    public final Set B;
    public final Map C;
    public final Set D;
    public final ZS E;
    public final Set F;
    public final Map G;
    public final m4 H;
    public C1159bb I;
    public final Set j;
    public final Set k;
    public Set l;
    public final Set m;
    public final Set n;
    public final Set o;
    public final Set p;
    public final Set q;
    public Set r;
    public final C0226i3 s;
    public final AbstractC0263n5 t;
    public final Map u;
    public final AbstractC3405i1 v;
    public final Map w;
    public final Set x;
    public final Set y;
    public final Set z;

    public C3403i(C3403i c3403i, Map map) {
        super(c3403i.g().a(c3403i.b()), c3403i.g, c3403i.f(), c3403i.h);
        this.H = new m4();
        this.I = null;
        this.j = c3403i.j;
        this.k = c3403i.k;
        this.l = c3403i.l;
        this.m = c3403i.m;
        this.n = c3403i.n;
        this.o = c3403i.o;
        this.p = c3403i.p;
        this.q = c3403i.q;
        this.r = c3403i.r;
        this.s = c3403i.s;
        this.t = c3403i.t;
        this.v = c3403i.v;
        this.w = c3403i.w;
        this.u = c3403i.u;
        this.x = c3403i.x;
        this.y = c3403i.y;
        this.z = c3403i.z;
        this.A = c3403i.A;
        this.E = c3403i.E;
        this.F = c3403i.F;
        this.G = map;
        this.B = c3403i.B;
        this.C = c3403i.C;
        this.D = c3403i.D;
        c3403i.e.f();
        if (J) {
            return;
        }
        r();
    }

    /* JADX WARN: Code duplicated, block: B:108:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:58:0x00f8  */
    public final com.android.tools.r8.graph.H0 a(C0333y c0333y, C0322w2 c0322w2, T4.c cVar, B5 b5, boolean z, L1 l1, final AbstractC0439Dm abstractC0439Dm) {
        com.android.tools.r8.graph.I2 i2A;
        com.android.tools.r8.graph.E0 e0D;
        AbstractC0184c3 y5;
        com.android.tools.r8.graph.D2 d2X;
        C4 c4;
        com.android.tools.r8.graph.E0 e0D2;
        boolean z2 = J;
        if (!z2) {
            c();
        }
        com.android.tools.r8.graph.H0 h0Q = null;
        if (!z2 && abstractC0439Dm == null) {
            x1f.a();
            return null;
        }
        if (c0322w2.w0().I0()) {
            return null;
        }
        if (!c0333y.K.a(c0333y, new Supplier() { // from class: f6h
            @Override // java.util.function.Supplier
            public final Object get() {
                return C3403i.a(abstractC0439Dm);
            }
        }, c0322w2.w0().b((C0333y<?>) c0333y)) || cVar.b.isInterface() != z || (e0D = d((i2A = C2283oj0.a(abstractC0439Dm, c0322w2, c0333y)))) == null) {
            return null;
        }
        if (this.H.d(i2A, c0322w2)) {
            return this.H.b(i2A, c0322w2);
        }
        if ((!abstractC0439Dm.e() && this.H.c(i2A, c0322w2)) || cVar.b((InterfaceC0332x5) b5.a(), c0333y).a()) {
            return null;
        }
        com.android.tools.r8.graph.H0 h0A = com.android.tools.r8.graph.H0.a(cVar.c, cVar.d);
        if ((h0A.a().f.f() || h0A.getAccessFlags().f()) && (!(h0A instanceof C0310u4) || l1.e(h0A.s()))) {
            return this.H.a(i2A, c0322w2, h0A);
        }
        C2441qd c2441qdB = abstractC0439Dm.b();
        if (c2441qdB == null || c2441qdB.Q() != i2A) {
            y5 = null;
        } else if (e0D.a0()) {
            InterfaceC0331x4 interfaceC0331x4A = cVar.a(e0D.X(), this);
            if (interfaceC0331x4A != null) {
                com.android.tools.r8.graph.H0 h0Q2 = interfaceC0331x4A.q();
                h0Q2.getClass();
                if (h0Q2 instanceof B5) {
                    AbstractC3405i1 abstractC3405i1 = this.v;
                    B5 b5C0 = interfaceC0331x4A.q().c0();
                    abstractC3405i1.getClass();
                    if (!abstractC3405i1.a(b5C0.a(), b5C0.e()).c(j())) {
                        y5 = new Y5(cVar);
                    }
                }
                y5 = new N5(interfaceC0331x4A.q(), cVar);
            } else {
                y5 = new Y5(cVar);
            }
        } else {
            com.android.tools.r8.graph.H0 h0A2 = com.android.tools.r8.graph.H0.a(cVar.c, cVar.d);
            com.android.tools.r8.graph.H0 h0D = e0D.d(e0D.d(h0A2.getReference()));
            y5 = (h0D == null || !T4.c.a(h0A2.e(), h0D.e())) ? new Y5(cVar) : new N5(h0D, cVar);
        }
        if (y5 != null) {
            if (y5 instanceof N5) {
                return y5.a().b;
            }
            return null;
        }
        if (e0D.y1()) {
            this.H.a(i2A, c0322w2);
            return null;
        }
        com.android.tools.r8.graph.E0 e0D3 = cVar.d();
        if (e0D3.isInterface() && e0D3.a0() && this.t.b(e0D3.X())) {
            this.H.a(i2A, c0322w2);
            return null;
        }
        if (!abstractC0439Dm.e() || (e0D2 = d(abstractC0439Dm.b().Q())) == null || ((d2X = e0D2.X()) != null && !c(d2X.e, i2A))) {
            d2X = null;
        }
        B4.a aVarA = cVar.a(b5.a(), c0333y, e0D.X(), d2X).a();
        if (aVarA != null && !aVarA.g()) {
            if (aVarA.g()) {
                c4 = null;
            } else if (aVarA.b.size() + aVarA.a.size() > 1) {
                c4 = null;
            } else if (aVarA.a.size() == 1) {
                c4 = (C4) aVarA.a.values().iterator().next();
            } else if (aVarA.b.size() == 1) {
                c4 = (C4) aVarA.b.get(0);
            } else {
                c4 = null;
            }
            if (c4 != null && c4.h()) {
                h0Q = c4.f().q();
            }
        }
        if (!abstractC0439Dm.e()) {
            this.H.a(i2A, c0322w2, h0Q);
        }
        return h0Q;
    }

    public final C3403i b(C3492a c3492a) {
        C2098md c2098md = this.g;
        R1 r1F = f();
        X1 x1 = this.h;
        Set set = this.j;
        Set set2 = this.k;
        set2.addAll(c3492a.d);
        return new C3403i(c3492a, c2098md, r1F, x1, set, set2, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A, this.E, this.F, this.G, this.B, this.C, this.D);
    }

    @Override // com.android.tools.r8.graph.C0229j
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C3403i a(final I5 i5, ExecutorService executorService, Ch0 ch0) {
        boolean z = J;
        if (!z && getClass() != C3403i.class) {
            x1f.a();
            return null;
        }
        if (!z) {
            c();
        }
        if (i5.d()) {
            if (z || b() == i5.a) {
                return this;
            }
            x1f.a();
            return null;
        }
        ch0.a("Pruning AppInfoWithLiveness");
        if (i5.c()) {
            this.t.a(new Consumer() { // from class: g6h
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((C0256m5) obj).a(i5);
                }
            }, this);
            this.v.a(new Consumer() { // from class: z4h
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((C3400h1) obj).b(i5);
                }
            });
        } else if (!i5.f.isEmpty() || !i5.g.isEmpty()) {
            this.v.a(new Consumer() { // from class: c5h
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((C3400h1) obj).b(i5);
                }
            });
        }
        Ng0 ng0 = new Ng0(j(), executorService);
        com.android.tools.r8.synthesis.J jG = g();
        C3492a c3492aA = com.android.tools.r8.synthesis.J.a(i5, jG.d, jG.e, jG.c, jG.a, jG.f);
        C2098md c2098mdA = this.g.a(i5);
        R1 r1A = f().a(i5);
        X1 x1 = this.h;
        Set set = this.j;
        Set setA = a(this.k, i5.e, ng0);
        Set setA2 = a(this.l, i5.g, ng0);
        Set setA3 = a(this.m, i5.e, ng0);
        Set setA4 = a(this.n, i5.g, ng0);
        Set setA5 = a(this.o, i5.f, ng0);
        Set setA6 = a(this.p, i5.g, ng0);
        Set setA7 = a(this.q, i5.g, ng0);
        Set setA8 = a(this.r, i5.g, ng0);
        C0226i3 c0226i3 = this.s;
        Iterator it = c0226i3.a.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (i5.a((C0245l1) entry.getKey())) {
                it.remove();
            } else {
                C0233j3 c0233j3 = (C0233j3) entry.getValue();
                c0233j3.c = c0233j3.c.a(i5);
                c0233j3.d = c0233j3.d.a(i5);
                entry.setValue(c0233j3);
            }
        }
        AbstractC0263n5 abstractC0263n5B = this.t.b(i5);
        Map mapA = a(this.u, i5);
        AbstractC3405i1 abstractC3405i1A = a(this, i5.b);
        Map map = this.w;
        Set setA9 = a(this.x, i5.g, ng0);
        Set setA10 = a(this.y, i5.g, ng0);
        Set setA11 = a(this.z, i5.g, ng0);
        FX fx = this.A;
        ZS zsA = a(this.E, i5, ng0);
        boolean zC = i5.c();
        Set setA12 = this.F;
        if (zC) {
            Set set2 = i5.e;
            int i = AbstractC2554rv.c;
            C1870jv c1870jv = new C1870jv();
            c1870jv.a((Iterable) setA12);
            c1870jv.a((Iterable) set2);
            setA12 = c1870jv.a();
        }
        C3403i c3403i = new C3403i(c3492aA, c2098mdA, r1A, x1, set, setA, setA2, setA3, setA4, setA5, setA6, setA7, setA8, c0226i3, abstractC0263n5B, mapA, abstractC3405i1A, map, setA9, setA10, setA11, fx, zsA, setA12, this.G, a(this.B, i5.e, ng0), a(this.C, i5.e, ng0), this.D);
        ng0.a((Consumer) null);
        ch0.b();
        return c3403i;
    }

    public final boolean d(com.android.tools.r8.graph.F0 f0) {
        boolean z = J;
        if (!z) {
            c();
        }
        if (z || b(f0)) {
            C0231j1 c0231j1O0 = d(f0.s()).X().O0();
            return c0231j1O0 != null && a(f0, c0231j1O0);
        }
        hih.a("Expected field `", f0.v(), "` to be written");
        return false;
    }

    public final boolean g(C0322w2 c0322w2) {
        return this.r.contains(c0322w2);
    }

    public final boolean h(C0322w2 c0322w2) {
        return this.l.contains(c0322w2);
    }

    @Override // com.android.tools.r8.graph.C0215h
    public final boolean i() {
        if (J) {
            return true;
        }
        c();
        return true;
    }

    public final boolean j(com.android.tools.r8.graph.I2 i2) {
        com.android.tools.r8.graph.E0 e0D = d(i2);
        if (e0D == null || !e0D.a0()) {
            return false;
        }
        return this.k.contains(e0D.X().e);
    }

    public final boolean k(com.android.tools.r8.graph.I2 i2) {
        if (this.k.contains(i2)) {
            return true;
        }
        if (this.F.contains(i2)) {
            return false;
        }
        com.android.tools.r8.graph.E0 e0D = d(i2);
        return e0D == null || !e0D.a0();
    }

    public final boolean l(com.android.tools.r8.graph.I2 i2) {
        if (!J) {
            c();
        }
        return this.F.contains(i2);
    }

    @Override // com.android.tools.r8.graph.C0215h
    public final C3403i m() {
        if (!J) {
            c();
        }
        return this;
    }

    @Override // com.android.tools.r8.graph.C0229j
    public final void p() {
        this.r = C2620sh0.b;
    }

    public final Set q() {
        if (!J) {
            c();
        }
        return this.F;
    }

    public final void r() {
        boolean z = J;
        if (!z) {
            this.v.a(j(), this.k);
        }
        if (z) {
            return;
        }
        AbstractC0263n5 abstractC0263n5 = this.t;
        Set set = this.k;
        X1 x1 = this.h;
        for (com.android.tools.r8.graph.D2 d2 : abstractC0263n5.a.keySet()) {
            if (!AbstractC0263n5.g && !set.contains(d2.getType())) {
                x1f.a();
                return;
            }
        }
        for (com.android.tools.r8.graph.D2 d3 : abstractC0263n5.b) {
            if (!AbstractC0263n5.g && !set.contains(d3.getType())) {
                x1f.a();
                return;
            }
        }
        for (com.android.tools.r8.graph.D2 d4 : abstractC0263n5.c) {
            if (!AbstractC0263n5.g && !set.contains(d4.getType())) {
                x1f.a();
                return;
            }
        }
        for (com.android.tools.r8.graph.D2 d5 : abstractC0263n5.d) {
            if (!AbstractC0263n5.g && !set.contains(d5.getType())) {
                x1f.a();
                return;
            }
        }
        for (com.android.tools.r8.graph.I2 i2 : abstractC0263n5.e.keySet()) {
            if (!AbstractC0263n5.g && !x1.a.contains(i2) && !d(i2).y1() && !set.contains(i2)) {
                x1f.a();
                return;
            }
        }
    }

    public final void s() {
        d().forEach(new Consumer() { // from class: e5h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.c((D2) obj);
            }
        });
    }

    @Override // com.android.tools.r8.graph.C0215h, com.android.tools.r8.graph.InterfaceC0189d1
    public com.android.tools.r8.graph.E0 d(com.android.tools.r8.graph.I2 i2) {
        com.android.tools.r8.graph.E0 e0D = super.d(i2);
        if (J || e0D != null || this.j.contains(i2) || o().a(i2) || C0385Bk.a(i2)) {
            return e0D;
        }
        s22.a("Failed lookup of non-missing type: ", i2);
        return null;
    }

    public final boolean b(com.android.tools.r8.graph.D2 d2) {
        boolean z = J;
        if (!z) {
            c();
        }
        if (a(d2)) {
            return true;
        }
        if (!z) {
            c();
        }
        return this.t.a(d2);
    }

    public final boolean b(com.android.tools.r8.graph.F0 f0) {
        boolean z = J;
        if (!z) {
            c();
        }
        if (!z) {
            c();
        }
        C0233j3 c0233j3A = this.s.a(f0.getReference());
        return ((c0233j3A == null || !c0233j3A.g()) ? (f0 instanceof C0346z5) ^ true : true) || a((InterfaceC0265o0) f0);
    }

    public final boolean b(com.android.tools.r8.graph.F0 f0, C0231j1 c0231j1) {
        boolean z = J;
        if (!z) {
            c();
        }
        if (z || b(f0)) {
            C0233j3 c0233j3A = this.s.a(f0.getReference());
            return (c0233j3A == null || !c0233j3A.g() || c0233j3A.a(c0231j1)) ? false : true;
        }
        hih.a("Expected field `", f0.v(), "` to be written");
        return false;
    }

    public C3403i(C3492a c3492a, C2098md c2098md, R1 r1, X1 x1, Set set, Set set2, Set set3, Set set4, Set set5, Set set6, Set set7, Set set8, Set set9, C0226i3 c0226i3, AbstractC0263n5 abstractC0263n5, Map map, AbstractC3405i1 abstractC3405i1, Map map2, Set set10, Set set11, Set set12, FX fx, ZS zs, Set set13, Map map3, Set set14, Map map4, Set set15) {
        super(c3492a, c2098md, r1, x1);
        this.H = new m4();
        this.I = null;
        this.j = set;
        this.k = set2;
        this.l = set3;
        this.m = set4;
        this.n = set5;
        this.o = set6;
        this.p = set7;
        this.q = set8;
        this.r = set9;
        this.s = c0226i3;
        this.t = abstractC0263n5;
        this.v = abstractC3405i1;
        this.w = map2;
        this.u = map;
        this.x = set10;
        this.y = set11;
        this.z = set12;
        this.A = fx;
        this.E = zs;
        this.F = set13;
        this.G = map3;
        this.B = set14;
        this.C = map4;
        this.D = set15;
        if (J) {
            return;
        }
        r();
    }

    public static /* synthetic */ boolean b(com.android.tools.r8.graph.I2 i2, B5 b5) {
        return b5.s() == i2 && b5.e().q1();
    }

    public final boolean c(com.android.tools.r8.graph.F0 f0) {
        C0233j3 c0233j3A;
        boolean z = J;
        if (!z) {
            c();
        }
        if (!z && !b(f0)) {
            hih.a("Expected field `", f0.v(), "` to be written");
            return false;
        }
        if (a((InterfaceC0265o0) f0) || (c0233j3A = this.s.a(f0.getReference())) == null || !c0233j3A.g()) {
            return false;
        }
        final com.android.tools.r8.graph.I2 i2S = f0.s();
        return c0233j3A.a(new Predicate() { // from class: b6h
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C3403i.b(i2S, (B5) obj);
            }
        });
    }

    @Override // com.android.tools.r8.graph.C0229j
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C3403i a(R1 r1) {
        return new C3403i(g().a(b()), this.g, r1, this.h, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A, this.E, this.F, this.G, this.B, this.C, this.D);
    }

    public final /* synthetic */ void c(com.android.tools.r8.graph.D2 d2) {
        if (J || !l(d2.e)) {
            return;
        }
        s4h.a(d2.e, " was not pruned");
    }

    public static Set a(final Set set, final Set set2, Ng0 ng0) {
        if (set != C2620sh0.b && !set2.isEmpty()) {
            ng0.b(new InterfaceC1681hh0() { // from class: d6h
                @Override // com.android.tools.r8.internal.InterfaceC1681hh0
                public final void b() {
                    C3403i.a(set, set2);
                }
            });
        }
        return set;
    }

    public static ZS a(final ZS zs, final I5 i5, Ng0 ng0) {
        if (i5.f.isEmpty() && i5.g.isEmpty()) {
            return zs;
        }
        ng0.b(new InterfaceC1681hh0() { // from class: n5h
            @Override // com.android.tools.r8.internal.InterfaceC1681hh0
            public final void b() {
                C3403i.a(i5, zs);
            }
        });
        return zs;
    }

    public static void a(I5 i5, final ZS zs) {
        Set set = i5.f;
        Objects.requireNonNull(zs);
        set.forEach(new Consumer() { // from class: k5h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                zs.c((C0245l1) obj);
            }
        });
        i5.g.forEach(new Consumer() { // from class: m5h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                zs.c((C0322w2) obj);
            }
        });
    }

    public static Map a(Map map, final I5 i5) {
        map.entrySet().removeIf(new Predicate() { // from class: o5h
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C3403i.a(i5, (Map.Entry) obj);
            }
        });
        return map;
    }

    public static boolean a(I5 i5, Map.Entry entry) {
        UY uyA = ((UY) entry.getValue()).a(i5);
        if (uyA.b.isEmpty()) {
            return true;
        }
        entry.setValue(uyA);
        return false;
    }

    public static /* synthetic */ void a(final Set set, Set set2) {
        if (set.size() <= set2.size()) {
            set.removeAll(set2);
        } else {
            set2.forEach(new Consumer() { // from class: l5h
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    set.remove(obj);
                }
            });
        }
    }

    public static Map a(final Map map, final Set set, Ng0 ng0) {
        if (!set.isEmpty()) {
            ng0.b(new InterfaceC1681hh0() { // from class: j5h
                @Override // com.android.tools.r8.internal.InterfaceC1681hh0
                public final void b() {
                    C3403i.a(map, set);
                }
            });
        }
        return map;
    }

    public static /* synthetic */ void a(final Map map, Set set) {
        if (map.size() <= set.size()) {
            map.keySet().removeAll(set);
        } else {
            set.forEach(new Consumer() { // from class: i5h
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    map.remove(obj);
                }
            });
        }
    }

    public final void a(M.a aVar) {
        if (aVar.c()) {
            this.r = C2620sh0.b;
        }
        this.l = C2620sh0.b;
    }

    public static AbstractC3405i1 a(final C3403i c3403i, final Collection collection) {
        if (collection != null && !collection.isEmpty()) {
            return c3403i.v.a(new Consumer() { // from class: d5h
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    C3403i.a(collection, c3403i, (C3400h1) obj);
                }
            });
        }
        return c3403i.v;
    }

    public static void a(Collection collection, C3403i c3403i, C3400h1 c3400h1) {
        B5 b5F;
        C0346z5 c0346z5B;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            com.android.tools.r8.graph.F2 f2 = (com.android.tools.r8.graph.F2) it.next();
            f2.getClass();
            if (f2 instanceof com.android.tools.r8.graph.I2) {
                com.android.tools.r8.graph.D2 d2B = com.android.tools.r8.graph.D2.b(c3403i.d(f2.r0()));
                if (d2B != null) {
                    c3400h1.a(new Consumer() { // from class: f5h
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ((X0) obj).j();
                        }
                    }, d2B);
                }
            } else if (f2.u0()) {
                C0322w2 c0322w2Q0 = f2.q0();
                com.android.tools.r8.graph.D2 d2B2 = com.android.tools.r8.graph.D2.b(c3403i.d(c0322w2Q0.f));
                if (d2B2 != null && (b5F = d2B2.f(c0322w2Q0)) != null) {
                    c3400h1.a(new Consumer() { // from class: g5h
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ((C3430n1) obj).j();
                        }
                    }, b5F);
                }
            } else {
                C0245l1 c0245l1O0 = f2.o0();
                com.android.tools.r8.graph.D2 d2B3 = com.android.tools.r8.graph.D2.b(c3403i.d(c0245l1O0.f));
                if (d2B3 != null && (c0346z5B = d2B3.b(c0245l1O0)) != null) {
                    c3400h1.a(c0346z5B, new Consumer() { // from class: h5h
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ((C3375c1) obj).j();
                        }
                    });
                }
            }
        }
    }

    public final void a(com.android.tools.r8.graph.D2 d2, com.android.tools.r8.graph.D2 d3, Consumer consumer) {
        List<com.android.tools.r8.graph.D2> listSubList;
        boolean z = C0229j.i;
        if (!z && !c(d3.e, d2.e)) {
            x1f.a();
            return;
        }
        if (!z && d3.isInterface()) {
            x1f.a();
            return;
        }
        if (!d2.isInterface()) {
            listSubList = b(d3, d2.e);
        } else {
            ArrayList arrayListB = b(d3, a().a2);
            Sm0 sm0 = new Sm0(2);
            int size = arrayListB.size() - 1;
            loop1: while (true) {
                if (size >= 0) {
                    com.android.tools.r8.graph.D2 d4 = (com.android.tools.r8.graph.D2) arrayListB.get(size);
                    com.android.tools.r8.graph.I2 i2 = d2.e;
                    sm0.b(d4.C0());
                    while (sm0.b()) {
                        com.android.tools.r8.graph.I2 i3 = (com.android.tools.r8.graph.I2) sm0.d();
                        if (i3 == i2) {
                            listSubList = arrayListB.subList(0, size + 1);
                            break loop1;
                        } else {
                            com.android.tools.r8.graph.E0 e0D = d(i3);
                            if (e0D != null) {
                                sm0.b(e0D.C0());
                            }
                        }
                    }
                    size--;
                } else {
                    listSubList = Collections.EMPTY_LIST;
                    break;
                }
            }
        }
        for (com.android.tools.r8.graph.D2 d5 : listSubList) {
            if (!a(d5)) {
                if (!this.v.a((InterfaceC0332x5) d5).d(j())) {
                    if (!J) {
                        c();
                    }
                    if (this.t.e(d5)) {
                    }
                }
            }
            consumer.accept(d5);
        }
    }

    public final AbstractC0464El a(com.android.tools.r8.graph.D0 d0, C0333y c0333y) {
        com.android.tools.r8.graph.E0 e0D;
        if (!J) {
            c();
        }
        C1819jJ c1819jJA = C1819jJ.a(d0, c0333y, (C0229j) c0333y.g(), null);
        List list = c1819jJA == C1819jJ.j ? null : c1819jJA.e;
        if (list != null && !list.isEmpty()) {
            C0412Cl c0412Cl = AbstractC0464El.d;
            C0438Dl c0438Dl = new C0438Dl();
            ArrayDeque arrayDeque = new ArrayDeque(list);
            Set setC = AbstractC2780ub0.c();
            while (!arrayDeque.isEmpty()) {
                com.android.tools.r8.graph.I2 i2 = (com.android.tools.r8.graph.I2) arrayDeque.removeFirst();
                if (setC.add(i2) && (e0D = d(i2)) != null) {
                    if (!J && !e0D.isInterface()) {
                        x1f.a();
                        return null;
                    }
                    C2924wC c2924wCF1 = e0D.F1();
                    GC gcA = NC.a(c2924wCF1.b.iterator(), c2924wCF1.c);
                    while (gcA.b.hasNext()) {
                        com.android.tools.r8.graph.H0 h0 = (com.android.tools.r8.graph.H0) gcA.a(gcA.b.next());
                        if (h0.getReference().x0().b(d0.e) && h0.getAccessFlags().J()) {
                            c0438Dl.add(h0);
                        }
                    }
                    Collections.addAll(arrayDeque, e0D.h.b);
                }
            }
            return c0438Dl;
        }
        return AbstractC0464El.d;
    }

    public final void a(C0333y c0333y) {
        this.s.a(c0333y);
    }

    public final void a(com.android.tools.r8.graph.E0 e0) {
        this.H.a(e0.e, this);
    }

    public final boolean a(com.android.tools.r8.graph.D2 d2) {
        if (!J) {
            c();
        }
        com.android.tools.r8.graph.I2 i2 = d2.e;
        if (d2.isInterface() || !this.t.c(d2)) {
            return d2.f.J() && this.k.contains(i2);
        }
        return true;
    }

    public final boolean a(com.android.tools.r8.graph.F0 f0) {
        if (!J) {
            c();
        }
        C0233j3 c0233j3A = this.s.a(f0.getReference());
        if ((c0233j3A == null || !c0233j3A.f()) && !a((InterfaceC0265o0) f0)) {
            return !(f0 instanceof C0346z5);
        }
        return true;
    }

    public final boolean a(com.android.tools.r8.graph.F0 f0, C0231j1 c0231j1) {
        boolean z = J;
        if (!z) {
            c();
        }
        if (!z && !b(f0)) {
            hih.a("Expected field `", f0.v(), "` to be written");
            return false;
        }
        if (a((InterfaceC0265o0) f0)) {
            return false;
        }
        return b(f0, c0231j1);
    }

    public final boolean a(com.android.tools.r8.graph.D2 d2, C0333y c0333y) {
        Y0 y0A = this.v.a(d2);
        C2752uB c2752uBJ = j();
        y0A.getClass();
        if (!c2752uBJ.f0() || !y0A.l || AbstractC2229o50.a(d2, c0333y.M())) {
            return false;
        }
        com.android.tools.r8.naming.T0 t0 = c0333y.O;
        if (t0 != null) {
            return !t0.a.containsKey(d2.e.f.toString());
        }
        return true;
    }

    public final boolean a(com.android.tools.r8.graph.F2 f2) {
        if (!J) {
            c();
        }
        return this.v.a(f2, j(), this);
    }

    public final boolean a(C0210g1 c0210g1) {
        return this.v.a(c0210g1, j(), this);
    }

    public final boolean a(AbstractC0175b1 abstractC0175b1) {
        com.android.tools.r8.graph.E0 e0D;
        if (this.v.a(abstractC0175b1, j(), this)) {
            return true;
        }
        if (abstractC0175b1.y0()) {
            C0231j1 c0231j1R0 = abstractC0175b1.r0();
            return !c0231j1R0.getReference().f.M0() || (e0D = d(c0231j1R0.getReference().f)) == null || !e0D.a0() || c0231j1R0.r1().c();
        }
        boolean z = J;
        if (!z && !abstractC0175b1.v0()) {
            x1f.a();
            return false;
        }
        com.android.tools.r8.graph.E0 e0O0 = abstractC0175b1.o0();
        if (!e0O0.y1()) {
            com.android.tools.r8.graph.D2 d2X = e0O0.X();
            if (!z) {
                c();
            }
            if (!this.t.e(d2X)) {
                return false;
            }
        }
        return true;
    }

    public final boolean a(InterfaceC0265o0 interfaceC0265o0) {
        if (!J && interfaceC0265o0 == null) {
            x1f.a();
            return false;
        }
        if (interfaceC0265o0.j()) {
            AbstractC3405i1 abstractC3405i1 = this.v;
            InterfaceC0332x5 interfaceC0332x5I = interfaceC0265o0.i();
            if (abstractC3405i1.a(interfaceC0332x5I).d(j())) {
                return true;
            }
        }
        return false;
    }

    public final C3403i a(C0177b3 c0177b3, XR xr, AbstractC3148ys abstractC3148ys, Ch0 ch0) {
        boolean z = J;
        if (!z) {
            c();
        }
        if (!z) {
            Iterable iterable = (Iterable) this.G.keySet().stream().map(new hni(this)).filter(new Predicate() { // from class: r5h
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((AbstractC0330x3) obj).y();
                }
            }).map(new Function() { // from class: u5h
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((AbstractC0330x3) obj).q();
                }
            }).collect(Collectors.toList());
            xr.getClass();
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                C0245l1 reference = ((C0210g1) it.next()).getReference();
                if (!AbstractC3148ys.a && xr.d(AbstractC3148ys.g(), reference) != reference) {
                    x1f.a();
                    return null;
                }
            }
        }
        C3492a c3492aA = g().a(c0177b3, xr, ch0);
        AbstractC0327x0 abstractC0327x0 = c3492aA.a;
        abstractC0327x0.getClass();
        C0313v0 c0313v0 = new C0313v0(c3492aA, abstractC0327x0);
        C2098md c2098mdA = this.g.a(xr, ch0);
        R1 r1A = f().a(g(), xr, ch0);
        X1 x1 = this.h;
        Set set = this.j;
        Set setA = xr.a(this.k);
        Set setA2 = xr.a(this.l);
        Set setA3 = xr.a(this.m);
        Set setA4 = xr.a(this.n);
        Set setA5 = xr.a(this.o, ch0);
        Set setA6 = xr.a(this.p);
        Set setA7 = xr.a(this.q);
        Set setA8 = xr.a(this.r);
        C0226i3 c0226i3A = this.s.a(c0313v0, xr, ch0);
        AbstractC0263n5 abstractC0263n5A = this.t.a(c0313v0, xr, abstractC3148ys, ch0);
        IdentityHashMap identityHashMapA = xr.a(this.u, c0313v0, ch0);
        C3400h1 c3400h1A = this.v.a(xr, c0177b3.d, ch0);
        IdentityHashMap identityHashMapA2 = xr.a(this.w, new BiFunction() { // from class: x5h
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return C3403i.a((F2) obj, (List) obj2);
            }
        });
        Set setA9 = xr.a(this.x);
        Set setA10 = xr.a(this.y);
        Set setA11 = xr.a(this.z);
        FX fx = this.A;
        w0h w0hVar = new w0h(xr);
        fx.getClass();
        FX fx2 = new FX();
        Iterator it2 = fx.a.iterator();
        while (it2.hasNext()) {
            Iterator it3 = it2;
            fx2.a.add(w0hVar.apply(it3.next()));
            it2 = it3;
            identityHashMapA = identityHashMapA;
        }
        IdentityHashMap identityHashMap = identityHashMapA;
        fx2.b.addAll(fx.b);
        ZS zs = this.E;
        ZS zs2 = new ZS();
        zs.getClass();
        for (XS xs = new XS(new YS(zs)); xs.hasNext(); xs = xs) {
            com.android.tools.r8.internal.S0 s0 = (com.android.tools.r8.internal.S0) xs.next();
            zs2.a(xr.b((com.android.tools.r8.graph.F2) s0.b, (AbstractC3148ys) null), s0.c);
        }
        return new C3403i(c3492aA, c2098mdA, r1A, x1, set, setA, setA2, setA3, setA4, setA5, setA6, setA7, setA8, c0226i3A, abstractC0263n5A, identityHashMap, c3400h1A, identityHashMapA2, setA9, setA10, setA11, fx2, zs2, this.F, xr.a(this.G), xr.a(this.B), a(xr), xr.a(this.D));
    }

    public static /* synthetic */ C3461t3 a(com.android.tools.r8.graph.F2 f2, List list) {
        return (C3461t3) C2847vL.a(list);
    }

    public final IdentityHashMap a(AbstractC3148ys abstractC3148ys) {
        return abstractC3148ys.b(this.C, new BiFunction() { // from class: z5h
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return C3403i.a((EnumC2630sm0) obj, (EnumC2630sm0) obj2);
            }
        });
    }

    public static EnumC2630sm0 a(EnumC2630sm0 enumC2630sm0, EnumC2630sm0 enumC2630sm1) {
        boolean z = J;
        if (!z) {
            enumC2630sm0.getClass();
            if (enumC2630sm0 == EnumC2630sm0.d) {
                x1f.a();
                return null;
            }
        }
        if (!z) {
            enumC2630sm1.getClass();
            if (enumC2630sm1 == EnumC2630sm0.d) {
                x1f.a();
                return null;
            }
        }
        enumC2630sm0.getClass();
        EnumC2630sm0 enumC2630sm2 = EnumC2630sm0.b;
        if (enumC2630sm0 != enumC2630sm2) {
            enumC2630sm1.getClass();
            if (enumC2630sm1 != enumC2630sm2) {
                EnumC2630sm0 enumC2630sm3 = EnumC2630sm0.c;
                return (enumC2630sm0 == enumC2630sm3 || enumC2630sm1 == enumC2630sm3) ? enumC2630sm3 : EnumC2630sm0.e;
            }
        }
        return enumC2630sm2;
    }

    public com.android.tools.r8.graph.H0 a(C0333y<C3403i> c0333y, C0322w2 c0322w2, B5 b5, boolean z, L1 l1, AbstractC0439Dm abstractC0439Dm) {
        if (!J) {
            c();
        }
        T4.c<?> cVarO = ((C3403i) c0333y.g()).b(c0322w2, z).o();
        if (cVarO != null) {
            return a(c0333y, c0322w2, cVarO, b5, z, l1, abstractC0439Dm);
        }
        return null;
    }

    public static /* synthetic */ AbstractC0439Dm a(AbstractC0439Dm abstractC0439Dm) {
        return abstractC0439Dm;
    }

    public final void a(Consumer consumer) {
        b().b().l.forEach(consumer);
    }

    @Override // com.android.tools.r8.graph.Z3
    public final void a(com.android.tools.r8.graph.I2 i2, Consumer consumer, Consumer consumer2) {
        this.t.a(i2, consumer, consumer2, this);
    }
}
