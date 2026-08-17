package com.android.tools.r8.ir.optimize;

import com.android.tools.r8.graph.AbstractC0223i0;
import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0229j;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.C1;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.EnumC0238k1;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.T4;
import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.AbstractC1047aC;
import com.android.tools.r8.internal.AbstractC1530fw;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.AbstractC2973wm0;
import com.android.tools.r8.internal.C0682Mw;
import com.android.tools.r8.internal.C0705Nt;
import com.android.tools.r8.internal.C0889Uv;
import com.android.tools.r8.internal.C1201c3;
import com.android.tools.r8.internal.C1328dc;
import com.android.tools.r8.internal.C1701hw;
import com.android.tools.r8.internal.C1983lC;
import com.android.tools.r8.internal.C2098md;
import com.android.tools.r8.internal.C2424qP;
import com.android.tools.r8.internal.C2441qd;
import com.android.tools.r8.internal.C2543rl0;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.EnumC3077y2;
import com.android.tools.r8.internal.H5;
import com.android.tools.r8.internal.IO;
import com.android.tools.r8.internal.InterfaceC1785iw;
import com.android.tools.r8.internal.Kk0;
import com.android.tools.r8.internal.VB;
import com.android.tools.r8.internal.Vd0;
import com.android.tools.r8.internal.Y6;
import com.android.tools.r8.ir.optimize.G;
import com.android.tools.r8.shaking.C3403i;
import com.android.tools.r8.shaking.C3418l;
import com.android.tools.r8.shaking.R1;
import com.android.tools.r8.shaking.Y0;
import defpackage.jh6;
import defpackage.tvg;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class G implements X {
    public static final /* synthetic */ boolean i = true;
    public final C0333y a;
    public final C2752uB b;
    public final C2752uB.i c;
    public final R1 d;
    public final B5 e;
    public final IO f;
    public final InterfaceC1785iw g;
    public int h;

    public G(C0333y c0333y, InterfaceC1785iw interfaceC1785iw, B5 b5, IO io2, int i2) {
        this.a = c0333y;
        C2752uB c2752uBM = c0333y.M();
        this.b = c2752uBM;
        this.c = c2752uBM.T();
        this.g = interfaceC1785iw;
        this.d = ((C3403i) c0333y.g()).f();
        this.e = b5;
        this.f = io2;
        this.h = i2;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x007b  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.ir.optimize.X
    public Q a(C0705Nt c0705Nt, AbstractC1047aC abstractC1047aC, T4.c cVar, B5 b5, B5 b6, C1328dc c1328dc, C1701hw c1701hw, AbstractC2973wm0 abstractC2973wm0) {
        O oA;
        Q q;
        C0210g1 c0210g1;
        Q q2 = null;
        if (a(abstractC1047aC, b5, abstractC2973wm0)) {
            return null;
        }
        C0322w2 reference = b5.getReference();
        boolean zG = true;
        if (this.a.a(b5).h(this.b)) {
            C3418l c3418l = this.a.e;
            if (c3418l.a(abstractC1047aC.U2()).c || c3418l.a(com.android.tools.r8.graph.H0.a(cVar.c, cVar.d).getReference()).c || c3418l.a(reference).c) {
                C0231j1 c0231j1E = b5.e();
                c0231j1E.O0();
                zG = true ^ c0231j1E.m.g();
            } else if (this.a.Q().N) {
                zG = false;
            } else {
                com.android.tools.r8.synthesis.J jG = this.a.a.g();
                D2 d2A = b5.a();
                jG.getClass();
                if (!jG.g(d2A.e)) {
                    zG = false;
                }
            }
        } else {
            abstractC2973wm0.p();
        }
        if (zG) {
            if (!b5.e().a1().g()) {
                return null;
            }
            throw new Kk0("Unexpected attempt to force inline method `" + b5.v() + "` in `" + b6.v() + "`.");
        }
        T tA = this.g.a(abstractC1047aC, b5, b6, this, c1701hw, this.f, abstractC2973wm0);
        if (tA == T.f) {
            return null;
        }
        if (this.f.c().a(b5, b6) && !b5.e().w1() && this.f.f()) {
            return new U();
        }
        if (!b5.e().a(this.a, this.e, abstractC2973wm0) || !a(c0705Nt, cVar, b5, abstractC2973wm0)) {
            return null;
        }
        if ((this.b.g() && ((b5.getAccessFlags().O() || b5.e().U0().v0()) && (b6.D().g() || c0705Nt.u().e()))) || (oA = abstractC1047aC.a(b5, this, c1328dc, abstractC2973wm0)) == null || !a(this.a, oA, abstractC1047aC, b5, b6)) {
            return null;
        }
        if (b5.e().q1()) {
            VB vbZ = abstractC1047aC.Z();
            if (this.c.a) {
                C0705Nt c0705NtB = c1701hw.b(b5, vbZ);
                I2 i2S = this.e.s();
                I2 i2S2 = b5.s();
                if (this.e.e().q1() && i2S == i2S2 && vbZ.V2() == c0705Nt.o()) {
                    c1701hw.a(vbZ, c0705NtB);
                    q = null;
                } else {
                    C2543rl0 c2543rl0C = ((AbstractC0890Uw) c0705NtB.j().f.get(0)).r().c();
                    ArrayList arrayList = new ArrayList();
                    for (AbstractC0890Uw abstractC0890Uw : c0705NtB.s()) {
                        if (abstractC0890Uw.a(this.a.a())) {
                            VB vbZ2 = abstractC0890Uw.Z();
                            if (vbZ2.V2().h() != c2543rl0C) {
                                continue;
                            } else if (this.b.h() || i2S2 == vbZ2.U2().w0()) {
                                arrayList.add(vbZ2);
                            } else {
                                abstractC2973wm0.a(vbZ2);
                            }
                        } else if (abstractC0890Uw.Q1()) {
                            C0682Mw c0682MwV = abstractC0890Uw.V();
                            C0245l1 field = c0682MwV.getField();
                            Q q3 = q2;
                            C3403i c3403i = (C3403i) this.a.g();
                            c3403i.getClass();
                            ArrayList arrayList2 = arrayList;
                            I2 i2 = field.f;
                            boolean z = C0229j.i;
                            if (!z) {
                                c3403i.c();
                            }
                            if (!z && !i2.M0()) {
                                x1f.a();
                                return q3;
                            }
                            C0210g1 c0210g1Q = c3403i.a(i2, field).q();
                            if (c0210g1Q == null || c0210g1Q.g.n()) {
                                c0210g1 = c0210g1Q;
                                c0210g1 = q3;
                            }
                            if (c0210g1 == 0 || c0210g1.g.f()) {
                                abstractC2973wm0.a(c0682MwV);
                                return q3;
                            }
                            arrayList = arrayList2;
                            q2 = q3;
                        } else {
                            continue;
                        }
                    }
                    q = q2;
                    ArrayList arrayList3 = arrayList;
                    if (arrayList3.isEmpty()) {
                        for (AbstractC0890Uw abstractC0890Uw2 : c2543rl0C.b0()) {
                            if (abstractC0890Uw2.Q1() && abstractC0890Uw2.V().f().h() == c2543rl0C) {
                                abstractC2973wm0.a(abstractC0890Uw2);
                                return q;
                            }
                        }
                    } else {
                        int iA = c0705NtB.A();
                        Iterator it = arrayList3.iterator();
                        while (it.hasNext()) {
                            AbstractC0890Uw abstractC0890Uw3 = (VB) it.next();
                            H5 h5I = abstractC0890Uw3.i();
                            for (AbstractC0890Uw abstractC0890Uw4 : h5I.b(abstractC0890Uw3)) {
                                Iterator it2 = abstractC0890Uw4.c.iterator();
                                while (it2.hasNext()) {
                                    Iterator it3 = it;
                                    if (((C2543rl0) it2.next()).h() == c2543rl0C) {
                                        c0705NtB.a(iA);
                                        abstractC2973wm0.a(abstractC0890Uw4);
                                        return q;
                                    }
                                    it = it3;
                                }
                            }
                            Iterator it4 = it;
                            Iterator<H5> it5 = h5I.s().iterator();
                            while (it5.hasNext()) {
                                c0705NtB.a(it5.next(), iA);
                            }
                            it = it4;
                        }
                        for (H5 h5 : c0705NtB.d) {
                            if (h5.a(iA)) {
                                for (AbstractC0890Uw abstractC0890Uw5 : h5.k()) {
                                    Iterator it6 = abstractC0890Uw5.c.iterator();
                                    while (it6.hasNext()) {
                                        if (((C2543rl0) it6.next()).h() == c2543rl0C) {
                                            c0705NtB.a(iA);
                                            abstractC2973wm0.a(abstractC0890Uw5);
                                            return q;
                                        }
                                    }
                                }
                            }
                        }
                        c0705NtB.a(iA);
                    }
                    c1701hw.a(vbZ, c0705NtB);
                }
            }
            return q2;
        }
        q = null;
        if (tA == T.d) {
            if (!i && !this.f.f()) {
                x1f.a();
                return q;
            }
            if (c(abstractC1047aC, b5, Optional.of(c1701hw))) {
                tA = T.e;
            }
            oA.a(tA);
        } else {
            if (tA == T.e && !c(abstractC1047aC, b5, Optional.of(c1701hw))) {
                abstractC2973wm0.j();
                return q;
            }
            oA.a(tA);
        }
        return oA.a();
    }

    public final int b(AbstractC1047aC abstractC1047aC, B5 b5, Optional optional) {
        int i2 = 0;
        if (!optional.isEmpty() && !abstractC1047aC.c.isEmpty() && b5.e().U0().D0()) {
            final C0705Nt c0705NtA = ((C1701hw) optional.get()).a(b5, abstractC1047aC);
            Objects.requireNonNull(c0705NtA);
            Iterable<C1201c3> iterable = new Iterable() { // from class: wv5
                @Override // java.lang.Iterable
                public final Iterator iterator() {
                    return c0705NtA.b();
                }
            };
            B1 b1A = this.a.a();
            for (C1201c3 c1201c3 : iterable) {
                C2543rl0 c2543rl0C = c1201c3.c();
                for (AbstractC0890Uw abstractC0890Uw : c2543rl0C.b0()) {
                    if (!abstractC0890Uw.u1()) {
                        I2 i2A = b5.a(c1201c3.b(true));
                        b1A.getClass();
                        final C1 c1 = i2A.T0() ? new C1(b1A.c(i2A), b1A.g(i2A)) : b1A.S5.containsValue(i2A) ? new C1(b1A.g(i2A), b1A.c(i2A)) : null;
                        if (c1 != null) {
                            C2543rl0 c2543rl0H = abstractC1047aC.b(c1201c3.b(true)).h();
                            if (abstractC0890Uw.a(c1.a) && c2543rl0H.c(new Predicate() { // from class: yv5
                                @Override // java.util.function.Predicate
                                public final boolean test(Object obj) {
                                    return G.b(c1, (AbstractC0890Uw) obj);
                                }
                            })) {
                                i2 = (c2543rl0H.U() == 1 && c2543rl0C.U() == 1) ? i2 + 8 : i2 + 4;
                            }
                        }
                    } else if (abstractC1047aC.b(c1201c3.b(true)).t().a(abstractC0890Uw.z().i.b(this.a), this.a)) {
                        i2 += 2;
                    }
                }
            }
        }
        return i2;
    }

    public final boolean c(AbstractC1047aC abstractC1047aC, B5 b5, Optional optional) {
        int iA;
        if (!this.f.a(b5)) {
            AbstractC0223i0 abstractC0223i0U0 = b5.e().U0();
            int i2 = this.c.c;
            if (i2 < 0) {
                i2 = 5;
            }
            int i3 = 0;
            if (this.b.T().d) {
                iA = ((this.b.Z() && abstractC1047aC.c1() && abstractC1047aC.c().z()) ? 1 : 0) + a(abstractC1047aC, b5, optional) + b(b5, abstractC1047aC);
            } else {
                iA = 0;
            }
            int iK = abstractC0223i0U0.k(iA + i2);
            if (iK >= 0) {
                if (iK <= i2) {
                    return true;
                }
                if (this.b.T().d) {
                    int iB = b(abstractC1047aC, b5, optional) + b(b5, abstractC1047aC);
                    if (this.b.Z() && abstractC1047aC.c1() && abstractC1047aC.c().z()) {
                        i3 = 1;
                    }
                    i3 += iB;
                }
                if (iK <= i2 + i3) {
                    return true;
                }
            }
        }
        C0231j1 c0231j1E = b5.e();
        c0231j1E.O0();
        return c0231j1E.m.u().a(abstractC1047aC);
    }

    public static int b(B5 b5, AbstractC1047aC abstractC1047aC) {
        BitSet bitSetS = b5.D().s();
        int i2 = 0;
        if (bitSetS == null) {
            return 0;
        }
        for (int iA = Y6.a(abstractC1047aC.X1()); iA < abstractC1047aC.c.size(); iA++) {
            C2543rl0 c2543rl0B = abstractC1047aC.b(iA);
            if (bitSetS.get(iA) && c2543rl0B.t().I() && c2543rl0B.M()) {
                i2 += 4;
            }
        }
        return i2;
    }

    @Override // com.android.tools.r8.ir.optimize.X
    public final C0333y b() {
        return this.a;
    }

    public static boolean b(C1 c1, AbstractC0890Uw abstractC0890Uw) {
        return abstractC0890Uw.a(c1.b);
    }

    @Override // com.android.tools.r8.ir.optimize.X
    public final boolean a() {
        return false;
    }

    public static boolean a(AbstractC1047aC abstractC1047aC, B5 b5, AbstractC2973wm0 abstractC2973wm0) {
        if (!b5.e().m1()) {
            if (!b5.e().i1()) {
                abstractC2973wm0.g();
                return true;
            }
            int size = ((ArrayList) abstractC1047aC.K2()).size() - Y6.a(abstractC1047aC.X1());
            int iA0 = b5.getReference().A0();
            if (size == iA0) {
                return false;
            }
            abstractC2973wm0.a(size, iA0);
            return true;
        }
        throw new Kk0("Unexpected attempt to invoke a class initializer (`" + b5.v() + "`)");
    }

    /* JADX WARN: Code duplicated, block: B:67:0x0143  */
    /* JADX WARN: Code duplicated, block: B:74:0x016b  */
    @Override // com.android.tools.r8.ir.optimize.X
    public final boolean a(C0705Nt c0705Nt, T4.c cVar, B5 b5, AbstractC2973wm0 abstractC2973wm0) {
        boolean zB;
        Y0 y0A;
        B5 b6 = this.e;
        if (this.b.a().d() && b6.s() != b5.s()) {
            com.android.tools.r8.androidapi.f fVar = b6.e().o;
            if (fVar.E()) {
                abstractC2973wm0.a();
            } else {
                com.android.tools.r8.androidapi.f fVar2 = b5.e().o;
                if (!b6.e().o.c(fVar2)) {
                    abstractC2973wm0.a(fVar, fVar2);
                }
            }
            return false;
        }
        if (this.e.b(b5)) {
            if (i || !b5.D().g()) {
                abstractC2973wm0.t();
                return false;
            }
            x1f.a();
            return false;
        }
        C2752uB c2752uB = this.b;
        c2752uB.getClass();
        if (c2752uB.a(EnumC3077y2.z) && ((c0705Nt.i().getAccessFlags().O() || c0705Nt.i.b(42)) && (b5.getAccessFlags().O() || b5.e().U0().v0()))) {
            return false;
        }
        if (this.f.a(b5)) {
            abstractC2973wm0.q();
            return false;
        }
        B5 b7 = this.e;
        C0333y c0333y = this.a;
        if (c0333y.M().o != null) {
            C2098md c2098md = ((C0229j) c0333y.g()).g;
            if (!c2098md.a(b7, b5, c0333y)) {
                if (!c2098md.a(b5.getReference(), c0333y.a.g()).isBase() || (c0333y.M().o.b && b5.e().l != EnumC0238k1.d)) {
                    abstractC2973wm0.l();
                    return false;
                }
            }
        }
        B5 b8 = this.e;
        C0333y c0333y2 = this.a;
        Vd0 vd0 = c0333y2.q;
        if (vd0.a() || c0333y2.M().L().b || b5.D().g()) {
            zB = true;
        } else if (c0333y2.g().i()) {
            if (((C3403i) c0333y2.V().g()).x.contains(b5.getReference())) {
                zB = true;
            } else {
                y0A = c0333y2.t().a(b5.a());
                c0333y2.M();
                if (y0A.h && vd0.b(b8.s())) {
                    zB = vd0.b(b5.s());
                } else {
                    zB = true;
                }
            }
        } else {
            y0A = c0333y2.t().a(b5.a());
            c0333y2.M();
            if (y0A.h) {
                zB = true;
            } else {
                zB = vd0.b(b5.s());
            }
        }
        if (!zB) {
            abstractC2973wm0.m();
            return false;
        }
        if (cVar.a(this.e, this.a).b()) {
            abstractC2973wm0.f();
            return false;
        }
        if (this.d.a(this.a, this.e, b5)) {
            abstractC2973wm0.k();
            return false;
        }
        if (i || !this.d.a(this.a, this.e, b5)) {
            return true;
        }
        x1f.a();
        return false;
    }

    public final boolean a(C0705Nt c0705Nt, AbstractC1047aC abstractC1047aC, C0705Nt c0705Nt2, AbstractC2973wm0 abstractC2973wm0) {
        if (!c0705Nt.i.b(42) || !c0705Nt2.i.b(42)) {
            return false;
        }
        Set setC = AbstractC2780ub0.c();
        Set setC2 = AbstractC2780ub0.c();
        AbstractC1530fw.a(c0705Nt, setC, setC2);
        if (setC.isEmpty() && setC2.isEmpty()) {
            return false;
        }
        Iterator it = c0705Nt2.b((Predicate) new tvg()).iterator();
        while (it.hasNext()) {
            C2543rl0 c2543rl0H = ((C2543rl0) ((C2424qP) it.next()).c.get(0)).h();
            if (c2543rl0H.c(new jh6())) {
                c2543rl0H = ((C2543rl0) abstractC1047aC.c.get(c2543rl0H.c.r().b(true))).h();
            }
            AbstractC1530fw.a(c2543rl0H, setC, setC2);
        }
        int size = setC2.size() + setC.size();
        int i2 = this.c.g;
        if (size <= i2) {
            return false;
        }
        abstractC2973wm0.d(size, i2);
        return true;
    }

    public final int a(AbstractC1047aC abstractC1047aC, B5 b5, Optional optional) {
        final C1 c1;
        int i2 = 0;
        if (!optional.isEmpty() && !abstractC1047aC.c.isEmpty() && b5.e().U0().D0()) {
            for (int iA = Y6.a(abstractC1047aC.X1()); iA < abstractC1047aC.c.size(); iA++) {
                C2543rl0 c2543rl0H = abstractC1047aC.b(iA).h();
                if (c2543rl0H.t().I()) {
                    i2 += 2;
                }
                I2 i2A = b5.a(iA);
                B1 b1A = this.a.a();
                b1A.getClass();
                if (i2A.T0()) {
                    c1 = new C1(b1A.c(i2A), b1A.g(i2A));
                } else {
                    c1 = b1A.S5.containsValue(i2A) ? new C1(b1A.g(i2A), b1A.c(i2A)) : null;
                }
                if (c1 != null && c2543rl0H.c(new Predicate() { // from class: uv5
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return G.a(c1, (AbstractC0890Uw) obj);
                    }
                })) {
                    i2 += 8;
                }
            }
        }
        return i2;
    }

    @Override // com.android.tools.r8.ir.optimize.X
    public final B5 a(B5 b5, AbstractC1047aC abstractC1047aC) {
        return com.android.tools.r8.graph.H0.a(abstractC1047aC.g(this.a, b5));
    }

    @Override // com.android.tools.r8.ir.optimize.X
    public final C2441qd a(AbstractC1047aC abstractC1047aC, C2441qd c2441qd) {
        return c2441qd;
    }

    public final boolean a(C1983lC c1983lC, final B5 b5, final B5 b6, C1328dc c1328dc) {
        if (((C3403i) this.a.g()).c(b5.s(), b6.s())) {
            return true;
        }
        return (!b5.e().z0() && ((Boolean) this.a.b(new Function() { // from class: sv5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((C0889Uv) obj).a(b6.a(), b5));
            }
        })).booleanValue()) || c1328dc.a(b6.s(), c1983lC) || !b6.a().a(this.a, b5) || this.a.P().g.contains(b6.getReference());
    }

    @Override // com.android.tools.r8.ir.optimize.X
    public final boolean a(P p, AbstractC2973wm0 abstractC2973wm0) {
        if (p.c == T.b) {
            return true;
        }
        boolean z = this.h > 0;
        if (!z) {
            abstractC2973wm0.n();
        }
        return z;
    }

    @Override // com.android.tools.r8.ir.optimize.X
    public final boolean a(P p, C0705Nt c0705Nt, C0705Nt c0705Nt2, AbstractC1047aC abstractC1047aC, H5 h5, AbstractC2973wm0 abstractC2973wm0) {
        if (p.c == T.b) {
            return false;
        }
        int iB = V.b(c0705Nt2);
        if (this.h < V.b(c0705Nt2)) {
            abstractC2973wm0.c(iB, this.h);
            return true;
        }
        if (a(c0705Nt, abstractC1047aC, c0705Nt2, abstractC2973wm0)) {
            return true;
        }
        if (h5.x()) {
            Iterator<H5> it = c0705Nt2.d.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                Iterator<AbstractC0890Uw> it2 = it.next().k().iterator();
                int i3 = 0;
                while (it2.hasNext()) {
                    if (it2.next().g()) {
                        i3++;
                    }
                }
                i2 += i3;
            }
            int size = h5.e.size() * i2;
            int i4 = this.c.h;
            if (size >= i4) {
                abstractC2973wm0.b(size, i4);
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.ir.optimize.X
    public final void a(C0705Nt c0705Nt) {
        this.h -= V.b(c0705Nt);
    }

    public static boolean a(C1 c1, AbstractC0890Uw abstractC0890Uw) {
        return abstractC0890Uw.a(c1.b);
    }
}
