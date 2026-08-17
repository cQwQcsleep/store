package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0259n1;
import com.android.tools.r8.graph.C0230j0;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.C0336y2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.internal.H5;
import com.android.tools.r8.ir.optimize.C3242a;
import defpackage.n33;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Jt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0602Jt {
    public static final /* synthetic */ boolean C = true;
    public final AS k;
    public final AS l;
    public final com.android.tools.r8.graph.B5 m;
    public com.android.tools.r8.graph.B5 n;
    public final C0333y o;
    public final AbstractC3148ys p;
    public final com.android.tools.r8.graph.proto.j q;
    public C2543rl0 r;
    public ArrayList s;
    public Xc0 t;
    public final C0944Wy a = new C0944Wy();
    public final C2481r30 b = new C2481r30();
    public final LinkedList c = new LinkedList();
    public boolean[] d = null;
    public HashSet e = null;
    public final LinkedList f = new LinkedList();
    public final LinkedList g = new LinkedList();
    public H5 h = null;
    public H5 i = null;
    public int j = -1;
    public boolean u = false;
    public C2543rl0 v = null;
    public final ArrayList w = new ArrayList();
    public C2986wz x = null;
    public ArrayList y = null;
    public boolean z = false;
    public boolean A = false;
    public final C0887Ut B = new C0887Ut();

    public C0602Jt(com.android.tools.r8.graph.B5 b5, C0333y c0333y, AbstractC3148ys abstractC3148ys, Xc0 xc0, com.android.tools.r8.graph.proto.j jVar, AS as) {
        if (!C && as == null) {
            x1f.a();
            throw null;
        }
        this.m = b5;
        this.o = c0333y;
        this.t = xc0;
        this.p = abstractC3148ys;
        this.q = jVar;
        this.k = as;
        this.l = new AS();
    }

    public final C0705Nt a(com.android.tools.r8.graph.B5 b5, AbstractC2166nO.a aVar) {
        H5 h5;
        boolean zContains;
        boolean zContains2;
        if (!C && this.t == null) {
            x1f.a();
            return null;
        }
        this.t.b();
        this.n = b5;
        C0472Et c0472Et = new C0472Et();
        this.a.a(-1, c0472Et);
        this.b.b(-1, c0472Et.a);
        int iE = this.t.e();
        this.d = new boolean[iE];
        int i = 0;
        this.c.add(0);
        while (true) {
            int i2 = 1;
            if (this.c.isEmpty()) {
                this.d = null;
                H5 h6 = ((C0472Et) this.a.get(-1)).a;
                this.i = h6;
                this.h = h6;
                this.t.c(this);
                a(this.i, 0);
                while (true) {
                    C0576It c0576It = (C0576It) this.f.poll();
                    if (c0576It == null) {
                        boolean z = C;
                        if (!z && this.i != null) {
                            x1f.a();
                            return null;
                        }
                        if (!z) {
                            for (H5 h7 : this.g) {
                                boolean z2 = C;
                                if (!z2) {
                                    if (!z2) {
                                        h7.T();
                                    }
                                    C0918Vy c0918Vy = new C0918Vy(((C0555Hy) this.a.values()).b);
                                    while (c0918Vy.hasNext()) {
                                        C0472Et c0472Et2 = (C0472Et) c0918Vy.a().c;
                                        if (c0472Et2 != null && c0472Et2.a == h7) {
                                            if (!C) {
                                                int i3 = c0472Et2.b.c + c0472Et2.d.c;
                                                Set setC = AbstractC2780ub0.c();
                                                for (H5 h8 : h7.s()) {
                                                    if (this.b.containsKey(h8)) {
                                                        setC.add(h8);
                                                    } else {
                                                        boolean z3 = C;
                                                        if (!z3 && h8.t().size() != 1) {
                                                            x1f.a();
                                                            return null;
                                                        }
                                                        if (!z3 && h8.s().size() != 1) {
                                                            x1f.a();
                                                            return null;
                                                        }
                                                        if (!z3) {
                                                            for (AbstractC0890Uw abstractC0890Uw : h8.k()) {
                                                                if (!C) {
                                                                    abstractC0890Uw.getClass();
                                                                    if (!(abstractC0890Uw instanceof C2766uP) && !abstractC0890Uw.K1() && !abstractC0890Uw.D1()) {
                                                                        x1f.a();
                                                                        return null;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        if (h8.s().get(0).c(h8)) {
                                                            setC.add(h8.s().get(0));
                                                        } else {
                                                            setC.add(h8);
                                                        }
                                                    }
                                                }
                                                if (i3 != setC.size()) {
                                                    x1f.a();
                                                    return null;
                                                }
                                            }
                                            boolean z4 = C;
                                            if (!z4 && c0472Et2.c.size() != h7.n().size()) {
                                                x1f.a();
                                                return null;
                                            }
                                            if (!h7.x() && !z4 && h7.a() && !c0472Et2.e.isEmpty()) {
                                                C0997Yz c0997Yz = c0472Et2.e;
                                                if (c0997Yz.c == 1 && new C0971Xz(c0997Yz).q() < 0) {
                                                    break;
                                                }
                                                x1f.a();
                                                return null;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (g()) {
                            for (H5 h9 : this.g) {
                                C0887Ut c0887Ut = this.B;
                                h9.getClass();
                                K5 k5 = new K5(c0887Ut, h9);
                                AbstractC2004lX abstractC2004lX = null;
                                while (k5.c.hasNext()) {
                                    AbstractC0890Uw abstractC0890Uw2 = (AbstractC0890Uw) k5.next();
                                    AbstractC2004lX position = abstractC0890Uw2.getPosition();
                                    if (!abstractC0890Uw2.k1()) {
                                        if (abstractC0890Uw2 instanceof C2766uP) {
                                            if (!C && abstractC2004lX != null) {
                                                x1f.a();
                                                return null;
                                            }
                                        } else if (abstractC0890Uw2 instanceof C0719Oh) {
                                            position.getClass();
                                            if (com.android.tools.r8.utils.structural.k.a(position, abstractC2004lX)) {
                                                k5.p();
                                            }
                                        } else if (!position.n() && !(position instanceof AbstractC2004lX.c) && !com.android.tools.r8.utils.structural.k.a(position, abstractC2004lX)) {
                                            C0719Oh c0719Oh = new C0719Oh();
                                            c0719Oh.b(position);
                                            k5.previous();
                                            k5.add(c0719Oh);
                                            k5.next();
                                        }
                                        abstractC2004lX = position;
                                    }
                                }
                            }
                        }
                        if (this.x != null) {
                            AbstractC2004lX abstractC2004lXR = this.h.r();
                            H5 h10 = this.h;
                            C0887Ut c0887Ut2 = this.B;
                            h10.getClass();
                            K5 k6 = new K5(c0887Ut2, h10);
                            k6.a(new Predicate() { // from class: xu7
                                @Override // java.util.function.Predicate
                                public final boolean test(Object obj) {
                                    return C0602Jt.d((AbstractC0890Uw) obj);
                                }
                            });
                            k6.previous();
                            C2900vz c2900vz = new C2900vz(((C2302oz) this.x.values()).b);
                            while (c2900vz.hasNext()) {
                                for (C2543rl0 c2543rl0 : (List) c2900vz.h.d[c2900vz.a()]) {
                                    if (c2543rl0.O()) {
                                        C0616Kh c0616Kh = new C0616Kh(c2543rl0);
                                        c0616Kh.a(this.h);
                                        c0616Kh.b(abstractC2004lXR);
                                        k6.add(c0616Kh);
                                    }
                                }
                            }
                        }
                        for (H5 h11 : this.g) {
                            h11.o = null;
                            Iterator<PW> it = h11.q().iterator();
                            while (it.hasNext()) {
                                it.next().v = null;
                            }
                        }
                        ArrayList arrayList = new ArrayList();
                        for (H5 h12 : this.g) {
                            if (!h12.k.isEmpty()) {
                                n33.a("Undefined value encountered during compilation. This is typically caused by invalid dex input that uses a register that is not defined on all control-flow paths leading to the use.");
                                return null;
                            }
                            if (!(((AbstractC0890Uw) h12.f.get(i)) instanceof C2766uP)) {
                                ArrayList arrayList2 = new ArrayList();
                                HashMap map = new HashMap();
                                HashMap map2 = new HashMap();
                                if (h12.q().size() > 0) {
                                    PW pw = h12.q().get(i);
                                    int i4 = i;
                                    while (i4 < pw.c0().size()) {
                                        List<PW> listQ = h12.q();
                                        C0550Ht c0550Ht = new C0550Ht();
                                        Iterator<PW> it2 = listQ.iterator();
                                        while (it2.hasNext()) {
                                            c0550Ht.a.add((C2543rl0) it2.next().s.get(i4));
                                        }
                                        H5 h13 = h12.s().get(i4);
                                        if (map.containsKey(c0550Ht)) {
                                            Integer num = (Integer) map.get(c0550Ht);
                                            int iIntValue = num.intValue();
                                            H5 h5A = (H5) map2.get(num);
                                            if (h5A == null) {
                                                h5A = H5.a(this.l.a(), h12.r(), this.B, h12);
                                                map2.put(num, h5A);
                                                arrayList.add(h5A);
                                                H5 h14 = h12.s().get(iIntValue);
                                                h5A.l().add(h14);
                                                h14.b(h12, h5A);
                                                h12.l().set(iIntValue, h5A);
                                            }
                                            h5A.l().add(h13);
                                            h13.b(h12, h5A);
                                            arrayList2.add(Integer.valueOf(i4));
                                        } else {
                                            i2 = i2;
                                            map.put(c0550Ht, Integer.valueOf(i4));
                                        }
                                        i4++;
                                        i2 = i2;
                                    }
                                }
                                int i5 = i2;
                                if (!arrayList2.isEmpty()) {
                                    List<H5> listL = h12.l();
                                    ArrayList arrayList3 = new ArrayList(listL);
                                    listL.clear();
                                    Iterator it3 = arrayList2.iterator();
                                    int i6 = 0;
                                    while (it3.hasNext()) {
                                        int iIntValue2 = ((Integer) it3.next()).intValue();
                                        listL.addAll(arrayList3.subList(i6, iIntValue2));
                                        i6 = iIntValue2 + 1;
                                    }
                                    listL.addAll(arrayList3.subList(i6, arrayList3.size()));
                                }
                                for (PW pw2 : h12.h) {
                                    pw2.getClass();
                                    if (!arrayList2.isEmpty()) {
                                        ArrayList arrayList4 = new ArrayList(pw2.s);
                                        pw2.s.clear();
                                        Iterator it4 = arrayList2.iterator();
                                        int i7 = 0;
                                        while (it4.hasNext()) {
                                            int iIntValue3 = ((Integer) it4.next()).intValue();
                                            pw2.s.addAll(arrayList4.subList(i7, iIntValue3));
                                            C2543rl0 c2543rl1 = (C2543rl0) arrayList4.get(iIntValue3);
                                            c2543rl1.f.remove(pw2);
                                            c2543rl1.g = null;
                                            i7 = iIntValue3 + 1;
                                        }
                                        pw2.s.addAll(arrayList4.subList(i7, arrayList4.size()));
                                    }
                                }
                                i2 = i5;
                                i = 0;
                            }
                        }
                        int i8 = i2;
                        this.g.addAll(arrayList);
                        C0705Nt c0705Nt = new C0705Nt(this.o.M(), this.m, this.t.b(0), this.g, this.k, this.l, this.B, aVar);
                        if (!C) {
                            for (H5 h15 : c0705Nt.d) {
                                List<H5> listS = h15.s();
                                int i9 = i8;
                                if (listS.size() > i9) {
                                    for (H5 h16 : listS) {
                                        boolean z5 = C0705Nt.k;
                                        if (!z5 && (h16.b.size() != i9 || !h16.h().K1())) {
                                            x1f.a();
                                            return null;
                                        }
                                        if (!z5 && h16.t().get(0) != h15) {
                                            x1f.a();
                                            return null;
                                        }
                                        i9 = 1;
                                    }
                                }
                                if (h15.x()) {
                                    for (H5 h17 : h15.i().a()) {
                                        boolean z6 = C0705Nt.k;
                                        if (!z6 && h17.s().size() != 1) {
                                            x1f.a();
                                            return null;
                                        }
                                        if (!z6 && h17.s().get(0) != h15) {
                                            x1f.a();
                                            return null;
                                        }
                                    }
                                }
                                i8 = 1;
                            }
                        }
                        Iterator it5 = this.g.iterator();
                        while (it5.hasNext()) {
                            ((H5) it5.next()).e();
                        }
                        c0705Nt.a(this, (C3242a) null);
                        c0705Nt.a(new C3242a(), C0822Sg.b());
                        if (this.z || this.y != null) {
                            if (!C && !(this.t instanceof C0931Wl)) {
                                x1f.a();
                                return null;
                            }
                            C2539rj0 c2539rj0 = new C2539rj0(this.o, this);
                            ArrayList arrayList5 = this.y;
                            ArrayList<C2543rl0> arrayList6 = new ArrayList();
                            for (H5 h18 : c0705Nt.d) {
                                for (PW pw3 : h18.q()) {
                                    if (!pw3.t().G()) {
                                        arrayList6.add(pw3);
                                    }
                                    Iterator<C2543rl0> it6 = pw3.c0().iterator();
                                    while (it6.hasNext()) {
                                        c2539rj0.a(c2539rj0.a(pw3), c2539rj0.a(it6.next()));
                                    }
                                }
                                for (AbstractC0890Uw abstractC0890Uw3 : h18.k()) {
                                    if (abstractC0890Uw3.c() != null && !abstractC0890Uw3.a().G()) {
                                        arrayList6.add(abstractC0890Uw3.c());
                                    }
                                    if (abstractC0890Uw3.L1() && abstractC0890Uw3.c.size() == 2) {
                                        C2040lu c2040luR = abstractC0890Uw3.R();
                                        if (!C2539rj0.d && c2040luR.P2()) {
                                            x1f.a();
                                            return null;
                                        }
                                        EnumC2211nu enumC2211nu = c2040luR.j;
                                        if (enumC2211nu == EnumC2211nu.b || enumC2211nu == EnumC2211nu.g) {
                                            c2539rj0.a(c2539rj0.a((C2543rl0) c2040luR.c.get(0)), c2539rj0.a((C2543rl0) c2040luR.c.get(1)));
                                        }
                                    }
                                }
                            }
                            ArrayList arrayList7 = new ArrayList(arrayList6.size());
                            for (C2543rl0 c2543rl2 : arrayList6) {
                                C0602Jt c0602Jt = c2539rj0.b;
                                c2543rl2.a(c2539rj0.a(false, c2543rl2), c0602Jt.m, c0602Jt.o.M().i);
                                if (!c2543rl2.t().G()) {
                                    arrayList7.add(c2543rl2);
                                }
                            }
                            new C2283oj0(c2539rj0.a, c0705Nt, true).d();
                            c2539rj0.a(c0705Nt, arrayList5, arrayList7);
                        } else if (!b() || this.A) {
                            new C2283oj0(this.o, c0705Nt, false).d();
                        } else {
                            boolean z7 = C;
                            if (!z7 && (!b() || this.A)) {
                                x1f.a();
                                return null;
                            }
                            if (!z7) {
                                a(c0705Nt);
                            }
                            new C2283oj0(this.o, c0705Nt, false).b();
                        }
                        c0705Nt.y();
                        if (!C) {
                            c0705Nt.d(this.o);
                        }
                        this.t.clear();
                        this.t = null;
                        return c0705Nt;
                    }
                    if (!c0576It.a.i) {
                        if (!C && !this.w.isEmpty()) {
                            x1f.a();
                            return null;
                        }
                        H5 h19 = c0576It.a;
                        this.i = h19;
                        this.g.add(h19);
                        this.i.c(this.l.a());
                        if (c0576It instanceof C0498Ft) {
                            C0498Ft c0498Ft = (C0498Ft) c0576It;
                            int iG = this.t.g(c0498Ft.f);
                            int iD = this.t.d(iG);
                            AbstractC2004lX abstractC2004lXB = this.t.b(c0498Ft.f);
                            if (iD >= 0) {
                                C2766uP c2766uP = new C2766uP(a(iD, AbstractC2624sj0.a(c0498Ft.d, C2427qS.b(), (C0333y<?>) this.o), 1, (C0230j0) null), c0498Ft.d, this.o.M());
                                c2766uP.b(abstractC2004lXB);
                                this.i.a(c2766uP, this.B);
                            }
                            this.t.a(this, c0498Ft.e, c0498Ft.f, true);
                            H5 h20 = ((C0472Et) this.a.get(c0498Ft.f)).a;
                            this.i.g(h20);
                            a(abstractC2004lXB, new C2636ss());
                            a(h20, iG);
                            c();
                        } else {
                            if (c0576It instanceof C0524Gt) {
                                C0524Gt c0524Gt = (C0524Gt) c0576It;
                                this.t.a(this, c0524Gt.d, c0524Gt.e, false);
                                if (c0576It.b == -1) {
                                    a(c0524Gt.f, new C2636ss());
                                    c();
                                } else if (!this.w.isEmpty()) {
                                    a(this.t.f(), new C0590Jh());
                                }
                            }
                            int iE2 = this.t.e();
                            int i10 = c0576It.b;
                            while (i10 < iE2 && this.i != null) {
                                int iE3 = this.t.e(i10);
                                C0472Et c0472Et3 = (C0472Et) this.a.get(iE3);
                                if (c0472Et3 != null && (h5 = c0472Et3.a) != this.i) {
                                    a(h5, i10);
                                    H5 h21 = c0472Et3.a;
                                    boolean z8 = C;
                                    if (!z8 && this.i == null) {
                                        x1f.a();
                                        return null;
                                    }
                                    if (!z8 && this.i.c(h21)) {
                                        x1f.a();
                                        return null;
                                    }
                                    this.i.g(h21);
                                    a((AbstractC2925wD) new C2636ss());
                                    break;
                                }
                                this.j = iE3;
                                this.t.a(this, i10, i10 == c0576It.b);
                                i10++;
                            }
                        }
                    }
                }
            } else {
                int iIntValue4 = ((Integer) this.c.remove()).intValue();
                int iG2 = this.t.g(iIntValue4);
                boolean[] zArr = this.d;
                if (iG2 < zArr.length) {
                    zContains = zArr[iG2];
                } else {
                    if (this.e == null) {
                        this.e = new HashSet();
                    }
                    zContains = this.e.contains(Integer.valueOf(iG2));
                }
                if (!zContains) {
                    while (iG2 < iE) {
                        if (!C) {
                            boolean[] zArr2 = this.d;
                            if (iG2 < zArr2.length) {
                                zContains2 = zArr2[iG2];
                            } else {
                                if (this.e == null) {
                                    this.e = new HashSet();
                                }
                                zContains2 = this.e.contains(Integer.valueOf(iG2));
                            }
                            if (zContains2) {
                                x1f.a();
                                return null;
                            }
                        }
                        boolean[] zArr3 = this.d;
                        if (iG2 < zArr3.length) {
                            zArr3[iG2] = true;
                        } else {
                            if (this.e == null) {
                                this.e = new HashSet();
                            }
                            this.e.add(Integer.valueOf(iG2));
                        }
                        int iA = this.t.a(iG2, this);
                        if (iA != -1) {
                            int i11 = iA + 1;
                            if (i11 >= iE) {
                                break;
                            }
                            f(this.t.e(i11));
                            break;
                        }
                        iG2++;
                        if (iG2 < iE) {
                            int iE4 = this.t.e(iG2);
                            if (this.a.get(iE4) != null) {
                                a(iIntValue4, iE4, true);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public final C2543rl0 b(int i, Gl0 gl0) {
        C0602Jt c0602Jt;
        Gl0 gl1;
        C2543rl0 c2543rl0A;
        C0230j0 c0230j0C = g() ? this.t.c(i) : null;
        H5 h5 = this.i;
        F5 f5 = F5.b;
        PW.a aVar = PW.a.b;
        e(i);
        C2543rl0 c2543rl0A2 = h5.a(i, f5);
        if (c2543rl0A2 != null) {
            c0602Jt = this;
            c2543rl0A = c2543rl0A2;
            gl1 = gl0;
        } else {
            c0602Jt = this;
            gl1 = gl0;
            c2543rl0A = c0602Jt.a(i, h5, f5, gl1, aVar);
        }
        if (c0230j0C != null && c2543rl0A.r() != c0230j0C && !c2543rl0A.N()) {
            throw new NB("Attempt to read local " + c0230j0C + " but no local information was associated with the value being read.");
        }
        if (!C && c2543rl0A.y() && c2543rl0A.n() == null) {
            Xc0 xc0 = c0602Jt.t;
            c2543rl0A.r();
            if (!xc0.d()) {
                x1f.a();
                return null;
            }
        }
        c2543rl0A.a(gl1, c0602Jt.m, c0602Jt.o.M().i);
        c2543rl0A.S();
        return c2543rl0A;
    }

    public final void c(int i) {
        LinkedList<AbstractC0890Uw> linkedListK = this.i.k();
        SB sbX = linkedListK.get(linkedListK.size() - 1).X();
        boolean z = C;
        if (!z && sbX.c() != null) {
            x1f.a();
            return;
        }
        if (!z) {
            sbX.getClass();
        }
        com.android.tools.r8.graph.I2 i2O2 = sbX.O2();
        C2427qS c2427qSB = (sbX.m2() || (sbX instanceof C1217cC)) ? C2427qS.b() : C2427qS.h();
        boolean z2 = sbX instanceof TB;
        C0333y c0333y = this.o;
        sbX.d(a(i, 2, z2 ? sbX.a(c0333y) : AbstractC2624sj0.a(i2O2, c2427qSB, (C0333y<?>) c0333y)));
    }

    public final void d(int i) {
        com.android.tools.r8.graph.I2 i2E1 = this.m.e().E1();
        if (!i2E1.W0()) {
            AbstractC2925wD c1837ja0 = new C1837ja0(b(i, this.q.f() ? Gl0.a(this.q.c.g()) : Gl0.a((char) i2E1.f.f[0])));
            b(c1837ja0);
            this.t.a(this);
            a(c1837ja0);
            return;
        }
        if (C || this.q.d()) {
            a();
        } else {
            x1f.a();
        }
    }

    public final void e(US us, int i, int i2, int i3) {
        boolean z = (us == US.h || us == US.g) ? false : true;
        C1433em c1433em = new C1433em(us, a(i, z ? 2 : 1, AbstractC2005lY.a(us)), b(i2, Gl0.a(us)), b(i3, Gl0.a(us)));
        if (C || c1433em.g() == z) {
            a(c1433em);
        } else {
            x1f.a();
        }
    }

    /* JADX WARN: Code duplicated, block: B:33:0x00e1  */
    public final C0472Et f(int i) {
        C0472Et c0472Et;
        boolean zContains;
        if (!C && i == -1) {
            x1f.a();
            return null;
        }
        C0472Et c0472Et2 = (C0472Et) this.a.get(i);
        if (c0472Et2 != null) {
            return c0472Et2;
        }
        if (i >= 0) {
            int iG = this.t.g(i);
            boolean[] zArr = this.d;
            if (iG < zArr.length) {
                zContains = zArr[iG];
            } else {
                if (this.e == null) {
                    this.e = new HashSet();
                }
                zContains = this.e.contains(Integer.valueOf(iG));
            }
            if (zContains) {
                int iD = this.a.a(i) ? i : ((C0866Ty) this.a.c(i)).d();
                C0472Et c0472Et3 = (C0472Et) this.a.get(iD);
                C0944Wy c0944Wy = this.a;
                c0472Et3.getClass();
                c0472Et = new C0472Et();
                c0472Et.b = new C0997Yz(Collections.singleton(Integer.valueOf(iD)));
                H5 h5 = c0472Et.a;
                h5.m++;
                h5.l++;
                InterfaceC1640hA it = c0472Et3.c.iterator();
                while (it.hasNext()) {
                    C0472Et c0472Et4 = (C0472Et) c0944Wy.get(it.q());
                    c0472Et4.b.k(iD);
                    c0472Et4.b.add(i);
                }
                c0472Et.c = c0472Et3.c;
                c0472Et3.c = new C0997Yz(Collections.singleton(Integer.valueOf(i)));
                C0997Yz c0997Yz = c0472Et.e;
                c0997Yz.getClass();
                C0971Xz c0971Xz = new C0971Xz(c0997Yz);
                while (c0971Xz.hasNext()) {
                    ((C0472Et) c0944Wy.get(c0971Xz.q())).d.add(i);
                }
                c0472Et.e = new C0997Yz(c0472Et3.e);
            } else {
                c0472Et = new C0472Et();
            }
        } else {
            c0472Et = new C0472Et();
        }
        this.a.a(i, c0472Et);
        this.b.b(i, c0472Et.a);
        return c0472Et;
    }

    public final void g(US us, int i, int i2, int i3) {
        C3023xP c3023xP = new C3023xP(us, a(i, 1, AbstractC2005lY.a(us)), b(i2, Gl0.a(us)), b(i3, Gl0.a(us)));
        c3023xP.O2();
        if (C || !c3023xP.g()) {
            a(this.t.f(), c3023xP);
        } else {
            x1f.a();
        }
    }

    public final void h(US us, int i, int i2, int i3) {
        boolean z = C;
        if (!z && !b(us)) {
            x1f.a();
            return;
        }
        C3023xP c3023xP = new C3023xP(us, a(i, 1, AbstractC2005lY.a(us)), b(i2, Gl0.a(us)), a(i3));
        c3023xP.O2();
        if (z || !c3023xP.g()) {
            a(this.t.f(), c3023xP);
        } else {
            x1f.a();
        }
    }

    public final void i(US us, int i, int i2, int i3) {
        boolean z = C;
        if (!z && !a(us)) {
            x1f.a();
            return;
        }
        C2772uV c2772uV = new C2772uV(us, a(i, 1, AbstractC2005lY.a(us)), b(i2, Gl0.a(us)), b(i3, Gl0.a(us)));
        c2772uV.O2();
        if (z || !c2772uV.g()) {
            a(this.t.f(), c2772uV);
        } else {
            x1f.a();
        }
    }

    public final void j(US us, int i, int i2, int i3) {
        boolean z = C;
        if (!z && !b(us)) {
            x1f.a();
            return;
        }
        C2772uV c2772uV = new C2772uV(us, a(i, 1, AbstractC2005lY.a(us)), b(i2, Gl0.a(us)), a(i3));
        c2772uV.O2();
        if (z || !c2772uV.g()) {
            a(this.t.f(), c2772uV);
        } else {
            x1f.a();
        }
    }

    public final void k(US us, int i, int i2, int i3) {
        boolean z = (us == US.h || us == US.g) ? false : true;
        C1036a50 c1036a50 = new C1036a50(us, a(i, z ? 2 : 1, AbstractC2005lY.a(us)), b(i2, Gl0.a(us)), b(i3, Gl0.a(us)));
        if (C || c1036a50.g() == z) {
            a(this.t.f(), c1036a50);
        } else {
            x1f.a();
        }
    }

    public final void l(US us, int i, int i2, int i3) {
        boolean z = C;
        if (!z && !b(us)) {
            x1f.a();
            return;
        }
        boolean z2 = (us == US.h || us == US.g) ? false : true;
        C1036a50 c1036a50 = new C1036a50(us, a(i, z2 ? 2 : 1, AbstractC2005lY.a(us)), b(i2, Gl0.a(us)), a(i3));
        if (z || c1036a50.g() == z2) {
            a(this.t.f(), c1036a50);
        } else {
            x1f.a();
        }
    }

    public final void m(US us, int i, int i2, int i3) {
        boolean z = C;
        if (!z && us == US.h) {
            x1f.a();
            return;
        }
        C1764ig0 c1764ig0 = new C1764ig0(us, a(i, 1, AbstractC2005lY.a(us)), a(i3), b(i2, Gl0.a(us)));
        if (z || !c1764ig0.g()) {
            a(this.t.f(), c1764ig0);
        } else {
            x1f.a();
        }
    }

    public final void n(US us, int i, int i2, int i3) {
        boolean z = C;
        if (!z && !a(us)) {
            x1f.a();
            return;
        }
        C3037xb0 c3037xb0 = new C3037xb0(us, a(i, 1, AbstractC2005lY.a(us)), b(i2, Gl0.a(us)), b(i3, Gl0.c));
        if (z || !c3037xb0.g()) {
            a(this.t.f(), c3037xb0);
        } else {
            x1f.a();
        }
    }

    public final void o(US us, int i, int i2, int i3) {
        boolean z = C;
        if (!z && !a(us)) {
            x1f.a();
            return;
        }
        Bb0 bb0 = new Bb0(us, a(i, 1, AbstractC2005lY.a(us)), b(i2, Gl0.a(us)), b(i3, Gl0.c));
        if (z || !bb0.g()) {
            a(this.t.f(), bb0);
        } else {
            x1f.a();
        }
    }

    public final void p(US us, int i, int i2, int i3) {
        C1764ig0 c1764ig0 = new C1764ig0(us, a(i, 1, AbstractC2005lY.a(us)), b(i2, Gl0.a(us)), b(i3, Gl0.a(us)));
        if (C || !c1764ig0.g()) {
            a(this.t.f(), c1764ig0);
        } else {
            x1f.a();
        }
    }

    public final void q(US us, int i, int i2, int i3) {
        boolean z = C;
        if (!z && !a(us)) {
            x1f.a();
            return;
        }
        C1603gl0 c1603gl0 = new C1603gl0(us, a(i, 1, AbstractC2005lY.a(us)), b(i2, Gl0.a(us)), b(i3, Gl0.c));
        if (z || !c1603gl0.g()) {
            a(this.t.f(), c1603gl0);
        } else {
            x1f.a();
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0077  */
    public final void r(US us, int i, int i2, int i3) {
        AbstractC0890Uw c1487fS;
        boolean z = C;
        if (!z && !a(us)) {
            x1f.a();
            return;
        }
        C2543rl0 c2543rl0B = b(i2, Gl0.a(us));
        C2543rl0 c2543rl0B2 = b(i3, Gl0.a(us));
        C2543rl0 c2543rl0A = a(i, 1, AbstractC2005lY.a(us));
        C2752uB c2752uBM = this.o.M();
        c2752uBM.getClass();
        if (c2752uBM.b(EnumC3077y2.w) && c2543rl0B2.H()) {
            C1678hg c1678hgF = c2543rl0B2.k().F();
            c1678hgF.getClass();
            if (!C1678hg.k && us != US.e && us != US.f) {
                x1f.a();
                return;
            } else if (us != US.e ? c1678hgF.O2() != -1 : c1678hgF.N2() != -1) {
                Ym0 ym0 = new Ym0(us, c2543rl0A, c2543rl0B, c2543rl0B2);
                ym0.O2();
                c1487fS = ym0;
            } else {
                c1487fS = new C1487fS(us, c2543rl0A, c2543rl0B);
            }
        } else {
            Ym0 ym1 = new Ym0(us, c2543rl0A, c2543rl0B, c2543rl0B2);
            ym1.O2();
            c1487fS = ym1;
        }
        if (z || !c1487fS.g()) {
            a(this.t.f(), c1487fS);
        } else {
            x1f.a();
        }
    }

    public final void s(US us, int i, int i2, int i3) {
        AbstractC0890Uw c1487fS;
        boolean z = C;
        if (!z && !b(us)) {
            x1f.a();
            return;
        }
        C2543rl0 c2543rl0B = b(i2, Gl0.a(us));
        C2752uB c2752uBM = this.o.M();
        c2752uBM.getClass();
        if (c2752uBM.b(EnumC3077y2.w) && i3 == -1) {
            c1487fS = new C1487fS(us, a(i, 1, AbstractC2005lY.a(us)), c2543rl0B);
        } else {
            Ym0 ym0 = new Ym0(us, a(i, 1, AbstractC2005lY.a(us)), c2543rl0B, a(i3));
            ym0.O2();
            c1487fS = ym0;
        }
        if (z || !c1487fS.g()) {
            a(this.t.f(), c1487fS);
        } else {
            x1f.a();
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("blocks:\n");
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            sb.append(((H5) it.next()).P());
            sb.append("\n");
        }
        return sb.toString();
    }

    public final boolean g() {
        if (this.o.M().Z0) {
            return true;
        }
        return this.m.a().f(this.o);
    }

    public final AbstractC3148ys e() {
        return this.p;
    }

    public final H5 g(int i) {
        return ((C0472Et) this.a.get(i)).a;
    }

    public final void e(int i) {
        if (i >= 0) {
            if (this.t.f(i)) {
                return;
            }
            throw new C0613Ke("Invalid use of register " + i);
        }
        throw new C1727iB("Invalid register");
    }

    public final void c(US us, int i, int i2, int i3) {
        boolean z = C;
        if (!z && !a(us)) {
            x1f.a();
            return;
        }
        C2308p2 c2308p2 = new C2308p2(us, a(i, 1, AbstractC2005lY.a(us)), b(i2, Gl0.a(us)), b(i3, Gl0.a(us)));
        c2308p2.O2();
        if (z || !c2308p2.g()) {
            a(this.t.f(), c2308p2);
        } else {
            x1f.a();
        }
    }

    public static /* synthetic */ boolean d(AbstractC0890Uw abstractC0890Uw) {
        return !abstractC0890Uw.k1();
    }

    public final void d(US us, int i, int i2, int i3) {
        boolean z = C;
        if (!z && !b(us)) {
            x1f.a();
            return;
        }
        C2308p2 c2308p2 = new C2308p2(us, a(i, 1, AbstractC2005lY.a(us)), b(i2, Gl0.a(us)), a(i3));
        c2308p2.O2();
        if (z || !c2308p2.g()) {
            a(this.t.f(), c2308p2);
        } else {
            x1f.a();
        }
    }

    public static /* synthetic */ void c(AbstractC0890Uw abstractC0890Uw) {
        if (C) {
            return;
        }
        abstractC0890Uw.getClass();
    }

    public final void c() {
        boolean z = C;
        if (!z && this.i == null) {
            x1f.a();
            return;
        }
        this.i.a(this);
        this.i = null;
        this.u = false;
        this.j = -1;
        if (z || this.w.isEmpty()) {
            return;
        }
        x1f.a();
    }

    public final com.android.tools.r8.graph.B1 d() {
        return this.o.a();
    }

    public final void b(int i, C0230j0 c0230j0) {
        C0602Jt c0602Jt;
        int i2;
        boolean z = C;
        if (!z && c0230j0 == null) {
            x1f.a();
            return;
        }
        if (g()) {
            if (!z && !g()) {
                x1f.a();
                return;
            }
            Gl0 gl0A = Gl0.a(c0230j0.c);
            H5 h5 = this.i;
            F5 f5 = F5.b;
            PW.a aVar = PW.a.c;
            e(i);
            C2543rl0 c2543rl0A = h5.a(i, f5);
            if (c2543rl0A != null) {
                c0602Jt = this;
                i2 = i;
            } else {
                c0602Jt = this;
                i2 = i;
                c2543rl0A = c0602Jt.a(i2, h5, f5, gl0A, aVar);
            }
            if (c2543rl0A.r() == c0230j0 && !c0602Jt.i.f.isEmpty() && c0602Jt.i.k().getLast().c() == c2543rl0A) {
                return;
            }
            c0602Jt.a(c0602Jt.t.f(), new C0642Lh(c0602Jt.a(i2, c2543rl0A.t(), 1, c0230j0), c2543rl0A));
        }
    }

    public final void b(US us, int i, int i2, int i3) {
        boolean z = C;
        if (!z && !b(us)) {
            x1f.a();
            return;
        }
        C1029a2 c1029a2A = C1029a2.a(us, a(i, 1, AbstractC2005lY.a(us)), b(i2, Gl0.a(us)), a(i3));
        if (z || !c1029a2A.g()) {
            a(this.t.f(), c1029a2A);
        } else {
            x1f.a();
        }
    }

    public final void b(KN kn, int i, int i2, int i3) {
        N3 n3A = N3.a(kn, b(i2, Gl0.b), b(i3, Gl0.c), b(i, Gl0.a(kn)));
        if (!kn.a()) {
            if (this.y == null) {
                this.y = new ArrayList();
            }
            this.y.add(n3A);
        }
        a(n3A);
    }

    public final void b(int i, int i2, C0245l1 c0245l1) {
        a(new C0682Mw(c0245l1, b(i2, Gl0.b), b(i, Gl0.a(c0245l1.i)), false));
    }

    public final void b(int i) {
        boolean z = C;
        if (!z && this.i.s().isEmpty()) {
            x1f.a();
            return;
        }
        if (!z && !this.i.s().stream().allMatch(new Predicate() { // from class: uu7
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C0602Jt.a((H5) obj);
            }
        })) {
            x1f.a();
            return;
        }
        C2543rl0 c2543rl0B = b(i, Gl0.b);
        if (z) {
            return;
        }
        if (!c2543rl0B.j()) {
            if (z) {
                return;
            }
            AbstractC0890Uw abstractC0890Uw = c2543rl0B.c;
            abstractC0890Uw.getClass();
            if (abstractC0890Uw instanceof C2766uP) {
                return;
            }
            x1f.a();
            return;
        }
        for (C2543rl0 c2543rl0 : c2543rl0B.m().c0()) {
            if (!C) {
                AbstractC0890Uw abstractC0890Uw2 = c2543rl0.c;
                abstractC0890Uw2.getClass();
                if (!(abstractC0890Uw2 instanceof C2766uP)) {
                    x1f.a();
                    return;
                }
            }
        }
    }

    public final void b(int i, C0245l1 c0245l1) {
        a(new C2614se0(c0245l1, b(i, Gl0.a(c0245l1.i))));
    }

    public final void b(US us, int i, int i2) {
        C2543rl0 c2543rl0C;
        AbstractC0890Uw c1487fS;
        C2543rl0 c2543rl0B = b(i2, Gl0.a(us));
        C2543rl0 c2543rl0A = a(i, 1, AbstractC2005lY.a(us));
        C2752uB c2752uBM = this.o.M();
        c2752uBM.getClass();
        if (c2752uBM.b(EnumC3077y2.w)) {
            c1487fS = new C1487fS(us, c2543rl0A, c2543rl0B);
        } else {
            Gl0 gl0A = Gl0.a(us);
            if (gl0A == Gl0.c) {
                c2543rl0C = a(-1L);
            } else if (!C && gl0A != Gl0.g) {
                x1f.a();
                return;
            } else {
                AbstractC0890Uw c1678hg = new C1678hg(new C2543rl0(this.k.a(), AbstractC2624sj0.l(), null), -1L);
                a(c1678hg);
                c2543rl0C = c1678hg.c();
            }
            Ym0 ym0 = new Ym0(us, c2543rl0A, c2543rl0B, c2543rl0C);
            ym0.O2();
            c1487fS = ym0;
        }
        if (C || !c1487fS.g()) {
            a(this.t.f(), c1487fS);
        } else {
            x1f.a();
        }
    }

    public final boolean b() {
        return !this.o.o() && this.t.a();
    }

    public final void b(AbstractC0890Uw abstractC0890Uw) {
        if (!g()) {
            boolean z = C;
            if (!z && this.v != null) {
                x1f.a();
                return;
            } else {
                if (z || this.w.isEmpty()) {
                    return;
                }
                x1f.a();
                return;
            }
        }
        C2543rl0 c2543rl0 = this.v;
        if (c2543rl0 != null && c2543rl0.r() == abstractC0890Uw.k()) {
            if (!C && abstractC0890Uw.c() == null) {
                x1f.a();
                return;
            }
            this.v.a(abstractC0890Uw);
        }
        Iterator it = this.w.iterator();
        while (it.hasNext()) {
            ((C2543rl0) it.next()).a(abstractC0890Uw);
        }
        this.v = null;
        this.w.clear();
    }

    public static boolean b(US us) {
        return (us == US.g || us == US.h || us == US.f) ? false : true;
    }

    public final void f(US us, int i, int i2, int i3) {
        boolean z = C;
        if (!z && !b(us)) {
            x1f.a();
            return;
        }
        boolean z2 = (us == US.h || us == US.g) ? false : true;
        C1433em c1433em = new C1433em(us, a(i, z2 ? 2 : 1, AbstractC2005lY.a(us)), b(i2, Gl0.a(us)), a(i3));
        if (z || c1433em.g() == z2) {
            a(c1433em);
        } else {
            x1f.a();
        }
    }

    public final com.android.tools.r8.graph.B5 f() {
        return this.m;
    }

    public static C0602Jt a(com.android.tools.r8.graph.B5 b5, C0333y c0333y, Xc0 xc0) {
        AbstractC3148ys abstractC3148ysA = b5.e().U0().a(c0333y);
        return new C0602Jt(b5, c0333y, abstractC3148ysA, xc0, c0333y.A().e(abstractC3148ysA, b5.getReference()), new AS());
    }

    public final void a(int i, C0231j1 c0231j1, BiConsumer biConsumer) {
        int i2;
        com.android.tools.r8.graph.I2 i2A;
        AbstractC2624sj0 abstractC2624sj0A;
        com.android.tools.r8.graph.proto.c cVar = this.q.b;
        if (c0231j1.z0()) {
            i2 = 0;
        } else {
            if (!C && cVar.b(0) != 0) {
                x1f.a();
                return;
            }
            biConsumer.accept(Integer.valueOf(i), c0231j1.E0());
            com.android.tools.r8.graph.B5 b5 = this.n;
            C2543rl0 c2543rl0A = a(i, AbstractC2624sj0.a(this.m.s(), (b5 == null || b5 == this.m) ? C2427qS.b() : C2427qS.h(), (C0333y<?>) this.o), 1, g() ? this.t.a(i) : null);
            a(this.t.f(), new C1201c3(c2543rl0A, this.i.f.size(), false));
            this.r = c2543rl0A;
            boolean z = C2543rl0.q;
            if (!z && !c2543rl0A.F()) {
                x1f.a();
                return;
            } else if (!z && c2543rl0A.l) {
                x1f.a();
                return;
            } else {
                c2543rl0A.l = true;
                i++;
                i2 = 1;
            }
        }
        int iA = ((com.android.tools.r8.graph.proto.c.a(Integer.MAX_VALUE, cVar.a) + c0231j1.c1().size()) + (!c0231j1.z0())) - this.q.a.size();
        int i3 = 0;
        while (i2 < iA) {
            com.android.tools.r8.graph.proto.b bVarA = cVar.a(i2);
            if (bVarA.c()) {
                com.android.tools.r8.graph.proto.g gVarA = bVarA.a();
                biConsumer.accept(Integer.valueOf(i), gVarA.f());
                abstractC2624sj0A = AbstractC2624sj0.a(gVarA.f(), C2427qS.h(), (C0333y<?>) this.o);
                a(i, abstractC2624sj0A);
                i3++;
            } else {
                int iA2 = cVar.c.a(i2 - i3);
                if (bVarA instanceof com.android.tools.r8.graph.proto.k) {
                    com.android.tools.r8.graph.proto.k kVarB = bVarA.b();
                    if (!C && c0231j1.getReference().a(iA2, c0231j1.z0()) != kVarB.f()) {
                        x1f.a();
                        return;
                    }
                    i2A = kVarB.g();
                } else {
                    i2A = c0231j1.getReference().a(iA2, c0231j1.z0());
                }
                biConsumer.accept(Integer.valueOf(i), i2A);
                AbstractC2624sj0 abstractC2624sj0A2 = AbstractC2624sj0.a(i2A, C2427qS.h(), (C0333y<?>) this.o);
                if (i2A.J0()) {
                    C1201c3 c1201c3 = new C1201c3(a(i, AbstractC2624sj0.k(), 1, g() ? this.t.a(i) : null), this.i.f.size(), true);
                    if (this.s == null) {
                        this.s = new ArrayList();
                    }
                    a(this.t.f(), c1201c3);
                    this.s.add(c1201c3.c());
                } else {
                    a(i, abstractC2624sj0A2);
                }
                abstractC2624sj0A = abstractC2624sj0A2;
            }
            i2++;
            i += abstractC2624sj0A.O();
        }
        for (AbstractC0493Fo abstractC0493Fo : this.q.a) {
            com.android.tools.r8.graph.I2 i2A2 = c0231j1.getReference().a(cVar.c.a(i2 - i3), c0231j1.z0());
            if (abstractC0493Fo.a()) {
                C1201c3 c1201c4 = new C1201c3(new C2543rl0(this.k.a(), i2A2.U0() ? AbstractC2624sj0.m() : i2A2.b(this.o), null), this.i.f.size(), false);
                if (this.s == null) {
                    this.s = new ArrayList();
                }
                a(this.t.f(), c1201c4);
                this.s.add(c1201c4.c());
            } else {
                a(i, abstractC0493Fo.a(this.o, i2A2));
            }
            i2++;
            i += i2A2.F0();
        }
    }

    public static AbstractC2624sj0 a(KN kn) {
        switch (kn.ordinal()) {
            case 0:
                return AbstractC2624sj0.f();
            case 1:
            case 2:
            case XmlPullParser.END_TAG /* 3 */:
            case 4:
                return AbstractC2624sj0.k();
            case XmlPullParser.CDSECT /* 5 */:
                return AbstractC2624sj0.j();
            case XmlPullParser.ENTITY_REF /* 6 */:
                return AbstractC2624sj0.l();
            case 7:
                return AbstractC2624sj0.i();
            case 8:
                return AbstractC2624sj0.o();
            case 9:
                return AbstractC2624sj0.q();
            default:
                defpackage.gk0.a("Unexpected member type: ", kn);
                return null;
        }
    }

    public final void a(H5 h5, int i) {
        if (h5.i) {
            return;
        }
        this.f.add(new C0576It(h5, i));
    }

    public static void a(C0705Nt c0705Nt) {
        c0705Nt.s().forEach(new Consumer() { // from class: wu7
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C0602Jt.c((AbstractC0890Uw) obj);
            }
        });
    }

    public static boolean a(H5 h5) {
        AbstractC0890Uw abstractC0890Uw = (AbstractC0890Uw) h5.f.get(0);
        abstractC0890Uw.getClass();
        return abstractC0890Uw instanceof C2766uP;
    }

    public final void a(AbstractC0890Uw abstractC0890Uw) {
        if (C || !abstractC0890Uw.d2()) {
            a(this.t.f(), abstractC0890Uw);
        } else {
            x1f.a();
        }
    }

    public final void a(int i, C0230j0 c0230j0) {
        C0602Jt c0602Jt;
        boolean z = C;
        if (!z && c0230j0 == null) {
            x1f.a();
            return;
        }
        if (g()) {
            if (!z && !g()) {
                x1f.a();
                return;
            }
            Gl0 gl0A = Gl0.a(c0230j0.c);
            H5 h5 = this.i;
            F5 f5 = F5.b;
            PW.a aVar = PW.a.c;
            e(i);
            C2543rl0 c2543rl0A = h5.a(i, f5);
            if (c2543rl0A != null) {
                c0602Jt = this;
            } else {
                c0602Jt = this;
                c2543rl0A = c0602Jt.a(i, h5, f5, gl0A, aVar);
            }
            if (c2543rl0A.N() || c2543rl0A.r() != c0230j0) {
                return;
            }
            c0602Jt.w.add(c2543rl0A);
        }
    }

    public final void a(AbstractC2004lX abstractC2004lX) {
        if (g()) {
            boolean z = C;
            if (!z && this.v != null) {
                x1f.a();
                return;
            }
            if (!z) {
                AbstractC2004lX abstractC2004lXF = this.t.f();
                abstractC2004lXF.getClass();
                if (!com.android.tools.r8.utils.structural.k.a(abstractC2004lXF, abstractC2004lX)) {
                    x1f.a();
                    return;
                }
            }
            if (!this.w.isEmpty()) {
                if (this.i.k().isEmpty()) {
                    a(this.t.f(), new C0590Jh());
                } else {
                    if (!z && this.w.contains(this.i.k().getLast().c())) {
                        x1f.a();
                        return;
                    }
                    b(this.i.k().getLast());
                }
            }
            a(this.t.f(), new C0719Oh());
        }
    }

    public final void a(KN kn, int i, int i2, int i3) {
        AbstractC2624sj0 abstractC2624sj0A;
        C2543rl0 c2543rl0B = b(i2, Gl0.b);
        C2543rl0 c2543rl0B2 = b(i3, Gl0.c);
        if (kn == KN.b && b()) {
            AbstractC2624sj0 abstractC2624sj0T = c2543rl0B.t();
            abstractC2624sj0T.getClass();
            if (abstractC2624sj0T instanceof C1034a40) {
                abstractC2624sj0A = AbstractC2624sj0.m();
            } else if (c2543rl0B.t().r()) {
                abstractC2624sj0A = c2543rl0B.t().a().R();
            } else {
                if (!C) {
                    AbstractC2624sj0 abstractC2624sj0T2 = c2543rl0B.t();
                    abstractC2624sj0T2.getClass();
                    if (!(abstractC2624sj0T2 instanceof C1720i7) || !this.A) {
                        x1f.a();
                        return;
                    }
                }
                abstractC2624sj0A = a(kn);
            }
        } else {
            abstractC2624sj0A = a(kn);
        }
        H3 h3 = new H3(kn, a(i, 2, abstractC2624sj0A), c2543rl0B, c2543rl0B2);
        if (!kn.a()) {
            if (this.y == null) {
                this.y = new ArrayList();
            }
            this.y.add(h3);
        }
        a(h3);
    }

    public final void a(int i, com.android.tools.r8.graph.I2 i2, boolean z) {
        C2543rl0 c2543rl0B = b(i, Gl0.b);
        C2543rl0 c2543rl0A = a(i, 2, AbstractC2624sj0.a(i2, c2543rl0B.t().N(), (C0333y<?>) this.o));
        a(z ? new Aa0(c2543rl0A, c2543rl0B, i2) : new C0428Db(c2543rl0A, c2543rl0B, i2));
    }

    public final void a(AbstractC2624sj0 abstractC2624sj0, int i, long j) {
        C1678hg c1678hg = new C1678hg(a(i, 1, abstractC2624sj0), j);
        if (C || !c1678hg.g()) {
            a(c1678hg);
        } else {
            x1f.a();
        }
    }

    public final void a(int i, com.android.tools.r8.graph.I2 i2) {
        C0333y c0333y = this.o;
        C2427qS c2427qSB = C2427qS.b();
        boolean z = AbstractC2624sj0.a;
        a(new C1169bg(a(i, 2, AbstractC2624sj0.a(c0333y.a().o2, c2427qSB, (C0333y<?>) c0333y).b()), i2, false));
    }

    public final void a(int i, C0336y2 c0336y2) {
        if (!C) {
            C2752uB c2752uBM = this.o.M();
            c2752uBM.getClass();
            if (!c2752uBM.b(EnumC3077y2.D)) {
                x1f.a();
                return;
            }
        }
        a(new C1336dg(a(i, 2, AbstractC2624sj0.a(this.o.a().E2, C2427qS.b(), (C0333y<?>) this.o)), c0336y2));
    }

    public final void a(int i, com.android.tools.r8.graph.E2 e2) {
        if (!C) {
            C2752uB c2752uBM = this.o.M();
            c2752uBM.getClass();
            if (!c2752uBM.b(EnumC3077y2.D)) {
                x1f.a();
                return;
            }
        }
        a(new C1421eg(a(i, 2, AbstractC2624sj0.a(this.o.a().H2, C2427qS.b(), (C0333y<?>) this.o)), e2));
    }

    public final void a(Gl0 gl0, int i, int i2) {
        C2543rl0 c2543rl0B = b(i2, gl0);
        if (g()) {
            C0230j0 c0230j0A = g() ? this.t.a(i) : null;
            if (c0230j0A != null && c0230j0A != c2543rl0B.r()) {
                a(this.t.f(), new C0642Lh(a(i, 1, c2543rl0B.t()), c2543rl0B));
                return;
            } else if (!this.w.isEmpty()) {
                a(this.t.f(), new C0590Jh());
            }
        }
        this.i.a(i, c2543rl0B, 1);
    }

    public final void a(int i) {
        H5 h5G = g(i);
        if (!C && this.i.c(h5G)) {
            x1f.a();
            return;
        }
        this.i.g(h5G);
        a(h5G, this.t.g(i));
        a((AbstractC2925wD) new C2636ss());
    }

    public final void a(int i, int i2, C0245l1 c0245l1) {
        a(this.t.f(), new C3152yw(c0245l1, a(i, 2, AbstractC2624sj0.a(c0245l1.i, C2427qS.h(), (C0333y<?>) this.o)), b(i2, Gl0.b)));
    }

    public final void a(C0245l1[] c0245l1Arr, C0919Vz c0919Vz, int i) {
        if (!C && c0245l1Arr.length != c0919Vz.c) {
            x1f.a();
            return;
        }
        ArrayList arrayList = new ArrayList(c0919Vz.c);
        Iterator itO = c0919Vz.o(0);
        while (itO.hasNext()) {
            arrayList.add(b(((W) itO).q(), Gl0.b));
        }
        a(new C2821v20(c0245l1Arr, a(i, 2, AbstractC2624sj0.a(this.o.a().d2, C2427qS.b(), (C0333y<?>) this.o)), arrayList));
    }

    public final void a(EnumC2326pC enumC2326pC, AbstractC0259n1 abstractC0259n1, com.android.tools.r8.graph.E2 e2, ArrayList arrayList, boolean z) {
        boolean z2 = C;
        if (!z2 && enumC2326pC == EnumC2326pC.k) {
            if (!z2 && !(abstractC0259n1 instanceof C0322w2)) {
                x1f.a();
                return;
            }
            C0322w2 c0322w2 = (C0322w2) abstractC0259n1;
            if (c0322w2.f == this.o.a().E2 && !z2) {
                C2752uB c2752uBM = this.o.M();
                c2752uBM.getClass();
                if (!c2752uBM.b(EnumC3077y2.B)) {
                    x1f.a();
                    return;
                }
            }
            if (c0322w2.f == this.o.a().D2 && !z2) {
                C2752uB c2752uBM2 = this.o.M();
                c2752uBM2.getClass();
                if (!c2752uBM2.b(EnumC3077y2.B)) {
                    x1f.a();
                    return;
                }
            }
        }
        a(SB.a(enumC2326pC, abstractC0259n1, e2, null, arrayList, z));
    }

    public final void a(EnumC2326pC enumC2326pC, C0322w2 c0322w2, com.android.tools.r8.graph.E2 e2, List list, List list2, boolean z) {
        if (!C && list.size() != list2.size()) {
            x1f.a();
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(b(((Integer) list2.get(i)).intValue(), Gl0.a((El0) list.get(i))));
        }
        a(enumC2326pC, c0322w2, e2, arrayList, z);
    }

    public final void a(EnumC2326pC enumC2326pC, C0322w2 c0322w2, com.android.tools.r8.graph.E2 e2, int i, int[] iArr) {
        String strO0;
        ArrayList arrayList = new ArrayList(i);
        int iC = 0;
        if (enumC2326pC != EnumC2326pC.f) {
            int i2 = iArr[0];
            Gl0 gl0 = Gl0.b;
            arrayList.add(b(i2, gl0));
            iC = gl0.c();
        }
        if (enumC2326pC == EnumC2326pC.k) {
            strO0 = e2.o0();
        } else {
            strO0 = c0322w2.i.o0();
        }
        for (int i3 = 1; i3 < strO0.length(); i3++) {
            Gl0 gl0A = Gl0.a(strO0.charAt(i3));
            arrayList.add(b(iArr[iC], gl0A));
            iC += gl0A.c();
        }
        a(iC, i);
        if (C || this.o.M().Z()) {
            a(enumC2326pC, (AbstractC0259n1) c0322w2, e2, arrayList, false);
        } else {
            x1f.a();
        }
    }

    public final void a(EnumC2326pC enumC2326pC, C0322w2 c0322w2, com.android.tools.r8.graph.E2 e2, int i, int i2) {
        int iC;
        String strO0;
        ArrayList arrayList = new ArrayList(i);
        if (enumC2326pC != EnumC2326pC.f) {
            Gl0 gl0 = Gl0.b;
            arrayList.add(b(i2, gl0));
            iC = gl0.c() + i2;
        } else {
            iC = i2;
        }
        if (enumC2326pC == EnumC2326pC.k) {
            strO0 = e2.o0();
        } else {
            strO0 = c0322w2.i.o0();
        }
        for (int i3 = 1; i3 < strO0.length(); i3++) {
            Gl0 gl0A = Gl0.a(strO0.charAt(i3));
            arrayList.add(b(iC, gl0A));
            iC += gl0A.c();
        }
        a(iC, i2 + i);
        if (C || this.o.M().Z()) {
            a(enumC2326pC, (AbstractC0259n1) c0322w2, e2, arrayList, false);
        } else {
            x1f.a();
        }
    }

    public static void a(int i, int i2) {
        if (i == i2) {
            return;
        }
        throw new C0613Ke("Invalid invoke instruction. Expected use of " + i + " argument registers, found actual use of " + i2);
    }

    public final void a() {
        AbstractC2925wD c1837ja0 = new C1837ja0();
        b(c1837ja0);
        this.t.a(this);
        a(c1837ja0);
    }

    public final void a(int i, C0245l1 c0245l1) {
        a(this.t.f(), new C2529re0(c0245l1, a(i, 2, AbstractC2624sj0.a(c0245l1.i, C2427qS.h(), (C0333y<?>) this.o))));
    }

    public final void a(int i, int[] iArr, int i2, int[] iArr2) {
        int i3;
        int length = iArr2.length;
        boolean z = C;
        if (!z && iArr.length != 1 && iArr.length != length) {
            x1f.a();
            return;
        }
        if (length == 0) {
            a(i2);
            return;
        }
        C2543rl0 c2543rl0B = b(i, Gl0.c);
        C0919Vz c0919Vz = new C0919Vz(length);
        C0919Vz c0919Vz2 = new C0919Vz(length);
        if (iArr.length == 1) {
            int i4 = iArr[0];
            i3 = 0;
            for (int i5 = 0; i5 < length; i5++) {
                if (iArr2[i5] != i2) {
                    c0919Vz.add(i4);
                    c0919Vz2.add(iArr2[i5]);
                } else {
                    i3++;
                }
                i4++;
            }
        } else {
            if (!z && iArr.length != length) {
                x1f.a();
                return;
            }
            i3 = 0;
            for (int i6 = 0; i6 < length; i6++) {
                if (iArr2[i6] != i2) {
                    c0919Vz.add(iArr[i6]);
                    c0919Vz2.add(iArr2[i6]);
                } else {
                    i3++;
                }
            }
        }
        H5 h5 = ((C0472Et) this.a.get(i2)).a;
        h5.m -= i3;
        h5.l -= i3;
        if (i3 == length) {
            if (!C && c0919Vz.c != 0) {
                x1f.a();
                return;
            } else {
                a(i2);
                return;
            }
        }
        int[] iArrA = c0919Vz.a((int[]) null);
        int[] iArrA2 = c0919Vz2.a((int[]) null);
        if (!C && iArrA.length != iArrA2.length) {
            x1f.a();
            return;
        }
        int[] iArr3 = new int[iArrA2.length];
        HashMap map = new HashMap();
        H5 h6 = ((C0472Et) this.a.get(i2)).a;
        this.i.g(h6);
        a(h6, this.t.g(i2));
        int size = this.i.t().size() - 1;
        map.put(Integer.valueOf(i2), Integer.valueOf(size));
        for (int i7 = 0; i7 < iArrA2.length; i7++) {
            int i8 = iArrA2[i7];
            H5 h7 = ((C0472Et) this.a.get(i8)).a;
            Integer num = (Integer) map.get(Integer.valueOf(i8));
            if (num == null) {
                this.i.g(h7);
                a(h7, this.t.g(i8));
                int size2 = this.i.t().size() - 1;
                map.put(Integer.valueOf(i8), Integer.valueOf(size2));
                iArr3[i7] = size2;
            } else {
                h7.d();
                iArr3[i7] = num.intValue();
            }
        }
        a((AbstractC2925wD) new FA(c2543rl0B, iArrA, iArr3, size));
    }

    public final C2543rl0 a(int i, H5 h5, F5 f5, Gl0 gl0, PW.a aVar) {
        H5 h6;
        F5 f6;
        ArrayList<C1405eW> arrayList;
        C2543rl0 c2543rl0A;
        H5 h7;
        C2543rl0 c2543rl0;
        H5 h8;
        PW pw;
        C2543rl0 c2543rl0A2;
        C2543rl0 c2543rl0A3;
        QW qw = null;
        if (h5.E() && h5.s().size() == 1) {
            ArrayList arrayList2 = new ArrayList(this.g.size());
            h6 = h5;
            f6 = f5;
            while (true) {
                if (!C) {
                    h6.T();
                }
                H5 h9 = h6.s().get(0);
                F5 f5B = h9.b(h6);
                e(i);
                c2543rl0A3 = h9.a(i, f5B);
                if (c2543rl0A3 != null) {
                    break;
                }
                arrayList2.add(new C1405eW(h6, f6));
                if (!h9.E() || h9.s().size() != 1) {
                    h6 = h9;
                    f6 = f5B;
                    break;
                }
                h6 = h9;
                f6 = f5B;
            }
            arrayList = arrayList2;
            c2543rl0A = c2543rl0A3;
        } else {
            h6 = h5;
            f6 = f5;
            arrayList = null;
            c2543rl0A = null;
        }
        if (c2543rl0A != null) {
            h7 = h6;
            c2543rl0 = c2543rl0A;
        } else if (h6 == this.h && aVar == PW.a.c) {
            if (!C && !h6.s().isEmpty()) {
                x1f.a();
                return null;
            }
            c2543rl0A = a(i, gl0);
            h7 = h6;
            c2543rl0 = c2543rl0A;
        } else {
            C0230j0 c0230j0A = g() ? this.t.a(i, this.b.b(h6)) : null;
            AbstractC2624sj0 abstractC2624sj0A = C2539rj0.a(gl0);
            this.z |= !abstractC2624sj0A.G();
            if (b() && !this.A) {
                com.android.tools.r8.graph.I2 i2A = this.t.a(i, this.b.b(h6), aVar);
                if (i2A != null) {
                    H5 h10 = h6;
                    C0230j0 c0230j0 = c0230j0A;
                    QW qw2 = new QW(this.k.a(), h10, AbstractC2624sj0.a(i2A, C2427qS.h(), (C0333y<?>) this.o), c0230j0, aVar);
                    c0230j0A = c0230j0;
                    h6 = h10;
                    qw = qw2;
                } else if (aVar != PW.a.c) {
                    if (!C && !this.m.e().T0().e(C1159bb.i)) {
                        x1f.a();
                        return null;
                    }
                    this.A = true;
                } else {
                    throw new NB("Information in locals-table is invalid with respect to the stack map table. Local refers to non-present stack map type for register: " + i + " with constraint " + gl0 + ".");
                }
            }
            if (qw == null) {
                H5 h11 = h6;
                pw = new PW(this.k.a(), h11, abstractC2624sj0A, c0230j0A, aVar);
                h8 = h11;
            } else {
                h8 = h6;
                pw = qw;
            }
            if (!h8.E()) {
                h8.a(i, pw, f6);
            } else {
                h8.a(i, (C2543rl0) pw, f6);
                pw.a(i, this);
                c2543rl0A2 = h8.a(i, f6);
            }
        }
        if (arrayList != null) {
            h7 = h8;
            c2543rl0 = c2543rl0A2;
            h7 = h8;
            c2543rl0 = pw;
            for (C1405eW c1405eW : arrayList) {
                ((H5) c1405eW.a()).a(i, c2543rl0, (F5) c1405eW.b());
            }
        }
        h7 = h8;
        c2543rl0 = c2543rl0A2;
        h7 = h8;
        c2543rl0 = pw;
        h7.a(i, c2543rl0, f6);
        return c2543rl0;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final C2543rl0 a(int i, Gl0 gl0) {
        if (!this.o.M().i0) {
            if (!C && !gl0.b()) {
                x1f.a();
                return null;
            }
            AbstractC2624sj0 abstractC2624sj0M = gl0.a() ? AbstractC2624sj0.m() : gl0.d();
            if (this.x == null) {
                this.x = new C2986wz();
            }
            List<C2543rl0> arrayList = (List) this.x.get(i);
            if (arrayList != null) {
                for (C2543rl0 c2543rl0 : arrayList) {
                    if (c2543rl0.t() == abstractC2624sj0M) {
                        return c2543rl0;
                    }
                }
            } else {
                arrayList = new ArrayList(2);
                this.x.a(i, arrayList);
            }
            C2543rl0 c2543rl1 = new C2543rl0(this.k.a(), abstractC2624sj0M, null);
            arrayList.add(c2543rl1);
            return c2543rl1;
        }
        throw new NB("Information in locals-table is invalid. Local refers to uninitialized register: " + i + " with constraint " + gl0 + ".");
    }

    public final void a(US us, int i, int i2, int i3) {
        C1029a2 c1029a2A = C1029a2.a(us, a(i, 1, AbstractC2005lY.a(us)), b(i2, Gl0.a(us)), b(i3, Gl0.a(us)));
        if (C || !c1029a2A.g()) {
            a(this.t.f(), c1029a2A);
        } else {
            x1f.a();
        }
    }

    public final void a(US us, EnumC0430Dd enumC0430Dd, int i, int i2, int i3) {
        C0456Ed c0456Ed = new C0456Ed(us, enumC0430Dd, a(i, 1, AbstractC2624sj0.k()), b(i2, Gl0.a(us)), b(i3, Gl0.a(us)));
        if (C || !c0456Ed.g()) {
            a(c0456Ed);
        } else {
            x1f.a();
        }
    }

    public final void a(US us, US us2, int i, int i2) {
        C3026xS c3026xS = new C3026xS(us2, us, a(i, 1, AbstractC2005lY.a(us)), b(i2, Gl0.a(us2)));
        if (C || !c3026xS.g()) {
            a(this.t.f(), c3026xS);
        } else {
            x1f.a();
        }
    }

    public final void a(US us, int i, int i2) {
        C2938wQ c2938wQ = new C2938wQ(us, a(i, 1, AbstractC2005lY.a(us)), b(i2, Gl0.a(us)));
        if (C || !c2938wQ.g()) {
            a(this.t.f(), c2938wQ);
        } else {
            x1f.a();
        }
    }

    public final C2543rl0 a(long j) {
        C1678hg c1678hg = new C1678hg(new C2543rl0(this.k.a(), AbstractC2624sj0.k(), null), j);
        a(c1678hg);
        return c1678hg.c();
    }

    public final C2543rl0 a(int i, AbstractC2624sj0 abstractC2624sj0, int i2, C0230j0 c0230j0) {
        C2543rl0 c2543rl0 = new C2543rl0(this.k.a(), abstractC2624sj0, c0230j0);
        e(i);
        this.i.a(i, c2543rl0, i2);
        return c2543rl0;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0046 A[PHI: r1
      0x0046: PHI (r1v1 com.android.tools.r8.internal.rl0) = 
      (r1v0 com.android.tools.r8.internal.rl0)
      (r1v0 com.android.tools.r8.internal.rl0)
      (r1v3 com.android.tools.r8.internal.rl0)
     binds: [B:10:0x001d, B:11:0x001f, B:21:0x0044] A[DONT_GENERATE, DONT_INLINE]] */
    public final C2543rl0 a(int i, int i2, AbstractC2624sj0 abstractC2624sj0) {
        C0602Jt c0602Jt;
        int i3;
        C2543rl0 c2543rl0A = null;
        C0230j0 c0230j0C = g() ? this.t.c(i) : null;
        C0230j0 c0230j0A = g() ? this.t.a(i) : null;
        if (c0230j0C == null || c0230j0C != c0230j0A) {
            c0602Jt = this;
            i3 = i;
        } else {
            if (!C && !g()) {
                x1f.a();
                return null;
            }
            Gl0 gl0A = Gl0.a(c0230j0C.c);
            H5 h5 = this.i;
            F5 f5 = F5.b;
            PW.a aVar = PW.a.c;
            e(i);
            c2543rl0A = h5.a(i, f5);
            if (c2543rl0A != null) {
                c0602Jt = this;
                i3 = i;
            } else {
                c0602Jt = this;
                i3 = i;
                c2543rl0A = c0602Jt.a(i3, h5, f5, gl0A, aVar);
            }
        }
        c0602Jt.v = c2543rl0A;
        return c0602Jt.a(i3, abstractC2624sj0, i2, c0230j0A);
    }

    public final void a(int i, AbstractC2624sj0 abstractC2624sj0) {
        C1201c3 c1201c3 = new C1201c3(a(i, abstractC2624sj0, 1, g() ? this.t.a(i) : null), this.i.f.size(), false);
        if (this.s == null) {
            this.s = new ArrayList();
        }
        a(this.t.f(), c1201c3);
        this.s.add(c1201c3.c());
    }

    public final void a(AbstractC2004lX abstractC2004lX, AbstractC0890Uw abstractC0890Uw) {
        boolean z = C;
        if (!z) {
            if (!z && abstractC0890Uw.c() != null && !abstractC0890Uw.n1() && !abstractC0890Uw.a(this.o).equals(abstractC0890Uw.a())) {
                x1f.a();
                return;
            }
            if (!z && abstractC0890Uw.c() != null && abstractC0890Uw.n1() && !abstractC0890Uw.a(this.o).equals(abstractC0890Uw.a())) {
                AbstractC2624sj0 abstractC2624sj0A = abstractC0890Uw.a();
                abstractC2624sj0A.getClass();
                if (!(abstractC2624sj0A instanceof C1720i7) || !abstractC0890Uw.a(this.o).I()) {
                    x1f.a();
                    return;
                }
            }
        }
        this.z |= (abstractC0890Uw.c() == null || abstractC0890Uw.a().G()) ? false : true;
        abstractC0890Uw.b(abstractC2004lX);
        b(abstractC0890Uw);
        this.i.a(abstractC0890Uw, this.B);
        if (abstractC0890Uw.g()) {
            if (!z && !this.t.c()) {
                x1f.a();
                return;
            }
            C2490r8 c2490r8B = this.t.b(this);
            if (c2490r8B != null) {
                if (!z && this.u) {
                    x1f.a();
                    return;
                }
                this.u = true;
                final ArrayList<H5> arrayList = new ArrayList(c2490r8B.c.size());
                final Set setC = AbstractC2780ub0.c();
                c2490r8B.a(new BiConsumer() { // from class: vu7
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        this.a.a(arrayList, setC, (I2) obj, (Integer) obj2);
                    }
                });
                H5 h5 = this.i;
                AbstractC0551Hu abstractC0551Hu = c2490r8B.b;
                h5.getClass();
                ArrayList arrayList2 = new ArrayList(arrayList.size());
                for (H5 h6 : arrayList) {
                    int iIndexOf = h5.b.indexOf(h6);
                    if (iIndexOf < 0) {
                        iIndexOf = h5.b.size();
                        h5.g(h6);
                    }
                    arrayList2.add(Integer.valueOf(iIndexOf));
                }
                h5.e = new C2490r8(abstractC0551Hu, arrayList2);
            }
        }
    }

    public final void a(List list, Set set, com.android.tools.r8.graph.I2 i2, Integer num) {
        H5 h5 = new H5();
        h5.m++;
        h5.l++;
        this.f.add(new C0498Ft(h5, i2, this.j, num.intValue()));
        list.add(h5);
        H5 h6 = ((C0472Et) this.a.get(num.intValue())).a;
        if (set.add(h6)) {
            return;
        }
        h6.m++;
        h6.l++;
    }

    public final void a(int i, int i2, boolean z) {
        boolean zContains;
        if (i2 >= 0) {
            int iG = this.t.g(i2);
            boolean[] zArr = this.d;
            if (iG < zArr.length) {
                zContains = zArr[iG];
            } else {
                if (this.e == null) {
                    this.e = new HashSet();
                }
                zContains = this.e.contains(Integer.valueOf(iG));
            }
            if (!zContains) {
                this.c.add(Integer.valueOf(i2));
            }
        }
        C0472Et c0472EtF = f(i2);
        if (!this.a.a(i)) {
            i = ((C0866Ty) this.a.c(i)).d();
        }
        C0472Et c0472Et = (C0472Et) this.a.get(i);
        if (z) {
            c0472Et.c.add(i2);
            c0472EtF.b.add(i);
        } else {
            c0472Et.e.add(i2);
            c0472EtF.d.add(i);
        }
        H5 h5 = c0472EtF.a;
        h5.m++;
        h5.l++;
    }

    public final void a(AbstractC2925wD abstractC2925wD) {
        boolean z = C;
        if (!z && abstractC2925wD.g()) {
            x1f.a();
            return;
        }
        if (!z && this.i == null) {
            x1f.a();
            return;
        }
        if (!z && !this.i.k().isEmpty() && this.i.k().getLast().d2()) {
            x1f.a();
            return;
        }
        if (!z && this.i == null) {
            x1f.a();
            return;
        }
        if (!z && !this.i.f.isEmpty() && this.i.k().getLast().d2()) {
            x1f.a();
            return;
        }
        C0472Et c0472Et = (C0472Et) this.a.get(this.b.b(this.i));
        AbstractC2004lX abstractC2004lXF = this.t.f();
        if (c0472Et.c.size() <= 1 && (c0472Et.c.size() != 1 || c0472Et.e.isEmpty())) {
            if (c0472Et.c.size() == 1) {
                this.t.a(this, this.j, c0472Et.c.iterator().q(), false);
            } else if (!z) {
                C0997Yz c0997Yz = new C0997Yz(c0472Et.e.c + c0472Et.c.size());
                c0997Yz.a(c0472Et.c);
                c0997Yz.a(c0472Et.e);
                if (!c0997Yz.isEmpty()) {
                    x1f.a();
                    return;
                }
            }
        } else {
            InterfaceC1640hA it = c0472Et.c.iterator();
            while (it.hasNext()) {
                int iIntValue = ((Integer) it.next()).intValue();
                C0472Et c0472Et2 = (C0472Et) this.a.get(iIntValue);
                if (c0472Et2.b.c + c0472Et2.d.c == 1) {
                    C0576It c0576It = null;
                    for (C0576It c0576It2 : this.f) {
                        if (c0576It2.a == c0472Et2.a) {
                            c0576It = c0576It2;
                        }
                    }
                    if (!C && c0576It.b != this.t.g(iIntValue)) {
                        x1f.a();
                        return;
                    } else {
                        this.f.remove(c0576It);
                        C0576It c0576It3 = c0576It;
                        this.f.add(new C0524Gt(c0576It3.b, c0576It3.a, abstractC2004lXF, this.j, iIntValue));
                    }
                } else {
                    H5 h5 = this.i;
                    H5 h6 = c0472Et2.a;
                    H5 h7 = new H5();
                    h7.m++;
                    h7.l++;
                    h7.l().add(h5);
                    h7.m().add(h6);
                    h5.b(h6, h7);
                    h6.a(h5, h7);
                    this.f.add(new C0524Gt(-1, h7, abstractC2004lXF, this.j, iIntValue));
                }
            }
        }
        a(this.t.f(), abstractC2925wD);
        c();
    }

    public static boolean a(US us) {
        return (us == US.g || us == US.h) ? false : true;
    }
}
