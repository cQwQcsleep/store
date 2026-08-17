package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.m80, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2064m80 extends AbstractC2209ns {
    public static final C2064m80 i = new C2064m80();
    public static final C1892k80 j = new C1892k80();
    public volatile Object e;
    public int f;
    public int g;
    public byte h;

    public C2064m80() {
        this.h = (byte) -1;
        this.e = XmlPullParser.NO_NAMESPACE;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) throws C0741Pd {
        if (!AbstractC2209ns.a(this.e)) {
            AbstractC2209ns.a(abstractC0793Rd, 1, this.e);
        }
        int i2 = this.f;
        if (i2 != 0) {
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(2, 0);
            c0689Nd.f(i2);
        }
        int i3 = this.g;
        if (i3 != 0) {
            C0689Nd c0689Nd2 = (C0689Nd) abstractC0793Rd;
            c0689Nd2.c(3, 0);
            c0689Nd2.f(i3);
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
        int iA = !AbstractC2209ns.a(this.e) ? AbstractC2209ns.a(1, this.e) : 0;
        int i3 = this.f;
        if (i3 != 0) {
            iA = AbstractC0484Ff.a(i3, AbstractC0793Rd.b(2), iA);
        }
        int i4 = this.g;
        if (i4 != 0) {
            iA = AbstractC0484Ff.a(i4, AbstractC0793Rd.b(3), iA);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.TN
    public final SN d() {
        return this == i ? new C1978l80() : new C1978l80().a(this);
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        String strC;
        String strC2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C2064m80)) {
            return super.equals(obj);
        }
        C2064m80 c2064m80 = (C2064m80) obj;
        Object obj2 = this.e;
        if (obj2 instanceof String) {
            strC = (String) obj2;
        } else {
            strC = ((U7) obj2).c();
            this.e = strC;
        }
        Object obj3 = c2064m80.e;
        if (obj3 instanceof String) {
            strC2 = (String) obj3;
        } else {
            strC2 = ((U7) obj3).c();
            c2064m80.e = strC2;
        }
        return strC.equals(strC2) && this.f == c2064m80.f && this.g == c2064m80.g && this.d.equals(c2064m80.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        i.getClass();
        return new C1978l80();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        String strC;
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int iA = AbstractC0432Df.a(AbstractC1468f90.U0, 779, 37, 1, 53);
        Object obj = this.e;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            this.e = strC;
        }
        int iHashCode = this.d.hashCode() + ((AbstractC0406Cf.a((((strC.hashCode() + iA) * 37) + 2) * 53, this.f, 37, 3, 53) + this.g) * 29);
        this.b = iHashCode;
        return iHashCode;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.V0.a(C2064m80.class, C1978l80.class);
    }

    public C2064m80(AbstractC0911Vr abstractC0911Vr) {
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
        return new C1978l80(c0859Tr);
    }
}
