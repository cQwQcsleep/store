package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.AbstractC0138z1;
import com.android.tools.r8.dex.code.C0029d1;
import com.android.tools.r8.dex.code.C0122w0;
import com.android.tools.r8.dex.code.C0127x0;
import com.android.tools.r8.graph.C0230j0;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.C2798ul;
import defpackage.exe;
import defpackage.x0g;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.RandomAccess;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vl, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2884vl {
    public static final /* synthetic */ boolean r = true;
    public final C0333y a;
    public final C0705Nt b;
    public final C1127b8 c;
    public final com.android.tools.r8.ir.regalloc.f d;
    public final C2752uB e;
    public final AbstractC2166nO f;
    public AbstractC2457ql[] l;
    public AbstractC2457ql m;
    public H5 q;
    public final ArrayList g = new ArrayList();
    public final ArrayList h = new ArrayList();
    public final Set i = AbstractC2780ub0.c();
    public int j = 0;
    public int k = 0;
    public int n = 0;
    public int o = 0;
    public boolean p = false;

    public C2884vl(C0705Nt c0705Nt, C1295d8 c1295d8, com.android.tools.r8.ir.regalloc.f fVar, C2752uB c2752uB, AbstractC2166nO abstractC2166nO) {
        if (!r && c0705Nt != null && abstractC2166nO != c0705Nt.b) {
            x1f.a();
            throw null;
        }
        this.a = fVar.d();
        this.b = c0705Nt;
        C1211c8 c1211c8 = C1211c8.b;
        this.c = new C1127b8(c1295d8);
        this.d = fVar;
        this.e = c2752uB;
        this.f = abstractC2166nO;
        if (c0705Nt == null) {
            this.l = new AbstractC2457ql[1];
        }
    }

    /* JADX WARN: Code duplicated, block: B:181:0x034d  */
    /* JADX WARN: Code duplicated, block: B:258:0x0470  */
    /* JADX WARN: Code duplicated, block: B:391:0x07f9  */
    /* JADX WARN: Code duplicated, block: B:393:0x0806  */
    /* JADX WARN: Code duplicated, block: B:435:0x0477 A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v29, types: [com.android.tools.r8.internal.P, com.android.tools.r8.internal.lz] */
    /* JADX WARN: Type inference failed for: r1v31, types: [com.android.tools.r8.internal.Mh] */
    /* JADX WARN: Type inference failed for: r1v33, types: [com.android.tools.r8.internal.P] */
    /* JADX WARN: Type inference failed for: r2v44, types: [com.android.tools.r8.internal.P, com.android.tools.r8.internal.lz] */
    /* JADX WARN: Type inference failed for: r2v55, types: [com.android.tools.r8.internal.P, com.android.tools.r8.internal.lz] */
    /* JADX WARN: Type inference failed for: r30v0, types: [com.android.tools.r8.internal.vl] */
    /* JADX WARN: Type inference failed for: r3v27, types: [com.android.tools.r8.internal.P, com.android.tools.r8.internal.lz] */
    /* JADX WARN: Type inference failed for: r3v32, types: [com.android.tools.r8.internal.P, com.android.tools.r8.internal.lz] */
    /* JADX WARN: Type inference failed for: r3v34, types: [com.android.tools.r8.internal.P, com.android.tools.r8.internal.lz] */
    /* JADX WARN: Type inference failed for: r3v45, types: [com.android.tools.r8.internal.P, com.android.tools.r8.internal.lz] */
    /* JADX WARN: Type inference failed for: r3v50, types: [com.android.tools.r8.internal.P, com.android.tools.r8.internal.lz] */
    /* JADX WARN: Type inference failed for: r3v51, types: [com.android.tools.r8.internal.P, com.android.tools.r8.internal.lz] */
    /* JADX WARN: Type inference failed for: r7v35, types: [com.android.tools.r8.internal.P, com.android.tools.r8.internal.lz] */
    /* JADX WARN: Type inference failed for: r7v38, types: [com.android.tools.r8.internal.P, com.android.tools.r8.internal.lz] */
    /* JADX WARN: Type inference failed for: r7v41, types: [com.android.tools.r8.internal.P, com.android.tools.r8.internal.lz] */
    /* JADX WARN: Type inference failed for: r7v42, types: [com.android.tools.r8.internal.P, com.android.tools.r8.internal.lz] */
    /* JADX WARN: Type inference failed for: r7v43, types: [com.android.tools.r8.internal.P, com.android.tools.r8.internal.lz] */
    /* JADX WARN: Type inference failed for: r9v31, types: [com.android.tools.r8.internal.P, com.android.tools.r8.internal.lz] */
    /* JADX WARN: Type inference failed for: r9v37, types: [com.android.tools.r8.internal.P, com.android.tools.r8.internal.lz] */
    public final com.android.tools.r8.graph.J0 a() {
        com.android.tools.r8.graph.J0 j0;
        int i;
        int i2;
        int i3;
        int length;
        com.android.tools.r8.graph.W0.a aVar;
        com.android.tools.r8.graph.W0.a aVar2;
        C1127b8 c1127b8;
        C1211c8 c1211c8;
        AbstractC0138z1 l3;
        com.android.tools.r8.graph.J0 j1;
        int i4;
        boolean z;
        boolean z2;
        do {
            j0 = null;
            if (!this.i.isEmpty()) {
                L5 l5T = this.b.t();
                while (l5T.hasNext()) {
                    H5 next = l5T.next();
                    if (this.i.contains(next)) {
                        C2040lu c2040luR = next.h().R();
                        H5 h5L2 = c2040luR.L2();
                        H5 h5A = H5.a(this.b.f.a(), c2040luR.getPosition(), this.b.i, h5L2);
                        boolean z3 = C2040lu.k;
                        if (!z3 && c2040luR.i().h() != c2040luR) {
                            x1f.a();
                            return null;
                        }
                        List<H5> listM = c2040luR.i().m();
                        if (!z3 && listM.size() < 2) {
                            x1f.a();
                            return null;
                        }
                        listM.set(listM.size() - 2, h5A);
                        h5A.l().add(next);
                        h5L2.a(next, h5A);
                        c2040luR.N2();
                        l5T.add(h5A);
                    }
                }
            }
            a(this.a, this.b);
            this.g.clear();
            this.h.clear();
            this.i.clear();
            i = 0;
            this.j = 0;
            this.k = 0;
            C0705Nt c0705Nt = this.b;
            Iterator<AbstractC0890Uw> it = c0705Nt.s().iterator();
            while (true) {
                i2 = -1;
                if (it.hasNext()) {
                    AbstractC0890Uw next2 = it.next();
                    if (next2.e == -1) {
                        int i5 = c0705Nt.h;
                        if (!AbstractC0890Uw.h && i5 == -1) {
                            x1f.a();
                            return null;
                        }
                        next2.e = i5;
                        c0705Nt.h = i5 + 2;
                    }
                }
            }
            this.l = new AbstractC2457ql[c0705Nt.h / 2];
            this.n = 0;
            this.o = 0;
            this.q = null;
            L5 l5T2 = this.b.t();
            if (!r && !l5T2.hasNext()) {
                x1f.a();
                return null;
            }
            H5 next3 = l5T2.next();
            do {
                this.q = l5T2.hasNext() ? l5T2.next() : null;
                Iterator it2 = next3.f.iterator();
                while (it2.hasNext()) {
                    ((AbstractC0890Uw) it2.next()).a((C2884vl) this);
                }
                next3 = this.q;
            } while (next3 != null);
            Iterator<AbstractC0890Uw> it3 = this.b.s().iterator();
            i3 = 0;
            length = 0;
            while (it3.hasNext()) {
                AbstractC2457ql abstractC2457qlA = a(it3.next());
                abstractC2457qlA.getClass();
                if (!AbstractC2457ql.d && length < 0) {
                    x1f.a();
                    return null;
                }
                abstractC2457qlA.b = length;
                length += abstractC2457qlA.a(this);
                i3++;
            }
        } while (!this.i.isEmpty());
        com.android.tools.r8.graph.U0 u0 = new com.android.tools.r8.graph.U0(this.a, this.b);
        ArrayList arrayList = new ArrayList(i3);
        Iterator<AbstractC0890Uw> it4 = this.b.s().iterator();
        int i6 = 0;
        while (it4.hasNext()) {
            AbstractC0890Uw next4 = it4.next();
            AbstractC2457ql abstractC2457qlA2 = a(next4);
            int size = arrayList.size();
            abstractC2457qlA2.a((C2884vl) this, arrayList);
            int iT = i6;
            while (size < arrayList.size()) {
                int i7 = size + 1;
                AbstractC0138z1 abstractC0138z1 = (AbstractC0138z1) arrayList.get(size);
                abstractC0138z1.f(iT);
                iT += abstractC0138z1.t();
                size = i7;
            }
            int i8 = ((AbstractC0890Uw) next4.i().f.get(i)) == next4 ? 1 : i;
            int i9 = next4.i().h() == next4 ? 1 : i;
            if (i8 != 0) {
                boolean z4 = com.android.tools.r8.graph.U0.o;
                if (!z4 && u0.f != null) {
                    x1f.a();
                    return j0;
                }
                if (!z4 && u0.h) {
                    x1f.a();
                    return j0;
                }
                InterfaceC2045lz interfaceC2045lz = next4.i().a;
                if (interfaceC2045lz == null) {
                    u0.f = AbstractC2216nz.a;
                } else {
                    C2986wz c2986wz = new C2986wz(interfaceC2045lz.size());
                    c2986wz.putAll(interfaceC2045lz);
                    u0.f = c2986wz;
                    u0.h = true;
                }
                P p = u0.k;
                if (p != null) {
                    j1 = j0;
                } else {
                    if (!z4 && u0.d != null) {
                        x1f.a();
                        return j0;
                    }
                    if (!z4 && p != null) {
                        x1f.a();
                        return j0;
                    }
                    if (!z4 && u0.e != null) {
                        x1f.a();
                        return j0;
                    }
                    if (!z4 && u0.n != i2) {
                        x1f.a();
                        return j0;
                    }
                    if (interfaceC2045lz == null) {
                        C2130mz c2130mz = AbstractC2216nz.a;
                        u0.k = c2130mz;
                        u0.e = c2130mz;
                        j1 = j0;
                    } else {
                        u0.k = new C2986wz(16);
                        BU it5 = interfaceC2045lz.c().iterator();
                        while (it5.hasNext()) {
                            InterfaceC1959kz interfaceC1959kz = (InterfaceC1959kz) it5.next();
                            if (((C0230j0) interfaceC1959kz.getValue()).d == null) {
                                u0.k.a(interfaceC1959kz.a(), (C0230j0) interfaceC1959kz.getValue());
                            } else if (u0.c.u1.O0) {
                                ArrayList arrayList2 = u0.m;
                                com.android.tools.r8.graph.J0 j2 = j0;
                                int iA = interfaceC1959kz.a();
                                C0230j0 c0230j0 = (C0230j0) interfaceC1959kz.getValue();
                                arrayList2.add(new com.android.tools.r8.graph.O0.d(iA, c0230j0.b, c0230j0.c, c0230j0.d));
                                u0.k.a(interfaceC1959kz.a(), (C0230j0) interfaceC1959kz.getValue());
                                j0 = j2;
                            }
                        }
                        j1 = j0;
                        u0.e = new C2986wz((InterfaceC2045lz) u0.k);
                    }
                }
                if (u0.g == null) {
                    u0.g = next4.getPosition();
                }
            } else {
                j1 = j0;
            }
            boolean z5 = com.android.tools.r8.graph.U0.o;
            if (!z5 && u0.f == null) {
                x1f.a();
                return j1;
            }
            AbstractC2004lX position = next4.getPosition();
            boolean z6 = i6 != iT;
            if (!z5) {
                next4.a(u0.c.Z0);
            }
            if (next4.k1()) {
                C1201c3 c1201c3R = next4.r();
                if (u0.d == null) {
                    u0.d = new ArrayList(u0.a.getReference().A0());
                }
                if (!c1201c3R.c().l) {
                    u0.d.add(c1201c3R.k());
                }
            } else if (next4 instanceof C0667Mh) {
                ?? I = next4.I();
                u0.h = true;
                I.a(u0.f);
            } else {
                if (z6) {
                    if (position.n() || com.android.tools.r8.utils.structural.k.a(position, u0.j) || !(u0.c.Z0 || next4.g() || u0.l)) {
                        i4 = iT;
                    } else {
                        if (!z5 && com.android.tools.r8.utils.structural.k.a(position, u0.j)) {
                            x1f.a();
                            return j1;
                        }
                        if (u0.n == -1) {
                            if (!z5 && !u0.j.n()) {
                                x1f.a();
                                return j1;
                            }
                            if (!(position instanceof AbstractC2004lX.c) || position.k()) {
                                u0.n = position.f();
                                AbstractC2004lX abstractC2004lXH = position.h();
                                AbstractC2004lX.b.a aVarA = AbstractC2004lX.b.s().a(position.f()).a(abstractC2004lXH.c);
                                aVarA.e = abstractC2004lXH.f;
                                u0.j = aVarA.a();
                            } else {
                                i4 = iT;
                            }
                        }
                        if (!z5 && u0.i == i6) {
                            x1f.a();
                            return j1;
                        }
                        int i10 = u0.i;
                        i4 = iT;
                        com.android.tools.r8.graph.U0.a(i10 == -1 ? 0 : i10, u0.j, i6, position, u0.m, u0.b, false);
                        u0.i = i6;
                        u0.j = position;
                        if (u0.h) {
                            z2 = !C0230j0.a((InterfaceC2045lz) u0.k, (InterfaceC2045lz) u0.f);
                            u0.h = z2;
                        } else {
                            z2 = false;
                        }
                        if (z2) {
                            com.android.tools.r8.graph.U0.a(u0.k, u0.f, u0.e, u0.m, u0.b);
                            if (!z5 && !C0230j0.a((InterfaceC2045lz) u0.k, (InterfaceC2045lz) u0.f)) {
                                x1f.a();
                                return j1;
                            }
                        }
                        u0.h = false;
                    }
                    if (u0.i != i6) {
                        if (u0.h) {
                            z = !C0230j0.a((InterfaceC2045lz) u0.k, (InterfaceC2045lz) u0.f);
                            u0.h = z;
                        } else {
                            z = false;
                        }
                        if (z) {
                            if (!z5 && u0.i == i6) {
                                x1f.a();
                                return j1;
                            }
                            int i11 = u0.i;
                            int i12 = i11 == -1 ? i6 : i6 - i11;
                            if (!z5 && i12 <= 0 && i11 != -1) {
                                x1f.a();
                                return j1;
                            }
                            if (i12 > 0) {
                                u0.m.add(u0.b.b(i12));
                            }
                            u0.i = i6;
                            com.android.tools.r8.graph.U0.a(u0.k, u0.f, u0.e, u0.m, u0.b);
                            u0.h = false;
                            if (!z5 && !C0230j0.a((InterfaceC2045lz) u0.k, (InterfaceC2045lz) u0.f)) {
                                x1f.a();
                                return j1;
                            }
                        }
                    }
                }
                if (i9 != 0) {
                    u0.f = j1;
                    u0.h = false;
                }
                i6 = i4;
                i = 0;
                j0 = null;
                i2 = -1;
            }
            i4 = iT;
            if (i9 != 0) {
                u0.f = j1;
                u0.h = false;
            }
            i6 = i4;
            i = 0;
            j0 = null;
            i2 = -1;
        }
        C2752uB c2752uB = this.e;
        c2752uB.getClass();
        if (c2752uB.a(EnumC3077y2.w) && (arrayList.get(arrayList.size() - 1) instanceof com.android.tools.r8.dex.code.c4) && this.p) {
            AbstractC0138z1 abstractC0138z2 = (AbstractC0138z1) arrayList.get(arrayList.size() - 1);
            int iQ = abstractC0138z2.q();
            C0029d1 c0029d1 = new C0029d1(abstractC0138z2.t() + 1);
            C0029d1 c0029d2 = new C0029d1(-abstractC0138z2.t());
            c0029d1.f(iQ);
            int i13 = iQ + 1;
            abstractC0138z2.f(i13);
            int iT2 = abstractC0138z2.t() + i13;
            c0029d2.f(iT2);
            length = iT2 + 1;
            arrayList.remove(arrayList.size() - 1);
            arrayList.add(c0029d1);
            arrayList.add(abstractC0138z2);
            arrayList.add(c0029d2);
        }
        for (C2713tl c2713tl : this.g) {
            if (length % 2 != 0) {
                com.android.tools.r8.dex.code.P2 p2 = new com.android.tools.r8.dex.code.P2();
                p2.f(length);
                arrayList.add(p2);
                length++;
            }
            FA fa = c2713tl.a;
            c2713tl.b.g = length - a(fa).b();
            int[] iArr = fa.j;
            int length2 = iArr.length;
            int[] iArr2 = new int[length2];
            for (int i14 = 0; i14 < iArr.length; i14++) {
                iArr2[i14] = a((AbstractC0890Uw) fa.b(i14).f.get(0)).b() - a(fa).b();
            }
            int iB = a((AbstractC0890Uw) fa.K2().f.get(0)).b() - a(fa).b();
            AB ab = AB.b;
            boolean z7 = FA.n;
            if (!z7 && fa.m.length != length2) {
                x1f.a();
                return null;
            }
            if (fa.a(ab)) {
                int iA2 = (int) FA.a(fa.m);
                if (length2 == iA2) {
                    l3 = new com.android.tools.r8.dex.code.Z2(iArr2, fa.m[0]);
                } else {
                    char c = 0;
                    int[] iArr3 = new int[iA2];
                    int i15 = 0;
                    int i16 = 0;
                    while (i15 < iA2) {
                        int[] iArr4 = fa.m;
                        if (iArr4[i16] == iArr4[c] + i15) {
                            iArr3[i15] = iArr2[i16];
                            i16++;
                        } else {
                            iArr3[i15] = iB;
                        }
                        i15++;
                        c = 0;
                    }
                    if (!FA.n && i16 != fa.m.length) {
                        x1f.a();
                        return null;
                    }
                    l3 = new com.android.tools.r8.dex.code.Z2(iArr3, fa.m[0]);
                }
            } else {
                if (!z7 && fa.L2() != fa.m.length) {
                    x1f.a();
                    return null;
                }
                l3 = new com.android.tools.r8.dex.code.L3(fa.m, iArr2);
            }
            l3.f(length);
            length += l3.t();
            arrayList.add(l3);
        }
        for (C2114ml c2114ml : this.h) {
            if (length % 2 != 0) {
                com.android.tools.r8.dex.code.P2 p3 = new com.android.tools.r8.dex.code.P2();
                p3.f(length);
                arrayList.add(p3);
                length++;
            }
            IQ iq = c2114ml.a;
            iq.getClass();
            C0127x0 c0127x0 = new C0127x0(iq.i, iq.j, iq.k);
            c0127x0.f(length);
            C0122w0 c0122w0 = c2114ml.b;
            c0122w0.g = length - c0122w0.q();
            length += c0127x0.h.length + 4;
            arrayList.add(c0127x0);
        }
        C0860Ts c0860TsJ = C0860Ts.j();
        ArrayList arrayList3 = (ArrayList) a(a(c0860TsJ), arrayList);
        int size2 = arrayList3.size();
        com.android.tools.r8.graph.J0.a[] aVarArr = new com.android.tools.r8.graph.J0.a[size2];
        for (int i17 = 0; i17 < size2; i17++) {
            C2798ul c2798ul = (C2798ul) arrayList3.get(i17);
            int i18 = c2798ul.c;
            com.android.tools.r8.graph.J0.a aVar3 = new com.android.tools.r8.graph.J0.a(i18, c2798ul.d - i18, -1);
            aVar3.e = ((Integer) c0860TsJ.get(c2798ul.b)).intValue();
            aVarArr[i17] = aVar3;
        }
        C0756Ps c0756Ps = (C0756Ps) c0860TsJ.f();
        com.android.tools.r8.graph.J0.b[] bVarArr = new com.android.tools.r8.graph.J0.b[c0756Ps.b.f];
        for (int i19 = 0; i19 < c0756Ps.b.f; i19++) {
            C2490r8 c2490r8 = (C2490r8) c0756Ps.get(Integer.valueOf(i19));
            ArrayList arrayList4 = new ArrayList();
            int i20 = -1;
            for (int i21 = 0; i21 < c2490r8.b.size(); i21++) {
                com.android.tools.r8.graph.I2 i22 = (com.android.tools.r8.graph.I2) c2490r8.b.get(i21);
                int iB2 = a((AbstractC0890Uw) ((H5) c2490r8.c.get(i21)).f.get(0)).b();
                if (i22 != this.e.a.o3) {
                    arrayList4.add(new com.android.tools.r8.graph.J0.b.a(iB2, i22));
                } else {
                    if (!r && i21 != c2490r8.b.size() - 1) {
                        x1f.a();
                        return null;
                    }
                    i20 = iB2;
                }
            }
            bVarArr[i19] = new com.android.tools.r8.graph.J0.b((com.android.tools.r8.graph.J0.b.a[]) arrayList4.toArray(new com.android.tools.r8.graph.J0.b.a[0]), i20);
        }
        int iE = this.d.e();
        int i23 = this.n;
        int i24 = this.o;
        AbstractC0138z1[] abstractC0138z1Arr = (AbstractC0138z1[]) arrayList.toArray(AbstractC0138z1.c);
        boolean z8 = com.android.tools.r8.graph.U0.o;
        if (!z8 && u0.f != null) {
            x1f.a();
            return null;
        }
        if (!z8 && u0.h) {
            x1f.a();
            return null;
        }
        if (!z8 && u0.g == null) {
            x1f.a();
            return null;
        }
        if (u0.n == -1) {
            if (u0.g.k()) {
                aVar = new com.android.tools.r8.graph.W0.a(u0.g.f(), new com.android.tools.r8.graph.H2[u0.a.getReference().A0()], new com.android.tools.r8.graph.O0[]{u0.b.a(u0.g), u0.b.r});
            } else {
                aVar2 = null;
            }
            c1127b8 = this.c;
            if (c1127b8.b.isEmpty()) {
                c1211c8 = C1211c8.b;
            } else {
                c1211c8 = new C1211c8(c1127b8.b);
            }
            return new com.android.tools.r8.graph.J0(iE, i23, i24, abstractC0138z1Arr, aVarArr, bVarArr, aVar2, c1211c8);
        }
        int iA0 = u0.a.getReference().A0();
        com.android.tools.r8.graph.H2[] h2Arr = new com.android.tools.r8.graph.H2[iA0];
        ArrayList arrayList5 = u0.d;
        if (arrayList5 != null) {
            if (!z8 && iA0 != arrayList5.size()) {
                x1f.a();
                return null;
            }
            for (int i25 = 0; i25 < u0.d.size(); i25++) {
                C0230j0 c0230j1 = (C0230j0) u0.d.get(i25);
                h2Arr[i25] = (c0230j1 == null || c0230j1.d != null) ? null : c0230j1.b;
            }
        }
        if (!com.android.tools.r8.graph.U0.o && !AbstractC0594Jl.a((com.android.tools.r8.graph.O0[]) u0.m.toArray(com.android.tools.r8.graph.O0.b))) {
            x1f.a();
            return null;
        }
        aVar = new com.android.tools.r8.graph.W0.a(u0.n, h2Arr, (com.android.tools.r8.graph.O0[]) u0.m.toArray(com.android.tools.r8.graph.O0.b));
        aVar2 = aVar;
        c1127b8 = this.c;
        if (c1127b8.b.isEmpty()) {
            c1211c8 = C1211c8.b;
        } else {
            c1211c8 = new C1211c8(c1127b8.b);
        }
        return new com.android.tools.r8.graph.J0(iE, i23, i24, abstractC0138z1Arr, aVarArr, bVarArr, aVar2, c1211c8);
    }

    public final C2752uB b() {
        return this.e;
    }

    public final com.android.tools.r8.ir.regalloc.f c() {
        return this.d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v1, types: [com.android.tools.r8.internal.S, com.android.tools.r8.internal.lz, com.android.tools.r8.internal.wz, java.util.Map] */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r3v18, types: [com.android.tools.r8.internal.Mh] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6, types: [com.android.tools.r8.internal.wz] */
    public static void a(C0333y c0333y, C0705Nt c0705Nt) {
        ?? c2986wz;
        AbstractC2004lX position;
        H5 h5K2;
        int i = 0;
        if ((c0333y.M().Z0 || c0705Nt.i().a().f(c0333y)) && c0705Nt.i.b(19)) {
            int iMax = 1;
            while (iMax < c0705Nt.d.size()) {
                H5 h5 = c0705Nt.d.get(iMax);
                if (!r && h5.s().isEmpty()) {
                    x1f.a();
                    return;
                }
                if (h5.f.size() == 2) {
                    C0719Oh c0719OhJ = ((AbstractC0890Uw) h5.f.get(0)).J();
                    C2636ss c2636ssQ = h5.h().Q();
                    if (c0719OhJ != null && c2636ssQ != null && !c0719OhJ.getPosition().n()) {
                        AbstractC2004lX position2 = c0719OhJ.getPosition();
                        Iterator<H5> it = h5.s().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                h5.e(c0719OhJ);
                                C2367pi0.b(h5, c2636ssQ.L2());
                                c0705Nt.b((Collection<H5>) Collections.singleton(h5));
                                iMax = Math.max(0, iMax - 2);
                                break;
                            }
                            H5 next = it.next();
                            if (next == h5) {
                                break;
                            }
                            AbstractC2004lX position3 = next.h().getPosition();
                            position2.getClass();
                            if (!com.android.tools.r8.utils.structural.k.a(position2, position3)) {
                                break;
                            }
                            AbstractC2925wD abstractC2925wDH = next.h();
                            if (abstractC2925wDH.L1()) {
                                h5K2 = abstractC2925wDH.R().K2();
                            } else if (abstractC2925wDH.B2()) {
                                h5K2 = abstractC2925wDH.M0().K2();
                            } else {
                                continue;
                            }
                            if (h5K2 == h5) {
                                int i2 = iMax + 1;
                                if (i2 >= c0705Nt.d.size()) {
                                    break;
                                }
                                if (C5.a(h5) != c0705Nt.d.get(i2)) {
                                    break;
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                }
                iMax++;
            }
            AbstractC2004lX abstractC2004lXR = AbstractC2004lX.r();
            ArrayList arrayList = new ArrayList();
            int i3 = 0;
            C0719Oh c0719OhJ2 = null;
            ?? c2986wz2 = 0;
            while (i3 < c0705Nt.d.size()) {
                H5 h6 = c0705Nt.d.get(i3);
                if (i3 != 0) {
                    H5 h7 = c0705Nt.d.get(i3 - 1);
                    if (!h7.h().K1() || h6.s().size() != 1 || h6.s().get(i) != h7) {
                        Iterator<H5> it2 = h6.s().iterator();
                        AbstractC2004lX abstractC2004lX = null;
                        while (true) {
                            if (!it2.hasNext()) {
                                abstractC2004lXR = abstractC2004lX;
                                break;
                            }
                            H5 next2 = it2.next();
                            if (next2 == h7) {
                                position = c0719OhJ2 != null ? c0719OhJ2.getPosition() : abstractC2004lXR;
                            } else {
                                position = next2.h().getPosition();
                            }
                            if (abstractC2004lX != null) {
                                if (!com.android.tools.r8.utils.structural.k.a(abstractC2004lX, position)) {
                                    abstractC2004lXR = AbstractC2004lX.r();
                                    break;
                                }
                            } else {
                                abstractC2004lX = position;
                            }
                        }
                        c0719OhJ2 = null;
                    }
                }
                InterfaceC2045lz interfaceC2045lz = h6.a;
                if (interfaceC2045lz != null) {
                    C2986wz c2986wz3 = new C2986wz(interfaceC2045lz.size());
                    c2986wz3.putAll(interfaceC2045lz);
                    c2986wz = c2986wz3;
                } else {
                    c2986wz = new C2986wz(16);
                }
                i3++;
                H5 h8 = i3 < c0705Nt.d.size() ? c0705Nt.d.get(i3) : null;
                c2986wz2 = c2986wz2;
                for (AbstractC0890Uw abstractC0890Uw : h6.k()) {
                    abstractC0890Uw.getClass();
                    if (abstractC0890Uw instanceof C0719Oh) {
                        if (c0719OhJ2 == null && abstractC2004lXR == abstractC0890Uw.getPosition()) {
                            arrayList.add(abstractC0890Uw.J());
                            if (!r && h6.f.size() == 2 && h6.h().getPosition() == abstractC2004lXR && h6.h().K1() && C5.a(h6) == h8) {
                                x01.a("Unexpected trivial fallthrough block. This should be removed already.");
                                return;
                            }
                        } else if (c0719OhJ2 != null && c0719OhJ2.getPosition() == abstractC0890Uw.getPosition() && c2986wz.equals(c2986wz2)) {
                            arrayList.add(c0719OhJ2);
                            c0719OhJ2 = abstractC0890Uw.J();
                        } else {
                            c0719OhJ2 = abstractC0890Uw.J();
                            c2986wz2 = new C2986wz(c2986wz.i);
                            c2986wz2.putAll(c2986wz);
                        }
                    } else {
                        if (!r && abstractC0890Uw.getPosition().n()) {
                            x1f.a();
                            return;
                        }
                        boolean z = abstractC0890Uw instanceof C0667Mh;
                        if (z) {
                            abstractC0890Uw.I().a(c2986wz);
                        } else if (!abstractC0890Uw.k1() && !z && ((!abstractC0890Uw.z1() || abstractC0890Uw.c().P() || abstractC0890Uw.c().T()) && (!abstractC0890Uw.K1() || abstractC0890Uw.Q().L2() != h8))) {
                            if (c0719OhJ2 != null) {
                                if (c0719OhJ2.getPosition() == abstractC0890Uw.getPosition() && c2986wz.equals(c2986wz2)) {
                                    arrayList.add(c0719OhJ2);
                                }
                                c0719OhJ2 = null;
                                c2986wz2 = 0;
                            }
                            abstractC2004lXR = abstractC0890Uw.getPosition();
                        }
                    }
                    i = 0;
                    c2986wz2 = c2986wz2;
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            InterfaceC0968Xw interfaceC0968XwR = c0705Nt.r();
            int i4 = 0;
            while (interfaceC0968XwR.hasNext() && i4 < arrayList.size()) {
                if (interfaceC0968XwR.next() == arrayList.get(i4)) {
                    interfaceC0968XwR.remove();
                    i4++;
                }
            }
            if (r || i4 == arrayList.size()) {
                return;
            }
            x1f.a();
        }
    }

    public final void a(AbstractC0890Uw abstractC0890Uw, AbstractC2457ql abstractC2457ql) {
        if (this.b == null) {
            if (r || this.l.length == 1) {
                this.l[0] = abstractC2457ql;
                return;
            } else {
                x1f.a();
                return;
            }
        }
        boolean z = r;
        if (!z && abstractC0890Uw == null) {
            x1f.a();
            return;
        }
        if (!z && a(abstractC0890Uw) != null) {
            x1f.a();
            return;
        }
        int i = this.k;
        boolean z2 = AbstractC2457ql.d;
        if (!z2 && i < 0) {
            x1f.a();
            return;
        }
        int i2 = this.j;
        if (!z2 && i2 < 0) {
            x1f.a();
            return;
        }
        abstractC2457ql.c = i2;
        this.k = abstractC2457ql.e() + i;
        this.j = abstractC2457ql.d() + this.j;
        if (!z && abstractC0890Uw.e < 0) {
            x1f.a();
            return;
        }
        if (!(abstractC2457ql instanceof C2029ll)) {
            this.m = abstractC2457ql;
        }
        this.l[abstractC0890Uw.e / 2] = abstractC2457ql;
    }

    public final void a(int i) {
        if (i > this.o) {
            this.o = i;
        }
    }

    public final int a(C2543rl0 c2543rl0, int i) {
        return this.d.a(c2543rl0, i);
    }

    public final void a(AbstractC0890Uw abstractC0890Uw, AbstractC0138z1 abstractC0138z1) {
        if (!r && abstractC0890Uw.K1()) {
            x1f.a();
            return;
        }
        a(abstractC0890Uw, new C2200nl(abstractC0890Uw, abstractC0138z1));
        C1127b8 c1127b8 = this.c;
        C1041a8 c1041a8 = (C1041a8) c1127b8.a.a.get(abstractC0890Uw);
        if (c1041a8 != null) {
            c1127b8.b.put(abstractC0138z1, c1041a8);
        }
    }

    public final void a(AbstractC0890Uw abstractC0890Uw, AbstractC0138z1... abstractC0138z1Arr) {
        if (r || !abstractC0890Uw.K1()) {
            a(abstractC0890Uw, new C2627sl(abstractC0890Uw, abstractC0138z1Arr));
        } else {
            x1f.a();
        }
    }

    public final AbstractC2457ql a(AbstractC0890Uw abstractC0890Uw) {
        if (!r && abstractC0890Uw.e < 0) {
            x1f.a();
            return null;
        }
        return this.l[abstractC0890Uw.e / 2];
    }

    public final AbstractC2457ql a(H5 h5) {
        J5 j5H = h5.H();
        AbstractC0890Uw next = null;
        while (j5H.hasNext()) {
            next = j5H.next();
            AbstractC2457ql abstractC2457qlA = a(next);
            if (!(abstractC2457qlA instanceof C2029ll)) {
                return abstractC2457qlA;
            }
        }
        boolean z = r;
        if (!z && next == null) {
            x1f.a();
            return null;
        }
        if (next.v2()) {
            if (!z && !(a(next) instanceof C2029ll)) {
                x1f.a();
                return null;
            }
            L5 l5T = this.b.t();
            for (H5 next2 = l5T.next(); next2 != h5; next2 = l5T.next()) {
            }
            return a(l5T.next());
        }
        if (z || next.K1()) {
            return a(next.Q().L2());
        }
        x1f.a();
        return null;
    }

    public final ArrayList a(C0860Ts c0860Ts) {
        List<AbstractC0890Uw> c3188zL;
        List c3188zL2;
        AbstractC0890Uw abstractC0890Uw;
        R5 r5F = c0860Ts.f();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<H5> it = this.b.d.iterator();
        while (true) {
            C2798ul c2798ul = null;
            while (true) {
                int i = 0;
                if (it.hasNext()) {
                    H5 next = it.next();
                    C2490r8 c2490r8I = next.i();
                    if (!r && !c2490r8I.isEmpty() && !next.a()) {
                        x1f.a();
                        return null;
                    }
                    if (!c2490r8I.isEmpty()) {
                        if (c0860Ts.containsKey(c2490r8I)) {
                            c2490r8I = (C2490r8) ((C0756Ps) r5F).get(c0860Ts.get(c2490r8I));
                        } else {
                            c0860Ts.a(c2490r8I, Integer.valueOf(c0860Ts.f), false);
                        }
                        AbstractC2457ql abstractC2457qlA = a((AbstractC0890Uw) next.f.get(0));
                        AbstractC2457ql abstractC2457qlA2 = a(next.h());
                        C2798ul c2798ul2 = new C2798ul(c2490r8I, abstractC2457qlA.b(), abstractC2457qlA2.c() + abstractC2457qlA2.b());
                        arrayList.add(c2798ul2);
                        arrayList2.add(next);
                        c2798ul = c2798ul2;
                    } else if (c2798ul != null && !next.a()) {
                        AbstractC2457ql abstractC2457qlA3 = a(next.h());
                        if (abstractC2457qlA3 != null) {
                            c2798ul.d = abstractC2457qlA3.c() + abstractC2457qlA3.b();
                        }
                    }
                } else {
                    if (arrayList.isEmpty()) {
                        return arrayList;
                    }
                    arrayList.sort(new Comparator() { // from class: jmi
                        @Override // java.util.Comparator
                        public final int compare(Object obj, Object obj2) {
                            return ((C2798ul) obj).a((C2798ul) obj2);
                        }
                    });
                    ArrayList arrayList3 = new ArrayList(arrayList.size());
                    C2798ul c2798ul3 = null;
                    while (i < arrayList.size()) {
                        if (c2798ul3 != null) {
                            LinkedList<AbstractC0890Uw> linkedListK = ((H5) arrayList2.get(i - 1)).k();
                            if (linkedListK instanceof RandomAccess) {
                                c3188zL2 = new C3019xL(linkedListK);
                            } else {
                                c3188zL2 = new C3188zL(linkedListK);
                            }
                            Iterator it2 = c3188zL2.iterator();
                            do {
                                if (!it2.hasNext()) {
                                    x0g.a("Expected to find a possibly throwing instruction");
                                    return null;
                                }
                                abstractC0890Uw = (AbstractC0890Uw) it2.next();
                            } while (!abstractC0890Uw.g());
                            AbstractC2457ql abstractC2457qlA4 = a(abstractC0890Uw);
                            c2798ul3.d = abstractC2457qlA4.c() + abstractC2457qlA4.b();
                        }
                        c2798ul3 = (C2798ul) arrayList.get(i);
                        arrayList3.add(c2798ul3);
                        for (AbstractC0890Uw abstractC0890Uw2 : ((H5) arrayList2.get(i)).k()) {
                            if (abstractC0890Uw2.g()) {
                                c2798ul3.c = a(abstractC0890Uw2).b();
                                break;
                            }
                        }
                        while (true) {
                            i++;
                            if (i >= arrayList.size()) {
                                break;
                            }
                            C2798ul c2798ul4 = (C2798ul) arrayList.get(i);
                            if (c2798ul3.d != c2798ul4.c || !c2798ul3.b.equals(c2798ul4.b)) {
                                break;
                            }
                            c2798ul3.d = c2798ul4.d;
                        }
                    }
                    LinkedList<AbstractC0890Uw> linkedListK2 = ((H5) arrayList2.get(arrayList.size() - 1)).k();
                    if (linkedListK2 instanceof RandomAccess) {
                        c3188zL = new C3019xL(linkedListK2);
                    } else {
                        c3188zL = new C3188zL(linkedListK2);
                    }
                    for (AbstractC0890Uw abstractC0890Uw3 : c3188zL) {
                        if (abstractC0890Uw3.g()) {
                            AbstractC2457ql abstractC2457qlA5 = a(abstractC0890Uw3);
                            c2798ul3.d = abstractC2457qlA5.c() + abstractC2457qlA5.b();
                            return arrayList3;
                        }
                    }
                    x0g.a("Expected to find a possibly throwing instruction");
                    return null;
                }
            }
        }
    }

    public static List a(ArrayList arrayList, ArrayList arrayList2) {
        int iQ;
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            C2798ul c2798ul = (C2798ul) it.next();
            for (int i2 = c2798ul.d - c2798ul.c; i2 > 65535; i2 -= 65535) {
                i++;
            }
        }
        if (i == 0) {
            return arrayList;
        }
        ArrayList arrayList3 = new ArrayList(arrayList.size() + i + 1);
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            C2798ul c2798ul2 = (C2798ul) it2.next();
            int i3 = c2798ul2.d;
            int i4 = c2798ul2.c;
            if (i3 - i4 <= 65535) {
                arrayList3.add(c2798ul2);
            } else {
                C2490r8 c2490r8 = c2798ul2.b;
                while (i3 - i4 > 65535) {
                    int i5 = i4 + 65535;
                    if (!r && i5 >= i3) {
                        x1f.a();
                        return null;
                    }
                    int size = arrayList2.size() - 1;
                    while (true) {
                        if (size < 0) {
                            iQ = -1;
                            break;
                        }
                        AbstractC0138z1 abstractC0138z1 = (AbstractC0138z1) arrayList2.get(size);
                        if (abstractC0138z1.q() <= i5) {
                            iQ = abstractC0138z1.q();
                            break;
                        }
                        size--;
                    }
                    if (iQ > i4) {
                        arrayList3.add(new C2798ul(c2490r8, i4, iQ));
                        i4 = iQ;
                    } else {
                        exe.a("Unexpected try-catch handler end point: ", iQ);
                        return null;
                    }
                }
                if (!r && i4 >= i3) {
                    x1f.a();
                    return null;
                }
                arrayList3.add(new C2798ul(c2490r8, i4, i3));
            }
        }
        if (r || arrayList3.size() >= arrayList.size() + i) {
            return arrayList3;
        }
        x1f.a();
        return null;
    }
}
