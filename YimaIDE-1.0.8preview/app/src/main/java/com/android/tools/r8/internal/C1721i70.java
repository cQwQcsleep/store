package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.i70, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1721i70 extends AbstractC0911Vr {
    public int f;
    public C2062m70 g;
    public Object h;
    public List i;
    public C2401q50 j;

    public C1721i70() {
        super(null);
        this.h = XmlPullParser.NO_NAMESPACE;
        this.i = Collections.EMPTY_LIST;
        C1805j70 c1805j70 = C1805j70.i;
    }

    public final C1721i70 a(C1805j70 c1805j70) {
        if (c1805j70 == C1805j70.i) {
            return this;
        }
        if (c1805j70.e != null) {
            C2062m70 c2062m70K = c1805j70.k();
            C2062m70 c2062m70 = this.g;
            if (c2062m70 != null) {
                this.g = C2062m70.g.d().a(c2062m70).a(c2062m70K).i();
            } else {
                this.g = c2062m70K;
            }
            p();
        }
        if (!c1805j70.l().isEmpty()) {
            this.h = c1805j70.f;
            p();
        }
        C2401q50 c2401q50 = this.j;
        List list = c1805j70.g;
        if (c2401q50 == null) {
            if (!list.isEmpty()) {
                if (this.i.isEmpty()) {
                    this.i = c1805j70.g;
                    this.f &= -2;
                } else {
                    if ((this.f & 1) == 0) {
                        this.i = new ArrayList(this.i);
                        this.f |= 1;
                    }
                    this.i.addAll(c1805j70.g);
                }
                p();
            }
        } else if (!list.isEmpty()) {
            boolean zIsEmpty = this.j.b.isEmpty();
            C2401q50 c2401q51 = this.j;
            if (zIsEmpty) {
                c2401q51.a = null;
                this.i = c1805j70.g;
                this.f &= -2;
                this.j = null;
            } else {
                c2401q51.a(c1805j70.g);
            }
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.p.a(C1805j70.class, C1721i70.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C1805j70 c1805j70I = i();
        if (c1805j70I.a()) {
            return c1805j70I;
        }
        throw H0.c(c1805j70I);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C1721i70 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C1805j70 c1805j70 = null;
        try {
            try {
                a((C1805j70) C1805j70.j.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C1805j70 c1805j71 = (C1805j70) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c1805j70 = c1805j71;
                    if (c1805j70 != null) {
                        a(c1805j70);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c1805j70 != null) {
                a(c1805j70);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C1721i70) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.o;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C1805j70 c1805j70I = i();
        if (c1805j70I.a()) {
            return c1805j70I;
        }
        throw H0.c(c1805j70I);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.p.a(C1805j70.class, C1721i70.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C1805j70 i() {
        C1805j70 c1805j70 = new C1805j70(this);
        c1805j70.e = this.g;
        c1805j70.f = this.h;
        C2401q50 c2401q50 = this.j;
        if (c2401q50 == null) {
            if ((this.f & 1) != 0) {
                this.i = Collections.unmodifiableList(this.i);
                this.f &= -2;
            }
            c1805j70.g = this.i;
        } else {
            c1805j70.g = c2401q50.b();
        }
        o();
        return c1805j70;
    }

    public C1721i70(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.h = XmlPullParser.NO_NAMESPACE;
        this.i = Collections.EMPTY_LIST;
        C1805j70 c1805j70 = C1805j70.i;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C1805j70) {
            return a((C1805j70) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C1805j70.i;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.p.a(C1805j70.class, C1721i70.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C1805j70) {
            return a((C1805j70) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C1721i70) c(c2712tk0);
    }
}
