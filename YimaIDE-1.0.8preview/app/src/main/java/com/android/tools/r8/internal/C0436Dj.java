package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Dj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0436Dj extends AbstractC0911Vr {
    public int f;
    public Object g;
    public List h;
    public C2401q50 i;
    public C0540Hj j;

    public C0436Dj() {
        super(null);
        this.g = XmlPullParser.NO_NAMESPACE;
        this.h = Collections.EMPTY_LIST;
    }

    public final C0436Dj a(C0462Ej c0462Ej) {
        C0540Hj c0540Hj;
        C0540Hj c0540Hj2;
        if (c0462Ej == C0462Ej.j) {
            return this;
        }
        if ((c0462Ej.e & 1) != 0) {
            this.f |= 1;
            this.g = c0462Ej.f;
            p();
        }
        C2401q50 c2401q50 = this.i;
        List list = c0462Ej.g;
        if (c2401q50 == null) {
            if (!list.isEmpty()) {
                if (this.h.isEmpty()) {
                    this.h = c0462Ej.g;
                    this.f &= -3;
                } else {
                    if ((this.f & 2) == 0) {
                        this.h = new ArrayList(this.h);
                        this.f |= 2;
                    }
                    this.h.addAll(c0462Ej.g);
                }
                p();
            }
        } else if (!list.isEmpty()) {
            boolean zIsEmpty = this.i.b.isEmpty();
            C2401q50 c2401q51 = this.i;
            if (zIsEmpty) {
                c2401q51.a = null;
                this.h = c0462Ej.g;
                this.f &= -3;
                this.i = null;
            } else {
                c2401q51.a(c0462Ej.g);
            }
        }
        if (c0462Ej.m()) {
            C0540Hj c0540HjL = c0462Ej.l();
            if ((this.f & 4) == 0 || (c0540Hj = this.j) == null || c0540Hj == (c0540Hj2 = C0540Hj.j)) {
                this.j = c0540HjL;
            } else {
                this.j = c0540Hj2.d().a(c0540Hj).a(c0540HjL).i();
            }
            p();
            this.f |= 4;
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C0462Ej) {
            return a((C0462Ej) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C0462Ej c0462EjI = i();
        if (c0462EjI.a()) {
            return c0462EjI;
        }
        throw H0.c(c0462EjI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C0436Dj a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C0462Ej c0462Ej = null;
        try {
            try {
                a((C0462Ej) C0462Ej.k.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C0462Ej c0462Ej2 = (C0462Ej) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c0462Ej = c0462Ej2;
                    if (c0462Ej != null) {
                        a(c0462Ej);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c0462Ej != null) {
                a(c0462Ej);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C0436Dj) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0877Uj.u;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C0462Ej c0462EjI = i();
        if (c0462EjI.a()) {
            return c0462EjI;
        }
        throw H0.c(c0462EjI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0877Uj.v.a(C0462Ej.class, C0436Dj.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C0462Ej i() {
        C0462Ej c0462Ej = new C0462Ej(this);
        int i = this.f;
        int i2 = (i & 1) != 0 ? 1 : 0;
        c0462Ej.f = this.g;
        C2401q50 c2401q50 = this.i;
        if (c2401q50 == null) {
            if ((this.f & 2) != 0) {
                this.h = Collections.unmodifiableList(this.h);
                this.f &= -3;
            }
            c0462Ej.g = this.h;
        } else {
            c0462Ej.g = c2401q50.b();
        }
        if ((i & 4) != 0) {
            c0462Ej.h = this.j;
            i2 |= 2;
        }
        c0462Ej.e = i2;
        o();
        return c0462Ej;
    }

    public C0436Dj(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.g = XmlPullParser.NO_NAMESPACE;
        this.h = Collections.EMPTY_LIST;
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

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C0462Ej.j;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C0462Ej) {
            return a((C0462Ej) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C0436Dj) c(c2712tk0);
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(n(), c1856jk).a(this, obj);
        return this;
    }
}
