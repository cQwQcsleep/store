package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.AbstractC0138z1;
import com.android.tools.r8.dex.code.C0082o;
import com.android.tools.r8.dex.code.C0087p;
import com.android.tools.r8.dex.code.C0092q;
import com.android.tools.r8.dex.code.C0101s;
import com.android.tools.r8.dex.code.C0106t;
import com.android.tools.r8.dex.code.C0111u;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.KN;
import defpackage.pah;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Consumer;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class H3 extends D3 {
    public static final /* synthetic */ boolean j = true;
    public KN i;

    public H3(KN kn, C2543rl0 c2543rl0, C2543rl0 c2543rl1, C2543rl0 c2543rl2) {
        super(Arrays.asList(c2543rl1, c2543rl2), c2543rl0);
        this.i = kn;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int F2() {
        return 255;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int G2() {
        return 255;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 6;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean J2() {
        return true;
    }

    @Override // com.android.tools.r8.internal.D3
    public final D3 O2() {
        return new H3(KN.f, c(), K2(), (C2543rl0) this.c.get(1));
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C2543rl0 V0() {
        return K2();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final AbstractC2624sj0 a(C0333y c0333y) {
        Q3 q3A = K2().t().r() ? K2().t().a() : null;
        switch (this.i.ordinal()) {
            case 0:
                AbstractC2624sj0 abstractC2624sj0M = q3A == null ? AbstractC2624sj0.m() : q3A.S();
                if (j || abstractC2624sj0M.I()) {
                    return abstractC2624sj0M;
                }
                x1f.a();
                return null;
            case 1:
            case 2:
            case XmlPullParser.END_TAG /* 3 */:
            case 4:
                if (j || q3A == null || q3A.S().C()) {
                    return AbstractC2624sj0.k();
                }
                x1f.a();
                return null;
            case XmlPullParser.CDSECT /* 5 */:
                if (j || q3A == null || q3A.S().B()) {
                    return AbstractC2624sj0.j();
                }
                x1f.a();
                return null;
            case XmlPullParser.ENTITY_REF /* 6 */:
                if (j || q3A == null || q3A.S().D()) {
                    return AbstractC2624sj0.l();
                }
                x1f.a();
                return null;
            case 7:
                if (j || q3A == null || q3A.S().z()) {
                    return AbstractC2624sj0.i();
                }
                x1f.a();
                return null;
            case 8:
                if (j || q3A == null || q3A.S().K()) {
                    return a(this.b, Gl0.e);
                }
                x1f.a();
                return null;
            case 9:
                if (j || q3A == null || q3A.S().M()) {
                    return a(this.b, Gl0.i);
                }
                x1f.a();
                return null;
            default:
                pah.a("Unexpected member type: ", this.i);
                return null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1, C0864Tw c0864Tw) {
        if (!K2().j() && ((C2543rl0) this.c.get(1)).J()) {
            B1 b1A = K2().h().a(c0333y, b5, J1.a);
            if (!b1A.A()) {
                return true;
            }
            int iX = b1A.x();
            int iN2 = ((C2543rl0) this.c.get(1)).k().F().N2();
            if (iX > 0 && iN2 >= 0 && iX > iN2) {
                return false;
            }
        }
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b1() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean d(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        return false;
    }

    @Override // com.android.tools.r8.internal.InterfaceC3237zv
    public final KN e() {
        return this.i;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean n1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final H3 u() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        return abstractC0890Uw.n1() && abstractC0890Uw.u().i == this.i;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(AbstractC0890Uw abstractC0890Uw, com.android.tools.r8.ir.regalloc.f fVar, AbstractC2166nO abstractC2166nO) {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.ir.optimize.N a(com.android.tools.r8.ir.optimize.W w, com.android.tools.r8.graph.B5 b5) {
        return com.android.tools.r8.ir.optimize.N.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C2884vl c2884vl) {
        AbstractC0138z1 c0101s;
        int iA = c2884vl.a(this.b, W0());
        int iA2 = c2884vl.a(K2(), W0());
        int iA3 = c2884vl.a(M2(), W0());
        switch (this.i.ordinal()) {
            case 0:
                c0101s = new C0101s(iA, iA2, iA3);
                break;
            case 1:
                Q3 q3A = K2().t().a();
                if (q3A != null && q3A.R() == AbstractC2624sj0.e()) {
                    c0101s = new C0087p(iA, iA2, iA3);
                } else {
                    if (!j && !K2().t().y() && q3A.R() != AbstractC2624sj0.g()) {
                        x1f.a();
                        return;
                    }
                    c0101s = new C0092q(iA, iA2, iA3);
                }
                break;
            case 2:
                c0101s = new com.android.tools.r8.dex.code.r(iA, iA2, iA3);
                break;
            case XmlPullParser.END_TAG /* 3 */:
                c0101s = new C0106t(iA, iA2, iA3);
                break;
            case 4:
            case XmlPullParser.CDSECT /* 5 */:
                c0101s = new C0082o(iA, iA2, iA3);
                break;
            case XmlPullParser.ENTITY_REF /* 6 */:
            case 7:
                if (!j && !c2884vl.b().o() && iA == iA2) {
                    x1f.a();
                    return;
                }
                c0101s = new C0111u(iA, iA2, iA3);
                break;
            case 8:
            case 9:
                pah.a("Unexpected imprecise type: ", this.i);
                return;
            default:
                pah.a("Unexpected type ", this.i);
                return;
        }
        c2884vl.a(this, c0101s);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(LK lk) {
        lk.a(this.i, K2(), (C2543rl0) this.c.get(1));
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(C0333y c0333y, C2543rl0 c2543rl0) {
        boolean z = j;
        if (!z && (c2543rl0 == null || !c2543rl0.t().I())) {
            x1f.a();
            return false;
        }
        if (z || this.b != null) {
            return this.b.t().I();
        }
        x1f.a();
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.graph.I2 a(C0333y c0333y, Nj0 nj0) {
        if (j || this.b.t().I()) {
            com.android.tools.r8.graph.I2 i2A = nj0.a(K2());
            return i2A == com.android.tools.r8.graph.B1.e6 ? i2A : i2A.a(1, c0333y.a());
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(K5 k5, IL il) {
        il.a(this, k5);
        il.b(this, k5);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(J8 j8) {
        j8.a(new C3173z8(this.i), this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        return null;
    }

    public static AbstractC2624sj0 a(C2543rl0 c2543rl0, Gl0 gl0) {
        AbstractC2624sj0 abstractC2624sj0A = c2543rl0.a(gl0);
        if (abstractC2624sj0A != null) {
            return abstractC2624sj0A;
        }
        throw new C0613Ke("Failure to constrain value: " + c2543rl0 + " by constraint: " + gl0);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, C2543rl0 c2543rl0) {
        return K2() == c2543rl0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(Set set) {
        return K2().t().r() && K2().t().a().R() == AbstractC2624sj0.e();
    }

    @Override // com.android.tools.r8.internal.InterfaceC3237zv
    public final void a(C2539rj0 c2539rj0) {
        c2539rj0.a(this.i, this.b, K2(), new Consumer() { // from class: i26
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((KN) obj);
            }
        });
    }

    public final /* synthetic */ void a(KN kn) {
        this.i = kn;
    }
}
