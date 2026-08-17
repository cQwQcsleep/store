package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.AbstractC0138z1;
import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xb0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3037xb0 extends QL {
    public C3037xb0(US us, C2543rl0 c2543rl0, C2543rl0 c2543rl1, C2543rl0 c2543rl2) {
        super(us, c2543rl0, c2543rl1, c2543rl2);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C3037xb0 F0() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 57;
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final boolean L2() {
        return false;
    }

    @Override // com.android.tools.r8.internal.QL
    public final Q9 Q2() {
        return Q9.b;
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final B1 a(C0333y c0333y, B1 b1, B1 b2) {
        b2.getClass();
        return !(b2 instanceof C2525rc0) ? Ak0.a : AbstractC2046m.a(c0333y, b1, (int) b2.e().b);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        abstractC0890Uw.getClass();
        return (abstractC0890Uw instanceof C3037xb0) && abstractC0890Uw.F0().i == this.i;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C3037xb0 c(C2543rl0 c2543rl0, C2543rl0 c2543rl1) {
        if (c2543rl0 == ((C2543rl0) this.c.get(0)) && c2543rl1 == P2()) {
            return this;
        }
        return null;
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 d(int i, int i2, int i3) {
        return new com.android.tools.r8.dex.code.E3(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final boolean f(C2543rl0 c2543rl0) {
        return g(c2543rl0);
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 c(int i, int i2) {
        return new com.android.tools.r8.dex.code.D3(i, i2);
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 b(int i, int i2, int i3) {
        throw new Kk0("Unsupported instruction ShlIntLit16");
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 c(int i, int i2, int i3) {
        return new com.android.tools.r8.dex.code.C3(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final long a(long j, long j2) {
        return j << ((int) j2);
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 b(int i, int i2) {
        return new com.android.tools.r8.dex.code.A3(i, i2);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        return null;
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 a(int i, int i2, int i3) {
        return new com.android.tools.r8.dex.code.B3(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final int a(int i, int i2) {
        return i << i2;
    }
}
