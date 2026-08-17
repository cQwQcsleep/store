package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1513fj extends AbstractC1102as {
    public static final C1513fj o = new C1513fj();
    public static final C1342dj p = new C1342dj();
    public int f;
    public int g;
    public boolean h;
    public int i;
    public boolean j;
    public boolean k;
    public boolean l;
    public List m;
    public byte n;

    public C1513fj() {
        this.n = (byte) -1;
        this.g = 0;
        this.i = 0;
        this.m = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        C1015Zr c1015Zr = new C1015Zr(this);
        if ((this.f & 1) != 0) {
            abstractC0793Rd.b(1, this.g);
        }
        if ((this.f & 2) != 0) {
            abstractC0793Rd.a(2, this.h);
        }
        if ((this.f & 16) != 0) {
            abstractC0793Rd.a(3, this.k);
        }
        if ((this.f & 8) != 0) {
            abstractC0793Rd.a(5, this.j);
        }
        if ((this.f & 4) != 0) {
            abstractC0793Rd.b(6, this.i);
        }
        if ((this.f & 32) != 0) {
            abstractC0793Rd.a(10, this.l);
        }
        for (int i = 0; i < this.m.size(); i++) {
            abstractC0793Rd.a(999, (TN) this.m.get(i));
        }
        c1015Zr.a(abstractC0793Rd);
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return o;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i = this.c;
        if (i != -1) {
            return i;
        }
        int iA = (this.f & 1) != 0 ? AbstractC0793Rd.a(this.g) + AbstractC0793Rd.b(1) : 0;
        if ((this.f & 2) != 0) {
            iA = V60.a(2, 1, iA);
        }
        if ((this.f & 16) != 0) {
            iA = V60.a(3, 1, iA);
        }
        if ((this.f & 8) != 0) {
            iA = V60.a(5, 1, iA);
        }
        if ((this.f & 4) != 0) {
            iA = AbstractC0458Ef.a(this.i, AbstractC0793Rd.b(6), iA);
        }
        if ((this.f & 32) != 0) {
            iA = V60.a(10, 1, iA);
        }
        for (int i2 = 0; i2 < this.m.size(); i2++) {
            iA = AbstractC1292d60.a((TN) this.m.get(i2), AbstractC0793Rd.b(999), iA);
        }
        int iC = this.d.c() + this.e.b() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C1513fj)) {
            return super.equals(obj);
        }
        C1513fj c1513fj = (C1513fj) obj;
        int i = this.f & 1;
        if ((i != 0) != ((c1513fj.f & 1) != 0)) {
            return false;
        }
        if ((i != 0 && this.g != c1513fj.g) || k() != c1513fj.k()) {
            return false;
        }
        if (k() && this.h != c1513fj.h) {
            return false;
        }
        int i2 = this.f;
        int i3 = i2 & 4;
        boolean z = i3 != 0;
        int i4 = c1513fj.f;
        if (z != ((i4 & 4) != 0)) {
            return false;
        }
        if (i3 != 0 && this.i != c1513fj.i) {
            return false;
        }
        int i5 = i2 & 8;
        if ((i5 != 0) != ((i4 & 8) != 0)) {
            return false;
        }
        if (i5 != 0 && this.j != c1513fj.j) {
            return false;
        }
        int i6 = i2 & 16;
        if ((i6 != 0) != ((i4 & 16) != 0)) {
            return false;
        }
        if (i6 != 0 && this.k != c1513fj.k) {
            return false;
        }
        int i7 = i2 & 32;
        if ((i7 != 0) != ((i4 & 32) != 0)) {
            return false;
        }
        return (i7 == 0 || this.l == c1513fj.l) && this.m.equals(c1513fj.m) && this.d.equals(c1513fj.d) && this.e.a().equals(c1513fj.e.a());
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        return o.d();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC0877Uj.C.hashCode() + 779;
        if ((this.f & 1) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + this.g;
        }
        if (k()) {
            iHashCode = Z50.a(iHashCode, 37, 2, 53) + AbstractC1556gB.a(this.h);
        }
        int i2 = this.f;
        if ((i2 & 4) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 6, 53) + this.i;
        }
        if ((i2 & 8) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 5, 53) + AbstractC1556gB.a(this.j);
        }
        if ((this.f & 16) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 3, 53) + AbstractC1556gB.a(this.k);
        }
        if ((this.f & 32) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 10, 53) + AbstractC1556gB.a(this.l);
        }
        if (this.m.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 999, 53) + this.m.hashCode();
        }
        int iHashCode2 = this.d.hashCode() + (J0.a(iHashCode, this.e.a()) * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC0877Uj.D.a(C1513fj.class, C1427ej.class);
    }

    public final boolean k() {
        return (this.f & 2) != 0;
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public final C1427ej d() {
        return this == o ? new C1427ej() : new C1427ej().a(this);
    }

    public C1513fj(AbstractC0963Xr abstractC0963Xr) {
        super(abstractC0963Xr);
        this.n = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.n;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        for (int i = 0; i < this.m.size(); i++) {
            if (!((C0851Tj) this.m.get(i)).a()) {
                this.n = (byte) 0;
                return false;
            }
        }
        if (!this.e.c()) {
            this.n = (byte) 0;
            return false;
        }
        this.n = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C1427ej(c0859Tr);
    }
}
