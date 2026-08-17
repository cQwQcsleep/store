package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class P70 extends AbstractC2209ns {
    public static final P70 h = new P70();
    public static final N70 i = new N70();
    public int e;
    public S70 f;
    public byte g;

    public P70(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
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
                        } else if (iS == 18) {
                            S70 s70 = this.f;
                            R70 r70K = s70 != null ? s70.d() : null;
                            S70 s71 = (S70) abstractC0663Md.a(S70.i, c0415Co);
                            this.f = s71;
                            if (r70K != null) {
                                r70K.a(s71);
                                this.f = r70K.i();
                            }
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
        if (this.f != null) {
            abstractC0793Rd.a(2, k());
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
        if (this.f != null) {
            S70 s70K = k();
            iA += AbstractC0793Rd.a(s70K) + AbstractC0793Rd.b(2);
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
        if (!(obj instanceof P70)) {
            return super.equals(obj);
        }
        P70 p70 = (P70) obj;
        if (this.e != p70.e) {
            return false;
        }
        S70 s70 = this.f;
        if ((s70 != null) != (p70.f != null)) {
            return false;
        }
        return (s70 == null || k().equals(p70.k())) && this.d.equals(p70.d);
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
        int iA = AbstractC0432Df.a(AbstractC1468f90.e, 779, 37, 1, 53) + this.e;
        if (this.f != null) {
            iA = Z50.a(iA, 37, 2, 53) + k().hashCode();
        }
        int iHashCode = this.d.hashCode() + (iA * 29);
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.f.a(P70.class, O70.class);
    }

    public final S70 k() {
        S70 s70 = this.f;
        return s70 == null ? S70.h : s70;
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public final O70 d() {
        return this == h ? new O70() : new O70().a(this);
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
        return new O70(c0859Tr);
    }

    public P70() {
        this.g = (byte) -1;
    }

    public P70(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.g = (byte) -1;
    }
}
