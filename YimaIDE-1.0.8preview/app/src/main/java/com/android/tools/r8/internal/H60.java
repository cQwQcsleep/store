package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class H60 extends AbstractC2209ns {
    public static final H60 g = new H60();
    public static final F60 h = new F60();
    public int e;
    public byte f;

    public H60(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        this.f = (byte) -1;
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        while (!z) {
            try {
                try {
                    try {
                        int iS = abstractC0663Md.s();
                        if (iS != 0) {
                            if (iS == 8) {
                                this.e = abstractC0663Md.t();
                            } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        RB rb = new RB(e);
                        rb.b = this;
                        throw rb;
                    }
                } catch (RB e2) {
                    e2.b = this;
                    throw e2;
                }
            } catch (Throwable th) {
                this.d = c2285ok0.build();
                throw th;
            }
        }
        this.d = c2285ok0.build();
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) throws C0741Pd {
        int i = this.e;
        if (i != 0) {
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(1, 0);
            c0689Nd.f(i);
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return g;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int iC;
        int i = this.c;
        if (i != -1) {
            return i;
        }
        int i2 = this.e;
        if (i2 != 0) {
            iC = AbstractC0793Rd.c(i2) + AbstractC0793Rd.b(1);
        } else {
            iC = 0;
        }
        int iC2 = this.d.c() + iC;
        this.c = iC2;
        return iC2;
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof H60)) {
            return super.equals(obj);
        }
        H60 h60 = (H60) obj;
        return this.e == h60.e && this.d.equals(h60.d);
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
        int iHashCode = this.d.hashCode() + ((AbstractC0432Df.a(AbstractC1468f90.E, 779, 37, 1, 53) + this.e) * 29);
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.F.a(H60.class, G60.class);
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final G60 d() {
        return this == g ? new G60() : new G60().a(this);
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
        return new G60(c0859Tr);
    }

    public H60() {
        this.f = (byte) -1;
    }

    public H60(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.f = (byte) -1;
    }
}
