package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.C0013a0;
import com.android.tools.r8.dex.code.C0038f0;
import com.android.tools.r8.dex.code.C0043g0;
import com.android.tools.r8.dex.code.C0048h0;
import com.android.tools.r8.dex.code.C0053i0;
import com.android.tools.r8.graph.C0333y;
import defpackage.hkh;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hg, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1678hg extends AbstractC1252cg {
    public static final /* synthetic */ boolean k = true;
    public final long j;

    public C1678hg(C2543rl0 c2543rl0, long j) {
        super(c2543rl0);
        if (!k) {
            c2543rl0.getClass();
            if (!(c2543rl0 instanceof C1438eq) && !c2543rl0.c.z1()) {
                x1f.a();
                throw null;
            }
        }
        this.j = j;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C1678hg F() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int F2() {
        if (k) {
            return 0;
        }
        x01.a("Const has no register arguments.");
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int G2() {
        return 255;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 15;
    }

    public final boolean K2() {
        return !U2();
    }

    public final double L2() {
        if (k || I2() == El0.f) {
            return Double.longBitsToDouble(this.j);
        }
        x1f.a();
        return 0.0d;
    }

    public final float M2() {
        if (k || I2() == El0.d) {
            return Float.intBitsToFloat((int) this.j);
        }
        x1f.a();
        return 0.0f;
    }

    public final int N2() {
        if (k || I2() == El0.c || I2() == El0.b) {
            return (int) this.j;
        }
        x1f.a();
        return 0;
    }

    public final long O2() {
        if (k || I2() == El0.e) {
            return this.j;
        }
        x1f.a();
        return 0L;
    }

    public long P2() {
        return this.j;
    }

    public final boolean Q2() {
        return QS.a(this.j);
    }

    public final boolean R2() {
        long j = this.j;
        return -128 <= j && j <= 127;
    }

    public final boolean S2() {
        return I2() == El0.c && N2() == 1;
    }

    public final boolean T2() {
        return I2() == El0.c && N2() == 0;
    }

    public final boolean U2() {
        return this.j == 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C2884vl c2884vl) {
        if (!this.b.T()) {
            c2884vl.getClass();
            c2884vl.a(this, new C2029ll(this));
            return;
        }
        int iA = c2884vl.d.a(this.b, this.e);
        if (!I2().a()) {
            El0 el0I2 = I2();
            el0I2.getClass();
            if (el0I2 != El0.c && el0I2 != El0.d) {
                if (!k && !I2().b()) {
                    x1f.a();
                    return;
                }
                if (QS.a(this.j)) {
                    c2884vl.a(this, new C0038f0(iA, (int) this.j));
                    return;
                }
                long j = this.j;
                if ((281474976710655L & j) == 0) {
                    c2884vl.a(this, new C0053i0(iA, (int) (this.j >>> 48)));
                    return;
                } else if (QS.b(j)) {
                    c2884vl.a(this, new C0043g0(iA, (int) this.j));
                    return;
                } else {
                    c2884vl.a(this, new C0048h0(iA, this.j));
                    return;
                }
            }
        }
        if (!k && !QS.b(this.j)) {
            x1f.a();
            return;
        }
        if ((iA & 15) == iA) {
            long j2 = this.j;
            if (-8 <= j2 && j2 <= 7) {
                c2884vl.a(this, new com.android.tools.r8.dex.code.X(iA, (int) this.j));
                return;
            }
        }
        if (QS.a(this.j)) {
            c2884vl.a(this, new com.android.tools.r8.dex.code.W(iA, (int) this.j));
        } else if ((this.j & 65535) == 0) {
            c2884vl.a(this, new C0013a0(iA, ((int) this.j) >>> 16));
        } else {
            c2884vl.a(this, new com.android.tools.r8.dex.code.Y(iA, (int) this.j));
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        if (abstractC0890Uw == this) {
            return true;
        }
        if (!abstractC0890Uw.z1()) {
            return false;
        }
        C1678hg c1678hgF = abstractC0890Uw.F();
        return c1678hgF.I2() == I2() && c1678hgF.j == this.j;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean g1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean r2() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final String toString() {
        if (this.b == null) {
            return super.toString() + " " + this.j + " (dead)";
        }
        return super.toString() + " " + this.j + " (" + a() + ")";
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean z1() {
        return true;
    }

    public static C1678hg a(C2543rl0 c2543rl0, C1678hg c1678hg) {
        if (k || c2543rl0 != c1678hg.c()) {
            return new C1678hg(c2543rl0, c1678hg.P2());
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public Object a(C0941Wv c0941Wv) {
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(K5 k5, IL il) {
        il.b(this, k5);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(J8 j8) {
        if (I2().a()) {
            j8.a(new Z8(), this);
        } else {
            j8.a(new C1129b9(this.j, I2()), this);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C1678hg a(long j) {
        if (this.j == j) {
            return this;
        }
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.graph.I2 a(C0333y c0333y, Nj0 nj0) {
        if (k || I2().a()) {
            c0333y.a();
            return com.android.tools.r8.graph.B1.e6;
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final AbstractC2624sj0 a(C0333y c0333y) {
        return a();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, Ol0 ol0) {
        if (k || !U2() || a().H()) {
            return;
        }
        AbstractC2624sj0 abstractC2624sj0A = a();
        abstractC2624sj0A.getClass();
        if (abstractC2624sj0A instanceof C1034a40) {
            return;
        }
        x1f.a();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(Set set) {
        long j = this.j;
        return j == 0 || j == 1;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final B1 a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1) {
        if (this.b.y()) {
            return Ak0.a;
        }
        C1 c1 = c0333y.t;
        if (a().I()) {
            AbstractC2624sj0 abstractC2624sj0A = a();
            c1.getClass();
            if (C1.f || abstractC2624sj0A.I()) {
                return C2440qc0.b;
            }
            x1f.a();
            return null;
        }
        return c1.a(this.j, a());
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(LK lk) {
        El0 el0I2 = I2();
        long j = this.j;
        lk.getClass();
        int i = EK.a[el0I2.ordinal()];
        if (i == 1) {
            lk.a();
            return;
        }
        if (i == 2) {
            lk.c((int) j);
            return;
        }
        if (i == 3) {
            lk.b((int) j);
            return;
        }
        if (i == 4) {
            lk.b(j);
        } else if (i == 5) {
            lk.a(j);
        } else {
            hkh.a();
        }
    }
}
