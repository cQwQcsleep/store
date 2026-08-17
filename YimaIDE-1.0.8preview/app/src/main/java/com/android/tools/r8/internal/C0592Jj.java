package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Jj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0592Jj extends AbstractC0911Vr {
    public int f;
    public List g;
    public C2401q50 h;

    public C0592Jj() {
        super(null);
        this.g = Collections.EMPTY_LIST;
    }

    public final C0592Jj a(C0695Nj c0695Nj) {
        if (c0695Nj == C0695Nj.g) {
            return this;
        }
        if (this.h == null) {
            if (!c0695Nj.e.isEmpty()) {
                if (this.g.isEmpty()) {
                    this.g = c0695Nj.e;
                    this.f &= -2;
                } else {
                    if ((this.f & 1) == 0) {
                        this.g = new ArrayList(this.g);
                        this.f |= 1;
                    }
                    this.g.addAll(c0695Nj.e);
                }
                p();
            }
        } else if (!c0695Nj.e.isEmpty()) {
            boolean zIsEmpty = this.h.b.isEmpty();
            C2401q50 c2401q50 = this.h;
            if (zIsEmpty) {
                c2401q50.a = null;
                this.g = c0695Nj.e;
                this.f &= -2;
                this.h = null;
            } else {
                c2401q50.a(c0695Nj.e);
            }
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C0695Nj) {
            return a((C0695Nj) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C0695Nj c0695NjI = i();
        if (c0695NjI.a()) {
            return c0695NjI;
        }
        throw H0.c(c0695NjI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C0592Jj a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C0695Nj c0695Nj = null;
        try {
            try {
                a((C0695Nj) C0695Nj.h.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C0695Nj c0695Nj2 = (C0695Nj) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c0695Nj = c0695Nj2;
                    if (c0695Nj != null) {
                        a(c0695Nj);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c0695Nj != null) {
                a(c0695Nj);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C0592Jj) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.S;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C0695Nj c0695NjI = i();
        if (c0695NjI.a()) {
            return c0695NjI;
        }
        throw H0.c(c0695NjI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.T.a(C0695Nj.class, C0592Jj.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C0695Nj i() {
        C0695Nj c0695Nj = new C0695Nj(this);
        int i = this.f;
        C2401q50 c2401q50 = this.h;
        if (c2401q50 == null) {
            if ((i & 1) != 0) {
                this.g = Collections.unmodifiableList(this.g);
                this.f &= -2;
            }
            c0695Nj.e = this.g;
        } else {
            c0695Nj.e = c2401q50.b();
        }
        o();
        return c0695Nj;
    }

    public C0592Jj(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.g = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(n(), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C0695Nj.g;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof C0695Nj) {
            return a((C0695Nj) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C0592Jj) c(c2712tk0);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(n(), c1856jk).a(this, obj);
        return this;
    }
}
