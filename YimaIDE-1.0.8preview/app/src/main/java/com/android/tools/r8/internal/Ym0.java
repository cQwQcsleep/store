package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.AbstractC0138z1;
import com.android.tools.r8.graph.C0333y;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Ym0 extends QL {
    public Ym0(US us, C2543rl0 c2543rl0, C2543rl0 c2543rl1, C2543rl0 c2543rl2) {
        super(us, c2543rl0, c2543rl1, c2543rl2);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 68;
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final boolean L2() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Ym0 P0() {
        return this;
    }

    @Override // com.android.tools.r8.internal.QL
    public final Q9 Q2() {
        return Q9.g;
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final B1 a(C0333y c0333y, B1 b1, B1 b2) {
        if (b1 instanceof C2525rc0) {
            b2.getClass();
            if (b2 instanceof C2525rc0) {
                return c0333y.t.a(((int) ((C2525rc0) b1).b) ^ ((int) b2.e().b));
            }
        }
        if (!b1.z() || !b2.z()) {
            return Ak0.a;
        }
        return c0333y.t.a((b1.v() & b2.w()) | (b1.w() & b2.v()), (b1.w() & b2.w()) | (b1.v() & b2.v()));
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        abstractC0890Uw.getClass();
        return (abstractC0890Uw instanceof Ym0) && abstractC0890Uw.P0().i == this.i;
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 c(int i, int i2) {
        return new com.android.tools.r8.dex.code.m4(i, i2);
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 d(int i, int i2, int i3) {
        return new com.android.tools.r8.dex.code.n4(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 c(int i, int i2, int i3) {
        return new com.android.tools.r8.dex.code.l4(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 b(int i, int i2, int i3) {
        return new com.android.tools.r8.dex.code.k4(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 b(int i, int i2) {
        return new com.android.tools.r8.dex.code.i4(i, i2);
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final long a(long j, long j2) {
        return j ^ j2;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final int a(int i, int i2) {
        return i ^ i2;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(Set set) {
        return ((C2543rl0) this.c.get(0)).b(set) && P2().b(set);
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 a(int i, int i2, int i3) {
        return new com.android.tools.r8.dex.code.j4(i, i2, i3);
    }
}
