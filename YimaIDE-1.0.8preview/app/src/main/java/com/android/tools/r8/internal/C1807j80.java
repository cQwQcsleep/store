package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.j80, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1807j80 extends AbstractC0911Vr {
    public int f;
    public Object g;
    public List h;
    public C2401q50 i;

    public C1807j80() {
        super(null);
        this.g = XmlPullParser.NO_NAMESPACE;
        this.h = Collections.EMPTY_LIST;
        C2149n80 c2149n80 = C2149n80.h;
    }

    public final C1807j80 a(C2149n80 c2149n80) {
        String strC;
        if (c2149n80 == C2149n80.h) {
            return this;
        }
        Object obj = c2149n80.e;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            c2149n80.e = strC;
        }
        if (!strC.isEmpty()) {
            this.g = c2149n80.e;
            p();
        }
        C2401q50 c2401q50 = this.i;
        List list = c2149n80.f;
        if (c2401q50 == null) {
            if (!list.isEmpty()) {
                if (this.h.isEmpty()) {
                    this.h = c2149n80.f;
                    this.f &= -2;
                } else {
                    if ((this.f & 1) == 0) {
                        this.h = new ArrayList(this.h);
                        this.f |= 1;
                    }
                    this.h.addAll(c2149n80.f);
                }
                p();
            }
        } else if (!list.isEmpty()) {
            boolean zIsEmpty = this.i.b.isEmpty();
            C2401q50 c2401q51 = this.i;
            if (zIsEmpty) {
                c2401q51.a = null;
                this.h = c2149n80.f;
                this.f &= -2;
                this.i = null;
            } else {
                c2401q51.a(c2149n80.f);
            }
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.T0.a(C2149n80.class, C1807j80.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C2149n80 c2149n80I = i();
        if (c2149n80I.a()) {
            return c2149n80I;
        }
        throw H0.c(c2149n80I);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C1807j80 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C2149n80 c2149n80 = null;
        try {
            try {
                a((C2149n80) C2149n80.i.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C2149n80 c2149n81 = (C2149n80) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c2149n80 = c2149n81;
                    if (c2149n80 != null) {
                        a(c2149n80);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c2149n80 != null) {
                a(c2149n80);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C1807j80) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.S0;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C2149n80 c2149n80I = i();
        if (c2149n80I.a()) {
            return c2149n80I;
        }
        throw H0.c(c2149n80I);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.T0.a(C2149n80.class, C1807j80.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C2149n80 i() {
        C2149n80 c2149n80 = new C2149n80(this);
        c2149n80.e = this.g;
        C2401q50 c2401q50 = this.i;
        if (c2401q50 == null) {
            if ((this.f & 1) != 0) {
                this.h = Collections.unmodifiableList(this.h);
                this.f &= -2;
            }
            c2149n80.f = this.h;
        } else {
            c2149n80.f = c2401q50.b();
        }
        o();
        return c2149n80;
    }

    public C1807j80(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.g = XmlPullParser.NO_NAMESPACE;
        this.h = Collections.EMPTY_LIST;
        C2149n80 c2149n80 = C2149n80.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C2149n80) {
            return a((C2149n80) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C2149n80.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.T0.a(C2149n80.class, C1807j80.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C2149n80) {
            return a((C2149n80) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C1807j80) c(c2712tk0);
    }
}
