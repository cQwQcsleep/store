package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.g70, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1550g70 extends AbstractC2209ns {
    public static final C1210c70 k = new C1210c70();
    public static final C1550g70 l = new C1550g70();
    public static final C1294d70 m = new C1294d70();
    public P70 e;
    public volatile Object f;
    public List g;
    public int h;
    public int i;
    public byte j;

    public C1550g70() {
        this.j = (byte) -1;
        this.f = XmlPullParser.NO_NAMESPACE;
        this.g = Collections.EMPTY_LIST;
    }

    @Override // com.android.tools.r8.internal.TN
    public final void a(AbstractC0793Rd abstractC0793Rd) throws C0741Pd {
        c();
        if (this.e != null) {
            abstractC0793Rd.a(1, k());
        }
        if (!AbstractC2209ns.a(this.f)) {
            AbstractC2209ns.a(abstractC0793Rd, 2, this.f);
        }
        if (new C1384eB(this.g).b.size() > 0) {
            abstractC0793Rd.f(26);
            abstractC0793Rd.f(this.h);
        }
        for (int i = 0; i < this.g.size(); i++) {
            abstractC0793Rd.e(((Integer) this.g.get(i)).intValue());
        }
        int i2 = this.i;
        if (i2 != 0) {
            C0689Nd c0689Nd = (C0689Nd) abstractC0793Rd;
            c0689Nd.c(4, 0);
            c0689Nd.f(i2);
        }
        this.d.a(abstractC0793Rd);
    }

    @Override // com.android.tools.r8.internal.WN
    public final J0 b() {
        return l;
    }

    @Override // com.android.tools.r8.internal.TN
    public final int c() {
        int i = this.c;
        if (i != -1) {
            return i;
        }
        int iA = this.e != null ? AbstractC0793Rd.a(k()) + AbstractC0793Rd.b(1) : 0;
        if (!AbstractC2209ns.a(this.f)) {
            iA += AbstractC2209ns.a(2, this.f);
        }
        int iA2 = 0;
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            iA2 += AbstractC0793Rd.a(((Integer) this.g.get(i2)).intValue());
        }
        int iA3 = iA + iA2;
        if (!new C1384eB(this.g).isEmpty()) {
            iA3 = iA3 + 1 + AbstractC0793Rd.c(iA2);
        }
        this.h = iA2;
        int i3 = this.i;
        if (i3 != 0) {
            iA3 = AbstractC0484Ff.a(i3, AbstractC0793Rd.b(4), iA3);
        }
        int iC = this.d.c() + iA3;
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
        if (!(obj instanceof C1550g70)) {
            return super.equals(obj);
        }
        C1550g70 c1550g70 = (C1550g70) obj;
        P70 p70 = this.e;
        if ((p70 != null) != (c1550g70.e != null)) {
            return false;
        }
        if (p70 != null && !k().equals(c1550g70.k())) {
            return false;
        }
        Object obj2 = this.f;
        if (obj2 instanceof String) {
            strC = (String) obj2;
        } else {
            strC = ((U7) obj2).c();
            this.f = strC;
        }
        Object obj3 = c1550g70.f;
        if (obj3 instanceof String) {
            strC2 = (String) obj3;
        } else {
            strC2 = ((U7) obj3).c();
            c1550g70.f = strC2;
        }
        return strC.equals(strC2) && this.g.equals(c1550g70.g) && this.i == c1550g70.i && this.d.equals(c1550g70.d);
    }

    @Override // com.android.tools.r8.internal.WN
    public final C2712tk0 g() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.J0
    public final H0 h() {
        return l.d();
    }

    @Override // com.android.tools.r8.internal.J0
    public final int hashCode() {
        String strC;
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int iHashCode = AbstractC1468f90.A.hashCode() + 779;
        if (this.e != null) {
            iHashCode = Z50.a(iHashCode, 37, 1, 53) + k().hashCode();
        }
        int iA = Z50.a(iHashCode, 37, 2, 53);
        Object obj = this.f;
        if (obj instanceof String) {
            strC = (String) obj;
        } else {
            strC = ((U7) obj).c();
            this.f = strC;
        }
        int iHashCode2 = strC.hashCode() + iA;
        if (this.g.size() > 0) {
            iHashCode2 = this.g.hashCode() + Z50.a(iHashCode2, 37, 3, 53);
        }
        int iHashCode3 = this.d.hashCode() + ((Z50.a(iHashCode2, 37, 4, 53) + this.i) * 29);
        this.b = iHashCode3;
        return iHashCode3;
    }

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final C2123ms j() {
        return AbstractC1468f90.B.a(C1550g70.class, C1378e70.class);
    }

    public final P70 k() {
        P70 p70 = this.e;
        return p70 == null ? P70.h : p70;
    }

    @Override // com.android.tools.r8.internal.TN
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public final C1378e70 d() {
        return this == l ? new C1378e70() : new C1378e70().a(this);
    }

    public C1550g70(AbstractC0911Vr abstractC0911Vr) {
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

    @Override // com.android.tools.r8.internal.AbstractC2209ns
    public final H0 a(C0859Tr c0859Tr) {
        return new C1378e70(c0859Tr);
    }
}
