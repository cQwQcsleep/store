package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.j70, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1805j70 extends AbstractC2209ns {
    public static final C1805j70 i = new C1805j70();
    public static final C1635h70 j = new C1635h70();
    public C2062m70 e;
    public volatile Object f;
    public List g;
    public byte h;

    public C1805j70() {
        this.h = (byte) -1;
        this.f = XmlPullParser.NO_NAMESPACE;
        this.g = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if (this.e != null) {
            abstractC0793Rd.a(1, k());
        }
        if (!AbstractC2209ns.a(this.f)) {
            AbstractC2209ns.a(abstractC0793Rd, 2, this.f);
        }
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            abstractC0793Rd.a(3, (TN) this.g.get(i2));
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
        int iA = this.e != null ? AbstractC0793Rd.a(k()) + AbstractC0793Rd.b(1) : 0;
        if (!AbstractC2209ns.a(this.f)) {
            iA += AbstractC2209ns.a(2, this.f);
        }
        for (int i3 = 0; i3 < this.g.size(); i3++) {
            iA = AbstractC1292d60.a((TN) this.g.get(i3), AbstractC0793Rd.b(3), iA);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == i ? new C1721i70() : new C1721i70().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C1805j70)) {
            return super.equals(obj);
        }
        C1805j70 c1805j70 = (C1805j70) obj;
        C2062m70 c2062m70 = this.e;
        if ((c2062m70 != null) != (c1805j70.e != null)) {
            return false;
        }
        return (c2062m70 == null || k().equals(c1805j70.k())) && l().equals(c1805j70.l()) && this.g.equals(c1805j70.g) && this.d.equals(c1805j70.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        i.getClass();
        return new C1721i70();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int iHashCode = AbstractC1468f90.o.hashCode() + 779;
        if (this.e != null) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + k().hashCode();
        }
        int iHashCode2 = l().hashCode() + Z50.a(iHashCode, 37, 2, 53);
        if (this.g.size() > 0) {
            iHashCode2 = this.g.hashCode() + Z50.a(iHashCode2, 37, 3, 53);
        }
        int iHashCode3 = this.d.hashCode() + (iHashCode2 * 29);
        this.b = iHashCode3;
        return iHashCode3;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.p.a(C1805j70.class, C1721i70.class);
    }

    public final C2062m70 k() {
        C2062m70 c2062m70 = this.e;
        return c2062m70 == null ? C2062m70.g : c2062m70;
    }

    public final String l() {
        Object obj = this.f;
        if (obj instanceof String) {
            return (String) obj;
        }
        String strC = ((U7) obj).c();
        this.f = strC;
        return strC;
    }

    public C1805j70(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.h = (byte) -1;
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
        return new C1721i70(c0859Tr);
    }
}
