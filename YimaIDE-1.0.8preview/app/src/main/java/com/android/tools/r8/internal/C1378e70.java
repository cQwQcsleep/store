package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.e70, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1378e70 extends AbstractC0911Vr {
    public int f;
    public P70 g;
    public Object h;
    public List i;
    public int j;

    public C1378e70() {
        super(null);
        this.h = XmlPullParser.NO_NAMESPACE;
        this.i = Collections.EMPTY_LIST;
        C1210c70 c1210c70 = C1550g70.k;
    }

    public final C1378e70 a(C1550g70 c1550g70) {
        String strC;
        if (c1550g70 == C1550g70.l) {
            return this;
        }
        if (c1550g70.e != null) {
            P70 p70K = c1550g70.k();
            P70 p70 = this.g;
            if (p70 != null) {
                this.g = P70.h.d().a(p70).a(p70K).i();
            } else {
                this.g = p70K;
            }
            p();
        }
        Object obj = c1550g70.f;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            c1550g70.f = strC;
        }
        if (!strC.isEmpty()) {
            this.h = c1550g70.f;
            p();
        }
        if (!c1550g70.g.isEmpty()) {
            if (this.i.isEmpty()) {
                this.i = c1550g70.g;
                this.f &= -2;
            } else {
                if ((this.f & 1) == 0) {
                    this.i = new ArrayList(this.i);
                    this.f |= 1;
                }
                this.i.addAll(c1550g70.g);
            }
            p();
        }
        int i = c1550g70.i;
        if (i != 0) {
            this.j = i;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.B.a(C1550g70.class, C1378e70.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C1550g70 c1550g70I = i();
        if (c1550g70I.a()) {
            return c1550g70I;
        }
        throw H0.c(c1550g70I);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C1378e70 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C1550g70 c1550g70 = null;
        try {
            try {
                a((C1550g70) C1550g70.m.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C1550g70 c1550g71 = (C1550g70) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c1550g70 = c1550g71;
                    if (c1550g70 != null) {
                        a(c1550g70);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c1550g70 != null) {
                a(c1550g70);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C1378e70) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.A;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C1550g70 c1550g70I = i();
        if (c1550g70I.a()) {
            return c1550g70I;
        }
        throw H0.c(c1550g70I);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.B.a(C1550g70.class, C1378e70.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C1550g70 i() {
        C1550g70 c1550g70 = new C1550g70(this);
        c1550g70.e = this.g;
        c1550g70.f = this.h;
        if ((this.f & 1) != 0) {
            this.i = Collections.unmodifiableList(this.i);
            this.f &= -2;
        }
        c1550g70.g = this.i;
        c1550g70.i = this.j;
        o();
        return c1550g70;
    }

    public C1378e70(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.h = XmlPullParser.NO_NAMESPACE;
        this.i = Collections.EMPTY_LIST;
        C1210c70 c1210c70 = C1550g70.k;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C1550g70) {
            return a((C1550g70) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C1550g70.l;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.B.a(C1550g70.class, C1378e70.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C1550g70) {
            return a((C1550g70) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C1378e70) c(c2712tk0);
    }
}
