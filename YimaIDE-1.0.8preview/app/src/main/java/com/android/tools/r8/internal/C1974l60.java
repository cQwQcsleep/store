package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.l60, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1974l60 extends AbstractC0911Vr {
    public int f;
    public int g;
    public int h;
    public int i;
    public List j;
    public C2401q50 k;

    public C1974l60() {
        super(null);
        this.j = Collections.EMPTY_LIST;
        C2317p60 c2317p60 = C2317p60.j;
    }

    public final C1974l60 a(C2317p60 c2317p60) {
        if (c2317p60 == C2317p60.j) {
            return this;
        }
        int i = c2317p60.e;
        if (i != 0) {
            this.g = i;
            p();
        }
        int i2 = c2317p60.f;
        if (i2 != 0) {
            this.h = i2;
            p();
        }
        int i3 = c2317p60.g;
        if (i3 != 0) {
            this.i = i3;
            p();
        }
        C2401q50 c2401q50 = this.k;
        List list = c2317p60.h;
        if (c2401q50 == null) {
            if (!list.isEmpty()) {
                if (this.j.isEmpty()) {
                    this.j = c2317p60.h;
                    this.f &= -2;
                } else {
                    if ((this.f & 1) == 0) {
                        this.j = new ArrayList(this.j);
                        this.f |= 1;
                    }
                    this.j.addAll(c2317p60.h);
                }
                p();
            }
        } else if (!list.isEmpty()) {
            boolean zIsEmpty = this.k.b.isEmpty();
            C2401q50 c2401q51 = this.k;
            if (zIsEmpty) {
                c2401q51.a = null;
                this.j = c2317p60.h;
                this.f &= -2;
                this.k = null;
            } else {
                c2401q51.a(c2317p60.h);
            }
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.n0.a(C2317p60.class, C1974l60.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C2317p60 c2317p60I = i();
        if (c2317p60I.a()) {
            return c2317p60I;
        }
        throw H0.c(c2317p60I);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C1974l60 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C2317p60 c2317p60 = null;
        try {
            try {
                a((C2317p60) C2317p60.k.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C2317p60 c2317p61 = (C2317p60) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c2317p60 = c2317p61;
                    if (c2317p60 != null) {
                        a(c2317p60);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c2317p60 != null) {
                a(c2317p60);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C1974l60) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.m0;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C2317p60 c2317p60I = i();
        if (c2317p60I.a()) {
            return c2317p60I;
        }
        throw H0.c(c2317p60I);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.n0.a(C2317p60.class, C1974l60.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C2317p60 i() {
        C2317p60 c2317p60 = new C2317p60(this);
        int i = this.f;
        c2317p60.e = this.g;
        c2317p60.f = this.h;
        c2317p60.g = this.i;
        C2401q50 c2401q50 = this.k;
        if (c2401q50 == null) {
            if ((i & 1) != 0) {
                this.j = Collections.unmodifiableList(this.j);
                this.f &= -2;
            }
            c2317p60.h = this.j;
        } else {
            c2317p60.h = c2401q50.b();
        }
        o();
        return c2317p60;
    }

    public C1974l60(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.j = Collections.EMPTY_LIST;
        C2317p60 c2317p60 = C2317p60.j;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C2317p60) {
            return a((C2317p60) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C2317p60.j;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.n0.a(C2317p60.class, C1974l60.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof C2317p60) {
            return a((C2317p60) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C1974l60) c(c2712tk0);
    }
}
