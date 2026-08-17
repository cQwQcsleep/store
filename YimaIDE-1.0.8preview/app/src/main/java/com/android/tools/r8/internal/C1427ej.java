package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0007c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ej, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1427ej extends AbstractC0963Xr {
    public int g;
    public int h;
    public boolean i;
    public int j;
    public boolean k;
    public boolean l;
    public boolean m;
    public List n;
    public C2401q50 o;

    public C1427ej() {
        this.h = 0;
        this.j = 0;
        this.n = Collections.EMPTY_LIST;
    }

    public final C1427ej a(C1513fj c1513fj) {
        int i;
        if (c1513fj == C1513fj.o) {
            return this;
        }
        int i2 = 3;
        if ((c1513fj.f & 1) != 0) {
            int i3 = c1513fj.g;
            if (i3 == 0) {
                i = 1;
            } else if (i3 != 1) {
                i = i3 != 2 ? 0 : 3;
            } else {
                i = 2;
            }
            if (i == 0) {
                i = 1;
            }
            this.g |= 1;
            this.h = AbstractC0007c.b(i);
            p();
        }
        if (c1513fj.k()) {
            boolean z = c1513fj.h;
            this.g |= 2;
            this.i = z;
            p();
        }
        if ((c1513fj.f & 4) != 0) {
            int i4 = c1513fj.i;
            if (i4 == 0) {
                i2 = 1;
            } else if (i4 == 1) {
                i2 = 2;
            } else if (i4 != 2) {
                i2 = 0;
            }
            int i5 = i2 != 0 ? i2 : 1;
            this.g |= 4;
            this.j = AbstractC0007c.b(i5);
            p();
        }
        if ((c1513fj.f & 8) != 0) {
            boolean z2 = c1513fj.j;
            this.g |= 8;
            this.k = z2;
            p();
        }
        if ((c1513fj.f & 16) != 0) {
            boolean z3 = c1513fj.k;
            this.g |= 16;
            this.l = z3;
            p();
        }
        if ((c1513fj.f & 32) != 0) {
            boolean z4 = c1513fj.l;
            this.g |= 32;
            this.m = z4;
            p();
        }
        C2401q50 c2401q50 = this.o;
        List list = c1513fj.m;
        if (c2401q50 == null) {
            if (!list.isEmpty()) {
                if (this.n.isEmpty()) {
                    this.n = c1513fj.m;
                    this.g &= -65;
                } else {
                    if ((this.g & 64) == 0) {
                        this.n = new ArrayList(this.n);
                        this.g |= 64;
                    }
                    this.n.addAll(c1513fj.m);
                }
                p();
            }
        } else if (!list.isEmpty()) {
            boolean zIsEmpty = this.o.b.isEmpty();
            C2401q50 c2401q51 = this.o;
            if (zIsEmpty) {
                c2401q51.a = null;
                this.n = c1513fj.m;
                this.g &= -65;
                this.o = null;
            } else {
                c2401q51.a(c1513fj.m);
            }
        }
        a((AbstractC1102as) c1513fj);
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C1513fj) {
            return a((C1513fj) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C1513fj c1513fjI = i();
        if (c1513fjI.a()) {
            return c1513fjI;
        }
        throw H0.c(c1513fjI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C1427ej a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C1513fj c1513fj = null;
        try {
            try {
                a((C1513fj) C1513fj.p.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C1513fj c1513fj2 = (C1513fj) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c1513fj = c1513fj2;
                    if (c1513fj != null) {
                        a(c1513fj);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c1513fj != null) {
                a(c1513fj);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C1427ej) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.C;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C1513fj c1513fjI = i();
        if (c1513fjI.a()) {
            return c1513fjI;
        }
        throw H0.c(c1513fjI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.D.a(C1513fj.class, C1427ej.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C1513fj i() {
        C1513fj c1513fj = new C1513fj(this);
        int i = this.g;
        int i2 = (i & 1) != 0 ? 1 : 0;
        c1513fj.g = this.h;
        if ((i & 2) != 0) {
            c1513fj.h = this.i;
            i2 |= 2;
        }
        if ((i & 4) != 0) {
            i2 |= 4;
        }
        c1513fj.i = this.j;
        if ((i & 8) != 0) {
            c1513fj.j = this.k;
            i2 |= 8;
        }
        if ((i & 16) != 0) {
            c1513fj.k = this.l;
            i2 |= 16;
        }
        if ((i & 32) != 0) {
            c1513fj.l = this.m;
            i2 |= 32;
        }
        C2401q50 c2401q50 = this.o;
        if (c2401q50 == null) {
            if ((i & 64) != 0) {
                this.n = Collections.unmodifiableList(this.n);
                this.g &= -65;
            }
            c1513fj.m = this.n;
        } else {
            c1513fj.m = c2401q50.b();
        }
        c1513fj.f = i2;
        o();
        return c1513fj;
    }

    public C1427ej(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.h = 0;
        this.j = 0;
        this.n = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        return (C1427ej) d(c1856jk, obj);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C1513fj.o;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        return (C1427ej) c(c1856jk, obj);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof C1513fj) {
            return a((C1513fj) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C1427ej) c(c2712tk0);
    }
}
