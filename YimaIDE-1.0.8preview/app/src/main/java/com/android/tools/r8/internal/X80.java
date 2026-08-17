package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class X80 extends AbstractC0911Vr {
    public int f;
    public List g;
    public C2401q50 h;
    public Object i;
    public Object j;
    public List k;
    public C2401q50 l;
    public List m;
    public C2401q50 n;

    public X80() {
        super(null);
        List list = Collections.EMPTY_LIST;
        this.g = list;
        this.i = XmlPullParser.NO_NAMESPACE;
        this.j = XmlPullParser.NO_NAMESPACE;
        this.k = list;
        this.m = list;
        Y80 y80 = Y80.k;
    }

    public final X80 a(Y80 y80) {
        String strC;
        if (y80 == Y80.k) {
            return this;
        }
        if (this.h == null) {
            if (!y80.e.isEmpty()) {
                if (this.g.isEmpty()) {
                    this.g = y80.e;
                    this.f &= -2;
                } else {
                    if ((this.f & 1) == 0) {
                        this.g = new ArrayList(this.g);
                        this.f |= 1;
                    }
                    this.g.addAll(y80.e);
                }
                p();
            }
        } else if (!y80.e.isEmpty()) {
            boolean zIsEmpty = this.h.b.isEmpty();
            C2401q50 c2401q50 = this.h;
            if (zIsEmpty) {
                c2401q50.a = null;
                this.g = y80.e;
                this.f &= -2;
                this.h = null;
            } else {
                c2401q50.a(y80.e);
            }
        }
        Object obj = y80.f;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            y80.f = strC;
        }
        if (!strC.isEmpty()) {
            this.i = y80.f;
            p();
        }
        if (!y80.k().isEmpty()) {
            this.j = y80.g;
            p();
        }
        C2401q50 c2401q51 = this.l;
        List list = y80.h;
        if (c2401q51 == null) {
            if (!list.isEmpty()) {
                if (this.k.isEmpty()) {
                    this.k = y80.h;
                    this.f &= -3;
                } else {
                    if ((this.f & 2) == 0) {
                        this.k = new ArrayList(this.k);
                        this.f |= 2;
                    }
                    this.k.addAll(y80.h);
                }
                p();
            }
        } else if (!list.isEmpty()) {
            boolean zIsEmpty2 = this.l.b.isEmpty();
            C2401q50 c2401q52 = this.l;
            if (zIsEmpty2) {
                c2401q52.a = null;
                this.k = y80.h;
                this.f &= -3;
                this.l = null;
            } else {
                c2401q52.a(y80.h);
            }
        }
        C2401q50 c2401q53 = this.n;
        List list2 = y80.i;
        if (c2401q53 == null) {
            if (!list2.isEmpty()) {
                if (this.m.isEmpty()) {
                    this.m = y80.i;
                    this.f &= -5;
                } else {
                    if ((this.f & 4) == 0) {
                        this.m = new ArrayList(this.m);
                        this.f |= 4;
                    }
                    this.m.addAll(y80.i);
                }
                p();
            }
        } else if (!list2.isEmpty()) {
            boolean zIsEmpty3 = this.n.b.isEmpty();
            C2401q50 c2401q54 = this.n;
            if (zIsEmpty3) {
                c2401q54.a = null;
                this.m = y80.i;
                this.f &= -5;
                this.n = null;
            } else {
                c2401q54.a(y80.i);
            }
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.J0.a(Y80.class, X80.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        Y80 y80I = i();
        if (y80I.a()) {
            return y80I;
        }
        throw H0.c(y80I);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final X80 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        Y80 y80 = null;
        try {
            try {
                a((Y80) Y80.l.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                Y80 y81 = (Y80) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    y80 = y81;
                    if (y80 != null) {
                        a(y80);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (y80 != null) {
                a(y80);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (X80) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.I0;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        Y80 y80I = i();
        if (y80I.a()) {
            return y80I;
        }
        throw H0.c(y80I);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.J0.a(Y80.class, X80.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final Y80 i() {
        Y80 y80 = new Y80(this);
        int i = this.f;
        C2401q50 c2401q50 = this.h;
        if (c2401q50 == null) {
            if ((i & 1) != 0) {
                this.g = Collections.unmodifiableList(this.g);
                this.f &= -2;
            }
            y80.e = this.g;
        } else {
            y80.e = c2401q50.b();
        }
        y80.f = this.i;
        y80.g = this.j;
        C2401q50 c2401q51 = this.l;
        if (c2401q51 == null) {
            if ((this.f & 2) != 0) {
                this.k = Collections.unmodifiableList(this.k);
                this.f &= -3;
            }
            y80.h = this.k;
        } else {
            y80.h = c2401q51.b();
        }
        C2401q50 c2401q52 = this.n;
        if (c2401q52 == null) {
            if ((this.f & 4) != 0) {
                this.m = Collections.unmodifiableList(this.m);
                this.f &= -5;
            }
            y80.i = this.m;
        } else {
            y80.i = c2401q52.b();
        }
        o();
        return y80;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    public X80(C0859Tr c0859Tr) {
        super(c0859Tr);
        List list = Collections.EMPTY_LIST;
        this.g = list;
        this.i = XmlPullParser.NO_NAMESPACE;
        this.j = XmlPullParser.NO_NAMESPACE;
        this.k = list;
        this.m = list;
        Y80 y80 = Y80.k;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof Y80) {
            return a((Y80) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return Y80.k;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.J0.a(Y80.class, X80.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof Y80) {
            return a((Y80) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (X80) c(c2712tk0);
    }
}
