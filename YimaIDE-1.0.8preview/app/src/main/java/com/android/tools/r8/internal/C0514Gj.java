package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Gj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0514Gj extends AbstractC0963Xr {
    public int g;
    public boolean h;
    public List i;
    public C2401q50 j;

    public C0514Gj() {
        this.i = Collections.EMPTY_LIST;
    }

    public final C0514Gj a(C0540Hj c0540Hj) {
        if (c0540Hj == C0540Hj.j) {
            return this;
        }
        if ((c0540Hj.f & 1) != 0) {
            boolean z = c0540Hj.g;
            this.g |= 1;
            this.h = z;
            p();
        }
        C2401q50 c2401q50 = this.j;
        List list = c0540Hj.h;
        if (c2401q50 == null) {
            if (!list.isEmpty()) {
                if (this.i.isEmpty()) {
                    this.i = c0540Hj.h;
                    this.g &= -3;
                } else {
                    if ((this.g & 2) == 0) {
                        this.i = new ArrayList(this.i);
                        this.g |= 2;
                    }
                    this.i.addAll(c0540Hj.h);
                }
                p();
            }
        } else if (!list.isEmpty()) {
            boolean zIsEmpty = this.j.b.isEmpty();
            C2401q50 c2401q51 = this.j;
            if (zIsEmpty) {
                c2401q51.a = null;
                this.i = c0540Hj.h;
                this.g &= -3;
                this.j = null;
            } else {
                c2401q51.a(c0540Hj.h);
            }
        }
        a((AbstractC1102as) c0540Hj);
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C0540Hj) {
            return a((C0540Hj) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C0540Hj c0540HjI = i();
        if (c0540HjI.a()) {
            return c0540HjI;
        }
        throw H0.c(c0540HjI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C0514Gj a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C0540Hj c0540Hj = null;
        try {
            try {
                a((C0540Hj) C0540Hj.k.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C0540Hj c0540Hj2 = (C0540Hj) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c0540Hj = c0540Hj2;
                    if (c0540Hj != null) {
                        a(c0540Hj);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c0540Hj != null) {
                a(c0540Hj);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C0514Gj) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.K;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C0540Hj c0540HjI = i();
        if (c0540HjI.a()) {
            return c0540HjI;
        }
        throw H0.c(c0540HjI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.L.a(C0540Hj.class, C0514Gj.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C0540Hj i() {
        int i;
        C0540Hj c0540Hj = new C0540Hj(this);
        int i2 = this.g;
        if ((i2 & 1) != 0) {
            c0540Hj.g = this.h;
            i = 1;
        } else {
            i = 0;
        }
        C2401q50 c2401q50 = this.j;
        if (c2401q50 == null) {
            if ((i2 & 2) != 0) {
                this.i = Collections.unmodifiableList(this.i);
                this.g &= -3;
            }
            c0540Hj.h = this.i;
        } else {
            c0540Hj.h = c2401q50.b();
        }
        c0540Hj.f = i;
        o();
        return c0540Hj;
    }

    public C0514Gj(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.i = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        return (C0514Gj) d(c1856jk, obj);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C0540Hj.j;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        return (C0514Gj) c(c1856jk, obj);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof C0540Hj) {
            return a((C0540Hj) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C0514Gj) c(c2712tk0);
    }
}
