package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class D60 extends AbstractC0911Vr {
    public int f;
    public H60 g;
    public Object h;
    public S80 i;
    public C1208c60 j;
    public C1550g70 k;
    public List l;
    public C2401q50 m;
    public V70 n;

    public D60() {
        super(null);
        this.h = XmlPullParser.NO_NAMESPACE;
        this.l = Collections.EMPTY_LIST;
        E60 e60 = E60.m;
    }

    public final D60 a(E60 e60) {
        if (e60 == E60.m) {
            return this;
        }
        if (e60.e != null) {
            H60 h60L = e60.l();
            H60 h60 = this.g;
            if (h60 != null) {
                G60 g60A = H60.g.d().a(h60).a(h60L);
                H60 h61 = new H60(g60A);
                h61.e = g60A.f;
                g60A.o();
                this.g = h61;
            } else {
                this.g = h60L;
            }
            p();
        }
        if (!e60.m().isEmpty()) {
            this.h = e60.f;
            p();
        }
        if (e60.g != null) {
            S80 s80P = e60.p();
            S80 s80 = this.i;
            if (s80 != null) {
                this.i = S80.j.d().a(s80).a(s80P).i();
            } else {
                this.i = s80P;
            }
            p();
        }
        if (e60.h != null) {
            C1208c60 c1208c60K = e60.k();
            C1208c60 c1208c60 = this.j;
            if (c1208c60 != null) {
                C1124b60 c1124b60A = C1208c60.h.d().a(c1208c60).a(c1208c60K);
                C1208c60 c1208c61 = new C1208c60(c1124b60A);
                c1208c61.e = c1124b60A.f;
                c1208c61.f = c1124b60A.g;
                c1124b60A.o();
                this.j = c1208c61;
            } else {
                this.j = c1208c60K;
            }
            p();
        }
        if (e60.q()) {
            C1550g70 c1550g70N = e60.n();
            C1550g70 c1550g70 = this.k;
            if (c1550g70 != null) {
                this.k = C1550g70.l.d().a(c1550g70).a(c1550g70N).i();
            } else {
                this.k = c1550g70N;
            }
            p();
        }
        C2401q50 c2401q50 = this.m;
        List list = e60.j;
        if (c2401q50 == null) {
            if (!list.isEmpty()) {
                if (this.l.isEmpty()) {
                    this.l = e60.j;
                    this.f &= -2;
                } else {
                    if ((this.f & 1) == 0) {
                        this.l = new ArrayList(this.l);
                        this.f |= 1;
                    }
                    this.l.addAll(e60.j);
                }
                p();
            }
        } else if (!list.isEmpty()) {
            boolean zIsEmpty = this.m.b.isEmpty();
            C2401q50 c2401q51 = this.m;
            if (zIsEmpty) {
                c2401q51.a = null;
                this.l = e60.j;
                this.f &= -2;
                this.m = null;
            } else {
                c2401q51.a(e60.j);
            }
        }
        if (e60.k != null) {
            V70 v70O = e60.o();
            V70 v70 = this.n;
            if (v70 != null) {
                U70 u70A = V70.h.d().a(v70).a(v70O);
                V70 v71 = new V70(u70A);
                v71.e = u70A.f;
                v71.f = u70A.g;
                u70A.o();
                this.n = v71;
            } else {
                this.n = v70O;
            }
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.H.a(E60.class, D60.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        E60 e60I = i();
        if (e60I.a()) {
            return e60I;
        }
        throw H0.c(e60I);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final D60 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        E60 e60 = null;
        try {
            try {
                a((E60) E60.n.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                E60 e61 = (E60) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    e60 = e61;
                    if (e60 != null) {
                        a(e60);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (e60 != null) {
                a(e60);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (D60) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.G;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        E60 e60I = i();
        if (e60I.a()) {
            return e60I;
        }
        throw H0.c(e60I);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.H.a(E60.class, D60.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final E60 i() {
        E60 e60 = new E60(this);
        e60.e = this.g;
        e60.f = this.h;
        e60.g = this.i;
        e60.h = this.j;
        e60.i = this.k;
        C2401q50 c2401q50 = this.m;
        if (c2401q50 == null) {
            if ((this.f & 1) != 0) {
                this.l = Collections.unmodifiableList(this.l);
                this.f &= -2;
            }
            e60.j = this.l;
        } else {
            e60.j = c2401q50.b();
        }
        e60.k = this.n;
        o();
        return e60;
    }

    public D60(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.h = XmlPullParser.NO_NAMESPACE;
        this.l = Collections.EMPTY_LIST;
        E60 e60 = E60.m;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof E60) {
            return a((E60) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return E60.m;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.H.a(E60.class, D60.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof E60) {
            return a((E60) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (D60) c(c2712tk0);
    }
}
