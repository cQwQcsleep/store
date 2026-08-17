package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.AbstractC0138z1;
import com.android.tools.r8.dex.code.C0062k;
import com.android.tools.r8.dex.code.C0067l;
import com.android.tools.r8.dex.code.C0091p3;
import com.android.tools.r8.dex.code.C0096q3;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ig0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1764ig0 extends C3 {
    public static final /* synthetic */ boolean l = true;

    public C1764ig0(US us, C2543rl0 c2543rl0, C2543rl0 c2543rl1, C2543rl0 c2543rl2) {
        super(us, c2543rl0, c2543rl1, c2543rl2);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 63;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C1764ig0 L0() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final boolean L2() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final int N2() {
        if (!c((C2543rl0) this.c.get(0))) {
            if (l || f((C2543rl0) this.c.get(0))) {
                return ((C2543rl0) this.c.get(0)).k().F().R2() ? 255 : 15;
            }
            x1f.a();
            return 0;
        }
        if (c(P2())) {
            return 255;
        }
        if (l || h(P2())) {
            long j = -P2().k().F().j;
            return (-128 > j || j > 127) ? 15 : 255;
        }
        x1f.a();
        return 0;
    }

    @Override // com.android.tools.r8.internal.C3
    public final C3004x8.a Q2() {
        return C3004x8.a.c;
    }

    @Override // com.android.tools.r8.internal.C3, com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C2884vl c2884vl) {
        AbstractC0138z1 y3;
        if (!a(c2884vl.d)) {
            US us = this.i;
            US us2 = US.e;
            if (us == us2) {
                if (!c((C2543rl0) this.c.get(0))) {
                    boolean z = l;
                    if (!z && !f((C2543rl0) this.c.get(0))) {
                        x1f.a();
                        return;
                    }
                    C1678hg c1678hgF = ((C2543rl0) this.c.get(0)).k().F();
                    int iA = c2884vl.d.a(P2(), this.e);
                    int iA2 = c2884vl.d.a(this.b, this.e);
                    if (c1678hgF.R2()) {
                        y3 = new C0096q3(iA2, iA, c1678hgF.N2());
                    } else {
                        if (!z && !QS.a(c1678hgF.j)) {
                            x1f.a();
                            return;
                        }
                        y3 = new C0091p3(iA2, iA, c1678hgF.N2());
                    }
                } else if (!c(P2())) {
                    boolean z2 = l;
                    if (!z2 && !h(P2())) {
                        x1f.a();
                        return;
                    }
                    int iA3 = c2884vl.d.a(this.b, this.e);
                    if (!z2 && !c((C2543rl0) this.c.get(0))) {
                        x1f.a();
                        return;
                    }
                    int iA4 = c2884vl.d.a((C2543rl0) this.c.get(0), this.e);
                    C1678hg c1678hgF2 = P2().k().F();
                    long j = -c1678hgF2.j;
                    if (-128 <= j && j <= 127) {
                        y3 = new C0067l(iA3, iA4, -c1678hgF2.N2());
                    } else {
                        if (!z2 && (-32768 > j || j > 32767)) {
                            x1f.a();
                            return;
                        }
                        y3 = new C0062k(iA3, iA4, -c1678hgF2.N2());
                    }
                } else {
                    if (!l && this.i != us2) {
                        x1f.a();
                        return;
                    }
                    y3 = new com.android.tools.r8.dex.code.Y3(c2884vl.d.a(this.b, this.e), c2884vl.d.a((C2543rl0) this.c.get(0), this.e), c2884vl.d.a(P2(), this.e));
                }
                c2884vl.a(this, y3);
                return;
            }
        }
        super.a(c2884vl);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        abstractC0890Uw.getClass();
        return (abstractC0890Uw instanceof C1764ig0) && abstractC0890Uw.L0().i == this.i;
    }

    @Override // com.android.tools.r8.internal.C3, com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean c(C2543rl0 c2543rl0) {
        if (((C2543rl0) this.c.get(0)) == P2()) {
            return true;
        }
        if (c2543rl0 == ((C2543rl0) this.c.get(0))) {
            return !f(c2543rl0);
        }
        if (l || c2543rl0 == P2()) {
            return !h(c2543rl0) || f((C2543rl0) this.c.get(0));
        }
        x1f.a();
        return false;
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 d(int i, int i2, int i3) {
        throw new Kk0("Unsupported instruction SubIntLit16");
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 e(int i, int i2, int i3) {
        throw new Kk0("Unsupported instruction SubIntLit8");
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 f(int i, int i2, int i3) {
        return new com.android.tools.r8.dex.code.a4(i, i2, i3);
    }

    public final boolean h(C2543rl0 c2543rl0) {
        if (this.i != US.e || !c2543rl0.J()) {
            return false;
        }
        long j = -c2543rl0.k().F().j;
        return -32768 <= j && j <= 32767;
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 d(int i, int i2) {
        return new com.android.tools.r8.dex.code.X3(i, i2);
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 e(int i, int i2) {
        return new com.android.tools.r8.dex.code.Z3(i, i2);
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 b(int i, int i2) {
        return new com.android.tools.r8.dex.code.T3(i, i2);
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 b(int i, int i2, int i3) {
        return new com.android.tools.r8.dex.code.W3(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 c(int i, int i2, int i3) {
        return new com.android.tools.r8.dex.code.Y3(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 c(int i, int i2) {
        return new com.android.tools.r8.dex.code.V3(i, i2);
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final float a(float f, float f2) {
        return f - f2;
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final int a(int i, int i2) {
        return i - i2;
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final long a(long j, long j2) {
        return j - j2;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        return null;
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 a(int i, int i2, int i3) {
        return new com.android.tools.r8.dex.code.U3(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final double a(double d, double d2) {
        return d - d2;
    }
}
