package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.t80, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2662t80 extends AbstractC2209ns {
    public static final C2662t80 g = new C2662t80();
    public static final C2235o80 h = new C2235o80();
    public List e;
    public byte f;

    public C2662t80() {
        this.f = (byte) -1;
        this.e = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        for (int i = 0; i < this.e.size(); i++) {
            abstractC0793Rd.a(1, (TN) this.e.get(i));
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return g;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i = this.c;
        if (i != -1) {
            return i;
        }
        int iA = 0;
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            iA = AbstractC1292d60.a((TN) this.e.get(i2), AbstractC0793Rd.b(1), iA);
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
        if (!(obj instanceof C2662t80)) {
            return super.equals(obj);
        }
        C2662t80 c2662t80 = (C2662t80) obj;
        return this.e.equals(c2662t80.e) && this.d.equals(c2662t80.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        return g.d();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC1468f90.u0.hashCode() + 779;
        if (this.e.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + this.e.hashCode();
        }
        int iHashCode2 = this.d.hashCode() + (iHashCode * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.v0.a(C2662t80.class, C2321p80.class);
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final C2321p80 d() {
        return this == g ? new C2321p80() : new C2321p80().a(this);
    }

    public C2662t80(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.f = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.f;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.f = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C2321p80(c0859Tr);
    }
}
