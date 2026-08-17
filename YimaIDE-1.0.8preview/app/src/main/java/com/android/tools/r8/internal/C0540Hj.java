package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Hj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0540Hj extends AbstractC1102as {
    public static final C0540Hj j = new C0540Hj();
    public static final C0488Fj k = new C0488Fj();
    public int f;
    public boolean g;
    public List h;
    public byte i;

    public C0540Hj() {
        this.i = (byte) -1;
        this.h = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.i;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        for (int i = 0; i < this.h.size(); i++) {
            if (!((C0851Tj) this.h.get(i)).a()) {
                this.i = (byte) 0;
                return false;
            }
        }
        if (this.e.c()) {
            this.i = (byte) 1;
            return true;
        }
        this.i = (byte) 0;
        return false;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return j;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i = this.c;
        if (i != -1) {
            return i;
        }
        int iB = (this.f & 1) != 0 ? AbstractC0793Rd.b(33) + 1 : 0;
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            iB = AbstractC1292d60.a((TN) this.h.get(i2), AbstractC0793Rd.b(999), iB);
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
        if (!(obj instanceof C0540Hj)) {
            return super.equals(obj);
        }
        C0540Hj c0540Hj = (C0540Hj) obj;
        int i = this.f & 1;
        if ((i != 0) != ((c0540Hj.f & 1) != 0)) {
            return false;
        }
        return (i == 0 || this.g == c0540Hj.g) && this.h.equals(c0540Hj.h) && this.d.equals(c0540Hj.d) && this.e.a().equals(c0540Hj.e.a());
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        return j.d();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC0877Uj.K.hashCode() + 779;
        if ((this.f & 1) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 33, 53) + AbstractC1556gB.a(this.g);
        }
        if (this.h.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 999, 53) + this.h.hashCode();
        }
        int iHashCode2 = this.d.hashCode() + (J0.a(iHashCode, this.e.a()) * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC0877Uj.L.a(C0540Hj.class, C0514Gj.class);
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final C0514Gj d() {
        return this == j ? new C0514Gj() : new C0514Gj().a(this);
    }

    public C0540Hj(AbstractC0963Xr abstractC0963Xr) {
        super(abstractC0963Xr);
        this.i = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        C1015Zr c1015Zr = new C1015Zr(this);
        if ((this.f & 1) != 0) {
            abstractC0793Rd.a(33, this.g);
        }
        for (int i = 0; i < this.h.size(); i++) {
            abstractC0793Rd.a(999, (TN) this.h.get(i));
        }
        c1015Zr.a(abstractC0793Rd);
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C0514Gj(c0859Tr);
    }
}
