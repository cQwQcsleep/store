package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class M70 extends AbstractC2209ns {
    public static final M70 k = new M70();
    public static final K70 l = new K70();
    public C1128b80 e;
    public List f;
    public List g;
    public List h;
    public List i;
    public byte j;

    public M70() {
        this.j = (byte) -1;
        List list = Collections.EMPTY_LIST;
        this.f = list;
        this.g = list;
        this.h = list;
        this.i = list;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if (this.e != null) {
            abstractC0793Rd.a(1, k());
        }
        for (int i = 0; i < this.f.size(); i++) {
            abstractC0793Rd.a(2, (TN) this.f.get(i));
        }
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            abstractC0793Rd.a(3, (TN) this.g.get(i2));
        }
        for (int i3 = 0; i3 < this.h.size(); i3++) {
            abstractC0793Rd.a(4, (TN) this.h.get(i3));
        }
        for (int i4 = 0; i4 < this.i.size(); i4++) {
            abstractC0793Rd.a(5, (TN) this.i.get(i4));
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return k;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i = this.c;
        if (i != -1) {
            return i;
        }
        int iA = this.e != null ? AbstractC0793Rd.a(k()) + AbstractC0793Rd.b(1) : 0;
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            iA = AbstractC1292d60.a((TN) this.f.get(i2), AbstractC0793Rd.b(2), iA);
        }
        for (int i3 = 0; i3 < this.g.size(); i3++) {
            iA = AbstractC1292d60.a((TN) this.g.get(i3), AbstractC0793Rd.b(3), iA);
        }
        for (int i4 = 0; i4 < this.h.size(); i4++) {
            iA = AbstractC1292d60.a((TN) this.h.get(i4), AbstractC0793Rd.b(4), iA);
        }
        for (int i5 = 0; i5 < this.i.size(); i5++) {
            iA = AbstractC1292d60.a((TN) this.i.get(i5), AbstractC0793Rd.b(5), iA);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == k ? new L70() : new L70().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof M70)) {
            return super.equals(obj);
        }
        M70 m70 = (M70) obj;
        C1128b80 c1128b80 = this.e;
        if ((c1128b80 != null) != (m70.e != null)) {
            return false;
        }
        return (c1128b80 == null || k().equals(m70.k())) && this.f.equals(m70.f) && this.g.equals(m70.g) && this.h.equals(m70.h) && this.i.equals(m70.i) && this.d.equals(m70.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        k.getClass();
        return new L70();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC1468f90.k.hashCode() + 779;
        if (this.e != null) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + k().hashCode();
        }
        if (this.f.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 2, 53) + this.f.hashCode();
        }
        if (this.g.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 3, 53) + this.g.hashCode();
        }
        if (this.h.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 4, 53) + this.h.hashCode();
        }
        if (this.i.size() > 0) {
            iHashCode = Z50.a(iHashCode, 37, 5, 53) + this.i.hashCode();
        }
        int iHashCode2 = this.d.hashCode() + (iHashCode * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.l.a(M70.class, L70.class);
    }

    public final C1128b80 k() {
        C1128b80 c1128b80 = this.e;
        return c1128b80 == null ? C1128b80.g : c1128b80;
    }

    public M70(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.j = (byte) -1;
    }

    @Override // com.android.tools.r8.internal.VN
    public final boolean a() {
        byte b = this.j;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.j = (byte) 1;
        return true;
    }

    public static M70 a(byte[] bArr) {
        return (M70) l.a(bArr);
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new L70(c0859Tr);
    }
}
