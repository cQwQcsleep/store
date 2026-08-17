package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ei, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0461Ei extends AbstractC2209ns {
    public static final C0461Ei i = new C0461Ei();
    public static final C0409Ci j = new C0409Ci();
    public int e;
    public int f;
    public int g;
    public byte h;

    public C0461Ei(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        this.h = (byte) -1;
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
            int i2 = this.f;
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(1, 0);
            c0689Nd.e(i2);
        }
        if ((this.e & 2) != 0) {
            int i3 = this.g;
            C0689Nd c0689Nd2 = (C0689Nd) abstractC0793Rd;
            c0689Nd2.c(2, 0);
            c0689Nd2.e(i3);
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
        int iA = (this.e & 1) != 0 ? AbstractC0793Rd.a(1, this.f) : 0;
        if ((this.e & 2) != 0) {
            iA += AbstractC0793Rd.a(2, this.g);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == i ? new C0435Di() : new C0435Di().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0461Ei)) {
            return super.equals(obj);
        }
        C0461Ei c0461Ei = (C0461Ei) obj;
        int i2 = this.e;
        int i3 = i2 & 1;
        boolean z = i3 != 0;
        int i4 = c0461Ei.e;
        if (z != ((i4 & 1) != 0)) {
            return false;
        }
        if (i3 != 0 && this.f != c0461Ei.f) {
            return false;
        }
        int i5 = i2 & 2;
        if ((i5 != 0) != ((i4 & 2) != 0)) {
            return false;
        }
        return (i5 == 0 || this.g == c0461Ei.g) && this.d.equals(c0461Ei.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        i.getClass();
        return new C0435Di();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int iHashCode = AbstractC0877Uj.g.hashCode() + 779;
        int i3 = this.e;
        if ((i3 & 1) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + this.f;
        }
        if ((i3 & 2) != 0) {
            iHashCode = Z50.a(iHashCode, 37, 2, 53) + this.g;
        }
        int iHashCode2 = this.d.hashCode() + (iHashCode * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC0877Uj.h.a(C0461Ei.class, C0435Di.class);
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
        return new C0435Di(c0859Tr);
    }

    public C0461Ei() {
        this.h = (byte) -1;
    }

    public C0461Ei(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.h = (byte) -1;
    }
}
