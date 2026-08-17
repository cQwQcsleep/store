package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0007c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.uj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2794uj extends AbstractC0963Xr {
    public int g;
    public boolean h;
    public int i;
    public List j;
    public C2401q50 k;

    public C2794uj() {
        this.i = 0;
        this.j = Collections.EMPTY_LIST;
    }

    public final C2794uj a(C2880vj c2880vj) {
        int i;
        if (c2880vj == C2880vj.k) {
            return this;
        }
        if ((c2880vj.f & 1) != 0) {
            boolean z = c2880vj.g;
            this.g |= 1;
            this.h = z;
            p();
        }
        if ((c2880vj.f & 2) != 0) {
            int i2 = c2880vj.h;
            if (i2 == 0) {
                i = 1;
            } else if (i2 != 1) {
                i = i2 != 2 ? 0 : 3;
            } else {
                i = 2;
            }
            int i3 = i != 0 ? i : 1;
            this.g |= 2;
            this.i = AbstractC0007c.b(i3);
            p();
        }
        C2401q50 c2401q50 = this.k;
        List list = c2880vj.i;
        if (c2401q50 == null) {
            if (!list.isEmpty()) {
                if (this.j.isEmpty()) {
                    this.j = c2880vj.i;
                    this.g &= -5;
                } else {
                    if ((this.g & 4) == 0) {
                        this.j = new ArrayList(this.j);
                        this.g |= 4;
                    }
                    this.j.addAll(c2880vj.i);
                }
                p();
            }
        } else if (!list.isEmpty()) {
            boolean zIsEmpty = this.k.b.isEmpty();
            C2401q50 c2401q51 = this.k;
            if (zIsEmpty) {
                c2401q51.a = null;
                this.j = c2880vj.i;
                this.g &= -5;
                this.k = null;
            } else {
                c2401q51.a(c2880vj.i);
            }
        }
        a((AbstractC1102as) c2880vj);
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C2880vj) {
            return a((C2880vj) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C2880vj c2880vjI = i();
        if (c2880vjI.a()) {
            return c2880vjI;
        }
        throw H0.c(c2880vjI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C2794uj a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C2880vj c2880vj = null;
        try {
            try {
                a((C2880vj) C2880vj.l.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C2880vj c2880vj2 = (C2880vj) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c2880vj = c2880vj2;
                    if (c2880vj != null) {
                        a(c2880vj);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c2880vj != null) {
                a(c2880vj);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C2794uj) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.M;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C2880vj c2880vjI = i();
        if (c2880vjI.a()) {
            return c2880vjI;
        }
        throw H0.c(c2880vjI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.N.a(C2880vj.class, C2794uj.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C2880vj i() {
        int i;
        C2880vj c2880vj = new C2880vj(this);
        int i2 = this.g;
        if ((i2 & 1) != 0) {
            c2880vj.g = this.h;
            i = 1;
        } else {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            i |= 2;
        }
        c2880vj.h = this.i;
        C2401q50 c2401q50 = this.k;
        if (c2401q50 == null) {
            if ((i2 & 4) != 0) {
                this.j = Collections.unmodifiableList(this.j);
                this.g &= -5;
            }
            c2880vj.i = this.j;
        } else {
            c2880vj.i = c2401q50.b();
        }
        c2880vj.f = i;
        o();
        return c2880vj;
    }

    public C2794uj(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.i = 0;
        this.j = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        return (C2794uj) d(c1856jk, obj);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C2880vj.k;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        return (C2794uj) c(c1856jk, obj);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof C2880vj) {
            return a((C2880vj) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C2794uj) c(c2712tk0);
    }
}
