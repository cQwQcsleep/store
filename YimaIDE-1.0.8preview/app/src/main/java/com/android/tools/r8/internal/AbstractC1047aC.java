package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.android.tools.r8.graph.AbstractC0184c3;
import com.android.tools.r8.graph.C0214g5;
import com.android.tools.r8.graph.C0229j;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0324w4;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.InterfaceC0331x4;
import com.android.tools.r8.internal.AbstractC1047aC;
import com.android.tools.r8.shaking.C3403i;
import com.sun.jna.platform.linux.Fcntl;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.aC, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1047aC extends SB {
    public static final /* synthetic */ boolean k = true;
    public final C0322w2 j;

    public AbstractC1047aC(C0322w2 c0322w2, C2543rl0 c2543rl0, List list) {
        super(list, c2543rl0);
        this.j = c0322w2;
    }

    @Override // com.android.tools.r8.internal.SB
    public com.android.tools.r8.graph.I2 O2() {
        return this.j.i.e;
    }

    public final C2543rl0 S2() {
        return b(Y6.a(X1()));
    }

    public abstract boolean T2();

    public C0322w2 U2() {
        return this.j;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean W1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final B1 a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1) {
        AbstractC1167bf c1081af = null;
        if (!k && !c1()) {
            x1f.a();
            return null;
        }
        com.android.tools.r8.graph.H0 h0G = g(c0333y, b5);
        if (h0G == null) {
            return Ak0.a;
        }
        C0231j1 c0231j1E = h0G.e();
        c0231j1E.O0();
        B1 b1I = c0231j1E.m.i();
        com.android.tools.r8.graph.I2 i2S = h0G.s();
        com.android.tools.r8.graph.B1 b1A = c0333y.a();
        switch (i2S.z0().e) {
            case Fcntl.S_IWGRP /* 16 */:
                if (com.android.tools.r8.graph.I2.a(i2S, b1A.O1)) {
                    c1081af = new C0898Ve(c0333y);
                } else if (com.android.tools.r8.graph.I2.a(i2S, b1A.T1)) {
                    c1081af = new C1081af(c0333y);
                }
                break;
            case 17:
                if (com.android.tools.r8.graph.I2.a(i2S, b1A.R1)) {
                    c1081af = new C0976Ye(c0333y);
                } else if (com.android.tools.r8.graph.I2.a(i2S, b1A.U1)) {
                    c1081af = new C1250cf(c0333y);
                }
                break;
            case AndroidSdkVersion.JELLY_BEAN_MR2 /* 18 */:
                if (com.android.tools.r8.graph.I2.a(i2S, b1A.Q1)) {
                    c1081af = new C0950Xe(c0333y);
                }
                break;
            case AndroidSdkVersion.KITKAT /* 19 */:
                if (com.android.tools.r8.graph.I2.a(i2S, b1A.N1)) {
                    c1081af = new C0872Ue(c0333y);
                } else if (com.android.tools.r8.graph.I2.a(i2S, b1A.S1)) {
                    c1081af = new C1002Ze(c0333y);
                }
                break;
            case AndroidSdkVersion.LOLLIPOP /* 21 */:
                if (com.android.tools.r8.graph.I2.a(i2S, b1A.P1)) {
                    c1081af = new C0924We(c0333y);
                }
                break;
        }
        if (c1081af != null) {
            B1 b1A2 = c1081af.a(this, h0G, b5, j1);
            if (!b1A2.isUnknown()) {
                return b1A2;
            }
        }
        return b1I;
    }

    public abstract com.android.tools.r8.ir.optimize.O a(com.android.tools.r8.graph.B5 b5, com.android.tools.r8.ir.optimize.G g, C1328dc c1328dc, AbstractC2973wm0 abstractC2973wm0);

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public boolean b(AbstractC0890Uw abstractC0890Uw) {
        return abstractC0890Uw.W1() && this.j == abstractC0890Uw.b0().U2();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final AbstractC1047aC b0() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean d(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public AbstractC2389q e(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        return ZJ.a(c0333y, this);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x007a  */
    public UY f(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        com.android.tools.r8.graph.D2 d2B;
        if (!U2().f.M0()) {
            return null;
        }
        if (!(this instanceof YB) && !c2()) {
            com.android.tools.r8.graph.B5 b5A = com.android.tools.r8.graph.H0.a(g(c0333y, b5));
            if (b5A != null) {
                return UY.a(b5A);
            }
            return null;
        }
        AbstractC1133bC abstractC1133bCC0 = c0();
        com.android.tools.r8.graph.D2 d2B2 = com.android.tools.r8.graph.D2.b(c0333y.d(C2283oj0.a(abstractC1133bCC0.V2().a(c0333y), abstractC1133bCC0.U2(), c0333y)));
        C2543rl0 c2543rl0V2 = c0().V2();
        c2543rl0V2.getClass();
        C2441qd c2441qdA = c2543rl0V2.a(c0333y, (AbstractC2624sj0) null, C2427qS.h());
        if (c2441qdA != null) {
            d2B = com.android.tools.r8.graph.D2.b(c0333y.d(c2441qdA.Q()));
            if (d2B2 != null && d2B != null && !((C3403i) c0333y.g()).c(d2B.e, d2B2.e)) {
                d2B = null;
            }
        } else {
            d2B = null;
        }
        com.android.tools.r8.graph.T4 t4B = ((C3403i) c0333y.g()).b(this.j, T2());
        com.android.tools.r8.graph.B4 b4A = d2B2 != null ? t4B.a(b5.a(), (C0333y<C3403i>) c0333y, d2B2, d2B) : t4B.b(b5.a(), (C0333y<C3403i>) c0333y);
        if (b4A.b()) {
            return null;
        }
        final UY uyC = UY.c();
        b4A.a(new Consumer() { // from class: mbg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AbstractC1047aC.a(uyC, (InterfaceC0331x4) obj);
            }
        }, new Consumer() { // from class: nbg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AbstractC1047aC.a((C0324w4) obj);
            }
        });
        return uyC;
    }

    public final com.android.tools.r8.graph.H0 g(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        com.android.tools.r8.graph.T4 t4H = h(c0333y, b5);
        if (!t4H.w()) {
            return null;
        }
        AbstractC0184c3 abstractC0184c3A = t4H.o().a(c0333y, this, b5);
        if (abstractC0184c3A instanceof com.android.tools.r8.graph.N5) {
            return abstractC0184c3A.a().b;
        }
        return null;
    }

    public final com.android.tools.r8.graph.T4 h(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        com.android.tools.r8.graph.H0 h0B;
        com.android.tools.r8.graph.D2 d2A;
        C0231j1 c0231j1C;
        if (c0333y.g().h()) {
            return b(c0333y.U());
        }
        com.android.tools.r8.graph.I2 i2W0 = this.j.w0();
        com.android.tools.r8.graph.I2 i2S = b5.s();
        i2W0.getClass();
        if (com.android.tools.r8.graph.I2.a(i2W0, i2S) && (c0231j1C = (d2A = b5.a()).c(this.j)) != null) {
            return com.android.tools.r8.graph.T4.a(d2A, d2A, c0231j1C);
        }
        if (!c0333y.C.c.contains(this.j.w0()) || (h0B = c0333y.b(this.j)) == null) {
            boolean z = com.android.tools.r8.graph.T4.a;
            return C0214g5.b;
        }
        com.android.tools.r8.graph.E0 e0A = h0B.a();
        return com.android.tools.r8.graph.T4.a(e0A, e0A, h0B.e());
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final String toString() {
        return super.toString() + "; method: " + this.j.m0();
    }

    public final com.android.tools.r8.graph.T4 b(C0333y c0333y) {
        return ((C0229j) c0333y.g()).a(this.j, T2());
    }

    public static /* synthetic */ void a(C0324w4 c0324w4) {
    }

    public static AbstractC1047aC a(EnumC2326pC enumC2326pC, C0322w2 c0322w2, C2543rl0 c2543rl0, List list, boolean z) {
        int iOrdinal = enumC2326pC.ordinal();
        if (iOrdinal == 0) {
            return new VB(c0322w2, c2543rl0, list, z);
        }
        if (iOrdinal == 1) {
            return new YB(c0322w2, c2543rl0, list);
        }
        if (iOrdinal == 2) {
            return new C1983lC(c0322w2, c2543rl0, list, z);
        }
        if (iOrdinal == 3) {
            return new C2069mC(c0322w2, c2543rl0, list, z);
        }
        if (iOrdinal == 4) {
            if (k || !z) {
                return new C2496rC(c0322w2, c2543rl0, list);
            }
            x1f.a();
            return null;
        }
        defpackage.gk0.a("Unexpected invoke type: ", enumC2326pC);
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(C0322w2 c0322w2) {
        return U2().a(c0322w2);
    }

    public static void a(UY uy, InterfaceC0331x4 interfaceC0331x4) {
        com.android.tools.r8.graph.H0 h0Q = interfaceC0331x4.q();
        h0Q.getClass();
        if (h0Q instanceof com.android.tools.r8.graph.B5) {
            uy.add(h0Q.c0());
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(AbstractC0890Uw abstractC0890Uw, com.android.tools.r8.ir.regalloc.f fVar, AbstractC2166nO abstractC2166nO) {
        if (!super.a(abstractC0890Uw, fVar, abstractC2166nO)) {
            return false;
        }
        fVar.c().getClass();
        AbstractC1047aC abstractC1047aCB0 = abstractC0890Uw.b0();
        for (int i = 0; i < this.c.size(); i++) {
            C2543rl0 c2543rl0 = (C2543rl0) this.c.get(i);
            if (c2543rl0.t().r() && c2543rl0 != abstractC1047aCB0.c.get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(K5 k5, IL il) {
        il.a(this, k5);
        if (O2().W0()) {
            return;
        }
        il.a(O2(), this, k5);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.graph.I2 a(C0333y c0333y, Nj0 nj0) {
        return O2();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public boolean a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, C2543rl0 c2543rl0) {
        com.android.tools.r8.graph.T4.c<?> cVarO;
        if (!c0333y.g().h() || (cVarO = b(c0333y.U()).o()) == null) {
            return false;
        }
        com.android.tools.r8.ir.optimize.info.h hVarA = cVarO.a(c0333y, this, g(c0333y, b5));
        if (hVarA.s() != null) {
            for (int i = 0; i < this.c.size(); i++) {
                if (c2543rl0 == b(i) && hVarA.s().get(i)) {
                    return true;
                }
            }
        }
        return false;
    }
}
