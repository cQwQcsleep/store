package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Hf, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0536Hf extends AbstractC0911Vr {
    public int A;
    public int B;
    public int C;
    public int D;
    public Object E;
    public int f;
    public int g;
    public Object h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    public int y;
    public int z;

    public C0536Hf() {
        super(null);
        this.h = XmlPullParser.NO_NAMESPACE;
        this.i = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        this.D = 0;
        this.E = XmlPullParser.NO_NAMESPACE;
        C0951Xf c0951Xf = C0951Xf.F;
    }

    public final C0536Hf a(C0951Xf c0951Xf) {
        String strC;
        String strC2;
        if (c0951Xf == C0951Xf.F) {
            return this;
        }
        int i = c0951Xf.e;
        if (i != 0) {
            this.f = i;
            p();
        }
        int i2 = c0951Xf.f;
        if (i2 != 0) {
            this.g = i2;
            p();
        }
        Object obj = c0951Xf.g;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            c0951Xf.g = strC;
        }
        if (!strC.isEmpty()) {
            this.h = c0951Xf.g;
            p();
        }
        int i3 = c0951Xf.h;
        if (i3 != 0) {
            this.i = i3;
            p();
        }
        int i4 = c0951Xf.i;
        if (i4 != 0) {
            this.j = i4;
            p();
        }
        int i5 = c0951Xf.j;
        if (i5 != 0) {
            this.k = i5;
            p();
        }
        int i6 = c0951Xf.k;
        if (i6 != 0) {
            this.l = i6;
            p();
        }
        int i7 = c0951Xf.l;
        if (i7 != 0) {
            this.m = i7;
            p();
        }
        int i8 = c0951Xf.m;
        if (i8 != 0) {
            this.n = i8;
            p();
        }
        int i9 = c0951Xf.n;
        if (i9 != 0) {
            this.o = i9;
            p();
        }
        int i10 = c0951Xf.o;
        if (i10 != 0) {
            this.p = i10;
            p();
        }
        int i11 = c0951Xf.p;
        if (i11 != 0) {
            this.q = i11;
            p();
        }
        int i12 = c0951Xf.q;
        if (i12 != 0) {
            this.r = i12;
            p();
        }
        int i13 = c0951Xf.r;
        if (i13 != 0) {
            this.s = i13;
            p();
        }
        int i14 = c0951Xf.s;
        if (i14 != 0) {
            this.t = i14;
            p();
        }
        int i15 = c0951Xf.t;
        if (i15 != 0) {
            this.u = i15;
            p();
        }
        int i16 = c0951Xf.u;
        if (i16 != 0) {
            this.v = i16;
            p();
        }
        int i17 = c0951Xf.v;
        if (i17 != 0) {
            this.w = i17;
            p();
        }
        int i18 = c0951Xf.w;
        if (i18 != 0) {
            this.x = i18;
            p();
        }
        int i19 = c0951Xf.x;
        if (i19 != 0) {
            this.y = i19;
            p();
        }
        int i20 = c0951Xf.y;
        if (i20 != 0) {
            this.z = i20;
            p();
        }
        int i21 = c0951Xf.z;
        if (i21 != 0) {
            this.A = i21;
            p();
        }
        int i22 = c0951Xf.A;
        if (i22 != 0) {
            this.B = i22;
            p();
        }
        int i23 = c0951Xf.B;
        if (i23 != 0) {
            this.C = i23;
            p();
        }
        int i24 = c0951Xf.C;
        if (i24 != 0) {
            this.D = i24;
            p();
        }
        Object obj2 = c0951Xf.D;
        if (obj2 instanceof String) {
            strC2 = (String) obj2;
        } else {
            strC2 = ((U7) obj2).c();
            c0951Xf.D = strC2;
        }
        if (!strC2.isEmpty()) {
            this.E = c0951Xf.D;
            p();
        }
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC0977Yf.b.a(C0951Xf.class, C0536Hf.class), c1856jk).b(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.SN
    public final TN build() {
        C0951Xf c0951XfI = i();
        if (c0951XfI.a()) {
            return c0951XfI;
        }
        throw H0.c(c0951XfI);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C0536Hf a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) throws Throwable {
        C0951Xf c0951Xf = null;
        try {
            try {
                a((C0951Xf) C0951Xf.G.a(abstractC0663Md, c0415Co));
                return this;
            } catch (RB e) {
                C0951Xf c0951Xf2 = (C0951Xf) e.b;
                try {
                    throw e.a();
                } catch (Throwable th) {
                    th = th;
                    c0951Xf = c0951Xf2;
                    if (c0951Xf != null) {
                        a(c0951Xf);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c0951Xf != null) {
                a(c0951Xf);
            }
            throw th;
        }
    }

    public final Object clone() {
        return (C0536Hf) k();
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final AbstractC0911Vr d(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0, com.android.tools.r8.internal.WN
    public final C0955Xj e() {
        return AbstractC0977Yf.a;
    }

    @Override // com.android.tools.r8.internal.H0
    public final J0 h() {
        C0951Xf c0951XfI = i();
        if (c0951XfI.a()) {
            return c0951XfI;
        }
        throw H0.c(c0951XfI);
    }

    @Override // com.android.tools.r8.internal.AbstractC0911Vr
    public final C2123ms n() {
        return AbstractC0977Yf.b.a(C0951Xf.class, C0536Hf.class);
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final C0951Xf i() {
        C0951Xf c0951Xf = new C0951Xf(this);
        c0951Xf.e = this.f;
        c0951Xf.f = this.g;
        c0951Xf.g = this.h;
        c0951Xf.h = this.i;
        c0951Xf.i = this.j;
        c0951Xf.j = this.k;
        c0951Xf.k = this.l;
        c0951Xf.l = this.m;
        c0951Xf.m = this.n;
        c0951Xf.n = this.o;
        c0951Xf.o = this.p;
        c0951Xf.p = this.q;
        c0951Xf.q = this.r;
        c0951Xf.r = this.s;
        c0951Xf.s = this.t;
        c0951Xf.t = this.u;
        c0951Xf.u = this.v;
        c0951Xf.v = this.w;
        c0951Xf.w = this.x;
        c0951Xf.x = this.y;
        c0951Xf.y = this.z;
        c0951Xf.z = this.A;
        c0951Xf.A = this.B;
        c0951Xf.B = this.C;
        c0951Xf.C = this.D;
        c0951Xf.D = this.E;
        o();
        return c0951Xf;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(C2712tk0 c2712tk0) {
        this.e = c2712tk0;
        p();
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 b(J0 j0) {
        if (j0 instanceof C0951Xf) {
            return a((C0951Xf) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return C0951Xf.F;
    }

    public C0536Hf(C0859Tr c0859Tr) {
        super(c0859Tr);
        this.h = XmlPullParser.NO_NAMESPACE;
        this.i = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        this.D = 0;
        this.E = XmlPullParser.NO_NAMESPACE;
        C0951Xf c0951Xf = C0951Xf.F;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C1856jk c1856jk, Object obj) {
        C2123ms.a(AbstractC0977Yf.b.a(C0951Xf.class, C0536Hf.class), c1856jk).a(this, obj);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    /* JADX INFO: renamed from: a */
    public final H0 b(J0 j0) {
        if (j0 instanceof C0951Xf) {
            return a((C0951Xf) j0);
        }
        super.b(j0);
        return this;
    }

    @Override // com.android.tools.r8.internal.H0
    public final H0 a(C2712tk0 c2712tk0) {
        return (C0536Hf) c(c2712tk0);
    }
}
