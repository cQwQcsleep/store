package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.oj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2282oj extends AbstractC0963Xr {
    public int g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public List l;
    public C2401q50 m;

    public C2282oj() {
        this.l = Collections.EMPTY_LIST;
    }

    public final C2282oj a(C2368pj c2368pj) {
        if (c2368pj == C2368pj.m) {
            return this;
        }
        if ((c2368pj.f & 1) != 0) {
            boolean z = c2368pj.g;
            this.g |= 1;
            this.h = z;
            p();
        }
        if ((c2368pj.f & 2) != 0) {
            boolean z2 = c2368pj.h;
            this.g |= 2;
            this.i = z2;
            p();
        }
        if ((c2368pj.f & 4) != 0) {
            boolean z3 = c2368pj.i;
            this.g |= 4;
            this.j = z3;
            p();
        }
        if ((c2368pj.f & 8) != 0) {
            boolean z4 = c2368pj.j;
            this.g |= 8;
            this.k = z4;
            p();
        }
        C2401q50 c2401q50 = this.m;
        List list = c2368pj.k;
        if (c2401q50 == null) {
            if (!list.isEmpty()) {
                if (this.l.isEmpty()) {
                    this.l = c2368pj.k;
                    this.g &= -17;
                } else {
                    if ((this.g & 16) == 0) {
                        this.l = new ArrayList(this.l);
                        this.g |= 16;
                    }
                    this.l.addAll(c2368pj.k);
                }
                p();
            }
        } else if (!list.isEmpty()) {
            boolean zIsEmpty = this.m.b.isEmpty();
            C2401q50 c2401q51 = this.m;
            if (zIsEmpty) {
                c2401q51.a = null;
                this.l = c2368pj.k;
                this.g &= -17;
                this.m = null;
            } else {
                c2401q51.a(c2368pj.k);
            }
        }
        a((AbstractC1102as) c2368pj);
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C2368pj) {
            return a((C2368pj) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C2368pj c2368pjI = i();
        if (c2368pjI.a()) {
            return c2368pjI;
        }
        throw H0.c(c2368pjI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C2282oj a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C2368pj c2368pj = null;
        try {
            try {
                a((C2368pj) C2368pj.n.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C2368pj c2368pj2 = (C2368pj) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c2368pj = c2368pj2;
                    if (c2368pj != null) {
                        a(c2368pj);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c2368pj != null) {
                a(c2368pj);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C2282oj) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.A;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C2368pj c2368pjI = i();
        if (c2368pjI.a()) {
            return c2368pjI;
        }
        throw H0.c(c2368pjI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.B.a(C2368pj.class, C2282oj.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C2368pj i() {
        int i;
        C2368pj c2368pj = new C2368pj(this);
        int i2 = this.g;
        if ((i2 & 1) != 0) {
            c2368pj.g = this.h;
            i = 1;
        } else {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            c2368pj.h = this.i;
            i |= 2;
        }
        if ((i2 & 4) != 0) {
            c2368pj.i = this.j;
            i |= 4;
        }
        if ((i2 & 8) != 0) {
            c2368pj.j = this.k;
            i |= 8;
        }
        C2401q50 c2401q50 = this.m;
        if (c2401q50 == null) {
            if ((i2 & 16) != 0) {
                this.l = Collections.unmodifiableList(this.l);
                this.g &= -17;
            }
            c2368pj.k = this.l;
        } else {
            c2368pj.k = c2401q50.b();
        }
        c2368pj.f = i;
        o();
        return c2368pj;
    }

    public C2282oj(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.l = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        return (C2282oj) d(c1856jk, obj);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C2368pj.m;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        return (C2282oj) c(c1856jk, obj);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C2368pj) {
            return a((C2368pj) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C2282oj) c(c2712tk0);
    }
}
