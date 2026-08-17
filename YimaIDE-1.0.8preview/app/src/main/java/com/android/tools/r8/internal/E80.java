package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class E80 extends AbstractC0911Vr {
    public int f;
    public I80 g;
    public Object h;
    public List i;
    public C2401q50 j;

    public E80() {
        super(null);
        this.h = XmlPullParser.NO_NAMESPACE;
        this.i = Collections.EMPTY_LIST;
        F80 f80 = F80.i;
    }

    public final E80 a(F80 f80) {
        String strC;
        if (f80 == F80.i) {
            return this;
        }
        if (f80.e != null) {
            I80 i80K = f80.k();
            I80 i80 = this.g;
            if (i80 != null) {
                H80 h80A = I80.g.d().a(i80).a(i80K);
                I80 i81 = new I80(h80A);
                i81.e = h80A.f;
                h80A.o();
                this.g = i81;
            } else {
                this.g = i80K;
            }
            p();
        }
        Object obj = f80.f;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            f80.f = strC;
        }
        if (!strC.isEmpty()) {
            this.h = f80.f;
            p();
        }
        C2401q50 c2401q50 = this.j;
        List list = f80.g;
        if (c2401q50 == null) {
            if (!list.isEmpty()) {
                if (this.i.isEmpty()) {
                    this.i = f80.g;
                    this.f &= -2;
                } else {
                    if ((this.f & 1) == 0) {
                        this.i = new ArrayList(this.i);
                        this.f |= 1;
                    }
                    this.i.addAll(f80.g);
                }
                p();
            }
        } else if (!list.isEmpty()) {
            boolean zIsEmpty = this.j.b.isEmpty();
            C2401q50 c2401q51 = this.j;
            if (zIsEmpty) {
                c2401q51.a = null;
                this.i = f80.g;
                this.f &= -2;
                this.j = null;
            } else {
                c2401q51.a(f80.g);
            }
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.t.a(F80.class, E80.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        F80 f80I = i();
        if (f80I.a()) {
            return f80I;
        }
        throw H0.c(f80I);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final E80 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        F80 f80 = null;
        try {
            try {
                a((F80) F80.j.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                F80 f81 = (F80) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    f80 = f81;
                    if (f80 != null) {
                        a(f80);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (f80 != null) {
                a(f80);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (E80) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.s;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        F80 f80I = i();
        if (f80I.a()) {
            return f80I;
        }
        throw H0.c(f80I);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.t.a(F80.class, E80.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final F80 i() {
        F80 f80 = new F80(this);
        f80.e = this.g;
        f80.f = this.h;
        C2401q50 c2401q50 = this.j;
        if (c2401q50 == null) {
            if ((this.f & 1) != 0) {
                this.i = Collections.unmodifiableList(this.i);
                this.f &= -2;
            }
            f80.g = this.i;
        } else {
            f80.g = c2401q50.b();
        }
        o();
        return f80;
    }

    public E80(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.h = XmlPullParser.NO_NAMESPACE;
        this.i = Collections.EMPTY_LIST;
        F80 f80 = F80.i;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof F80) {
            return a((F80) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return F80.i;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.t.a(F80.class, E80.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof F80) {
            return a((F80) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (E80) c(c2712tk0);
    }
}
