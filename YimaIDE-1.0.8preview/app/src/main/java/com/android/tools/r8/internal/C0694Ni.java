package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ni, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0694Ni extends AbstractC0963Xr {
    public int g;
    public boolean h;
    public boolean i;
    public List j;
    public C2401q50 k;

    public C0694Ni() {
        this.j = Collections.EMPTY_LIST;
    }

    public final C0694Ni a(C0720Oi c0720Oi) {
        if (c0720Oi == C0720Oi.k) {
            return this;
        }
        if ((c0720Oi.f & 1) != 0) {
            boolean z = c0720Oi.g;
            this.g |= 1;
            this.h = z;
            p();
        }
        if ((c0720Oi.f & 2) != 0) {
            boolean z2 = c0720Oi.h;
            this.g |= 2;
            this.i = z2;
            p();
        }
        C2401q50 c2401q50 = this.k;
        List list = c0720Oi.i;
        if (c2401q50 == null) {
            if (!list.isEmpty()) {
                if (this.j.isEmpty()) {
                    this.j = c0720Oi.i;
                    this.g &= -5;
                } else {
                    if ((this.g & 4) == 0) {
                        this.j = new ArrayList(this.j);
                        this.g |= 4;
                    }
                    this.j.addAll(c0720Oi.i);
                }
                p();
            }
        } else if (!list.isEmpty()) {
            boolean zIsEmpty = this.k.b.isEmpty();
            C2401q50 c2401q51 = this.k;
            if (zIsEmpty) {
                c2401q51.a = null;
                this.j = c0720Oi.i;
                this.g &= -5;
                this.k = null;
            } else {
                c2401q51.a(c0720Oi.i);
            }
        }
        a((AbstractC1102as) c0720Oi);
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C0720Oi) {
            return a((C0720Oi) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C0720Oi c0720OiI = i();
        if (c0720OiI.a()) {
            return c0720OiI;
        }
        throw H0.c(c0720OiI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C0694Ni a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C0720Oi c0720Oi = null;
        try {
            try {
                a((C0720Oi) C0720Oi.l.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C0720Oi c0720Oi2 = (C0720Oi) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c0720Oi = c0720Oi2;
                    if (c0720Oi != null) {
                        a(c0720Oi);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c0720Oi != null) {
                a(c0720Oi);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C0694Ni) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.G;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C0720Oi c0720OiI = i();
        if (c0720OiI.a()) {
            return c0720OiI;
        }
        throw H0.c(c0720OiI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.H.a(C0720Oi.class, C0694Ni.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C0720Oi i() {
        int i;
        C0720Oi c0720Oi = new C0720Oi(this);
        int i2 = this.g;
        if ((i2 & 1) != 0) {
            c0720Oi.g = this.h;
            i = 1;
        } else {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            c0720Oi.h = this.i;
            i |= 2;
        }
        C2401q50 c2401q50 = this.k;
        if (c2401q50 == null) {
            if ((i2 & 4) != 0) {
                this.j = Collections.unmodifiableList(this.j);
                this.g &= -5;
            }
            c0720Oi.i = this.j;
        } else {
            c0720Oi.i = c2401q50.b();
        }
        c0720Oi.f = i;
        o();
        return c0720Oi;
    }

    public C0694Ni(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.j = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        return (C0694Ni) d(c1856jk, obj);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C0720Oi.k;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        return (C0694Ni) c(c1856jk, obj);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof C0720Oi) {
            return a((C0720Oi) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C0694Ni) c(c2712tk0);
    }
}
