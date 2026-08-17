package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Xf, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0951Xf extends AbstractC2209ns {
    public static final C0951Xf F = new C0951Xf();
    public static final C0510Gf G = new C0510Gf();
    public int A;
    public int B;
    public int C;
    public volatile Object D;
    public byte E;
    public int e;
    public int f;
    public volatile Object g;
    public int h;
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

    public C0951Xf() {
        this.E = (byte) -1;
        this.g = XmlPullParser.NO_NAMESPACE;
        this.h = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.C = 0;
        this.D = XmlPullParser.NO_NAMESPACE;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) throws C0741Pd {
        int i = this.e;
        if (i != 0) {
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(1, 0);
            c0689Nd.f(i);
        }
        int i2 = this.f;
        if (i2 != 0) {
            C0689Nd c0689Nd2 = (C0689Nd) abstractC0793Rd;
            c0689Nd2.c(2, 0);
            c0689Nd2.f(i2);
        }
        if (!AbstractC2209ns.a(this.g)) {
            AbstractC2209ns.a(abstractC0793Rd, 3, this.g);
        }
        if (this.h != AbstractC0665Mf.a(1)) {
            abstractC0793Rd.b(4, this.h);
        }
        int i3 = this.i;
        if (i3 != 0) {
            C0689Nd c0689Nd3 = (C0689Nd) abstractC0793Rd;
            c0689Nd3.c(5, 0);
            c0689Nd3.f(i3);
        }
        int i4 = this.j;
        if (i4 != 0) {
            C0689Nd c0689Nd4 = (C0689Nd) abstractC0793Rd;
            c0689Nd4.c(6, 0);
            c0689Nd4.f(i4);
        }
        int i5 = this.k;
        if (i5 != 0) {
            C0689Nd c0689Nd5 = (C0689Nd) abstractC0793Rd;
            c0689Nd5.c(7, 0);
            c0689Nd5.f(i5);
        }
        int i6 = this.l;
        if (i6 != 0) {
            C0689Nd c0689Nd6 = (C0689Nd) abstractC0793Rd;
            c0689Nd6.c(8, 0);
            c0689Nd6.f(i6);
        }
        int i7 = this.m;
        if (i7 != 0) {
            C0689Nd c0689Nd7 = (C0689Nd) abstractC0793Rd;
            c0689Nd7.c(9, 0);
            c0689Nd7.f(i7);
        }
        if (this.n != AbstractC0795Rf.a(1)) {
            abstractC0793Rd.b(10, this.n);
        }
        if (this.o != AbstractC0769Qf.a(1)) {
            abstractC0793Rd.b(11, this.o);
        }
        if (this.p != AbstractC0821Sf.a(1)) {
            abstractC0793Rd.b(12, this.p);
        }
        if (this.q != AbstractC0925Wf.a(1)) {
            abstractC0793Rd.b(13, this.q);
        }
        if (this.r != AbstractC0588Jf.a(1)) {
            abstractC0793Rd.b(14, this.r);
        }
        if (this.s != AbstractC0743Pf.a(1)) {
            abstractC0793Rd.b(15, this.s);
        }
        if (this.t != AbstractC0899Vf.a(1)) {
            abstractC0793Rd.b(16, this.t);
        }
        if (this.u != AbstractC0873Uf.a(1)) {
            abstractC0793Rd.b(17, this.u);
        }
        int i8 = this.v;
        if (i8 != 0) {
            C0689Nd c0689Nd8 = (C0689Nd) abstractC0793Rd;
            c0689Nd8.c(18, 0);
            c0689Nd8.f(i8);
        }
        if (this.w != AbstractC0847Tf.a(1)) {
            abstractC0793Rd.b(19, this.w);
        }
        if (this.x != AbstractC0640Lf.a(1)) {
            abstractC0793Rd.b(20, this.x);
        }
        if (this.y != AbstractC0614Kf.a(1)) {
            abstractC0793Rd.b(21, this.y);
        }
        if (this.z != AbstractC0691Nf.a(1)) {
            abstractC0793Rd.b(22, this.z);
        }
        if (this.A != AbstractC0717Of.a(1)) {
            abstractC0793Rd.b(23, this.A);
        }
        int i9 = this.B;
        if (i9 != 0) {
            C0689Nd c0689Nd9 = (C0689Nd) abstractC0793Rd;
            c0689Nd9.c(24, 0);
            c0689Nd9.f(i9);
        }
        if (!AbstractC2209ns.a(this.D)) {
            AbstractC2209ns.a(abstractC0793Rd, 25, this.D);
        }
        if (this.C != AbstractC0562If.a(1)) {
            abstractC0793Rd.b(26, this.C);
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return F;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int iA;
        int i = this.c;
        if (i != -1) {
            return i;
        }
        int i2 = this.e;
        if (i2 != 0) {
            iA = AbstractC0793Rd.c(i2) + AbstractC0793Rd.b(1);
        } else {
            iA = 0;
        }
        int i3 = this.f;
        if (i3 != 0) {
            iA = AbstractC0484Ff.a(i3, AbstractC0793Rd.b(2), iA);
        }
        if (!AbstractC2209ns.a(this.g)) {
            iA += AbstractC2209ns.a(3, this.g);
        }
        if (this.h != AbstractC0665Mf.a(1)) {
            iA = AbstractC0458Ef.a(this.h, AbstractC0793Rd.b(4), iA);
        }
        int i4 = this.i;
        if (i4 != 0) {
            iA = AbstractC0484Ff.a(i4, AbstractC0793Rd.b(5), iA);
        }
        int i5 = this.j;
        if (i5 != 0) {
            iA = AbstractC0484Ff.a(i5, AbstractC0793Rd.b(6), iA);
        }
        int i6 = this.k;
        if (i6 != 0) {
            iA = AbstractC0484Ff.a(i6, AbstractC0793Rd.b(7), iA);
        }
        int i7 = this.l;
        if (i7 != 0) {
            iA = AbstractC0484Ff.a(i7, AbstractC0793Rd.b(8), iA);
        }
        int i8 = this.m;
        if (i8 != 0) {
            iA = AbstractC0484Ff.a(i8, AbstractC0793Rd.b(9), iA);
        }
        if (this.n != AbstractC0795Rf.a(1)) {
            iA = AbstractC0458Ef.a(this.n, AbstractC0793Rd.b(10), iA);
        }
        if (this.o != AbstractC0769Qf.a(1)) {
            iA = AbstractC0458Ef.a(this.o, AbstractC0793Rd.b(11), iA);
        }
        if (this.p != AbstractC0821Sf.a(1)) {
            iA = AbstractC0458Ef.a(this.p, AbstractC0793Rd.b(12), iA);
        }
        if (this.q != AbstractC0925Wf.a(1)) {
            iA = AbstractC0458Ef.a(this.q, AbstractC0793Rd.b(13), iA);
        }
        if (this.r != AbstractC0588Jf.a(1)) {
            iA = AbstractC0458Ef.a(this.r, AbstractC0793Rd.b(14), iA);
        }
        if (this.s != AbstractC0743Pf.a(1)) {
            iA = AbstractC0458Ef.a(this.s, AbstractC0793Rd.b(15), iA);
        }
        if (this.t != AbstractC0899Vf.a(1)) {
            iA = AbstractC0458Ef.a(this.t, AbstractC0793Rd.b(16), iA);
        }
        if (this.u != AbstractC0873Uf.a(1)) {
            iA = AbstractC0458Ef.a(this.u, AbstractC0793Rd.b(17), iA);
        }
        int i9 = this.v;
        if (i9 != 0) {
            iA = AbstractC0484Ff.a(i9, AbstractC0793Rd.b(18), iA);
        }
        if (this.w != AbstractC0847Tf.a(1)) {
            iA = AbstractC0458Ef.a(this.w, AbstractC0793Rd.b(19), iA);
        }
        if (this.x != AbstractC0640Lf.a(1)) {
            iA = AbstractC0458Ef.a(this.x, AbstractC0793Rd.b(20), iA);
        }
        if (this.y != AbstractC0614Kf.a(1)) {
            iA = AbstractC0458Ef.a(this.y, AbstractC0793Rd.b(21), iA);
        }
        if (this.z != AbstractC0691Nf.a(1)) {
            iA = AbstractC0458Ef.a(this.z, AbstractC0793Rd.b(22), iA);
        }
        if (this.A != AbstractC0717Of.a(1)) {
            iA = AbstractC0458Ef.a(this.A, AbstractC0793Rd.b(23), iA);
        }
        int i10 = this.B;
        if (i10 != 0) {
            iA = AbstractC0484Ff.a(i10, AbstractC0793Rd.b(24), iA);
        }
        if (!AbstractC2209ns.a(this.D)) {
            iA += AbstractC2209ns.a(25, this.D);
        }
        if (this.C != AbstractC0562If.a(1)) {
            iA = AbstractC0458Ef.a(this.C, AbstractC0793Rd.b(26), iA);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        String strC;
        String strC2;
        String strC3;
        String strC4;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0951Xf)) {
            return super.equals(obj);
        }
        C0951Xf c0951Xf = (C0951Xf) obj;
        if (this.e != c0951Xf.e || this.f != c0951Xf.f) {
            return false;
        }
        Object obj2 = this.g;
        if (obj2 instanceof String) {
            strC = (String) obj2;
        } else {
            strC = ((U7) obj2).c();
            this.g = strC;
        }
        Object obj3 = c0951Xf.g;
        if (obj3 instanceof String) {
            strC2 = (String) obj3;
        } else {
            strC2 = ((U7) obj3).c();
            c0951Xf.g = strC2;
        }
        if (!strC.equals(strC2) || this.h != c0951Xf.h || this.i != c0951Xf.i || this.j != c0951Xf.j || this.k != c0951Xf.k || this.l != c0951Xf.l || this.m != c0951Xf.m || this.n != c0951Xf.n || this.o != c0951Xf.o || this.p != c0951Xf.p || this.q != c0951Xf.q || this.r != c0951Xf.r || this.s != c0951Xf.s || this.t != c0951Xf.t || this.u != c0951Xf.u || this.v != c0951Xf.v || this.w != c0951Xf.w || this.x != c0951Xf.x || this.y != c0951Xf.y || this.z != c0951Xf.z || this.A != c0951Xf.A || this.B != c0951Xf.B || this.C != c0951Xf.C) {
            return false;
        }
        Object obj4 = this.D;
        if (obj4 instanceof String) {
            strC3 = (String) obj4;
        } else {
            strC3 = ((U7) obj4).c();
            this.D = strC3;
        }
        Object obj5 = c0951Xf.D;
        if (obj5 instanceof String) {
            strC4 = (String) obj5;
        } else {
            strC4 = ((U7) obj5).c();
            c0951Xf.D = strC4;
        }
        return strC3.equals(strC4) && this.d.equals(c0951Xf.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        return F.d();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        String strC;
        String strC2;
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iA = AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0432Df.a(AbstractC0977Yf.a, 779, 37, 1, 53), this.e, 37, 2, 53), this.f, 37, 3, 53);
        Object obj = this.g;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            this.g = strC;
        }
        int iA2 = AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0406Cf.a((((strC.hashCode() + iA) * 37) + 4) * 53, this.h, 37, 5, 53), this.i, 37, 6, 53), this.j, 37, 7, 53), this.k, 37, 8, 53), this.l, 37, 9, 53), this.m, 37, 10, 53), this.n, 37, 11, 53), this.o, 37, 12, 53), this.p, 37, 13, 53), this.q, 37, 14, 53), this.r, 37, 15, 53), this.s, 37, 16, 53), this.t, 37, 17, 53), this.u, 37, 18, 53), this.v, 37, 19, 53), this.w, 37, 20, 53), this.x, 37, 21, 53), this.y, 37, 22, 53), this.z, 37, 23, 53), this.A, 37, 24, 53), this.B, 37, 26, 53), this.C, 37, 25, 53);
        Object obj2 = this.D;
        if (obj2 instanceof String) {
            strC2 = (String) obj2;
        } else {
            strC2 = ((U7) obj2).c();
            this.D = strC2;
        }
        int iHashCode = this.d.hashCode() + ((strC2.hashCode() + iA2) * 29);
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC0977Yf.b.a(C0951Xf.class, C0536Hf.class);
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final C0536Hf d() {
        return this == F ? new C0536Hf() : new C0536Hf().a(this);
    }

    public C0951Xf(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.E = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.E;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.E = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C0536Hf(c0859Tr);
    }
}
