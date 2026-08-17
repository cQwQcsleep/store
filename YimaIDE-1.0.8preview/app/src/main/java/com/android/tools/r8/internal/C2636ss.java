package com.android.tools.r8.internal;

import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ss, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2636ss extends AbstractC2925wD {
    public static final /* synthetic */ boolean j = true;

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int F2() {
        if (j) {
            return 0;
        }
        x01.a("Goto has no register arguments.");
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int G2() {
        if (j) {
            return 0;
        }
        x01.a("Goto defines no values.");
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 24;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean K1() {
        return true;
    }

    public final H5 L2() {
        boolean z = j;
        if (!z && i().h() != this) {
            x1f.a();
            return null;
        }
        List<H5> listT = i().t();
        if (z || listT.size() >= 1) {
            return listT.get(listT.size() - 1);
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C2636ss Q() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C2884vl c2884vl) {
        c2884vl.getClass();
        if (L2() != c2884vl.q) {
            c2884vl.a(this, new C2286ol(this));
        } else {
            c2884vl.a(this, new C2029ll(this));
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        return abstractC0890Uw.K1() && abstractC0890Uw.Q().L2() == L2();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean i1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final String toString() {
        H5 h5I = i();
        if (h5I == null || h5I.t().isEmpty() || C2847vL.b(h5I.k()) != this) {
            return super.toString() + "block <unknown>";
        }
        return super.toString() + "block " + L2().p();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(K5 k5, IL il) {
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(J8 j8) {
        j8.a(new C2322p9(j8.a(L2())), this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(LK lk) {
        int iA = lk.h.a(L2());
        boolean z = W7.a;
        lk.c();
        lk.c.a(167, 4);
        lk.e(iA);
    }
}
