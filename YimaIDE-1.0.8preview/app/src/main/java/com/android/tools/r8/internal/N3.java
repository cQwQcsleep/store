package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.AbstractC0138z1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.KN;
import defpackage.e63;
import defpackage.pah;
import defpackage.uaa;
import java.util.Arrays;
import java.util.function.Consumer;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class N3 extends D3 {
    public static final /* synthetic */ boolean j = true;
    public KN i;

    public N3(KN kn, C2543rl0 c2543rl0, C2543rl0 c2543rl1, C2543rl0 c2543rl2) {
        super(Arrays.asList(c2543rl0, c2543rl1, c2543rl2), null);
        this.i = kn;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int F2() {
        return 255;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int G2() {
        if (j) {
            return 0;
        }
        x01.a("ArrayPut instructions define no values.");
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 8;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean J2() {
        return true;
    }

    @Override // com.android.tools.r8.internal.D3
    public final D3 O2() {
        return a(KN.f, K2(), (C2543rl0) this.c.get(1), value());
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C2543rl0 V0() {
        return K2();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C2884vl c2884vl) {
        AbstractC0138z1 f;
        int iA = c2884vl.a(value(), W0());
        int iA2 = c2884vl.a(K2(), W0());
        int iA3 = c2884vl.a(M2(), W0());
        switch (this.i.ordinal()) {
            case 0:
                f = new com.android.tools.r8.dex.code.F(iA, iA2, iA3);
                break;
            case 1:
                Q3 q3A = K2().t().a();
                if (q3A != null && q3A.R() == AbstractC2624sj0.e()) {
                    f = new com.android.tools.r8.dex.code.C(iA, iA2, iA3);
                } else {
                    if (!j && !K2().t().y() && q3A.R() != AbstractC2624sj0.g()) {
                        x1f.a();
                        return;
                    }
                    f = new com.android.tools.r8.dex.code.D(iA, iA2, iA3);
                }
                break;
            case 2:
                f = new com.android.tools.r8.dex.code.E(iA, iA2, iA3);
                break;
            case XmlPullParser.END_TAG /* 3 */:
                f = new com.android.tools.r8.dex.code.G(iA, iA2, iA3);
                break;
            case 4:
            case XmlPullParser.CDSECT /* 5 */:
                f = new com.android.tools.r8.dex.code.B(iA, iA2, iA3);
                break;
            case XmlPullParser.ENTITY_REF /* 6 */:
            case 7:
                f = new com.android.tools.r8.dex.code.H(iA, iA2, iA3);
                break;
            case 8:
            case 9:
                pah.a("Unexpected imprecise type: ", this.i);
                return;
            default:
                pah.a("Unexpected type: ", this.i);
                return;
        }
        c2884vl.a(this, f);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        return abstractC0890Uw.p1() && abstractC0890Uw.w().i == this.i;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b1() {
        return true;
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
    public final boolean p1() {
        return true;
    }

    public C2543rl0 value() {
        return (C2543rl0) this.c.get(2);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final N3 w() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1, C0864Tw c0864Tw) {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(AbstractC0890Uw abstractC0890Uw, com.android.tools.r8.ir.regalloc.f fVar, AbstractC2166nO abstractC2166nO) {
        return false;
    }

    public static N3 a(KN kn, C2543rl0 c2543rl0, C2543rl0 c2543rl1, C2543rl0 c2543rl2) {
        N3 n3 = new N3(kn, c2543rl0, c2543rl1, c2543rl2);
        boolean z = j;
        if (!z) {
            if (!z && n3.i == null) {
                x1f.a();
                return null;
            }
            if (!z) {
                n3.K2().a(El0.b);
            }
            if (!z) {
                ((C2543rl0) n3.c.get(1)).a(El0.c);
            }
        }
        return n3;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.ir.optimize.N a(com.android.tools.r8.ir.optimize.W w, com.android.tools.r8.graph.B5 b5) {
        return com.android.tools.r8.ir.optimize.N.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(LK lk) {
        lk.a(this.i, K2(), (C2543rl0) this.c.get(1), value());
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        return null;
    }

    @Override // com.android.tools.r8.internal.D3, com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1, C0864Tw c0864Tw) {
        int size;
        int iN2;
        C2543rl0 c2543rl0H = K2().h();
        if (c2543rl0H.c(new e63()) && !c2543rl0H.y()) {
            AbstractC0890Uw abstractC0890UwP = c2543rl0H.p();
            abstractC0890UwP.getClass();
            if (abstractC0890UwP instanceof FQ) {
                C2543rl0 c2543rl0 = (C2543rl0) abstractC0890UwP.q0().c.get(0);
                if (!c2543rl0.J()) {
                    return true;
                }
                size = c2543rl0.k().F().N2();
            } else {
                size = abstractC0890UwP.r0().c.size();
            }
            C2543rl0 c2543rl0H2 = ((C2543rl0) this.c.get(1)).h();
            if (c2543rl0H2.J() && (iN2 = c2543rl0H2.k().F().N2()) >= 0 && iN2 < size) {
                AbstractC2624sj0 abstractC2624sj0T = c2543rl0H.t();
                AbstractC2624sj0 abstractC2624sj0T2 = value().t();
                if (abstractC2624sj0T.r()) {
                    return !abstractC2624sj0T2.b(abstractC2624sj0T.a().S(), c0333y);
                }
                return true;
            }
        }
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.ir.optimize.E a(C0333y c0333y, C0705Nt c0705Nt) {
        if (!a(c0333y, c0705Nt.i())) {
            C2543rl0 c2543rl0H = K2().h();
            if (c2543rl0H.c(new uaa())) {
                return new com.android.tools.r8.ir.optimize.D(c2543rl0H);
            }
        }
        return com.android.tools.r8.ir.optimize.E.b;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(K5 k5, IL il) {
        il.a(this, k5);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(J8 j8) {
        j8.a(new B8(this.i), this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, C2543rl0 c2543rl0) {
        return K2() == c2543rl0;
    }

    @Override // com.android.tools.r8.internal.InterfaceC3237zv
    public final void a(C2539rj0 c2539rj0) {
        c2539rj0.a(this.i, value(), K2(), new Consumer() { // from class: taa
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
