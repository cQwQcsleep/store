package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L70 extends AbstractC0911Vr {
    public int f;
    public C1128b80 g;
    public List h;
    public C2401q50 i;
    public List j;
    public C2401q50 k;
    public List l;
    public C2401q50 m;
    public List n;
    public C2401q50 o;

    public L70() {
        super(null);
        List list = Collections.EMPTY_LIST;
        this.h = list;
        this.j = list;
        this.l = list;
        this.n = list;
        M70 m70 = M70.k;
    }

    public final L70 a(M70 m70) {
        if (m70 == M70.k) {
            return this;
        }
        if (m70.e != null) {
            C1128b80 c1128b80K = m70.k();
            C1128b80 c1128b80 = this.g;
            if (c1128b80 != null) {
                C1042a80 c1042a80A = C1128b80.g.d().a(c1128b80).a(c1128b80K);
                C1128b80 c1128b81 = new C1128b80(c1042a80A);
                c1128b81.e = c1042a80A.f;
                c1042a80A.o();
                this.g = c1128b81;
            } else {
                this.g = c1128b80K;
            }
            p();
        }
        C2401q50 c2401q50 = this.i;
        List list = m70.f;
        if (c2401q50 == null) {
            if (!list.isEmpty()) {
                if (this.h.isEmpty()) {
                    this.h = m70.f;
                    this.f &= -2;
                } else {
                    if ((this.f & 1) == 0) {
                        this.h = new ArrayList(this.h);
                        this.f |= 1;
                    }
                    this.h.addAll(m70.f);
                }
                p();
            }
        } else if (!list.isEmpty()) {
            boolean zIsEmpty = this.i.b.isEmpty();
            C2401q50 c2401q51 = this.i;
            if (zIsEmpty) {
                c2401q51.a = null;
                this.h = m70.f;
                this.f &= -2;
                this.i = null;
            } else {
                c2401q51.a(m70.f);
            }
        }
        C2401q50 c2401q52 = this.k;
        List list2 = m70.g;
        if (c2401q52 == null) {
            if (!list2.isEmpty()) {
                if (this.j.isEmpty()) {
                    this.j = m70.g;
                    this.f &= -3;
                } else {
                    if ((this.f & 2) == 0) {
                        this.j = new ArrayList(this.j);
                        this.f |= 2;
                    }
                    this.j.addAll(m70.g);
                }
                p();
            }
        } else if (!list2.isEmpty()) {
            boolean zIsEmpty2 = this.k.b.isEmpty();
            C2401q50 c2401q53 = this.k;
            if (zIsEmpty2) {
                c2401q53.a = null;
                this.j = m70.g;
                this.f &= -3;
                this.k = null;
            } else {
                c2401q53.a(m70.g);
            }
        }
        C2401q50 c2401q54 = this.m;
        List list3 = m70.h;
        if (c2401q54 == null) {
            if (!list3.isEmpty()) {
                if (this.l.isEmpty()) {
                    this.l = m70.h;
                    this.f &= -5;
                } else {
                    if ((this.f & 4) == 0) {
                        this.l = new ArrayList(this.l);
                        this.f |= 4;
                    }
                    this.l.addAll(m70.h);
                }
                p();
            }
        } else if (!list3.isEmpty()) {
            boolean zIsEmpty3 = this.m.b.isEmpty();
            C2401q50 c2401q55 = this.m;
            if (zIsEmpty3) {
                c2401q55.a = null;
                this.l = m70.h;
                this.f &= -5;
                this.m = null;
            } else {
                c2401q55.a(m70.h);
            }
        }
        C2401q50 c2401q56 = this.o;
        List list4 = m70.i;
        if (c2401q56 == null) {
            if (!list4.isEmpty()) {
                if (this.n.isEmpty()) {
                    this.n = m70.i;
                    this.f &= -9;
                } else {
                    if ((this.f & 8) == 0) {
                        this.n = new ArrayList(this.n);
                        this.f |= 8;
                    }
                    this.n.addAll(m70.i);
                }
                p();
            }
        } else if (!list4.isEmpty()) {
            boolean zIsEmpty4 = this.o.b.isEmpty();
            C2401q50 c2401q57 = this.o;
            if (zIsEmpty4) {
                c2401q57.a = null;
                this.n = m70.i;
                this.f &= -9;
                this.o = null;
            } else {
                c2401q57.a(m70.i);
            }
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.l.a(M70.class, L70.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        M70 m70I = i();
        if (m70I.a()) {
            return m70I;
        }
        throw H0.c(m70I);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final L70 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        M70 m70 = null;
        try {
            try {
                a((M70) M70.l.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                M70 m71 = (M70) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    m70 = m71;
                    if (m70 != null) {
                        a(m70);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (m70 != null) {
                a(m70);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (L70) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.k;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        M70 m70I = i();
        if (m70I.a()) {
            return m70I;
        }
        throw H0.c(m70I);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.l.a(M70.class, L70.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final M70 i() {
        M70 m70 = new M70(this);
        int i = this.f;
        m70.e = this.g;
        C2401q50 c2401q50 = this.i;
        if (c2401q50 == null) {
            if ((i & 1) != 0) {
                this.h = Collections.unmodifiableList(this.h);
                this.f &= -2;
            }
            m70.f = this.h;
        } else {
            m70.f = c2401q50.b();
        }
        C2401q50 c2401q51 = this.k;
        if (c2401q51 == null) {
            if ((this.f & 2) != 0) {
                this.j = Collections.unmodifiableList(this.j);
                this.f &= -3;
            }
            m70.g = this.j;
        } else {
            m70.g = c2401q51.b();
        }
        C2401q50 c2401q52 = this.m;
        if (c2401q52 == null) {
            if ((this.f & 4) != 0) {
                this.l = Collections.unmodifiableList(this.l);
                this.f &= -5;
            }
            m70.h = this.l;
        } else {
            m70.h = c2401q52.b();
        }
        C2401q50 c2401q53 = this.o;
        if (c2401q53 == null) {
            if ((this.f & 8) != 0) {
                this.n = Collections.unmodifiableList(this.n);
                this.f &= -9;
            }
            m70.i = this.n;
        } else {
            m70.i = c2401q53.b();
        }
        o();
        return m70;
    }

    public L70(C0859Tr c0859Tr) {
        super(c0859Tr);
        List list = Collections.EMPTY_LIST;
        this.h = list;
        this.j = list;
        this.l = list;
        this.n = list;
        M70 m70 = M70.k;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof M70) {
            return a((M70) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return M70.k;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.l.a(M70.class, L70.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof M70) {
            return a((M70) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (L70) c(c2712tk0);
    }
}
