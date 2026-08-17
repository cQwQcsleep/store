package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class V70 extends AbstractC2209ns {
    public static final V70 h = new V70();
    public static final T70 i = new T70();
    public P70 e;
    public int f;
    public byte g;

    public V70(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
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
                            P70 p70 = this.e;
                            O70 o70L = p70 != null ? p70.d() : null;
                            P70 p71 = (P70) abstractC0663Md.a(P70.i, c0415Co);
                            this.e = p71;
                            if (o70L != null) {
                                o70L.a(p71);
                                this.e = o70L.i();
                            }
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
        if (this.e != null) {
            abstractC0793Rd.a(1, k());
        }
        int i2 = this.f;
        if (i2 != 0) {
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(2, 0);
            c0689Nd.f(i2);
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
            P70 p70K = k();
            iA = AbstractC0793Rd.a(p70K) + AbstractC0793Rd.b(1);
        } else {
            iA = 0;
        }
        int i3 = this.f;
        if (i3 != 0) {
            iA = AbstractC0484Ff.a(i3, AbstractC0793Rd.b(2), iA);
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
        if (!(obj instanceof V70)) {
            return super.equals(obj);
        }
        V70 v70 = (V70) obj;
        P70 p70 = this.e;
        if ((p70 != null) != (v70.e != null)) {
            return false;
        }
        return (p70 == null || k().equals(v70.k())) && this.f == v70.f && this.d.equals(v70.d);
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
        int iHashCode = AbstractC1468f90.C.hashCode() + 779;
        if (this.e != null) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + k().hashCode();
        }
        int iHashCode2 = this.d.hashCode() + ((Z50.a(iHashCode, 37, 2, 53) + this.f) * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.D.a(V70.class, U70.class);
    }

    public final P70 k() {
        P70 p70 = this.e;
        return p70 == null ? P70.h : p70;
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public final U70 d() {
        return this == h ? new U70() : new U70().a(this);
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
        return new U70(c0859Tr);
    }

    public V70() {
        this.g = (byte) -1;
    }

    public V70(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.g = (byte) -1;
    }
}
