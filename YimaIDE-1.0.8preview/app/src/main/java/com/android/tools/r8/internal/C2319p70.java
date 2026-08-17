package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.p70, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2319p70 extends AbstractC0911Vr {
    public int f;
    public List g;
    public C2401q50 h;

    public C2319p70() {
        super(null);
        this.g = Collections.EMPTY_LIST;
        C2660t70 c2660t70 = C2660t70.g;
    }

    public final C2319p70 a(C2660t70 c2660t70) {
        if (c2660t70 == C2660t70.g) {
            return this;
        }
        if (this.h == null) {
            if (!c2660t70.e.isEmpty()) {
                if (this.g.isEmpty()) {
                    this.g = c2660t70.e;
                    this.f &= -2;
                } else {
                    if ((this.f & 1) == 0) {
                        this.g = new ArrayList(this.g);
                        this.f |= 1;
                    }
                    this.g.addAll(c2660t70.e);
                }
                p();
            }
        } else if (!c2660t70.e.isEmpty()) {
            boolean zIsEmpty = this.h.b.isEmpty();
            C2401q50 c2401q50 = this.h;
            if (zIsEmpty) {
                c2401q50.a = null;
                this.g = c2660t70.e;
                this.f &= -2;
                this.h = null;
            } else {
                c2401q50.a(c2660t70.e);
            }
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.D0.a(C2660t70.class, C2319p70.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C2660t70 c2660t70I = i();
        if (c2660t70I.a()) {
            return c2660t70I;
        }
        throw H0.c(c2660t70I);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C2319p70 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C2660t70 c2660t70 = null;
        try {
            try {
                a((C2660t70) C2660t70.h.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C2660t70 c2660t71 = (C2660t70) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c2660t70 = c2660t71;
                    if (c2660t70 != null) {
                        a(c2660t70);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c2660t70 != null) {
                a(c2660t70);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C2319p70) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.C0;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C2660t70 c2660t70I = i();
        if (c2660t70I.a()) {
            return c2660t70I;
        }
        throw H0.c(c2660t70I);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.D0.a(C2660t70.class, C2319p70.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C2660t70 i() {
        C2660t70 c2660t70 = new C2660t70(this);
        int i = this.f;
        C2401q50 c2401q50 = this.h;
        if (c2401q50 == null) {
            if ((i & 1) != 0) {
                this.g = Collections.unmodifiableList(this.g);
                this.f &= -2;
            }
            c2660t70.e = this.g;
        } else {
            c2660t70.e = c2401q50.b();
        }
        o();
        return c2660t70;
    }

    public C2319p70(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.g = Collections.EMPTY_LIST;
        C2660t70 c2660t70 = C2660t70.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C2660t70) {
            return a((C2660t70) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C2660t70.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.D0.a(C2660t70.class, C2319p70.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof C2660t70) {
            return a((C2660t70) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C2319p70) c(c2712tk0);
    }
}
