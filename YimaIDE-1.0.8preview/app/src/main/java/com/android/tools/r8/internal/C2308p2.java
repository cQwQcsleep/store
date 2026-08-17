package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.AbstractC0138z1;
import com.android.tools.r8.dex.code.C0116v;
import com.android.tools.r8.dex.code.C0121w;
import com.android.tools.r8.dex.code.C0126x;
import com.android.tools.r8.dex.code.C0131y;
import com.android.tools.r8.dex.code.C0136z;
import com.android.tools.r8.graph.C0333y;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.p2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2308p2 extends QL {
    public C2308p2(US us, C2543rl0 c2543rl0, C2543rl0 c2543rl1, C2543rl0 c2543rl2) {
        super(us, c2543rl0, c2543rl1, c2543rl2);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 4;
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final boolean L2() {
        return true;
    }

    @Override // com.android.tools.r8.internal.QL
    public final Q9 Q2() {
        return Q9.e;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(Set set) {
        return ((C2543rl0) this.c.get(0)).b(set) && P2().b(set);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        abstractC0890Uw.getClass();
        return (abstractC0890Uw instanceof C2308p2) && abstractC0890Uw.q().i == this.i;
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 c(int i, int i2) {
        return new C0136z(i, i2);
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 d(int i, int i2, int i3) {
        return new com.android.tools.r8.dex.code.A(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C2308p2 q() {
        return this;
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 c(int i, int i2, int i3) {
        return new C0131y(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 b(int i, int i2, int i3) {
        return new C0126x(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 b(int i, int i2) {
        return new C0116v(i, i2);
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final long a(long j, long j2) {
        return j & j2;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C2308p2 a(C2543rl0 c2543rl0, C2543rl0 c2543rl1) {
        if (c2543rl0 == ((C2543rl0) this.c.get(0)) && c2543rl1 == P2()) {
            return this;
        }
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final int a(int i, int i2) {
        return i & i2;
    }

    @Override // com.android.tools.r8.internal.QL
    public final AbstractC0138z1 a(int i, int i2, int i3) {
        return new C0121w(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final B1 a(C0333y c0333y, B1 b1, B1 b2) {
        return AbstractC2046m.a(c0333y, b1, b2);
    }
}
