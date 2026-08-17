package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.AbstractC0138z1;
import com.android.tools.r8.dex.code.C0032e;
import com.android.tools.r8.dex.code.C0037f;
import com.android.tools.r8.dex.code.C0042g;
import com.android.tools.r8.dex.code.C0047h;
import com.android.tools.r8.dex.code.C0052i;
import com.android.tools.r8.dex.code.C0057j;
import com.android.tools.r8.dex.code.C0062k;
import com.android.tools.r8.dex.code.C0067l;
import com.android.tools.r8.dex.code.C0072m;
import com.android.tools.r8.dex.code.C0077n;

/* JADX INFO: renamed from: com.android.tools.r8.internal.a2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1029a2 extends C3 {
    public C1029a2(US us, C2543rl0 c2543rl0, C2543rl0 c2543rl1, C2543rl0 c2543rl2) {
        super(us, c2543rl0, c2543rl1, c2543rl2);
    }

    public static C1029a2 a(US us, C2543rl0 c2543rl0, C2543rl0 c2543rl1, C2543rl0 c2543rl2) {
        C1029a2 c1029a2 = new C1029a2(us, c2543rl0, c2543rl1, c2543rl2);
        c1029a2.O2();
        return c1029a2;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final boolean L2() {
        return true;
    }

    @Override // com.android.tools.r8.internal.C3
    public final C3004x8.a Q2() {
        return C3004x8.a.b;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        return abstractC0890Uw.h1() && abstractC0890Uw.o().i == this.i;
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 c(int i, int i2, int i3) {
        return new C0057j(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 d(int i, int i2) {
        return new C0052i(i, i2);
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 e(int i, int i2) {
        return new C0072m(i, i2);
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 f(int i, int i2, int i3) {
        return new C0077n(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean h1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C1029a2 o() {
        return this;
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 c(int i, int i2) {
        return new C0042g(i, i2);
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 d(int i, int i2, int i3) {
        return new C0062k(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 e(int i, int i2, int i3) {
        return new C0067l(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final float a(float f, float f2) {
        return f + f2;
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final int a(int i, int i2) {
        return i + i2;
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final long a(long j, long j2) {
        return j + j2;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC1547g6
    public final double a(double d, double d2) {
        return d + d2;
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 a(int i, int i2, int i3) {
        return new C0037f(i, i2, i3);
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 b(int i, int i2) {
        return new C0032e(i, i2);
    }

    @Override // com.android.tools.r8.internal.C3
    public final AbstractC0138z1 b(int i, int i2, int i3) {
        return new C0047h(i, i2, i3);
    }
}
