package com.android.tools.r8.graph;

import com.android.tools.r8.ClassFileConsumer;
import com.android.tools.r8.FeatureSplit;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0215h;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC0706Nu;
import com.android.tools.r8.internal.AbstractC0837Sv;
import com.android.tools.r8.internal.AbstractC1543g4;
import com.android.tools.r8.internal.AbstractC1831jV;
import com.android.tools.r8.internal.AbstractC2004lX;
import com.android.tools.r8.internal.AbstractC2116mm;
import com.android.tools.r8.internal.AbstractC2173nV;
import com.android.tools.r8.internal.AbstractC2554rv;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.Ah0;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.C0509Ge;
import com.android.tools.r8.internal.C0548Hr;
import com.android.tools.r8.internal.C0587Je;
import com.android.tools.r8.internal.C0629Ku;
import com.android.tools.r8.internal.C0672Mm;
import com.android.tools.r8.internal.C0698Nm;
import com.android.tools.r8.internal.C0807Rr;
import com.android.tools.r8.internal.C0820Se;
import com.android.tools.r8.internal.C0833Sr;
import com.android.tools.r8.internal.C0889Uv;
import com.android.tools.r8.internal.C1055aK;
import com.android.tools.r8.internal.C1086ah0;
import com.android.tools.r8.internal.C1218cD;
import com.android.tools.r8.internal.C1285d3;
import com.android.tools.r8.internal.C1350dn;
import com.android.tools.r8.internal.C1527ft;
import com.android.tools.r8.internal.C1596gi;
import com.android.tools.r8.internal.C1605gm0;
import com.android.tools.r8.internal.C2039lt;
import com.android.tools.r8.internal.C2098md;
import com.android.tools.r8.internal.C2640sw;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C2791uh0;
import com.android.tools.r8.internal.C2819v10;
import com.android.tools.r8.internal.C2868vd;
import com.android.tools.r8.internal.C2974wn;
import com.android.tools.r8.internal.C3076y10;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.Dj0;
import com.android.tools.r8.internal.FN;
import com.android.tools.r8.internal.HN;
import com.android.tools.r8.internal.I10;
import com.android.tools.r8.internal.InterfaceC1681hh0;
import com.android.tools.r8.internal.InterfaceC1936kh0;
import com.android.tools.r8.internal.InterfaceC2706th0;
import com.android.tools.r8.internal.J50;
import com.android.tools.r8.internal.JN;
import com.android.tools.r8.internal.Lb0;
import com.android.tools.r8.internal.MX;
import com.android.tools.r8.internal.Ng0;
import com.android.tools.r8.internal.PN;
import com.android.tools.r8.internal.T40;
import com.android.tools.r8.internal.Vd0;
import com.android.tools.r8.internal.W40;
import com.android.tools.r8.internal.WJ;
import com.android.tools.r8.internal.XR;
import com.android.tools.r8.internal.Yg0;
import com.android.tools.r8.internal.Zg0;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.shaking.AbstractC3395g1;
import com.android.tools.r8.shaking.AbstractC3405i1;
import com.android.tools.r8.shaking.C3380d1;
import com.android.tools.r8.shaking.C3403i;
import com.android.tools.r8.shaking.C3413k;
import com.android.tools.r8.shaking.C3418l;
import com.android.tools.r8.shaking.C3435o1;
import com.android.tools.r8.utils.StringDiagnostic;
import defpackage.iti;
import defpackage.oti;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.graph.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0333y<T extends C0215h> implements InterfaceC0189d1, com.android.tools.r8.shaking.L1 {
    public static final /* synthetic */ boolean X = true;
    public final C1055aK A;
    public final C1285d3 B;
    public final WJ C;
    public final C3076y10 D;
    public C0889Uv G;
    public C1605gm0 I;
    public AbstractC1831jV K;
    public Set L;
    public final IdentityHashMap M;
    public final IdentityHashMap N;
    public com.android.tools.r8.naming.T0 O;
    public final I10 P;
    public Set Q;
    public final C0587Je R;
    public final Thread S;
    public final com.android.tools.r8.androidapi.a T;
    public final com.android.tools.r8.androidapi.f U;
    public boolean V;
    public final int W;
    public C0215h a;
    public C0229j b;
    public C0243l c;
    public AbstractC1543g4 d;
    public final AbstractC2116mm f;
    public AbstractC0837Sv j;
    public com.android.tools.r8.shaking.Q2 m;
    public com.android.tools.r8.shaking.e4 n;
    public Vd0 q;
    public final com.android.tools.r8.internal.C1 t;
    public final com.android.tools.r8.internal.D1 u;
    public final com.android.tools.r8.internal.E1 v;
    public final com.android.tools.r8.internal.G1 w;
    public final Dj0 z;
    public C3418l e = new C3413k().a();
    public AbstractC3148ys g = AbstractC3148ys.g();
    public AbstractC3148ys h = AbstractC3148ys.g();
    public AbstractC3148ys i = AbstractC3148ys.g();
    public AbstractC3148ys k = AbstractC3148ys.g();
    public AbstractC3345r0 l = AbstractC3345r0.a();
    public com.android.tools.r8.ir.optimize.info.s o = com.android.tools.r8.ir.optimize.info.s.b;
    public com.android.tools.r8.shaking.c4 p = null;
    public AbstractC3405i1 r = null;
    public C0820Se s = null;
    public final C2640sw x = new C2640sw();
    public final Lb0 y = new Lb0();
    public boolean E = false;
    public Predicate F = MX.b;
    public C2039lt H = new C2039lt(new C0698Nm());
    public C2974wn J = null;

    /* JADX WARN: Code duplicated, block: B:22:0x0125  */
    public C0333y(C0215h c0215h, AbstractC1543g4 abstractC1543g4, Vd0 vd0, int i, Dj0 dj0, final Ch0 ch0) {
        boolean z = false;
        boolean z2 = AbstractC1831jV.a;
        this.K = C1596gi.b;
        int i2 = AbstractC2554rv.c;
        this.L = W40.j;
        this.M = new IdentityHashMap();
        this.N = new IdentityHashMap();
        this.P = null;
        this.Q = null;
        this.S = Thread.currentThread();
        this.V = false;
        if (!X && c0215h == null) {
            x1f.a();
            throw null;
        }
        this.a = c0215h;
        this.R = (C0587Je) ch0.a("Compilation context", new InterfaceC2706th0() { // from class: yti
            @Override // com.android.tools.r8.internal.InterfaceC2706th0
            public final Object get() {
                return this.a.E();
            }
        });
        this.W = i;
        this.t = new com.android.tools.r8.internal.C1();
        this.u = new com.android.tools.r8.internal.D1(this);
        if (o()) {
            this.v = new com.android.tools.r8.internal.E1(U());
            this.w = new com.android.tools.r8.internal.G1(U());
        } else {
            this.v = null;
            this.w = null;
        }
        this.d = abstractC1543g4;
        this.q = vd0;
        this.f = (AbstractC2116mm) ch0.a("Dont warn config", new InterfaceC2706th0() { // from class: zti
            @Override // com.android.tools.r8.internal.InterfaceC2706th0
            public final Object get() {
                return this.a.F();
            }
        });
        this.j = (AbstractC0837Sv) ch0.a("Init class lens", new InterfaceC2706th0() { // from class: aui
            @Override // com.android.tools.r8.internal.InterfaceC2706th0
            public final Object get() {
                return AbstractC0837Sv.a();
            }
        });
        this.z = dj0;
        ch0.a("Create argument propagator");
        if (o()) {
            C2752uB.c cVarC = M().c();
            if (C2752uB.this.e0() && C2752uB.this.g0()) {
                z = cVarC.a;
            }
            if (z) {
                this.B = new C1285d3(V());
            } else {
                this.B = null;
            }
        } else {
            this.B = null;
        }
        if (o() && M().d0()) {
            this.P = J50.a(this);
        }
        ch0.b();
        this.A = (C1055aK) ch0.a("Library side-effects", new InterfaceC2706th0() { // from class: bui
            @Override // com.android.tools.r8.internal.InterfaceC2706th0
            public final Object get() {
                return this.a.G();
            }
        });
        this.C = (WJ) ch0.a("Library optimizer", new InterfaceC2706th0() { // from class: cui
            @Override // com.android.tools.r8.internal.InterfaceC2706th0
            public final Object get() {
                return this.a.a(ch0);
            }
        });
        this.D = (C3076y10) ch0.a("Proto shrinker", new InterfaceC2706th0() { // from class: lti
            @Override // com.android.tools.r8.internal.InterfaceC2706th0
            public final Object get() {
                return this.a.H();
            }
        });
        this.T = (com.android.tools.r8.androidapi.a) ch0.a("ApiLevel compute", new InterfaceC2706th0() { // from class: mti
            @Override // com.android.tools.r8.internal.InterfaceC2706th0
            public final Object get() {
                return this.a.I();
            }
        });
        this.U = (com.android.tools.r8.androidapi.f) ch0.a("ApiLevel computed", new InterfaceC2706th0() { // from class: nti
            @Override // com.android.tools.r8.internal.InterfaceC2706th0
            public final Object get() {
                return this.a.J();
            }
        });
    }

    public static void b(final ExecutorService executorService, final C0333y c0333y, final Ch0 ch0, final C0177b3 c0177b3, final XR xr, final AbstractC3148ys abstractC3148ys) throws Throwable {
        boolean z = X;
        if (!z && xr == null) {
            x1f.a();
            return;
        }
        if (!z && c0177b3 == null) {
            x1f.a();
            return;
        }
        ch0.a("Rewrite AppView");
        boolean zA = c0333y.a(xr);
        if (!z && !zA) {
            xr.getClass();
            if (!(xr instanceof C1527ft)) {
                x1f.a();
                return;
            }
        }
        if (!z) {
            C0177b3 c0177b3B = ((C0229j) c0333y.g()).b().b();
            c0177b3.getClass();
            if (!C0177b3.m) {
                Iterator<E> it = c0177b3B.k.iterator();
                while (it.hasNext()) {
                    I2 type = ((D2) it.next()).getType();
                    xr.getClass();
                    I2 i2E = xr.c(AbstractC3148ys.g(), type);
                    if (!i2E.P0() && i2E != type && (c0177b3.d(type) != null || c0177b3.d(i2E) == null)) {
                        if (!C0177b3.m && c0177b3.d(type).e != i2E && c0177b3.d(i2E) == null) {
                            x01.a("The lens and app is inconsistent");
                            return;
                        }
                    }
                }
            }
            if (!C0177b3.m) {
                c0177b3.l();
            }
        }
        XR xrD = xr;
        while (true) {
            AbstractC3148ys abstractC3148ys2 = xrD.d;
            if (abstractC3148ys2 == abstractC3148ys) {
                AbstractC3148ys abstractC3148ysA = a(c0333y, abstractC3148ys, xrD, ch0);
                InterfaceC1681hh0 interfaceC1681hh0 = new InterfaceC1681hh0() { // from class: sti
                    @Override // com.android.tools.r8.internal.InterfaceC1681hh0
                    public final void b() {
                        C0333y.a(executorService, c0333y, ch0, c0177b3, xr, abstractC3148ys);
                    }
                };
                AbstractC3148ys abstractC3148ys3 = xrD.d;
                xrD.d = abstractC3148ysA;
                interfaceC1681hh0.b();
                xrD.d = abstractC3148ys3;
                ch0.b();
                return;
            }
            boolean z2 = X;
            if (!z2 && !abstractC3148ys2.n()) {
                x1f.a();
                return;
            } else {
                if (!z2 && abstractC3148ys2 == c0333y.g) {
                    x1f.a();
                    return;
                }
                xrD = abstractC3148ys2.d();
            }
        }
    }

    public AbstractC3148ys A() {
        return this.h;
    }

    public final boolean B() {
        return !this.L.isEmpty();
    }

    public final boolean C() {
        return g().i();
    }

    public final boolean D() {
        return this.J != null;
    }

    public final C0587Je E() {
        return new C0587Je(M());
    }

    public final /* synthetic */ AbstractC2116mm F() {
        return AbstractC2116mm.a(M().H());
    }

    public final /* synthetic */ C1055aK G() {
        return new C1055aK(this);
    }

    public final C3076y10 H() {
        C0333y<C3403i> c0333yV = V();
        if (!c0333yV.o() || !c0333yV.M().m0().b()) {
            return null;
        }
        C2819v10 c2819v10 = new C2819v10(c0333yV.a());
        if (c0333yV.d(c2819v10.i) != null) {
            return new C3076y10(c0333yV, c2819v10);
        }
        c0333yV.M().i.warning(new StringDiagnostic("Ignoring -shrinkunusedprotofields since the protobuf-lite runtime is missing"));
        C2752uB.o oVarM0 = c0333yV.M().m0();
        oVarM0.a = false;
        oVarM0.b = false;
        oVarM0.c = false;
        oVarM0.d = false;
        oVarM0.e = false;
        return null;
    }

    public final /* synthetic */ com.android.tools.r8.androidapi.a I() {
        return com.android.tools.r8.androidapi.a.a((C0333y<?>) this);
    }

    public final /* synthetic */ com.android.tools.r8.androidapi.f J() {
        return this.T.a(M());
    }

    public final WJ K() {
        return this.C;
    }

    public final void L() {
        if (M().H().s()) {
            this.O = com.android.tools.r8.naming.T0.a(M().i, M().H().d());
        }
    }

    public C2752uB M() {
        return this.a.j();
    }

    public final C3076y10 N() {
        return this.D;
    }

    public final C2742u50 O() {
        return M().i;
    }

    public com.android.tools.r8.shaking.e4 P() {
        return this.n;
    }

    public C2752uB.q Q() {
        return M().u1;
    }

    public final C2974wn R() {
        if (D()) {
            return this.J;
        }
        T40 t40 = T40.i;
        return new C2974wn(t40, t40);
    }

    public final void S() {
        for (I2 i2 : this.J.a()) {
            boolean z = X;
            if (!z && this.a.c(i2) != null) {
                iti.a("Enum ", i2, " has been unboxed but is still in the program.");
                return;
            } else if (!z && !g().m().l(i2)) {
                iti.a("Enum ", i2, " has been unboxed but was not pruned.");
                return;
            }
        }
    }

    public final void T() {
        f().b().e().forEach(new Consumer() { // from class: rti
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((D2) obj).n(new Consumer() { // from class: pti
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj2) {
                        C0333y.b((B5) obj2);
                    }
                });
            }
        });
    }

    public final C0333y U() {
        if (this.a.h()) {
            return this;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public C0333y<C3403i> V() {
        return this;
    }

    public final C0333y W() {
        if (X || !g().h()) {
            return this;
        }
        x1f.a();
        return null;
    }

    public final void a(I5 i5, ExecutorService executorService, Ch0 ch0) {
        if (i5.d()) {
            if (X || g().b() == i5.a) {
                return;
            }
            x1f.a();
            return;
        }
        ch0.a("Prune AppView");
        if (this.a.i()) {
            C0333y<C3403i> c0333yV = V();
            c0333yV.c(((C3403i) c0333yV.g()).a(i5, executorService, ch0));
        } else if (this.a.h()) {
            C0333y c0333yU = U();
            c0333yU.c(((C0229j) c0333yU.g()).a(i5, executorService, ch0));
        } else {
            c(g().a(i5, executorService, ch0));
        }
        C0243l c0243l = this.c;
        if (c0243l != null) {
            ch0.a("Prune AppServices");
            C0629Ku c0629KuE = AbstractC0706Nu.e();
            for (Map.Entry entry : c0243l.c.entrySet()) {
                if (!i5.e.contains(entry.getKey())) {
                    C0629Ku c0629KuE2 = AbstractC0706Nu.e();
                    for (Map.Entry entry2 : ((Map) entry.getValue()).entrySet()) {
                        C0473Eu c0473EuG = AbstractC0551Hu.g();
                        for (I2 i2 : (List) entry2.getValue()) {
                            if (!i5.e.contains(i2)) {
                                c0473EuG.a(i2);
                            }
                        }
                        if (c0473EuG.a().size() > 0) {
                            c0629KuE2.a((FeatureSplit) entry2.getKey(), c0473EuG.a());
                        }
                    }
                    AbstractC0706Nu abstractC0706NuB = c0629KuE2.b();
                    if (abstractC0706NuB.size() > 0) {
                        c0629KuE.a((I2) entry.getKey(), abstractC0706NuB);
                    }
                }
            }
            C0243l c0243l2 = new C0243l(c0243l.a, c0629KuE.b());
            ch0.b();
            a(c0243l2);
        }
        this.d = this.d.a(i5, ch0);
        this.e = this.e.a(i5, ch0);
        com.android.tools.r8.shaking.Q2 q2 = this.m;
        if (q2 != null) {
            ch0.a("Prune ProguardCompatibilityActions");
            Set setC = AbstractC2780ub0.c();
            for (I2 i3 : q2.a) {
                if (!i5.e.contains(i3)) {
                    setC.add(i3);
                }
            }
            com.android.tools.r8.shaking.Q2 q3 = new com.android.tools.r8.shaking.Q2(setC);
            ch0.b();
            if (!X && !M().V0) {
                x1f.a();
                return;
            }
            this.m = q3;
        }
        com.android.tools.r8.shaking.e4 e4Var = this.n;
        if (e4Var != null) {
            ch0.a("Prune RootSet");
            com.android.tools.r8.shaking.V1 v1B = e4Var.a.b(null);
            if (v1B != null) {
                v1B.a(i5);
                if (v1B.a.isEmpty()) {
                    com.android.tools.r8.shaking.C c = e4Var.a;
                }
            }
            ch0.b();
        }
        Vd0 vd0 = this.q;
        this.a.g();
        this.q = vd0.a(i5, ch0);
        com.android.tools.r8.shaking.c4 c4Var = this.p;
        if (c4Var != null) {
            this.p = c4Var.a(i5, ch0);
        }
        a(this.K.a(i5, ch0));
        ch0.b();
    }

    /* JADX WARN: Incorrect types in method signature: <U:TT;>(TU;)Lcom/android/tools/r8/graph/y<TU;>; */
    public C0333y c(C0215h c0215h) {
        if (!X && c0215h.e.a()) {
            x1f.a();
            return null;
        }
        C0215h c0215h2 = this.a;
        this.a = c0215h;
        this.b = null;
        if (c0215h != c0215h2) {
            c0215h2.e.f();
        }
        if (c0215h.i()) {
            this.r = c0215h.m().v;
        }
        return this;
    }

    public final PN d() {
        PN pn = new PN();
        if (!this.H.a.isEmpty()) {
            pn.a.add(this.H);
        }
        C1605gm0 c1605gm0 = this.I;
        if (c1605gm0 != null) {
            pn.a.add(c1605gm0);
        }
        return pn;
    }

    @Override // com.android.tools.r8.shaking.L1
    public final boolean e(I2 i2) {
        return this.C.c.contains(i2);
    }

    public AbstractC0327x0 f() {
        return g().b();
    }

    public T g() {
        if (X || !this.a.h() || o()) {
            return (T) this.a;
        }
        x1f.a();
        return null;
    }

    public C0229j h() {
        if (o()) {
            if (X || this.a.h()) {
                return this.a.l();
            }
            x1f.a();
            return null;
        }
        if (!X && this.a.h()) {
            x1f.a();
            return null;
        }
        if (this.b == null) {
            C0215h c0215hG = g();
            if (!C0229j.i && c0215hG.h()) {
                x1f.a();
                return null;
            }
            this.b = new C0229j(c0215hG);
        }
        return this.b;
    }

    public final C0243l i() {
        return this.c;
    }

    public final boolean j() {
        return !this.V && M().g0();
    }

    public final void k() {
        this.O = null;
    }

    public final AbstractC3148ys l() {
        return this.g;
    }

    public final C0509Ge m() {
        boolean z = X;
        if (!z && !z && this.S != Thread.currentThread()) {
            x1f.a();
            return null;
        }
        C0587Je c0587Je = this.R;
        int i = c0587Je.d;
        c0587Je.d = i + 1;
        C0509Ge c0509Ge = new C0509Ge(c0587Je, i);
        boolean z2 = C0587Je.e;
        if (!z2) {
            c0587Je.a(c0509Ge);
        }
        if (z2 || c0587Je.b == Thread.currentThread()) {
            return c0509Ge;
        }
        x01.a("Invoked on another thread than main");
        return null;
    }

    public final void n() {
        this.V = true;
    }

    public boolean o() {
        return this.W == 1;
    }

    public final void p() {
        AbstractC3148ys abstractC3148ysA = A();
        a(AbstractC3148ys.g());
        a(new com.android.tools.r8.internal.Y2(U(), abstractC3148ysA));
    }

    public final AbstractC1543g4 q() {
        return this.d;
    }

    public final C0820Se r() {
        if (!X) {
            C1218cD c1218cDD = M().D();
            if ((!c1218cDD.a() || !c1218cDD.b) && !c1218cDD.a()) {
                x1f.a();
                return null;
            }
        }
        if (this.s == null) {
            this.s = new C0820Se(a());
        }
        return this.s;
    }

    public final AbstractC2116mm s() {
        return this.f;
    }

    public final AbstractC3405i1 t() {
        if (X || this.r != null) {
            return this.r;
        }
        x1f.a();
        return null;
    }

    public final AbstractC3148ys u() {
        return this.k;
    }

    public final C1055aK v() {
        return this.A;
    }

    public AbstractC3345r0 w() {
        return this.l;
    }

    public final Vd0 x() {
        return this.q;
    }

    public final com.android.tools.r8.synthesis.J y() {
        return this.a.g();
    }

    public final C1605gm0 z() {
        return this.I;
    }

    public final com.android.tools.r8.androidapi.a e() {
        return this.T;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0189d1
    public final InterfaceC0174b0 g(I2 i2) {
        return g().g(i2);
    }

    public final void d(InterfaceC1936kh0 interfaceC1936kh0) throws Throwable {
        C0833Sr c0833Sr;
        C3076y10 c3076y10 = this.D;
        if (c3076y10 == null || (c0833Sr = c3076y10.d) == null) {
            return;
        }
        interfaceC1936kh0.accept(c0833Sr);
    }

    @Override // com.android.tools.r8.graph.InterfaceC0189d1
    public final E0 d(I2 i2) {
        return g().d(i2);
    }

    public final com.android.tools.r8.internal.C1 c() {
        return this.t;
    }

    public final void c(InterfaceC1936kh0 interfaceC1936kh0) throws Throwable {
        C0807Rr c0807Rr;
        C3076y10 c3076y10 = this.D;
        if (c3076y10 == null || (c0807Rr = c3076y10.e) == null) {
            return;
        }
        interfaceC1936kh0.accept(c0807Rr);
    }

    public static boolean c(B5 b5) {
        AbstractC0223i0 abstractC0223i0U0 = b5.e().U0();
        if (abstractC0223i0U0 == null) {
            return true;
        }
        final C0322w2 c0322w2U = b5.getReference();
        abstractC0223i0U0.a(b5.getReference(), b5.e().I0(), new Consumer() { // from class: qti
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C0333y.a(c0322w2U, (AbstractC2004lX) obj);
            }
        });
        return true;
    }

    public final boolean h(I2 i2) {
        if (X || i2.M0()) {
            return this.F.test(i2);
        }
        x1f.a();
        return false;
    }

    public final void b(InterfaceC1936kh0 interfaceC1936kh0) throws Throwable {
        C0548Hr c0548Hr;
        C3076y10 c3076y10 = this.D;
        if (c3076y10 == null || (c0548Hr = c3076y10.c) == null) {
            return;
        }
        interfaceC1936kh0.accept(c0548Hr);
    }

    public final Object b(Object obj, Function function) {
        C0833Sr c0833Sr;
        C3076y10 c3076y10 = this.D;
        return (c3076y10 == null || (c0833Sr = c3076y10.d) == null) ? obj : function.apply(c0833Sr);
    }

    public final Object b(Function function) {
        Boolean bool = Boolean.FALSE;
        C0889Uv c0889Uv = this.G;
        return c0889Uv != null ? function.apply(c0889Uv) : bool;
    }

    public static C0333y b(C0215h c0215h) {
        return new C0333y(c0215h, C0672Mm.c, new C1350dn(), 2, c0215h.j().O(), Ch0.a());
    }

    public final boolean b(D2 d2) {
        if (!M().r().b) {
            return false;
        }
        if (X || this.Q != null) {
            return this.Q.contains(d2.getType());
        }
        x1f.a();
        return false;
    }

    public static /* synthetic */ void b(B5 b5) {
        if (X || c(b5)) {
            return;
        }
        x1f.a();
    }

    public static <T extends C0215h> C0333y<T> a(T t) {
        return new C0333y<>(t, AbstractC1543g4.a(t, t.j()), new C1350dn(), 2, t.j().O(), Ch0.a());
    }

    public static C0333y<C0229j> a(AbstractC0327x0 abstractC0327x0) {
        return a(abstractC0327x0, com.android.tools.r8.shaking.R1.b());
    }

    public static C0333y a(AbstractC0327x0 abstractC0327x0, com.android.tools.r8.shaking.R1 r1) {
        C2752uB c2752uB = abstractC0327x0.d;
        boolean z = C2098md.c;
        C0229j c0229jA = C0229j.a(abstractC0327x0, C2098md.a(c2752uB.s(), c2752uB.o, c2752uB.i), r1, com.android.tools.r8.synthesis.E.e());
        return new C0333y(c0229jA, AbstractC1543g4.a(c0229jA, c0229jA.j()), Vd0.a(abstractC0327x0), 1, c0229jA.j().O(), Ch0.a());
    }

    public final void a(ExecutorService executorService, Ch0 ch0) {
        ch0.a("Clear code rewritings");
        a(new C2868vd(U()));
        a(HN.a(U(), executorService));
        ch0.b();
    }

    public void a(C0243l c0243l) {
        this.c = c0243l;
    }

    public final void a(AbstractC1543g4 abstractC1543g4) {
        this.d = abstractC1543g4;
    }

    public final void a(Vd0 vd0) {
        this.q = vd0;
    }

    public final void a(C3418l c3418l) {
        this.e = c3418l;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0189d1
    public B1 a() {
        return this.a.a();
    }

    public final void a(InterfaceC1936kh0 interfaceC1936kh0) throws Throwable {
        C1285d3 c1285d3 = this.B;
        if (c1285d3 != null) {
            interfaceC1936kh0.accept(c1285d3);
        }
    }

    public final Object a(Object obj, Function function) {
        C0548Hr c0548Hr;
        C3076y10 c3076y10 = this.D;
        return (c3076y10 == null || (c0548Hr = c3076y10.c) == null) ? obj : function.apply(c0548Hr);
    }

    public final Object a(Function function) {
        C0807Rr c0807Rr;
        Boolean bool = Boolean.FALSE;
        C3076y10 c3076y10 = this.D;
        return (c3076y10 == null || (c0807Rr = c3076y10.e) == null) ? bool : function.apply(c0807Rr);
    }

    public final boolean a(AbstractC3148ys abstractC3148ys) {
        if (abstractC3148ys == this.h) {
            return false;
        }
        this.h = abstractC3148ys;
        abstractC3148ys.getClass();
        if (!(abstractC3148ys instanceof com.android.tools.r8.internal.Y2) && !(abstractC3148ys instanceof C2868vd)) {
            return true;
        }
        this.g = abstractC3148ys;
        return true;
    }

    public void a(com.android.tools.r8.shaking.e4 e4Var) {
        this.n = e4Var;
    }

    public final void a(com.android.tools.r8.shaking.c4 c4Var) {
        this.p = c4Var;
    }

    public final com.android.tools.r8.shaking.Y0 a(D2 d2) {
        return this.r != null ? t().a(d2) : com.android.tools.r8.shaking.Y0.p;
    }

    public final AbstractC3395g1 a(final InterfaceC0332x5 interfaceC0332x5) {
        return (AbstractC3395g1) interfaceC0332x5.getReference().a(new Function() { // from class: vti
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(interfaceC0332x5, (I2) obj);
            }
        }, new Function() { // from class: wti
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(interfaceC0332x5, (C0245l1) obj);
            }
        }, new Function() { // from class: xti
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(interfaceC0332x5, (C0322w2) obj);
            }
        });
    }

    public final AbstractC3395g1 a(InterfaceC0332x5 interfaceC0332x5, I2 i2) {
        return t().a(interfaceC0332x5.X());
    }

    public final /* synthetic */ AbstractC3395g1 a(InterfaceC0332x5 interfaceC0332x5, C0245l1 c0245l1) {
        return a(interfaceC0332x5.O());
    }

    public final /* synthetic */ AbstractC3395g1 a(InterfaceC0332x5 interfaceC0332x5, C0322w2 c0322w2) {
        return a(interfaceC0332x5.c0());
    }

    public final C3380d1 a(C0346z5 c0346z5) {
        AbstractC3405i1 abstractC3405i1T = t();
        abstractC3405i1T.getClass();
        return abstractC3405i1T.a(c0346z5.a(), c0346z5.e());
    }

    public final C3435o1 a(B5 b5) {
        AbstractC3405i1 abstractC3405i1T = t();
        abstractC3405i1T.getClass();
        return abstractC3405i1T.a(b5.a(), b5.e());
    }

    public final void a(AbstractC3345r0 abstractC3345r0) {
        this.l = abstractC3345r0;
    }

    public final void a(C2039lt c2039lt) {
        if (!X && !this.H.a.isEmpty()) {
            x1f.a();
        } else {
            this.H = c2039lt;
            Q().B.accept(this, c2039lt);
        }
    }

    public final void a(C1605gm0 c1605gm0, int i) {
        if (i == 1) {
            if (!X && this.I != null) {
                x1f.a();
                return;
            } else {
                this.I = c1605gm0;
                Q().H.accept(a(), c1605gm0);
                return;
            }
        }
        if (X || this.I != null) {
            return;
        }
        x1f.a();
    }

    public void a(AbstractC1831jV abstractC1831jV) {
        this.K = abstractC1831jV;
    }

    public final void a(C2974wn c2974wn) {
        if (!X && D()) {
            x1f.a();
        } else {
            this.J = c2974wn;
            Q().G.accept(a(), c2974wn);
        }
    }

    public final AbstractC2173nV a(I2 i2, I2 i3) {
        if (g().h()) {
            return AbstractC2173nV.a(g().l().c(i2, i3));
        }
        if (i2 != i3 && i3 != a().a2) {
            return AbstractC2173nV.c;
        }
        return AbstractC2173nV.a;
    }

    public final boolean a(C0231j1 c0231j1) {
        if (!M().k0) {
            return false;
        }
        if (!X && !(M().j instanceof ClassFileConsumer)) {
            x1f.a();
            return false;
        }
        if (this.L.contains(c0231j1.getReference())) {
            return true;
        }
        return M().u1.X0 != null && M().u1.X0.test(c0231j1.getReference());
    }

    public final /* synthetic */ WJ a(Ch0 ch0) {
        return new WJ(this, ch0);
    }

    public final void a(XR xr, C0177b3 c0177b3, ExecutorService executorService, Ch0 ch0) throws Throwable {
        b(executorService, U(), ch0, c0177b3, xr, xr.d);
        if (X) {
            return;
        }
        T();
    }

    public static void a(ExecutorService executorService, C0333y c0333y, Ch0 ch0, C0177b3 c0177b3, XR xr, AbstractC3148ys abstractC3148ys) {
        AbstractC3148ys abstractC3148ysG = AbstractC3148ys.g();
        C2752uB c2752uBM = c0333y.M();
        ch0.getClass();
        Ah0 ah0A = ch0.a(C1086ah0.a(executorService), "Rewrite AppView concurrently");
        ah0A.d = null;
        Yg0[] yg0Arr = {new C0271p(c0333y, c0177b3, xr, abstractC3148ysG), new C0278q(c0333y, xr), new r(c0333y, xr), new C0291s(c0333y, xr, abstractC3148ysG), new C0298t(c0333y, xr), new C0305u(c0333y, xr), new C0312v(c0333y, xr), new C0319w(c0333y, xr), new C0326x(c0333y, xr), new C0250m(c0333y, xr), new C0257n(c0333y, xr, abstractC3148ysG), new C0264o(c0333y, xr, abstractC3148ys)};
        Ng0 ng0 = new Ng0(c2752uBM.N(), executorService, 12);
        if (ah0A instanceof C2791uh0) {
            for (int i = 0; i < 12; i++) {
                Yg0 yg0 = yg0Arr[i];
                if (yg0.b()) {
                    Zg0.a(yg0, ng0);
                }
            }
            ng0.a((Consumer) null);
        } else {
            Ch0[] ch0Arr = new Ch0[12];
            Ch0 ch0A = Ch0.a();
            boolean z = com.android.tools.r8.internal.R3.a;
            Arrays.fill(ch0Arr, ch0A);
            List listAsList = Arrays.asList(ch0Arr);
            int i2 = 0;
            for (int i3 = 0; i3 < 12; i3++) {
                Yg0 yg1 = yg0Arr[i3];
                if (yg1.b()) {
                    Zg0.a(c2752uBM, yg1, i2, ng0, listAsList);
                    i2++;
                }
            }
            ng0.a((Consumer) null);
            ah0A.a(listAsList);
            ah0A.a();
        }
        for (int i4 = 0; i4 < 12; i4++) {
            Yg0 yg2 = yg0Arr[i4];
            if (yg2.b()) {
                yg2.a();
            }
        }
    }

    public static AbstractC3148ys a(C0333y c0333y, AbstractC3148ys abstractC3148ys, XR xr, Ch0 ch0) {
        ch0.a("Compute new member rebinding lens");
        AbstractC3148ys abstractC3148ysG = AbstractC3148ys.g();
        if (!(xr instanceof JN) && !(xr instanceof FN)) {
            oti otiVar = new oti();
            AbstractC3148ys abstractC3148ys2 = xr.d;
            XR xrA = abstractC3148ys2.n() ? abstractC3148ys2.d().a(otiVar) : null;
            if (xrA != null) {
                FN fnC = xrA.c();
                fnC.getClass();
                abstractC3148ysG = fnC.a(c0333y, abstractC3148ys, xrA, AbstractC3148ys.g());
            }
        }
        ch0.b();
        return abstractC3148ysG;
    }

    public final boolean a(Supplier supplier) {
        if (Q().E0) {
            return ((Boolean) supplier.get()).booleanValue();
        }
        return true;
    }

    public static void a(C0322w2 c0322w2, AbstractC2004lX abstractC2004lX) {
        C0322w2 c0322w3 = abstractC2004lX.h().c;
        if (X || c0322w2.a(c0322w3)) {
            return;
        }
        x1f.a();
    }
}
