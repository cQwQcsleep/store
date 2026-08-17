package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Hi, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0539Hi extends AbstractC0911Vr {
    public int f;
    public Object g;
    public List h;
    public C2401q50 i;
    public C0720Oi j;
    public List k;
    public C2401q50 l;
    public AJ m;

    public C0539Hi() {
        super(null);
        this.g = XmlPullParser.NO_NAMESPACE;
        List list = Collections.EMPTY_LIST;
        this.h = list;
        this.k = list;
        this.m = C3101yJ.d;
    }

    public final C0539Hi a(C0643Li c0643Li) {
        C0720Oi c0720Oi;
        C0720Oi c0720Oi2;
        if (c0643Li == C0643Li.l) {
            return this;
        }
        if ((c0643Li.e & 1) != 0) {
            this.f |= 1;
            this.g = c0643Li.f;
            p();
        }
        C2401q50 c2401q50 = this.i;
        List list = c0643Li.g;
        if (c2401q50 == null) {
            if (!list.isEmpty()) {
                if (this.h.isEmpty()) {
                    this.h = c0643Li.g;
                    this.f &= -3;
                } else {
                    if ((this.f & 2) == 0) {
                        this.h = new ArrayList(this.h);
                        this.f |= 2;
                    }
                    this.h.addAll(c0643Li.g);
                }
                p();
            }
        } else if (!list.isEmpty()) {
            boolean zIsEmpty = this.i.b.isEmpty();
            C2401q50 c2401q51 = this.i;
            if (zIsEmpty) {
                c2401q51.a = null;
                this.h = c0643Li.g;
                this.f &= -3;
                this.i = null;
            } else {
                c2401q51.a(c0643Li.g);
            }
        }
        if (c0643Li.m()) {
            C0720Oi c0720OiL = c0643Li.l();
            if ((this.f & 4) == 0 || (c0720Oi = this.j) == null || c0720Oi == (c0720Oi2 = C0720Oi.k)) {
                this.j = c0720OiL;
            } else {
                this.j = c0720Oi2.d().a(c0720Oi).a(c0720OiL).i();
            }
            p();
            this.f |= 4;
        }
        C2401q50 c2401q52 = this.l;
        List list2 = c0643Li.i;
        if (c2401q52 == null) {
            if (!list2.isEmpty()) {
                if (this.k.isEmpty()) {
                    this.k = c0643Li.i;
                    this.f &= -9;
                } else {
                    if ((this.f & 8) == 0) {
                        this.k = new ArrayList(this.k);
                        this.f |= 8;
                    }
                    this.k.addAll(c0643Li.i);
                }
                p();
            }
        } else if (!list2.isEmpty()) {
            boolean zIsEmpty2 = this.l.b.isEmpty();
            C2401q50 c2401q53 = this.l;
            if (zIsEmpty2) {
                c2401q53.a = null;
                this.k = c0643Li.i;
                this.f &= -9;
                this.l = null;
            } else {
                c2401q53.a(c0643Li.i);
            }
        }
        if (!c0643Li.j.isEmpty()) {
            if (this.m.isEmpty()) {
                this.m = c0643Li.j;
                this.f &= -17;
            } else {
                if ((this.f & 16) == 0) {
                    this.m = new C3101yJ(this.m);
                    this.f |= 16;
                }
                this.m.addAll(c0643Li.j);
            }
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C0643Li) {
            return a((C0643Li) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C0643Li c0643LiI = i();
        if (c0643LiI.a()) {
            return c0643LiI;
        }
        throw H0.c(c0643LiI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C0539Hi a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C0643Li c0643Li = null;
        try {
            try {
                a((C0643Li) C0643Li.m.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C0643Li c0643Li2 = (C0643Li) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c0643Li = c0643Li2;
                    if (c0643Li != null) {
                        a(c0643Li);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c0643Li != null) {
                a(c0643Li);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C0539Hi) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.o;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C0643Li c0643LiI = i();
        if (c0643LiI.a()) {
            return c0643LiI;
        }
        throw H0.c(c0643LiI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.p.a(C0643Li.class, C0539Hi.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C0643Li i() {
        C0643Li c0643Li = new C0643Li(this);
        int i = this.f;
        int i2 = (i & 1) != 0 ? 1 : 0;
        c0643Li.f = this.g;
        C2401q50 c2401q50 = this.i;
        if (c2401q50 == null) {
            if ((this.f & 2) != 0) {
                this.h = Collections.unmodifiableList(this.h);
                this.f &= -3;
            }
            c0643Li.g = this.h;
        } else {
            c0643Li.g = c2401q50.b();
        }
        if ((i & 4) != 0) {
            c0643Li.h = this.j;
            i2 |= 2;
        }
        C2401q50 c2401q51 = this.l;
        if (c2401q51 == null) {
            if ((this.f & 8) != 0) {
                this.k = Collections.unmodifiableList(this.k);
                this.f &= -9;
            }
            c0643Li.i = this.k;
        } else {
            c0643Li.i = c2401q51.b();
        }
        if ((this.f & 16) != 0) {
            this.m = this.m.f();
            this.f &= -17;
        }
        c0643Li.j = this.m;
        c0643Li.e = i2;
        o();
        return c0643Li;
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

    public C0539Hi(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.g = XmlPullParser.NO_NAMESPACE;
        List list = Collections.EMPTY_LIST;
        this.h = list;
        this.k = list;
        this.m = C3101yJ.d;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C0643Li.l;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C0643Li) {
            return a((C0643Li) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C0539Hi) c(c2712tk0);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(n(), c1856jk).a(this, obj);
        return this;
    }
}
