package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C80 extends AbstractC2209ns {
    public static final C80 h = new C80();
    public static final A80 i = new A80();
    public volatile Object e;
    public volatile Object f;
    public byte g;

    public C80() {
        this.g = (byte) -1;
        this.e = XmlPullParser.NO_NAMESPACE;
        this.f = XmlPullParser.NO_NAMESPACE;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if (!AbstractC2209ns.a(this.e)) {
            AbstractC2209ns.a(abstractC0793Rd, 1, this.e);
        }
        if (!AbstractC2209ns.a(this.f)) {
            AbstractC2209ns.a(abstractC0793Rd, 2, this.f);
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return h;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i2 = this.c;
        if (i2 != -1) {
            return i2;
        }
        int iA = !AbstractC2209ns.a(this.e) ? AbstractC2209ns.a(1, this.e) : 0;
        if (!AbstractC2209ns.a(this.f)) {
            iA += AbstractC2209ns.a(2, this.f);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == h ? new B80() : new B80().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        String strC;
        String strC2;
        String strC3;
        String strC4;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C80)) {
            return super.equals(obj);
        }
        C80 c80 = (C80) obj;
        Object obj2 = this.e;
        if (obj2 instanceof String) {
            strC = (String) obj2;
        } else {
            strC = ((U7) obj2).c();
            this.e = strC;
        }
        Object obj3 = c80.e;
        if (obj3 instanceof String) {
            strC2 = (String) obj3;
        } else {
            strC2 = ((U7) obj3).c();
            c80.e = strC2;
        }
        if (!strC.equals(strC2)) {
            return false;
        }
        Object obj4 = this.f;
        if (obj4 instanceof String) {
            strC3 = (String) obj4;
        } else {
            strC3 = ((U7) obj4).c();
            this.f = strC3;
        }
        Object obj5 = c80.f;
        if (obj5 instanceof String) {
            strC4 = (String) obj5;
        } else {
            strC4 = ((U7) obj5).c();
            c80.f = strC4;
        }
        return strC3.equals(strC4) && this.d.equals(c80.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        h.getClass();
        return new B80();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        String strC;
        String strC2;
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int iA = AbstractC0432Df.a(AbstractC1468f90.g, 779, 37, 1, 53);
        Object obj = this.e;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            this.e = strC;
        }
        int iHashCode = (((strC.hashCode() + iA) * 37) + 2) * 53;
        Object obj2 = this.f;
        if (obj2 instanceof String) {
            strC2 = (String) obj2;
        } else {
            strC2 = ((U7) obj2).c();
            this.f = strC2;
        }
        int iHashCode2 = this.d.hashCode() + ((strC2.hashCode() + iHashCode) * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.h.a(C80.class, B80.class);
    }

    public C80(AbstractC0911Vr abstractC0911Vr) {
        super(abstractC0911Vr);
        this.g = (byte) -1;
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
        return new B80(c0859Tr);
    }
}
