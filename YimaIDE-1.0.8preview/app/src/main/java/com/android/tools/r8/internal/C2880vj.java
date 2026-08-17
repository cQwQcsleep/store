package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2880vj extends AbstractC1102as {
    public static final C2880vj k = new C2880vj();
    public static final C2709tj l = new C2709tj();
    public int f;
    public boolean g;
    public int h;
    public List i;
    public byte j;

    public C2880vj() {
        this.j = (byte) -1;
        this.h = 0;
        this.i = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        C1015Zr c1015Zr = new C1015Zr(this);
        if ((this.f & 1) != 0) {
            abstractC0793Rd.a(33, this.g);
        }
        if ((this.f & 2) != 0) {
            abstractC0793Rd.b(34, this.h);
        }
        for (int i = 0; i < this.i.size(); i++) {
            abstractC0793Rd.a(999, (TN) this.i.get(i));
        }
        c1015Zr.a(abstractC0793Rd);
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return k;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i = this.c;
        if (i != -1) {
            return i;
        }
        int iB = (this.f & 1) != 0 ? AbstractC0793Rd.b(33) + 1 : 0;
        if ((this.f & 2) != 0) {
            iB = AbstractC0458Ef.a(this.h, AbstractC0793Rd.b(34), iB);
        }
        for (int i2 = 0; i2 < this.i.size(); i2++) {
            iB = AbstractC1292d60.a((TN) this.i.get(i2), AbstractC0793Rd.b(999), iB);
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
        if (!(obj instanceof C2880vj)) {
            return super.equals(obj);
        }
        C2880vj c2880vj = (C2880vj) obj;
        int i = this.f;
        int i2 = i & 1;
        boolean z = i2 != 0;
        int i3 = c2880vj.f;
        if (z != ((i3 & 1) != 0)) {
            return false;
        }
        if (i2 != 0 && this.g != c2880vj.g) {
            return false;
        }
        int i4 = i & 2;
        if ((i4 != 0) != ((i3 & 2) != 0)) {
            return false;
        }
        return (i4 == 0 || this.h == c2880vj.h) && this.i.equals(c2880vj.i) && this.d.equals(c2880vj.d) && this.e.a().equals(c2880vj.e.a());
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        return k.d();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC0877Uj.M.hashCode() + 779;
        if ((this.f & 1) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 33, 53) + AbstractC1556gB.a(this.g);
        }
        if ((this.f & 2) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 34, 53) + this.h;
        }
        if (this.i.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 999, 53) + this.i.hashCode();
        }
        int iHashCode2 = this.d.hashCode() + (J0.a(iHashCode, this.e.a()) * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC0877Uj.N.a(C2880vj.class, C2794uj.class);
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final C2794uj d() {
        return this == k ? new C2794uj() : new C2794uj().a(this);
    }

    public C2880vj(AbstractC0963Xr abstractC0963Xr) {
        super(abstractC0963Xr);
        this.j = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.j;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        for (int i = 0; i < this.i.size(); i++) {
            if (!((C0851Tj) this.i.get(i)).a()) {
                this.j = (byte) 0;
                return false;
            }
        }
        if (!this.e.c()) {
            this.j = (byte) 0;
            return false;
        }
        this.j = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C2794uj(c0859Tr);
    }
}
