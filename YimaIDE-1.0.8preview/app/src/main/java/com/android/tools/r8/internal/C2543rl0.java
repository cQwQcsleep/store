package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0230j0;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.C2543rl0;
import com.android.tools.r8.internal.PW;
import com.android.tools.r8.ir.optimize.C3242a;
import com.android.tools.r8.position.MethodPosition;
import com.android.tools.r8.utils.StringDiagnostic;
import defpackage.jh6;
import defpackage.udh;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rl0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2543rl0 implements Comparable<C2543rl0> {
    public static final C2543rl0 p = new C2543rl0(-1, AbstractC2624sj0.f(), null);
    public static final /* synthetic */ boolean q = true;
    public final int b;
    public com.android.tools.r8.ir.regalloc.c j;
    public C1395eM m;
    public C2373pl0 n;
    public AbstractC2624sj0 o;
    public AbstractC0890Uw c = null;
    public LinkedList d = new LinkedList();
    public AbstractC2554rv e = null;
    public LinkedList f = new LinkedList();
    public AbstractC2554rv g = null;
    public C2543rl0 h = null;
    public C2543rl0 i = null;
    public int k = -1;
    public boolean l = false;

    public C2543rl0(int i, AbstractC2624sj0 abstractC2624sj0, C0230j0 c0230j0) {
        if (!q && abstractC2624sj0 == null) {
            x1f.a();
            throw null;
        }
        this.b = i;
        this.n = c0230j0 != null ? new C2373pl0(c0230j0) : null;
        this.o = abstractC2624sj0;
    }

    public final boolean A() {
        return !this.f.isEmpty();
    }

    public final boolean B() {
        return b0().size() == 1;
    }

    public boolean C() {
        return !this.d.isEmpty();
    }

    public final boolean D() {
        return this.d != null;
    }

    public final boolean E() {
        return this.m != null || H();
    }

    public boolean F() {
        return c(new jh6());
    }

    public final boolean G() {
        if (!J()) {
            return false;
        }
        AbstractC1252cg abstractC1252cgK = k();
        abstractC1252cgK.getClass();
        return abstractC1252cgK instanceof C1169bg;
    }

    public boolean H() {
        return J() && k().z1();
    }

    public final boolean I() {
        return J() && k().A1();
    }

    public boolean J() {
        return this.c.r2() && !y();
    }

    public final boolean K() {
        if (!J()) {
            return false;
        }
        AbstractC1252cg abstractC1252cgK = k();
        abstractC1252cgK.getClass();
        return abstractC1252cgK instanceof C0646Ll;
    }

    public boolean L() {
        return this instanceof C1438eq;
    }

    public boolean M() {
        if (q || this.o.I()) {
            return c(new Predicate() { // from class: k8i
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((AbstractC0890Uw) obj).s1();
                }
            }) || this.o.x();
        }
        x1f.a();
        return false;
    }

    public final boolean N() {
        AbstractC0890Uw abstractC0890Uw = this.c;
        return abstractC0890Uw != null && (abstractC0890Uw instanceof C0616Kh);
    }

    public final boolean O() {
        if (!this.d.isEmpty() || !this.f.isEmpty()) {
            return true;
        }
        C2373pl0 c2373pl0 = this.n;
        return (c2373pl0 == null ? 0 : c2373pl0.b.size()) > 0;
    }

    public boolean P() {
        return this instanceof Od0;
    }

    public final boolean Q() {
        return J() && k().z1() && k().F().U2();
    }

    public void S() {
        if (q || !j()) {
            return;
        }
        x1f.a();
    }

    public boolean T() {
        boolean z;
        boolean z2 = q;
        if (!z2 && this.k < 0) {
            x1f.a();
            return false;
        }
        if (!z2 && D() && U() <= 100) {
            boolean z3 = this.k > 0;
            if (!H() || V() > 0) {
                z = true;
                break;
            }
            Iterator<AbstractC0890Uw> it = b0().iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                if (it.next().c(this)) {
                    z = true;
                    break;
                }
            }
            if (z3 != z) {
                x1f.a();
                return false;
            }
        }
        return this.k > 0;
    }

    public int U() {
        int iV = V() + W();
        C2373pl0 c2373pl0 = this.n;
        return (c2373pl0 == null ? 0 : c2373pl0.b.size()) + iV;
    }

    public final int V() {
        int size = this.f.size();
        return size <= 1 ? size : a0().size();
    }

    public final int W() {
        int size = this.d.size();
        return size <= 1 ? size : b0().size();
    }

    public final boolean X() {
        if (j()) {
            return false;
        }
        int iH2 = this.c.H2();
        if (iH2 == 5) {
            return true;
        }
        if (iH2 == 10) {
            return this.c.z().f().X();
        }
        if (iH2 == 12 || iH2 == 20) {
            return true;
        }
        if (iH2 != 29) {
            return iH2 == 15 || iH2 == 16;
        }
        return ((C2543rl0) this.c.U().c.get(0)).X();
    }

    public El0 Y() {
        return El0.a(this.o);
    }

    public final AbstractC0890Uw Z() {
        if (q || AbstractC2554rv.a(this.d).size() == 1) {
            return (AbstractC0890Uw) this.d.getFirst();
        }
        x1f.a();
        return null;
    }

    public final AbstractC2624sj0 a(Gl0 gl0) {
        if (gl0 == Gl0.f && !this.o.M()) {
            return this.o;
        }
        int iOrdinal = gl0.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal != 1) {
                if (iOrdinal != 2) {
                    if (iOrdinal != 3) {
                        if (iOrdinal != 5) {
                            if (iOrdinal != 6) {
                                if (iOrdinal != 7) {
                                    defpackage.gk0.a("Unexpected constraint: ", gl0);
                                    return null;
                                }
                                if (this.o.M()) {
                                    return this.o;
                                }
                            } else if (this.o.M() && !this.o.D()) {
                                return AbstractC2624sj0.i();
                            }
                        } else if (this.o.M() && !this.o.z()) {
                            return AbstractC2624sj0.l();
                        }
                    } else {
                        if (this.o.L()) {
                            return AbstractC2624sj0.o();
                        }
                        if (this.o.K()) {
                            return this.o;
                        }
                    }
                } else if (this.o.L() || (this.o.K() && !this.o.C())) {
                    return AbstractC2624sj0.j();
                }
            } else if (this.o.L() || (this.o.K() && !this.o.B())) {
                return AbstractC2624sj0.k();
            }
        } else {
            if (this.o.L()) {
                AbstractC0890Uw abstractC0890Uw = this.c;
                if (abstractC0890Uw == null || !abstractC0890Uw.z1()) {
                    return AbstractC2624sj0.f();
                }
                if (q || this.c.F().U2()) {
                    return AbstractC2624sj0.m();
                }
                x1f.a();
                return null;
            }
            boolean zI = this.o.I();
            AbstractC2624sj0 abstractC2624sj0 = this.o;
            if (zI) {
                return abstractC2624sj0;
            }
            if (abstractC2624sj0.t()) {
                if (q || j() || this.c.E1() || (this.c.n1() && this.c.u().e() == KN.b)) {
                    return this.o;
                }
                x1f.a();
                return null;
            }
        }
        return null;
    }

    public final Set a0() {
        AbstractC2554rv abstractC2554rv = this.g;
        if (abstractC2554rv != null) {
            return abstractC2554rv;
        }
        AbstractC2554rv abstractC2554rvA = AbstractC2554rv.a(this.f);
        this.g = abstractC2554rvA;
        return abstractC2554rvA;
    }

    public final boolean b(Set set) {
        AbstractC2624sj0 abstractC2624sj0T = t();
        abstractC2624sj0T.getClass();
        if (!(abstractC2624sj0T instanceof GA)) {
            return false;
        }
        if (!j()) {
            if (q || this.c != null) {
                return this.c.a(set);
            }
            x1f.a();
            return false;
        }
        PW pwM = m();
        if (set == null) {
            set = AbstractC2780ub0.c();
        }
        if (set.contains(pwM)) {
            return true;
        }
        set.add(pwM);
        for (C2543rl0 c2543rl0 : pwM.c0()) {
            if (!c2543rl0.b(set)) {
                c2543rl0.b(set);
                return false;
            }
        }
        return true;
    }

    public Set<AbstractC0890Uw> b0() {
        AbstractC2554rv abstractC2554rv = this.e;
        if (abstractC2554rv != null) {
            return abstractC2554rv;
        }
        AbstractC2554rv abstractC2554rvA = AbstractC2554rv.a(this.d);
        this.e = abstractC2554rvA;
        return abstractC2554rvA;
    }

    public final boolean c(C0333y c0333y) {
        if (y()) {
            return false;
        }
        if (this.o.N().e()) {
            return true;
        }
        if (this.o.w() && c0333y.g().i()) {
            return this.o.b().Q().a(c0333y.V());
        }
        return false;
    }

    @Override // java.lang.Comparable
    public final int compareTo(C2543rl0 c2543rl0) {
        return Integer.compare(this.b, c2543rl0.b);
    }

    public final void d() {
        this.d.clear();
        this.e = null;
        this.f.clear();
        this.g = null;
        C2373pl0 c2373pl0 = this.n;
        if (c2373pl0 != null) {
            c2373pl0.b.clear();
        }
    }

    public void e(C2543rl0 c2543rl0) {
        if (this == c2543rl0) {
            return;
        }
        Iterator it = a0().iterator();
        while (it.hasNext()) {
            ((PW) it.next()).a(this, c2543rl0, (Set) null);
        }
        this.f.clear();
        this.g = null;
    }

    public void f() {
        boolean z;
        if (!q && this.k >= 0) {
            x1f.a();
            return;
        }
        if (H() && V() <= 0) {
            Iterator<AbstractC0890Uw> it = b0().iterator();
            do {
                if (!it.hasNext()) {
                    z = false;
                }
            } while (!it.next().c(this));
            z = true;
        } else {
            z = true;
        }
        a(z);
    }

    public final Set g() {
        C2373pl0 c2373pl0 = this.n;
        if (c2373pl0 == null) {
            return null;
        }
        return Collections.unmodifiableSet(c2373pl0.b);
    }

    public C2543rl0 h() {
        return a(C0953Xh.a, MX.c);
    }

    public final int hashCode() {
        return this.b;
    }

    public H5 i() {
        return this.c.i();
    }

    public boolean j() {
        return false;
    }

    public final AbstractC1252cg k() {
        if (q || J()) {
            return this.c.X0();
        }
        x1f.a();
        return null;
    }

    public PW m() {
        return null;
    }

    public final Set n() {
        C2373pl0 c2373pl0 = this.n;
        return c2373pl0 == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(c2373pl0.b);
    }

    public final AbstractC0890Uw p() {
        if (q || !j()) {
            return this.c;
        }
        x1f.a();
        return null;
    }

    public final com.android.tools.r8.ir.regalloc.c q() {
        return this.j;
    }

    public final C0230j0 r() {
        C2373pl0 c2373pl0 = this.n;
        if (c2373pl0 == null) {
            return null;
        }
        return c2373pl0.a;
    }

    public int s() {
        return this.b;
    }

    public AbstractC2624sj0 t() {
        return this.o;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("v");
        sb.append(this.b);
        AbstractC0890Uw abstractC0890Uw = this.c;
        boolean z = abstractC0890Uw != null && abstractC0890Uw.z1();
        if (z || y()) {
            sb.append("(");
            if (z && this.c.F().c1()) {
                C1678hg c1678hgF = this.c.F();
                if (c1678hgF.a().K()) {
                    final C2543rl0 c2543rl0C = c1678hgF.c();
                    int iP2 = (int) c1678hgF.P2();
                    if ((c2543rl0C.f == null || !c2543rl0C.A()) && c2543rl0C.e != null && c2543rl0C.b0().stream().allMatch(new Predicate() { // from class: g8i
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return C2543rl0.a(this.b, (AbstractC0890Uw) obj);
                        }
                    })) {
                        sb.append("0b");
                        sb.append(Integer.toBinaryString(iP2));
                    } else {
                        sb.append(iP2);
                    }
                } else {
                    sb.append(c1678hgF.P2());
                }
            }
            if (z && y()) {
                sb.append(", ");
            }
            if (y()) {
                sb.append(r());
            }
            sb.append(")");
        }
        C1395eM c1395eM = this.m;
        if (c1395eM != null) {
            sb.append(c1395eM);
        }
        return sb.toString();
    }

    public final C1395eM u() {
        if (!H()) {
            return this.m;
        }
        if (this.o.K()) {
            int iN2 = k().F().N2();
            return new C1395eM(iN2, iN2);
        }
        if (q || this.o.M()) {
            long jO2 = k().F().O2();
            return new C1395eM(jO2, jO2);
        }
        x1f.a();
        return null;
    }

    public final boolean v() {
        return C() || A() || x();
    }

    public boolean w() {
        return this.c.Z0();
    }

    public final boolean x() {
        C2373pl0 c2373pl0 = this.n;
        return (c2373pl0 == null || c2373pl0.b.isEmpty()) ? false : true;
    }

    public final boolean y() {
        return r() != null;
    }

    public final boolean z() {
        return C() || A();
    }

    public final /* synthetic */ boolean d(C2543rl0 c2543rl0) {
        return c2543rl0 == this;
    }

    public final void e() {
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        C2373pl0 c2373pl0 = this.n;
        if (c2373pl0 != null) {
            c2373pl0.b = null;
        }
    }

    public C1438eq c() {
        return null;
    }

    public void f(C2543rl0 c2543rl0) {
        a(c2543rl0, (Set) null);
    }

    public final void c(AbstractC0890Uw abstractC0890Uw) {
        Set set;
        C2373pl0 c2373pl0 = this.n;
        if (c2373pl0 != null && (set = c2373pl0.b) != null) {
            set.remove(abstractC0890Uw);
        } else {
            if (q) {
                return;
            }
            x1f.a();
        }
    }

    public boolean c(Predicate<AbstractC0890Uw> predicate) {
        return predicate.test(this.c);
    }

    public final boolean b(Predicate predicate) {
        Iterator<AbstractC0890Uw> it = b0().iterator();
        while (it.hasNext()) {
            if (predicate.test(it.next())) {
                return true;
            }
        }
        return false;
    }

    public final void b(final AbstractC0890Uw abstractC0890Uw) {
        this.d.removeIf(new Predicate() { // from class: h8i
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C2543rl0.a(abstractC0890Uw, (AbstractC0890Uw) obj);
            }
        });
        this.e = null;
    }

    public final void b(PW pw) {
        this.f.remove(pw);
        this.g = null;
    }

    public final Set b() {
        C0953Xh c0953Xh = C0953Xh.a;
        Set setC = AbstractC2780ub0.c();
        a(c0953Xh, this, setC);
        return setC;
    }

    public AbstractC2624sj0 b(C0333y c0333y) {
        AbstractC2624sj0 abstractC2624sj0B;
        C2543rl0 c2543rl0A = a(new Predicate() { // from class: l8i
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((C2543rl0) obj).c(new khg());
            }
        });
        C2543rl0 c2543rl0H = h();
        if (c2543rl0A != null) {
            abstractC2624sj0B = c2543rl0A.p().x().i.a().n();
        } else if (c2543rl0H.j()) {
            abstractC2624sj0B = c2543rl0H.b(c0333y);
        } else {
            abstractC2624sj0B = this.o;
        }
        if (!this.o.x() || !abstractC2624sj0B.F()) {
            return abstractC2624sj0B;
        }
        if (q || abstractC2624sj0B.I()) {
            return abstractC2624sj0B.d().P();
        }
        x1f.a();
        return null;
    }

    public static /* synthetic */ boolean a(AbstractC0890Uw abstractC0890Uw, AbstractC0890Uw abstractC0890Uw2) {
        return abstractC0890Uw2 == abstractC0890Uw;
    }

    public void a(Gl0 gl0, com.android.tools.r8.graph.B5 b5, C2742u50 c2742u50) {
        AbstractC2624sj0 abstractC2624sj0A = a(gl0);
        if (abstractC2624sj0A != null) {
            if (VU.a(abstractC2624sj0A, (Object) this.o)) {
                a(abstractC2624sj0A);
                return;
            }
            return;
        }
        c2742u50.a(new StringDiagnostic("Cannot constrain type: " + this.o + " for value: " + this + " by constraint: " + gl0, b5.getOrigin(), new MethodPosition(b5.A())));
        throw null;
    }

    public final C0491Fm a(C0333y c0333y) {
        if (AbstractC0439Dm.a || t().I()) {
            AbstractC2624sj0 abstractC2624sj0B = b(c0333y);
            return AbstractC0439Dm.a(c0333y, abstractC2624sj0B, a(c0333y, abstractC2624sj0B, abstractC2624sj0B.N()));
        }
        x1f.a();
        return null;
    }

    public static /* synthetic */ boolean a(PW pw, PW pw2) {
        return pw2 == pw;
    }

    public final void a(El0 el0) {
        Gl0 gl0A = Gl0.a(el0);
        if (q || a(gl0A) != null) {
            return;
        }
        x1f.a();
    }

    public final C2543rl0 a(InterfaceC1115b2 interfaceC1115b2, Predicate predicate) {
        C2543rl0 c2543rl0A;
        if (!q && predicate == null) {
            x1f.a();
            return null;
        }
        Set setC = AbstractC2780ub0.c();
        while (!this.j() && !predicate.test(this)) {
            AbstractC0890Uw abstractC0890Uw = this.c;
            if (interfaceC1115b2.b(abstractC0890Uw)) {
                c2543rl0A = interfaceC1115b2.a(abstractC0890Uw);
                if (!q && !setC.add(c2543rl0A)) {
                    x1f.a();
                    return null;
                }
            } else {
                c2543rl0A = this;
            }
            if (c2543rl0A == this) {
                if (!q && !c2543rl0A.j()) {
                    AbstractC0890Uw abstractC0890Uw2 = c2543rl0A.c;
                    abstractC0890Uw2.getClass();
                    if (abstractC0890Uw2 instanceof C3165z4) {
                        x1f.a();
                        return null;
                    }
                }
                return c2543rl0A;
            }
            this = c2543rl0A;
        }
        return this;
    }

    public final C2543rl0 a(Predicate predicate) {
        C2543rl0 c2543rl0A = a(C0953Xh.a, predicate);
        if (predicate.test(c2543rl0A)) {
            return c2543rl0A;
        }
        return null;
    }

    public final void a(C0230j0 c0230j0) {
        boolean z = q;
        if (!z && c0230j0 == null) {
            x1f.a();
        } else if (z || this.n == null) {
            this.n = new C2373pl0(c0230j0);
        } else {
            x1f.a();
        }
    }

    public void a(AbstractC0890Uw abstractC0890Uw) {
        if (!q && abstractC0890Uw == null) {
            x1f.a();
            return;
        }
        this.n.b.add(abstractC0890Uw);
        if (!AbstractC0890Uw.h) {
            abstractC0890Uw.getClass();
            if (!y()) {
                x1f.a();
                return;
            }
        }
        if (abstractC0890Uw.f == null) {
            abstractC0890Uw.f = AbstractC2780ub0.c();
        }
        abstractC0890Uw.f.add(this);
    }

    public final boolean a(H5 h5) {
        if (A() || x()) {
            return false;
        }
        Iterator<AbstractC0890Uw> it = b0().iterator();
        while (it.hasNext()) {
            if (it.next().i() != h5) {
                return false;
            }
        }
        return true;
    }

    public static void a(InterfaceC1115b2 interfaceC1115b2, C2543rl0 c2543rl0, Set set) {
        for (AbstractC0890Uw abstractC0890Uw : c2543rl0.b0()) {
            if (set.add(abstractC0890Uw) && interfaceC1115b2.b(abstractC0890Uw)) {
                a(interfaceC1115b2, abstractC0890Uw.c(), set);
            }
        }
    }

    public final void a(final PW pw) {
        this.f.removeIf(new Predicate() { // from class: i8i
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C2543rl0.a(pw, (PW) obj);
            }
        });
        this.g = null;
    }

    public final C3242a a() {
        final C3242a c3242a = new C3242a();
        Consumer consumer = new Consumer() { // from class: m8i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c3242a.a((C2543rl0) obj);
            }
        };
        for (AbstractC0890Uw abstractC0890Uw : b0()) {
            if (abstractC0890Uw.c1()) {
                consumer.accept(abstractC0890Uw.c());
            }
        }
        a0().forEach(consumer);
        return c3242a;
    }

    public final void a(Set set) {
        Objects.requireNonNull(set);
        udh udhVar = new udh(set);
        for (AbstractC0890Uw abstractC0890Uw : b0()) {
            if (abstractC0890Uw.c1()) {
                udhVar.accept(abstractC0890Uw.c());
            }
        }
        a0().forEach(udhVar);
    }

    public final void a(C2543rl0 c2543rl0, Set set) {
        if (this == c2543rl0) {
            return;
        }
        Iterator<AbstractC0890Uw> it = b0().iterator();
        while (it.hasNext()) {
            it.next().a(this, c2543rl0, set);
        }
        Iterator it2 = a0().iterator();
        while (it2.hasNext()) {
            ((PW) it2.next()).a(this, c2543rl0, set);
        }
        C2373pl0 c2373pl0 = this.n;
        if (c2373pl0 != null) {
            Iterator it3 = c2373pl0.b.iterator();
            while (it3.hasNext()) {
                ((AbstractC0890Uw) it3.next()).e(this, c2543rl0);
            }
            this.n.b.clear();
        }
        d();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(C2543rl0 c2543rl0, Set set, Map map, C3242a c3242a) {
        if (this == c2543rl0) {
            return;
        }
        for (AbstractC0890Uw abstractC0890Uw : b0()) {
            if (set.contains(abstractC0890Uw)) {
                b(abstractC0890Uw);
                abstractC0890Uw.a(this, c2543rl0, c3242a);
            }
        }
        Set setKeySet = map.keySet();
        for (PW pw : a0()) {
            if (setKeySet.contains(pw)) {
                long jCount = pw.c0().stream().filter(new Predicate() { // from class: f8i
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return this.b.d((C2543rl0) obj);
                    }
                }).count();
                InterfaceC1981lA interfaceC1981lA = (InterfaceC1981lA) map.get(pw);
                if (jCount == interfaceC1981lA.size()) {
                    a(pw);
                }
                InterfaceC2067mA it = interfaceC1981lA.iterator();
                while (it.hasNext()) {
                    int iIntValue = ((Integer) it.next()).intValue();
                    if (!q && ((C2543rl0) pw.s.get(iIntValue)) != this) {
                        x1f.a();
                        return;
                    }
                    pw.a(iIntValue, c2543rl0, c3242a);
                }
            }
        }
        C2373pl0 c2373pl0 = this.n;
        if (c2373pl0 != null) {
            Iterator it2 = c2373pl0.b.iterator();
            while (it2.hasNext()) {
                AbstractC0890Uw abstractC0890Uw2 = (AbstractC0890Uw) it2.next();
                if (set.contains(abstractC0890Uw2)) {
                    abstractC0890Uw2.e(this, c2543rl0);
                    it2.remove();
                }
            }
        }
    }

    public void a(boolean z) {
        int i;
        if (!q && (i = this.k) != -1) {
            if ((i > 0) != z) {
                x1f.a();
                return;
            }
        }
        this.k = z ? 1 : 0;
    }

    public final int a(C2543rl0 c2543rl0) {
        return Integer.compare(this.b, c2543rl0.b);
    }

    public static boolean a(C2543rl0 c2543rl0, AbstractC0890Uw abstractC0890Uw) {
        abstractC0890Uw.getClass();
        if ((abstractC0890Uw instanceof C2308p2) || (abstractC0890Uw instanceof C2772uV)) {
            return true;
        }
        return (((abstractC0890Uw instanceof C3037xb0) || (abstractC0890Uw instanceof Bb0) || (abstractC0890Uw instanceof C1603gl0)) && c2543rl0 == abstractC0890Uw.T0()) || (abstractC0890Uw instanceof Ym0);
    }

    public final B1 a(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        return a(c0333y, b5, J1.a);
    }

    public final B1 a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1) {
        if (!c0333y.o()) {
            return Ak0.a;
        }
        if (t().N().e()) {
            C1 c1 = c0333y.t;
            AbstractC2624sj0 abstractC2624sj0T = t();
            c1.getClass();
            if (C1.f || abstractC2624sj0T.I()) {
                return C2440qc0.b;
            }
            x1f.a();
            return null;
        }
        C2543rl0 c2543rl0H = h();
        if (c2543rl0H.j()) {
            return Ak0.a;
        }
        return c2543rl0H.c.a(c0333y, b5, j1);
    }

    public final void a(AbstractC2624sj0 abstractC2624sj0) {
        if (q || abstractC2624sj0 != null) {
            this.o = abstractC2624sj0;
        } else {
            x1f.a();
        }
    }

    public final void a(C0333y c0333y, AbstractC2624sj0 abstractC2624sj0) {
        if (!q && ((c0333y.M().u1.n0 || c0333y.o()) && !this.o.a(abstractC2624sj0, (C0333y<?>) c0333y))) {
            throw new AssertionError("During WIDENING, " + abstractC2624sj0 + " < " + this.o + " at " + (j() ? m().e0() : this.c.toString()));
        }
        a(abstractC2624sj0);
    }

    public final void a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, AbstractC2624sj0 abstractC2624sj0) {
        if (!q && ((c0333y.M().u1.n0 || c0333y.o()) && this.o.c(abstractC2624sj0, c0333y))) {
            throw new AssertionError("During NARROWING, " + this.o + " < " + abstractC2624sj0 + " at " + (j() ? m().e0() : this.c.toString()) + " (context: " + b5 + ")");
        }
        a(abstractC2624sj0);
    }

    public final C2441qd a(C0333y c0333y, AbstractC2624sj0 abstractC2624sj0, C2427qS c2427qS) {
        C2441qd c2441qdB;
        if (abstractC2624sj0 == null) {
            abstractC2624sj0 = t();
        }
        if (abstractC2624sj0.w()) {
            C2441qd c2441qdB2 = abstractC2624sj0.b();
            com.android.tools.r8.graph.E0 e0D = c0333y.d(c2441qdB2.Q());
            if (e0D != null && e0D.c(c0333y)) {
                C2427qS c2427qS2 = c2441qdB2.b;
                c2427qS2.getClass();
                C2427qS c2427qS3 = C2427qS.c;
                if (c2427qS2 != c2427qS3) {
                    c2427qS = (c2427qS == c2427qS3 || c2427qS2 == c2427qS) ? c2427qS2 : C2427qS.d;
                }
                return c2441qdB2.a(c2427qS);
            }
        }
        C2543rl0 c2543rl0H = h();
        if (c2543rl0H.j()) {
            return null;
        }
        AbstractC0890Uw abstractC0890UwP = c2543rl0H.p();
        if (abstractC0890UwP.o2()) {
            com.android.tools.r8.graph.I2 i2L2 = abstractC0890UwP.t0().L2();
            com.android.tools.r8.graph.E0 e0D2 = c0333y.d(i2L2);
            if (e0D2 == null || e0D2.isInterface()) {
                return null;
            }
            if (!q) {
                c2427qS.getClass();
                if (c2427qS == C2427qS.d) {
                    x1f.a();
                    return null;
                }
            }
            return AbstractC2624sj0.a(i2L2, C2427qS.b(), (C0333y<?>) c0333y).b();
        }
        C2543rl0 c2543rl0A = a(new Predicate() { // from class: j8i
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((C2543rl0) obj).p().r1();
            }
        });
        if (c2543rl0A == null || (c2441qdB = c2543rl0A.p().x().i.a().b()) == null) {
            return null;
        }
        C2427qS c2427qSN = t().N();
        C2427qS c2427qS4 = c2441qdB.b;
        c2427qS4.getClass();
        C2427qS c2427qS5 = C2427qS.c;
        if (c2427qS4 != c2427qS5) {
            c2427qSN = (c2427qSN == c2427qS5 || c2427qS4 == c2427qSN) ? c2427qS4 : C2427qS.d;
        }
        C2441qd c2441qdC = c2441qdB.a(c2427qSN);
        if (q || c2441qdC.b.a(c2427qS) == c2427qS) {
            return c2441qdC;
        }
        x1f.a();
        return null;
    }
}
