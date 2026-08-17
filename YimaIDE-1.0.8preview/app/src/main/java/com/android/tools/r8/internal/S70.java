package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class S70 extends AbstractC2209ns {
    public static final S70 h = new S70();
    public static final Q70 i = new Q70();
    public int e;
    public int f;
    public byte g;

    public S70(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
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
                            this.e = abstractC0663Md.t();
                        } else if (iS == 16) {
                            this.f = abstractC0663Md.t();
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
        int i2 = this.e;
        if (i2 != 0) {
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(1, 0);
            c0689Nd.f(i2);
        }
        int i3 = this.f;
        if (i3 != 0) {
            C0689Nd c0689Nd2 = (C0689Nd) abstractC0793Rd;
            c0689Nd2.c(2, 0);
            c0689Nd2.f(i3);
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
        int i3 = this.e;
        if (i3 != 0) {
            iA = AbstractC0793Rd.c(i3) + AbstractC0793Rd.b(1);
        } else {
            iA = 0;
        }
        int i4 = this.f;
        if (i4 != 0) {
            iA = AbstractC0484Ff.a(i4, AbstractC0793Rd.b(2), iA);
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
        if (!(obj instanceof S70)) {
            return super.equals(obj);
        }
        S70 s70 = (S70) obj;
        return this.e == s70.e && this.f == s70.f && this.d.equals(s70.d);
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
        int iHashCode = this.d.hashCode() + ((AbstractC0406Cf.a(AbstractC0432Df.a(AbstractC1468f90.c, 779, 37, 1, 53), this.e, 37, 2, 53) + this.f) * 29);
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.d.a(S70.class, R70.class);
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final R70 d() {
        return this == h ? new R70() : new R70().a(this);
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
        return new R70(c0859Tr);
    }

    public S70() {
        this.g = (byte) -1;
    }

    public S70(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.g = (byte) -1;
    }
}
