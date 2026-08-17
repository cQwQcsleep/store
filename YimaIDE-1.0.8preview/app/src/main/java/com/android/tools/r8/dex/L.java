package com.android.tools.r8.dex;

import com.android.tools.r8.dex.L;
import com.android.tools.r8.errors.UnsupportedDefaultInterfaceMethodDiagnostic;
import com.android.tools.r8.errors.UnsupportedInvokeCustomDiagnostic;
import com.android.tools.r8.errors.UnsupportedPrivateInterfaceMethodDiagnostic;
import com.android.tools.r8.errors.UnsupportedStaticInterfaceMethodDiagnostic;
import com.android.tools.r8.graph.AbstractC0217h1;
import com.android.tools.r8.graph.AbstractC0223i0;
import com.android.tools.r8.graph.AbstractC0259n1;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0196e1;
import com.android.tools.r8.graph.C0203f1;
import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0270o5;
import com.android.tools.r8.graph.C0284q5;
import com.android.tools.r8.graph.C0285r0;
import com.android.tools.r8.graph.C0292s0;
import com.android.tools.r8.graph.C0299t0;
import com.android.tools.r8.graph.C0304t5;
import com.android.tools.r8.graph.C0306u0;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.C0336y2;
import com.android.tools.r8.graph.D0;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.E2;
import com.android.tools.r8.graph.EnumC0329x2;
import com.android.tools.r8.graph.F4;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.InterfaceC0170a3;
import com.android.tools.r8.graph.J0;
import com.android.tools.r8.graph.K2;
import com.android.tools.r8.graph.O2;
import com.android.tools.r8.graph.X3;
import com.android.tools.r8.graph.Z0;
import com.android.tools.r8.graph.Z2;
import com.android.tools.r8.internal.AbstractC2554rv;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C0613Ke;
import com.android.tools.r8.internal.C1202c30;
import com.android.tools.r8.internal.C1573gT;
import com.android.tools.r8.internal.C1627h30;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C2753uC;
import com.android.tools.r8.internal.CJ;
import com.android.tools.r8.internal.EnumC1095am;
import com.android.tools.r8.internal.EnumC3077y2;
import com.android.tools.r8.internal.Z20;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.MethodPosition;
import com.android.tools.r8.position.Position;
import com.sun.jna.platform.linux.Fcntl;
import java.nio.ShortBuffer;
import java.security.MessageDigest;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import java.util.zip.Adler32;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L {
    public static final /* synthetic */ boolean j = true;
    public final C0333y a;
    public final AbstractC3148ys b;
    public final C0284q5 c;
    public final C2752uB d;
    public final C0161y e;
    public final J f;
    public final r g;
    public final t0 h;
    public final boolean i;

    public L(C0333y c0333y, C0161y c0161y, C0284q5 c0284q5, r rVar, t0 t0Var, boolean z) {
        this.a = c0333y;
        this.b = c0333y.A();
        this.c = c0284q5;
        C2752uB c2752uBM = c0333y.M();
        this.d = c2752uBM;
        this.e = c0161y;
        this.f = new J(c2752uBM);
        this.g = rVar;
        this.h = t0Var;
        this.i = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final G a(int i, int i2) {
        byte[] bArrA;
        for (D2 d2 : this.c.e) {
            if (d2.isInterface()) {
                Iterator<C0231j1> it = d2.L0().iterator();
                while (it.hasNext()) {
                    a(d2, it.next());
                }
                Iterator<C0231j1> it2 = d2.G1().iterator();
                while (it2.hasNext()) {
                    a(d2, it2.next());
                }
            }
        }
        G g = null;
        if (!j && !this.a.a().d6) {
            EnumC3077y2 enumC3077y2F = this.d.F();
            for (C0245l1 c0245l1 : C0284q5.a(this.c.i)) {
                if (!j && !c0245l1.g.c(enumC3077y2F)) {
                    x1f.a();
                    return null;
                }
            }
            for (C0322w2 c0322w2 : C0284q5.a(this.c.h)) {
                if (!j && !c0322w2.g.c(enumC3077y2F)) {
                    x1f.a();
                    return null;
                }
            }
            for (I2 i3 : C0284q5.a(this.c.g)) {
                if (i3.M0()) {
                    boolean z = j;
                    if (!z && !H2.a(enumC3077y2F, i3.B0())) {
                        x1f.a();
                        return null;
                    }
                    if (!z) {
                        boolean z2 = com.android.tools.r8.synthesis.S.T;
                        com.android.tools.r8.synthesis.S.a(i3.Z0());
                    }
                }
            }
        }
        C0284q5 c0284q5 = this.c;
        boolean z3 = this.i;
        boolean z4 = H.y;
        if (!z4 && i != 0 && i2 != 2) {
            x1f.a();
            return null;
        }
        int i4 = (i2 == 2 ? 120 : 112) + i;
        int size = (z3 ? C0284q5.a(c0284q5.j).size() * 4 : 0) + i4;
        int size2 = (C0284q5.a(c0284q5.g).size() * 4) + size;
        int size3 = (C0284q5.a(c0284q5.f).size() * 12) + size2;
        int size4 = (C0284q5.a(c0284q5.i).size() * 8) + size3;
        int size5 = (C0284q5.a(c0284q5.h).size() * 8) + size4;
        int length = (c0284q5.e.length * 32) + size5;
        int size6 = (C0284q5.a(c0284q5.k).size() * 4) + length;
        int size7 = (C0284q5.a(c0284q5.l).size() * 8) + size6;
        final H h = new H(i, i4, size, size2, size3, size4, size5, length, size6, size7, i2);
        int i5 = -1;
        if (!z4 && h.l != -1) {
            x1f.a();
            return null;
        }
        h.l = size7;
        Y yA = Y.a(this.a, this.f, this.h);
        Collection<B5> collectionF = yA.f();
        HashSet hashSet = new HashSet();
        int i6 = 0;
        int i7 = 0;
        for (B5 b5 : collectionF) {
            InterfaceC0170a3 interfaceC0170a3P0 = b5.e().U0().p0();
            C2752uB c2752uB = this.d;
            c2752uB.getClass();
            g = g;
            if (!c2752uB.b(EnumC3077y2.G) || hashSet.add(interfaceC0170a3P0.a(b5, this.a.a()))) {
                i7++;
                int i8 = (i6 + 3) & (-4);
                C0333y c0333y = this.a;
                ((AbstractC0223i0) interfaceC0170a3P0).getClass();
                AbstractC3148ys abstractC3148ys = c0333y.g;
                int length2 = (interfaceC0170a3P0.r().length * 8) + (interfaceC0170a3P0.S() * 2) + 16;
                if (interfaceC0170a3P0.w().length > 0) {
                    length2 = CJ.d(interfaceC0170a3P0.w().length) + ((length2 + 3) & (-4));
                    J0.b[] bVarArrW = interfaceC0170a3P0.w();
                    int length3 = bVarArrW.length;
                    int i9 = 0;
                    while (i9 < length3) {
                        J0.b bVar = bVarArrW[i9];
                        boolean z5 = bVar.c != i5;
                        J0.b.a[] aVarArr = bVar.b;
                        int iC = CJ.c(z5 ? -aVarArr.length : aVarArr.length) + length2;
                        J0.b.a[] aVarArr2 = bVar.b;
                        int i10 = i9;
                        int length4 = aVarArr2.length;
                        int iD = iC;
                        int i11 = 0;
                        while (i11 < length4) {
                            int i12 = length4;
                            J0.b.a aVar = aVarArr2[i11];
                            iD = CJ.d(aVar.c) + CJ.d(C0284q5.a(this.b.c(abstractC3148ys, aVar.b), this.c.g)) + iD;
                            i11++;
                            length4 = i12;
                            yA = yA;
                            collectionF = collectionF;
                        }
                        Y y = yA;
                        Collection<B5> collection = collectionF;
                        length2 = z5 ? CJ.d(bVar.c) + iD : iD;
                        i9 = i10 + 1;
                        yA = y;
                        collectionF = collection;
                        i5 = -1;
                    }
                }
                i6 = i8 + length2;
                yA = yA;
                collectionF = collectionF;
                i5 = -1;
            }
        }
        Y y2 = yA;
        Collection<B5> collection2 = collectionF;
        G g2 = g;
        C0161y c0161y = this.e;
        boolean z6 = H.y;
        if (!z6 && !H.a(h.l, true)) {
            x1f.a();
            return g2;
        }
        c0161y.e(h.l + i6);
        if (((AbstractCollection) this.f.b.keySet()).isEmpty()) {
            if (!z6 && h.m != -1) {
                x1f.a();
                return g2;
            }
            h.m = 0;
        } else {
            int iA = this.e.a(1);
            if (!z6 && h.m != -1) {
                x1f.a();
                return g2;
            }
            h.m = iA;
            HashSet hashSet2 = new HashSet(((C1573gT) this.f.b.keySet()).b.l);
            Iterator<B5> it3 = collection2.iterator();
            while (it3.hasNext()) {
                Z0 z0C = it3.next().e().U0().p0().C();
                if (z0C != null && hashSet2.add(z0C)) {
                    AbstractC3148ys abstractC3148ys2 = this.a.g;
                    J.a(z0C, this.e.b(), this.f.b);
                    this.e.a(new C0157u(z0C, this.c, this.b, abstractC3148ys2).a());
                }
            }
        }
        int iA2 = this.e.a(4);
        boolean z7 = H.y;
        if (!z7 && h.n != -1) {
            x1f.a();
            return g2;
        }
        h.n = iA2;
        C0161y c0161y2 = this.e;
        if (!z7 && !H.a(h.l, true)) {
            x1f.a();
            return g2;
        }
        c0161y2.e(h.l);
        if (!j && this.e.b() % 4 != 0) {
            x1f.a();
            return g2;
        }
        HashMap map = new HashMap();
        for (B5 b6 : collection2) {
            InterfaceC0170a3 interfaceC0170a3P1 = b6.e().U0().p0();
            C2752uB c2752uB2 = this.d;
            c2752uB2.getClass();
            if (c2752uB2.b(EnumC3077y2.G)) {
                Z2 z2A = interfaceC0170a3P1.a(b6, this.a.a());
                Integer num = (Integer) map.get(z2A);
                if (num != null) {
                    J.a(b6.e(), num.intValue(), this.f.a);
                } else {
                    map.put(z2A, Integer.valueOf(a(b6, interfaceC0170a3P1)));
                }
            } else {
                a(b6, interfaceC0170a3P1);
            }
        }
        boolean z8 = j;
        if (!z8) {
            C1627h30 c1627h30 = this.f.a;
            if (c1627h30.o == null) {
                c1627h30.o = new Z20(c1627h30);
            }
            if (i7 != AbstractC2554rv.a(c1627h30.o).size()) {
                x1f.a();
                return g2;
            }
        }
        boolean z9 = H.y;
        if (!z9 && h.x != -1) {
            x1f.a();
            return g2;
        }
        h.x = i7;
        if (!z8) {
            if (!z9 && !H.a(h.m, false)) {
                x1f.a();
                return g2;
            }
            if (h.m != 0) {
                int iB = this.e.b();
                if (!z9 && !H.a(h.m, false)) {
                    x1f.a();
                    return g2;
                }
                if (iB != h.m) {
                    x1f.a();
                    return g2;
                }
            }
        }
        C0161y c0161y3 = this.e;
        if (!z9 && !H.a(h.n, true)) {
            x1f.a();
            return g2;
        }
        c0161y3.e(h.n);
        a(y2.i(), new Consumer() { // from class: ik8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                h.a(((Integer) obj).intValue());
            }
        }, new Consumer() { // from class: lj8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((K2) obj);
            }
        }, 1);
        if (this.i) {
            a(y2.h(), new Consumer() { // from class: rj8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    h.h(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: tj8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a((H2) obj);
                }
            }, 1);
        } else {
            h.o = 0;
        }
        a(y2.b(), new Consumer() { // from class: uj8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                h.e(((Integer) obj).intValue());
            }
        }, new Consumer() { // from class: vj8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C0285r0) obj);
            }
        }, 1);
        a(y2.e(), new Consumer() { // from class: wj8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                h.f(((Integer) obj).intValue());
            }
        }, new Consumer() { // from class: xj8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.b((D2) obj);
            }
        }, 1);
        a(y2.g(), new Consumer() { // from class: yj8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                h.g(((Integer) obj).intValue());
            }
        }, new Consumer() { // from class: zj8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C0203f1) obj);
            }
        }, 1);
        a(y2.c(), new Consumer() { // from class: jk8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                h.d(((Integer) obj).intValue());
            }
        }, new Consumer() { // from class: kk8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C0306u0) obj);
            }
        }, 4);
        a(y2.d(), new Consumer() { // from class: lk8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                h.c(((Integer) obj).intValue());
            }
        }, new Consumer() { // from class: mk8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C0304t5) obj);
            }
        }, 4);
        a(y2.a(), new Consumer() { // from class: nk8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                h.b(((Integer) obj).intValue());
            }
        }, new Consumer() { // from class: fj8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C0292s0) obj);
            }
        }, 4);
        int iA3 = this.e.a(4);
        h.v = iA3;
        this.e.d(4);
        int iA4 = 0;
        for (I i13 : h.a(this, 0, ((C1202c30) this.f.d.keySet()).b.l, h.b, h.b())) {
            iA4 += this.i ? i13.a(this.e) : (i13.c != 0 || i13.a == 8194) ? 1 : 0;
        }
        this.e.e(iA3);
        this.e.f(iA4);
        this.e.d(iA4 * 12);
        h.w = this.e.b();
        this.e.e((h.k == 2 ? 120 : 112) + h.a);
        if (this.i) {
            a(C0284q5.a(this.c.j), h.b, new Consumer() { // from class: hj8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.b((H2) obj);
                }
            });
        } else if (!j && h.b != h.c) {
            x1f.a();
            return g2;
        }
        a(C0284q5.a(this.c.g), h.c, new Consumer() { // from class: ij8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((I2) obj);
            }
        });
        a(C0284q5.a(this.c.f), h.d, new Consumer() { // from class: jj8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((E2) obj);
            }
        });
        a(C0284q5.a(this.c.i), h.e, new Consumer() { // from class: kj8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C0245l1) obj);
            }
        });
        a(C0284q5.a(this.c.h), h.f, new Consumer() { // from class: mj8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C0322w2) obj);
            }
        });
        D2[] d2Arr = this.c.e;
        int i14 = h.g;
        Consumer consumer = new Consumer() { // from class: nj8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.c((D2) obj);
            }
        };
        if (!j && this.e.b() != i14) {
            x1f.a();
            return g2;
        }
        for (D2 d3 : d2Arr) {
            consumer.accept(d3);
        }
        a(C0284q5.a(this.c.k), h.h, new Consumer() { // from class: oj8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((D0) obj);
            }
        });
        a(C0284q5.a(this.c.l), h.i, new Consumer() { // from class: pj8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C0336y2) obj);
            }
        });
        this.e.e(h.a);
        this.e.a(C0156t.a);
        C0161y c0161y4 = this.e;
        C2752uB c2752uB3 = this.d;
        C2752uB.q qVar = c2752uB3.u1;
        if (qVar.o) {
            bArrA = EnumC1095am.V41.a();
        } else if (qVar.n && c2752uB3.F().a(EnumC3077y2.F)) {
            bArrA = EnumC1095am.V40.a();
        } else {
            C2752uB c2752uB4 = this.d;
            byte[] bArr = c2752uB4.u1.v;
            bArrA = bArr != null ? bArr : EnumC1095am.c(c2752uB4.F()).a();
        }
        c0161y4.a(bArrA);
        this.e.a((byte) 0);
        this.e.e(h.a + 32);
        this.e.f(h.w - h.a);
        this.e.f(h.k == 2 ? 120 : 112);
        this.e.f(305419896);
        this.e.f(0);
        this.e.f(0);
        this.e.f(h.v);
        int size8 = C0284q5.a(this.c.j).size();
        this.e.f(size8);
        this.e.f(size8 == 0 ? 0 : h.b);
        int size9 = C0284q5.a(this.c.g).size();
        this.e.f(size9);
        this.e.f(size9 == 0 ? 0 : h.c);
        int size10 = C0284q5.a(this.c.f).size();
        this.e.f(size10);
        this.e.f(size10 == 0 ? 0 : h.d);
        int size11 = C0284q5.a(this.c.i).size();
        this.e.f(size11);
        this.e.f(size11 == 0 ? 0 : h.e);
        int size12 = C0284q5.a(this.c.h).size();
        this.e.f(size12);
        this.e.f(size12 == 0 ? 0 : h.f);
        int length5 = this.c.e.length;
        this.e.f(length5);
        this.e.f(length5 == 0 ? 0 : h.g);
        int i15 = h.k;
        C0161y c0161y5 = this.e;
        if (i15 == 2) {
            c0161y5.f(0);
            this.e.f(0);
            this.e.f(0);
            this.e.f(h.a);
        } else {
            int i16 = h.w - h.j;
            if (!H.y && i16 % 4 != 0) {
                x1f.a();
                return g2;
            }
            c0161y5.f(i16);
            this.e.f(h.j);
        }
        if (!j && this.e.b() != h.b) {
            x1f.a();
            return g2;
        }
        if (this.i) {
            b(h, this.e);
            a(h, this.e);
        }
        return new G(this, this.e, h);
    }

    public final void b(D2 d2) {
        if (!j && !d2.Q1()) {
            x1f.a();
            return;
        }
        J.a(d2, this.e.b(), this.f.i);
        C0161y c0161y = this.e;
        int size = d2.D1().size();
        c0161y.getClass();
        CJ.b(c0161y, size);
        C0161y c0161y2 = this.e;
        int size2 = d2.m1().size();
        c0161y2.getClass();
        CJ.b(c0161y2, size2);
        C0161y c0161y3 = this.e;
        int iG = d2.V().g();
        c0161y3.getClass();
        CJ.b(c0161y3, iG);
        C0161y c0161y4 = this.e;
        int iH = d2.V().h();
        c0161y4.getClass();
        CJ.b(c0161y4, iH);
        a((List) d2.D1());
        a((List) d2.m1());
        a(d2.L0());
        a(d2.G1());
    }

    public final void c(D2 d2) {
        int iB;
        this.g.a(d2);
        this.e.f(C0284q5.a(d2.e, this.c.g));
        C0161y c0161y = this.e;
        com.android.tools.r8.graph.Q q = d2.f;
        int i = q.c & (-65569);
        if (q.L()) {
            i |= Fcntl.S_ISGID;
        }
        c0161y.f(i);
        C0161y c0161y2 = this.e;
        I2 i2 = d2.g;
        c0161y2.f(i2 == null ? -1 : C0284q5.a(i2, this.c.g));
        C0161y c0161y3 = this.e;
        J j2 = this.f;
        K2 k2 = d2.h;
        j2.getClass();
        c0161y3.f(k2.isEmpty() ? 0 : J.b(j2.c, k2));
        C0161y c0161y4 = this.e;
        H2 h2 = d2.i;
        c0161y4.f(h2 != null ? C0284q5.a(h2, this.c.j) : -1);
        C0161y c0161y5 = this.e;
        J j3 = this.f;
        j3.getClass();
        if (d2.N1()) {
            iB = j3.h.b((C0292s0) j3.k.get(d2));
            if (!J.n && iB == -2) {
                x1f.a();
                return;
            }
        } else {
            iB = 0;
        }
        c0161y5.f(iB);
        this.e.f(d2.Q1() ? J.a(d2, this.f.i) : 0);
        C0161y c0161y6 = this.e;
        J j4 = this.f;
        c0161y6.f(J.b(j4.j, (C0203f1) j4.l.get(d2)));
    }

    public final int b(C0231j1 c0231j1) {
        J j2 = this.f;
        C0304t5 c0304t5 = c0231j1.i;
        j2.getClass();
        if (c0304t5.isEmpty()) {
            return 0;
        }
        return J.b(j2.g, c0304t5);
    }

    public final void b(H2 h2) {
        this.e.f(J.a(h2, this.f.d));
    }

    public static void b(H h, C0161y c0161y) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            byte[] bArrA = c0161y.a();
            int i = h.a;
            messageDigest.update(bArrA, i + 32, (h.w - i) - 32);
            messageDigest.digest(c0161y.a(), h.a + 12, 20);
        } catch (Exception e) {
            rc6.a(e);
        }
    }

    public final void a(I2 i2) {
        this.e.f(C0284q5.a(this.a.w().c(i2), this.c.j));
    }

    public static void a(C0196e1 c0196e1, C0161y c0161y, final C0284q5 c0284q5) {
        ArrayList<C0299t0> arrayList = new ArrayList(Arrays.asList(c0196e1.c));
        arrayList.sort(new Comparator() { // from class: ak8
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return L.a(c0284q5, (C0299t0) obj, (C0299t0) obj2);
            }
        });
        int iA = C0284q5.a(c0196e1.b, c0284q5.g);
        c0161y.getClass();
        CJ.b(c0161y, iA);
        CJ.b(c0161y, arrayList.size());
        for (C0299t0 c0299t0 : arrayList) {
            CJ.b(c0161y, C0284q5.a(c0299t0.b, c0284q5.j));
            c0299t0.c.a(c0161y, c0284q5);
        }
    }

    public static int a(C0284q5 c0284q5, C0299t0 c0299t0, C0299t0 c0299t1) {
        H2 h2 = c0299t0.b;
        H2 h3 = c0299t1.b;
        C0270o5 c0270o5 = c0284q5.o;
        h2.getClass();
        return c0270o5.a(h2, h3);
    }

    public final L a() {
        K k = new K(this, this.a, this.c.e);
        for (D2 d2 : this.c.e) {
            k.a(d2);
        }
        this.f.i.keySet().forEach(new Consumer() { // from class: ck8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((D2) obj);
            }
        });
        if (!j && this.f.d.l != 0) {
            x1f.a();
            return null;
        }
        for (H2 h2 : C0284q5.a(this.c.j)) {
            C1627h30 c1627h30 = this.f.d;
            if (!c1627h30.containsKey(h2)) {
                c1627h30.b(-1, h2);
            }
        }
        Iterator it = C0284q5.a(this.c.f).iterator();
        while (it.hasNext()) {
            this.f.a(((E2) it.next()).q0());
        }
        AbstractC0259n1.a(this.f, C0284q5.a(this.c.k));
        AbstractC0259n1.a(this.f, this.c.e);
        return this;
    }

    public final void a(D2 d2) {
        C0203f1 c0203f1A = d2.a(this.a.w());
        if (c0203f1A != null) {
            J j2 = this.f;
            C0203f1 c0203f1 = (C0203f1) j2.l.put(d2, c0203f1A);
            if (J.n || c0203f1 == null) {
                J.a(j2.j, c0203f1A);
            } else {
                x1f.a();
            }
        }
    }

    public final void a(D2 d2, C0231j1 c0231j1) {
        if (this.a.a().a(c0231j1.getReference())) {
            return;
        }
        if (c0231j1.g.n()) {
            if (!this.d.i()) {
                C2752uB c2752uB = this.d;
                if (!c2752uB.u1.w0) {
                    c2752uB.i.a(new UnsupportedStaticInterfaceMethodDiagnostic(d2.getOrigin(), MethodPosition.create(c0231j1)));
                    throw null;
                }
            }
        } else if (!c0231j1.q1()) {
            if (!c0231j1.g.J() && !c0231j1.g.i() && !this.d.i()) {
                this.d.i.a(new UnsupportedDefaultInterfaceMethodDiagnostic(d2.getOrigin(), MethodPosition.create(c0231j1)));
                throw null;
            }
        } else {
            throw new C0613Ke("Interface must not have constructors: " + c0231j1.getReference().m0());
        }
        if (c0231j1.g.i()) {
            if (this.d.n()) {
                return;
            }
            this.d.i.a(new UnsupportedPrivateInterfaceMethodDiagnostic(d2.getOrigin(), MethodPosition.create(c0231j1)));
            throw null;
        }
        if (c0231j1.g.m()) {
            return;
        }
        throw new C0613Ke("Interface methods must not be protected or package private: " + c0231j1.getReference().m0());
    }

    public final void a(Collection collection, int i, Consumer consumer) {
        if (!j && this.e.b() != i) {
            x1f.a();
            return;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            consumer.accept((X3) it.next());
        }
    }

    public final void a(Collection collection, Consumer consumer, Consumer consumer2, int i) {
        if (collection.isEmpty()) {
            consumer.accept(0);
        } else {
            consumer.accept(Integer.valueOf(this.e.a(i)));
            collection.forEach(consumer2);
        }
    }

    public final void a(E2 e2) {
        this.e.f(C0284q5.a((H2) this.c.m.get(e2), this.c.j));
        this.e.f(C0284q5.a(e2.e, this.c.g));
        C0161y c0161y = this.e;
        J j2 = this.f;
        K2 k2 = e2.f;
        j2.getClass();
        c0161y.f(k2.isEmpty() ? 0 : J.b(j2.c, k2));
    }

    public final void a(C0245l1 c0245l1) {
        int iA = C0284q5.a(c0245l1.f, this.c.g);
        boolean z = j;
        if (!z && (iA & 65535) != iA) {
            x1f.a();
            return;
        }
        this.e.a((short) iA);
        int iA2 = C0284q5.a(c0245l1.i, this.c.g);
        if (!z && (iA2 & 65535) != iA2) {
            x1f.a();
            return;
        }
        this.e.a((short) iA2);
        this.e.f(C0284q5.a(this.a.w().a(c0245l1), this.c.j));
    }

    public final void a(C0322w2 c0322w2) {
        int iA = C0284q5.a(c0322w2.f, this.c.g);
        boolean z = j;
        if (!z && (iA & 65535) != iA) {
            x1f.a();
            return;
        }
        this.e.a((short) iA);
        int iA2 = C0284q5.a(c0322w2.i, this.c.f);
        if (!z && (iA2 & 65535) != iA2) {
            x1f.a();
            return;
        }
        this.e.a((short) iA2);
        this.e.f(C0284q5.a(this.a.w().a(c0322w2), this.c.j));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int a(B5 b5, InterfaceC0170a3 interfaceC0170a3) {
        C0333y c0333y = this.a;
        ((AbstractC0223i0) interfaceC0170a3).getClass();
        AbstractC3148ys abstractC3148ys = c0333y.g;
        int iA = this.e.a(4);
        J.a(b5.e(), iA, this.f.a);
        this.e.a((short) interfaceC0170a3.a(b5));
        this.e.a((short) interfaceC0170a3.b(b5));
        this.e.a((short) interfaceC0170a3.A());
        this.e.a((short) interfaceC0170a3.r().length);
        this.e.f(J.b(this.f.b, interfaceC0170a3.C()));
        int iB = this.e.b();
        this.e.d(4);
        C0161y c0161y = this.e;
        C0333y c0333y2 = this.a;
        C0284q5 c0284q5 = this.c;
        r rVar = this.g;
        c0161y.getClass();
        c0161y.c(interfaceC0170a3.S() * 2);
        int i = 0;
        if (!C0161y.c && c0161y.b.c().position() % 2 != 0) {
            x1f.a();
            return 0;
        }
        ShortBuffer shortBufferAsShortBuffer = c0161y.b.c().asShortBuffer();
        interfaceC0170a3.a(c0284q5, b5, c0284q5.c, c0333y2.g, c0284q5.d, shortBufferAsShortBuffer);
        interfaceC0170a3.a(rVar);
        C0155s c0155s = c0161y.b;
        c0155s.c((shortBufferAsShortBuffer.position() * 2) + c0155s.c().position());
        int iB2 = this.e.b() - iB;
        int i2 = iB2 - 4;
        this.e.d(-iB2);
        this.e.f(i2 / 2);
        this.e.d(i2);
        if (interfaceC0170a3.r().length > 0) {
            int iA2 = this.e.a(4);
            this.e.d(interfaceC0170a3.r().length * 8);
            int iB3 = this.e.b();
            C0161y c0161y2 = this.e;
            int length = interfaceC0170a3.w().length;
            c0161y2.getClass();
            CJ.b(c0161y2, length);
            short[] sArr = new short[interfaceC0170a3.w().length];
            J0.b[] bVarArrW = interfaceC0170a3.w();
            int length2 = bVarArrW.length;
            int i3 = 0;
            int i4 = 0;
            while (i3 < length2) {
                J0.b bVar = bVarArrW[i3];
                int i5 = i4 + 1;
                sArr[i4] = (short) (this.e.b() - iB3);
                int i6 = bVar.c != -1 ? 1 : i;
                C0161y c0161y3 = this.e;
                int length3 = bVar.b.length;
                if (i6 != 0) {
                    length3 = -length3;
                }
                c0161y3.getClass();
                CJ.a(c0161y3, length3);
                J0.b.a[] aVarArr = bVar.b;
                int length4 = aVarArr.length;
                while (i < length4) {
                    J0.b.a aVar = aVarArr[i];
                    int i7 = iB3;
                    C0161y c0161y4 = this.e;
                    short[] sArr2 = sArr;
                    int iA3 = C0284q5.a(this.b.c(abstractC3148ys, aVar.b), this.c.g);
                    c0161y4.getClass();
                    CJ.b(c0161y4, iA3);
                    C0161y c0161y5 = this.e;
                    int i8 = aVar.c;
                    c0161y5.getClass();
                    CJ.b(c0161y5, i8);
                    this.g.a(this.b.c(abstractC3148ys, aVar.b));
                    i++;
                    iB3 = i7;
                    sArr = sArr2;
                    bVarArrW = bVarArrW;
                    length2 = length2;
                }
                int i9 = iB3;
                short[] sArr3 = sArr;
                J0.b[] bVarArr = bVarArrW;
                int i10 = length2;
                if (i6 != 0) {
                    C0161y c0161y6 = this.e;
                    int i11 = bVar.c;
                    c0161y6.getClass();
                    CJ.b(c0161y6, i11);
                }
                i3++;
                iB3 = i9;
                i4 = i5;
                sArr = sArr3;
                bVarArrW = bVarArr;
                length2 = i10;
                i = 0;
            }
            short[] sArr4 = sArr;
            int iB4 = this.e.b();
            this.e.e(iA2);
            for (J0.a aVar2 : interfaceC0170a3.r()) {
                this.e.f(aVar2.c);
                this.e.a((short) aVar2.d);
                this.e.a(sArr4[aVar2.e]);
            }
            this.e.e(iB4);
        }
        return iA;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0024, code lost:
    
        if (r0.c.isEmpty() == false) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void a(K2 k2) {
        if (!j && k2.isEmpty()) {
            x1f.a();
            return;
        }
        J j2 = this.f;
        int iA = this.e.a(4);
        if (!J.n) {
            if (iA == 0) {
                j2.getClass();
            }
            x1f.a();
            return;
        }
        J.a(k2, iA, j2.c);
        I2[] i2Arr = k2.b;
        this.e.f(i2Arr.length);
        for (I2 i2 : i2Arr) {
            this.e.a((short) C0284q5.a(i2, this.c.g));
        }
    }

    public final void a(H2 h2) {
        J.a(h2, this.e.b(), this.f.d);
        C0161y c0161y = this.e;
        int i = h2.e;
        c0161y.getClass();
        CJ.b(c0161y, i);
        this.e.a(h2.f);
    }

    public final void a(C0285r0 c0285r0) {
        J.a(c0285r0, this.e.b(), this.f.e);
        this.e.a((byte) c0285r0.b);
        a(c0285r0.c, this.e, this.c);
    }

    public final void a(C0306u0 c0306u0) {
        J j2 = this.f;
        int iA = this.e.a(4);
        if (!J.n) {
            C2752uB c2752uB = j2.m;
            c2752uB.getClass();
            if (!c2752uB.a(EnumC3077y2.s) && c0306u0.isEmpty()) {
                x1f.a();
                return;
            }
        }
        J.a(c0306u0, iA, j2.f);
        ArrayList arrayList = new ArrayList(Arrays.asList(c0306u0.d));
        arrayList.sort(new Comparator() { // from class: ej8
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return this.b.a((C0285r0) obj, (C0285r0) obj2);
            }
        });
        this.e.f(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.e.f(J.b(this.f.e, (C0285r0) it.next()));
        }
    }

    public final int a(C0285r0 c0285r0, C0285r0 c0285r1) {
        I2 i2 = c0285r0.c.b;
        I2 i3 = c0285r1.c.b;
        C0270o5 c0270o5 = this.c.o;
        i2.getClass();
        return c0270o5.a(i2, i3);
    }

    public final void a(C0304t5 c0304t5) {
        if (!j && c0304t5.isEmpty()) {
            x1f.a();
            return;
        }
        J j2 = this.f;
        int iA = this.e.a(4);
        if (!J.n) {
            j2.getClass();
            if (iA == 0 || c0304t5.isEmpty()) {
                x1f.a();
                return;
            }
        }
        J.a(c0304t5, iA, j2.g);
        this.e.f(c0304t5.b.length);
        for (int i = 0; i < c0304t5.size(); i++) {
            if (!C0304t5.e && i < 0) {
                x1f.a();
                return;
            } else {
                if (i >= c0304t5.c) {
                    this.e.f(this.f.b(c0304t5.j(i)));
                }
            }
        }
    }

    public final void a(C0292s0 c0292s0) {
        J.a(c0292s0, this.e.a(4), this.f.h);
        this.e.f(this.f.b(c0292s0.b.n0()));
        ArrayList<AbstractC0217h1> arrayListB = c0292s0.b(this.c.o);
        ArrayList<AbstractC0217h1> arrayListC = c0292s0.c(this.c.o);
        ArrayList<AbstractC0217h1> arrayListA = c0292s0.a(this.c.o);
        this.e.f(arrayListA.size());
        this.e.f(arrayListB.size());
        this.e.f(arrayListC.size());
        ToIntFunction toIntFunction = new ToIntFunction() { // from class: bk8
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return this.b.a((C0210g1) obj);
            }
        };
        for (AbstractC0217h1 abstractC0217h1 : arrayListA) {
            this.e.f(abstractC0217h1.getReference().a(this.c));
            this.e.f(toIntFunction.applyAsInt(abstractC0217h1));
        }
        ToIntFunction toIntFunction2 = new ToIntFunction() { // from class: dk8
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return this.b.a((C0231j1) obj);
            }
        };
        for (AbstractC0217h1 abstractC0217h2 : arrayListB) {
            this.e.f(abstractC0217h2.getReference().a(this.c));
            this.e.f(toIntFunction2.applyAsInt(abstractC0217h2));
        }
        ToIntFunction toIntFunction3 = new ToIntFunction() { // from class: ek8
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return this.b.b((C0231j1) obj);
            }
        };
        for (AbstractC0217h1 abstractC0217h3 : arrayListC) {
            this.e.f(abstractC0217h3.getReference().a(this.c));
            this.e.f(toIntFunction3.applyAsInt(abstractC0217h3));
        }
    }

    public final /* synthetic */ int a(C0210g1 c0210g1) {
        return this.f.b(c0210g1.n0());
    }

    public final /* synthetic */ int a(C0231j1 c0231j1) {
        return this.f.b(c0231j1.n0());
    }

    public final void a(List list) {
        ArrayList<C0210g1> arrayList = new ArrayList(list);
        arrayList.sort(new Comparator() { // from class: qj8
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return this.b.a((C0210g1) obj, (C0210g1) obj2);
            }
        });
        int i = 0;
        for (C0210g1 c0210g1 : arrayList) {
            boolean z = j;
            if (!z && !c0210g1.b(this.a.a())) {
                x1f.a();
                return;
            }
            int iA = C0284q5.a(c0210g1.getReference(), this.c.i);
            if (!z && iA - i < 0) {
                x1f.a();
                return;
            }
            C0161y c0161y = this.e;
            c0161y.getClass();
            CJ.b(c0161y, iA - i);
            C0161y c0161y2 = this.e;
            int i2 = c0210g1.g.c;
            c0161y2.getClass();
            CJ.b(c0161y2, i2);
            this.g.a(c0210g1.getReference());
            i = iA;
        }
    }

    public final int a(C0210g1 c0210g1, C0210g1 c0210g2) {
        C0245l1 reference = c0210g1.getReference();
        C0245l1 reference2 = c0210g2.getReference();
        C0270o5 c0270o5 = this.c.o;
        reference.getClass();
        return c0270o5.a(reference, reference2);
    }

    public final void a(Iterable iterable) {
        ArrayList<C0231j1> arrayListB = C2753uC.b(iterable);
        arrayListB.sort(new Comparator() { // from class: gk8
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return this.b.a((C0231j1) obj, (C0231j1) obj2);
            }
        });
        int i = 0;
        for (C0231j1 c0231j1 : arrayListB) {
            int iA = C0284q5.a(c0231j1.getReference(), this.c.h);
            boolean z = j;
            if (!z && iA - i < 0) {
                x1f.a();
                return;
            }
            C0161y c0161y = this.e;
            c0161y.getClass();
            CJ.b(c0161y, iA - i);
            C0161y c0161y2 = this.e;
            F4 f4 = c0231j1.g;
            F4 f5 = new F4(f4.b, f4.c);
            if (f5.O() && !f5.M()) {
                f5.c(32);
                f5.b(131072);
            }
            int i2 = f5.c;
            c0161y2.getClass();
            CJ.b(c0161y2, i2);
            InterfaceC0170a3 interfaceC0170a3V0 = c0231j1.V0();
            this.g.a(c0231j1.getReference());
            if (interfaceC0170a3V0 == null) {
                if (!z && !c0231j1.F1()) {
                    x1f.a();
                    return;
                } else {
                    C0161y c0161y3 = this.e;
                    c0161y3.getClass();
                    CJ.b(c0161y3, 0);
                }
            } else {
                C0161y c0161y4 = this.e;
                int iA2 = J.a(c0231j1, this.f.a);
                c0161y4.getClass();
                CJ.b(c0161y4, iA2);
                c0231j1.O0();
                c0231j1.j = null;
            }
            i = iA;
        }
    }

    public final int a(C0231j1 c0231j1, C0231j1 c0231j2) {
        C0322w2 reference = c0231j1.getReference();
        C0322w2 reference2 = c0231j2.getReference();
        C0270o5 c0270o5 = this.c.o;
        reference.getClass();
        return c0270o5.a(reference, reference2);
    }

    public final void a(C0203f1 c0203f1) {
        J.a(c0203f1, this.e.b(), this.f.j);
        C0161y c0161y = this.e;
        int length = c0203f1.b.length;
        c0161y.getClass();
        CJ.b(c0161y, length);
        for (O2 o2 : c0203f1.b) {
            o2.a(this.e, this.c);
        }
    }

    public static void a(H h, C0161y c0161y) {
        Adler32 adler32 = new Adler32();
        byte[] bArrA = c0161y.a();
        int i = h.a;
        adler32.update(bArrA, i + 12, (h.w - i) - 12);
        c0161y.e(h.a + 8);
        c0161y.f((int) adler32.getValue());
    }

    public final void a(D0 d0) {
        C2752uB c2752uB = this.d;
        c2752uB.getClass();
        if (c2752uB.b(EnumC3077y2.B)) {
            if (!j && this.e.b() % 4 != 0) {
                x1f.a();
                return;
            }
            C0161y c0161y = this.e;
            J j2 = this.f;
            c0161y.f(J.b(j2.j, d0.o0()));
            return;
        }
        C2742u50 c2742u50 = this.d.i;
        c2742u50.a(null, new UnsupportedInvokeCustomDiagnostic(Origin.unknown(), Position.UNKNOWN));
        throw c2742u50.c;
    }

    public final void a(C0336y2 c0336y2) {
        EnumC0329x2 enumC0329x2;
        int iA;
        C2752uB c2752uB = this.d;
        c2752uB.getClass();
        if (c2752uB.b(EnumC3077y2.B)) {
            if (c0336y2.e.ordinal() != 9) {
                enumC0329x2 = c0336y2.e;
            } else {
                enumC0329x2 = EnumC0329x2.j;
            }
            boolean z = j;
            if (!z && this.e.b() % 4 != 0) {
                x1f.a();
                return;
            }
            this.e.a(enumC0329x2.b);
            this.e.a((short) 0);
            if (c0336y2.e.g()) {
                iA = C0284q5.a(c0336y2.p0(), this.c.h);
            } else if (!z && !c0336y2.e.a()) {
                x1f.a();
                return;
            } else {
                iA = C0284q5.a(c0336y2.o0(), this.c.i);
            }
            if (!z && (65535 & iA) != iA) {
                x1f.a();
                return;
            } else {
                this.e.a((short) iA);
                this.e.a((short) 0);
                return;
            }
        }
        C2742u50 c2742u50 = this.d.i;
        c2742u50.a(null, new UnsupportedInvokeCustomDiagnostic(Origin.unknown(), Position.UNKNOWN));
        throw c2742u50.c;
    }
}
