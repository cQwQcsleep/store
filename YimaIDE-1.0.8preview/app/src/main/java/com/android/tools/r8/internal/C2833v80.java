package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.v80, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2833v80 extends AbstractC0911Vr {
    public int f;
    public Object g;
    public List h;
    public C2401q50 i;

    public C2833v80() {
        super(null);
        this.g = XmlPullParser.NO_NAMESPACE;
        this.h = Collections.EMPTY_LIST;
        C3174z80 c3174z80 = C3174z80.h;
    }

    public final C2833v80 a(C3174z80 c3174z80) {
        String strC;
        if (c3174z80 == C3174z80.h) {
            return this;
        }
        Object obj = c3174z80.e;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            c3174z80.e = strC;
        }
        if (!strC.isEmpty()) {
            this.g = c3174z80.e;
            p();
        }
        C2401q50 c2401q50 = this.i;
        List list = c3174z80.f;
        if (c2401q50 == null) {
            if (!list.isEmpty()) {
                if (this.h.isEmpty()) {
                    this.h = c3174z80.f;
                    this.f &= -2;
                } else {
                    if ((this.f & 1) == 0) {
                        this.h = new ArrayList(this.h);
                        this.f |= 1;
                    }
                    this.h.addAll(c3174z80.f);
                }
                p();
            }
        } else if (!list.isEmpty()) {
            boolean zIsEmpty = this.i.b.isEmpty();
            C2401q50 c2401q51 = this.i;
            if (zIsEmpty) {
                c2401q51.a = null;
                this.h = c3174z80.f;
                this.f &= -2;
                this.i = null;
            } else {
                c2401q51.a(c3174z80.f);
            }
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.b0.a(C3174z80.class, C2833v80.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C3174z80 c3174z80I = i();
        if (c3174z80I.a()) {
            return c3174z80I;
        }
        throw H0.c(c3174z80I);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C2833v80 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C3174z80 c3174z80 = null;
        try {
            try {
                a((C3174z80) C3174z80.i.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C3174z80 c3174z81 = (C3174z80) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c3174z80 = c3174z81;
                    if (c3174z80 != null) {
                        a(c3174z80);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c3174z80 != null) {
                a(c3174z80);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C2833v80) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.a0;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C3174z80 c3174z80I = i();
        if (c3174z80I.a()) {
            return c3174z80I;
        }
        throw H0.c(c3174z80I);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.b0.a(C3174z80.class, C2833v80.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C3174z80 i() {
        C3174z80 c3174z80 = new C3174z80(this);
        c3174z80.e = this.g;
        C2401q50 c2401q50 = this.i;
        if (c2401q50 == null) {
            if ((this.f & 1) != 0) {
                this.h = Collections.unmodifiableList(this.h);
                this.f &= -2;
            }
            c3174z80.f = this.h;
        } else {
            c3174z80.f = c2401q50.b();
        }
        o();
        return c3174z80;
    }

    public C2833v80(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.g = XmlPullParser.NO_NAMESPACE;
        this.h = Collections.EMPTY_LIST;
        C3174z80 c3174z80 = C3174z80.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C3174z80) {
            return a((C3174z80) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C3174z80.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.b0.a(C3174z80.class, C2833v80.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C3174z80) {
            return a((C3174z80) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C2833v80) c(c2712tk0);
    }
}
