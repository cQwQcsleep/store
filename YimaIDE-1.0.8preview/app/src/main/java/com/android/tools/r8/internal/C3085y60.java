package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.y60, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3085y60 extends AbstractC2209ns {
    public static final C3085y60 h = new C3085y60();
    public static final C2915w60 i = new C2915w60();
    public C0951Xf e;
    public O80 f;
    public byte g;

    public C3085y60(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        this.g = (byte) -1;
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 10) {
                            C0951Xf c0951Xf = this.e;
                            C0536Hf c0536HfK = c0951Xf != null ? c0951Xf.d() : null;
                            C0951Xf c0951Xf2 = (C0951Xf) abstractC0663Md.a(C0951Xf.G, c0415Co);
                            this.e = c0951Xf2;
                            if (c0536HfK != null) {
                                c0536HfK.a(c0951Xf2);
                                this.e = c0536HfK.i();
                            }
                        } else if (iS == 18) {
                            O80 o80 = this.f;
                            N80 n80D = o80 != null ? o80.d() : null;
                            O80 o81 = (O80) abstractC0663Md.a(O80.l, c0415Co);
                            this.f = o81;
                            if (n80D != null) {
                                n80D.a(o81);
                                this.f = n80D.i();
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
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if (this.e != null) {
            abstractC0793Rd.a(1, k());
        }
        if (this.f != null) {
            abstractC0793Rd.a(2, l());
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
        if (this.e != null) {
            C0951Xf c0951XfK = k();
            iA = AbstractC0793Rd.a(c0951XfK) + AbstractC0793Rd.b(1);
        } else {
            iA = 0;
        }
        if (this.f != null) {
            O80 o80L = l();
            iA += AbstractC0793Rd.a(o80L) + AbstractC0793Rd.b(2);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == h ? new C3001x60() : new C3001x60().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C3085y60)) {
            return super.equals(obj);
        }
        C3085y60 c3085y60 = (C3085y60) obj;
        C0951Xf c0951Xf = this.e;
        if ((c0951Xf != null) != (c3085y60.e != null)) {
            return false;
        }
        if ((c0951Xf == null || k().equals(c3085y60.k())) && m() == c3085y60.m()) {
            return (!m() || l().equals(c3085y60.l())) && this.d.equals(c3085y60.d);
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        h.getClass();
        return new C3001x60();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int iHashCode = AbstractC1468f90.I.hashCode() + 779;
        if (this.e != null) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + k().hashCode();
        }
        if (m()) {
            iHashCode = Z50.a(iHashCode, 37, 2, 53) + l().hashCode();
        }
        int iHashCode2 = this.d.hashCode() + (iHashCode * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.J.a(C3085y60.class, C3001x60.class);
    }

    public final C0951Xf k() {
        C0951Xf c0951Xf = this.e;
        return c0951Xf == null ? C0951Xf.F : c0951Xf;
    }

    public final O80 l() {
        O80 o80 = this.f;
        return o80 == null ? O80.k : o80;
    }

    public final boolean m() {
        return this.f != null;
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
        return new C3001x60(c0859Tr);
    }

    public C3085y60() {
        this.g = (byte) -1;
    }

    public C3085y60(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.g = (byte) -1;
    }
}
