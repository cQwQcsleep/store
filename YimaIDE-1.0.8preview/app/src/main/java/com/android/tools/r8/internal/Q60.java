package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0007c;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Q60 extends AbstractC0911Vr {
    public int f;
    public AbstractC2209ns g;

    public Q60() {
        super(null);
        this.f = 0;
        R60 r60 = R60.h;
    }

    public final Q60 a(R60 r60) {
        AbstractC2209ns abstractC2209ns;
        J70 j70;
        AbstractC2209ns abstractC2209ns2;
        Y70 y70;
        AbstractC2209ns abstractC2209ns3;
        F70 f70;
        AbstractC2209ns abstractC2209ns4;
        C3174z80 c3174z80;
        AbstractC2209ns abstractC2209ns5;
        L60 l60;
        AbstractC2209ns abstractC2209ns6;
        O60 o60;
        AbstractC2209ns abstractC2209ns7;
        C70 c70;
        if (r60 == R60.h) {
            return this;
        }
        switch (AbstractC0007c.b(r60.m())) {
            case 0:
                J70 j70L = r60.l();
                if (this.f != 1 || (abstractC2209ns = this.g) == (j70 = J70.m)) {
                    this.g = j70L;
                } else {
                    this.g = j70.d().a((J70) abstractC2209ns).a(j70L).i();
                }
                p();
                this.f = 1;
                break;
            case 1:
                Y70 y71 = r60.e == 2 ? (Y70) r60.f : Y70.g;
                if (this.f != 2 || (abstractC2209ns2 = this.g) == (y70 = Y70.g)) {
                    this.g = y71;
                } else {
                    X70 x70A = y70.d().a((Y70) abstractC2209ns2).a(y71);
                    Y70 y72 = new Y70(x70A);
                    y72.e = x70A.f;
                    x70A.o();
                    this.g = y72;
                }
                p();
                this.f = 2;
                break;
            case 2:
                F70 f71 = r60.e == 3 ? (F70) r60.f : F70.g;
                if (this.f != 3 || (abstractC2209ns3 = this.g) == (f70 = F70.g)) {
                    this.g = f71;
                } else {
                    E70 e70A = f70.d().a((F70) abstractC2209ns3).a(f71);
                    F70 f72 = new F70(e70A);
                    f72.e = e70A.f;
                    e70A.o();
                    this.g = f72;
                }
                p();
                this.f = 3;
                break;
            case XmlPullParser.END_TAG /* 3 */:
                C3174z80 c3174z81 = r60.e == 4 ? (C3174z80) r60.f : C3174z80.h;
                if (this.f != 4 || (abstractC2209ns4 = this.g) == (c3174z80 = C3174z80.h)) {
                    this.g = c3174z81;
                } else {
                    this.g = c3174z80.d().a((C3174z80) abstractC2209ns4).a(c3174z81).i();
                }
                p();
                this.f = 4;
                break;
            case 4:
                L60 l60K = r60.k();
                if (this.f != 5 || (abstractC2209ns5 = this.g) == (l60 = L60.h)) {
                    this.g = l60K;
                } else {
                    J60 j60A = l60.d().a((L60) abstractC2209ns5).a(l60K);
                    L60 l61 = new L60(j60A);
                    l61.e = j60A.f;
                    l61.f = j60A.g;
                    j60A.o();
                    this.g = l61;
                }
                p();
                this.f = 5;
                break;
            case XmlPullParser.CDSECT /* 5 */:
                O60 o61 = r60.e == 6 ? (O60) r60.f : O60.f;
                if (this.f != 6 || (abstractC2209ns6 = this.g) == (o60 = O60.f)) {
                    this.g = o61;
                } else {
                    N60 n60A = o60.d().a((O60) abstractC2209ns6).a(o61);
                    O60 o62 = new O60(n60A);
                    n60A.o();
                    this.g = o62;
                }
                p();
                this.f = 6;
                break;
            case XmlPullParser.ENTITY_REF /* 6 */:
                C70 c71 = r60.e == 7 ? (C70) r60.f : C70.h;
                if (this.f != 7 || (abstractC2209ns7 = this.g) == (c70 = C70.h)) {
                    this.g = c71;
                } else {
                    this.g = c70.d().a((C70) abstractC2209ns7).a(c71).i();
                }
                p();
                this.f = 7;
                break;
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.N.a(R60.class, Q60.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        R60 r60I = i();
        if (r60I.a()) {
            return r60I;
        }
        throw H0.c(r60I);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final Q60 a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        R60 r60 = null;
        try {
            try {
                a((R60) R60.i.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                R60 r61 = (R60) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    r60 = r61;
                    if (r60 != null) {
                        a(r60);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (r60 != null) {
                a(r60);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (Q60) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC1468f90.M;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        R60 r60I = i();
        if (r60I.a()) {
            return r60I;
        }
        throw H0.c(r60I);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC1468f90.N.a(R60.class, Q60.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final R60 i() {
        R60 r60 = new R60(this);
        int i = this.f;
        if (i == 1) {
            r60.f = this.g;
        }
        if (i == 2) {
            r60.f = this.g;
        }
        if (i == 3) {
            r60.f = this.g;
        }
        if (i == 4) {
            r60.f = this.g;
        }
        if (i == 5) {
            r60.f = this.g;
        }
        if (i == 6) {
            r60.f = this.g;
        }
        if (i == 7) {
            r60.f = this.g;
        }
        r60.e = i;
        o();
        return r60;
    }

    public Q60(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.f = 0;
        R60 r60 = R60.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof R60) {
            return a((R60) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return R60.h;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC1468f90.N.a(R60.class, Q60.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(J0 j0) {
        if (j0 instanceof R60) {
            return a((R60) j0);
        }
        super.a(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (Q60) c(c2712tk0);
    }
}
