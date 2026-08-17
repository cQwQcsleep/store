package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.p60, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2317p60 extends AbstractC2209ns {
    public static final C2317p60 j = new C2317p60();
    public static final C1888k60 k = new C1888k60();
    public int e;
    public int f;
    public int g;
    public List h;
    public byte i;

    public C2317p60() {
        this.i = (byte) -1;
        this.h = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) throws C0741Pd {
        int i = this.e;
        if (i != 0) {
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(1, 0);
            c0689Nd.f(i);
        }
        int i2 = this.f;
        if (i2 != 0) {
            C0689Nd c0689Nd2 = (C0689Nd) abstractC0793Rd;
            c0689Nd2.c(2, 0);
            c0689Nd2.e(i2);
        }
        int i3 = this.g;
        if (i3 != 0) {
            C0689Nd c0689Nd3 = (C0689Nd) abstractC0793Rd;
            c0689Nd3.c(3, 0);
            c0689Nd3.e(i3);
        }
        for (int i4 = 0; i4 < this.h.size(); i4++) {
            abstractC0793Rd.a(4, (TN) this.h.get(i4));
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
        int i2 = this.e;
        int iC = i2 != 0 ? AbstractC0793Rd.c(i2) + AbstractC0793Rd.b(1) : 0;
        int i3 = this.f;
        if (i3 != 0) {
            iC += AbstractC0793Rd.a(2, i3);
        }
        int i4 = this.g;
        if (i4 != 0) {
            iC += AbstractC0793Rd.a(3, i4);
        }
        for (int i5 = 0; i5 < this.h.size(); i5++) {
            iC = AbstractC1292d60.a((TN) this.h.get(i5), AbstractC0793Rd.b(4), iC);
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
        if (!(obj instanceof C2317p60)) {
            return super.equals(obj);
        }
        C2317p60 c2317p60 = (C2317p60) obj;
        return this.e == c2317p60.e && this.f == c2317p60.f && this.g == c2317p60.g && this.h.equals(c2317p60.h) && this.d.equals(c2317p60.d);
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
        int iA = AbstractC0406Cf.a(AbstractC0406Cf.a(AbstractC0432Df.a(AbstractC1468f90.m0, 779, 37, 1, 53), this.e, 37, 2, 53), this.f, 37, 3, 53) + this.g;
        if (this.h.size() > 0) {
            iA = Z50.a(iA, 37, 4, 53) + this.h.hashCode();
        }
        int iHashCode = this.d.hashCode() + (iA * 29);
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.n0.a(C2317p60.class, C1974l60.class);
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final C1974l60 d() {
        return this == j ? new C1974l60() : new C1974l60().a(this);
    }

    public C2317p60(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.i = (byte) -1;
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
        this.i = (byte) 1;
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C1974l60(c0859Tr);
    }
}
