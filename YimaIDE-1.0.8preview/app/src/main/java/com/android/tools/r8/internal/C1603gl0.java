package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.AbstractC0138z1;
import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gl0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1603gl0 extends QL {
    public C1603gl0(US us, C2543rl0 c2543rl0, C2543rl0 c2543rl1, C2543rl0 c2543rl2) {
        super(us, c2543rl0, c2543rl1, c2543rl2);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 67;
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final boolean L2() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C1603gl0 O0() {
        return this;
    }

    @Override // com.android.tools.r8.internal.QL
    public final Q9 Q2() {
        return Q9.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final B1 a(C0333y c0333y, B1 b1, B1 b2) {
        b2.getClass();
        if (!(b2 instanceof C2525rc0)) {
            return Ak0.a;
        }
        int i = (int) b2.e().b;
        if (i == 0) {
            return b1;
        }
        if (b1 instanceof C2525rc0) {
            return c0333y.t.a(((int) ((C2525rc0) b1).b) >>> i);
        }
        if (!b1.z() || i <= 0) {
            return Ak0.a;
        }
        return c0333y.t.a(b1.v() >>> i, (Integer.MIN_VALUE >> (i - 1)) | (b1.w() >>> i));
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        abstractC0890Uw.getClass();
        return (abstractC0890Uw instanceof C1603gl0) && abstractC0890Uw.O0().i == this.i;
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 c(int i, int i2) {
        return new com.android.tools.r8.dex.code.g4(i, i2);
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 d(int i, int i2, int i3) {
        return new com.android.tools.r8.dex.code.h4(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final boolean f(C2543rl0 c2543rl0) {
        return g(c2543rl0);
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 c(int i, int i2, int i3) {
        return new com.android.tools.r8.dex.code.f4(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 b(int i, int i2, int i3) {
        throw new Kk0("Unsupported instruction ShrIntLit16");
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 b(int i, int i2) {
        return new com.android.tools.r8.dex.code.d4(i, i2);
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final long a(long j, long j2) {
        return j >>> ((int) j2);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        return null;
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 a(int i, int i2, int i3) {
        return new com.android.tools.r8.dex.code.e4(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final int a(int i, int i2) {
        return i >>> i2;
    }
}
