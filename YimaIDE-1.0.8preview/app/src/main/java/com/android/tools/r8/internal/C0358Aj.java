package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Aj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0358Aj extends AbstractC0963Xr {
    public int g;
    public List h;
    public C2401q50 i;

    public C0358Aj() {
        this.h = Collections.EMPTY_LIST;
    }

    public final C0358Aj a(C0384Bj c0384Bj) {
        if (c0384Bj == C0384Bj.h) {
            return this;
        }
        if (this.i == null) {
            if (!c0384Bj.f.isEmpty()) {
                if (this.h.isEmpty()) {
                    this.h = c0384Bj.f;
                    this.g &= -2;
                } else {
                    if ((this.g & 1) == 0) {
                        this.h = new ArrayList(this.h);
                        this.g |= 1;
                    }
                    this.h.addAll(c0384Bj.f);
                }
                p();
            }
        } else if (!c0384Bj.f.isEmpty()) {
            boolean zIsEmpty = this.i.b.isEmpty();
            C2401q50 c2401q50 = this.i;
            if (zIsEmpty) {
                c2401q50.a = null;
                this.h = c0384Bj.f;
                this.g &= -2;
                this.i = null;
            } else {
                c2401q50.a(c0384Bj.f);
            }
        }
        a((AbstractC1102as) c0384Bj);
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C0384Bj) {
            return a((C0384Bj) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C0384Bj c0384BjI = i();
        if (c0384BjI.a()) {
            return c0384BjI;
        }
        throw H0.c(c0384BjI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C0358Aj a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C0384Bj c0384Bj = null;
        try {
            try {
                a((C0384Bj) C0384Bj.i.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C0384Bj c0384Bj2 = (C0384Bj) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c0384Bj = c0384Bj2;
                    if (c0384Bj != null) {
                        a(c0384Bj);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c0384Bj != null) {
                a(c0384Bj);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C0358Aj) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.E;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C0384Bj c0384BjI = i();
        if (c0384BjI.a()) {
            return c0384BjI;
        }
        throw H0.c(c0384BjI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.F.a(C0384Bj.class, C0358Aj.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C0384Bj i() {
        C0384Bj c0384Bj = new C0384Bj(this);
        int i = this.g;
        C2401q50 c2401q50 = this.i;
        if (c2401q50 == null) {
            if ((i & 1) != 0) {
                this.h = Collections.unmodifiableList(this.h);
                this.g &= -2;
            }
            c0384Bj.f = this.h;
        } else {
            c0384Bj.f = c2401q50.b();
        }
        o();
        return c0384Bj;
    }

    public C0358Aj(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.h = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        return (C0358Aj) d(c1856jk, obj);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C0384Bj.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        return (C0358Aj) c(c1856jk, obj);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof C0384Bj) {
            return a((C0384Bj) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C0358Aj) c(c2712tk0);
    }
}
