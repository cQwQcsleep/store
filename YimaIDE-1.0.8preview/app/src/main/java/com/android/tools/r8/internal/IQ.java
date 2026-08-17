package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.C0122w0;
import com.android.tools.r8.graph.C0333y;
import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class IQ extends AbstractC0890Uw {
    public static final /* synthetic */ boolean l = true;
    public final int i;
    public final long j;
    public final short[] k;

    public IQ(C2543rl0 c2543rl0, int i, long j, short[] sArr) {
        super((C2543rl0) null, c2543rl0);
        this.i = i;
        this.j = j;
        this.k = sArr;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int F2() {
        return 255;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int G2() {
        if (l) {
            return 0;
        }
        x01.a("NewArrayFilledData defines no values.");
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 48;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final B1 a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1) {
        if (b(c0333y, b5, j1, C0864Tw.a) || this.j > 2147483647L) {
            return Ak0.a;
        }
        if (!l && (c0333y.M().Z0 || ((C2543rl0) this.c.get(0)).t().F())) {
            x1f.a();
            return null;
        }
        RI riA = c0333y.t.a((int) this.j);
        riA.getClass();
        return new C1675he0(riA);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1, C0864Tw c0864Tw) {
        if (a(c0333y, b5, j1, c0864Tw) || ((C2543rl0) this.c.get(0)).U() > 1) {
            return true;
        }
        boolean z = l;
        if (!z && ((C2543rl0) this.c.get(0)).Z() != this) {
            x1f.a();
            return false;
        }
        if (!z && ((C2543rl0) this.c.get(0)).j()) {
            x1f.a();
            return false;
        }
        if (!z) {
            AbstractC0890Uw abstractC0890Uw = ((C2543rl0) this.c.get(0)).c;
            abstractC0890Uw.getClass();
            if (!(abstractC0890Uw instanceof FQ)) {
                x1f.a();
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean d(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw, com.android.tools.r8.internal.H
    public final boolean g() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean n2() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final IQ s0() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C2884vl c2884vl) {
        C0122w0 c0122w0 = new C0122w0(c2884vl.d.a((C2543rl0) this.c.get(0), this.e));
        c2884vl.h.add(new C2114ml(this, c0122w0));
        c2884vl.a(this, c0122w0);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(J8 j8) {
        throw new Kk0("Conversion from DEX to classfile not supported for NewArrayFilledData");
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(LK lk) {
        int i = this.i;
        long j = this.j;
        short[] sArr = this.k;
        C2543rl0 c2543rl0 = (C2543rl0) this.c.get(0);
        lk.getClass();
        lk.a(215, Collections.singletonList(new FK(i, j, sArr)), Collections.singletonList(c2543rl0));
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(K5 k5, IL il) {
        throw new Kk0("Conversion from DEX to classfile not supported for NewArrayFilledData");
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1, C0864Tw c0864Tw) {
        return c0333y.M().Z0 || ((C2543rl0) this.c.get(0)).t().F();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        if (!abstractC0890Uw.n2()) {
            return false;
        }
        IQ iqS0 = abstractC0890Uw.s0();
        return iqS0.i == this.i && iqS0.j == this.j && Arrays.equals(iqS0.k, this.k);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.ir.optimize.N a(com.android.tools.r8.ir.optimize.W w, com.android.tools.r8.graph.B5 b5) {
        return com.android.tools.r8.ir.optimize.N.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        return null;
    }
}
