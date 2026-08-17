package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.p80, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2321p80 extends AbstractC0911Vr {
    public int f;
    public List g;
    public C2401q50 h;

    public C2321p80() {
        super(null);
        this.g = Collections.EMPTY_LIST;
        C2662t80 c2662t80 = C2662t80.g;
    }

    public final C2321p80 a(C2662t80 c2662t80) {
        if (c2662t80 == C2662t80.g) {
            return this;
        }
        if (this.h == null) {
            if (!c2662t80.e.isEmpty()) {
                if (this.g.isEmpty()) {
                    this.g = c2662t80.e;
                    this.f &= -2;
                } else {
                    if ((this.f & 1) == 0) {
                        this.g = new ArrayList(this.g);
                        this.f |= 1;
                    }
                    this.g.addAll(c2662t80.e);
                }
                p();
            }
        } else if (!c2662t80.e.isEmpty()) {
            boolean zIsEmpty = this.h.b.isEmpty();
            C2401q50 c2401q50 = this.h;
            if (zIsEmpty) {
                c2401q50.a = null;
                this.g = c2662t80.e;
                this.f &= -2;
                this.h = null;
            } else {
                c2401q50.a(c2662t80.e);
            }
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.v0.a(C2662t80.class, C2321p80.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C2662t80 c2662t80I = i();
        if (c2662t80I.a()) {
            return c2662t80I;
        }
        throw H0.c(c2662t80I);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C2321p80 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C2662t80 c2662t80 = null;
        try {
            try {
                a((C2662t80) C2662t80.h.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C2662t80 c2662t81 = (C2662t80) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c2662t80 = c2662t81;
                    if (c2662t80 != null) {
                        a(c2662t80);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c2662t80 != null) {
                a(c2662t80);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C2321p80) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.u0;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C2662t80 c2662t80I = i();
        if (c2662t80I.a()) {
            return c2662t80I;
        }
        throw H0.c(c2662t80I);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.v0.a(C2662t80.class, C2321p80.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C2662t80 i() {
        C2662t80 c2662t80 = new C2662t80(this);
        int i = this.f;
        C2401q50 c2401q50 = this.h;
        if (c2401q50 == null) {
            if ((i & 1) != 0) {
                this.g = Collections.unmodifiableList(this.g);
                this.f &= -2;
            }
            c2662t80.e = this.g;
        } else {
            c2662t80.e = c2401q50.b();
        }
        o();
        return c2662t80;
    }

    public C2321p80(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.g = Collections.EMPTY_LIST;
        C2662t80 c2662t80 = C2662t80.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C2662t80) {
            return a((C2662t80) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C2662t80.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.v0.a(C2662t80.class, C2321p80.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof C2662t80) {
            return a((C2662t80) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C2321p80) c(c2712tk0);
    }
}
