package com.android.tools.r8.internal;

import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L80 extends AbstractC2209ns {
    public static final L80 h = new L80();
    public static final J80 i = new J80();
    public long e;
    public long f;
    public byte g;

    public L80(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        this.g = (byte) -1;
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 8) {
                            this.e = abstractC0663Md.u();
                        } else if (iS == 16) {
                            this.f = abstractC0663Md.u();
                        } else if (!c2285ok0.a(iS, abstractC0663Md)) {
                        }
                    }
                    z = true;
                } catch (RB e) {
                    e.b = this;
                    throw e;
                } catch (IOException e2) {
                    RB rb = new RB(e2);
                    rb.b = this;
                    throw rb;
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
        long j = this.e;
        if (j != 0) {
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(1, 0);
            c0689Nd.c(j);
        }
        long j2 = this.f;
        if (j2 != 0) {
            C0689Nd c0689Nd2 = (C0689Nd) abstractC0793Rd;
            c0689Nd2.c(2, 0);
            c0689Nd2.c(j2);
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return h;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int iA;
        int i2 = this.c;
        if (i2 != -1) {
            return i2;
        }
        long j = this.e;
        if (j != 0) {
            iA = AbstractC0793Rd.a(j) + AbstractC0793Rd.b(1);
        } else {
            iA = 0;
        }
        long j2 = this.f;
        if (j2 != 0) {
            iA += AbstractC0793Rd.a(j2) + AbstractC0793Rd.b(2);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == h ? new K80() : new K80().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof L80)) {
            return super.equals(obj);
        }
        L80 l80 = (L80) obj;
        return this.e == l80.e && this.f == l80.f && this.d.equals(l80.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        h.getClass();
        return new K80();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int iA = AbstractC0432Df.a(AbstractC1468f90.W0, 779, 37, 1, 53);
        long j = this.e;
        Charset charset = AbstractC1556gB.a;
        int iA2 = AbstractC0406Cf.a(iA, (int) (j ^ (j >>> 32)), 37, 2, 53);
        long j2 = this.f;
        int iHashCode = this.d.hashCode() + ((iA2 + ((int) (j2 ^ (j2 >>> 32)))) * 29);
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.X0.a(L80.class, K80.class);
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
        this.g = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new K80(c0859Tr);
    }

    public L80() {
        this.g = (byte) -1;
    }

    public L80(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.g = (byte) -1;
    }
}
