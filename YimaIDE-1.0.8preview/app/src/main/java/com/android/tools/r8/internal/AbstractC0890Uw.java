package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0230j0;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.C2543rl0;
import com.android.tools.r8.shaking.C3462u;
import defpackage.bka;
import defpackage.k26;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Uw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0890Uw implements H, InterfaceC0994Yw, InterfaceC2507rN {
    public static final /* synthetic */ boolean h = true;
    public C2543rl0 b = null;
    public final ArrayList c = new ArrayList();
    public H5 d = null;
    public int e = -1;
    public Set f = null;
    public AbstractC2004lX g = null;

    public AbstractC0890Uw(List list, C2543rl0 c2543rl0) {
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                b((C2543rl0) it.next());
            }
        }
        d(c2543rl0);
    }

    public C0456Ed A() {
        return null;
    }

    public C2821v20 A0() {
        return null;
    }

    public boolean A1() {
        return false;
    }

    public boolean A2() {
        return this instanceof C1764ig0;
    }

    public C1169bg B() {
        return null;
    }

    public C1036a50 B0() {
        return null;
    }

    public boolean B1() {
        return (this instanceof FQ) || n2() || m2() || (this instanceof C1217cC) || (this instanceof C2821v20);
    }

    public boolean B2() {
        return false;
    }

    public AbstractC1252cg C() {
        return null;
    }

    public D50 C0() {
        return null;
    }

    public final boolean C1() {
        return o2() || B1();
    }

    public boolean C2() {
        return false;
    }

    public C1336dg D() {
        return null;
    }

    public C1837ja0 D0() {
        return null;
    }

    public final boolean D1() {
        return (this instanceof C0719Oh) || (this instanceof C0667Mh) || (this instanceof C0590Jh) || (this instanceof C0642Lh) || (this instanceof C0616Kh);
    }

    public boolean D2() {
        return false;
    }

    public C1421eg E() {
        return null;
    }

    public Aa0 E0() {
        return null;
    }

    public boolean E1() {
        return this instanceof C0642Lh;
    }

    public boolean E2() {
        return this instanceof C1432el0;
    }

    public C1678hg F() {
        return null;
    }

    public C3037xb0 F0() {
        return null;
    }

    public boolean F1() {
        return this instanceof C0719Oh;
    }

    public abstract int F2();

    public C2104mg G() {
        return null;
    }

    public Bb0 G0() {
        return null;
    }

    public boolean G1() {
        return this instanceof C0646Ll;
    }

    public abstract int G2();

    public C0642Lh H() {
        return null;
    }

    public C2529re0 H0() {
        return null;
    }

    public boolean H1() {
        return this instanceof C3152yw;
    }

    public abstract int H2();

    public C0667Mh I() {
        return null;
    }

    public C2614se0 I0() {
        return null;
    }

    public boolean I1() {
        return false;
    }

    public final El0 I2() {
        return this.b.Y();
    }

    public C0719Oh J() {
        return null;
    }

    public C2700te0 J0() {
        return null;
    }

    public boolean J1() {
        return this instanceof C0682Mw;
    }

    public boolean J2() {
        return false;
    }

    public C0646Ll K() {
        return null;
    }

    public If0 K0() {
        return null;
    }

    public boolean K1() {
        return false;
    }

    public C1433em L() {
        return null;
    }

    public C1764ig0 L0() {
        return null;
    }

    public boolean L1() {
        return false;
    }

    public C3227zm M() {
        return null;
    }

    public AbstractC2618sg0 M0() {
        return null;
    }

    public boolean M1() {
        return false;
    }

    public InterfaceC1694hp N() {
        return null;
    }

    public C1424eh0 N0() {
        return null;
    }

    public boolean N1() {
        return this instanceof C3152yw;
    }

    public AbstractC1949kp O() {
        return null;
    }

    public C1603gl0 O0() {
        return null;
    }

    public boolean O1() {
        return this instanceof C3152yw;
    }

    public InterfaceC3145yp P() {
        return null;
    }

    public Ym0 P0() {
        return null;
    }

    public boolean P1() {
        return this instanceof C0605Jw;
    }

    public C2636ss Q() {
        return null;
    }

    public final void Q0() {
        Set set = this.f;
        if (set != null) {
            Iterator it = set.iterator();
            while (it.hasNext()) {
                ((C2543rl0) it.next()).c(this);
            }
            this.f.clear();
        }
    }

    public boolean Q1() {
        return false;
    }

    public C2040lu R() {
        return null;
    }

    public final void R0() {
        d(null);
    }

    public boolean R1() {
        return false;
    }

    public C0785Qv S() {
        return null;
    }

    public final Set S0() {
        Set set = this.f;
        if (set != null) {
            return set;
        }
        int i = AbstractC2554rv.c;
        return W40.j;
    }

    public boolean S1() {
        return false;
    }

    public InterfaceC2726tw T() {
        return null;
    }

    public C2543rl0 T0() {
        return a(0);
    }

    public boolean T1() {
        return this instanceof TB;
    }

    public C0605Jw U() {
        return null;
    }

    public String U0() {
        return getClass().getSimpleName();
    }

    public boolean U1() {
        return false;
    }

    public C0682Mw V() {
        return null;
    }

    public C2543rl0 V0() {
        throw new Kk0("Should conform to throwsOnNullInput.");
    }

    public boolean V1() {
        return this instanceof YB;
    }

    public FA W() {
        return null;
    }

    public final int W0() {
        return this.e;
    }

    public boolean W1() {
        return false;
    }

    public SB X() {
        return null;
    }

    public AbstractC1252cg X0() {
        return null;
    }

    public boolean X1() {
        return false;
    }

    public TB Y() {
        return null;
    }

    public final String Y0() {
        AbstractC2004lX abstractC2004lX = this.g;
        return abstractC2004lX == null ? "???" : abstractC2004lX.toString();
    }

    public boolean Y1() {
        return this instanceof C1217cC;
    }

    public VB Z() {
        return null;
    }

    public final boolean Z0() {
        return this.d != null;
    }

    public boolean Z1() {
        return this instanceof C1301dC;
    }

    public abstract com.android.tools.r8.ir.optimize.N a(com.android.tools.r8.ir.optimize.W w, com.android.tools.r8.graph.B5 b5);

    public abstract Object a(C0941Wv c0941Wv);

    public abstract void a(J8 j8);

    public abstract void a(K5 k5, IL il);

    public abstract void a(LK lk);

    public abstract void a(C2884vl c2884vl);

    /* JADX WARN: Code duplicated, block: B:106:0x0194  */
    /* JADX WARN: Code duplicated, block: B:53:0x00cc  */
    public boolean a(AbstractC0890Uw abstractC0890Uw, com.android.tools.r8.ir.regalloc.f fVar, AbstractC2166nO abstractC2166nO) {
        boolean zA;
        boolean zA2;
        if (abstractC0890Uw.getClass() != getClass() || !a(abstractC0890Uw, fVar)) {
            return false;
        }
        if (U1()) {
            VB vbZ = Z();
            SB sbX = abstractC0890Uw.X();
            if (vbZ.U2().g.toString().equals("<init>") && vbZ.c.get(0) != sbX.c.get(0)) {
                return false;
            }
        }
        C2543rl0 c2543rl0 = this.b;
        C2543rl0 c2543rl1 = abstractC0890Uw.b;
        if (c2543rl0 != null) {
            if (c2543rl1 == null) {
                return false;
            }
            int i = this.e;
            int i2 = abstractC0890Uw.e;
            boolean z = c2543rl0 instanceof Od0;
            boolean z2 = c2543rl0 instanceof Pd0;
            if (z != (c2543rl1 instanceof Od0) || z2 != (c2543rl1 instanceof Pd0)) {
                zA2 = false;
            } else if (z) {
                zA2 = a((Od0) c2543rl0, (Od0) c2543rl1);
            } else if (z2) {
                Od0[] od0Arr = ((Pd0) c2543rl0).r;
                Od0[] od0Arr2 = ((Pd0) c2543rl1).r;
                if (od0Arr.length == od0Arr2.length) {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= od0Arr.length) {
                            zA2 = true;
                        } else if (a(od0Arr[i3], od0Arr2[i3])) {
                            i3++;
                        }
                    }
                }
                zA2 = false;
            } else if (c2543rl0.T() == c2543rl1.T() && (!c2543rl0.T() ? c2543rl0.k().F().b(c2543rl1.k().F()) : fVar.a(c2543rl0, i) == fVar.a(c2543rl1, i2)) && c2543rl0.Y() == c2543rl1.Y()) {
                zA2 = true;
            } else {
                zA2 = false;
            }
            if (!zA2) {
                return false;
            }
        } else if (c2543rl1 != null) {
            return false;
        }
        if (this.c.size() != abstractC0890Uw.c.size()) {
            return false;
        }
        for (int i4 = 0; i4 < this.c.size(); i4++) {
            C2543rl0 c2543rl2 = (C2543rl0) this.c.get(i4);
            C2543rl0 c2543rl3 = (C2543rl0) abstractC0890Uw.c.get(i4);
            int i5 = this.e;
            int i6 = abstractC0890Uw.e;
            boolean z3 = c2543rl2 instanceof Od0;
            boolean z4 = c2543rl2 instanceof Pd0;
            if (z3 != (c2543rl3 instanceof Od0) || z4 != (c2543rl3 instanceof Pd0)) {
                zA = false;
            } else if (z3) {
                zA = a((Od0) c2543rl2, (Od0) c2543rl3);
            } else if (z4) {
                Od0[] od0Arr3 = ((Pd0) c2543rl2).r;
                Od0[] od0Arr4 = ((Pd0) c2543rl3).r;
                if (od0Arr3.length == od0Arr4.length) {
                    int i7 = 0;
                    while (true) {
                        if (i7 >= od0Arr3.length) {
                            zA = true;
                        } else if (a(od0Arr3[i7], od0Arr4[i7])) {
                            i7++;
                        }
                    }
                }
                zA = false;
            } else if (c(c2543rl2) == abstractC0890Uw.c(c2543rl3) && (c(c2543rl2) || a(fVar) || abstractC0890Uw.a(fVar) ? c2543rl2.T() && c2543rl3.T() && fVar.a(c2543rl2, i5) == fVar.a(c2543rl3, i6) : c2543rl2.k().F().b(c2543rl3.k().F())) && c2543rl2.Y() == c2543rl3.Y()) {
                zA = true;
            } else {
                zA = false;
            }
            if (!zA) {
                return false;
            }
        }
        if (abstractC2166nO.b()) {
            C2884vl c2884vl = new C2884vl(null, C1295d8.b(), fVar, fVar.c(), abstractC2166nO);
            a(c2884vl);
            boolean z5 = C2884vl.r;
            if (!z5 && c2884vl.l.length != 1) {
                x1f.a();
                return false;
            }
            AbstractC2457ql abstractC2457ql = c2884vl.l[0];
            abstractC0890Uw.a(c2884vl);
            if (!z5 && c2884vl.l.length != 1) {
                x1f.a();
                return false;
            }
            if (!abstractC2457ql.a(c2884vl.l[0], c2884vl)) {
                return false;
            }
        }
        return true;
    }

    public YB a0() {
        return null;
    }

    public final boolean a1() {
        bka bkaVar = new bka();
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            if (bkaVar.test((C2543rl0) it.next())) {
                return true;
            }
        }
        return false;
    }

    public boolean a2() {
        return false;
    }

    public void b(C2543rl0 c2543rl0) {
        if (c2543rl0 != null) {
            this.c.add(c2543rl0);
            if (!h && !c2543rl0.D()) {
                x1f.a();
            } else if (c2543rl0.D()) {
                c2543rl0.d.add(this);
                c2543rl0.e = null;
            }
        }
    }

    public abstract boolean b(AbstractC0890Uw abstractC0890Uw);

    public AbstractC1047aC b0() {
        return null;
    }

    public abstract boolean b1();

    public boolean b2() {
        return this instanceof C2069mC;
    }

    public final void c(AbstractC0890Uw abstractC0890Uw) {
        Set<C2543rl0> set = this.f;
        if (set == null) {
            return;
        }
        for (C2543rl0 c2543rl0 : set) {
            boolean zRemove = c2543rl0.n.b.remove(this);
            if (!C2543rl0.q && !zRemove) {
                x1f.a();
                return;
            } else if (zRemove) {
                c2543rl0.a(abstractC0890Uw);
            }
        }
        this.f.clear();
    }

    public AbstractC1133bC c0() {
        return null;
    }

    public boolean c1() {
        return this.b != null;
    }

    public boolean c2() {
        return false;
    }

    public C2543rl0 d(C2543rl0 c2543rl0) {
        C2543rl0 c2543rl0C = c();
        this.b = c2543rl0;
        if (c2543rl0 != null) {
            c2543rl0.c = this;
        }
        return c2543rl0C;
    }

    public abstract boolean d(C0333y c0333y, com.android.tools.r8.graph.B5 b5);

    public C1217cC d0() {
        return null;
    }

    public final boolean d1() {
        return !e1();
    }

    public boolean d2() {
        return false;
    }

    public final void e(C2543rl0 c2543rl0, C2543rl0 c2543rl1) {
        boolean z = h;
        if (!z && !c2543rl0.y()) {
            x1f.a();
            return;
        }
        if (!z && !c2543rl1.y()) {
            x1f.a();
            return;
        }
        if (!z && c2543rl1.r() != c2543rl0.r()) {
            k26.a("Replacing debug values with inconsistent locals ", c2543rl0.r(), " and ", c2543rl1.r(), ". This is likely a code transformation bug that has not taken local information into account");
            return;
        }
        boolean zRemove = this.f.remove(c2543rl0);
        if (!z && !zRemove) {
            x1f.a();
        } else if (zRemove && c2543rl1.y()) {
            c2543rl1.a(this);
        }
    }

    public C1301dC e0() {
        return null;
    }

    public final boolean e1() {
        return c1() && c().v();
    }

    public boolean e2() {
        return this instanceof EL;
    }

    public C1983lC f0() {
        return null;
    }

    public final List f1() {
        return this.c;
    }

    public boolean f2() {
        return false;
    }

    @Override // com.android.tools.r8.internal.H
    public boolean g() {
        return false;
    }

    public C2069mC g0() {
        return null;
    }

    public boolean g1() {
        return this instanceof C1169bg;
    }

    public boolean g2() {
        return this instanceof C2424qP;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2507rN
    public final AbstractC2004lX getPosition() {
        if (h || this.g != null) {
            return this.g;
        }
        x1f.a();
        return null;
    }

    public C2496rC h0() {
        return null;
    }

    public boolean h1() {
        return false;
    }

    public boolean h2() {
        return false;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0994Yw
    public H5 i() {
        if (h || this.d != null) {
            return this.d;
        }
        x1f.a();
        return null;
    }

    public AbstractC2925wD i0() {
        return null;
    }

    public boolean i1() {
        return this instanceof C0590Jh;
    }

    public boolean i2() {
        return false;
    }

    public EL j0() {
        return null;
    }

    public boolean j1() {
        return this instanceof C2308p2;
    }

    public boolean j2() {
        return this instanceof C2766uP;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2507rN
    public C0230j0 k() {
        C2543rl0 c2543rl0 = this.b;
        if (c2543rl0 == null) {
            return null;
        }
        return c2543rl0.r();
    }

    public QL k0() {
        return null;
    }

    public boolean k1() {
        return false;
    }

    public boolean k2() {
        return this instanceof FQ;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0994Yw
    public final AbstractC0890Uw l() {
        return this;
    }

    public C2424qP l0() {
        return null;
    }

    public boolean l1() {
        return false;
    }

    public final boolean l2() {
        return (this instanceof FQ) || m2();
    }

    public C2680tP m0() {
        return null;
    }

    public boolean m1() {
        return false;
    }

    public boolean m2() {
        return false;
    }

    public C2766uP n0() {
        return null;
    }

    public boolean n1() {
        return false;
    }

    public boolean n2() {
        return false;
    }

    public C1029a2 o() {
        return null;
    }

    public C3023xP o0() {
        return null;
    }

    public boolean o1() {
        return this instanceof J3;
    }

    public boolean o2() {
        return false;
    }

    public C2938wQ p0() {
        return null;
    }

    public boolean p1() {
        return false;
    }

    public boolean p2() {
        return false;
    }

    public C2308p2 q() {
        return null;
    }

    public FQ q0() {
        return null;
    }

    public boolean q1() {
        return this instanceof C3165z4;
    }

    public boolean q2() {
        return this instanceof C2772uV;
    }

    public C1201c3 r() {
        return null;
    }

    public HQ r0() {
        return null;
    }

    public final boolean r1() {
        return (this instanceof C3165z4) && x().K2();
    }

    public boolean r2() {
        return this instanceof C1169bg;
    }

    public C3 s() {
        return null;
    }

    public IQ s0() {
        return null;
    }

    public final boolean s1() {
        return (this instanceof C3165z4) && x().i.d().d();
    }

    public boolean s2() {
        return this instanceof C1492fX;
    }

    public D3 t() {
        return null;
    }

    public KQ t0() {
        return null;
    }

    public boolean t1() {
        return false;
    }

    public boolean t2() {
        return this instanceof C2821v20;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(U0());
        for (int length = sb.length(); length < 20; length++) {
            sb.append(" ");
        }
        sb.append(" ");
        C2543rl0 c2543rl0 = this.b;
        if (c2543rl0 != null) {
            sb.append(c2543rl0);
            sb.append(" <- ");
        }
        if (!this.c.isEmpty()) {
            Wf0.a(sb, this.c, ", ", Wf0.a.e);
        }
        return sb.toString();
    }

    public H3 u() {
        return null;
    }

    public LQ u0() {
        return null;
    }

    public boolean u1() {
        return false;
    }

    public boolean u2() {
        return this instanceof D50;
    }

    public J3 v() {
        return null;
    }

    public C1487fS v0() {
        return null;
    }

    public boolean v1() {
        return this instanceof C1169bg;
    }

    public boolean v2() {
        return false;
    }

    public N3 w() {
        return null;
    }

    public C3026xS w0() {
        return null;
    }

    public boolean w1() {
        return false;
    }

    public boolean w2() {
        return this instanceof C2529re0;
    }

    public C3165z4 x() {
        return null;
    }

    public C2772uV x0() {
        return null;
    }

    public boolean x1() {
        return this instanceof C1336dg;
    }

    public boolean x2() {
        return this instanceof C2529re0;
    }

    public AbstractC1547g6 y() {
        return null;
    }

    public C3113yV y0() {
        return null;
    }

    public boolean y1() {
        return this instanceof C1421eg;
    }

    public boolean y2() {
        return this instanceof C2700te0;
    }

    public C0428Db z() {
        return null;
    }

    public C1492fX z0() {
        return null;
    }

    public boolean z1() {
        return false;
    }

    public boolean z2() {
        return false;
    }

    public boolean d() {
        return false;
    }

    public Bb0 d(C2543rl0 c2543rl0, C2543rl0 c2543rl1) {
        return null;
    }

    public C3152yw b() {
        return null;
    }

    public void b(AbstractC2004lX abstractC2004lX) {
        if (!h && this.g != null) {
            x1f.a();
        } else {
            this.g = abstractC2004lX;
        }
    }

    public C2772uV b(C2543rl0 c2543rl0, C2543rl0 c2543rl1) {
        return null;
    }

    public final boolean b(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        return b(c0333y, b5, J1.a(c0333y, b5), C0864Tw.a);
    }

    public final boolean b(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1) {
        return b(c0333y, b5, j1, C0864Tw.a);
    }

    public boolean b(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1, C0864Tw c0864Tw) {
        return a(c0333y, b5, j1, c0864Tw);
    }

    public AbstractC0890Uw(C2543rl0 c2543rl0, C2543rl0 c2543rl1) {
        b(c2543rl1);
        d(c2543rl0);
    }

    public boolean c(C2543rl0 c2543rl0) {
        return true;
    }

    public C2543rl0 c() {
        return this.b;
    }

    public C3037xb0 c(C2543rl0 c2543rl0, C2543rl0 c2543rl1) {
        return null;
    }

    public final boolean c(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        return b(c0333y, b5, J1.a(c0333y, b5), C0864Tw.c);
    }

    public AbstractC0890Uw(C2543rl0 c2543rl0) {
        d(c2543rl0);
    }

    public final C2543rl0 e(C2543rl0 c2543rl0) {
        C2543rl0 c2543rl1 = this.b;
        this.b = null;
        d(c2543rl0);
        if (c2543rl1 != null) {
            c2543rl1.c = null;
        }
        return c2543rl1;
    }

    public AbstractC2389q e(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        if (d(c0333y, b5) && b(c0333y, b5)) {
            return C2626sk0.a;
        }
        return C0828Sm.a;
    }

    public C2308p2 a(C2543rl0 c2543rl0, C2543rl0 c2543rl1) {
        return null;
    }

    public void a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, Ol0 ol0) {
    }

    public void a(C3462u c3462u) {
    }

    public boolean a(com.android.tools.r8.graph.B1 b1) {
        return false;
    }

    public boolean a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.B5 b5, C0333y c0333y, int i, int i3) {
        return false;
    }

    public boolean a(C0322w2 c0322w2) {
        return false;
    }

    public boolean a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, C2543rl0 c2543rl0) {
        return false;
    }

    public boolean a(C0333y c0333y, C2543rl0 c2543rl0) {
        return false;
    }

    public boolean a(com.android.tools.r8.ir.regalloc.f fVar) {
        return false;
    }

    public boolean a(Set set) {
        return this instanceof C0605Jw;
    }

    public static boolean a(Od0 od0, Od0 od1) {
        return od0.r == od1.r && El0.a(od0.o) == El0.a(od1.o);
    }

    public final void a(AbstractC2004lX abstractC2004lX, C2752uB c2752uB) {
        if (!g() && !c2752uB.Z0) {
            b(AbstractC2004lX.r());
        } else {
            b(abstractC2004lX);
        }
    }

    public void a(AbstractC2004lX abstractC2004lX) {
        boolean z = h;
        if (!z && abstractC2004lX == null) {
            x1f.a();
        } else if (z || this.g != null) {
            this.g = abstractC2004lX;
        } else {
            x1f.a();
        }
    }

    public final C2543rl0 a(int i) {
        return (C2543rl0) this.c.get(i);
    }

    public B1 a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1) {
        if (!h && !c1()) {
            x1f.a();
            return null;
        }
        if (this.b.E()) {
            C1395eM c1395eMU = this.b.u();
            C1 c1 = c0333y.t;
            long jB = c1395eMU.b();
            long jA = c1395eMU.a();
            c1.getClass();
            return new C3110yS(jB, jA);
        }
        return Ak0.a;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2507rN
    public AbstractC2624sj0 a() {
        if (c1()) {
            return c().t();
        }
        return null;
    }

    public static void a(AbstractC0890Uw abstractC0890Uw) {
        C2543rl0 c2543rl0 = abstractC0890Uw.b;
        if (c2543rl0 != null) {
            c2543rl0.e();
        }
        abstractC0890Uw.c.forEach(new Consumer() { // from class: x2f
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((C2543rl0) obj).e();
            }
        });
        Set set = abstractC0890Uw.f;
        if (set != null) {
            set.forEach(new Consumer() { // from class: x2f
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((C2543rl0) obj).e();
                }
            });
            abstractC0890Uw.f = null;
        }
    }

    public final void a(C2543rl0 c2543rl0, C2543rl0 c2543rl1, Set set) {
        for (int i = 0; i < this.c.size(); i++) {
            if (c2543rl0 == this.c.get(i)) {
                this.c.set(i, c2543rl1);
                c2543rl1.d.add(this);
                c2543rl1.e = null;
            }
        }
        c2543rl0.d.remove(this);
        c2543rl0.e = null;
        if (set == null || !c1()) {
            return;
        }
        set.add(c());
    }

    public void a(int i, C2543rl0 c2543rl0) {
        C2543rl0 c2543rl1 = (C2543rl0) this.c.get(i);
        this.c.set(i, c2543rl0);
        c2543rl0.d.add(this);
        c2543rl0.e = null;
        c2543rl1.d.remove(this);
        c2543rl1.e = null;
    }

    public void a(H5 h5) {
        if (h || h5 != null) {
            this.d = h5;
        } else {
            x1f.a();
        }
    }

    public boolean a(AbstractC0890Uw abstractC0890Uw, com.android.tools.r8.ir.regalloc.f fVar) {
        if (!h && getClass() != abstractC0890Uw.getClass()) {
            x1f.a();
            return false;
        }
        if (!b(abstractC0890Uw)) {
            return false;
        }
        if (!g() && !fVar.c().Z0) {
            return true;
        }
        AbstractC2004lX abstractC2004lX = this.g;
        AbstractC2004lX abstractC2004lX2 = abstractC0890Uw.g;
        abstractC2004lX.getClass();
        return com.android.tools.r8.utils.structural.k.a(abstractC2004lX, abstractC2004lX2);
    }

    public C1678hg a(long j) {
        return null;
    }

    public final boolean a(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        return a(c0333y, b5, J1.a(c0333y, b5), C0864Tw.a);
    }

    public boolean a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1, C0864Tw c0864Tw) {
        return g();
    }

    public com.android.tools.r8.ir.optimize.E a(C0333y c0333y, C0705Nt c0705Nt) {
        if (b(c0333y, c0705Nt.i())) {
            return com.android.tools.r8.ir.optimize.E.b;
        }
        return com.android.tools.r8.ir.optimize.E.a;
    }

    public com.android.tools.r8.graph.I2 a(C0333y c0333y, Nj0 nj0) {
        C2543rl0 c2543rl0;
        if (h || (c2543rl0 = this.b) == null || !c2543rl0.t().I()) {
            throw new Kk0("Instruction without object outValue cannot compute verification type");
        }
        throw new AssertionError();
    }

    public AbstractC2624sj0 a(C0333y c0333y) {
        if (!h && this.b != null) {
            throw new AssertionError();
        }
        throw new C1345dk0("Implement type lattice evaluation for: " + U0());
    }

    public final void a(boolean z) {
        boolean z2 = h;
        if (!z2 && this.g == null) {
            x1f.a();
            return;
        }
        if (!z2 && z && getPosition().n()) {
            x1f.a();
            return;
        }
        if (z2 || !g() || A1() || (this instanceof C0646Ll) || !getPosition().n()) {
            return;
        }
        AbstractC2004lX position = getPosition();
        position.getClass();
        if (position == AbstractC2004lX.c.h) {
            return;
        }
        x1f.a();
    }
}
