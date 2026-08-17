package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.AbstractC0138z1;
import com.android.tools.r8.dex.code.C0134y2;
import com.android.tools.r8.dex.code.C0139z2;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xP, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3023xP extends C3 {
    public C3023xP(US us, C2543rl0 c2543rl0, C2543rl0 c2543rl1, C2543rl0 c2543rl2) {
        super(us, c2543rl0, c2543rl1, c2543rl2);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 45;
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final boolean L2() {
        return true;
    }

    @Override // com.android.tools.r8.internal.C3
    public final C3004x8.a Q2() {
        return C3004x8.a.d;
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 a(int i, int i2, int i3) {
        return i == i3 ? new C0139z2(i, i3, i2) : new C0139z2(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        abstractC0890Uw.getClass();
        return (abstractC0890Uw instanceof C3023xP) && abstractC0890Uw.o0().i == this.i;
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 c(int i, int i2, int i3) {
        return i == i3 ? new com.android.tools.r8.dex.code.D2(i, i3, i2) : new com.android.tools.r8.dex.code.D2(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 d(int i, int i2) {
        return new com.android.tools.r8.dex.code.C2(i, i2);
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 e(int i, int i2) {
        return new com.android.tools.r8.dex.code.G2(i, i2);
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 f(int i, int i2, int i3) {
        return i == i3 ? new com.android.tools.r8.dex.code.H2(i, i3, i2) : new com.android.tools.r8.dex.code.H2(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C3023xP o0() {
        return this;
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 d(int i, int i2, int i3) {
        return new com.android.tools.r8.dex.code.E2(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 e(int i, int i2, int i3) {
        return new com.android.tools.r8.dex.code.F2(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final float a(float f, float f2) {
        return f * f2;
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 c(int i, int i2) {
        return new com.android.tools.r8.dex.code.A2(i, i2);
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final int a(int i, int i2) {
        return i * i2;
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final long a(long j, long j2) {
        return j * j2;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        return null;
    }

    public static C3023xP a(US us, C2543rl0 c2543rl0, C2543rl0 c2543rl1, C2543rl0 c2543rl2) {
        C3023xP c3023xP = new C3023xP(us, c2543rl0, c2543rl1, c2543rl2);
        c3023xP.O2();
        return c3023xP;
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final double a(double d, double d2) {
        return d * d2;
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 b(int i, int i2) {
        return new C0134y2(i, i2);
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 b(int i, int i2, int i3) {
        return i == i3 ? new com.android.tools.r8.dex.code.B2(i, i3, i2) : new com.android.tools.r8.dex.code.B2(i, i2, i3);
    }
}
