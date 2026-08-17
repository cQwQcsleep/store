package com.android.tools.r8.internal;

import defpackage.gkh;
import defpackage.pah;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.lu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2040lu extends AbstractC2925wD {
    public static final /* synthetic */ boolean k = true;
    public EnumC2211nu j;

    public C2040lu(EnumC2211nu enumC2211nu, C2543rl0 c2543rl0) {
        super(c2543rl0);
        this.j = enumC2211nu;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int F2() {
        return P2() ? 255 : 15;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int G2() {
        if (k) {
            return 0;
        }
        x01.a("If instructions define no values.");
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 25;
    }

    @Override // com.android.tools.r8.internal.AbstractC2925wD
    public H5 K2() {
        boolean z = k;
        if (!z && i().h() != this) {
            x1f.a();
            return null;
        }
        List<H5> listT = i().t();
        if (z || listT.size() >= 2) {
            return listT.get(listT.size() - 1);
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean L1() {
        return true;
    }

    public final H5 L2() {
        boolean z = k;
        if (!z && i().h() != this) {
            x1f.a();
            return null;
        }
        List<H5> listT = i().t();
        if (z || listT.size() >= 2) {
            return listT.get(listT.size() - 2);
        }
        x1f.a();
        return null;
    }

    public final EnumC2211nu M2() {
        return this.j;
    }

    public final void N2() {
        H5 h5L2 = L2();
        H5 h5K2 = K2();
        boolean z = k;
        if (!z && i().h() != this) {
            x1f.a();
            return;
        }
        List<H5> listM = i().m();
        if (!z && listM.size() < 2) {
            x1f.a();
            return;
        }
        listM.set(listM.size() - 2, h5K2);
        b(h5L2);
        this.j = this.j.a();
    }

    public final boolean O2() {
        return P2() && ((C2543rl0) this.c.get(0)).h().c(new gkh());
    }

    public boolean P2() {
        return this.c.size() == 1;
    }

    public final C2543rl0 Q2() {
        return (C2543rl0) this.c.get(0);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C2040lu R() {
        return this;
    }

    public final C2543rl0 R2() {
        if (k || !P2()) {
            return (C2543rl0) this.c.get(1);
        }
        x1f.a();
        return null;
    }

    public final H5 S2() {
        boolean z = k;
        if (!z && !P2()) {
            x1f.a();
            return null;
        }
        if (z || ((C2543rl0) this.c.get(0)).Y().a()) {
            return b(1);
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(J8 j8) {
        El0 el0Y = ((C2543rl0) this.c.get(0)).Y();
        if (this.c.size() == 1) {
            j8.a(new C2492r9(this.j, el0Y, j8.a(L2())), this);
            return;
        }
        boolean z = k;
        if (!z && this.c.size() != 2) {
            x1f.a();
        } else if (z || ((C2543rl0) this.c.get(0)).Y() == ((C2543rl0) this.c.get(1)).Y()) {
            j8.a(new C2663t9(this.j, el0Y, j8.a(L2())), this);
        } else {
            x1f.a();
        }
    }

    public final H5 b(int i) {
        if (!k && Integer.signum(i) != i) {
            x1f.a();
            return null;
        }
        switch (AbstractC1954ku.a[this.j.ordinal()]) {
            case 1:
                return i == 0 ? L2() : K2();
            case 2:
                return i != 0 ? L2() : K2();
            case XmlPullParser.END_TAG /* 3 */:
                return i >= 0 ? L2() : K2();
            case 4:
                return i > 0 ? L2() : K2();
            case XmlPullParser.CDSECT /* 5 */:
                return i <= 0 ? L2() : K2();
            case XmlPullParser.ENTITY_REF /* 6 */:
                return i < 0 ? L2() : K2();
            default:
                pah.a("Unexpected condition type ", this.j);
                return null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(' ');
        sb.append(this.j);
        sb.append(P2() ? 'Z' : ' ');
        if (i().h() == this && i().t().size() >= 2) {
            sb.append(" block ");
            sb.append(L2().p());
            sb.append(" (fallthrough ");
            sb.append(K2().p());
            sb.append(')');
        }
        return sb.toString();
    }

    public C2040lu(EnumC2211nu enumC2211nu, List list) {
        super(list);
        this.j = enumC2211nu;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        if (!abstractC0890Uw.L1()) {
            return false;
        }
        C2040lu c2040luR = abstractC0890Uw.R();
        return c2040luR.L2() == L2() && c2040luR.K2() == K2() && c2040luR.j == this.j;
    }

    public static boolean a(AbstractC2624sj0 abstractC2624sj0, EnumC2211nu enumC2211nu) {
        abstractC2624sj0.getClass();
        if (abstractC2624sj0 instanceof GA) {
            return true;
        }
        if ((abstractC2624sj0 instanceof C0365Aq) && (enumC2211nu == EnumC2211nu.b || enumC2211nu == EnumC2211nu.g)) {
            return true;
        }
        if (abstractC2624sj0.I()) {
            return enumC2211nu == EnumC2211nu.b || enumC2211nu == EnumC2211nu.g;
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC2925wD
    public final void b(H5 h5) {
        List<H5> listM = i().m();
        listM.set(listM.size() - 1, h5);
    }

    public final H5 a(C1678hg c1678hg, C1678hg c1678hg2) {
        boolean z = k;
        if (!z && P2()) {
            x1f.a();
            return null;
        }
        if (!z && c1678hg.I2() != c1678hg2.I2()) {
            x1f.a();
            return null;
        }
        if (!z && !a(c1678hg.a(), this.j)) {
            x1f.a();
            return null;
        }
        long jP2 = c1678hg.P2();
        long jP3 = c1678hg2.P2();
        if (z || !P2()) {
            return b(Long.signum(jP2 - jP3));
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(K5 k5, IL il) {
        il.a(this, k5);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(LK lk) {
        El0 el0Y = ((C2543rl0) this.c.get(0)).Y();
        if (this.c.size() == 1) {
            lk.a(this.j, el0Y, (C2543rl0) this.c.get(0), L2());
            return;
        }
        boolean z = k;
        if (!z && this.c.size() != 2) {
            x1f.a();
        } else if (z || ((C2543rl0) this.c.get(0)).Y() == ((C2543rl0) this.c.get(1)).Y()) {
            lk.a(this.j, el0Y, this.c, L2());
        } else {
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C2884vl c2884vl) {
        if (!C2884vl.r) {
            if (c2884vl.q != K2()) {
                x1f.a();
                return;
            }
        } else {
            c2884vl.getClass();
        }
        c2884vl.a(this, new C2372pl(this));
    }
}
