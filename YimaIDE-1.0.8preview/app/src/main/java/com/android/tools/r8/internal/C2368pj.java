package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2368pj extends AbstractC1102as {
    public static final C2368pj m = new C2368pj();
    public static final C2196nj n = new C2196nj();
    public int f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public List k;
    public byte l;

    public C2368pj() {
        this.l = (byte) -1;
        this.k = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        C1015Zr c1015Zr = new C1015Zr(this);
        if ((this.f & 1) != 0) {
            abstractC0793Rd.a(1, this.g);
        }
        if ((this.f & 2) != 0) {
            abstractC0793Rd.a(2, this.h);
        }
        if ((this.f & 4) != 0) {
            abstractC0793Rd.a(3, this.i);
        }
        if ((this.f & 8) != 0) {
            abstractC0793Rd.a(7, this.j);
        }
        for (int i = 0; i < this.k.size(); i++) {
            abstractC0793Rd.a(999, (TN) this.k.get(i));
        }
        c1015Zr.a(abstractC0793Rd);
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return m;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i = this.c;
        if (i != -1) {
            return i;
        }
        int iB = (this.f & 1) != 0 ? AbstractC0793Rd.b(1) + 1 : 0;
        if ((this.f & 2) != 0) {
            iB = V60.a(2, 1, iB);
        }
        if ((this.f & 4) != 0) {
            iB = V60.a(3, 1, iB);
        }
        if ((this.f & 8) != 0) {
            iB = V60.a(7, 1, iB);
        }
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            iB = AbstractC1292d60.a((TN) this.k.get(i2), AbstractC0793Rd.b(999), iB);
        }
        int iC = this.d.c() + this.e.b() + iB;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C2368pj)) {
            return super.equals(obj);
        }
        C2368pj c2368pj = (C2368pj) obj;
        int i = this.f;
        int i2 = i & 1;
        boolean z = i2 != 0;
        int i3 = c2368pj.f;
        if (z != ((i3 & 1) != 0)) {
            return false;
        }
        if (i2 != 0 && this.g != c2368pj.g) {
            return false;
        }
        int i4 = i & 2;
        if ((i4 != 0) != ((i3 & 2) != 0)) {
            return false;
        }
        if (i4 != 0 && this.h != c2368pj.h) {
            return false;
        }
        int i5 = i & 4;
        if ((i5 != 0) != ((i3 & 4) != 0)) {
            return false;
        }
        if (i5 != 0 && this.i != c2368pj.i) {
            return false;
        }
        int i6 = i & 8;
        if ((i6 != 0) != ((i3 & 8) != 0)) {
            return false;
        }
        return (i6 == 0 || this.j == c2368pj.j) && this.k.equals(c2368pj.k) && this.d.equals(c2368pj.d) && this.e.a().equals(c2368pj.e.a());
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
        int iHashCode = AbstractC0877Uj.A.hashCode() + 779;
        if ((this.f & 1) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + AbstractC1556gB.a(this.g);
        }
        if ((this.f & 2) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 2, 53) + AbstractC1556gB.a(this.h);
        }
        if ((this.f & 4) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 3, 53) + AbstractC1556gB.a(this.i);
        }
        if ((this.f & 8) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 7, 53) + AbstractC1556gB.a(this.j);
        }
        if (this.k.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 999, 53) + this.k.hashCode();
        }
        int iHashCode2 = this.d.hashCode() + (J0.a(iHashCode, this.e.a()) * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC0877Uj.B.a(C2368pj.class, C2282oj.class);
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final C2282oj d() {
        return this == m ? new C2282oj() : new C2282oj().a(this);
    }

    public C2368pj(AbstractC0963Xr abstractC0963Xr) {
        super(abstractC0963Xr);
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
        for (int i = 0; i < this.k.size(); i++) {
            if (!((C0851Tj) this.k.get(i)).a()) {
                this.l = (byte) 0;
                return false;
            }
        }
        if (!this.e.c()) {
            this.l = (byte) 0;
            return false;
        }
        this.l = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C2282oj(c0859Tr);
    }
}
