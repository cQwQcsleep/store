package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.s60, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2573s60 extends AbstractC2209ns {
    public static final C2573s60 g = new C2573s60();
    public static final C2403q60 h = new C2403q60();
    public boolean e;
    public byte f;

    public C2573s60(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
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
                                this.e = abstractC0663Md.c();
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
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        boolean z = this.e;
        if (z) {
            abstractC0793Rd.a(1, z);
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
        int iC = this.d.c() + (this.e ? AbstractC0793Rd.b(1) + 1 : 0);
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C2573s60)) {
            return super.equals(obj);
        }
        C2573s60 c2573s60 = (C2573s60) obj;
        return this.e == c2573s60.e && this.d.equals(c2573s60.d);
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
        int iHashCode = this.d.hashCode() + ((AbstractC1556gB.a(this.e) + AbstractC0432Df.a(AbstractC1468f90.Q, 779, 37, 1, 53)) * 29);
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.R.a(C2573s60.class, C2487r60.class);
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final C2487r60 d() {
        return this == g ? new C2487r60() : new C2487r60().a(this);
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
        return new C2487r60(c0859Tr);
    }

    public C2573s60() {
        this.f = (byte) -1;
    }

    public C2573s60(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.f = (byte) -1;
    }
}
