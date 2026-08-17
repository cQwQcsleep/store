package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.b80, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1128b80 extends AbstractC2209ns {
    public static final C1128b80 g = new C1128b80();
    public static final Z70 h = new Z70();
    public U7 e;
    public byte f;

    public C1128b80() {
        this.f = (byte) -1;
        this.e = U7.c;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) throws C0741Pd {
        if (this.e.size() != 0) {
            U7 u7 = this.e;
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(1, 2);
            c0689Nd.b(u7);
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return g;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int iA;
        int i = this.c;
        if (i != -1) {
            return i;
        }
        if (this.e.size() == 0) {
            iA = 0;
        } else {
            U7 u7 = this.e;
            iA = AbstractC0793Rd.a(u7) + AbstractC0793Rd.b(1);
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
        if (!(obj instanceof C1128b80)) {
            return super.equals(obj);
        }
        C1128b80 c1128b80 = (C1128b80) obj;
        return this.e.equals(c1128b80.e) && this.d.equals(c1128b80.d);
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
        int iHashCode = this.d.hashCode() + ((this.e.hashCode() + AbstractC0432Df.a(AbstractC1468f90.a, 779, 37, 1, 53)) * 29);
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.b.a(C1128b80.class, C1042a80.class);
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final C1042a80 d() {
        return this == g ? new C1042a80() : new C1042a80().a(this);
    }

    public C1128b80(AbstractC0911Vr abstractC0911Vr) {
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
        return new C1042a80(c0859Tr);
    }
}
