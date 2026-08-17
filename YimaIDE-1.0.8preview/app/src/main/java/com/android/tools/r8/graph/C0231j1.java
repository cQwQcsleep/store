package com.android.tools.r8.graph;

import com.android.tools.r8.graph.AbstractC0223i0;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0285r0;
import com.android.tools.r8.graph.F4;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC2004lX;
import com.android.tools.r8.internal.AbstractC2173nV;
import com.android.tools.r8.internal.AbstractC2335pL;
import com.android.tools.r8.internal.AbstractC2624sj0;
import com.android.tools.r8.internal.AbstractC2973wm0;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.C0479Fa;
import com.android.tools.r8.internal.C0822Sg;
import com.android.tools.r8.internal.C0842Ta;
import com.android.tools.r8.internal.C0884Uq;
import com.android.tools.r8.internal.C0946Xa;
import com.android.tools.r8.internal.C1022Zy;
import com.android.tools.r8.internal.C1159bb;
import com.android.tools.r8.internal.C1213c9;
import com.android.tools.r8.internal.C1345dk0;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.internal.C2427qS;
import com.android.tools.r8.internal.C2441qd;
import com.android.tools.r8.internal.C2543rl0;
import com.android.tools.r8.internal.E6;
import com.android.tools.r8.internal.El0;
import com.android.tools.r8.internal.EnumC0858Tq;
import com.android.tools.r8.internal.G9;
import com.android.tools.r8.internal.InterfaceC2045lz;
import com.android.tools.r8.internal.Kk0;
import com.android.tools.r8.internal.LK;
import com.android.tools.r8.internal.P9;
import com.android.tools.r8.internal.SK;
import com.android.tools.r8.internal.SW;
import com.android.tools.r8.internal.VU;
import com.android.tools.r8.internal.W9;
import com.android.tools.r8.internal.XK;
import com.android.tools.r8.ir.optimize.AbstractC3247c0;
import com.android.tools.r8.ir.optimize.info.C3263d;
import com.android.tools.r8.ir.optimize.info.w;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.utils.structural.A;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import com.android.tools.r8.utils.structural.o;
import com.sun.jna.platform.linux.Fcntl;
import defpackage.hih;
import defpackage.pah;
import defpackage.s26;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.graph.j1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0231j1 extends AbstractC0217h1<C0231j1, C0322w2> implements com.android.tools.r8.utils.structural.x<C0231j1> {
    public static final C0231j1[] u = new C0231j1[0];
    public static final C0231j1 v;
    public static final C1022Zy w;
    public static final /* synthetic */ boolean x = true;
    public final F4 g;
    public final boolean h;
    public C0304t5 i;
    public AbstractC0223i0 j;
    public C0322w2 k;
    public EnumC0238k1 l;
    public com.android.tools.r8.ir.optimize.info.h m;
    public C1159bb n;
    public com.android.tools.r8.androidapi.f o;
    public com.android.tools.r8.kotlin.f0 p;
    public B3.g q;
    public AbstractC2173nV r;
    public InterfaceC2045lz s;
    public boolean t;

    static {
        F4 f4E = F4.e(0);
        B3.g gVarD = B3.g.d();
        C0306u0 c0306u0O0 = C0306u0.o0();
        C0304t5 c0304t5 = C0304t5.d;
        int i = com.android.tools.r8.androidapi.f.a;
        com.android.tools.r8.androidapi.g gVar = com.android.tools.r8.androidapi.g.b;
        v = new C0231j1(null, f4E, gVarD, c0306u0O0, c0304t5, null, null, false, gVar, gVar, null, C3263d.b, false);
        w = new C1022Zy(0);
    }

    public C0231j1(C0322w2 c0322w2, F4 f4, B3.g gVar, C0306u0 c0306u0, C0304t5 c0304t5, AbstractC0223i0 abstractC0223i0, C0322w2 c0322w3, boolean z, com.android.tools.r8.androidapi.f fVar, com.android.tools.r8.androidapi.f fVar2, C1159bb c1159bb, com.android.tools.r8.ir.optimize.info.h hVar, boolean z2) {
        AbstractC0223i0 abstractC0223i1;
        super(c0322w2, c0306u0, z, fVar);
        this.l = EnumC0238k1.b;
        this.p = com.android.tools.r8.kotlin.d0.a;
        this.r = AbstractC2173nV.c;
        this.s = w;
        this.t = false;
        this.g = f4;
        this.h = z2;
        this.q = gVar;
        this.i = c0304t5;
        this.j = abstractC0223i0;
        this.k = c0322w3;
        this.n = c1159bb;
        this.o = fVar2;
        Objects.requireNonNull(hVar);
        this.m = hVar;
        boolean z3 = x;
        if (!z3 && f4 == null) {
            x1f.a();
            throw null;
        }
        if (!z3 && abstractC0223i0 != null && F1()) {
            x1f.a();
            throw null;
        }
        if (!z3 && (abstractC0223i1 = this.j) != null && !abstractC0223i1.F0() && j1()) {
            x1f.a();
            throw null;
        }
        if (!z3 && c0304t5 == null) {
            x1f.a();
            throw null;
        }
        if (!z3 && fVar == null) {
            x1f.a();
            throw null;
        }
        if (z3 || fVar2 != null) {
            return;
        }
        x1f.a();
        throw null;
    }

    public static a G1() {
        return new a(true);
    }

    public static a N0() {
        return new a(false);
    }

    public static /* synthetic */ C0285r0 c(BiFunction biFunction, C0285r0 c0285r0) {
        return (C0285r0) biFunction.apply(c0285r0, EnumC0272p0.e);
    }

    public static a e(C0231j1 c0231j1) {
        return new a(c0231j1, true);
    }

    @Override // com.android.tools.r8.graph.AbstractC0217h1, com.android.tools.r8.graph.AbstractC0175b1
    public final boolean A0() {
        O0();
        return z0();
    }

    public boolean A1() {
        O0();
        return this.g.p();
    }

    @Override // com.android.tools.r8.graph.AbstractC0217h1
    public final void B() {
        this.q = B3.g.d();
    }

    @Override // com.android.tools.r8.graph.AbstractC0217h1
    public final void B0() {
        this.p = com.android.tools.r8.kotlin.d0.a;
    }

    public boolean B1() {
        O0();
        return (this.g.n() || this.g.L()) ? false : true;
    }

    @Override // com.android.tools.r8.graph.AbstractC0217h1
    public final com.android.tools.r8.androidapi.f C0() {
        com.android.tools.r8.androidapi.f fVar = this.e;
        return F1() ? fVar : fVar.e(this.o);
    }

    public final void C1() {
        O0();
        this.l = EnumC0238k1.b;
    }

    public String D1() {
        O0();
        return getReference().E0();
    }

    public I2 E1() {
        return getReference().i.e;
    }

    public boolean F1() {
        return this.g.J() || this.g.M();
    }

    @Override // com.android.tools.r8.graph.AbstractC0217h1
    public final com.android.tools.r8.ir.optimize.info.g G0() {
        O0();
        return this.m;
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1
    public final void J() {
        this.b = C0306u0.o0();
        this.i = C0304t5.d;
    }

    public final boolean L0() {
        return this.g.F();
    }

    public final boolean M0() {
        return !this.g.F();
    }

    public final void O0() {
        if (x || !this.t) {
            return;
        }
        x1f.a();
    }

    public String P0() {
        O0();
        AbstractC0223i0 abstractC0223i0 = this.j;
        return abstractC0223i0 == null ? "<no code>" : abstractC0223i0.a(this, C1581ga0.b);
    }

    public String Q0() {
        O0();
        return a(AbstractC3345r0.a());
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1, com.android.tools.r8.graph.InterfaceC0265o0
    /* JADX INFO: renamed from: R0, reason: merged with bridge method [inline-methods] */
    public F4 getAccessFlags() {
        return this.g;
    }

    public final com.android.tools.r8.androidapi.f S0() {
        return this.o;
    }

    public final C1159bb T0() {
        O0();
        if (x || this.n != null) {
            return this.n;
        }
        x1f.a();
        return null;
    }

    public AbstractC0223i0 U0() {
        O0();
        return this.j;
    }

    public final InterfaceC0170a3 V0() {
        AbstractC0223i0 abstractC0223i0U0 = U0();
        if (!x && abstractC0223i0U0 != null && !abstractC0223i0U0.z0()) {
            x1f.a();
            return null;
        }
        if (abstractC0223i0U0 == null) {
            return null;
        }
        return abstractC0223i0U0.p0();
    }

    @Override // com.android.tools.r8.graph.AbstractC0217h1
    public final com.android.tools.r8.kotlin.P W() {
        return this.p;
    }

    public B3.g W0() {
        return this.q;
    }

    public final com.android.tools.r8.kotlin.f0 X0() {
        return this.p;
    }

    public synchronized com.android.tools.r8.ir.optimize.info.w Y0() {
        com.android.tools.r8.ir.optimize.info.w wVar;
        O0();
        wVar = (com.android.tools.r8.ir.optimize.info.w) this.m.b();
        this.m = wVar;
        return wVar;
    }

    public final int Z0() {
        return getReference().a(z0());
    }

    public final void a(final C0333y c0333y, final C0322w2 c0322w2, a aVar) {
        AbstractC0223i0 abstractC0223i0A;
        C0884Uq c0884UqA = new C0884Uq(c0333y.a()).a(z0(), new Consumer() { // from class: xah
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(c0322w2, c0333y, (C0884Uq) obj);
            }
        }, new Consumer() { // from class: yah
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.b(c0322w2, c0333y, (C0884Uq) obj);
            }
        });
        if (c0333y.Q().d()) {
            if (!C0884Uq.k) {
                c0884UqA.c();
            }
            if (c0884UqA.h || c0884UqA.i || c0884UqA.d || c0884UqA.j != null) {
                throw new C1345dk0();
            }
            EnumC0858Tq enumC0858Tq = c0884UqA.f;
            if (enumC0858Tq != EnumC0858Tq.b && enumC0858Tq != EnumC0858Tq.e) {
                throw new C1345dk0();
            }
            if (enumC0858Tq == EnumC0858Tq.e) {
                I2 i2W0 = c0884UqA.b.w0();
                I2 i2W1 = c0884UqA.c.w0();
                i2W0.getClass();
                if (I2.a(i2W0, i2W1)) {
                    throw new C1345dk0();
                }
            }
            SW sw = new SW();
            LK lkA = SK.a(c0884UqA.b, true, (XK) sw, c0333y.M());
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (i < c0884UqA.b.a(c0884UqA.e)) {
                I2 i2A = c0884UqA.b.a(i, c0884UqA.e);
                C2427qS c2427qSH = (i != 0 || c0884UqA.e) ? C2427qS.h() : C2427qS.b();
                i2A.getClass();
                C2543rl0 c2543rl0 = new C2543rl0(i, AbstractC2624sj0.a(i2A, c2427qSH, (C0333y<?>) c0333y), null);
                arrayList.add(c2543rl0);
                sw.b.b(c2543rl0.s(), c2543rl0);
                i2A.J0();
                lkA.a(i);
                i++;
            }
            EnumC0858Tq enumC0858Tq2 = c0884UqA.f;
            EnumC0858Tq enumC0858Tq3 = EnumC0858Tq.b;
            C0322w2 c0322w3 = c0884UqA.c;
            if (enumC0858Tq2 == enumC0858Tq3) {
                lkA.b(c0322w3, arrayList, c0884UqA.g.booleanValue());
            } else {
                boolean zBooleanValue = c0884UqA.g.booleanValue();
                lkA.getClass();
                lkA.a(zBooleanValue ? 208 : 207, Collections.singletonList(c0322w3), arrayList);
            }
            if (c0884UqA.b.D0().W0()) {
                lkA.b();
            } else {
                C2543rl0 c2543rl1 = new C2543rl0(i, c0884UqA.b.D0().b((C0333y<?>) c0333y), null);
                sw.b.b(c2543rl1.s(), c2543rl1);
                lkA.getClass();
                lkA.a(176, Collections.EMPTY_LIST, Collections.singletonList(c2543rl1));
            }
            abstractC0223i0A = lkA.d();
        } else {
            abstractC0223i0A = c0884UqA.a();
        }
        aVar.a(abstractC0223i0A).b(new Consumer() { // from class: zah
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((F4) obj).Q();
            }
        });
    }

    public final com.android.tools.r8.ir.optimize.info.h a1() {
        O0();
        return this.m;
    }

    public final boolean b(C0333y c0333y, C0231j1 c0231j1) {
        boolean z = x;
        if (!z && getReference().C0() != c0231j1.getReference().C0()) {
            x1f.a();
            return false;
        }
        if (!z && !c0333y.a(E0(), c0231j1.E0()).d() && !c0333y.a(c0231j1.E0(), E0()).d()) {
            x1f.a();
            return false;
        }
        F4 accessFlags = getAccessFlags();
        if (accessFlags.e() < c0231j1.getAccessFlags().e()) {
            return false;
        }
        if (accessFlags.i()) {
            return E0() == c0231j1.E0();
        }
        if (accessFlags.m()) {
            return true;
        }
        if (z || accessFlags.g() || accessFlags.l()) {
            return E0().E0().equals(c0231j1.E0().E0());
        }
        x1f.a();
        return false;
    }

    public C0304t5 b1() {
        return this.i;
    }

    public K2 c1() {
        return getReference().B0();
    }

    public final AbstractC2004lX d1() {
        if (!j1()) {
            return null;
        }
        if (!x && !I0()) {
            x1f.a();
            return null;
        }
        AbstractC2004lX.c.a aVarA = AbstractC2004lX.c.s().a(getReference());
        aVarA.e = I0();
        return a((AbstractC2004lX) ((AbstractC2004lX.c.a) aVarA.c()).a(0).a());
    }

    public E2 e1() {
        return getReference().C0();
    }

    public final void f(com.android.tools.r8.androidapi.f fVar) {
        if (x || fVar != null) {
            this.o = fVar;
        } else {
            x1f.a();
        }
    }

    public I2 f1() {
        return getReference().D0();
    }

    public final B2 g1() {
        return new C0343z2(getReference());
    }

    public final boolean h1() {
        O0();
        return this.n != null;
    }

    public boolean i1() {
        return this.j != null;
    }

    public I2 j(int i) {
        return getReference().k(i);
    }

    public final boolean j1() {
        return this.k != null;
    }

    public C0306u0 k(int i) {
        return b1().j(i);
    }

    public boolean k1() {
        return this.g.J();
    }

    public boolean l1() {
        return this.g.K();
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public String m0() {
        O0();
        return getReference().m0();
    }

    public boolean m1() {
        O0();
        return this.g.L() && this.g.n();
    }

    public final boolean n1() {
        if (z0() || k1()) {
            return false;
        }
        O0();
        return (this.g.i() || q1()) ? false : true;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: bbh
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a2) {
                C0231j1.a(a2);
            }
        };
    }

    public boolean o1() {
        return this.g.f();
    }

    public boolean p1() {
        O0();
        return q1() || m1();
    }

    public boolean q1() {
        O0();
        return this.g.L() && !this.g.n();
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1
    public final C0231j1 r0() {
        O0();
        return this;
    }

    public AbstractC2173nV r1() {
        return u1() ? this.r : AbstractC2173nV.b;
    }

    public final boolean s1() {
        return this.g.M();
    }

    public final boolean t1() {
        O0();
        return (this.g.J() || this.g.M()) ? false : true;
    }

    public String toString() {
        return m0();
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1
    public final boolean u0() {
        return t0() || !b1().isEmpty();
    }

    public boolean u1() {
        O0();
        O0();
        return !this.g.i() && B1();
    }

    public final boolean v1() {
        O0();
        return this.g.i();
    }

    public final boolean w1() {
        O0();
        return this.l != EnumC0238k1.b;
    }

    public final boolean x1() {
        O0();
        return this.g.l();
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1
    public final boolean y0() {
        O0();
        return true;
    }

    public boolean y1() {
        O0();
        return this.g.m();
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1
    public boolean z0() {
        O0();
        return this.g.n();
    }

    public final boolean z1() {
        return this.g.O();
    }

    /* JADX INFO: renamed from: com.android.tools.r8.graph.j1$a */
    public static class a {
        public static final /* synthetic */ boolean u = true;
        public F4 a;
        public AbstractC0223i0 b;
        public C0322w2 c;
        public B3.g d;
        public C0306u0 e;
        public AbstractC2173nV f;
        public C0304t5 g;
        public EnumC0238k1 h;
        public com.android.tools.r8.ir.optimize.info.h i;
        public final com.android.tools.r8.kotlin.f0 j;
        public C1159bb k;
        public com.android.tools.r8.androidapi.f l;
        public com.android.tools.r8.androidapi.f m;
        public final boolean n;
        public boolean o;
        public C0322w2 p;
        public boolean q;
        public boolean r;
        public boolean s;
        public Consumer t;

        public a(C0231j1 c0231j1, boolean z) {
            com.android.tools.r8.ir.optimize.info.h wVar;
            this.d = B3.g.d();
            this.e = C0306u0.o0();
            this.f = AbstractC2173nV.c;
            C0304t5 c0304t5 = C0304t5.d;
            this.g = c0304t5;
            this.h = EnumC0238k1.b;
            this.i = C3263d.b;
            this.j = com.android.tools.r8.kotlin.d0.a;
            this.k = null;
            int i = com.android.tools.r8.androidapi.f.a;
            com.android.tools.r8.androidapi.g gVar = com.android.tools.r8.androidapi.g.b;
            this.l = gVar;
            this.m = gVar;
            this.o = false;
            this.p = null;
            this.q = true;
            this.r = true;
            this.s = true;
            this.t = C0822Sg.b();
            this.c = c0231j1.getReference();
            F4 accessFlags = c0231j1.getAccessFlags();
            this.a = new F4(accessFlags.b, accessFlags.c);
            this.d = c0231j1.W0();
            this.e = c0231j1.n0();
            this.b = c0231j1.U0();
            this.p = c0231j1.k;
            this.l = c0231j1.e;
            this.m = c0231j1.o;
            c0231j1.O0();
            if (c0231j1.m.d()) {
                c0231j1.O0();
                com.android.tools.r8.ir.optimize.info.w wVarA = c0231j1.m.a();
                wVarA.getClass();
                wVar = new com.android.tools.r8.ir.optimize.info.w(wVarA);
            } else {
                c0231j1.O0();
                wVar = c0231j1.m;
            }
            this.i = wVar;
            this.j = c0231j1.p;
            this.k = c0231j1.n;
            this.n = z;
            this.o = c0231j1.h;
            if (c0231j1.b1().isEmpty() || c0231j1.b1().size() == c0231j1.c1().size()) {
                this.g = c0231j1.b1();
                return;
            }
            C0304t5 c0304t5B1 = c0231j1.b1();
            int size = c0231j1.c1().size();
            if (c0304t5B1 == c0304t5) {
                c0304t5B1.getClass();
            } else if (size != c0304t5B1.size()) {
                if (size < c0304t5B1.size()) {
                    C0306u0[] c0306u0Arr = new C0306u0[size];
                    System.arraycopy(c0304t5B1.b, 0, c0306u0Arr, 0, size);
                    c0304t5B1 = new C0304t5(c0306u0Arr, 0);
                } else {
                    C0306u0[] c0306u0Arr2 = c0304t5B1.b;
                    c0304t5B1 = new C0304t5(c0306u0Arr2, size - c0306u0Arr2.length);
                }
            }
            this.g = c0304t5B1;
        }

        public C0231j1 a() {
            AbstractC0223i0 abstractC0223i0;
            boolean z = u;
            if (!z && this.q && this.c == null) {
                x1f.a();
                return null;
            }
            if (!z && this.a == null) {
                x1f.a();
                return null;
            }
            if (!z && this.e == null) {
                x1f.a();
                return null;
            }
            if (!z && this.g == null) {
                x1f.a();
                return null;
            }
            if (!z && this.r && !this.g.isEmpty() && this.g.size() != this.c.i.f.size()) {
                x1f.a();
                return null;
            }
            if (!z && this.s && this.l == null) {
                x1f.a();
                return null;
            }
            if (!z && this.s && this.m == null) {
                x1f.a();
                return null;
            }
            if (!z && (abstractC0223i0 = this.b) != null && !abstractC0223i0.F0() && this.p != null) {
                x1f.a();
                return null;
            }
            C0231j1 c0231j1 = new C0231j1(this.c, this.a, this.d, this.e, this.g, this.b, this.p, this.n, this.l, this.m, this.k, this.i, this.o);
            c0231j1.a(this.j);
            c0231j1.l = this.h;
            if (!this.f.e()) {
                c0231j1.a(this.f);
            }
            this.t.accept(c0231j1);
            return c0231j1;
        }

        public final a b(Consumer consumer) {
            consumer.accept(this.a);
            return this;
        }

        public a c() {
            this.q = false;
            return this;
        }

        public final a b(com.android.tools.r8.androidapi.f fVar) {
            this.l = fVar;
            return this;
        }

        public a b() {
            this.s = false;
            return this;
        }

        public final a a(boolean z, Consumer consumer) {
            Consumer consumerB = C0822Sg.b();
            if (z) {
                consumer.accept(this);
                return this;
            }
            consumerB.accept(this);
            return this;
        }

        public final a a(final C0333y c0333y, final M5 m5) {
            return a(new BiConsumer() { // from class: mbh
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((w) obj2).a(c0333y, m5);
                }
            });
        }

        public a a(F4 f4) {
            this.a = f4;
            return this;
        }

        public a a(C0322w2 c0322w2) {
            this.c = c0322w2;
            return this;
        }

        public final a a(AbstractC2173nV abstractC2173nV) {
            if (u || !abstractC2173nV.e()) {
                this.f = abstractC2173nV;
                return this;
            }
            x1f.a();
            return null;
        }

        public final a a(final BiConsumer biConsumer) {
            this.t = this.t.andThen(new Consumer() { // from class: nbh
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a(biConsumer, (C0231j1) obj);
                }
            });
            return this;
        }

        public final /* synthetic */ void a(BiConsumer biConsumer, C0231j1 c0231j1) {
            if (this.i.d()) {
                biConsumer.accept(c0231j1, this.i.a());
            }
        }

        public a a(AbstractC0223i0 abstractC0223i0) {
            this.b = abstractC0223i0;
            return this;
        }

        public final a a(Function function) {
            this.b = (AbstractC0223i0) function.apply(this.c);
            return this;
        }

        public final a a(com.android.tools.r8.androidapi.f fVar) {
            this.m = fVar;
            return this;
        }

        public final a a(C1159bb c1159bb) {
            this.k = c1159bb;
            return this;
        }

        public final a a(Consumer consumer) {
            consumer.accept(this);
            return this;
        }

        public a(boolean z) {
            this.d = B3.g.d();
            this.e = C0306u0.o0();
            this.f = AbstractC2173nV.c;
            this.g = C0304t5.d;
            this.h = EnumC0238k1.b;
            this.i = C3263d.b;
            this.j = com.android.tools.r8.kotlin.d0.a;
            this.k = null;
            int i = com.android.tools.r8.androidapi.f.a;
            com.android.tools.r8.androidapi.g gVar = com.android.tools.r8.androidapi.g.b;
            this.l = gVar;
            this.m = gVar;
            this.o = false;
            this.p = null;
            this.q = true;
            this.r = true;
            this.s = true;
            this.t = C0822Sg.b();
            this.n = z;
        }
    }

    public final void b(C0322w2 c0322w2, C0333y c0333y, C0884Uq c0884Uq) {
        c0884Uq.b = c0322w2;
        c0884Uq.e = false;
        C0322w2 c0322w2H0 = getReference();
        I2 i2W0 = getReference().w0();
        i2W0.getClass();
        boolean zIsInterface = c0333y.d(i2W0).isInterface();
        c0884Uq.c = c0322w2H0;
        c0884Uq.f = EnumC0858Tq.e;
        c0884Uq.g = Boolean.valueOf(zIsInterface);
    }

    public final void b(C1159bb c1159bb) {
        O0();
        if (x || c1159bb != null) {
            this.n = (C1159bb) com.android.tools.r8.utils.structural.s.c(this.n, c1159bb);
        } else {
            x1f.a();
        }
    }

    public static /* synthetic */ C0285r0 b(BiFunction biFunction, C0285r0 c0285r0) {
        return (C0285r0) biFunction.apply(c0285r0, EnumC0272p0.c);
    }

    public static void a(com.android.tools.r8.utils.structural.A a2) {
        a2.e(new s26()).e(new Function() { // from class: jbh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0231j1) obj).getAccessFlags();
            }
        }).e(new Function() { // from class: kbh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0231j1) obj).n0();
            }
        }).e(new Function() { // from class: lbh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0231j1) obj).i;
            }
        }).j(new Function() { // from class: qah
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0231j1) obj).n;
            }
        }).b(new Predicate() { // from class: rah
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((C0231j1) obj).I0();
            }
        }).j(new Function() { // from class: sah
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0231j1) obj).k;
            }
        }).a(new Predicate() { // from class: tah
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((C0231j1) obj).q.a();
            }
        }).a(new Function() { // from class: uah
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0231j1) obj).U0();
            }
        }, new com.android.tools.r8.utils.structural.v() { // from class: vah
            @Override // com.android.tools.r8.utils.structural.v
            public final int a(Object obj, Object obj2, AbstractC3519a abstractC3519a) {
                return C0231j1.a((AbstractC0223i0) obj, (AbstractC0223i0) obj2, abstractC3519a);
            }
        }, new com.android.tools.r8.utils.structural.w() { // from class: ibh
            @Override // com.android.tools.r8.utils.structural.w
            public final void a(Object obj, o oVar) {
                C0231j1.a((AbstractC0223i0) obj, oVar);
            }
        });
    }

    public static int a(AbstractC0223i0 abstractC0223i0, AbstractC0223i0 abstractC0223i1, AbstractC3519a abstractC3519a) {
        if (abstractC0223i0 == abstractC0223i1) {
            return 0;
        }
        if (abstractC0223i0 != null && abstractC0223i1 != null) {
            if (abstractC0223i0.D0() && abstractC0223i1.D0()) {
                return abstractC0223i0.r0().a(abstractC0223i1.r0(), abstractC3519a);
            }
            if (abstractC0223i0.x0() && abstractC0223i1.x0()) {
                return abstractC0223i0.o0().a(abstractC0223i1.o0(), abstractC3519a);
            }
            if (abstractC0223i0.z0() && abstractC0223i1.z0()) {
                return abstractC0223i0.p0().a(abstractC0223i1.p0(), abstractC3519a);
            }
            throw new Kk0("Unexpected attempt to compare incompatible synthetic objects: " + abstractC0223i0 + " and " + abstractC0223i1);
        }
        return abstractC3519a.a(abstractC0223i0 != null, abstractC0223i1 != null);
    }

    public static void a(AbstractC0223i0 abstractC0223i0, com.android.tools.r8.utils.structural.o oVar) {
        if (abstractC0223i0 == null) {
            return;
        }
        if (abstractC0223i0.D0()) {
            abstractC0223i0.r0().a(oVar);
            return;
        }
        if (abstractC0223i0.x0()) {
            abstractC0223i0.o0().a(oVar);
        } else if (x || abstractC0223i0.z0()) {
            abstractC0223i0.p0().a(oVar);
        } else {
            x1f.a();
        }
    }

    public final void a(AbstractC2173nV abstractC2173nV) {
        boolean z = x;
        if (!z && !u1()) {
            x1f.a();
            return;
        }
        if (!z && abstractC2173nV.e()) {
            x1f.a();
            return;
        }
        if (!z && !abstractC2173nV.b() && !this.r.c()) {
            hih.a("Method `", getReference().m0(), "` went from not overriding a library method to overriding a library method");
        } else if (!z && !abstractC2173nV.c() && !this.r.b()) {
            hih.a("Method `", getReference().m0(), "` went from overriding a library method to not overriding a library method");
        } else {
            this.r = abstractC2173nV;
        }
    }

    @Override // com.android.tools.r8.graph.AbstractC0217h1
    public final Object a(Function function, Function function2) {
        return function2.apply(this);
    }

    public final B5 a(D2 d2) {
        if (x || E0() == d2.getType()) {
            return new B5(d2, this);
        }
        x1f.a();
        return null;
    }

    public final A5 a(InterfaceC0189d1 interfaceC0189d1) {
        if (!x && !getReference().f.M0()) {
            x1f.a();
            return null;
        }
        D2 d2B = D2.b(interfaceC0189d1.a((AbstractC0287r2) getReference()));
        if (d2B != null) {
            return new B5(d2B, this);
        }
        return null;
    }

    public final void a(com.android.tools.r8.kotlin.f0 f0Var) {
        if (x || this.p == com.android.tools.r8.kotlin.d0.a) {
            this.p = f0Var;
        } else {
            x1f.a();
        }
    }

    public final boolean a(C0333y c0333y, B5 b5, AbstractC2973wm0 abstractC2973wm0) {
        O0();
        C0229j c0229j = (C0229j) c0333y.g();
        switch (this.l.ordinal()) {
            case 0:
                abstractC2973wm0.i();
                return false;
            case 1:
                abstractC2973wm0.h();
                return false;
            case 2:
                return true;
            case XmlPullParser.END_TAG /* 3 */:
                if (c0229j.c(b5.s(), E0())) {
                    return true;
                }
                abstractC2973wm0.e();
                return false;
            case 4:
                if (b5.a(E0())) {
                    return true;
                }
                abstractC2973wm0.d();
                return false;
            case XmlPullParser.CDSECT /* 5 */:
                if (AbstractC3247c0.a(b5.s(), E0(), c0229j)) {
                    return true;
                }
                abstractC2973wm0.c();
                return false;
            case XmlPullParser.ENTITY_REF /* 6 */:
                if (b5.s().a(E0())) {
                    return true;
                }
                abstractC2973wm0.b();
                return false;
            default:
                pah.a("Unexpected compilation state: ", this.l);
                return false;
        }
    }

    public final boolean a(com.android.tools.r8.ir.optimize.N n) {
        O0();
        EnumC0238k1 enumC0238k1 = this.l;
        switch (AbstractC0224i1.b[n.a.ordinal()]) {
            case 1:
                this.l = EnumC0238k1.d;
                break;
            case 2:
                this.l = EnumC0238k1.e;
                break;
            case XmlPullParser.END_TAG /* 3 */:
                this.l = EnumC0238k1.f;
                break;
            case 4:
                this.l = EnumC0238k1.g;
                break;
            case XmlPullParser.CDSECT /* 5 */:
                this.l = EnumC0238k1.h;
                break;
            case XmlPullParser.ENTITY_REF /* 6 */:
                this.l = EnumC0238k1.c;
                break;
        }
        return enumC0238k1 != this.l;
    }

    public final void a(AbstractC0223i0 abstractC0223i0, InterfaceC2045lz interfaceC2045lz) {
        O0();
        if (abstractC0223i0 != null && !abstractC0223i0.F0() && j1()) {
            if (!x) {
                a(abstractC0223i0);
            }
            this.k = null;
        }
        this.j = abstractC0223i0;
        this.s = interfaceC2045lz;
    }

    public final void a(AbstractC0223i0 abstractC0223i0) {
        final E6 e6 = new E6(false);
        C0322w2 c0322w2H0 = getReference();
        O0();
        abstractC0223i0.a(c0322w2H0, true, new Consumer() { // from class: fbh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(e6, (AbstractC2004lX) obj);
            }
        });
        if (x || e6.e()) {
            return;
        }
        x1f.a();
    }

    public final void a(E6 e6, AbstractC2004lX abstractC2004lX) {
        if (e6.e()) {
            return;
        }
        do {
            if (!abstractC2004lX.f && abstractC2004lX.c.equals(this.k)) {
                e6.b(true);
                return;
            }
            abstractC2004lX = abstractC2004lX.d;
        } while (abstractC2004lX != null);
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public final void a(com.android.tools.r8.dex.X x2) {
        x2.b(this);
    }

    public final AbstractC2004lX a(AbstractC2004lX abstractC2004lX) {
        if (!x && abstractC2004lX == null) {
            x1f.a();
            return null;
        }
        if (!j1()) {
            return abstractC2004lX;
        }
        AbstractC2004lX.c.a aVarA = AbstractC2004lX.c.s().a(0).a(this.k);
        aVarA.c = abstractC2004lX;
        return ((AbstractC2004lX.c.a) aVarA.c()).a();
    }

    public final void a(C1159bb c1159bb) {
        O0();
        if (x || c1159bb != null) {
            this.n = (C1159bb) com.android.tools.r8.utils.structural.s.d(this.n, c1159bb);
        } else {
            x1f.a();
        }
    }

    public final String a(AbstractC3345r0 abstractC3345r0) {
        O0();
        StringBuilder sb = new StringBuilder("(");
        for (I2 i2 : getReference().i.f.b) {
            sb.append(abstractC3345r0.c(i2).toString());
        }
        sb.append(")");
        sb.append(abstractC3345r0.c(getReference().i.e).toString());
        return sb.toString();
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1
    public final void a(final BiFunction biFunction) {
        this.b = n0().a(new Function() { // from class: cbh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C0231j1.b(biFunction, (C0285r0) obj);
            }
        });
        this.i = b1().a(new Function() { // from class: dbh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C0231j1.c(biFunction, (C0285r0) obj);
            }
        });
    }

    public static void a(AbstractC0223i0 abstractC0223i0, int i, C0333y c0333y) {
        if (abstractC0223i0.y0()) {
            J0 j0N = abstractC0223i0.N();
            W0.a aVarA = j0N.a(c0333y.a());
            if (!x && aVarA != null && i != aVarA.f.length) {
                x1f.a();
                return;
            } else {
                j0N.a((W0) aVarA);
                return;
            }
        }
        if (x || abstractC0223i0.w0()) {
            abstractC0223i0.H().a(c0333y.a());
        } else {
            x1f.a();
        }
    }

    public final SK a(C0333y c0333y) {
        O0();
        B1 b1A = c0333y.a();
        com.android.tools.r8.naming.V.b bVarA = com.android.tools.r8.naming.V.b.a(getReference());
        H2 h2C = b1A.c("Shaking error: Missing method in " + getReference().f.m0() + ": " + bVarA);
        H2 h2C2 = b1A.c("[R8]");
        I2 i2 = b1A.J3;
        C0322w2 c0322w2A = b1A.a(i2, b1A.a(i2, b1A.Y1), b1A.c("getLogger"));
        C0322w2 c0322w2A2 = b1A.a(i2, b1A.a(b1A.E1, b1A.Y1), b1A.c("severe"));
        I2 i3 = b1A.m3;
        C0322w2 c0322w2A3 = b1A.a(i3, b1A.a(b1A.E1, b1A.Y1), b1A.c1);
        C2441qd c2441qdA = AbstractC2624sj0.a((C0333y<?>) c0333y, C2427qS.b());
        AbstractC2624sj0 abstractC2624sj0B = i2.b((C0333y<?>) c0333y);
        AbstractC2624sj0 abstractC2624sj0A = i3.a(c0333y, C2427qS.b());
        XK xkB = AbstractC2335pL.a().b();
        LK lkA = SK.a(getReference(), I0(), xkB, c0333y.M());
        int i = 0;
        while (i < getReference().a(z0())) {
            getReference().a(i, z0()).J0();
            lkA.a(i);
            i++;
        }
        C2543rl0 c2543rl0 = new C2543rl0(i, c2441qdA, null);
        xkB.a(c2543rl0, c2543rl0.s());
        lkA.a(h2C2);
        C2543rl0 c2543rl1 = new C2543rl0(i + 1, abstractC2624sj0B, null);
        xkB.a(c2543rl1, c2543rl1.s());
        lkA.b(c0322w2A, AbstractC0551Hu.c(c2543rl0), false);
        C2543rl0 c2543rl2 = new C2543rl0(i + 2, c2441qdA, null);
        xkB.a(c2543rl2, c2543rl2.s());
        lkA.a(h2C);
        lkA.a(c0322w2A2, AbstractC0551Hu.a(c2543rl1, c2543rl2));
        C2543rl0 c2543rl3 = new C2543rl0(i + 4, abstractC2624sj0A, null);
        xkB.a(c2543rl3, c2543rl3.s());
        lkA.a(i3).a(c0322w2A3, (List) AbstractC0551Hu.a(c2543rl3, c2543rl2), false);
        lkA.a(c2543rl3);
        return lkA.d();
    }

    public final G a(B1 b1) {
        O0();
        com.android.tools.r8.naming.V.b bVarA = com.android.tools.r8.naming.V.b.a(getReference());
        H2 h2C = b1.c("Shaking error: Missing method in " + getReference().f.m0() + ": " + bVarA);
        H2 h2C2 = b1.c("[R8]");
        I2 i2 = b1.J3;
        C0322w2 c0322w2A = b1.a(i2, b1.a(i2, b1.Y1), b1.c("getLogger"));
        C0322w2 c0322w2A2 = b1.a(i2, b1.a(b1.E1, b1.Y1), b1.c("severe"));
        I2 i3 = b1.m3;
        C0322w2 c0322w2A3 = b1.a(i3, b1.a(b1.E1, b1.Y1), b1.c1);
        int size = getReference().i.f.size();
        int i = size + 1;
        O0();
        if (!z0()) {
            i = size + 2;
        }
        C0473Eu c0473EuG = AbstractC0551Hu.g();
        C0473Eu c0473EuB = c0473EuG.a(new C1213c9(h2C2)).a(new G9(184, c0322w2A, false));
        El0 el0 = El0.b;
        int i4 = i - 1;
        c0473EuB.a(new C0842Ta(el0, i4)).a(new P9(el0, i4)).a(new C1213c9(h2C)).a(new G9(182, c0322w2A2, false)).a(new W9(i3)).a(new C0479Fa(C0479Fa.a.e)).a(new C1213c9(h2C)).a(new G9(183, c0322w2A3, false)).a(new C0946Xa());
        return new G(getReference().f, i, c0473EuG.a());
    }

    public final C0231j1 a(B1 b1, C0322w2 c0322w2) {
        O0();
        return a(c0322w2, b1, (Consumer) null);
    }

    public final C0231j1 a(final C0322w2 c0322w2, final B1 b1, final Consumer consumer) {
        final boolean z = true;
        Consumer consumer2 = new Consumer() { // from class: wah
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(c0322w2, z, b1, consumer, (C0231j1.a) obj);
            }
        };
        O0();
        a aVarE = e(this);
        if (u1() && r1() != AbstractC2173nV.c) {
            aVarE.a(r1());
        }
        aVarE.a(c0322w2);
        consumer2.accept(aVarE);
        return aVarE.a();
    }

    public final void a(C0322w2 c0322w2, boolean z, B1 b1, Consumer consumer, a aVar) {
        AbstractC0223i0 abstractC0223i0;
        AbstractC0223i0 abstractC0223i1 = this.j;
        if (abstractC0223i1 != null && !abstractC0223i1.F0()) {
            aVar.a(U0().a(c0322w2, z, getReference(), I0(), b1));
        } else {
            C0322w2 c0322w2H0 = getReference();
            boolean zI0 = I0();
            boolean z2 = a.u;
            if (!z2 && (abstractC0223i0 = aVar.b) != null && !abstractC0223i0.F0()) {
                x1f.a();
                return;
            } else if (zI0) {
                aVar.getClass();
            } else {
                if (!z2 && aVar.p != null) {
                    x1f.a();
                    return;
                }
                aVar.p = c0322w2H0;
            }
        }
        if (consumer != null) {
            AbstractC0223i0 abstractC0223i2 = aVar.b;
            consumer.accept(aVar);
            if (x || VU.a(abstractC0223i2, aVar.b)) {
                return;
            }
            x1f.a();
        }
    }

    public final B5 a(D2 d2, C0322w2 c0322w2, B1 b1) {
        boolean z = x;
        if (!z && z0()) {
            x1f.a();
            return null;
        }
        if (!z && J0()) {
            x1f.a();
            return null;
        }
        if (!z) {
            I2 i2E0 = E0();
            I2 i2W0 = c0322w2.w0();
            i2E0.getClass();
            if (!I2.a(i2E0, i2W0)) {
                x1f.a();
                return null;
            }
        }
        O0();
        return new B5(d2, a(c0322w2, b1, new Consumer() { // from class: abh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((C0231j1.a) obj).b(new Consumer() { // from class: ebh
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj2) {
                        C0231j1.a((F4) obj2);
                    }
                });
            }
        }));
    }

    public static /* synthetic */ void a(F4 f4) {
        f4.A();
        f4.C();
        f4.D();
        f4.w();
    }

    public final C0231j1 a(final C0333y c0333y, E0 e0, Consumer consumer) {
        C0322w2 c0322w2H0 = getReference();
        B1 b1A = c0333y.a();
        c0322w2H0.getClass();
        final C0322w2 c0322w2A = c0322w2H0.a(e0.z(), b1A);
        O0();
        this.g.a();
        a aVarB = e(this).a(c0322w2A).b(new Consumer() { // from class: gbh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((F4) obj).A();
            }
        });
        aVarB.d = B3.g.d();
        a aVarA = aVarB.a(!k1(), new Consumer() { // from class: hbh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(c0333y, c0322w2A, (C0231j1.a) obj);
            }
        });
        boolean z = (z0() || r1().e()) ? false : true;
        AbstractC2173nV abstractC2173nVR1 = r1();
        if (z) {
            aVarA = aVarA.a(abstractC2173nVR1);
        }
        return aVarA.a(consumer).a();
    }

    public final void a(C0322w2 c0322w2, C0333y c0333y, C0884Uq c0884Uq) {
        c0884Uq.b = c0322w2;
        c0884Uq.e = true;
        C0322w2 c0322w2H0 = getReference();
        I2 i2W0 = getReference().w0();
        i2W0.getClass();
        c0884Uq.a(c0322w2H0, c0333y.d(i2W0).isInterface());
    }

    public static C0231j1 a(H0 h0, E0 e0, C0322w2 c0322w2, B1 b1) {
        if (!x && c0322w2 == null) {
            x1f.a();
            return null;
        }
        C0322w2 c0322w2U = h0.getReference();
        c0322w2U.getClass();
        C0322w2 c0322w2A = c0322w2U.a(e0.z(), b1);
        F4 f4W = h0.getAccessFlags();
        F4 f4 = new F4(f4W.b, f4W.c);
        f4.A();
        f4.c(Fcntl.S_ISGID);
        a aVarA = new a(true).a(c0322w2A).a(f4);
        aVarA.d = B3.g.d();
        aVarA.e = C0306u0.o0();
        C0884Uq c0884Uq = new C0884Uq(b1);
        c0884Uq.b = c0322w2A;
        c0884Uq.e = false;
        a aVarA2 = aVarA.a(c0884Uq.a(c0322w2, false).a());
        aVarA2.l = h0.e().e;
        aVarA2.m = h0.e().o;
        return aVarA2.a();
    }

    public final void a(com.android.tools.r8.ir.optimize.info.w wVar) {
        O0();
        this.m = wVar;
    }

    public final void a(C0333y c0333y, C0231j1 c0231j1) {
        O0();
        if (c0231j1.h1()) {
            b(c0231j1.T0());
        }
        if (c0333y.M().a().b && c0333y.o()) {
            this.o = this.o.e(c0231j1.o);
        }
    }
}
