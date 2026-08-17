package com.android.tools.r8.graph;

import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.E0;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.T4;
import com.android.tools.r8.internal.AbstractC0439Dm;
import com.android.tools.r8.internal.AbstractC1047aC;
import com.android.tools.r8.internal.AbstractC1133bC;
import com.android.tools.r8.internal.AbstractC2173nV;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.C0491Fm;
import com.android.tools.r8.internal.C0822Sg;
import com.android.tools.r8.internal.C1819jJ;
import com.android.tools.r8.internal.C1975l7;
import com.android.tools.r8.internal.C2069mC;
import com.android.tools.r8.internal.YB;
import com.android.tools.r8.ir.optimize.info.C3263d;
import com.android.tools.r8.shaking.C3403i;
import defpackage.a0e;
import defpackage.b0e;
import defpackage.wzd;
import defpackage.xzd;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class T4 extends D4<C0231j1, C0322w2> {
    public static final /* synthetic */ boolean a = true;

    public static c a(E0 e0, E0 e1, C0231j1 c0231j1) {
        if (e1.b0()) {
            return new C0200e5(e0, e1.Z(), c0231j1);
        }
        if (e1 instanceof I0) {
            return new C0193d5(e0, e1.m(), c0231j1);
        }
        if (a || e1.a0()) {
            return new C0207f5(e0, e1.X(), c0231j1);
        }
        x1f.a();
        return null;
    }

    public abstract B4 a(D2 d2, C0333y<C3403i> c0333y, D2 d3, D2 d4);

    public abstract B4 a(D2 d2, C0333y<? extends C0229j> c0333y, Z3 z3, InterfaceC0318v5 interfaceC0318v5);

    public abstract C4 a(com.android.tools.r8.shaking.V0 v0, C0229j c0229j);

    public abstract H0 a(D2 d2, C0333y<? extends C0229j> c0333y);

    public abstract H0 a(D2 d2, C0333y c0333y, C0229j c0229j);

    public abstract InterfaceC0331x4 a(E0 e0, C0229j c0229j);

    public abstract void a(Consumer consumer, Consumer consumer2, Consumer consumer3, Consumer consumer4);

    public final B4 b(D2 d2, C0333y<C3403i> c0333y) {
        C3403i c3403i = (C3403i) c0333y.g();
        Objects.requireNonNull(c3403i);
        return a(d2, c0333y, c3403i, new wzd(c3403i));
    }

    public abstract H0 b(D2 d2, C0333y c0333y, C0229j c0229j);

    public abstract AbstractC2173nV b(InterfaceC0332x5 interfaceC0332x5, C0333y<? extends C0229j> c0333y);

    public abstract H0 c(D2 d2, C0333y c0333y, C0229j c0229j);

    public final H0 c(InterfaceC0332x5 interfaceC0332x5, C0333y<? extends C0229j> c0333y) {
        return c(interfaceC0332x5.b(), c0333y, (C0229j) c0333y.g());
    }

    public E0 d() {
        return null;
    }

    @Override // com.android.tools.r8.graph.D4
    public final T4 f() {
        return this;
    }

    @Override // com.android.tools.r8.graph.D4
    public T5 g() {
        return null;
    }

    @Override // com.android.tools.r8.graph.D4
    public boolean j() {
        return false;
    }

    public a k() {
        return null;
    }

    public b l() {
        return null;
    }

    public C0193d5 m() {
        return null;
    }

    public C0207f5 n() {
        return null;
    }

    public c<?> o() {
        return null;
    }

    public H0 p() {
        return null;
    }

    public C0231j1 q() {
        return null;
    }

    public B5 r() {
        return null;
    }

    public final C0231j1 s() {
        if (w()) {
            return o().q();
        }
        return null;
    }

    public boolean t() {
        return false;
    }

    public boolean u() {
        return false;
    }

    public boolean v() {
        return false;
    }

    public boolean w() {
        return false;
    }

    public abstract boolean x();

    public static class b extends a {
        public static final b c = new b();

        private b() {
            super(null);
        }

        public static b z() {
            return c;
        }

        @Override // com.android.tools.r8.graph.T4
        public final boolean b(E0 e0, C0333y c0333y, C0229j c0229j) {
            return true;
        }

        @Override // com.android.tools.r8.graph.T4
        public final b l() {
            return this;
        }

        @Override // com.android.tools.r8.graph.T4
        public final boolean t() {
            return true;
        }

        public b(Set set) {
            super(set);
        }
    }

    public static abstract class a extends S4 {
        public final Collection b;

        public a(Collection collection) {
            this.b = collection;
        }

        public void a(Consumer<I2> consumer, Consumer<? super C0231j1> consumer2) {
            Collection collection = this.b;
            if (collection != null) {
                collection.forEach(consumer);
            }
        }

        @Override // com.android.tools.r8.graph.T4
        public final AbstractC2173nV b(InterfaceC0332x5 interfaceC0332x5, C0333y c0333y) {
            return AbstractC2173nV.b;
        }

        @Override // com.android.tools.r8.graph.D4
        public final boolean h() {
            return true;
        }

        @Override // com.android.tools.r8.graph.T4
        public final a k() {
            return this;
        }

        @Override // com.android.tools.r8.graph.T4
        public final boolean x() {
            return false;
        }

        public boolean y() {
            return false;
        }

        @Override // com.android.tools.r8.graph.D4
        public final AbstractC2173nV a(InterfaceC0332x5 interfaceC0332x5, C0333y c0333y, C0229j c0229j) {
            return AbstractC2173nV.b;
        }

        @Override // com.android.tools.r8.graph.T4
        public final void a(Consumer consumer, Consumer consumer2, Consumer consumer3, Consumer consumer4) {
            consumer4.accept(this);
        }
    }

    public boolean b(E0 e0, C0333y<?> c0333y, C0229j c0229j) {
        return false;
    }

    public final boolean b(E0 e0, C0333y<? extends C0229j> c0333y) {
        return b(e0, c0333y, (C0229j) c0333y.g());
    }

    public static abstract class c<T extends E0> extends T4 implements T5 {
        public static final /* synthetic */ boolean e = true;
        public final E0 b;
        public final E0 c;
        public final C0231j1 d;

        public c(E0 e0, E0 e1, C0231j1 c0231j1) {
            boolean z = e;
            if (!z && e0 == null) {
                x1f.a();
                throw null;
            }
            if (!z && e1 == null) {
                x1f.a();
                throw null;
            }
            if (!z && c0231j1 == null) {
                x1f.a();
                throw null;
            }
            if (!z && e1.e != c0231j1.E0()) {
                x1f.a();
                throw null;
            }
            this.c = e1;
            this.d = c0231j1;
            this.b = e0;
            if (z) {
                return;
            }
            c0231j1.O0();
            if (!c0231j1.g.i() || e0.e == c0231j1.E0()) {
                return;
            }
            x1f.a();
            throw null;
        }

        public abstract c a(E0 e0);

        /* JADX WARN: Code duplicated, block: B:48:0x00e3  */
        /* JADX WARN: Code duplicated, block: B:66:0x0117  */
        /* JADX WARN: Code duplicated, block: B:69:0x0130  */
        /* JADX WARN: Code duplicated, block: B:75:0x014b  */
        /* JADX WARN: Code duplicated, block: B:77:0x0151  */
        /* JADX WARN: Code duplicated, block: B:93:0x01b3  */
        public final AbstractC0184c3 a(C0333y c0333y, AbstractC1047aC abstractC1047aC, B5 b5) {
            H0 h0D;
            I2 i2W0;
            I2 i2S;
            H0 h0D2;
            H0 h0D3;
            int iOrdinal = abstractC1047aC.P2().ordinal();
            H0 h0A = null;
            if (iOrdinal == 0) {
                C0322w2 c0322w2U2 = abstractC1047aC.Z().U2();
                if (c0333y.g().i()) {
                    C0333y<C3403i> c0333yV = c0333y.V();
                    H0 h0A2 = ((C3403i) c0333yV.g()).a(c0322w2U2, b5, c0333yV);
                    if (!e) {
                        c0333y.g().getClass();
                        I2 i2W1 = c0322w2U2.w0();
                        I2 i2S2 = b5.s();
                        i2W1.getClass();
                        if (I2.a(i2W1, i2S2)) {
                            D2 d2A = b5.a();
                            h0D = d2A.d(d2A.b(c0322w2U2));
                            if (h0D == null || h0D.getAccessFlags().n()) {
                                h0D = null;
                            }
                        } else {
                            h0D = null;
                        }
                        if (h0D != null && !AbstractC1047aC.k && !h0D.b(h0A2)) {
                            x1f.a();
                            return null;
                        }
                    }
                    h0A = h0A2;
                } else {
                    c0333y.g().getClass();
                    I2 i2W2 = c0322w2U2.w0();
                    I2 i2S3 = b5.s();
                    i2W2.getClass();
                    if (I2.a(i2W2, i2S3)) {
                        D2 d2A2 = b5.a();
                        H0 h0D4 = d2A2.d(d2A2.b(c0322w2U2));
                        if (h0D4 != null && !h0D4.getAccessFlags().n()) {
                            h0A = h0D4;
                        }
                    }
                }
                return h0A != null ? new N5(h0A, this) : new Y5(this);
            }
            if (iOrdinal == 8) {
                return new Y5(this);
            }
            if (iOrdinal != 2) {
                if (iOrdinal != 3) {
                    if (e || (abstractC1047aC instanceof YB) || abstractC1047aC.c2()) {
                        AbstractC1133bC abstractC1133bCC0 = abstractC1047aC.c0();
                        return a(c0333y, abstractC1133bCC0, c0333y.g().i() ? abstractC1133bCC0.V2().a(c0333y.V()) : AbstractC0439Dm.m(), b5);
                    }
                    x1f.a();
                    return null;
                }
                C2069mC c2069mCG0 = abstractC1047aC.g0();
                if (c0333y.g().i() && b5 != null) {
                    C0333y<C3403i> c0333yV2 = c0333y.V();
                    C3403i c3403i = (C3403i) c0333yV2.g();
                    C0322w2 c0322w2U3 = c2069mCG0.U2();
                    if (c3403i.c(b5.s(), c0322w2U3.w0())) {
                        h0A = c3403i.a(c0322w2U3, b5.a(), c0333yV2, (C0229j) c0333yV2.g());
                    }
                }
                return h0A != null ? new N5(h0A, this) : new Y5(this);
            }
            C0322w2 c0322w2U4 = abstractC1047aC.f0().U2();
            if (!c0333y.g().i()) {
                E0 e0D = c0333y.d(c0322w2U4.f);
                if (e0D == null) {
                    c0333y.g().getClass();
                    i2W0 = c0322w2U4.w0();
                    i2S = b5.s();
                    i2W0.getClass();
                    if (I2.a(i2W0, i2S)) {
                        D2 d2A3 = b5.a();
                        h0D2 = d2A3.d(d2A3.b(c0322w2U4));
                        if (h0D2 != null && h0D2.getAccessFlags().n()) {
                        }
                    }
                } else {
                    if (!e0D.b0()) {
                        if (!c0333y.C.c.contains(e0D.e)) {
                            c0333y.g().getClass();
                            i2W0 = c0322w2U4.w0();
                            i2S = b5.s();
                            i2W0.getClass();
                            if (I2.a(i2W0, i2S)) {
                                D2 d2A4 = b5.a();
                                h0D2 = d2A4.d(d2A4.b(c0322w2U4));
                                if (h0D2 != null) {
                                }
                            }
                        }
                    }
                    h0A = e0D.a(c0322w2U4);
                }
                return h0A != null ? new N5(h0A, this) : new Y5(this);
            }
            C0333y<C3403i> c0333yV3 = c0333y.V();
            h0D2 = ((C3403i) c0333yV3.g()).b(c0322w2U4, b5, c0333yV3);
            if (!e) {
                I2 i2W3 = c0322w2U4.w0();
                I2 i2S4 = b5.s();
                i2W3.getClass();
                if (I2.a(i2W3, i2S4)) {
                    D2 d2A5 = b5.a();
                    h0D3 = d2A5.d(d2A5.b(c0322w2U4));
                    if (h0D3 == null || !h0D3.getAccessFlags().n()) {
                        h0D3 = null;
                    }
                } else {
                    h0D3 = null;
                }
                if (h0D3 != null && !AbstractC1047aC.k && !h0D3.b(h0D2)) {
                    x1f.a();
                    return null;
                }
            }
            h0A = h0D2;
            if (h0A != null) {
            }
        }

        @Override // com.android.tools.r8.graph.T4
        public final H0 b(D2 d2, C0333y c0333y, C0229j c0229j) {
            if (!a((InterfaceC0332x5) d2, c0333y, c0229j).a() && this.d.z0()) {
                return H0.a(this.c, this.d);
            }
            return null;
        }

        @Override // com.android.tools.r8.graph.T4
        public final H0 c(D2 d2, C0333y c0333y, C0229j c0229j) {
            E0 e0;
            if (this.d.q1() || !(((e0 = this.b) == d2 || c0229j.b(d2.e, e0.e)) && a((InterfaceC0332x5) d2, c0333y, c0229j).c())) {
                return null;
            }
            return a(d2, c0229j, new BiPredicate() { // from class: d0e
                @Override // java.util.function.BiPredicate
                public final boolean test(Object obj, Object obj2) {
                    return T4.c.a((E0) obj, (E0) obj2);
                }
            });
        }

        @Override // com.android.tools.r8.graph.T4, com.android.tools.r8.graph.T5
        public T d() {
            return (T) this.c;
        }

        @Override // com.android.tools.r8.graph.T4, com.android.tools.r8.graph.D4
        public final T5 g() {
            return this;
        }

        @Override // com.android.tools.r8.graph.T4, com.android.tools.r8.graph.D4
        public final boolean j() {
            return true;
        }

        @Override // com.android.tools.r8.graph.T4
        public final c o() {
            return this;
        }

        @Override // com.android.tools.r8.graph.T4
        public final H0 p() {
            return H0.a(this.c, this.d);
        }

        @Override // com.android.tools.r8.graph.T4
        public C0231j1 q() {
            return this.d;
        }

        @Override // com.android.tools.r8.graph.T4
        public final B5 r() {
            if (this.c.a0()) {
                return new B5(this.c.X(), this.d);
            }
            return null;
        }

        @Override // com.android.tools.r8.graph.T4
        public final boolean w() {
            return true;
        }

        @Override // com.android.tools.r8.graph.T4
        public final boolean x() {
            return this.d.B1();
        }

        @Override // com.android.tools.r8.graph.T4
        public final AbstractC2173nV b(InterfaceC0332x5 interfaceC0332x5, C0333y c0333y) {
            if (this.d.B1()) {
                return a(interfaceC0332x5, c0333y, (C0229j) c0333y.g());
            }
            return AbstractC2173nV.b;
        }

        @Override // com.android.tools.r8.graph.T5
        public final AbstractC0217h1 b() {
            return this.d;
        }

        @Override // com.android.tools.r8.graph.T5
        public final G0 c() {
            return H0.a(this.c, this.d);
        }

        public static /* synthetic */ boolean a(E0 e0, E0 e1) {
            return true;
        }

        public final void a(C0317v4 c0317v4, C0229j c0229j, A4 a4, D2 d2) {
            if (c0317v4.a.a(d2)) {
                if (c0317v4.b == null) {
                    c0317v4.b = AbstractC2780ub0.c();
                }
                c0317v4.b.add(d2.e);
            }
            I2 i2 = this.c.e;
            Objects.requireNonNull(a4);
            InterfaceC0331x4 interfaceC0331x4A = a(d2, c0229j, i2, new a0e(a4), new b0e(a4));
            if (interfaceC0331x4A != null) {
                E0 e0A = interfaceC0331x4A.a();
                if (c0317v4.a.a(e0A)) {
                    if (c0317v4.b == null) {
                        c0317v4.b = AbstractC2780ub0.c();
                    }
                    c0317v4.b.add(e0A.e);
                }
                C0231j1 c0231j1E = interfaceC0331x4A.e();
                if (c0317v4.a.a(c0231j1E)) {
                    if (c0317v4.c == null) {
                        c0317v4.c = AbstractC2780ub0.c();
                    }
                    c0317v4.c.add(c0231j1E.getReference());
                }
                a(interfaceC0331x4A, this.c.isInterface(), a4);
            }
        }

        @Override // com.android.tools.r8.graph.D4
        public final AbstractC2173nV a(InterfaceC0332x5 interfaceC0332x5, C0333y c0333y, C0229j c0229j) {
            return AbstractC0194e.a(c(), a(), interfaceC0332x5, c0333y, c0229j);
        }

        public final C4 a(C1819jJ c1819jJ, C0229j c0229j, Consumer consumer, Consumer consumer2) {
            if (c1819jJ.b.a(this.d)) {
                C0322w2 c0322w2P0 = c1819jJ.d.p0();
                I2 i2W0 = c0322w2P0.w0();
                H0 h0A = c0322w2P0.a(i2W0 == null ? null : c0229j.g(i2W0).P());
                if (h0A == null) {
                    return null;
                }
                return new C0324w4(c1819jJ, h0A);
            }
            T4 t4A = c0229j.a(c1819jJ, this.d.getReference());
            if (t4A.w()) {
                return t4A.p();
            }
            if (t4A.h()) {
                t4A.k().a((Consumer<I2>) consumer, (Consumer<? super C0231j1>) consumer2);
                return null;
            }
            if (!e && !(t4A instanceof P4)) {
                x1f.a();
            }
            return null;
        }

        public final com.android.tools.r8.ir.optimize.info.h a(C0333y c0333y, AbstractC1047aC abstractC1047aC, H0 h0) {
            if (h0 != null) {
                return h0.D();
            }
            abstractC1047aC.getClass();
            if (((abstractC1047aC instanceof YB) || abstractC1047aC.c2()) && this.d.M0()) {
                return c0333y.o.a(this.c, this.d);
            }
            return C3263d.b;
        }

        /* JADX WARN: Code duplicated, block: B:13:0x004b  */
        /* JADX WARN: Code duplicated, block: B:20:0x0065  */
        public final AbstractC0184c3 a(C0333y c0333y, AbstractC1133bC abstractC1133bC, C0491Fm c0491Fm, B5 b5) {
            c<T> cVar;
            H0 h0A;
            E0 e0D;
            H0 h0A2;
            C0322w2 c0322w2U2 = abstractC1133bC.U2();
            if (c0333y.g().i()) {
                C0333y<C3403i> c0333yV = c0333y.V();
                cVar = this;
                h0A = ((C3403i) c0333yV.g()).a(c0333yV, c0322w2U2, cVar, b5, abstractC1133bC.T2(), c0333y, c0491Fm);
            } else {
                cVar = this;
                I2 i2W0 = c0322w2U2.w0();
                if (!i2W0.M0() || (e0D = c0333y.d(i2W0)) == null) {
                    h0A = null;
                } else if (!e0D.b0()) {
                    if (c0333y.C.c.contains(e0D.getType())) {
                        h0A2 = e0D.a(c0322w2U2);
                        if (h0A2 == null && (e0D.f.f() || h0A2.getAccessFlags().f())) {
                            h0A = h0A2;
                        } else {
                            h0A = null;
                        }
                    } else {
                        h0A = null;
                    }
                } else {
                    h0A2 = e0D.a(c0322w2U2);
                    if (h0A2 == null) {
                        h0A = null;
                    } else {
                        h0A = null;
                    }
                }
            }
            if (h0A != null) {
                return new N5(h0A, cVar);
            }
            return new Y5(cVar);
        }

        @Override // com.android.tools.r8.graph.T4, com.android.tools.r8.graph.T5
        public final E0 a() {
            return this.b;
        }

        @Override // com.android.tools.r8.graph.T4
        public final H0 a(D2 d2, C0333y c0333y) {
            final C0229j c0229j = (C0229j) c0333y.g();
            if (a((InterfaceC0332x5) d2, (C0333y<? extends C0229j>) c0333y).c()) {
                return a(d2, c0229j, new BiPredicate() { // from class: e0e
                    @Override // java.util.function.BiPredicate
                    public final boolean test(Object obj, Object obj2) {
                        return T4.c.a(c0229j, (E0) obj, (E0) obj2);
                    }
                });
            }
            return null;
        }

        @Override // com.android.tools.r8.graph.T4
        public final H0 a(D2 d2, C0333y c0333y, C0229j c0229j) {
            if (a((InterfaceC0332x5) d2, c0333y, c0229j).a()) {
                return null;
            }
            C0231j1 c0231j1 = this.d;
            c0231j1.O0();
            if ((c0231j1.g.i() || c0231j1.g.L()) && !c0231j1.g.n()) {
                return H0.a(this.c, this.d);
            }
            return null;
        }

        public final H0 a(D2 d2, C0229j c0229j, BiPredicate biPredicate) {
            E0 e0P;
            if (q().z0()) {
                return null;
            }
            if (d().isInterface() && q().J0()) {
                return H0.a(this.c, this.d);
            }
            E0 e0 = this.b;
            if (this.d.q1() || e0.isInterface() || !biPredicate.test(e0, d2)) {
                e0P = e0;
            } else {
                I2 i2 = d2.g;
                e0P = i2 == null ? null : c0229j.g(i2).P();
            }
            if (e0P == null) {
                return null;
            }
            C0322w2 c0322w2H0 = q().getReference();
            E0 e0P2 = e0P;
            H0 h0B = null;
            while (e0P2 != null) {
                h0B = e0P2.a(c0322w2H0);
                if (h0B != null) {
                    break;
                }
                I2 i3 = e0P2.g;
                e0P2 = i3 == null ? null : c0229j.g(i3).P();
            }
            if (h0B == null) {
                h0B = c0229j.b(e0P, c0322w2H0);
            }
            if (h0B == null || h0B.getAccessFlags().n()) {
                return null;
            }
            if (((h0B.e().q1() || h0B.getAccessFlags().i()) && h0B.s() != e0.e) || h0B.getAccessFlags().J()) {
                return null;
            }
            return h0B;
        }

        public static boolean a(C0229j c0229j, E0 e0, E0 e1) {
            return c0229j.b(e1.e, e0.e);
        }

        @Override // com.android.tools.r8.graph.T4
        public final B4 a(D2 d2, final C0333y c0333y, Z3 z3, InterfaceC0318v5 interfaceC0318v5) {
            final C0229j c0229j = (C0229j) c0333y.g();
            boolean z = e;
            if (!z && !c0229j.c(this.b.e, this.c.e)) {
                xzd.a(this.b.e, " is not a subtype of ", this.c.e);
                return null;
            }
            if (d2 != null && a((InterfaceC0332x5) d2, (C0333y<? extends C0229j>) c0333y).a()) {
                return C0345z4.a;
            }
            if (this.d.v1()) {
                boolean z2 = interfaceC0318v5.a(this.c) && interfaceC0318v5.a(this.d);
                H0 h0A = H0.a(this.c, this.d);
                Map mapSingletonMap = Collections.singletonMap(h0A.getReference(), h0A);
                List list = Collections.EMPTY_LIST;
                return B4.a(mapSingletonMap, list, list, z2 ? 2 : 1);
            }
            if (!z && !this.d.u1()) {
                x1f.a();
                return null;
            }
            final A4 a4D = B4.a.d();
            final C0317v4 c0317v4 = new C0317v4(interfaceC0318v5);
            z3.a(this.b.e, new Consumer() { // from class: yzd
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a(c0317v4, c0229j, a4D, (D2) obj);
                }
            }, new Consumer() { // from class: zzd
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a(c0333y, c0229j, a4D, (C1819jJ) obj);
                }
            });
            return a4D.a(c0317v4.a(this.d.getReference(), c0229j)).a();
        }

        public final void a(C0333y c0333y, C0229j c0229j, A4 a4, C1819jJ c1819jJ) {
            if (!e && !this.c.isInterface() && this.c.e != c0333y.a().a2) {
                x1f.a();
                return;
            }
            Objects.requireNonNull(a4);
            C4 c4A = a(c1819jJ, c0229j, new a0e(a4), new b0e(a4));
            if (c4A != null) {
                if (c4A.n()) {
                    a4.b.add(c4A.o());
                } else {
                    a(c4A.f(), this.c.isInterface(), a4);
                }
            }
        }

        @Override // com.android.tools.r8.graph.T4
        public final B4 a(D2 d2, C0333y c0333y, D2 d3, D2 d4) {
            C3403i c3403i = (C3403i) c0333y.g();
            boolean z = e;
            if (!z && d3 == null) {
                x1f.a();
                return null;
            }
            if (!z && !c3403i.c(d3.e, this.b.e)) {
                x1f.a();
                return null;
            }
            if (!z && d4 != null && !c3403i.c(d4.e, d3.e)) {
                x1f.a();
                return null;
            }
            C1975l7 c1975l7 = new C1975l7(Boolean.FALSE);
            Z3 z3A = a(c3403i, d3, d4, c1975l7);
            Objects.requireNonNull(c3403i);
            B4 b4A = a(d2, c0333y, z3A, new wzd(c3403i));
            if (((Boolean) c1975l7.a()).booleanValue() && b4A.c()) {
                b4A.a().d = 2;
            }
            return b4A;
        }

        public static Z3 a(final C3403i c3403i, final D2 d2, final D2 d3, final C1975l7 c1975l7) {
            return new Z3() { // from class: f0e
                @Override // com.android.tools.r8.graph.Z3
                public final void a(I2 i2, Consumer consumer, Consumer consumer2) {
                    T4.c.a(c3403i, c1975l7, d3, d2, i2, consumer, consumer2);
                }
            };
        }

        public static void a(final C3403i c3403i, final C1975l7 c1975l7, D2 d2, D2 d3, I2 i2, final Consumer consumer, Consumer consumer2) {
            Consumer consumer3 = new Consumer() { // from class: c0e
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    T4.c.a(consumer, c3403i, c1975l7, (D2) obj);
                }
            };
            if (d2 == null) {
                c3403i.t.a(d3.e, consumer3, consumer2, c3403i);
            } else {
                c3403i.a(d3, d2, consumer3);
            }
        }

        public static void a(Consumer consumer, C3403i c3403i, C1975l7 c1975l7, D2 d2) {
            consumer.accept(d2);
            if (!C3403i.J) {
                c3403i.c();
            }
            if (c3403i.t.e(d2)) {
                c1975l7.a(Boolean.TRUE);
            }
        }

        public static void a(InterfaceC0331x4 interfaceC0331x4, boolean z, A4 a4) {
            boolean z2 = e;
            if (!z2) {
                interfaceC0331x4.getClass();
            }
            C0231j1 c0231j1E = interfaceC0331x4.f().e();
            if (!z2) {
                c0231j1E.O0();
                if (c0231j1E.g.i()) {
                    x1f.a();
                    return;
                }
            }
            if (z) {
                if (c0231j1E.n1()) {
                    a4.a(interfaceC0331x4);
                }
                if (c0231j1E.g.J() || !c0231j1E.g.K()) {
                    return;
                }
                a4.a(interfaceC0331x4);
                return;
            }
            a4.a(interfaceC0331x4);
        }

        @Override // com.android.tools.r8.graph.T4
        public final C4 a(com.android.tools.r8.shaking.V0 v0, C0229j c0229j) {
            v0.getClass();
            if (v0 instanceof com.android.tools.r8.shaking.T0) {
                return a(v0.a(), c0229j);
            }
            return a(v0.b(), c0229j, C0822Sg.b(), C0822Sg.b());
        }

        @Override // com.android.tools.r8.graph.T4
        public final InterfaceC0331x4 a(E0 e0, C0229j c0229j) {
            return a(e0, c0229j, this.b.e, C0822Sg.b(), C0822Sg.b());
        }

        public final InterfaceC0331x4 a(E0 e0, C0229j c0229j, I2 i2, Consumer consumer, Consumer consumer2) {
            if (!e && !c0229j.c(e0.e, i2)) {
                xzd.a(e0.e, " is not a subtype of ", i2);
                return null;
            }
            H0 h0A = H0.a(this.c, this.d);
            if (this.d.v1()) {
                return h0A;
            }
            boolean zG = this.d.g.g();
            E0 e0P = e0;
            H0 h0D = h0A;
            while (e0P != null) {
                C0231j1 c0231j1E = h0D.e();
                C0231j1 c0231j1D = e0P.d(c0231j1E.getReference());
                boolean z = e;
                if (!z && c0231j1D != null) {
                    c0231j1D.O0();
                    if (c0231j1D.g.i()) {
                        x1f.a();
                        return null;
                    }
                }
                if (c0231j1D == null) {
                    c0231j1D = null;
                } else if (!a(c0231j1E, c0231j1D)) {
                    c0231j1D = C0231j1.v;
                }
                C0231j1 c0231j1 = C0231j1.v;
                if (c0231j1D == c0231j1 && zG) {
                    if (!z && !h0A.e().getAccessFlags().g()) {
                        x1f.a();
                        return null;
                    }
                    E0 e0P2 = e0P;
                    while (true) {
                        if (e0P2.l1()) {
                            I2 i2D1 = e0P2.d1();
                            e0P2 = i2D1 == null ? null : c0229j.g(i2D1).P();
                            if (e0P2 != null) {
                                h0D = e0P2.d(e0P2.d(h0A.getReference()));
                                if (h0D != null && a(h0A.e(), h0D.e()) && (h0D.getAccessFlags().m() || h0D.getAccessFlags().l())) {
                                    if (!e && h0A.e() == h0D.e()) {
                                        x1f.a();
                                        return null;
                                    }
                                }
                            }
                            zG = false;
                        }
                        h0D = h0A;
                        zG = false;
                    }
                } else {
                    if (c0231j1D != null && c0231j1D != c0231j1) {
                        H0 h0A2 = H0.a(e0P, c0231j1D);
                        return h0D != h0A ? new C0338y4(h0A2, h0D) : h0A2;
                    }
                    if (e0P.e == h0D.s()) {
                        return null;
                    }
                    I2 i3 = e0P.g;
                    e0P = i3 == null ? null : c0229j.g(i3).P();
                }
            }
            if (!this.c.isInterface()) {
                return null;
            }
            T4 t4C = c0229j.c(e0, this.d.getReference());
            if (t4C.w()) {
                return t4C.p();
            }
            if (t4C.h()) {
                t4C.k().a((Consumer<I2>) consumer, (Consumer<? super C0231j1>) consumer2);
                return null;
            }
            if (!e && !(t4C instanceof P4)) {
                x1f.a();
            }
            return null;
        }

        public static boolean a(C0231j1 c0231j1, C0231j1 c0231j2) {
            boolean z = e;
            if (!z && !c0231j1.getReference().d(c0231j2.getReference())) {
                x1f.a();
                return false;
            }
            if (!z) {
                c0231j2.O0();
                if (c0231j2.g.i()) {
                    x1f.a();
                    return false;
                }
            }
            if (c0231j1.g.m() || c0231j1.g.l()) {
                return true;
            }
            if (!z && !c0231j1.g.g()) {
                x1f.a();
                return false;
            }
            return c0231j1.E0().D0().equals(c0231j2.E0().D0());
        }
    }

    public E0 a() {
        return null;
    }

    public boolean a(E0 e0, C0333y<?> c0333y, C0229j c0229j) {
        return false;
    }

    public final boolean a(E0 e0, C0333y<? extends C0229j> c0333y) {
        return a(e0, c0333y, (C0229j) c0333y.g());
    }

    public final void a(Consumer<T4> consumer) {
        a(consumer, consumer, consumer, consumer);
    }
}
