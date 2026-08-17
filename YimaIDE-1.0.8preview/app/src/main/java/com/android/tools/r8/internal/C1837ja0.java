package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.C0071l3;
import com.android.tools.r8.dex.code.C0076m3;
import com.android.tools.r8.dex.code.C0081n3;
import com.android.tools.r8.dex.code.C0086o3;
import defpackage.hkh;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ja0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1837ja0 extends AbstractC2925wD {
    public static final /* synthetic */ boolean j = true;

    public C1837ja0() {
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C1837ja0 D0() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int F2() {
        return 255;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int G2() {
        if (j) {
            return 0;
        }
        x01.a("Return defines no values.");
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 56;
    }

    public final boolean L2() {
        return this.c.size() == 0;
    }

    public C2543rl0 M2() {
        if (j || !L2()) {
            return (C2543rl0) this.c.get(0);
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(J8 j8) {
        AbstractC3175z9 c2948wa;
        if (L2()) {
            c2948wa = new C3034xa();
        } else {
            if (!j && L2()) {
                x1f.a();
                return;
            }
            c2948wa = new C2948wa(El0.a(M2().t()));
        }
        j8.a(c2948wa, this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        if (!abstractC0890Uw.v2()) {
            return false;
        }
        C1837ja0 c1837ja0D0 = abstractC0890Uw.D0();
        if (L2()) {
            return c1837ja0D0.L2();
        }
        boolean z = j;
        if (!z && L2()) {
            x1f.a();
            return false;
        }
        AbstractC2624sj0 abstractC2624sj0T = M2().t();
        if (!z && c1837ja0D0.L2()) {
            x1f.a();
            return false;
        }
        AbstractC2624sj0 abstractC2624sj0T2 = c1837ja0D0.M2().t();
        if (abstractC2624sj0T.I() && abstractC2624sj0T2.I()) {
            return true;
        }
        if (abstractC2624sj0T.K() && abstractC2624sj0T2.K()) {
            return true;
        }
        return abstractC2624sj0T.M() && abstractC2624sj0T2.M();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean v2() {
        return true;
    }

    public C1837ja0(C2543rl0 c2543rl0) {
        super(c2543rl0);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(LK lk) {
        if (!L2()) {
            C2543rl0 c2543rl0M2 = M2();
            lk.getClass();
            lk.a(176, Collections.EMPTY_LIST, Collections.singletonList(c2543rl0M2));
            return;
        }
        lk.b();
    }

    @Override // com.android.tools.r8.internal.AbstractC2925wD, com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.ir.optimize.N a(com.android.tools.r8.ir.optimize.W w, com.android.tools.r8.graph.B5 b5) {
        if (!L2()) {
            if (M2().t().a(b5.G().b(w.a), w.a)) {
                return com.android.tools.r8.ir.optimize.N.d;
            }
            return com.android.tools.r8.ir.optimize.N.c;
        }
        return com.android.tools.r8.ir.optimize.N.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C2884vl c2884vl) {
        com.android.tools.r8.dex.code.J jB = b(c2884vl);
        H5 h5 = c2884vl.q;
        if (h5 != null && a((AbstractC0890Uw) h5.f.get(0), c2884vl.d, c2884vl.f)) {
            c2884vl.a(this, new C2029ll(this));
        } else {
            c2884vl.a(this, jB);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(K5 k5, IL il) {
        if (L2()) {
            return;
        }
        il.a(this, k5);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        return null;
    }

    public final com.android.tools.r8.dex.code.J b(C2884vl c2884vl) {
        if (L2()) {
            return new C0081n3();
        }
        int iA = c2884vl.d.a(M2(), this.e);
        if (!j && L2()) {
            x1f.a();
            return null;
        }
        AbstractC2624sj0 abstractC2624sj0T = M2().t();
        if (abstractC2624sj0T.I()) {
            return new C0076m3(iA);
        }
        if (abstractC2624sj0T.K()) {
            return new C0071l3(iA);
        }
        if (abstractC2624sj0T.M()) {
            return new C0086o3(iA);
        }
        hkh.a();
        return null;
    }
}
