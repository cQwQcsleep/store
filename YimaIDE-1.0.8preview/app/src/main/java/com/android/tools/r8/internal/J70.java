package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class J70 extends AbstractC2209ns {
    public static final J70 m = new J70();
    public static final G70 n = new G70();
    public int e;
    public int f;
    public volatile Object g;
    public boolean h;
    public C2573s60 i;
    public int j;
    public boolean k;
    public byte l;

    public J70() {
        this.l = (byte) -1;
        this.e = 0;
        this.g = XmlPullParser.NO_NAMESPACE;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) throws C0741Pd {
        if (this.e != I70.a(1)) {
            abstractC0793Rd.b(1, this.e);
        }
        int i = this.f;
        if (i != 0) {
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(2, 0);
            c0689Nd.f(i);
        }
        if (!AbstractC2209ns.a(this.g)) {
            AbstractC2209ns.a(abstractC0793Rd, 3, this.g);
        }
        boolean z = this.h;
        if (z) {
            abstractC0793Rd.a(4, z);
        }
        if (this.i != null) {
            abstractC0793Rd.a(5, l());
        }
        int i2 = this.j;
        if (i2 != 0) {
            C0689Nd c0689Nd2 = (C0689Nd) abstractC0793Rd;
            c0689Nd2.c(6, 0);
            c0689Nd2.f(i2);
        }
        boolean z2 = this.k;
        if (z2) {
            abstractC0793Rd.a(7, z2);
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return m;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int iA;
        int i = this.c;
        if (i != -1) {
            return i;
        }
        if (this.e != I70.a(1)) {
            int i2 = this.e;
            iA = AbstractC0793Rd.a(i2) + AbstractC0793Rd.b(1);
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
        if (this.h) {
            iA = V60.a(4, 1, iA);
        }
        if (this.i != null) {
            C2573s60 c2573s60L = l();
            iA += AbstractC0793Rd.a(c2573s60L) + AbstractC0793Rd.b(5);
        }
        int i4 = this.j;
        if (i4 != 0) {
            iA = AbstractC0484Ff.a(i4, AbstractC0793Rd.b(6), iA);
        }
        if (this.k) {
            iA = V60.a(7, 1, iA);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof J70)) {
            return super.equals(obj);
        }
        J70 j70 = (J70) obj;
        if (this.e != j70.e || this.f != j70.f || !m().equals(j70.m()) || this.h != j70.h) {
            return false;
        }
        C2573s60 c2573s60 = this.i;
        if ((c2573s60 != null) != (j70.i != null)) {
            return false;
        }
        return (c2573s60 == null || l().equals(j70.l())) && this.j == j70.j && this.k == j70.k && this.d.equals(j70.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        return m.d();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iA = AbstractC1556gB.a(this.h) + ((((m().hashCode() + AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0432Df.a(AbstractC1468f90.S, 779, 37, 1, 53), this.e, 37, 2, 53), this.f, 37, 3, 53)) * 37) + 4) * 53);
        if (this.i != null) {
            iA = Z50.a(iA, 37, 5, 53) + l().hashCode();
        }
        int iHashCode = this.d.hashCode() + ((AbstractC1556gB.a(this.k) + AbstractC0406Cf.a(Z50.a(iA, 37, 6, 53), this.j, 37, 7, 53)) * 29);
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.T.a(J70.class, H70.class);
    }

    public final int k() {
        return this.f;
    }

    public final C2573s60 l() {
        C2573s60 c2573s60 = this.i;
        return c2573s60 == null ? C2573s60.g : c2573s60;
    }

    public final String m() {
        Object obj = this.g;
        if (obj instanceof String) {
            return (String) obj;
        }
        String strC = ((U7) obj).c();
        this.g = strC;
        return strC;
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public final H70 d() {
        return this == m ? new H70() : new H70().a(this);
    }

    public J70(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.l = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.l;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.l = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new H70(c0859Tr);
    }
}
