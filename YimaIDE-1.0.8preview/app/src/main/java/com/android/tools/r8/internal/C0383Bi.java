package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Bi, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0383Bi extends AbstractC2209ns {
    public static final C0383Bi j = new C0383Bi();
    public static final C3219zi k = new C3219zi();
    public int e;
    public int f;
    public int g;
    public C0954Xi h;
    public byte i;

    public C0383Bi(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        this.i = (byte) -1;
        c0415Co.getClass();
        C2285ok0 c2285ok0 = new C2285ok0();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int iS = abstractC0663Md.s();
                    if (iS != 0) {
                        if (iS == 8) {
                            this.e |= 1;
                            this.f = abstractC0663Md.j();
                        } else if (iS == 16) {
                            this.e |= 2;
                            this.g = abstractC0663Md.j();
                        } else if (iS == 26) {
                            C0928Wi c0928WiK = (this.e & 4) != 0 ? this.h.d() : null;
                            C0954Xi c0954Xi = (C0954Xi) abstractC0663Md.a(C0954Xi.i, c0415Co);
                            this.h = c0954Xi;
                            if (c0928WiK != null) {
                                c0928WiK.a(c0954Xi);
                                this.h = c0928WiK.i();
                            }
                            this.e |= 4;
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
        if ((this.e & 1) != 0) {
            int i = this.f;
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(1, 0);
            c0689Nd.e(i);
        }
        if ((this.e & 2) != 0) {
            int i2 = this.g;
            C0689Nd c0689Nd2 = (C0689Nd) abstractC0793Rd;
            c0689Nd2.c(2, 0);
            c0689Nd2.e(i2);
        }
        if ((this.e & 4) != 0) {
            abstractC0793Rd.a(3, k());
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return j;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i = this.c;
        if (i != -1) {
            return i;
        }
        int iA = (this.e & 1) != 0 ? AbstractC0793Rd.a(1, this.f) : 0;
        if ((this.e & 2) != 0) {
            iA += AbstractC0793Rd.a(2, this.g);
        }
        if ((this.e & 4) != 0) {
            iA += AbstractC0793Rd.a(k()) + AbstractC0793Rd.b(3);
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
        if (!(obj instanceof C0383Bi)) {
            return super.equals(obj);
        }
        C0383Bi c0383Bi = (C0383Bi) obj;
        int i = this.e;
        int i2 = i & 1;
        boolean z = i2 != 0;
        int i3 = c0383Bi.e;
        if (z != ((i3 & 1) != 0)) {
            return false;
        }
        if (i2 != 0 && this.f != c0383Bi.f) {
            return false;
        }
        int i4 = i & 2;
        if ((i4 != 0) != ((i3 & 2) != 0)) {
            return false;
        }
        if ((i4 == 0 || this.g == c0383Bi.g) && l() == c0383Bi.l()) {
            return (!l() || k().equals(c0383Bi.k())) && this.d.equals(c0383Bi.d);
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        return j.d();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC0877Uj.e.hashCode() + 779;
        int i2 = this.e;
        if ((i2 & 1) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + this.f;
        }
        if ((i2 & 2) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 2, 53) + this.g;
        }
        if (l()) {
            iHashCode = Z50.a(iHashCode, 37, 3, 53) + k().hashCode();
        }
        int iHashCode2 = this.d.hashCode() + (iHashCode * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC0877Uj.f.a(C0383Bi.class, C0357Ai.class);
    }

    public final C0954Xi k() {
        C0954Xi c0954Xi = this.h;
        return c0954Xi == null ? C0954Xi.h : c0954Xi;
    }

    public final boolean l() {
        return (this.e & 4) != 0;
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public final C0357Ai d() {
        return this == j ? new C0357Ai() : new C0357Ai().a(this);
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.i;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if (l() && !k().a()) {
            this.i = (byte) 0;
            return false;
        }
        this.i = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C0357Ai(c0859Tr);
    }

    public C0383Bi() {
        this.i = (byte) -1;
    }

    public C0383Bi(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.i = (byte) -1;
    }
}
