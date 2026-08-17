package com.android.tools.r8.internal;

import com.android.tools.r8.ClassFileConsumer;
import com.android.tools.r8.internal.C2543rl0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class WV {
    public static final /* synthetic */ boolean o = true;
    public final com.android.tools.r8.graph.B5 a;
    public final List b;
    public int c;
    public int d;
    public int e;
    public ArrayList f;
    public ArrayList g;
    public ArrayList h;
    public int i;
    public com.android.tools.r8.graph.I2 j;
    public C2543rl0 k;
    public int l;
    public int m = -1;
    public final /* synthetic */ XV n;

    public WV(XV xv, com.android.tools.r8.graph.B5 b5, List list) {
        this.n = xv;
        this.a = b5;
        this.b = list;
        a(0);
    }

    /* JADX WARN: Code duplicated, block: B:110:0x020d  */
    /* JADX WARN: Code duplicated, block: B:112:0x0225  */
    /* JADX WARN: Code duplicated, block: B:113:0x0230  */
    /* JADX WARN: Code duplicated, block: B:114:0x0235  */
    /* JADX WARN: Code duplicated, block: B:116:0x0251  */
    /* JADX WARN: Code duplicated, block: B:117:0x025c  */
    /* JADX WARN: Code duplicated, block: B:134:0x0281 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:140:0x027a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:141:0x0270 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:142:0x0036 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:143:0x028c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:144:0x0287 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:152:0x00c4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:153:0x00d6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:155:0x00b0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:156:0x00b0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:16:0x0040  */
    /* JADX WARN: Code duplicated, block: B:27:0x006f  */
    /* JADX WARN: Code duplicated, block: B:28:0x007b  */
    /* JADX WARN: Code duplicated, block: B:37:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:40:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:52:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:61:0x0106  */
    /* JADX WARN: Code duplicated, block: B:62:0x0112  */
    /* JADX WARN: Code duplicated, block: B:63:0x0118  */
    /* JADX WARN: Code duplicated, block: B:76:0x0139 A[LOOP:2: B:76:0x0139->B:96:0x01ea, LOOP_START, PHI: r2
      0x0139: PHI (r2v5 int) = (r2v0 int), (r2v6 int) binds: [B:75:0x0137, B:96:0x01ea] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:78:0x013f  */
    /* JADX WARN: Code duplicated, block: B:80:0x014b  */
    /* JADX WARN: Code duplicated, block: B:81:0x0156  */
    /* JADX WARN: Code duplicated, block: B:91:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:93:0x01b1 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:95:0x01bd  */
    public final void a() {
        boolean zA;
        int i;
        ArrayList arrayList;
        C2543rl0 c2543rl0;
        C2543rl0 c2543rl0C;
        com.android.tools.r8.graph.I2 i2A;
        int iU;
        C2543rl0 c2543rl0C2;
        com.android.tools.r8.graph.I2 i2;
        int iU2;
        C2543rl0 c2543rl0H;
        C2543rl0 c2543rl0C3;
        com.android.tools.r8.graph.I2 i3;
        int iU3;
        Iterator it;
        int i4;
        C3165z4 c3165z4X;
        int iU4;
        int i5;
        int i6;
        while (this.d < this.b.size()) {
            AbstractC0890Uw abstractC0890Uw = (AbstractC0890Uw) this.b.get(this.d);
            if (abstractC0890Uw.w1()) {
                int i7 = this.d;
                if (i7 == this.c) {
                    a(i7 + 1);
                }
            } else {
                if (!(abstractC0890Uw instanceof C3165z4)) {
                    zA = a(abstractC0890Uw);
                    i = 1;
                }
                if (zA) {
                    this.e += i;
                    if (abstractC0890Uw instanceof C3165z4) {
                        c3165z4X = abstractC0890Uw.x();
                        if (this.k != null && c3165z4X.L2().h() == this.k) {
                            iU4 = this.l + (c3165z4X.c().U() - 1);
                            this.l = iU4;
                            if (o && iU4 < 0) {
                                x1f.a();
                                return;
                            } else if (iU4 == 0) {
                                this.k = null;
                                this.j = this.n.e.E1;
                            }
                        }
                    } else {
                        C2543rl0 c2543rl1 = this.k;
                        arrayList = abstractC0890Uw.c;
                        if (abstractC0890Uw.t1() && abstractC0890Uw.y().L2() && arrayList.get(1) == c2543rl1) {
                            C2543rl0 c2543rl2 = (C2543rl0) arrayList.get(0);
                            arrayList.set(0, (C2543rl0) arrayList.get(1));
                            arrayList.set(1, c2543rl2);
                        }
                        c2543rl0 = this.k;
                        if (c2543rl0 != null) {
                            it = arrayList.iterator();
                            while (it.hasNext()) {
                                if (((C2543rl0) it.next()).h() == this.k) {
                                    i4 = this.l - 1;
                                    this.l = i4;
                                    if (o && i4 < 0) {
                                        x1f.a();
                                        return;
                                    } else if (i4 == 0) {
                                        this.k = null;
                                        this.j = this.n.e.E1;
                                    }
                                }
                            }
                        }
                        if (abstractC0890Uw.o2()) {
                            if (o && this.k != null) {
                                x1f.a();
                                return;
                            }
                            c2543rl0C3 = abstractC0890Uw.c();
                            i3 = abstractC0890Uw.t0().i;
                            iU3 = c2543rl0C3.U();
                            this.l = iU3;
                            if (iU3 == 0) {
                                this.k = null;
                                this.j = this.n.e.E1;
                            } else {
                                this.k = c2543rl0C3;
                                this.j = i3;
                            }
                        } else {
                            if (o && !abstractC0890Uw.S1() && !abstractC0890Uw.w1() && !abstractC0890Uw.l1()) {
                                x1f.a();
                                return;
                            }
                            if (arrayList.size() > 0) {
                                for (int i8 = 0; i8 < arrayList.size(); i8++) {
                                    c2543rl0H = ((C2543rl0) arrayList.get(i8)).h();
                                    if (c2543rl0H == c2543rl0) {
                                        this.h.add(-1);
                                    } else if (!abstractC0890Uw.X1() || (abstractC0890Uw instanceof C1301dC)) {
                                        int iIndexOf = this.f.indexOf(c2543rl0H);
                                        if (i8 == 0 || iIndexOf == -1) {
                                            this.f.add(c2543rl0H);
                                            this.i = c2543rl0H.o.O() + this.i;
                                            this.g.add(a(c2543rl0H, abstractC0890Uw.b0(), i8));
                                            this.h.add(Integer.valueOf(this.g.size() - 1));
                                        } else {
                                            this.h.add(Integer.valueOf(iIndexOf));
                                        }
                                    } else {
                                        this.f.add(c2543rl0H);
                                        this.i = c2543rl0H.o.O() + this.i;
                                        boolean zW1 = abstractC0890Uw.W1();
                                        ArrayList arrayList2 = this.g;
                                        if (zW1) {
                                            arrayList2.add(a(c2543rl0H, abstractC0890Uw.b0(), i8));
                                        } else {
                                            arrayList2.add(abstractC0890Uw.y().K2().a(this.n.e));
                                        }
                                        this.h.add(Integer.valueOf(this.g.size() - 1));
                                    }
                                }
                            }
                            if (!abstractC0890Uw.w1() && abstractC0890Uw.c() != null) {
                                if (o && this.k != null) {
                                    x1f.a();
                                    return;
                                }
                                if (abstractC0890Uw.W1()) {
                                    c2543rl0C2 = abstractC0890Uw.c();
                                    i2 = abstractC0890Uw.b0().U2().i.e;
                                    iU2 = c2543rl0C2.U();
                                    this.l = iU2;
                                    if (iU2 == 0) {
                                        this.k = null;
                                        this.j = this.n.e.E1;
                                    } else {
                                        this.k = c2543rl0C2;
                                        this.j = i2;
                                    }
                                } else {
                                    c2543rl0C = abstractC0890Uw.c();
                                    i2A = abstractC0890Uw.y().K2().a(this.n.e);
                                    iU = c2543rl0C.U();
                                    this.l = iU;
                                    if (iU == 0) {
                                        this.k = null;
                                        this.j = this.n.e.E1;
                                    } else {
                                        this.k = c2543rl0C;
                                        this.j = i2A;
                                    }
                                }
                            }
                        }
                    }
                    if (this.e >= this.n.d.M().Z.c) {
                        a(this.c, this.d + 1);
                    } else {
                        this.d++;
                    }
                } else {
                    i5 = this.d;
                    i6 = this.c;
                    if (i5 > i6) {
                        a(i6, i5);
                    } else {
                        a(i5 + 1);
                    }
                }
            }
            i = 0;
            zA = true;
            if (zA) {
                this.e += i;
                if (abstractC0890Uw instanceof C3165z4) {
                    c3165z4X = abstractC0890Uw.x();
                    if (this.k != null) {
                        iU4 = this.l + (c3165z4X.c().U() - 1);
                        this.l = iU4;
                        if (o) {
                        }
                        if (iU4 == 0) {
                            this.k = null;
                            this.j = this.n.e.E1;
                        }
                    }
                } else {
                    C2543rl0 c2543rl3 = this.k;
                    arrayList = abstractC0890Uw.c;
                    if (abstractC0890Uw.t1()) {
                        C2543rl0 c2543rl4 = (C2543rl0) arrayList.get(0);
                        arrayList.set(0, (C2543rl0) arrayList.get(1));
                        arrayList.set(1, c2543rl4);
                    }
                    c2543rl0 = this.k;
                    if (c2543rl0 != null) {
                        it = arrayList.iterator();
                        while (it.hasNext()) {
                            if (((C2543rl0) it.next()).h() == this.k) {
                                i4 = this.l - 1;
                                this.l = i4;
                                if (o) {
                                }
                                if (i4 == 0) {
                                    this.k = null;
                                    this.j = this.n.e.E1;
                                }
                            }
                        }
                    }
                    if (abstractC0890Uw.o2()) {
                        if (o) {
                        }
                        c2543rl0C3 = abstractC0890Uw.c();
                        i3 = abstractC0890Uw.t0().i;
                        iU3 = c2543rl0C3.U();
                        this.l = iU3;
                        if (iU3 == 0) {
                            this.k = null;
                            this.j = this.n.e.E1;
                        } else {
                            this.k = c2543rl0C3;
                            this.j = i3;
                        }
                    } else {
                        if (o) {
                        }
                        if (arrayList.size() > 0) {
                            while (i8 < arrayList.size()) {
                                c2543rl0H = ((C2543rl0) arrayList.get(i8)).h();
                                if (c2543rl0H == c2543rl0) {
                                    this.h.add(-1);
                                } else if (abstractC0890Uw.X1()) {
                                    int iIndexOf2 = this.f.indexOf(c2543rl0H);
                                    if (i8 == 0) {
                                        this.f.add(c2543rl0H);
                                        this.i = c2543rl0H.o.O() + this.i;
                                        this.g.add(a(c2543rl0H, abstractC0890Uw.b0(), i8));
                                        this.h.add(Integer.valueOf(this.g.size() - 1));
                                    } else {
                                        this.f.add(c2543rl0H);
                                        this.i = c2543rl0H.o.O() + this.i;
                                        this.g.add(a(c2543rl0H, abstractC0890Uw.b0(), i8));
                                        this.h.add(Integer.valueOf(this.g.size() - 1));
                                    }
                                } else {
                                    int iIndexOf3 = this.f.indexOf(c2543rl0H);
                                    if (i8 == 0) {
                                        this.f.add(c2543rl0H);
                                        this.i = c2543rl0H.o.O() + this.i;
                                        this.g.add(a(c2543rl0H, abstractC0890Uw.b0(), i8));
                                        this.h.add(Integer.valueOf(this.g.size() - 1));
                                    } else {
                                        this.f.add(c2543rl0H);
                                        this.i = c2543rl0H.o.O() + this.i;
                                        this.g.add(a(c2543rl0H, abstractC0890Uw.b0(), i8));
                                        this.h.add(Integer.valueOf(this.g.size() - 1));
                                    }
                                }
                            }
                        }
                        if (!abstractC0890Uw.w1()) {
                            if (o) {
                            }
                            if (abstractC0890Uw.W1()) {
                                c2543rl0C2 = abstractC0890Uw.c();
                                i2 = abstractC0890Uw.b0().U2().i.e;
                                iU2 = c2543rl0C2.U();
                                this.l = iU2;
                                if (iU2 == 0) {
                                    this.k = null;
                                    this.j = this.n.e.E1;
                                } else {
                                    this.k = c2543rl0C2;
                                    this.j = i2;
                                }
                            } else {
                                c2543rl0C = abstractC0890Uw.c();
                                i2A = abstractC0890Uw.y().K2().a(this.n.e);
                                iU = c2543rl0C.U();
                                this.l = iU;
                                if (iU == 0) {
                                    this.k = null;
                                    this.j = this.n.e.E1;
                                } else {
                                    this.k = c2543rl0C;
                                    this.j = i2A;
                                }
                            }
                        }
                    }
                }
                if (this.e >= this.n.d.M().Z.c) {
                    a(this.c, this.d + 1);
                } else {
                    this.d++;
                }
            } else {
                i5 = this.d;
                i6 = this.c;
                if (i5 > i6) {
                    a(i6, i5);
                } else {
                    a(i5 + 1);
                }
            }
        }
        if (this.e > 0) {
            a(this.c, this.d);
        }
    }

    public abstract void a(int i, int i2, OV ov);

    public final boolean b(C2543rl0 c2543rl0) {
        if (!c2543rl0.t().r()) {
            return true;
        }
        if (this.n.d.M().u1.Q0 && (this.n.d.M().j instanceof ClassFileConsumer)) {
            return true;
        }
        AbstractC2624sj0 abstractC2624sj0Q = c2543rl0.t().a().Q();
        if (abstractC2624sj0Q.H()) {
            return true;
        }
        if (abstractC2624sj0Q.w()) {
            return abstractC2624sj0Q.b().R().a.isEmpty();
        }
        return false;
    }

    public final boolean a(AbstractC0890Uw abstractC0890Uw) {
        AbstractC0890Uw abstractC0890Uw2;
        int i = this.l;
        if (this.k != null && AbstractC3179zC.b(abstractC0890Uw.c, new EX() { // from class: kff
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return this.b.a((C2543rl0) obj);
            }
        })) {
            i--;
        }
        if (abstractC0890Uw.c() != null && i > 0) {
            return false;
        }
        if (abstractC0890Uw.o2()) {
            if (abstractC0890Uw.c().O()) {
                this.m = this.d;
            }
            return true;
        }
        if (abstractC0890Uw.l1()) {
            return true;
        }
        if (!abstractC0890Uw.W1()) {
            return false;
        }
        AbstractC1047aC abstractC1047aCB0 = abstractC0890Uw.b0();
        boolean zB = this.n.e.b(abstractC1047aCB0.U2());
        if (abstractC1047aCB0.a(this.n.f, this.a) != com.android.tools.r8.ir.optimize.N.d) {
            return false;
        }
        int i2 = this.i;
        if (!abstractC1047aCB0.c.isEmpty()) {
            for (int i3 = 0; i3 < abstractC1047aCB0.c.size(); i3++) {
                C2543rl0 c2543rl0H = abstractC1047aCB0.b(i3).h();
                if (c2543rl0H != this.k) {
                    if (!b(c2543rl0H)) {
                        return false;
                    }
                    if (abstractC1047aCB0.a2() || c2543rl0H != abstractC1047aCB0.c0().V2().h() || !this.f.contains(c2543rl0H)) {
                        int iO = c2543rl0H.o.O();
                        i2 = iO + i2;
                    }
                }
            }
        }
        if (i2 > 5) {
            return false;
        }
        if (zB) {
            int i4 = this.c;
            int i5 = this.d;
            if (i4 == i5) {
                return false;
            }
            if (!o && i5 <= 0) {
                x1f.a();
                return false;
            }
            int i6 = 0;
            do {
                i6++;
                abstractC0890Uw2 = (AbstractC0890Uw) this.b.get(this.d - i6);
            } while (abstractC0890Uw2.w1());
            if (abstractC0890Uw2.o2()) {
                if (abstractC1047aCB0 == AbstractC0783Qt.a(this.n.e, abstractC0890Uw2.t0().c())) {
                    C2543rl0 c2543rl0 = this.k;
                    if (c2543rl0 != null && c2543rl0 == abstractC0890Uw2.c()) {
                        this.m = -1;
                    } else {
                        if (o) {
                            return false;
                        }
                        x1f.a();
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final /* synthetic */ boolean a(C2543rl0 c2543rl0) {
        return c2543rl0.h() == this.k;
    }

    public static com.android.tools.r8.graph.I2 a(AbstractC1047aC abstractC1047aC, int i) {
        com.android.tools.r8.graph.E2 e2;
        int i2 = (abstractC1047aC.X1() || (abstractC1047aC instanceof C1301dC)) ? 1 : 0;
        if (i2 != 0 && i == 0) {
            return abstractC1047aC.U2().f;
        }
        if (abstractC1047aC instanceof C1301dC) {
            e2 = abstractC1047aC.e0().l;
        } else {
            e2 = abstractC1047aC.U2().i;
        }
        return e2.f.b[i - i2];
    }

    public final com.android.tools.r8.graph.I2 a(C2543rl0 c2543rl0, AbstractC1047aC abstractC1047aC, int i) {
        boolean z = o;
        if (!z && !b(c2543rl0)) {
            x1f.a();
            return null;
        }
        com.android.tools.r8.graph.B1 b1 = this.n.d.M().a;
        com.android.tools.r8.graph.I2 i2 = b1.a2;
        AbstractC2624sj0 abstractC2624sj0T = c2543rl0.t();
        if (abstractC2624sj0T.w()) {
            C2441qd c2441qdB = c2543rl0.t().b();
            if (c2441qdB.Q() == i2 && c2441qdB.R().d()) {
                return c2441qdB.R().c();
            }
            return c2441qdB.Q();
        }
        if (abstractC2624sj0T.r()) {
            return c2543rl0.t().a().b(b1);
        }
        if (abstractC2624sj0T instanceof C1034a40) {
            return a(abstractC1047aC, i);
        }
        if (!z && !abstractC2624sj0T.H()) {
            x1f.a();
            return null;
        }
        if (!z) {
            AbstractC2005lY abstractC2005lYC = abstractC2624sj0T.c();
            abstractC2005lYC.getClass();
            if (!(abstractC2005lYC instanceof X6) && !(abstractC2005lYC instanceof V7) && !(abstractC2005lYC instanceof Ab0) && !(abstractC2005lYC instanceof C2864vb) && !(abstractC2005lYC instanceof GA) && !(abstractC2005lYC instanceof C0365Aq) && !(abstractC2005lYC instanceof C2164nM) && !(abstractC2005lYC instanceof C2459qm)) {
                x1f.a();
                return null;
            }
        }
        com.android.tools.r8.graph.I2 i2B = abstractC2624sj0T.c().b(b1);
        if (abstractC2624sj0T instanceof GA) {
            if (z || i2B == b1.B1) {
                return a(abstractC1047aC, i);
            }
            x1f.a();
            return null;
        }
        if (z || i2B == a(abstractC1047aC, i)) {
            return i2B;
        }
        x1f.a();
        return null;
    }

    public final void a(int i, int i2) {
        if (!o && ((AbstractC0890Uw) this.b.get(i)).w1()) {
            x1f.a();
            return;
        }
        int i3 = this.m;
        if (i3 != -1) {
            if (i3 == i) {
                a(i2);
                return;
            } else {
                a(i3);
                return;
            }
        }
        int i4 = i2;
        while (((AbstractC0890Uw) this.b.get(i4 - 1)).w1()) {
            i4--;
        }
        if (this.e < this.n.d.M().Z.b) {
            a(i + 1);
        } else {
            a(i, i4, new OV(this.n, this.b, this.g, this.h, this.j, i, i4));
            a(i2);
        }
    }

    public final void a(int i) {
        this.c = i;
        this.d = i;
        this.e = 0;
        this.f = new ArrayList(5);
        this.g = new ArrayList(5);
        this.h = new ArrayList(5);
        this.i = 0;
        this.j = this.n.e.E1;
        this.k = null;
        this.l = 0;
        this.m = -1;
    }
}
