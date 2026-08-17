package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.c3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1201c3 extends AbstractC0890Uw {
    public static final /* synthetic */ boolean k = true;
    public final int i;
    public final boolean j;

    public C1201c3(C2543rl0 c2543rl0, int i, boolean z) {
        super(c2543rl0);
        this.i = i;
        this.j = z;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int F2() {
        if (k) {
            return 0;
        }
        x01.a("Argument has no register arguments.");
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int G2() {
        return 65535;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 5;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C2884vl c2884vl) {
        c2884vl.n = c().o.O() + c2884vl.n;
        c2884vl.a(this, new C2029ll(this));
    }

    public final int b(boolean z) {
        if (!k && z) {
            J5 j5H = i().H();
            int i = 0;
            while (j5H.hasNext()) {
                AbstractC0890Uw next = j5H.next();
                boolean z2 = k;
                if (!z2 && !next.k1()) {
                    x1f.a();
                    return 0;
                }
                if (next != this) {
                    i++;
                } else if (!z2 && i != this.i) {
                    x1f.a();
                    return 0;
                }
            }
            x1f.a();
            return 0;
        }
        return this.i;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean d(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean k1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C1201c3 r() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(K5 k5, IL il) {
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final B1 a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1) {
        if (this.b.y()) {
            return Ak0.a;
        }
        return Ak0.a;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.ir.optimize.N a(com.android.tools.r8.ir.optimize.W w, com.android.tools.r8.graph.B5 b5) {
        return com.android.tools.r8.ir.optimize.N.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.ir.optimize.E a(C0333y c0333y, C0705Nt c0705Nt) {
        return com.android.tools.r8.ir.optimize.E.b;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.graph.I2 a(C0333y c0333y, Nj0 nj0) {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(J8 j8) {
        j8.getClass();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final AbstractC2624sj0 a(C0333y c0333y) {
        return this.b.t();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(Set set) {
        return this.j;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(LK lk) {
        lk.a(this.i);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        return abstractC0890Uw.k1();
    }
}
