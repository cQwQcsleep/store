package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Xi, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0954Xi extends AbstractC1102as {
    public static final C0954Xi h = new C0954Xi();
    public static final C0902Vi i = new C0902Vi();
    public List f;
    public byte g;

    public C0954Xi() {
        this.g = (byte) -1;
        this.f = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.g;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            if (!((C0851Tj) this.f.get(i2)).a()) {
                this.g = (byte) 0;
                return false;
            }
        }
        if (this.e.c()) {
            this.g = (byte) 1;
            return true;
        }
        this.g = (byte) 0;
        return false;
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return h;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i2 = this.c;
        if (i2 != -1) {
            return i2;
        }
        int iA = 0;
        for (int i3 = 0; i3 < this.f.size(); i3++) {
            iA = AbstractC1292d60.a((TN) this.f.get(i3), AbstractC0793Rd.b(999), iA);
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
        if (!(obj instanceof C0954Xi)) {
            return super.equals(obj);
        }
        C0954Xi c0954Xi = (C0954Xi) obj;
        return this.f.equals(c0954Xi.f) && this.d.equals(c0954Xi.d) && this.e.a().equals(c0954Xi.e.a());
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        return h.d();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int iHashCode = AbstractC0877Uj.i.hashCode() + 779;
        if (this.f.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 999, 53) + this.f.hashCode();
        }
        int iHashCode2 = this.d.hashCode() + (J0.a(iHashCode, this.e.a()) * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC0877Uj.j.a(C0954Xi.class, C0928Wi.class);
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final C0928Wi d() {
        return this == h ? new C0928Wi() : new C0928Wi().a(this);
    }

    public C0954Xi(AbstractC0963Xr abstractC0963Xr) {
        super(abstractC0963Xr);
        this.g = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        C1015Zr c1015Zr = new C1015Zr(this);
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            abstractC0793Rd.a(999, (TN) this.f.get(i2));
        }
        c1015Zr.a(abstractC0793Rd);
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C0928Wi(c0859Tr);
    }
}
