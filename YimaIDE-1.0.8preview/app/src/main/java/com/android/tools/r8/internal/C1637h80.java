package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.h80, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1637h80 extends AbstractC2209ns {
    public static final C1637h80 i = new C1637h80();
    public static final C1212c80 j = new C1212c80();
    public J70 e;
    public P70 f;
    public List g;
    public byte h;

    public C1637h80() {
        this.h = (byte) -1;
        this.g = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if (this.e != null) {
            abstractC0793Rd.a(1, k());
        }
        if (this.f != null) {
            abstractC0793Rd.a(2, l());
        }
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            abstractC0793Rd.a(3, (TN) this.g.get(i2));
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return i;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i2 = this.c;
        if (i2 != -1) {
            return i2;
        }
        int iA = this.e != null ? AbstractC0793Rd.a(k()) + AbstractC0793Rd.b(1) : 0;
        if (this.f != null) {
            iA += AbstractC0793Rd.a(l()) + AbstractC0793Rd.b(2);
        }
        for (int i3 = 0; i3 < this.g.size(); i3++) {
            iA = AbstractC1292d60.a((TN) this.g.get(i3), AbstractC0793Rd.b(3), iA);
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
        if (!(obj instanceof C1637h80)) {
            return super.equals(obj);
        }
        C1637h80 c1637h80 = (C1637h80) obj;
        J70 j70 = this.e;
        if ((j70 != null) != (c1637h80.e != null)) {
            return false;
        }
        if (j70 != null && !k().equals(c1637h80.k())) {
            return false;
        }
        P70 p70 = this.f;
        if ((p70 != null) != (c1637h80.f != null)) {
            return false;
        }
        return (p70 == null || l().equals(c1637h80.l())) && this.g.equals(c1637h80.g) && this.d.equals(c1637h80.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        return i.d();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int iHashCode = AbstractC1468f90.q0.hashCode() + 779;
        if (this.e != null) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + k().hashCode();
        }
        if (this.f != null) {
            iHashCode = Z50.a(iHashCode, 37, 2, 53) + l().hashCode();
        }
        if (this.g.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 3, 53) + this.g.hashCode();
        }
        int iHashCode2 = this.d.hashCode() + (iHashCode * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.r0.a(C1637h80.class, C1296d80.class);
    }

    public final J70 k() {
        J70 j70 = this.e;
        return j70 == null ? J70.m : j70;
    }

    public final P70 l() {
        P70 p70 = this.f;
        return p70 == null ? P70.h : p70;
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public final C1296d80 d() {
        return this == i ? new C1296d80() : new C1296d80().a(this);
    }

    public C1637h80(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.h = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.h;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.h = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C1296d80(c0859Tr);
    }
}
