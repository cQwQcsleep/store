package com.android.tools.r8.graph;

import com.android.tools.r8.dex.code.InterfaceC0022c;
import com.android.tools.r8.graph.G;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AS;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC0837Sv;
import com.android.tools.r8.internal.AbstractC2004lX;
import com.android.tools.r8.internal.AbstractC2166nO;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.AbstractC3175z9;
import com.android.tools.r8.internal.BU;
import com.android.tools.r8.internal.C0401Ca;
import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.internal.C0705Nt;
import com.android.tools.r8.internal.C0972Ya;
import com.android.tools.r8.internal.C1022Zy;
import com.android.tools.r8.internal.C1041a8;
import com.android.tools.r8.internal.C1159bb;
import com.android.tools.r8.internal.C1211c8;
import com.android.tools.r8.internal.C1345dk0;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.internal.C1752ia;
import com.android.tools.r8.internal.C2065m9;
import com.android.tools.r8.internal.C2150n9;
import com.android.tools.r8.internal.C2520ra;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C3034xa;
import com.android.tools.r8.internal.C3047xh;
import com.android.tools.r8.internal.InterfaceC1959kz;
import com.android.tools.r8.internal.InterfaceC2045lz;
import com.android.tools.r8.internal.K9;
import com.android.tools.r8.internal.Kk0;
import com.android.tools.r8.internal.NB;
import com.android.tools.r8.internal.RJ;
import com.android.tools.r8.internal.S9;
import com.android.tools.r8.internal.WI;
import com.android.tools.r8.internal.YO;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.shaking.C3403i;
import com.android.tools.r8.utils.structural.A;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class G extends AbstractC0223i0 implements P, com.android.tools.r8.utils.structural.x<G> {
    public static final /* synthetic */ boolean n = true;
    public final I2 e;
    public int f;
    public int g;
    public List h;
    public final List i;
    public final List j;
    public int k;
    public final Position l;
    public final C1211c8 m;

    public G(I2 i2, int i, int i3, List list, List list2, List list3, Position position, C1211c8 c1211c8) {
        this.k = 1;
        this.e = i2;
        this.g = i;
        this.f = i3;
        this.h = list;
        this.i = list2;
        this.j = list3;
        this.l = position;
        this.m = c1211c8;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final boolean A0() {
        for (AbstractC3175z9 abstractC3175z9 : this.h) {
            if (!(abstractC3175z9 instanceof C3034xa) && !(abstractC3175z9 instanceof K9) && !(abstractC3175z9 instanceof C1752ia)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0, com.android.tools.r8.graph.P
    public final G H() {
        return this;
    }

    public List<AbstractC3175z9> H0() {
        return Collections.unmodifiableList(this.h);
    }

    public List<a> I0() {
        return Collections.unmodifiableList(this.j);
    }

    public int J0() {
        return this.f;
    }

    public final int K0() {
        return this.g;
    }

    @Override // com.android.tools.r8.graph.P
    public final int L() {
        return 1;
    }

    public final AbstractC2004lX L0() {
        for (AbstractC3175z9 abstractC3175z9 : this.h) {
            abstractC3175z9.getClass();
            if (!(abstractC3175z9 instanceof K9)) {
                if (!(abstractC3175z9 instanceof C1752ia)) {
                    return null;
                }
                AbstractC2004lX abstractC2004lXT = abstractC3175z9.r().T();
                if (abstractC2004lXT.f() == 0) {
                    return abstractC2004lXT;
                }
                return null;
            }
        }
        return null;
    }

    public final int M0() {
        if (n || this.k != 1) {
            return this.k;
        }
        x1f.a();
        return 0;
    }

    public List<C0972Ya> N0() {
        return this.i;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0093  */
    /* JADX WARN: Code duplicated, block: B:54:0x00cf  */
    @Override // com.android.tools.r8.graph.P
    public final void a(B5 b5, C1159bb c1159bb, C0333y c0333y, AbstractC3345r0 abstractC3345r0, RJ rj, YO yo) {
        K9 k9;
        boolean z;
        int iA;
        C0333y c0333y2 = c0333y;
        YO yo2 = yo;
        AbstractC3148ys abstractC3148ysA = c0333y2.A();
        AbstractC3148ys abstractC3148ysA2 = a(c0333y2);
        boolean z2 = n;
        if (!z2 && (iA = a(c0333y2, b5)) != 4 && iA != 2) {
            x01.a("Could not validate stack map frames");
            return;
        }
        B1 b1A = c0333y2.a();
        AbstractC0837Sv abstractC0837Sv = c0333y2.j;
        C2752uB c2752uBM = c0333y2.M();
        C0231j1 c0231j1E = b5.e();
        if (c0333y2.M().U() || c0333y2.M().Z0 || c0333y2.a(c0231j1E)) {
            k9 = null;
        } else {
            if (!z2 && !this.j.isEmpty()) {
                x1f.a();
                return;
            }
            if (c0231j1E.s == C0231j1.w) {
                k9 = null;
            } else {
                if (c0333y2.g().i()) {
                    C3403i c3403iM = c0333y2.g().m();
                    if (!c3403iM.v.a(c0231j1E, c3403iM.j(), c3403iM)) {
                        k9 = null;
                    }
                }
                K9 k10 = new K9();
                yo2.a(k10.T());
                k9 = k10;
            }
        }
        C1159bb c1159bb2 = C1159bb.g;
        if (c1159bb.e(c1159bb2)) {
            z = true;
        } else {
            if (c0333y2.o() && c1159bb.isEqualTo(c1159bb2)) {
                if (!C2752uB.Y1 && !c2752uBM.L1 && c2752uBM.H() == null) {
                    x1f.a();
                    return;
                } else if (!c2752uBM.L1 && !c2752uBM.H().f().s) {
                    z = true;
                }
            }
            z = false;
        }
        AbstractC2004lX abstractC2004lXL0 = L0();
        boolean z3 = abstractC2004lXL0 != null && (abstractC2004lXL0 instanceof AbstractC2004lX.c);
        boolean z4 = z3;
        for (AbstractC3175z9 abstractC3175z9 : this.h) {
            if (!z || !abstractC3175z9.G()) {
                if (z4) {
                    abstractC3175z9.getClass();
                    if (abstractC3175z9 instanceof C1752ia) {
                        AbstractC2004lX abstractC2004lXT = abstractC3175z9.r().T();
                        abstractC2004lXT.getClass();
                        if (com.android.tools.r8.utils.structural.k.a(abstractC2004lXT, abstractC2004lXL0)) {
                            z4 = false;
                        }
                    }
                }
                abstractC3175z9.a(c0333y2, b5, b1A, abstractC3148ysA, abstractC3148ysA2, abstractC0837Sv, abstractC3345r0, rj, yo2);
            }
            c0333y2 = c0333y;
            abstractC3148ysA = abstractC3148ysA;
            abstractC3148ysA2 = abstractC3148ysA2;
            abstractC0837Sv = abstractC0837Sv;
            abstractC2004lXL0 = abstractC2004lXL0;
        }
        AbstractC3148ys abstractC3148ys = abstractC3148ysA;
        AbstractC3148ys abstractC3148ys2 = abstractC3148ysA2;
        yo2.c(this.g, this.f);
        for (C0972Ya c0972Ya : this.i) {
            WI wiT = c0972Ya.a.T();
            WI wiT2 = c0972Ya.b.T();
            for (int i = 0; i < c0972Ya.c.size(); i++) {
                I2 i2E = abstractC3148ys.c(abstractC3148ys2, c0972Ya.c.get(i));
                yo2.a(wiT, wiT2, c0972Ya.d.get(i).T(), i2E == c2752uBM.a.o3 ? null : abstractC3345r0.d(i2E));
            }
        }
        if (k9 == null) {
            for (a aVar : this.j) {
                C0230j0 c0230j0 = aVar.b;
                K9 k11 = aVar.c;
                K9 k12 = aVar.d;
                int i2 = aVar.a;
                I2 i2E2 = abstractC3148ys.c(abstractC3148ys2, c0230j0.c);
                String string = c0230j0.b.toString();
                String string2 = abstractC3345r0.c(i2E2).toString();
                H2 h2 = c0230j0.d;
                yo.a(string, string2, h2 == null ? null : h2.toString(), k11.T(), k12.T(), i2);
            }
            return;
        }
        if (!n && !this.j.isEmpty()) {
            x1f.a();
            return;
        }
        BU it = b5.e().s.c().iterator();
        while (it.hasNext()) {
            InterfaceC1959kz interfaceC1959kz = (InterfaceC1959kz) it.next();
            C0230j0 c0230j1 = (C0230j0) interfaceC1959kz.getValue();
            int iA2 = interfaceC1959kz.a();
            I2 i2E3 = abstractC3148ys.c(abstractC3148ys2, c0230j1.c);
            String string3 = c0230j1.b.toString();
            String string4 = abstractC3345r0.c(i2E3).toString();
            H2 h3 = c0230j1.d;
            yo2.a(string3, string4, h3 == null ? null : h3.toString(), k9.T(), k9.T(), iA2);
            yo2 = yo;
        }
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public G a(C0322w2 c0322w2, boolean z, C0322w2 c0322w3, boolean z2, B1 b1) {
        K9 k9;
        AbstractC2004lX.c cVarA;
        AbstractC2004lX.c.a aVarA = AbstractC2004lX.c.s().a(0).a(c0322w2);
        aVarA.e = z;
        AbstractC2004lX.c cVarA2 = aVarA.a();
        ArrayList arrayList = new ArrayList(this.h.size() + 2);
        AbstractC3175z9 abstractC3175z9 = (AbstractC3175z9) this.h.get(0);
        abstractC3175z9.getClass();
        if (abstractC3175z9 instanceof K9) {
            k9 = ((AbstractC3175z9) this.h.get(0)).o();
        } else {
            k9 = new K9();
            arrayList.add(k9);
        }
        boolean z3 = false;
        for (AbstractC3175z9 abstractC3175z10 : this.h) {
            abstractC3175z10.getClass();
            if (abstractC3175z10 instanceof C1752ia) {
                C1752ia c1752iaR = abstractC3175z10.r();
                arrayList.add(new C1752ia(c1752iaR.c, AbstractC0223i0.a(cVarA2, c1752iaR.T(), z2)));
                z3 = true;
            } else {
                if (!(abstractC3175z10 instanceof K9) && !z3) {
                    if (z2) {
                        cVarA = cVarA2;
                    } else {
                        AbstractC2004lX.c.a aVarA2 = AbstractC2004lX.c.s().a(c0322w3);
                        aVarA2.c = cVarA2;
                        cVarA = aVarA2.a(0).a();
                    }
                    arrayList.add(new C1752ia(k9, cVarA));
                    z3 = true;
                }
                arrayList.add(abstractC3175z10);
            }
        }
        return new G(this.e, this.g, this.f, arrayList, this.i, this.j);
    }

    @Override // com.android.tools.r8.graph.E
    public final boolean c(Object obj) {
        throw new C1345dk0();
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final int k(int i) {
        Iterator it = this.h.iterator();
        int i2 = 0;
        while (it.hasNext() && (!((AbstractC3175z9) it.next()).y() || (i2 = i2 + 1) <= i)) {
        }
        if (i2 <= i) {
            return i2;
        }
        return -1;
    }

    public final AbstractC3175z9 l(int i) {
        return (AbstractC3175z9) this.h.get(i);
    }

    @Override // com.android.tools.r8.graph.E
    public final int n0() {
        throw new C1345dk0();
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final P o0() {
        return this;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final int t0() {
        return k(Integer.MAX_VALUE) * 5;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public String toString() {
        return new C2520ra(this, null, C1581ga0.b).toString();
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final boolean v0() {
        Iterator<AbstractC3175z9> it = H0().iterator();
        while (it.hasNext()) {
            if (it.next() instanceof S9) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final boolean w0() {
        return true;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final boolean x0() {
        return true;
    }

    public static class a {
        public static final /* synthetic */ boolean e = true;
        public final int a;
        public final C0230j0 b;
        public final K9 c;
        public K9 d;

        public a(int i, C0230j0 c0230j0, K9 k9, K9 k10) {
            this.a = i;
            this.b = c0230j0;
            this.c = k9;
            a(k10);
        }

        public static void a(O o, com.android.tools.r8.utils.structural.A a) {
            com.android.tools.r8.utils.structural.A a2 = a.a(new ToIntFunction() { // from class: fw5
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((G.a) obj).b();
                }
            });
            Function function = new Function() { // from class: gw5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((G.a) obj).d();
                }
            };
            com.android.tools.r8.utils.structural.u uVarA = o.a();
            com.android.tools.r8.utils.structural.A a3 = a2.a(function, uVarA, uVarA);
            Function function2 = new Function() { // from class: hw5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((G.a) obj).a();
                }
            };
            com.android.tools.r8.utils.structural.u uVarA2 = o.a();
            a3.a(function2, uVarA2, uVarA2).e(new Function() { // from class: iw5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((G.a) obj).c();
                }
            });
        }

        public int b() {
            return this.a;
        }

        public C0230j0 c() {
            return this.b;
        }

        public K9 d() {
            return this.c;
        }

        public final String toString() {
            return this.a + " => " + this.b;
        }

        public a(int i, C0230j0 c0230j0, K9 k9) {
            this.a = i;
            this.b = c0230j0;
            this.c = k9;
        }

        public K9 a() {
            return this.d;
        }

        public final int a(a aVar, AbstractC3519a abstractC3519a, final O o) {
            return abstractC3519a.a(this, aVar, (com.android.tools.r8.utils.structural.y<a>) new com.android.tools.r8.utils.structural.y() { // from class: ew5
                @Override // com.android.tools.r8.utils.structural.y
                public final void a(A a) {
                    G.a.a(o, a);
                }
            });
        }

        public final void a(K9 k9) {
            boolean z = e;
            if (!z && this.d != null) {
                x1f.a();
            } else if (!z && k9 == null) {
                x1f.a();
            } else {
                this.d = k9;
            }
        }
    }

    public G(I2 i2, int i, int i3, List list, List list2, List list3) {
        this(i2, i, i3, list, list2, list3, Position.UNKNOWN, C1211c8.b);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public G(I2 i2, int i, AbstractC0551Hu abstractC0551Hu) {
        List list = Collections.EMPTY_LIST;
        this(i2, 3, i, abstractC0551Hu, list, list);
    }

    public final void b(C0333y c0333y, B5 b5) {
        int iA = a(c0333y, b5);
        this.k = iA;
        if (iA == 4 || iA == 2) {
            return;
        }
        ArrayList arrayList = new ArrayList(this.h);
        arrayList.removeIf(new Predicate() { // from class: xv5
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((AbstractC3175z9) obj).G();
            }
        });
        a(arrayList);
    }

    public static void a(com.android.tools.r8.utils.structural.o oVar, AbstractC3175z9 abstractC3175z9) {
        ((com.android.tools.r8.utils.structural.q) oVar).a.a(abstractC3175z9.z());
        abstractC3175z9.a(oVar);
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final C1041a8 a(InterfaceC0022c interfaceC0022c) {
        return (C1041a8) this.m.a.get(interfaceC0022c.n());
    }

    public void a(List<AbstractC3175z9> list) {
        this.h = list;
    }

    @Override // com.android.tools.r8.graph.P, com.android.tools.r8.utils.structural.x
    public final void a(final com.android.tools.r8.utils.structural.o oVar) {
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.a.a(this.h.size());
        qVar.a.a(this.i.size());
        qVar.a.a(this.j.size());
        this.h.forEach(new Consumer() { // from class: tv5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                G.a(oVar, (AbstractC3175z9) obj);
            }
        });
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final int a(G g, AbstractC3519a abstractC3519a) {
        final O o = new O(this, g);
        return abstractC3519a.a(this, g, (com.android.tools.r8.utils.structural.y<G>) new com.android.tools.r8.utils.structural.y() { // from class: aw5
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a2) {
                G.a(o, a2);
            }
        });
    }

    public static void a(O o, com.android.tools.r8.utils.structural.A a2) {
        Function function = new Function() { // from class: bw5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((G) obj).h;
            }
        };
        o.getClass();
        a2.a(function, (com.android.tools.r8.utils.structural.u) new L(o)).a(new Function() { // from class: cw5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((G) obj).i;
            }
        }, (com.android.tools.r8.utils.structural.u) new M(o)).a(new Function() { // from class: dw5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((G) obj).j;
            }
        }, (com.android.tools.r8.utils.structural.u) new N(o));
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final C0705Nt a(B5 b5, C0333y c0333y, AbstractC2166nO.a aVar) {
        b(c0333y, b5);
        AbstractC3148ys abstractC3148ys = c0333y.g;
        b5.getClass();
        if (c0333y.Q().l0 || (!c0333y.M().Z0 && !b5.a().f(c0333y))) {
            return a(Collections.EMPTY_LIST, b5, b5, c0333y, abstractC3148ys, null, null, null, aVar);
        }
        try {
            return a(Collections.unmodifiableList(this.j), b5, b5, c0333y, abstractC3148ys, null, null, null, aVar);
        } catch (NB e) {
            c0333y.M().a(b5, e);
            return a(Collections.EMPTY_LIST, b5, b5, c0333y, abstractC3148ys, null, null, null, aVar);
        }
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final C0705Nt a(B5 b5, B5 b6, C0333y c0333y, AbstractC3148ys abstractC3148ys, AS as, AbstractC2004lX abstractC2004lX, com.android.tools.r8.graph.proto.j jVar) {
        boolean z = n;
        if (!z && as == null) {
            x1f.a();
            return null;
        }
        if (!z && abstractC2004lX == null) {
            x1f.a();
            return null;
        }
        if (!z && jVar == null) {
            x1f.a();
            return null;
        }
        b(c0333y, b6);
        AbstractC2166nO.a aVarE = AbstractC2166nO.e();
        b6.getClass();
        if (c0333y.Q().l0 || (!c0333y.M().Z0 && !b6.a().f(c0333y))) {
            return a(Collections.EMPTY_LIST, b5, b6, c0333y, abstractC3148ys, as, abstractC2004lX, jVar, aVarE);
        }
        try {
            return a(Collections.unmodifiableList(this.j), b5, b6, c0333y, abstractC3148ys, as, abstractC2004lX, jVar, aVarE);
        } catch (NB e) {
            c0333y.M().a(b6, e);
            return a(Collections.EMPTY_LIST, b5, b6, c0333y, abstractC3148ys, as, abstractC2004lX, jVar, aVarE);
        }
    }

    public final C0705Nt a(List list, B5 b5, B5 b6, C0333y c0333y, AbstractC3148ys abstractC3148ys, AS as, AbstractC2004lX abstractC2004lX, com.android.tools.r8.graph.proto.j jVar, AbstractC2166nO.a aVar) {
        C0602Jt c0602Jt;
        C0401Ca c0401Ca = new C0401Ca(this, list, b6, abstractC2004lX, c0333y);
        if (as == null) {
            if (!n && jVar != null) {
                x1f.a();
                return null;
            }
            c0602Jt = C0602Jt.a(b6, c0333y, c0401Ca);
        } else {
            c0602Jt = new C0602Jt(b6, c0333y, abstractC3148ys, c0401Ca, jVar, as);
        }
        return c0602Jt.a(b5, aVar);
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final void a(B5 b5, Z5 z5) {
        if (!n && !z5.c.d()) {
            x1f.a();
            return;
        }
        ListIterator listIterator = this.h.listIterator();
        while (listIterator.hasNext()) {
            ((AbstractC3175z9) listIterator.next()).a(z5, listIterator);
            if (z5.c.c()) {
                return;
            }
        }
        Iterator it = this.i.iterator();
        while (it.hasNext()) {
            ((C0972Ya) it.next()).a(z5);
            if (z5.c.c()) {
                return;
            }
        }
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final void a(C0195e0 c0195e0, final C3047xh c3047xh) {
        ListIterator listIterator = this.h.listIterator();
        while (listIterator.hasNext()) {
            ((AbstractC3175z9) listIterator.next()).a(c3047xh, listIterator);
        }
        this.i.forEach(new Consumer() { // from class: vv5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                G.a(c3047xh, (C0972Ya) obj);
            }
        });
    }

    public static /* synthetic */ void a(final Z5 z5, C0972Ya c0972Ya) {
        List<I2> list = c0972Ya.c;
        Objects.requireNonNull(z5);
        list.forEach(new Consumer() { // from class: zv5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                z5.f((I2) obj);
            }
        });
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final InterfaceC2045lz a(C0333y c0333y, C0231j1 c0231j1) {
        K9 k9;
        int i;
        Iterator it = this.h.iterator();
        while (true) {
            if (!it.hasNext()) {
                k9 = null;
                break;
            }
            AbstractC3175z9 abstractC3175z9 = (AbstractC3175z9) it.next();
            if (abstractC3175z9 instanceof K9) {
                k9 = (K9) abstractC3175z9;
                break;
            }
        }
        if (k9 == null) {
            return C0231j1.w;
        }
        if (c0333y.M().h != null && c0333y.M().H().v()) {
            if (c0333y.g().i()) {
                C3403i c3403iM = c0333y.g().m();
                if (!c3403iM.v.a(c0231j1, c3403iM.j(), c3403iM)) {
                    return C0231j1.w;
                }
            }
            BitSet bitSet = new BitSet(0);
            if (c0231j1.z0()) {
                i = 0;
            } else {
                bitSet.set(0);
                i = 1;
            }
            for (I2 i2 : c0231j1.getReference().i.f.b) {
                bitSet.set(i);
                i += (i2.Q0() || i2.N0()) ? 2 : 1;
            }
            C1022Zy c1022Zy = new C1022Zy(bitSet.cardinality());
            for (a aVar : this.j) {
                if (aVar.c == k9 && bitSet.get(aVar.a) && !c1022Zy.a(aVar.a)) {
                    int i3 = aVar.a;
                    C0230j0 c0230j0 = aVar.b;
                    c1022Zy.a(i3, new C0230j0(c0230j0.b, c0230j0.c, c0230j0.d));
                }
            }
            return c1022Zy;
        }
        return C0231j1.w;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final String a(C0231j1 c0231j1, C1581ga0 c1581ga0) {
        return new C2520ra(this, c0231j1, c1581ga0).toString();
    }

    public final void a(B1 b1) {
        List list = this.j;
        if (list == null || list.isEmpty()) {
            return;
        }
        int i = -1;
        int iMax = 0;
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            a aVar = (a) this.j.get(i2);
            iMax = Math.max(iMax, J0.a(b1, aVar.b.b));
            if (aVar.b.b.toString().equals("this")) {
                i = i2;
            }
        }
        if (i < 0) {
            return;
        }
        C0230j0 c0230j0 = new C0230j0(b1.c("_".repeat(iMax + 1) + "this"), this.e, null);
        a aVar2 = (a) this.j.get(i);
        this.j.set(i, new a(aVar2.a, c0230j0, aVar2.c, aVar2.d));
    }

    public final int a(C0333y c0333y, B5 b5) {
        G g;
        int i = this.k;
        if (i != 0) {
            if (i == 1) {
                g = this;
                g.k = new C2065m9(c0333y, g, new C2150n9(c0333y, this, b5), new F(c0333y), b5).a();
            } else {
                g = this;
            }
            return g.k;
        }
        throw null;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final void a(C0322w2 c0322w2, boolean z, Consumer consumer) {
        for (AbstractC3175z9 abstractC3175z9 : H0()) {
            abstractC3175z9.getClass();
            if (abstractC3175z9 instanceof C1752ia) {
                consumer.accept(abstractC3175z9.r().T());
            }
        }
    }
}
