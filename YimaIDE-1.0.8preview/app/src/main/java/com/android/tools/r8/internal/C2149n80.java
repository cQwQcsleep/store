package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.n80, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2149n80 extends AbstractC2209ns {
    public static final C2149n80 h = new C2149n80();
    public static final C1723i80 i = new C1723i80();
    public volatile Object e;
    public List f;
    public byte g;

    public C2149n80() {
        this.g = (byte) -1;
        this.e = XmlPullParser.NO_NAMESPACE;
        this.f = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) {
        if (!AbstractC2209ns.a(this.e)) {
            AbstractC2209ns.a(abstractC0793Rd, 1, this.e);
        }
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            abstractC0793Rd.a(2, (TN) this.f.get(i2));
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
        for (int i3 = 0; i3 < this.f.size(); i3++) {
            iA = AbstractC1292d60.a((TN) this.f.get(i3), AbstractC0793Rd.b(2), iA);
        }
        int iC = this.d.c() + iA;
        this.c = iC;
        return iC;
    }

    @Override // com.android.tools.r8.internal.J0
    public final boolean equals(Object obj) {
        String strC;
        String strC2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C2149n80)) {
            return super.equals(obj);
        }
        C2149n80 c2149n80 = (C2149n80) obj;
        Object obj2 = this.e;
        if (obj2 instanceof String) {
            strC = (String) obj2;
        } else {
            strC = ((U7) obj2).c();
            this.e = strC;
        }
        Object obj3 = c2149n80.e;
        if (obj3 instanceof String) {
            strC2 = (String) obj3;
        } else {
            strC2 = ((U7) obj3).c();
            c2149n80.e = strC2;
        }
        return strC.equals(strC2) && this.f.equals(c2149n80.f) && this.d.equals(c2149n80.d);
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
        String strC;
        int i2 = this.b;
        if (i2 != 0) {
            return i2;
        }
        int iA = AbstractC0432Df.a(AbstractC1468f90.S0, 779, 37, 1, 53);
        Object obj = this.e;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            this.e = strC;
        }
        int iHashCode = strC.hashCode() + iA;
        if (this.f.size() > 0) {
            iHashCode = this.f.hashCode() + Z50.a(iHashCode, 37, 2, 53);
        }
        int iHashCode2 = this.d.hashCode() + (iHashCode * 29);
        this.b = iHashCode2;
        return iHashCode2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.T0.a(C2149n80.class, C1807j80.class);
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final C1807j80 d() {
        return this == h ? new C1807j80() : new C1807j80().a(this);
    }

    public C2149n80(AbstractC0911Vr abstractC0911Vr) {
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
        return new C1807j80(c0859Tr);
    }
}
